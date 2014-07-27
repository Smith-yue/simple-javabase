package org.simple.javabase.sort;

public class InsertSort {

	public static void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} 
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[] { 4, 3, 6, 1, 2, 5,0 };
		InsertSort.sort(a);
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "");
		}
	}
	
}
