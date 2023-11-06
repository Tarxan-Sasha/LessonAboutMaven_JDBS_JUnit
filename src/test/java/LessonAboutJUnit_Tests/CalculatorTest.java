package LessonAboutJUnit_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import LessonAboutJUnit.Calculator;



@Tag("Blue")
public class CalculatorTest {

	
	@BeforeEach
	public void something() {
		System.out.println("Dota");
	}
	
	
	@Test
	public void sumTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(5, calculator.sum(3, 3), "Сумма не равна заявленному числу");
	}
	
	@Test
	public void numberEvenTest() {
		Calculator calculator = new Calculator();
		Assertions.assertTrue(calculator.numberEven(5), "Число являеться не четным");		
	}
	
	
}
