package com.ling.learn0604.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 内部类示例
 *
 * 1. 内部类中的静态域必须是final的，因为静态域的作用就是每个类只有一个实例，然而每个外部类对象都有单独的内部类实例，如果不定义为final
 * ，这个域就可能不是唯一的
 * 
 * 2. 内部类中不能定义静态方法，没有原因
 *
 * Chapter6/com.ling.learn0604.innerclass.InnerClassDemoTest.java
 *
 * author lingang
 *
 * createTime 2019-10-28 23:19:57
 *
 */
public class InnerClassDemoTest {
	public static void main(String[] args) {
		// TalkingClock tc = new TalkingClock(3000, true);
		TalkingClock tc = new TalkingClock(3000, false);
		tc.start();

		JOptionPane.showMessageDialog(null, "停止");
		System.exit(0);
	}
}

class TalkingClock {
	private int interval;
	private boolean beep;

	/**
	 * @param interval
	 * @param beep
	 */
	public TalkingClock(int interval, boolean beep) {
		super();
		this.interval = interval;
		this.beep = beep;
	}

	void start() {
		/* 可以在new关键字前加上this.明确指示实例化的类为当前类的内部类 */
		// TimePrinter tp = this.new TimePrinter();
		TimePrinter tp = new TimePrinter();
		Timer timer = new Timer(interval, tp);
		timer.start();
	}

	class TimePrinter implements ActionListener {
		/*
		 * The field staticFieldTest cannot be declared static in a non-static
		 * inner type, unless initialized with a constant expression
		 */
		// private static String staticFieldTest = "";
		/*
		 * 内部类中的静态域必须是final的，因为静态域的作用就是每个类只有一个实例，然而每个外部类对象都有单独的内部类实例，如果不定义为final
		 * ，这个域就可能不是唯一的
		 */
		@SuppressWarnings("unused")
		private final static String staticFieldTest = "";

		/*
		 * The method staticFunTest cannot be declared static; static methods
		 * can only be declared in a static or top level type
		 * 
		 * 内部类中不能定义静态方法，没有原因
		 * 
		 */
		// static void staticFunTest() {}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			/* 可以明确指定使用外部类引用来访问私有域，TalkingClock.this.可以省略 */
			// if (TalkingClock.this.beep) {
			if (beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}

	}

}
