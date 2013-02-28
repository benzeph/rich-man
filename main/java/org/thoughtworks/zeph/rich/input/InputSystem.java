package org.thoughtworks.zeph.rich.input;

import java.io.InputStream;
import java.util.Scanner;

public class InputSystem {

	private Scanner scanner;

	public InputSystem(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}

	public InputSystem(String instruction) {
		scanner = new Scanner(instruction);
	}

	public String getInput() {
		return scanner.nextLine();
	}
}
