package com.ling.learn0803.annotationdemo;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 自定义注解测试
 * 
 * 本身为按钮添加事件需要调用myButton.addActionListener(()->doSomething())，使用注解可以直接在方法上添加注解来达到为按钮添加事件的效果
 *
 *	此示例是在运行时处理注解的，即调用ActionListenerInstaller.processAnnotations(this)为注解添加操作来实现注解处理的
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0803.annotationdemo.ButtonFrameTest.java
 *
 * author lingang
 *
 * createTime 2020-03-01 20:29:43
 *
 */
@SuppressWarnings("serial")
public class ButtonFrameTest extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	private JPanel panel;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton redButton;

	public static void main(String[] args) {
		ButtonFrameTest bf = new ButtonFrameTest();
		bf.setVisible(true);
	}

	public ButtonFrameTest() throws HeadlessException {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		panel = new JPanel();
		add(panel);

		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");

		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);

		ActionListenerInstaller.processAnnotations(this);
	}

	@ActionListenerFor(source = "yellowButton")
	public void yellowBackground() {
		panel.setBackground(Color.YELLOW);
	}

	@ActionListenerFor(source = "blueButton")
	public void blueBackground() {
		panel.setBackground(Color.BLUE);
	}

	@ActionListenerFor(source = "redButton")
	public void redBackground() {
		panel.setBackground(Color.RED);
	}

}
