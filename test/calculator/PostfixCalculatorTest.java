package calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class PostfixCalculatorTest {
	PostfixCalculator calc;

	@Test
	public void testSimpleAddition() {
		calc = new PostfixCalculator(Arrays.asList("10","5","+"));
		assertEquals(15, calc.getResult(),0);
	}

}
