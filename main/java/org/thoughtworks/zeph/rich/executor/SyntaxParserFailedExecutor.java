package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.output.SystemOut;

public class SyntaxParserFailedExecutor implements Executor {
	@Override
	public void execute() {
		SystemOut.illegalInstruction();
	}
}
