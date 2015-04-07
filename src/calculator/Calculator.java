package calculator;

public class Calculator {
	
	String exp;
	
	public Calculator(String expression) {
		exp = expression;
	}
	
	public Calculator() {
		
	}
	
	public float getResult() {
		PostfixParser parser = new PostfixParser(exp);
		PostfixCalculator calc = new PostfixCalculator(parser.getPostFix());
		
		return calc.getResult();
		
	}
	
	public float getResult(String expression) {
		exp=expression;
		return getResult();
	}
	
	
}
