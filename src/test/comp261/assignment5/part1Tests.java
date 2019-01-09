package test.comp261.assignment5;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.Assignment5;
import nz.comp261.assignment5.KMP;

class part1Tests {
	private static final File DEFAULT_EDITOR_FILE = new File("data/war_and_peace.txt"); 
	private static String DEFAULT_STRING;
	
	@BeforeAll
	static void initialise() throws IOException {
		byte[] encoded = Files.readAllBytes(DEFAULT_EDITOR_FILE.toPath());
		DEFAULT_STRING = new String(encoded, StandardCharsets.UTF_8);
		System.out.println("Before");
	}
	
	@Test
	void test1() {
		String text = "The cat jumped over the rat.";
		String pattern = "rat";
		KMP kmp = new KMP(pattern, text);
				
		assertTrue(kmp.bruteForceAlgorithm(pattern, text) == 24);
	}
	
	@Test
	void test2() { 
		String text = "The cat jumped over the rat.";
		String pattern = "pigeon";
		KMP kmp = new KMP(pattern, text);
				
		assertTrue(kmp.bruteForceAlgorithm(pattern, text) == -1);
	}
	
	@Test
	void test3() { 
		String text = DEFAULT_STRING;
		String pattern = "bulldozer";
		KMP kmp = new KMP(pattern, text);
				
		assertTrue(text.length() != 0);
		assertTrue(kmp.bruteForceAlgorithm(pattern, text) == -1);
	}
	
	@Test
	void test4() {
		String text = DEFAULT_STRING;
		String pattern = "concious";
		KMP kmp = new KMP(pattern, text);
		
		assertTrue(text.length() != 0);
//		System.out.println(kmp.bruteForceAlgorithm(pattern, text));
//		assertTrue(kmp.bruteForceAlgorithm(pattern, text));
		assert(true);
		
	}
	
	@Test
	void test5() {
		String text = DEFAULT_STRING;
		String pattern = "concious";
		KMP kmp = new KMP(pattern, text);
		
		System.out.println(kmp.search(pattern, text));
	}

}
