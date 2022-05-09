package com.github.gossard.klogformatter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class LogFormatter {

	private final File srcDir, dstDir;
	private final List<LinesFormatter> formatters;
	private final LogFileFormat format;

	public LogFormatter(File srcDir, File dstDir, List<LinesFormatter> formatters, LogFileFormat format) {
		this.srcDir = srcDir;
		this.dstDir = dstDir;
		this.formatters = formatters;
		this.format = format;
	}

	public int format() throws Exception {
		dstDir.mkdir();
		int numFormattedFiles = 0;

		File[] srcFiles = srcDir.listFiles();
		List<String> allLines = new ArrayList<>();

		for (File srcFile : srcFiles) {
			if (!format.isLogFile(srcFile))
				continue;

			List<String> lines = Files.readAllLines(srcFile.toPath());
			if (!format.hasHeader(lines))
				continue;

			for (LinesFormatter formatter : formatters)
				formatter.format(lines);

			allLines.addAll(lines);

			File dstFile = new File(dstDir, srcFile.getName());
			Files.write(dstFile.toPath(), lines, StandardOpenOption.CREATE);
			numFormattedFiles++;
		}
		if (!allLines.isEmpty()) {
			File combinedFile = new File(dstDir, "combined." + format.getExtension());
			Files.write(combinedFile.toPath(), allLines, StandardOpenOption.CREATE);
		}
		return numFormattedFiles;
	}

	public File getSrcDir() {
		return srcDir;
	}

	public File getDstDir() {
		return dstDir;
	}

	public List<LinesFormatter> getFormatters() {
		return formatters;
	}

	public LogFileFormat getFormat() {
		return format;
	}

}
