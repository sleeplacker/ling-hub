package com.ling.learn1405.synchronize;

/**
 * 3个被弃用的方法被弃用的原因：
 * 
 * Thread.stop方法：因为这个方法可能会导致状态不一致，比如在转账线程在转出之后转入之前被stop掉，那么账会不平
 * 
 * Thread.suspend方法和Thread.resume方法：暂停和恢复线程容易造成死锁，比如线程A暂停了线程B，而线程B持有锁L，这时如果线程A也需要获得锁L就死锁了
 *
 * Chapter14/com.ling.learn1405.synchronize.DeprecatedMethodsTest.java
 *
 * author lingang
 *
 * createTime 2019-12-17 22:41:53
 *
 */
public class DeprecatedMethodsTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Thread.currentThread().stop();
		Thread.currentThread().suspend();
		Thread.currentThread().resume();
	}
}
