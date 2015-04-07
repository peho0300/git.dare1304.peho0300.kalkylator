package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PostfixParserTest {
	PostfixParser parser;
	List<String> expectedResult;

	@Test
	public void testSimpleAddition() {
		parser = new PostfixParser("5+6");
		expectedResult = Arrays.asList("5", "6", "+");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testDoubleAddition() {
		parser = new PostfixParser("5+6+8");
		expectedResult = Arrays.asList("5", "6", "+", "8", "+");
		assertEquals(expectedResult, parser.getPostFix());
	}

	@Test
	public void testSimpleSubtraction() {
		parser = new PostfixParser("5-6");
		expectedResult = Arrays.asList("5", "6", "-");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testDoubleSubtraction() {
		parser = new PostfixParser("5-6-8");
		expectedResult = Arrays.asList("5", "6", "-", "8", "-");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testAdditionSubtractionPriority() {
		parser = new PostfixParser("6+5-4");
		expectedResult = Arrays.asList("6", "5", "+", "4", "-");
		assertEquals(expectedResult, parser.getPostFix());
		
		parser = new PostfixParser("6-5+4");
		expectedResult = Arrays.asList("6", "5", "-", "4", "+");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testSimpleMultiplication() {
		parser = new PostfixParser("16*45");
		expectedResult = Arrays.asList("16", "45", "*");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testDoubleMultiplication() {
		parser = new PostfixParser("16*45*19");
		expectedResult = Arrays.asList("16", "45", "*", "19", "*");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testSimpleDivision() {
		parser = new PostfixParser("16/45");
		expectedResult = Arrays.asList("16", "45", "/");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testDoubleDivision() {
		parser = new PostfixParser("16/45/19");
		expectedResult = Arrays.asList("16", "45", "/", "19", "/");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testTripleDivision() {
		parser = new PostfixParser("16/45/19/88");
		expectedResult = Arrays.asList("16", "45", "/", "19", "/", "88", "/");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testPriorities() {
		parser = new PostfixParser("6/2*(1+2)");
		expectedResult = Arrays.asList("6","2","/","1","2","+","*");
		assertEquals(expectedResult, parser.getPostFix());
	}
	
	@Test
	public void testSimpleParenthesis() {
		parser = new PostfixParser("(4+6)*5");
		expectedResult = Arrays.asList("4", "6", "+", "5", "*");
		assertEquals(expectedResult, parser.getPostFix());
	}
}
