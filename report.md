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

**Question 3**: Consider the Huffman coding of war_and_peace.txt, taisho.txt, and pi.txt. Which of these achieves the best compression, i.e. the best reduction in size? What makes some of the encodings better than others?

**Question 4**: The Lempel-Ziv algorithm has a parameter: the size of the sliding window. On a text of your choice, how does changing the window size affect the quality of the compression?

**Question 5**: What happens if you Huffman encode War and Peace before applying Lempel- Ziv compression to it? Do you get a smaller file size (in characters) overall?
