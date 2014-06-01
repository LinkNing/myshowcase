package net.nifoo.tij.inherit;

import java.util.EnumMap;
import java.util.EnumSet;

public class ColorTest {

	public static EnumMap<Color, String> colorInfos = new EnumMap<Color, String>(Color.class);

	static {
		colorInfos.put(Color.RED, "红色");
		colorInfos.put(Color.GREEN, "绿色");
		colorInfos.put(Color.YELLOW, "黄色");
		colorInfos.put(Color.BLUE, "蓝色");
	}

	public void testColor() {
		Color color = Color.RED;
		System.out.println(color);
		System.out.println(color.ordinal());
	}

	public void testEnumMap() {
		System.out.println(colorInfos.get(Color.RED));
	}

	public void testEnumSet() {
		EnumSet<Color> subset = null;
		subset = EnumSet.range(Color.GREEN, Color.BLUE);
		//subset = EnumSet.of(Color.GREEN, Color.BLUE);
		for (Color color : subset) {
			System.out.println(color);
		}
	}

	public static void main(String[] args) {
		ColorTest ct = new ColorTest();
		ct.testColor();
		ct.testEnumMap();
		ct.testEnumSet();
	}

}
