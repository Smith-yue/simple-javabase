package org.simple.javabase.sort;

import org.junit.Test;

public class MergeSortTest {
	@Test
	public void sort() {
		int[] a = new int[] { 4, 3, 6, 1, 2, 5 };
		MergeSort.mergeSort(a, 0, 1);
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "");
		}

	}
}
