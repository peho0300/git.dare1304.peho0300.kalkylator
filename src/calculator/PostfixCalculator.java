package calculator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PostfixCalculator {
	List<String> input;
	Stack<Float> stacken;
	
	public PostfixCalculator(List<String> in) {
		input= in;
	}
	
	public float getResult() {
		
		stacken = new Stack<Float>();
		Float leftOperand,rightOperand,result = null;
		String currentItem;
		Iterator<String> iter = input.iterator();
		
		while(iter.hasNext()) {
			
			currentItem = iter.next();
			
			if(currentItem.equals("+")|currentItem.equals("-")|currentItem.equals("*")|currentItem.equals("/")) {
				
				rightOperand = stacken.pop();
				leftOperand = stacken.pop();
				
				switch (currentItem) { 
					case "+":
						result = rightOperand+leftOperand;
						break;
					case "-":
						result = rightOperand-leftOperand;
						break;
					case "*":
						result = rightOperand*leftOperand;
						break;
					case "/":
						result = rightOperand/leftOperand;
						break;
				}
				
				stacken.push(result);
				
			}
			
			else {
				
				stacken.push(Float.parseFloat(currentItem));
				
			}
			
			iter.remove();
		}
		return stacken.pop();
	}
}
