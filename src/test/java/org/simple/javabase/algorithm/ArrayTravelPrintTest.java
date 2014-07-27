package org.simple.javabase.algorithm;

import org.junit.Test;

public class ArrayTravelPrintTest {

	private final int[][] a = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 },
			{ 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

	@Test
	public void printFormRightPoint() {
		int size = a.length;
		int lines = a.length*2 -1;
		for (int k = 0; k < lines; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (j - i == size - k -1) {
						System.out.print(a[i][j]+" ");
					}
				}
			}
			System.out.println();
		}
	}
}
