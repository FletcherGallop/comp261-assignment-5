package nz.comp261.assignment5;
/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	int[] matchTable;
	String pattern;
	String text;

	public KMP(String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
		
		
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		this.matchTable = constructMatchTable(pattern);

		int k = 0; //start of current match in text
		int i = 0; //position of current character in pattern
		int n = text.length();
		int m = pattern.length();
		
		while ( k + i < n) {
			if (pattern.charAt(i) == text.charAt(k+i)) { //match
				i = i + 1;
				if (i == m) {
					return k;
				}
			} else if (this.matchTable[i] == -1) {
				k = k + i + 1;
				i = 0;
			} else {
				k = k + i - this.matchTable[i];
				i = this.matchTable[i];
			}
		}
		
		return -1;
	}
	
	public int[] constructMatchTable(String pattern) {
		//Length of longest prefix
		int[] matchTable = new int[pattern.length()];
		
		matchTable[0] = -1;
		matchTable[1] = 0;
		
		int j = 0;
		int pos = 2;
		int m = pattern.length();
		
		while (pos < m) {
			if (pattern.charAt(pos-1) == pattern.charAt(j)) {
				matchTable[pos] = j + 1;
				pos++;
				j++;
			} else if (j > 0) {
				j = matchTable[j];
			} else {
				matchTable[pos] = 0;
				pos++;
			}
		}
		
//		System.out.println(matchTable.length);
		
		return matchTable;
		
	}
	
	public int bruteForceAlgorithm(String pattern, String text) {
		//basic
		//for k<- to T.length() - S.length()
		//	if T.substring(k, S.length()).equals(S) then return k
		//return -1
		
		//improved 
		//for k <- 0 to n-m
		//	found <- true
		//	for i <- 0 to m-1
		//		if S[i] != T[k+i] then found <- false, break
		//	if found then return k
		//return -1
		
		int m = pattern.length();
		int n = text.length();
		
//		System.out.println(n + " " + m);
		
		for (int i = 0; i <= n-m; i++) {
			boolean found = true;
						
			for (int j = 0; j <= m-1; j++) {
				if (pattern.charAt(j) != text.charAt(i+j)) {
					found = false;
					break;
				}
			}
			
			if (found) {
				return i;
			}
			
		}
		
		
		
		return -1;
		
	}
}
