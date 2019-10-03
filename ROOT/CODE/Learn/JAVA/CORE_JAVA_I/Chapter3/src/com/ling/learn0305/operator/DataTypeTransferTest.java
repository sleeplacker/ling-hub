package com.ling.learn0305.operator;

/**
 * 数值类型转换-四条规则
 * 
 * 1.操作数中包含double类型，另一操作数就会被转换为double类型。
 * 
 * 2.操作数中不包含double类型，但包含float类型，另一操作数就会被转换为float类型。
 * 
 * 3.操作数中不包含double和float类型，但包含long类型，另一操作数就会被转换为long类型。
 * 
 * 4.操作数中不包含double、float和long类型，两个操作数都会被转换为int类型。
 *
 * Chapter3/com.ling.learn0305.operator.DataTypeTransferTest.java
 *
 * author lingang
 *
 * createTime 2019-10-03 23:35:46
 *
 */
public class DataTypeTransferTest {
	public static void main(String[] args) {
		byte bValue = 10;
		char cValue = 'A';
		short sValue = 10;
		int iValue = 10;
		long lValue = 10l;
		float fValue = 1.0f;
		double dValue = 1.0;

		/* 操作数中包含double类型，结果为double类型 < */
		System.out.println("操作数中包含double类型，结果为double类型");
		Object dd = dValue + dValue;
		Object df = dValue + fValue;
		Object dl = dValue + lValue;
		Object di = dValue + iValue;
		Object ds = dValue + sValue;
		Object dc = dValue + cValue;
		Object db = dValue + bValue;
		System.out.println("d+d : " + dd.getClass().getSimpleName());
		System.out.println("d+f : " + df.getClass().getSimpleName());
		System.out.println("d+l : " + dl.getClass().getSimpleName());
		System.out.println("d+i : " + di.getClass().getSimpleName());
		System.out.println("d+s : " + ds.getClass().getSimpleName());
		System.out.println("d+c : " + dc.getClass().getSimpleName());
		System.out.println("d+b : " + db.getClass().getSimpleName());
		/* 操作数中包含double类型，结果为double类型 > */

		/* 操作数中包含float类型，且不包含double类型，结果为float类型 < */
		System.out.println("\n操作数中包含float类型，且不包含double类型，结果为float类型");
		Object ff = fValue + fValue;
		Object fl = fValue + lValue;
		Object fi = fValue + iValue;
		Object fs = fValue + sValue;
		Object fc = fValue + cValue;
		Object fb = fValue + bValue;
		System.out.println("f+f : " + ff.getClass().getSimpleName());
		System.out.println("f+l : " + fl.getClass().getSimpleName());
		System.out.println("f+i : " + fi.getClass().getSimpleName());
		System.out.println("f+s : " + fs.getClass().getSimpleName());
		System.out.println("f+c : " + fc.getClass().getSimpleName());
		System.out.println("f+b : " + fb.getClass().getSimpleName());
		/* 操作数中包含float类型，且不包含double类型，结果为float类型 > */

		/* 操作数中包含long类型，且不包含double、float类型，结果为long类型 < */
		System.out.println("\n操作数中包含long类型，且不包含double、float类型，结果为long类型");
		Object ll = lValue + lValue;
		Object li = lValue + iValue;
		Object ls = lValue + sValue;
		Object lc = lValue + cValue;
		Object lb = lValue + bValue;
		System.out.println("l+l : " + ll.getClass().getSimpleName());
		System.out.println("l+i : " + li.getClass().getSimpleName());
		System.out.println("l+s : " + ls.getClass().getSimpleName());
		System.out.println("l+c : " + lc.getClass().getSimpleName());
		System.out.println("l+b : " + lb.getClass().getSimpleName());
		/* 操作数中包含long类型，且不包含double、float类型，结果为long类型 > */

		/* 操作数中不包含double、float、long类型，结果为int类型 < */
		System.out.println("\n操作数中不包含double、float、long类型，结果为int类型");
		Object ii = iValue + iValue;
		Object is = iValue + sValue;
		Object ic = iValue + cValue;
		Object ib = iValue + bValue;
		Object ss = sValue + sValue;
		Object sc = sValue + cValue;
		Object sb = sValue + bValue;
		Object cc = cValue + cValue;
		Object cb = cValue + bValue;
		Object bb = bValue + bValue;
		System.out.println("i+i : " + ii.getClass().getSimpleName());
		System.out.println("i+s : " + is.getClass().getSimpleName());
		System.out.println("i+c : " + ic.getClass().getSimpleName());
		System.out.println("i+b : " + ib.getClass().getSimpleName());
		System.out.println("s+s : " + ss.getClass().getSimpleName());
		System.out.println("s+c : " + sc.getClass().getSimpleName());
		System.out.println("s+b : " + sb.getClass().getSimpleName());
		System.out.println("c+c : " + cc.getClass().getSimpleName());
		System.out.println("c+b : " + cb.getClass().getSimpleName());
		System.out.println("b+b : " + bb.getClass().getSimpleName());
		/* 操作数中不包含double、float、long类型，结果为int类型 > */
	}
}
