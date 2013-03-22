package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.SyntaxParserFailedExecutor;

public class SyntaxParserFailed implements SyntaxParser{
	@Override
	public Executor parse() {
		return new SyntaxParserFailedExecutor();
	}
}
