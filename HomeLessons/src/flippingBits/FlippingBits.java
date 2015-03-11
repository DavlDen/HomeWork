package flippingBits;
import java.util.Scanner;

public class FlippingBits {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt() ;	
		int count = a;
		if (count > 100) 
			count = 100;
		
		for (int i = 0; i < count; i++) {
			long b = scanner.nextLong();
			long c = ~ b & 0x0000_0000_ffff_ffffl;
			System.out.println(c);
		}
	}
}

