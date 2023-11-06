package LessonAboutJUnit_Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import LessonAboutJUnit.LessonAboutJUnit;

/*
 * Для того что бы определить метод тестом в JUnit 5 есть анотации. 
 * Их не так уж и много:
 * 1) @Test - Стандартная анотация теста, указывает что данный метод являеться тестом.
 * 2) @BeforeEach - Обозначает что метод должен будет выполниться перед КАЖДЫМ тестом.
 * Метод с такой анотацией НЕ может быть статическим. 
 * 3) @BeforeAll - Обозначает что метод выполнится один раз перед ВСЕМИ тестами. 
 * Метод с такой анотацией ДОЛЖЕН быть статическим. 
 * 4) @AfterEach - Обозначает что метод выполнится после КАЖДОГО теста.
 * Метод с такой анотацией НЕ может быть статическим. 
 * 5) @AfterAll - Обозначет что метод выполниться после ВСЕХ тестов.
 * Метод с такой анотацией ДОЛЖЕН быть статическим. 
 * 6) @DisplayName - Дает тестовому методу или классу имя, по которому в последствии можно будет их фильтровать и в целом удобней будет найти в графе тестов
 * 7) @Disable - Отключает тестовый медот или класс, то есть тесты с такой анотацией будут игнорироваться
 * 8) @Nested - Обозначает вложеный тестовый класс
 * 9) @Tag - Дает тестовому классу тег/пометочку. Это нужно для фильтрации и обнаружения теста в наборе. Но про наборы потом
 * 10) @TestFactory - тестовая фабрика для динамических тестов.
 * 11) @RepeatedTest - вызывает тест несколько раз.
 * 12) @Nested - используется для создания тестовых классов, они нужны, как я понимаю, когда треубеться отделить группу тестов по каким то причинам, 
 * например сугубо логических целей, типо: тесты деления сюда, а тесты на время сюда...
 * Анотации @BeforeEach и @AfterEach будут работать и для тестов во вложеных классах. 
 * Это не все анотации, но основные.
 * 
 * Есть класс Assertions который имеет кучку статических методов для различных проверок. 
 * Эти методы проверяют ожидаемый результат с текущим. 
 * Большинство из них работают так, в параметр ложиться первое число - это то что мы хотим видеть, и второе само действие.
 * После чего метод сравнивает ожидание с реальным результатом действия.
 * В целом этих методов много, но вот основные:
 * 1) assertEquals() / assertNotEquals() - эти два метода сравнивают на равно ли ожидание к текущему результату.
 * Важно отметить что если мы сравниваем обьекты, тогда будет использоваться метод equals() этих обьектов. 
 * И потому этот метод лучше переопределить, иначе в своем обычном виде он просто сравнивает ссылки обьектов.
 * 2) assertSame() / asserNottSame() - эти два метода похожи на прошлые два, они проверяют равны ли два обьекта или нет. В плане по ссылкам.
 * Проверяют имеют ли два обьекта одну ссылку ил нет, ссылаютсья ли они на одно и тоже. Грубо говоря это буквально не переопределенный метод equals().
 * 3) assertArrayEquals() / qssertArrayEquals() - проверяют равны ли значения ожидаемого и реального массива.
 * 4) assertNull() / assertNotNull() - проверяет являеться ли реальный результат NUll или нет.
 * 5) assertTrue() / assertFalse() - проверяет являеться ли реальный результат true или false.
 * 6) assertAll() - Он выполняет сразу несколько тестов в один момент, при чем если один тест не пройдет, он все равно протестирует все данные ему тесты.
 * (Обычно если мы в одном тестовом методе пишем несколько тестов/assert'ов то как только один из них не проходит, то сразу все дропаеться и следующие тесты не тестируються,
 * Но с методом assertAll() протестируються в любом случае все)
 * Этот метод сначала принимает строку которую выведет на консоль, перед результатми тестов, а уже после лямбды выражения, где каждая лямбда это какой то метод assert.
 * 7) assertTimeout() - этот метод проверяет что бы тест выполнился за определенное количество времени. 
 * Первым аргументом вписываеться время, с помощью какого то статического метода класса Duration (из пакета java.time.Duration). 
 * Например, ofMillis() сюда нужно вписать int значение, обозначающее время в милисикундах, ну и дальше там подобные ему методы, только для секунд минут часов дней.
 * А вторым лямда выражение которое должно будет отработать за указанное время или меньше, если дольше тогда тест не пройдет.
 * 8) assertIterableEquals() - этот метод проверяет и сравнивает две колекции, если колекции равны тогда тестп ройден если нет, тогда дропю
 * 9) assertThrow() - этот метод метод првоеряет выбрасываеться ли исключение или нет, 
 * в первый аргумент кладеться тип какого исключения мы хотим получить, а вторым лямбда выражение которое мы проверяем.
 * Если указанное исключение выброситься то тест пройден, если нет тогда провал.
 * Этим тестом можно тестировать, например, логику catch.
 * 10) fail() - собственоручный дроп теста
 * 
 */
