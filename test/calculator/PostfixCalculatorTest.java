package calculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testclass for PostfixCalculator class using JUnit.
 * @author Peter Höglund
 * @verison 1.0
 */
public class PostfixCalculatorTest {
	PostfixCalculator calc;

	/**
	 * Test a simple addition.
	 */
	@Test
	public void testSimpleAddition() { 
		calc = new PostfixCalculator(Arrays.asList("4","6","+"));
		assertEquals(10, calc.getResult(),0);
		
	}
	
	/**
	 * Test a more complex expression.
	 */
	@Test
	public void testComplex() {
		calc = new PostfixCalculator(Arrays.asList("4","6","+","5","*"));
		assertEquals(50, calc.getResult(),0);
		
	}

}
