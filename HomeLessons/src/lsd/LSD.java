package lsd;

import java.util.Arrays;
import java.util.Random;

public class LSD {
	
	private static final int MAX = 1000000;
	private static final int W = 6;
	private static final String dopZero = "000000";
	
	public static void main(String[] args) {
			String[] data = generate();
			
			long startLSD = System.currentTimeMillis();
			sort(data, W);
			long stopLSD = System.currentTimeMillis();
/*
	        for (int i = 0; i < data.length; i++) {
	        	System.out.println("data[" + i + "] = " + data[i]);
	        }
*/		        
			System.out.println("Elapsed LSD = " + (stopLSD - startLSD) + " msec");
		
			
			long start = System.currentTimeMillis();
			Arrays.sort(data);
			long stop = System.currentTimeMillis();
/*
	        for (int i = 0; i < data.length; i++) {
	        	System.out.println("data[" + i + "] = " + data[i]);
	        }
*/	        
			System.out.println("Elapsed quicksort = " + (stop - start) + " msec");
	}
	
	private static String[] generate() {
		int[] dataFist = new int[MAX];
		String[] data = new String[MAX];
		Random random = new Random();
		
		for (int i = 0; i < dataFist.length; i++) {
			dataFist[i] = random.nextInt(MAX); // 0 - 999999
			data[i] = dopZero + dataFist[i];
			data[i] = data[i].substring(data[i].length() - W);
		}
		return data;
	}
	
	public static void sort(String[] data, int W) {
		int R = 256;
		int N = data.length;
		String[] aux = new String[N];
		
		for (int d = W-1; d >= 0; d--) {
			int[] count = new int[R+1];
			
			for (int i = 0; i < N; i++)
				count[data[i].charAt(d) + 1]++;
			
			for (int r = 0; r < R; r++)
				count[r+1] += count[r];
			
			for (int i = 0; i < N; i++)
				aux[count[data[i].charAt(d)]++] = data[i];
			
			for (int i = 0; i < N; i++)
				data[i] = aux[i];
		}
	}
}
