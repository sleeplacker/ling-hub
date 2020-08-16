package com.ling.algorithms11.hashtables;

/**
 * 开放寻址法链表(双重哈希法)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms11.hashtables.OpenAddressingHashTable.java
 *
 * author lingang
 *
 * createTime 2020-08-17 00:48:43
 *
 */
public class OpenAddressingHashTable {
	private Integer[] data;// 散列表数组
	private static int size = 13;// 散列表的槽的数目，最好是素数，否则容易受攻击
	private static int m2 = 11;// 第二个散列函数用到的m值，这个取值是受限制的，因为要保证第二个散列函数能查找整个散列表

	public static void main(String[] args) {
		// 插入序列
		Integer[] data = { 77, 19, 5, 27, 68, 58, 44, 43, 39, 35, 49, 80, 13 };
		OpenAddressingHashTable oaht = new OpenAddressingHashTable();
		// 插入元素
		for (Integer e : data)
			oaht.insert(e);
		// 遍历哈希表
		System.out.println(oaht);
		// 删除元素
		System.out.println(oaht.delete(58));
		// 查找元素
		System.out.println(oaht.search(80));
		// 再次遍历哈希表
		System.out.println(oaht);

		// 继续插入元素
		oaht.insert(66);

		// 再次遍历哈希表
		System.out.println(oaht);

		// 继续插入元素
		oaht.insert(77);// 由于表已满，再插入会报错
	}

	public OpenAddressingHashTable() {
		this.data = new Integer[size];
	}

	/**
	 * 将元素插入散列表
	 * 
	 * @param e
	 */
	public void insert(Integer e) {
		Integer firstHashCode = getHashCode(e);
		Integer hashCode = firstHashCode;
		int i = 0;
		// 下面的条件表示：该位置上有元素，且该元素不是-1(-1给删除元素后的标志)，且探查次数还没达到散列表长度
		while (this.data[hashCode] != null && this.data[hashCode] >= 0 && i < size) {
			hashCode = (firstHashCode + secondHash(e, i)) % size;
			++i;
		}
		if (data[hashCode] == null || data[hashCode] == -1)
			data[hashCode] = e;
		else
			throw new RuntimeException("hash table is full !");
	}

	/**
	 * 从散列表删除元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean delete(Integer e) {
		Integer firstHashCode = getHashCode(e);
		Integer hashCode = firstHashCode;

		int i = 0;
		while (this.data[hashCode] != null && this.data[hashCode] != e && i < size) {
			hashCode = (firstHashCode + secondHash(e, i)) % size;
			++i;
		}
		if (this.data[hashCode] != null && this.data[hashCode] == e) {
			this.data[hashCode] = -1;// 不置null，因为置null会影响查找
			return true;
		}
		return false;
	}

	/**
	 * 从散列表中查找元素
	 * 
	 * @param e
	 */
	public boolean search(Integer e) {
		Integer firstHashCode = getHashCode(e);
		Integer hashCode = firstHashCode;

		int i = 0;
		while (this.data[hashCode] != null && this.data[hashCode] != e && i < size) {
			hashCode = (firstHashCode + secondHash(e, i)) % size;
			++i;
		}
		if (this.data[hashCode] != null && this.data[hashCode] == e)
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("***** datas < *****\n");
		for (int i = 0; i < this.data.length; ++i)
			sb.append("At Index ").append(i).append(" : ").append(this.data[i]).append("\n");
		sb.append("***** datas > *****\n");
		return sb.toString();
	}

	/**
	 * 使用简单的除法散列法
	 * 
	 * @param e
	 * @return
	 */
	public Integer getHashCode(Integer e) {
		return e % data.length;
	}

	/**
	 * 第二次哈希函数
	 * 
	 * @param e
	 * @param i
	 *            - 探查序列号，取值从 0 至 size - 1
	 * @return
	 */
	private Integer secondHash(Integer e, int i) {
		return i * (1 + (e % m2));
	}
}
