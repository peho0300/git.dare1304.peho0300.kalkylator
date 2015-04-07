package calculator;

import exception.ParserErrorException;

/**
 * 
 * @author Peter H�glund
 * @versionj 1.0
 */
public class Calculator {
	
	String exp;
	
	public Calculator(String expression) {
		exp = expression;
	}
	
	public Calculator() {
		
	}
	
	public float getResult() throws ParserErrorException {
		
			PostfixParser parser = new PostfixParser(exp);
			PostfixCalculator calc = new PostfixCalculator(parser.getPostfix());
			
			return calc.getResult();
		
	}
	
	public float getResult(String expression) throws ParserErrorException {
		exp=expression;
		return getResult();
	}
	
	
}
