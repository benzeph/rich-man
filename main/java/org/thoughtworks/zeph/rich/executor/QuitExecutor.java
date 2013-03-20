package org.thoughtworks.zeph.rich.executor;

public class QuitExecutor implements Executor {

	@Override
	public void execute() {
		System.exit(0);
	}
}
