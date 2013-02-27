package org.thoughtworks.zeph.rich.output;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OutputTest {

	interface api extends StdCallLibrary {
		api INSTANCE = (api) Native.loadLibrary("kernel32", api.class);
		int GetStdHandle(int stdHand);
		boolean SetConsoleTextAttribute(int hConsoleOutput, int textAtt);
	}

	public static void out(String str, int color) {
		int ptr = api.INSTANCE.GetStdHandle(-11);
		api.INSTANCE.SetConsoleTextAttribute(ptr, color);
		System.out.println(str);
	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_print_color_word_when_run_test_below() {
		out("hello ", 5);
		out("world", 6);
	}
}
