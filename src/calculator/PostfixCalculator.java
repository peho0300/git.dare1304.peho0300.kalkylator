package calculator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * En klass som tar en lista som inneh�ller ett uttryck i postfix-
 * format, r�knar ut det och returnerar ett resultat
 * 
 * @author Peter H�glund
 *
 */
public class PostfixCalculator {
	List<String> input;
	Stack<Float> stacken;
	
	/**
	 * Konstuktor
	 * @param in Uttrycket som ska r�knas i Postfix-format
	 */
	public PostfixCalculator(List<String> in) {
		input= in;
	}
	
	/**
	 * Funktion som r�knar ut uttryck i postfix-format
	 * @return Resultatet fr�n utr�kningen
	 */
	public float getResult() {
		
		stacken = new Stack<Float>();
		Float leftOperand,rightOperand,result = null;
		String currentItem;
		Iterator<String> iter = input.iterator();
		
		while(iter.hasNext()) {
			
			currentItem = iter.next();
			
			if(currentItem.equals("+")|currentItem.equals("-")|currentItem.equals("*")|currentItem.equals("/")) { //Kolla ifall currentItem �r en operator
				
				/* Poppa operanderna*/
				leftOperand = stacken.pop();
				rightOperand = stacken.pop();
				
				
				/* Avg�r vilken operator det �r*/
				switch (currentItem) { 
					case "+":
						result = rightOperand+leftOperand; //R�kna
						break;
					case "-":
						result = rightOperand-leftOperand; //R�kna
						break;
					case "*":
						result = rightOperand*leftOperand; //R�kna
						break;
					case "/":
						result = rightOperand/leftOperand; //R�kna
						break;
				}
				
				stacken.push(result); //L�gg resultatet p� stacken
				
			}
			
			else {
				
				stacken.push(Float.parseFloat(currentItem)); //L�gg operand p� stacken
				
			}
			
		}
		return stacken.pop(); //Returnera det kvarvarande v�rdet p� stacken
	}
}
