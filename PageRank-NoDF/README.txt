This code for page rank is based on the concept explained at: http://www.math.cornell.edu/~mec/Winter2009/RalucaRemus/Lecture3/lecture3.html

Input:
This code takes the adjancency matrix as the input. This input document is in the sparse matrix format. i.e. each row 
denotes information for a cell. e.g. a row of the form "i j k" denotes that the matrix contains a value k at the row i 
column j.  The value k=1 denotes that there is a link from document i to document j. Example input file- input.txt and
input1.txt are included. input1.txt is the matrix of graph used as an example in above link explaining the concept.

Output:
The page rank scores for the top 100 pages/nodes. If there are less than 100 documents then all the documents according
to the rank score are displayed. 

Classes created to implement pageRank:

1) globals- This file contains all the shared variables declarations.

2) PageRank- This is the class which contains main. In this I have created initial graph matrix A and matrix v.
	     Since the data provided i.e. the input graph can be very large, I have stored the graph in the form 
	     of HashMap of HashMap. In this nested map each row has a corresponding column map with its value.
	     Calculations of graph A does not consider the damping factor (1- a).

3) MatrixMultiplication- I have written this class for multiplying A with v. As my matrix is in the form of map
			 therefore I was not able to use any inbuilt matrix multiplication library. Using existing
			 libraries will create huge sparse matrix for huge data and resulting in memory shortage.
			 Thus, this technique of using HashMap is more memory efficient.

4) Convergence - This class is written to check the condition of convergence where results generating 2 similar 
		 matrices is produced. Final result is printed in this class. As the result has to be printed in
		 sorted order of rank scores, I have used a HashMap to store the score for each page and then sorted
		 it using Comparator function on value.

5) ValueComparator- This is used to sort the hashMap based on values.


To run this project:
1) Paste folder PageRank in your eclipse workspace.
2) Import the existing project "PageRank" in eclipse.
3) Open PageRank project and open the main file PageRank.java
4) Run the project.

If you want to change the input file:
1) Paste your .txt in PageRank folder.
2) Change the variable filename in globals.java to your input file name.



		