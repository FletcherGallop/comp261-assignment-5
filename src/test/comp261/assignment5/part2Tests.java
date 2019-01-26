package test.comp261.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.HuffmanCoding;
import nz.comp261.assignment5.KMP;

class part2Tests {

	@Test
	void test1() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);
		
		String huffEncoded = huff.encode(text);
		
		System.out.println(huffEncoded);
	}
	
	@Test
	void test2() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		
		String huffDecoded = huff.decode(huffEncoded);
		
		System.out.println(huffDecoded);
	}

}
