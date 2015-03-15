package sort;

import java.util.Arrays;
import java.util.Random;

public class sort {

	private static final int MAX = 1000000;
	private static final int R = 0;

	public static void main(String[] args) {
		
		int[] data = generate();
		
		long start = System.currentTimeMillis();
		//Arrays.sort(data);
		sort(data);	
		long stop = System.currentTimeMillis();
		//System.out.println(data);
		System.out.println("Elapsed = " + (stop - start));
	}

	private static int[] generate() {
		int[] data = new int[MAX];
		
		Random random = new Random();
		
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(MAX); // 0 - 999999
		}
		
		return data;
	}
	
	
	public static void sort(int [] a) {
	
		int R = MAX;
		int N = a.length;
		int[] aux = new int[N];
		int[] count = new int[R+1];
		
		for (int i = 0; i < N; i++)
		count[a[i]+1]++;
		
		for (int r = 0; r < R; r++)
		count[r+1] += count[r];
		
		for (int i = 0; i < N; i++)
		aux[count[a[i]]++] = a[i];
		
		for (int i = 0; i < N; i++)
		a[i] = aux[i];
		
	}
	
}
