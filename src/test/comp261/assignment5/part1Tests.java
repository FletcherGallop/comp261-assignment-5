package test.comp261.assignment5;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

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
		assertTrue(kmp.bruteForceAlgorithm(pattern, text) != -1); //Is present
		
		assert(true);
		
	}
	
	@Test
	void test5() {
		String text = DEFAULT_STRING;
		String pattern = "concious";
		KMP kmp = new KMP(pattern, text);
		
		assertTrue(text.length() != 0);
		assertTrue(kmp.bruteForceAlgorithm(pattern, text) != -1); //Is present
		
	}
	
	@Test
	void test6() {
		String text = DEFAULT_STRING;
		String pattern = "concious";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.nanoTime();
		kmp.bruteForceAlgorithm(pattern, text);
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		
		System.out.println("Test 6:");
		System.out.print("\nExecuted in: " + TimeUnit.NANOSECONDS.toMillis(executionTime) % 1E+3 + " milliseconds, ");
		System.out.print(executionTime % 1E+6 + " nanoseconds");
	}
	
	@Test
	void test7a() {
		String text = DEFAULT_STRING;
		String pattern = "it was necessary to renounce the consciousness";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.nanoTime();
		int searchResults = kmp.bruteForceAlgorithm(pattern, text);
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 7a:");
		System.out.println("Brute Force Search:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + TimeUnit.NANOSECONDS.toMillis(executionTime) + " milliseconds, ");
		System.out.print(executionTime + " nanoseconds");
	}

	@Test
	void test7b() {
		String text = DEFAULT_STRING;
		String pattern = "it was necessary to renounce the consciousness";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.nanoTime();
		int searchResults = kmp.search(pattern, text);
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 7b:");
		System.out.println("KMP Search:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + TimeUnit.NANOSECONDS.toMillis(executionTime) + " milliseconds, ");
		System.out.print(executionTime + " nanoseconds");
	}
	
	@Test
	void test8b() {
		String text = DEFAULT_STRING;
		String pattern = "it was necessary to renounce the consciousness";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.currentTimeMillis();
		int searchResults = kmp.search(pattern, text);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 8b:");
		System.out.println("KMP Search:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + executionTime + " milliseconds\n");
	}
	
	@Test
	void test8a() {
		String text = DEFAULT_STRING;
		String pattern = "it was necessary to renounce the consciousness";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.currentTimeMillis();
		int searchResults = kmp.bruteForceAlgorithm(pattern, text);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 8a:");
		System.out.println("Brute Force Search:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + executionTime + " milliseconds\n");
	}
	
	@Test
	void test9a() {
		String text = DEFAULT_STRING;
		String pattern = "pigeon poop";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.currentTimeMillis();
		int searchResults = kmp.bruteForceAlgorithm(pattern, text);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 9a:");
		System.out.println("Brute Force Search:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + executionTime + " milliseconds\n");
	}
	
	@Test
	void test9b() {
		String text = DEFAULT_STRING;
		String pattern = "pigeon poop";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.currentTimeMillis();
		int searchResults = kmp.search(pattern, text);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.println("\nTest 9b:");
		System.out.println("KMP:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + executionTime + " milliseconds\n");
	}
	
	@Test
	void test10() throws UnsupportedEncodingException, IOException { 
		String text = new String(Files.readAllBytes(Paths.get("data/war_and_peace.txt")), "UTF-8");
		
//		System.out.println(content);
		String pattern = "and to recognize a dependence of which";
		KMP kmp = new KMP(pattern, text);
		
		long startTime = System.currentTimeMillis();
		int searchResults = kmp.search(pattern, text);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.println("\nWar and Peace Test:");
		System.out.println("KMP:");
		if (searchResults == -1) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println("FOUND @:" + searchResults);
		}
		System.out.print("Executed in: " + executionTime + " milliseconds\n");
	}
	

}
