package com.ling.learn0807.bytedemo;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ling.learn0807.bytedemo.LogEntry;
/**
 * 
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0807.bytedemo.Item.java
 *
 * author lingang
 *
 * createTime 2020-03-05 22:28:14 
 *
 */
public class Item {
	private String description;
	private int partNumber;

	/**
	 * Constructs an item.
	 * 
	 * @param aDescription
	 *            the item's description
	 * @param aPartNumber
	 *            the item's part number
	 */
	public Item(String aDescription, int aPartNumber) {
		description = aDescription;
		partNumber = aPartNumber;
	}

	/**
	 * Gets the description of this item.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public String toString() {
		return "[description=" + description + ", partNumber=" + partNumber + "]";
	}

	@LogEntry(logger = "com.ling")
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Item other = (Item) otherObject;
		return Objects.equals(description, other.description) && partNumber == other.partNumber;
	}

	@LogEntry(logger = "com.ling")
	public int hashCode() {
		return Objects.hash(description, partNumber);
	}

	public static void main(String[] args) {
		Logger.getLogger("com.ling").setLevel(Level.FINEST);
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINEST);
		Logger.getLogger("com.ling").addHandler(handler);

		Item it1 = new Item("AAA", 3);
		Item it2 = new Item("AAA", 3);
		System.out.println(it1.equals(it2));
		System.out.println(it1.hashCode());
	}
}
