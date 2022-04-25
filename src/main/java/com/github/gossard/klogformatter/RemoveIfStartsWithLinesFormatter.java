package com.github.gossard.klogformatter;

import java.util.List;

public class RemoveIfStartsWithLinesFormatter implements LinesFormatter {

	private final String prefix;

	public RemoveIfStartsWithLinesFormatter(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void format(List<String> lines) {
		lines.removeIf((s) -> s.startsWith(prefix));
	}

	public String getPrefix() {
		return prefix;
	}

}
