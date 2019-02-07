Report

**Question 1**: Write a short summary of the performance you observed using the two search algorithms.

|Test #|Algorithm|Found?|Time(ms)|
|---|:-----:|:---:|---:|
|8a| Brute Force | FOUND | 17 |
|8b| KMP | FOUND | 14 |
|9a| Brute Force | NOT FOUND | 7 |
|9b| KMP | NOT FOUND | 11 |

Initially I thought perhaps I was getting unexpected results. Whilst this was in part due to how I was accessing the Match Table and timing the operations.

The results are now as I'd expected. When the pattern isn't in the text, then the Brute Force algorithm performs better.

**Question 2**: Report the binary tree of codes your algorithm generates, and the final size of War and Peace after Huffman coding.
Text Length: 3258227
Compressed Length: 1848562
Tree: {000=e, 0010=s, 0011=h, 0100=i, 0101=n, 0110000=k, 01100010000=C, 01100010001=E, 0110001001=W, 011000101=P, 011000110=A, 011000111000=), 011000111001=V, 01100011101=O, 0110001111=S, 011001=y, 01101=l, 0111=o, 1000=a, 100100=g, 100101000=T, 100101001=-, 1001010100=?, 1001010101=M, 100101011=I, 1001011=v, 100110=f, 100111=w, 1010=t, 10110=d, 101110=m, 101111=c, 110= , 1110000000=N, 1110000001=B, 1110000010=H, 1110000011000000=﾿, 1110000011000001=ﾽ, 1110000011000010=￯, 1110000011000011000=/, 1110000011000011001==, 111000001100001101=4, 11100000110000111=X, 1110000011000100=6, 1110000011000101=3, 111000001100011=Z, 11100000110010=U, 111000001100110=2, 11100000110011100=9, 11100000110011101=7, 11100000110011110=Q, 11100000110011111=5, 111000001101=:, 11100000111=F, 111000010=', 1110000110=x, 1110000111=!, 1110001=., 111001=
, 111010=
, 111011=u, 11110=r, 1111100=b, 11111010=", 11111011000=D, 11111011001=j, 11111011010=R, 11111011011000=1, 11111011011001=*, 111110110110100=0, 111110110110101=8, 11111011011011=J, 111110110111=;, 11111011100=z, 11111011101=q, 111110111100=K, 111110111101=G, 111110111110=Y, 1111101111110=L, 1111101111111=(, 1111110=p, 1111111=,}



**Question 3**: Consider the Huffman coding of war_and_peace.txt, taisho.txt, and pi.txt. 
Which of these achieves the best compression, i.e. the best reduction in size? 
What makes some of the encodings better than others?

War and Peace Text Length: 3258246
Compressed Length: 1848584
Taisho Text Length: 3649944
Compressed Length: 152917
Pi Text Length: 1010003
Compressed Length: 443637

My implementation of Huffman coding included a step to encode the text from a String to a byte array, this decreased the size of the file, rather than expanding is as happened when I hadn't included the byte array encoding.


**Question 4**: The Lempel-Ziv algorithm has a parameter: the size of the sliding window.
 On a text of your choice, how does changing the window size affect the quality of the compression?

**Question 5**: What happens if you Huffman encode War and Peace before applying Lempel- Ziv compression to it?
 Do you get a smaller file size (in characters) overall?
