package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import exception.ParserErrorException;

/**
 * Parses an infix expression to a postfix expression used by the reversed polish
 * notation system.
 * @author Daniel Rehnberg
 * @version 1.0
 */
public class PostfixParser {
	/* Represents operators in an expression */
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
		
		/* Returns the symbol associated with the operator */
		public String getSymbol() { return symbol; }
		
		/* Return the priority for the operator */
		public int getPriority() { return priority; }
		
		/* Returns an operator from its symbol */
		public static Operators getFromSymbol(String symbol) {
			for (Operators operator : values()) {
				if (operator.symbol.equals(symbol))
					return operator;
			}
			throw new IllegalArgumentException("No such operator: " + symbol);
		}
		
		/* Tests if a symbol represents an operator */
		public static boolean isOperator(String symbol) {
			for (Operators operator : values()) {
				if (operator.symbol.equals(symbol))
					return true;
			}
			return false;
		}
		
	}

	private List<String> input;
	private List<String> output;
	private Deque<Operators> operatorStack = new ArrayDeque<Operators>();
	private String operatorString;
	
	/**
	 * Constructor.
	 * @param infix The infix expression to be parsed.
	 */
	public PostfixParser(String infix) {
		input = new Vector<String>();
		output = new Vector<String>();
		initOperatorString();
		tokenize(infix);
	}
	
	/**
	 * Gets the postfix expression from the object.
	 * @return The postfix expression.
	 * @throws ParserErrorException Thrown if an invalid infix expression has been subjected.
	 */
	public List<String> getPostfix() throws ParserErrorException {
		parse();
		return output;
	}
	
	/* Initiates the string to be used by the StringTokenizer */
	private void initOperatorString() {
		StringBuilder builder = new StringBuilder();
		for (Operators op : Operators.values()) {
			builder.append(op.getSymbol());
		}
		operatorString = builder.toString();
	}
	
	/* Tokenizes the infix expression */
	private void tokenize(String infix) {
		infix = infix.replaceAll("\\s","");
		StringTokenizer tokenizer = new StringTokenizer(infix, operatorString, true);
		while(tokenizer.hasMoreTokens()) {
			input.add(tokenizer.nextToken());
		}
	}
	
	/* Parses the infix expression to a postfix expression.
	 * Throws ParserErrorException if an invalid expression has been subjected.
	 */
	private void parse() throws ParserErrorException {
		boolean lastInputWasNumber = false;
		boolean lastInputWasEndParenthesis = false;
		if (input.isEmpty()) 
			throw new ParserErrorException("Expression cannot be empty");
		for (String string : input) {
			if (Operators.isOperator(string)) {
				lastInputWasEndParenthesis = handleAsOperator(string, lastInputWasNumber, lastInputWasEndParenthesis);
				lastInputWasNumber = false;
			}
			else {
				handleAsNumber(string, lastInputWasEndParenthesis);
				lastInputWasNumber = true;
				lastInputWasEndParenthesis = false;
			}
		}
		handleRemainingOperators();
	}
	
	/* Separates an end parenthesis from all other operators 
	 * Return true if last operator was an end parenthesis, false otherwise
	 * Throws ParserErrorException if an invalid expression has been subjected. 
	 */
	private boolean handleAsOperator(String operator, boolean lastInputWasNumber, boolean lastInputWasEndParenthesis) throws ParserErrorException {
		Operators op = Operators.getFromSymbol(operator);
		if (op.equals(Operators.END_PARENTHESIS)) {
			handleEndParenthesis();
			return true;
		}		
		else if (op.equals(Operators.START_PARENTHESIS)) {
			handleStartParenthesis(lastInputWasNumber);
		}
		else {
			if (!lastInputWasNumber && !lastInputWasEndParenthesis)
				throw new ParserErrorException("Double operators");
			addOperator(op);
		}
		return false;
	}
	
	/* Adds number to output 
	 * Throws ParserErrorException if the String contains invalid characters. 
	 */
	private void handleAsNumber(String number, boolean lastInputWasEndParenthesis) throws ParserErrorException {
		if (!isAllNumber(number))
			throw new ParserErrorException("Expression contains invalid characters");
		if (lastInputWasEndParenthesis)
			addOperator(Operators.MULTIPLICATION);
		output.add(number);
	}
	
	/* Check if a String contains only digits*/
	private boolean isAllNumber(String number) {
		boolean result = true;
		for (char c : number.toCharArray()) {
			result = result && Character.isDigit(c);
		}
		return result;
	}
	
	/* Adds a start parenthesis to the operator stack and 
	 * a multiplication operator before that if the last input was a number 
	 */
	private void handleStartParenthesis(boolean lastInputWasNumber) {
		if (lastInputWasNumber)
			addOperator(Operators.MULTIPLICATION);
		addOperator(Operators.START_PARENTHESIS);
	}
	
	/* Flushes operatorStack until a start parenthesis is encountered.
	 * Throws ParserErrorException if no start parenthesis is encountered. 
	 */
	private void handleEndParenthesis() throws ParserErrorException {
		while(true) {
			if (operatorStack.isEmpty())
				throw new ParserErrorException("Encountered end parenthesis but no start parenthesis");
			Operators op = operatorStack.peekFirst();
			if (op.equals(Operators.START_PARENTHESIS)) {
				operatorStack.removeFirst();
				break;
			}
			popTopOperator();
		}
		
	}
	
	/* Adds an operator to the operator stack */
	private void addOperator(Operators op) {
		if (!operatorStack.isEmpty()) {
			if (operatorShouldBePopped(op)) {
				popTopOperator();
			}
		}
		operatorStack.addFirst(op);
	}
	
	/* Checks if the top operator on the operator stack should be popped */
	private boolean operatorShouldBePopped(Operators op) {
		Operators topOperator = operatorStack.peekFirst();
		return (topOperator != Operators.START_PARENTHESIS) && 
		(op.getPriority() <= operatorStack.peekFirst().getPriority());
	}
	
	/* Ensures that here are no start parentheses left in the operator stack and pops
	 * all remaining operators to the output
	 * Throws ParserErrorException if a start parenthesis is encountered.
	 */
	private void handleRemainingOperators() throws ParserErrorException {
		for (Operators op : operatorStack) {
			if (op.equals(Operators.START_PARENTHESIS))
				throw new ParserErrorException("Encountered start parenthesis but no end parenthesis");
		}
		popAllOperators();
	}
	
	/* Pops all operators from the operator stack to the output */
	private void popAllOperators() {
		while(!operatorStack.isEmpty()) {
			popTopOperator();
		}
	}
	
	/* Pops the top operator from the operator stack to the output */
	private void popTopOperator() {
		output.add(operatorStack.peekFirst().getSymbol());
		operatorStack.removeFirst();
	}
}
