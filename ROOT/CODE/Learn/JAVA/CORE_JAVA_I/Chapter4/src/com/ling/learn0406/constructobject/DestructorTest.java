package com.ling.learn0406.constructobject;

/**
 * 析构器
 *
 * 1. Object对象的finalize()方法会在该对象被回收前调用，而垃圾回收的时间是不确定的，所以不一定能看到这个方法的执行
 * 
 * 2. Runtime.getRuntime().addShutdownHook(Thread thread) 方法可以在jvm关闭前执行方法中的thread线程，可以
 * 在构造对象时添加这个钩子线程，然后把对象的析构操作放到该线程中来执行，这样可以实现jvm结束前做些必要操作(比如释放掉jvm以外的资源)
 *
 * Chapter4/com.ling.learn0406.constructobject.DestructorTest.java
 *
 * author lingang
 *
 * createTime 2019-10-13 23:35:47 
 *
 */
public class DestructorTest {
	public static void main(String[] args) {
		brith();
	}
	
	private static void brith() {
		new Child("yiyi");
	}

}

class Child {
	private String name;

	{ // 对象构造时，添加钩子线程，该线程在jvm关闭之前被调用
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					userFinalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}));
	}
	
	public Child(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "A [name=" + name + "]";
	}

	@Override
	protected void finalize() throws Throwable { // 1. 该方法会在该对象被回收前调用，而垃圾回收的时间是不确定的，所以不一定能看到这个方法的执行
		System.out.println("对象即将被回收");
		super.finalize();
	}
	
	/**
	 * 自定义析构方法
	 * @throws Throwable
	 */
	protected void userFinalize() throws Throwable {
		System.out.println("jvm进程结束前执行自定义析构方法");
	}
	
}