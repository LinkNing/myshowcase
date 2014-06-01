package net.nifoo.tij.inherit;

public class Son extends Parent {

	private String info = "Son";

	public Son() {
		this("Son");
	}

	public Son(String info) {
		super(info);
		System.out.println("Son init");
		show();
	}

	private void prvMethod() {

	}

	@Override
	public void setInfo(String info) {
		this.info = info;
		//super.setInfo(info);
	}

	public String getInfo() {
		return super.getInfo();
	}

	public void show() {
		System.out.println("son:" + info);
	}

	static public void main(String[] args) {
		Parent p = new Son();
		//p.setInfo("xxx");
		//p.show();
		//System.out.println(p.getInfo());

		//System.out.println(p instanceof Parent);
		//System.out.println(p.getClass().getSimpleName());
	}

}