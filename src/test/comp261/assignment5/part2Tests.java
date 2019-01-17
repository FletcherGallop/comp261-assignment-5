package test.comp261.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.HuffmanCoding;
import nz.comp261.assignment5.KMP;

class part2Tests {

	@Test
	void test() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);
		
		huff.encode(text);
	}

}
