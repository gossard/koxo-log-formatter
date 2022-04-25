package com.github.gossard.klogformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RemoveIfStartsWithLinesFormatterTest {

	@Test
	void create() {
		RemoveIfStartsWithLinesFormatter formatter = new RemoveIfStartsWithLinesFormatter("prefix");
		assertEquals("prefix", formatter.getPrefix());
	}

	@Test
	void format() {
		RemoveIfStartsWithLinesFormatter formatter = new RemoveIfStartsWithLinesFormatter("toRemove:");
		List<String> lines = new ArrayList<>();
		lines.add("toRemove: 1");
		lines.add("2");
		lines.add("toRemove: 3");
		lines.add("4");

		formatter.format(lines);
		assertEquals(2, lines.size());
		assertEquals("2", lines.get(0));
		assertEquals("4", lines.get(1));
	}

}
