package jeanscompiler;

import java.util.Scanner;

public class Jeans {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char c = 's';
		String str = "Hello World";
		int x = 13;
		x++;
		System.out.println(x);
		while (x!=1) {
			System.out.println(x);
			x--;
		}
		if (x==2) {
			System.out.println("IN THE IF!");
			if (c=='s') {
				System.out.println("IM NESTED!");
			}
		}
		else if (c=='s') {
			System.out.println("Im in the else if!");
		}
		else {
			System.out.println("IN THE ELSE!");
		}
		System.out.println("done");
	}
}
