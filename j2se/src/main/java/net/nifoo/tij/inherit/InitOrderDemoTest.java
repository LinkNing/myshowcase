package net.nifoo.tij.inherit;

/**
 * 
 * 类继承时，相关方法执行顺序： 父类Static块 -> 子类Static块 -> 父类初始化块 -> 父类构造函数 -> 子类初始化块 -> 子类构造函数
 * 
 * PS: static 块只会执行一次
 * 
 * @author Nifoo Ning
 *
 */
public class InitOrderDemoTest {

	public static void main(String[] args) {
		InitOrderDemoTest.Son son1 = new Son();
		InitOrderDemoTest.Son son2 = new Son();
	}

	static class Pa {
		static {
			System.out.println("1. Pa's static block");
		}

		{
			System.out.println("3. Pa's init block");
		}

		public Pa() {
			System.out.println("4. Pa's construction function");
		}
	}

	static class Son extends Pa{
		static {
			System.out.println("2. Son's static block");
		}

		public Son() {
			System.out.println("6. Son's construction function");
		}

		{
			System.out.println("5. Son's init block");
		}
	}

}
