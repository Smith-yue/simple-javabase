package org.simple.javabase.sort;

public class ShellSort {

	public static void sort(int[] a) {
		int d = a.length;
		while (true) {
			d = d / 2;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i = i + d) {
					int temp = a[i];
					int j;
					for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		ShellSort.sort(a);
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "");
		}
	}
}
