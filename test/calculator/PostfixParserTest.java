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

}
