package lsd;

import java.util.Arrays;
import java.util.Random;

public class LSD {
    
    private static final int MAX = 1000000; // max count num
    private static final int MAXINT = 2147483647; // max int
    private static final String DOPZERO = "0000000000"; // dop zero for size max int
    private static final int W = 10; // size max int
    
    public static void main(String[] args) {
            String[] dataLSD  = generateStr();
            String[] dataArrays = Arrays.copyOf(dataLSD, dataLSD.length);
            
            int[] dataLSDByte  = generateInt();
            int[] dataArraysByte = Arrays.copyOf(dataLSDByte, dataLSDByte.length);
            
            long startLSD = System.currentTimeMillis();
            sortStr(dataLSD, W);
            long stopLSD = System.currentTimeMillis();

//            for (int i = 999989; i < dataLSD.length; i++) {
//                System.out.println("dataLSD[" + i + "] = " + dataLSD[i]);
//            }  
            System.out.println("Elapsed LSDSortStr = " + (stopLSD - startLSD) + " msec");
            
            long startArrays = System.currentTimeMillis();
            Arrays.sort(dataArrays);
            long stopArrays = System.currentTimeMillis();

//            for (int i = 999989; i < dataArrays.length; i++) {
//                System.out.println("dataArrays[" + i + "] = " + dataArrays[i]);
//            }  
            System.out.println("Elapsed quickSortStr = " + (stopArrays - startArrays) + " msec");
            
            long startLSDByte = System.currentTimeMillis();
            sortByte(dataLSDByte);
            long stopLSDByte = System.currentTimeMillis();

//            for (int i = 999989; i < dataLSDByte.length; i++) {
//                System.out.println("dataByte[" + i + "] = " + dataLSDByte[i]);
//            }
            System.out.println("Elapsed LSDSortInt = " + (stopLSDByte - startLSDByte) + " msec");         
            
            long startArraysBits = System.currentTimeMillis();
            Arrays.sort(dataArraysByte);
            long stopArraysBits = System.currentTimeMillis();

//            for (int i = 999989; i < dataArraysByte.length; i++) {
//                System.out.println("dataArraysByte[" + i + "] = " + dataArraysByte[i]);
//            }
            System.out.println("Elapsed quickSortInt = " + (stopArraysBits - startArraysBits) + " msec");
    }
    
    //generate array string
    private static String[] generateStr() {
        int[] dataInt = new int[MAX];
        String[] dataStr = new String[dataInt.length];
        Random random = new Random();
        
        for (int i = 0; i < dataInt.length; i++) {
            dataInt[i] = random.nextInt(MAXINT);
            dataStr[i] = DOPZERO + dataInt[i];
            dataStr[i] = dataStr[i].substring(dataStr[i].length() - W);
        }
        return dataStr;
    }
    
  //generate array int
    private static int[] generateInt() {
        int[] dataInt = new int[MAX];
        Random random = new Random();
        
        for (int i = 0; i < dataInt.length; i++) {
            dataInt[i] = random.nextInt(MAXINT);
        }
        return dataInt;
    }
    
    //sort string array
    public static void sortStr(String[] dataStr, int W) {
        int R = 58; // 0-9 = 49-58
        int N = dataStr.length;
        
        String[] aux = new String[N];
        int[] count = new int[R+1];
        
        //index for d
        for (int d = W-1; d >= 0; d--) {
        	
        	//count frequency 
            for (int i = 0; i < N; i++)
                count[dataStr[i].charAt(d) + 1]++;
            
            // cumulative totals
            for (int r = 49; r < R; r++)
                count[r+1] += count[r];
            
            //new sorted array 
            for (int i = 0; i < N; i++)
                aux[count[dataStr[i].charAt(d)]++] = dataStr[i];
            
            //new data
            for (int i = 0; i < N; i++)
            	dataStr[i] = aux[i];
            
            Arrays.fill(count, 0);
        }
    }
    
    public static void sortByte(int[] dataInt) {
        int bitsword = 32;                
        int bitsbyte = 8;
        int bytesword = bitsword / bitsbyte;  
        int R = 1 << bitsbyte;   
        int MASK = R - 1;

        int N = dataInt.length;
        int[] aux = new int[N];
        int[] count = new int[R+1];

        for (int d = 0; d < bytesword; d++) {         

        	//count frequency 
            for (int i = 0; i < N; i++) {           
                int c = (dataInt[i] >> bitsbyte*d) & MASK;
                count[c + 1]++;
            }

            //cumulative totals
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            if (d == bytesword-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            //new sorted array 
            for (int i = 0; i < N; i++) {
                int c = (dataInt[i] >> bitsbyte*d) & MASK;
                aux[count[c]++] = dataInt[i];
            }

            //new data
            for (int i = 0; i < N; i++)
                dataInt[i] = aux[i];
            
            Arrays.fill(count, 0);
        }
    }   
}