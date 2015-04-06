package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PostfixParserTest {
	PostfixParser parser;
	List<String> expectedResult;
	
	@Before
	public void setUp() throws Exception {
		parser = new PostfixParser();
	}

	@Test
	public void testSimpleAddition() {
		expectedResult = Arrays.asList("5", "6", "+");
		assertEquals(expectedResult, parser.getPostFix("5+6"));
	}
	
	@Test
	public void testDoubleAddition() {
		expectedResult = Arrays.asList("5", "6", "+", "8", "+");
		assertEquals(expectedResult, parser.getPostFix("5+6+8"));
	}

	@Test
	public void testSimpleSubtraction() {
		expectedResult = Arrays.asList("5", "6", "-");
		assertEquals(expectedResult, parser.getPostFix("5-6"));
	}
	
	@Test
	public void testDoubleSubtraction() {
		expectedResult = Arrays.asList("5", "6", "-", "8", "-");
		assertEquals(expectedResult, parser.getPostFix("5-6-8"));
	}
	
	@Test
	public void testAdditionSubtractionPriority() {
		expectedResult = Arrays.asList("6", "5", "+", "4", "-");
		assertEquals(expectedResult, parser.getPostFix("6+5-4"));
		expectedResult = Arrays.asList("6", "5", "-", "4", "+");
		assertEquals(expectedResult, parser.getPostFix("6-5+4"));
	}
	
	@Test
	public void testSimpleMultiplication() {
		expectedResult = Arrays.asList("16", "45", "*");
		assertEquals(expectedResult, parser.getPostFix("16*45"));
	}
	
	@Test
	public void testDoubleMultiplication() {
		expectedResult = Arrays.asList("16", "45", "*", "19", "*");
		assertEquals(expectedResult, parser.getPostFix("16*45*19"));
	}
	
	@Test
	public void testSimpleDivision() {
		expectedResult = Arrays.asList("16", "45", "/");
		assertEquals(expectedResult, parser.getPostFix("16/45"));
	}
	
	@Test
	public void testDoubleDivision() {
		expectedResult = Arrays.asList("16", "45", "/", "19", "/");
		assertEquals(expectedResult, parser.getPostFix("16/45/19"));
	}
	
	@Test
	public void testTripleDivision() {
		expectedResult = Arrays.asList("16", "45", "/", "19", "/", "88", "/");
		assertEquals(expectedResult, parser.getPostFix("16/45/19/88"));
	}
	
	@Test
	public void testPriorities() {
		expectedResult = Arrays.asList("6","2","/","1","2","+","*");
		assertEquals(expectedResult, parser.getPostFix("6/2*(1+2)"));
	}
	
	@Test
	public void testSimpleParenthesis() {
		expectedResult = Arrays.asList("4", "6", "+", "5", "*");
		assertEquals(expectedResult, parser.getPostFix("(4+6)*5"));
	}
}
