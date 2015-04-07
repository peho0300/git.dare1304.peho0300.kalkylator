package calculator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Calculates Postfix expression and returns result
 * 
 * @author Peter Höglund
 * @version 1.0
 * 
 */
public class PostfixCalculator {
	List<String> input;
	Stack<Float> stacken;
	
	/**
	 * Constructor
	 * @param in Expression in Postfix-format
	 */
	public PostfixCalculator(List<String> in) {
		input= in;
	}
	
	/**
	 * Calculates the expression.
	 * @return The result of the calculation.
	 */
	public float getResult() {
		
		stacken = new Stack<Float>();
		Float leftOperand,rightOperand,result = null;
		String currentItem;
		Iterator<String> iter = input.iterator();
		
		while(iter.hasNext()) {
			
			currentItem = iter.next();
			
			/* Check for operator */
			if(currentItem.equals("+")|currentItem.equals("-")|currentItem.equals("*")|currentItem.equals("/")) {
				
				/* Pop the operands*/
				rightOperand = stacken.pop();
				leftOperand = stacken.pop();
				
				
				
				/* Decide what operator to use*/
				switch (currentItem) { 
					case "+":
						result = leftOperand+rightOperand; //Calculate
						break;
					case "-":
						result = leftOperand-rightOperand; //Calculate
						break;
					case "*":
						result = leftOperand*rightOperand; //Calculate
						break;
					case "/":
						result = leftOperand/rightOperand; //Calculate
						break;
				}
				
				/* Add result to stack */
				stacken.push(result);
				
			}
			
			else {
				/* Add operand to stack */
				stacken.push(Float.parseFloat(currentItem));
				
			}
			
		}
		/* Return the value left on the stack */
		return stacken.pop();
	}
}
