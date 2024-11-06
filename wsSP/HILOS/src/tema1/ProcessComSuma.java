package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessComSuma {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int num1 = Integer.parseInt(br.readLine());
		int num2 = 10;
		
		System.out.println("La suma da "+(num1 + num2));

	}

}
