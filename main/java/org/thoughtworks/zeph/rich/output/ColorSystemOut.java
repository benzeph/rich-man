package org.thoughtworks.zeph.rich.output;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class ColorSystemOut {

	interface api extends StdCallLibrary {
		api INSTANCE = (api) Native.loadLibrary("kernel32", api.class);

		int GetStdHandle(int stdHand);

		boolean SetConsoleTextAttribute(int hConsoleOutput, int textAtt);
	}

	public static void println(String str, int color) {
		int ptr = api.INSTANCE.GetStdHandle(-11);
		api.INSTANCE.SetConsoleTextAttribute(ptr, color);
		System.out.println(str);
	}

	public static void print(String str, int color) {
		int ptr = api.INSTANCE.GetStdHandle(-11);
		api.INSTANCE.SetConsoleTextAttribute(ptr, color);
		System.out.print(str);
	}
}
