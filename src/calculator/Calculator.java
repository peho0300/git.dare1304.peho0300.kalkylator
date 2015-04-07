package calculator;

import exception.ParserErrorException;

/**
 * Calculates math-expression.
 * 
 * @author Peter Höglund
 * @versionj 1.0
 */
public class Calculator {
	
	String exp;
	
	/**
	 * Constructor that initializes expression
	 * @param expression
	 */
	public Calculator(String expression) {
		exp = expression;
	}
	
	/**
	 * Default Constructor
	*/
	public Calculator() {
		
	}
	
	/**
	 * Calculates expression
	 * @return the result of the calculation
	 * @throws ParserErrorException
	 */
	public float getResult() throws ParserErrorException {
		
			PostfixParser parser = new PostfixParser(exp);
			PostfixCalculator calc = new PostfixCalculator(parser.getPostfix());
			
			return calc.getResult();
		
	}
	
	/**
	 * Calculates expression
	 * @param expression Expression to be calculated
	 * @return the result of the calculation
	 * @throws ParserErrorException
	 */
	public float getResult(String expression) throws ParserErrorException {
		exp=expression;
		return getResult();
	}
	
	
}
