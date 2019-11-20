package com.ling.learn0602.interfaceexample;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 接口回调测试
 *
 * Chapter6/com.ling.learn0601.interfaceexample.InterfaceCallbackTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 00:50:53
 *
 */
public class InterfaceCallbackTest {
	public static void main(String[] args) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				Toolkit.getDefaultToolkit().beep();
			}
		};

		new Timer(5 * 1000, listener).start();

		JOptionPane.showMessageDialog(null, "结束程序");
		System.exit(0);
	}
}
