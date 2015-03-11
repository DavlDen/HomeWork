package matchNumber;
import java.util.Scanner;

public class MatchNumber {
	
	 public static int recursFunc(int min, int max) {
		  int num = 0;
		  num = (int) (max+min)/2;
		  return (num);
		 }
		 
	/*
	public static int recursFunc(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		String ans;
		int newMin = 0, newMax = 0;
		
		num = (max+min)/2;
		
		System.out.println("This number is " + num + "?");
		ans = scanner.nextLine();
		
		switch (ans) {		
	  	case "more": 
	  		newMin = num; 
	  		newMax = max;
	  		break;
	  	case "less": 
	  		newMin = min; 
	  		newMax = num;
	  		break;
	  	case "right": 
	  		return(num); 
		}
		
	  	if (newMax == max && newMin == min) newMin++;
	  	
	  	num = recursFunc(newMin, newMax);
	  	return(num);  	
	}
	*/
	
	public static void main(String[] args) {
		
		int min = 1;		
		int max = 100;
		int step = 0;
		int num = 0;

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Make a number from 1 to 100, please!");
		System.out.println("You should answer more, less or right.");	
		
		num = recursFunc(min, max);
		
		//System.out.println("Вы загадали число: " + num);

		
		System.out.println("This number is " + num + "?");
	
		while(step <= 7) {
			String ans;		
			ans = scanner.nextLine();	
			step ++;
			
		if (ans.equals("more")) {
			min = num;
			max = max;
			num = recursFunc(min, max);
			System.out.println("This number is " + num);
		}
		else if (ans.equals("less")) {
			min = min;
			max = num;
			num = recursFunc(min, max);
			System.out.println("This number is " + num);
		}
		else if (ans.equals("right")) {
			System.out.println("Number matched at " + step + " steps!");
			break;
		}
		else {
			System.out.println("Incorrect answer!You should answer more, less or right.");
		}
		} 
		
	}

}