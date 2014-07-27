package org.simple.javabase.sort;

public class QuickSort {

	public static void sort(int[] a, int start, int end) {
		int i = 0;
		int mid = (start + end) / 2;
		int key = a[mid];
		int j = end;
		while (i < mid) {
			if (a[i] > key) {
				int temp = a[mid];
				a[mid] = a[i];
				a[i] = temp;
				i++;
			}

		}
		while (j >mid) {
			if (a[j] < key) {
				int temp = a[mid];
				a[mid] = a[j];
				a[j] = temp;
				j--;
			}
		}
		sort(a, start, mid);
		sort(a, mid + 1, end);
	}

	public static void main(String[] args) {
		int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		QuickSort.sort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "");
		}

	}

}
