/*
 * Copyright (c) 2023 Bernhard Haumacher et al. All Rights Reserved.
 */
package de.haumacher.phoneblock.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test of {@link NumberTree}.
 */
class TestNumberTree {
	
	@Test
	void testLeve1Wildcard() {
		NumberTree tree = new NumberTree();
		tree.insert("0201234567");
		tree.insert("0305200156399");
		tree.insert("0305200156332");
		tree.insert("0305200156327");
		tree.insert("0305200156329");
		tree.insert("0305200156328");
		tree.insert("0305200156330");
		tree.insert("0305200156331");
		tree.insert("0305200156325");
		tree.insert("0305200156324");
		tree.insert("0305200156333");
		tree.insert("0305200156322");
		tree.markWildcards();
		List<String> entries = tree.createBlockEntries();
		
		assertEquals(List.of("0201234567", "030520015632*", "030520015633*", "0305200156399"), entries);
	}

	@Test
	void testWhitelistBreaksWildcard() {
		NumberTree tree = new NumberTree();
		tree.insert("0100001");
		tree.insert("0100002");
		tree.insert("0100003");
		tree.insert("0100004", -10, 0);
		tree.insert("0100005");
		
		tree.insert("0200001");
		tree.insert("0200002");
		tree.insert("0200003");
		tree.insert("0200005");
		tree.markWildcards();
		List<String> entries = tree.createBlockEntries();
		
		assertEquals(List.of("0100001", "0100002", "0100003", "0100005", "020000*"), entries);
	}
	
	@Test
	void testWhitelistBreaksWildcardLevel2() {
		NumberTree tree = new NumberTree();
		tree.insert("01000015");
		tree.insert("01000016");
		tree.insert("01000017");
		tree.insert("01000027");
		tree.insert("01000028");
		tree.insert("01000029");
		tree.insert("0100003");
		tree.insert("01000041", -10, 0);
		tree.insert("01000057");
		tree.insert("01000058");
		tree.insert("01000059");
		tree.markWildcards();
		List<String> entries = tree.createBlockEntries();
		
		assertEquals(List.of("0100001*", "0100002*", "0100003", "0100005*"), entries);
	}
	
	@Test
	void testLeve2Wildcard() {
		NumberTree tree = new NumberTree();
		tree.insert("030123010");
		tree.insert("030123011");
		tree.insert("030123012");
		tree.insert("030123020");
		tree.insert("030123021");
		tree.insert("030123022");
		tree.insert("030123030");
		tree.insert("030123031");
		tree.insert("030123032");
		tree.markWildcards();
		List<String> entries = tree.createBlockEntries();
		
		assertEquals(List.of("0301230*"), entries);
	}
	
	@Test
	void testInternational() {
		NumberTree tree = new NumberTree();
		tree.insert("+4930123010");
		tree.insert("+4930123011");
		tree.insert("+4930123012");
		tree.insert("+4930123020");
		tree.insert("+4930123021");
		tree.insert("+4930123022");
		tree.insert("+4930123030");
		tree.insert("+4930123031");
		tree.insert("+4930123032");
		tree.insert("+4330123010");
		tree.insert("+4330123011");
		tree.insert("+4330123012");
		tree.insert("+4330123020");
		tree.insert("+4330123021");
		tree.insert("+4330123022");
		tree.insert("+4330123030");
		tree.insert("+4330123031");
		tree.insert("+4330123032");
		tree.markWildcards();
		List<String> entries = tree.createBlockEntries();
		
		assertEquals(List.of("+43301230*", "+49301230*"), entries);
	}
	
	@Test
	void testRealData() throws IOException {
		NumberTree tree = new NumberTree();

		int cnt = 0;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("SPAMREPORTS_202309301715.csv"), StandardCharsets.UTF_8))) {
			in.readLine();
			
			String line;
			while ((line = in.readLine()) != null) {
				if (line.isBlank()) {
					continue;
				}
				
				if (line.startsWith("\"") && line.endsWith("\"")) {
					String phone = line.substring(1, line.length() - 1);
					
					cnt ++;
					tree.insert(phone);
				}
			}
		}
		
		tree.markWildcards();
		
		List<NumberBlock> blocks = tree.createNumberBlocks(1, 300, "+49");
		int numbers = 0;
		int wildcard = 0;
		
		for (NumberBlock block : blocks) {
			System.out.println("## " + block.getBlockTitle());
			for (String number : block.getNumbers()) {
				System.out.println(number);
				
				numbers++;
				if (number.endsWith("*")) {
					wildcard++;
				}
			}
			
			System.out.println();
		}
		
		System.out.println("Input numbers: " + cnt);
		System.out.println("Output numbers: " + numbers);
		System.out.println("Wildcards: " + wildcard);
		System.out.println("Blocks: " + blocks.size());
		assertEquals(10049, cnt);
		assertEquals(266, wildcard);
		assertEquals(300, numbers);
		assertEquals(99, blocks.size());
	}
	
}
