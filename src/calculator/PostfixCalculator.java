package calculator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * En klass som tar en lista som innehåller ett uttryck i postfix-
 * format, räknar ut det och returnerar ett resultat
 * 
 * @author Peter Höglund
 *
 */
public class PostfixCalculator {
	List<String> input;
	Stack<Float> stacken;
	
	/**
	 * Konstuktor
	 * @param in Uttrycket som ska räknas i Postfix-format
	 */
	public PostfixCalculator(List<String> in) {
		input= in;
	}
	
	/**
	 * Funktion som räknar ut uttryck i postfix-format
	 * @return Resultatet från uträkningen
	 */
	public float getResult() {
		
		stacken = new Stack<Float>();
		Float leftOperand,rightOperand,result = null;
		String currentItem;
		Iterator<String> iter = input.iterator();
		
		while(iter.hasNext()) {
			
			currentItem = iter.next();
			
			if(currentItem.equals("+")|currentItem.equals("-")|currentItem.equals("*")|currentItem.equals("/")) { //Kolla ifall currentItem är en operator
				
				/* Poppa operanderna*/
				leftOperand = stacken.pop();
				rightOperand = stacken.pop();
				
				
				/* Avgör vilken operator det är*/
				switch (currentItem) { 
					case "+":
						result = rightOperand+leftOperand; //Räkna
						break;
					case "-":
						result = rightOperand-leftOperand; //Räkna
						break;
					case "*":
						result = rightOperand*leftOperand; //Räkna
						break;
					case "/":
						result = rightOperand/leftOperand; //Räkna
						break;
				}
				
				stacken.push(result); //Lägg resultatet på stacken
				
			}
			
			else {
				
				stacken.push(Float.parseFloat(currentItem)); //Lägg operand på stacken
				
			}
			
		}
		return stacken.pop(); //Returnera det kvarvarande värdet på stacken
	}
}
