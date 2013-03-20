package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.QuitExecutor;

public class QuitSyntaxParser implements SyntaxParser {
	@Override
	public Executor parse() {
		return new QuitExecutor();
	}
}
