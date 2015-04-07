package calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PostfixCalculatorTest.class, PostfixParserTest.class, CalculatorTest.class })
public class AllTests {

}
