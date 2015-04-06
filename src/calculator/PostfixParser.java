package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class PostfixParser {
	List<String> input;
	List<String> output;
	
	public PostfixParser() {
		input = new Vector<String>();
		output = new Vector<String>();
	}
	
	public List<String> getPostFix(String infix) {
		StringTokenizer tokenizer = new StringTokenizer(infix, "+", true);
		while(tokenizer.hasMoreTokens()) {
			input.add(tokenizer.nextToken());
		}
		parse();
		return output;
	}
	
	private void parse() {
		Deque<String> operatorStack = new ArrayDeque<String>();
		for (String string : input) {
			if (string.equals("+")) {
				operatorStack.addFirst(string);
			}
			else {
				output.add(string);
			}
		}
		while(!operatorStack.isEmpty()) {
			output.add(operatorStack.peekFirst());
			operatorStack.removeFirst();
		}
	}
}
