package net.nifoo.java8.intf;

public class InterfaceWithDefaultMethodDemo {

	public static void main(String[] args) {

		System.out.printf("static method in Interface: %s; \n", Formula.echo("It works!"));

		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return a * a;
			}
		};

		System.out.println(formula.calculate(16));
		System.out.println(formula.sqrt(16));
	}

}

interface Formula {
	double calculate(int a);
	
	// 可以使用实例方法
	default double sqrt(int a) {
		double b = this.calculate(a);
		return Math.sqrt(b);
	}
	
	// 可以定义静态方法
	static String echo(String s) {
		return s;
	}
	
}