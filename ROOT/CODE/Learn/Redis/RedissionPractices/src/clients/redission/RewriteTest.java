package clients.redission;

import java.util.Arrays;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;

/**
 * 重写测试
 * 
 * 自动重写机制的触发条件：aof_current_size > auto-aof-rewrite-min-size && (aof_current_size - aof_base_size) / aof_base_size >= auto-aof-rewrite-percentage / 100
 * auto-aof-rewrite-min-size：最小重写大小，如64mb
 * auto-aof-rewrite-percentage：和上次重写大小的比例(注意是百分比，100表示上次重写大小的1倍)
 * aof_base_size：上次重写后 AOF 文件大小
 *
 * RedissionPractices/clients.redission.RedissioinTest.java
 *
 * author lingang
 *
 * createTime 2021-07-08 16:56:22
 *
 */
public class RewriteTest {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSentinelServers().setMasterName("mymaster")
				// 可以用"rediss://"来启用SSL连接
				.addSentinelAddress("redis://127.0.0.1:26379", "redis://127.0.0.1:26380", "redis://127.0.0.1:26381");
		SentinelServersConfig sentinelConfig = config.useSentinelServers();
		sentinelConfig.setDatabase(0);//设置数据库号
		sentinelConfig.setPassword("dbp12345");//设置密码
		config.setCodec(StringCodec.INSTANCE);// 设置序列号方式
		RedissonClient redisson = Redisson.create(config);

		byte[] bs = new byte[1024*1024];
		Arrays.fill(bs, (byte) 97);
		for (int i = 0; i < 20; ++i) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			redisson.getBucket("test" + i).set(new String(bs));
			System.out.println("write " + (i + 1) + " M success");
		}
		redisson.shutdown();

	}
}
