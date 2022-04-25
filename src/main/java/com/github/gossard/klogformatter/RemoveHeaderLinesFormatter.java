package com.github.gossard.klogformatter;

import java.util.Iterator;
import java.util.List;

public class RemoveHeaderLinesFormatter implements LinesFormatter {

	private final int numHeaderLines;

	public RemoveHeaderLinesFormatter(int numHeaderLines) {
		this.numHeaderLines = numHeaderLines;
	}

	@Override
	public void format(List<String> lines) {

		if (lines.size() < numHeaderLines)
			throw new IllegalArgumentException("The number of lines must be greater than or equal to " + numHeaderLines
					+ " (" + lines.size() + ")");

		Iterator<String> it = lines.iterator();
		for (int i = 0; i < numHeaderLines; i++) {
			it.next();
			it.remove();
		}
	}

	public int getNumHeaderLines() {
		return numHeaderLines;
	}

}
