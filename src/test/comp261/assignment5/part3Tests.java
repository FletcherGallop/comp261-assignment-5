package test.comp261.assignment5;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.HuffmanCoding;
import nz.comp261.assignment5.LempelZiv;

class part3Tests {

	@Test
	void test1() {
		String text = "The cat jumped over the rat.";
		LempelZiv lz = new LempelZiv();
		
		String lzCompressed = lz.compress(text);
		
//		System.out.println(lzCompressed);
	}
	
	@Test
	void test2() {
		String text = "|[0|0|T]|[0|0|h]|[0|0|e]|[0|0| ]|[0|0|c]|[0|0|a]|[0|0|t]|[4|1|j]|[0|0|u]|[0|0|m]|[0|0|p]|[10|1|d]|[11|1|o]|[0|0|v]|[15|1|r]|[16|1|t]|[20|3|r]|[25|0|a]|[26|0|t]|[0|0|.]";
		LempelZiv lz = new LempelZiv();
		
		assertTrue(lz.decompress(text).compareTo("The cat jumped over the rat.") == 0);
		
	}

}
