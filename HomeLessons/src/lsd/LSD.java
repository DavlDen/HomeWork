package lsd;

import java.util.Arrays;
import java.util.Random;

public class LSD {
	
	private static final int MAX = 1000000;
	
	public static void main(String[] args) {
		
		String[] data = generate();
		
		long start = System.currentTimeMillis();
		
		//Arrays.sort(data);
		sort(data, MAX);
		long stop = System.currentTimeMillis();
		//System.out.println(data);
		System.out.println("Elapsed = " + (stop - start));
	}
	
	private static String[] generate() {
		int[] dataFist = new int[MAX];
		String[] data = new String[MAX];
		
		Random random = new Random();

		for (int i = 0; i < dataFist.length; i++) {
			dataFist[i] = random.nextInt(MAX); // 0 - 999999
			data[i] = Integer.toString(dataFist[i]);
		}
		return data;
	}
	

	public static void sort(String[] a, int W) {
		
		int R = 256;
		int N = a.length;
		String [] aux = new String[N];
		
		for (int d = W-1; d >= 0; d--) {
			
			int[] count = new int[R+1];	
			
			for (int i = 0; i < N; i++)
				count[a[i].charAt(d) + 1]++;
			
			for (int r = 0; r < R; r++)
				count[r+1] += count[r];
			
			for (int i = 0; i < N; i++)
				aux[count[a[i].charAt(d)]++] = a[i];
			
			for (int i = 0; i < N; i++)
				a[i] = aux[i];
			}
	}
	
}
