package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.output.SystemOut;

public class QuitExecutor implements Executor {

	@Override
	public void execute() {
		SystemOut.gameOver();
		System.exit(0);
	}
}
