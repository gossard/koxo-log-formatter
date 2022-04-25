package com.github.gossard.klogformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class LogFileFormatTest {

	@Test
	void create() {
		LogFileFormat format = new LogFileFormat("LOG", 6, "#####", "[LOGOFF]");
		assertEquals("LOG", format.getUpperCaseExtension());
		assertEquals(6, format.getNumHeaderLines());
		assertEquals("#####", format.getHeaderStartsWith());
		assertEquals("[LOGOFF]", format.getLogOffPrefix());
	}

	@Test
	void isLogFile() {
		LogFileFormat format = new LogFileFormat("ABC", 6, "#####", "[LOGOFF]");
		assertTrue(format.isLogFile(new File("file.abc")));
		assertTrue(format.isLogFile(new File("file.ABC")));
		assertFalse(format.isLogFile(new File("file")));
	}

	@Test
	void hasHeaderTrue() {
		LogFileFormat format = new LogFileFormat("ABC", 1, "#####", "[LOGOFF]");
		List<String> lines = new ArrayList<>();
		lines.add("#####");
		assertTrue(format.hasHeader(lines));
	}

	@Test
	void hasHeaderFalseWithEmptyLines() {
		LogFileFormat format = new LogFileFormat("ABC", 6, "#####", "[LOGOFF]");
		assertFalse(format.hasHeader(Collections.emptyList()));
	}

	@Test
	void hasHeaderFalseWithInvalidNumLines() {
		LogFileFormat format = new LogFileFormat("ABC", 2, "#####", "[LOGOFF]");
		List<String> lines = new ArrayList<>();
		lines.add("#####");
		assertFalse(format.hasHeader(lines));
	}

	@Test
	void hasHeaderFalseWithLinesThatDoNotStartWithGivenString() {
		LogFileFormat format = new LogFileFormat("ABC", 1, "#####", "[LOGOFF]");
		List<String> lines = new ArrayList<>();
		lines.add("abc");
		assertFalse(format.hasHeader(lines));
	}

}
