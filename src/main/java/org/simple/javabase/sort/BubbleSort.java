package org.simple.javabase.sort;

public class BubbleSort {

	public static void sort(int[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[i]) {
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		BubbleSort.sort(a);
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "");
		}
	}

}
