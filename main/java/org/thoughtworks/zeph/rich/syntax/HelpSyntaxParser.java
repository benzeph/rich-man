package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.HelpExecutor;

public class HelpSyntaxParser implements SyntaxParser {
	@Override
	public Executor parser() {
		return new HelpExecutor();
	}
}
