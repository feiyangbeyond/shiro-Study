package com.beisi.shiro.utils;

public class Algorithm {

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int partition(int[] arr, int p, int q) {
		int x = arr[p];
//		int x = arr[p+(int)((q-p)*Math.random())];
		int i = p;
		for (int j = p + 1; j < q; j++)
			if (arr[j] <= x)
				swap(arr, ++i, j);
		swap(arr, i, p);
		return i;
	}

	public static void quickSort(int[] arr, int p, int q) {
		if (p < q) {
			int r = partition(arr, p, q);
			quickSort(arr, p, r);
			quickSort(arr, r + 1, q);
		}
	}

	public static int findKMin(int[] arr, int start, int end, int k) {
		int r = partition(arr, start, end);
		if (r == k)
			return arr[r];
		else if (r > k)
			return findKMin(arr, start, r, k);
		else
			return findKMin(arr, r + 1, end, k);
	}
}
