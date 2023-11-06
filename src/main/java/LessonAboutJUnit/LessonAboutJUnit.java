package LessonAboutJUnit;

public class LessonAboutJUnit {

	private int k;

	public static void main(String[] args) {

		LessonAboutJUnit calculator = new LessonAboutJUnit();
		System.out.println(calculator.division(4,0));

	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	public int getSum(int x, int y) {
		return x + y;
	}

	public int getMultiplication(int x, int y) {
		return x * y;
	}

	public boolean evenNumber(int x) {
		return x % 2 == 0;
	}
	public int division(int x, int y) {
		return x/y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LessonAboutJUnit other = (LessonAboutJUnit) obj;
		return k == other.k;
	}



}