@DisplayName("Наш первый ТестКласс")//Имя для тестовго класса, почему бы и нет
public class LessonAboutJUnit_Test2 {
	LessonAboutJUnit laj = new LessonAboutJUnit();
	static int i;
////////////////////////////
	@BeforeAll//Тест сработает перед всеми тестами
	public static void beforeAll() {
		System.out.println("Тесты пошли: ");
	}

	@BeforeEach//Тест сработает перед каждым тестом
	public void beforeEachTests() {
		i++;
		System.out.println("Тест " + i + " запускаеться ");
	}
/////////////////////////////////
	
	@DisplayName("NotEquals")//Имя теста
	@Test
	public void assertNotEqualsTest() {
		Assertions.assertNotEquals(5, laj.getMultiplication(2, 2),"Не равны");
	}

	@Test
	public void assertEqualsTest() {
		LessonAboutJUnit lej1 = new LessonAboutJUnit();
		lej1.setK(2);
		LessonAboutJUnit lej2 = new LessonAboutJUnit();
		lej2.setK(2);

		Assertions.assertEquals(lej1, lej2, "Это два разных обьекта");
	}

	@Test
	public void assertSameTest() {
		LessonAboutJUnit lej1 = new LessonAboutJUnit();
		lej1.setK(2);
		LessonAboutJUnit lej2 = new LessonAboutJUnit();
		lej2.setK(2);

		Assertions.assertSame(lej1, lej2, "Это две сылки ссылаються на разные участки памяти(обьекты)");
	}

	@Test
	public void assertTrueTest() {
		int x = 4;
		Assertions.assertTrue(laj.evenNumber(x), "Число не четное");
	}

	@Test
	public void assertNullTest() {
		Assertions.assertNull(laj.evenNumber(1), "Значение не null");
	}

	@Test
	public void assertAllTest() {
		Assertions.assertAll("Кучка тестов: ", () -> Assertions.assertFalse(laj.evenNumber(5)),
				() -> Assertions.assertEquals(11, laj.getK()), () -> Assertions.assertNotNull(laj.getK()));
	}

	@Test
	public void assertTimeoutTest() {
		Assertions.assertTimeout(Duration.ofMillis(100), () -> Assertions.assertEquals(3, laj.getSum(1, 1)));
	}

	@Test
	public void assertIterableEqualsTest() {
		List<Integer> arrList1 = new ArrayList<Integer>();
		Collections.addAll(arrList1, 1, 2, 3, 4);
		List<Integer> arrList2 = new ArrayList<Integer>();
		Collections.addAll(arrList2, 1, 2, 3, 4);

		Assertions.assertIterableEquals(arrList1, arrList2);
	}
	
	@DisplayName("Наш вложеный ТестКласс")//Имя для вложеного тестовго класса
	@Nested
	class nestedClassTest {
		@Test
		public void assertThrowTest() {
			Assertions.assertThrows(ArithmeticException.class, () -> laj.division(4, 0));
		}

		@Test
		public void failTest() {
			Assertions.fail();
		}
	}
	

//////////////////////////////
	@AfterEach//Тест сработает после всех тестов
	public void afterEachTests() {
		System.out.println("После каждого теста");
	}

	@AfterAll//Тест сработает после каждого теста
	public static void afterAllTests() {
		System.out.println("После всех тестов");
	}
///////////////////////////////

	
}
