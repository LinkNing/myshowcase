package net.nifoo.tij.inherit;

public class Parent implements MyInterface{

	private String info = "Parent";
	
	public Parent() {
		this("Parent");
	}

	public Parent(String info) {
		this.info = info;
		System.out.println("Parent init");
		show();
	}
	
	private void prvMethod(){
		
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void show() {
		System.out.println(info);
	}

	@Override
	public int getRadom(int maxValue) {
		return 0;
	}

}
