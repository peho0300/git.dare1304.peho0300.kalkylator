package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class PostfixParser {
	private enum Operators {
		ADDITION("+", 1), 
		SUBTRACTION("-", 1), 
		MULTIPLICATION("*", 2), 
		DIVISON("/", 2),
		START_PARENTHESIS("(", 3),
		END_PARENTHESIS(")", 3);
		
		private String symbol;
		private int priority;
		
		private Operators(String symbol, int priority) {
			this.symbol = symbol;
			this.priority = priority;
		}
		
		public String getSymbol() { return symbol; }
		public int getPriority() { return priority; }
		
		public static Operators getFromSymbol(String symbol) {
			for (Operators operator : values()) {
				if (operator.symbol.equals(symbol))
					return operator;
			}
			throw new IllegalArgumentException("No such operator: " + symbol);
		}
		
		public static boolean isOperator(String symbol) {
			for (Operators operator : values()) {
				if (operator.symbol.equals(symbol))
					return true;
			}
			return false;
		}
		
	}

	List<String> input;
	List<String> output;
	Deque<Operators> operatorStack = new ArrayDeque<Operators>();
	String operatorString;
	
	public PostfixParser() {
		input = new Vector<String>();
		output = new Vector<String>();
		initOperatorString();
	}
	
	public List<String> getPostFix(String infix) {
		input.clear();
		output.clear();
		tokenize(infix);
		parse();
		return output;
	}
	
	private void initOperatorString() {
		StringBuilder builder = new StringBuilder();
		for (Operators op : Operators.values()) {
			builder.append(op.getSymbol());
		}
		operatorString = builder.toString();
	}
	
	private void tokenize(String infix) {
		StringTokenizer tokenizer = new StringTokenizer(infix, operatorString, true);
		while(tokenizer.hasMoreTokens()) {
			input.add(tokenizer.nextToken());
		}
	}
	
	private void parse() {
		
		for (String string : input) {
			if (Operators.isOperator(string)) {
				handleAsOperator(string);
			}
			else {
				output.add(string);
			}
		}
		popAllOperators();
	}
	
	private void handleAsOperator(String operator) {
		Operators op = Operators.getFromSymbol(operator);
		if (op.equals(Operators.END_PARENTHESIS)) {
			handleEndParenthesis();
		}
		else {
			addOperator(op);
		}	
	}
	
	private void handleEndParenthesis() {
		while(true) {
			Operators op = operatorStack.peekFirst();
			if (op.equals(Operators.START_PARENTHESIS)) {
				operatorStack.removeFirst();
				break;
			}
			popTopOperator();
		}
	}
	
	private void addOperator(Operators op) {
		if (!operatorStack.isEmpty()) {
			if (operatorShouldBePopped(op)) {
				popTopOperator();
			}
		}
		operatorStack.addFirst(op);
	}
	
	private boolean operatorShouldBePopped(Operators op) {
		Operators topOperator = operatorStack.peekFirst();
		return (topOperator != Operators.START_PARENTHESIS) && 
		(op.getPriority() <= operatorStack.peekFirst().getPriority());
	}
	
	private void popAllOperators() {
		while(!operatorStack.isEmpty()) {
			popTopOperator();
		}
	}
	
	private void popTopOperator() {
		output.add(operatorStack.peekFirst().getSymbol());
		operatorStack.removeFirst();
	}
}
