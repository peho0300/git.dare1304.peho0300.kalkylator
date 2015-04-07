package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.ParserErrorException;

/**
 * Testclass for Calculator class using JUnit.
 * @author Peter H�glund
 * @verison 1.0
 */
public class CalculatorTest {

	@Test
	public void test() {
		/*Create object*/
		Calculator calc = new Calculator();
		
		try {
			/*Test different expressions*/
			assertEquals(50, calc.getResult("10*5"),0);
			assertEquals(53, calc.getResult("10*5+3"),0);
			assertEquals(90, calc.getResult("10*(3*3)"),0);
			assertEquals(125, calc.getResult("5+5*3*(4+4)"),0);
			assertEquals(1, calc.getResult("8/(4+4)"),0);
			assertEquals(29, calc.getResult("4*6+5"),0);
			assertEquals(5, calc.getResult("6-5+4"),0);
			assertEquals(50, calc.getResult("(4+6)*5"),0);
			assertEquals(67, calc.getResult("3*(5+3*6)-5+2*(9/2-3)"),0);
		} 
		catch (ParserErrorException e) {
			e.printStackTrace();
		}
		
	}

}
