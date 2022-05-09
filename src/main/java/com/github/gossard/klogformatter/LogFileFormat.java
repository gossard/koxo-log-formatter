package com.github.gossard.klogformatter;

import java.io.File;
import java.util.List;

public class LogFileFormat {

	private final String extension;
	private final int numHeaderLines;
	private final String headerStartsWith;
	private final String logOffPrefix;

	public LogFileFormat(String extension, int numHeaderLines, String headerStartsWith, String logOffPrefix) {
		this.extension = extension;
		this.numHeaderLines = numHeaderLines;
		this.headerStartsWith = headerStartsWith;
		this.logOffPrefix = logOffPrefix;
	}

	public boolean isLogFile(File file) {
		return file.getName().toUpperCase().endsWith("." + extension.toUpperCase());
	}

	public boolean hasHeader(List<String> lines) {
		if (lines.isEmpty() || lines.size() < numHeaderLines)
			return false;
		return lines.get(0).startsWith(headerStartsWith);
	}

	public String getExtension() {
		return extension;
	}

	public int getNumHeaderLines() {
		return numHeaderLines;
	}

	public String getHeaderStartsWith() {
		return headerStartsWith;
	}

	public String getLogOffPrefix() {
		return logOffPrefix;
	}

}
