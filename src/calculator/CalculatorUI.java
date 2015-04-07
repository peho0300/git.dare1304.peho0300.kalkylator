package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.ParserErrorException;

/**
 * A console based calculator
 * @author Daniel Rehnberg
 * @version 1.0
 *
 */
public class CalculatorUI {
	
	/**
	 * Runs the calculator
	 */
	public void run() {
		Calculator calc = new Calculator();
		
		System.out.println("=== CALCULATOR ===");
		System.out.println("Enter 'exit' to quit.");
		/* Lets the user enter an expression and get the result. This continues until the user
		 * wishes to quit.
		 */
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String expression = "";
			float result;
			
			System.out.println("Enter expression: ");
			try {
				expression = input.readLine();
				if (expression.equalsIgnoreCase("exit"))
					break;
				result = calc.getResult(expression);
				System.out.println(String.format("Result: %f", result));
			} 
			catch (ParserErrorException e) {
				System.out.println(String.format("Invalid expression: %s", e.getMessage()));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Main function
	 * @param args Args not used
	 */
	public static void main(String[] args) {
		CalculatorUI calc = new CalculatorUI();
		calc.run();
	}
}

