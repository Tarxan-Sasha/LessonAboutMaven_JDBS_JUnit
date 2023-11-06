package LessonAboutJUnit;

public class Calculator {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		int i = calculator.sum(3, 3);
		System.out.println(i);

	}
	
	public int sum(int x, int y) {
		return x+y;
	}
	public boolean numberEven(int x) {
		
		return x%2==0;
	}
	


}
