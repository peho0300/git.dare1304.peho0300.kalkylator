package calculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PostfixCalculatorTest {
	PostfixCalculator calc;

	@Test
	public void testSimpleAddition() {
		
		List<String> lista = new ArrayList<String>();
		lista.addAll(Arrays.asList("4","6","+"));
		calc = new PostfixCalculator(lista);
		assertEquals(10, calc.getResult(),0);
		
	}
	
	@Test
	public void testComplex() {
		
		List<String> lista = new ArrayList<String>();
		lista.addAll(Arrays.asList("4","6","+","5","*"));
		calc = new PostfixCalculator(lista);
		assertEquals(50, calc.getResult(),0);
		
	}

}
