package net.nifoo.tij.cls;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MySet {

	class MyIterator implements Iterator {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	private Set mySet;

	public MySet(Set mySet) {
		this.mySet = mySet;
	}
	
	public static void main(String[] args) {
		MySet set = new MySet(new TreeSet());
		Iterator itor = set.new MyIterator();
	}

}
