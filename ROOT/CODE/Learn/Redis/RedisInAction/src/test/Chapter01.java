package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class Chapter01 {
	private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
	private static final int VOTE_SCORE = 432;
	private static final int ARTICLES_PER_PAGE = 25;

	public static void main(String[] args) {
		new Chapter01().run();
	}

	public void run() {
		Jedis conn = new Jedis("localhost");
		// select命令用于选择数据库号，默认使用0号数据库，这里表示使用15号数据库
		conn.select(15);

		String articleId = postArticle(conn, "username", "A title", "http://www.google.com");
		System.out.println("We posted a new article with id: " + articleId);
		System.out.println("Its HASH looks like:");
		/*Redis Hgetall 命令用于返回哈希表中，所有的字段和值。*/
		Map<String, String> articleData = conn.hgetAll("article:" + articleId);
		for (Map.Entry<String, String> entry : articleData.entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
		}

		System.out.println();

		articleVote(conn, "other_user", "article:" + articleId);
		String votes = conn.hget("article:" + articleId, "votes");//获取投票人数
		System.out.println("We voted for the article, it now has votes: " + votes);
		assert Integer.parseInt(votes) > 1;

		System.out.println("The currently highest-scoring articles are:");
		List<Map<String, String>> articles = getArticles(conn, 1);//获取第1页的所有文章，按投票人数逆序排序
		printArticles(articles);
		assert articles.size() >= 1;

		addGroups(conn, articleId, new String[] { "new-group" });//将文章加入新的分组
		System.out.println("We added the article to a new group, other articles include:");
		articles = getGroupArticles(conn, "new-group", 1);//获取新分组中第1页的所有文章
		printArticles(articles);
		assert articles.size() >= 1;
	}

	/**
	 * 添加新文章
	 * @param conn
	 * @param user
	 * @param title
	 * @param link
	 * @return
	 */
	public String postArticle(Jedis conn, String user, String title, String link) {
		// incr命令增加key值，这里的key为article:
		String articleId = String.valueOf(conn.incr("article:"));

		String voted = "voted:" + articleId;
		// 在集合中新增元素
		conn.sadd(voted, user);//将文章作者添加到文章的已投票用户名单中
		/* Redis Expire 命令用于设置 key 的过期时间，key 过期后将不再可用。单位以秒计。 */
		/* 以下实例中我们为键 voted 设置了过期时间为 ONE_WEEK_IN_SECONDS 秒钟，该时间过后该键会自动删除。 */
		conn.expire(voted, ONE_WEEK_IN_SECONDS);

		long now = System.currentTimeMillis() / 1000;
		String article = "article:" + articleId;
		HashMap<String, String> articleData = new HashMap<String, String>();
		articleData.put("title", title);
		articleData.put("link", link);
		articleData.put("user", user);
		articleData.put("now", String.valueOf(now));
		articleData.put("votes", "1");

		/*
		 * Redis Hmset 命令用于同时将多个 field-value (字段-值)对设置到哈希表中。 此命令会覆盖哈希表中已存在的字段。
		 * 如果哈希表不存在，会创建一个空哈希表，并执行 HMSET 操作。
		 */
		conn.hmset(article, articleData);

		/*
		 * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
		 * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。 分数值可以是整数值或双精度浮点数。
		 * 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。 当 key 存在但不是有序集类型时，返回一个错误。
		 */
		conn.zadd("score:", now + VOTE_SCORE, article);//加入文章得分有序列表
		conn.zadd("time:", now, article);//加入文章创建时间有序列表

		return articleId;
	}

	/**
	 * 为文章投票
	 * @param conn
	 * @param user
	 * @param article
	 */
	public void articleVote(Jedis conn, String user, String article) {
		long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
		/* Redis Zscore 命令返回有序集中，成员的分数值。 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil 。 */
		if (conn.zscore("time:", article) < cutoff) {//如果文章过期，则无法投票
			return;
		}

		String articleId = article.substring(article.indexOf(':') + 1);
		if (conn.sadd("voted:" + articleId, user) == 1) {//投票成功
			/*
			 * Redis Zincrby 命令对有序集合中指定成员的分数加上增量 increment(第二个参数) 可以通过传递一个负数值 increment
			 * ，让分数减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。 当 key
			 * 不存在，或分数不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment
			 * member 。 当 key 不是有序集类型时，返回一个错误。 分数值可以是整数值或双精度浮点数。
			 */
			conn.zincrby("score:", VOTE_SCORE, article);//为有序集合中的成员进行加分
			/*
			 * Redis Hincrby 命令用于为哈希表中的字段值加上指定增量值。 增量也可以为负数，相当于对指定字段进行减法操作。 如果哈希表的 key
			 * 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。 如果指定的字段不存在，那么在执行命令前，字段的值被初始化为 0 。
			 * 对一个储存字符串值的字段执行 HINCRBY 命令将造成一个错误。 本操作的值被限制在 64 位(bit)有符号数字表示之内。
			 */
			conn.hincrBy(article, "votes", 1);//增加文章的投票人数
		}
	}

	/**
	 * 获取某一页的所有文章信息
	 * @param conn
	 * @param page
	 * @return
	 */
	public List<Map<String, String>> getArticles(Jedis conn, int page) {
		return getArticles(conn, page, "score:");
	}

	/**
	 * 获取某一页的所有文章信息，并按 order 排序
	 * @param conn
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Map<String, String>> getArticles(Jedis conn, int page, String order) {
		int start = (page - 1) * ARTICLES_PER_PAGE;
		int end = start + ARTICLES_PER_PAGE - 1;

		/*
		 * Redis Zrevrange 命令返回有序集中，指定区间内的成员。 其中成员的位置按分数值递减(从大到小)来排列。
		 * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。 除了成员按分数值递减的次序排列这一点外，
		 * ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
		 */
		Set<String> ids = conn.zrevrange(order, start, end);//按投票人数逆序查找指定范围的分数有序集合(集合中的元素为文章id)
		List<Map<String, String>> articles = new ArrayList<Map<String, String>>();
		for (String id : ids) {
			Map<String, String> articleData = conn.hgetAll(id);//根据文章id获取文章详细信息
			articleData.put("id", id);// 为文章信息加上id字段
			articles.add(articleData);// 将文章添加到待返回列表中
		}

		return articles;
	}

	/**
	 * 文章分组
	 * @param conn
	 * @param articleId
	 * @param toAdd
	 */
	public void addGroups(Jedis conn, String articleId, String[] toAdd) {
		String article = "article:" + articleId;
		for (String group : toAdd) {
			conn.sadd("group:" + group, article);//其实就是将文章添加到新的集合
		}
	}

	/**
	 * 获取分组中某一页的所有文章
	 * @param conn
	 * @param group
	 * @param page
	 * @return
	 */
	public List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page) {
		return getGroupArticles(conn, group, page, "score:");
	}

	/**
	 * 获取分组中某一页的所有文章-按分数逆序排序
	 * @param conn
	 * @param group
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page, String order) {
		String key = order + group;
		/* Redis EXISTS 命令用于检查给定 key 是否存在。 */
		if (!conn.exists(key)) {
			ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
			/*
			 * Redis Zinterstore 命令计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys(第2个参数)
			 * 参数指定，并将该交集(结果集)储存到 destination(第1个参数) 。 默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和。
			 * 
			 * 注意：zinterstore操作得到的结果集总是zset类型，就算时两个普通集合set进行交集运算得到的也是zset类型 
			 */ 
			conn.zinterstore(key, params, "group:" + group, order);//这里最后两个参数是两个集合
			conn.expire(key, 60);
		}
		return getArticles(conn, page, key);
	}

	/**
	 * 打印文章信息
	 * @param articles
	 */
	private void printArticles(List<Map<String, String>> articles) {
		for (Map<String, String> article : articles) {
			System.out.println("  id: " + article.get("id"));
			for (Map.Entry<String, String> entry : article.entrySet()) {
				if (entry.getKey().equals("id")) {
					continue;
				}
				System.out.println("    " + entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
