package net.nifoo.tij;

import java.util.Arrays;

public class App {
	
	public static void main(String[] args) {
		Num[] numbers = new Num[]{new Num(2), new Num(4), new Num(6), new Num(8)};
		
		Num[] nums = new Num[4];
		
		Arrays.fill(nums, new Num(0));
		
		System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.deepEquals(numbers, nums));
	}
}

class Num{
	int num;
	
	public Num(int num){
		this.num = num;
	}
	
	public String toString(){
		return "Number:" + Integer.toString(num);
	}
}
