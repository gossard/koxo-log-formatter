package com.github.gossard.klogformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class RemoveHeaderLinesFormatterTest {

	@Test
	void create() {
		RemoveHeaderLinesFormatter formatter = new RemoveHeaderLinesFormatter(2);
		assertEquals(2, formatter.getNumHeaderLines());
	}

	@Test
	void formatSuccess() {
		RemoveHeaderLinesFormatter formatter = new RemoveHeaderLinesFormatter(2);
		List<String> lines = new ArrayList<>();
		lines.add("1");
		lines.add("2");
		lines.add("3");

		formatter.format(lines);
		assertEquals(1, lines.size());
		assertEquals("3", lines.get(0));
	}

	@Test
	void formatFail() {
		RemoveHeaderLinesFormatter formatter = new RemoveHeaderLinesFormatter(2);
		List<String> lines = new ArrayList<>();
		lines.add("");

		Executable exe = () -> formatter.format(lines);
		Exception e = assertThrows(IllegalArgumentException.class, exe);
		assertEquals("The number of lines must be greater than or equal to 2 (1)", e.getMessage());
	}

}
