package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.output.Printer;

public class SyntaxParserFailedExecutor implements Executor {
	@Override
	public void execute() {
		Printer.illegalInstruction();
	}
}
