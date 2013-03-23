package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.output.Printer;

public class QuitExecutor implements Executor {

	@Override
	public void execute() {
		Printer.gameOver();
		System.exit(0);
	}
}
