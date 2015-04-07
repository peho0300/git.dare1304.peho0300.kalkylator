package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.ParserErrorException;

/**
 * Tests the PostfixParser class
 * @author Daniel Rehnberg
 * @version 1.0
 */
public class PostfixParserTest {
	PostfixParser parser;
	List<String> expectedResult;

	@Test
	public void testSimpleAddition() throws ParserErrorException {
		parser = new PostfixParser("5+6");
		expectedResult = Arrays.asList("5", "6", "+");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testDoubleAddition() throws ParserErrorException {
		parser = new PostfixParser("5+6+8");
		expectedResult = Arrays.asList("5", "6", "+", "8", "+");
		assertEquals(expectedResult, parser.getPostfix());
	}

	@Test
	public void testSimpleSubtraction() throws ParserErrorException {
		parser = new PostfixParser("5-6");
		expectedResult = Arrays.asList("5", "6", "-");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testDoubleSubtraction() throws ParserErrorException {
		parser = new PostfixParser("5-6-8");
		expectedResult = Arrays.asList("5", "6", "-", "8", "-");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testAdditionSubtractionPriority() throws ParserErrorException {
		parser = new PostfixParser("6+5-4");
		expectedResult = Arrays.asList("6", "5", "+", "4", "-");
		assertEquals(expectedResult, parser.getPostfix());
		
		parser = new PostfixParser("6-5+4");
		expectedResult = Arrays.asList("6", "5", "-", "4", "+");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testSimpleMultiplication() throws ParserErrorException {
		parser = new PostfixParser("16*45");
		expectedResult = Arrays.asList("16", "45", "*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testDoubleMultiplication() throws ParserErrorException {
		parser = new PostfixParser("16*45*19");
		expectedResult = Arrays.asList("16", "45", "*", "19", "*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testSimpleDivision() throws ParserErrorException {
		parser = new PostfixParser("16/45");
		expectedResult = Arrays.asList("16", "45", "/");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testDoubleDivision() throws ParserErrorException {
		parser = new PostfixParser("16/45/19");
		expectedResult = Arrays.asList("16", "45", "/", "19", "/");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testTripleDivision() throws ParserErrorException {
		parser = new PostfixParser("16/45/19/88");
		expectedResult = Arrays.asList("16", "45", "/", "19", "/", "88", "/");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testPriorities() throws ParserErrorException {
		parser = new PostfixParser("6/2*(1+2)");
		expectedResult = Arrays.asList("6","2","/","1","2","+","*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testSimpleParenthesis() throws ParserErrorException {
		parser = new PostfixParser("(4+6)*5");
		expectedResult = Arrays.asList("4", "6", "+", "5", "*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testNestedParenthesis() throws ParserErrorException {
		parser = new PostfixParser("(4+6*(8+18))*5");
		expectedResult = Arrays.asList("4", "6", "8", "18", "+", "*", "+", "5", "*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testImplicitMultiplication() throws ParserErrorException {
		parser = new PostfixParser("10(3*3)18");
		expectedResult = Arrays.asList("10", "3", "3", "*", "*", "18", "*");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testComplexExpression() throws ParserErrorException {
		parser = new PostfixParser("3*(5+3*6)-5+2*(9/2-3)");
		expectedResult = Arrays.asList("3","5","3","6","*","+","*","5","-","2","9","2","/","3","-","*","+");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test
	public void testWhitespace() throws ParserErrorException {
		parser = new PostfixParser("8 +18");
		expectedResult = Arrays.asList("8","18","+");
		assertEquals(expectedResult, parser.getPostfix());
	}
	
	@Test(expected=ParserErrorException.class)
	public void testLetter() throws ParserErrorException {
		parser = new PostfixParser("8+L");
		parser.getPostfix();
	}
	
	@Test(expected=ParserErrorException.class)
	public void testEmptyInput() throws ParserErrorException {
		parser = new PostfixParser("");
		parser.getPostfix();
	}
	
	@Test(expected=ParserErrorException.class)
	public void testDoubleOperator() throws ParserErrorException {
		parser = new PostfixParser("23**14");
		parser.getPostfix();
	}
	
	@Test(expected=ParserErrorException.class)
	public void testNoStartParenthesis() throws ParserErrorException {
		parser = new PostfixParser("4*8)+3");
		parser.getPostfix();
	}
	
	@Test(expected=ParserErrorException.class)
	public void testNoEndParenthesis() throws ParserErrorException {
		parser = new PostfixParser("(4*8+3");
		parser.getPostfix();
	}
}
