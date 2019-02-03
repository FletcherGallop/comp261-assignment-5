package test.comp261.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.HuffmanCoding;
import nz.comp261.assignment5.KMP;

class part2Tests {

	@Test
	void test1() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);
		
		String huffEncoded = huff.encode(text);
		
//		System.out.println(huffEncoded);
	}
	
	@Test
	void test2() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		String huffDecoded = huff.decode(huffEncoded);
		
//		System.out.println(huffDecoded);
		assertTrue(text.compareTo(huffDecoded) == 0);
	}
	
	@Test
	void test3() {
		String text = "Are we human? Or are we dancers? My sign is vital, my hands are cold.";
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		String huffDecoded = huff.decode(huffEncoded);
		
//		System.out.println(huffEncoded);
//		System.out.println(huffDecoded);
		assertTrue(text.compareTo(huffDecoded) == 0);
	}
	
	@Test 
	void test4() throws UnsupportedEncodingException, IOException {
		String text = new String(Files.readAllBytes(Paths.get("data/war_and_peace.txt")), "UTF-8"); //I think it's this line here, but I don;t know how else to do it. 
		
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		
		System.out.println(huffEncoded.length());
	}

}
