package org.simple.javabase.number;

public class NumberUtil {

	/**
	 * 字符位数为1的个数 2
	 * 
	 * @param v
	 * @return
	 */
	public static int count(byte v) {
		int num = 0;
		while (v > 0) {
			if (v % 2 == 1) {
				num++;
			}
			v >>= 1;
		}
		return num;
	}

	/**
	 * 字符位数为1的个数 2
	 * 
	 * @param v
	 * @return
	 */
	public static int count1(byte v) {
		int num = 0;
		while (v > 0) {
			num += v & 0x1;
			v >>= 1;
		}
		return num;
	}

	/**
	 * 输出字节数的0,1串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
				+ (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
				+ (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
				+ (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
	}

	/**
	 * 不使用临时变量交换两个数
	 * 
	 * @param a
	 * @param b
	 */
	public static void interChange(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println("a=" + a + " b=" + b);
	}

	/**
	 * 比较两个数不同的位数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int compare(int a, int b) {
		int c = a ^ b;
		int num = count1((byte) c);
		System.out.println("different bit num " + num);
		return num;
	}

	/**
	 * 判断是都是2的方幂 1
	 * 
	 * @param a
	 * @return
	 */
	public static boolean check(int a) {
		if (a > 0 && ((a & (a - 1)) == 0)) {
			System.out.println("check a :" + a + " success");
			return true;
		}
		System.out.println("check a :" + a + " failed");
		return false;
	}

	/**
	 * 判断是都是2的方幂 2
	 * 
	 * @param a
	 * @return
	 */
	public static boolean check2(int a) {
		if (count1((byte) a) == 1) {
			System.out.println("check a :" + a + " success");
			return true;
		}
		System.out.println("check a :" + a + " failed");
		return false;
	}

	/**
	 * 数组中最大子数组之和 o(N3)
	 * 
	 * @param a
	 */
	public static void findMaxChildArray(int a[]) {
		int n = a.length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += a[k];
					if (sum > max) {
						max = sum;
					}
				}
			}
		}
		System.out.println(max);

	}

	public static void findMaxChildArray2(int a[]) {
		int n = a.length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += a[j];
				if (sum > max) {
					max = sum;
				}
			}
		}
		System.out.println(max);
	}

	public static void findMaxChildArray3(int a[]) {
		int length = a.length;
		int sum = a[length - 1];
		int start = a[length - 1];
		for (int i = length - 2; i >=0; i--) {
			System.out.println("two numbers: a=" + a[i] + " b="
					+ (a[i] + start));
			start = func(a[i], a[i] + start);
			System.out.println("sum numbers: a=" + start + " b="
					+ (a[i] + start));
			sum = func(start, sum);
		}
		System.out.println(sum);
	}

	public static int func(int x, int y) {
		// if (x > 0) {
		// if (y > 0) {
		// return x + y;
		// } else {
		// return x;
		// }
		// } else {
		// if (y > 0) {
		// return y;
		// } else {
		// return x > y ? x : y;
		// }
		// }
		return (x > y) ? x : y;
	}

	public static void main(String[] args) {
		// int i = 127;
		// byte b = (byte) i;
		// System.out.println(NumberUtil.byteToBit(b));
		// int num = NumberUtil.count(b);
		// System.out.println("1 num:" + num);
		// NumberUtil.interChange(2, 3);
		// NumberUtil.compare(1, 127);
		// NumberUtil.check(1);
		// NumberUtil.check2(6);
		// int a[] = { 0, -2, 3, 5, -1, 2 };
		int a[] = { 9, -2, 3, 5, 3 };
		NumberUtil.findMaxChildArray3(a);
	}

}
