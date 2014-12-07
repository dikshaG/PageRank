/*This is the class which contains main. In this I have created initial graph matrix M and matrix v.
Since the data provided i.e. the input graph can be very large, I have stored the graph in the form 
of HashMap of HashMap. In this nested map each row has a corresponding column map with its value.
Calculations of graph M considers the damping factor as 0.85 i.e. (1- a).*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class PageRank {


	public static void storeSparseMatrix(String filename) {

		//Reading the transpose of input graph and storing it in nested map
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String cur;

			HashMap<Integer, HashMap<Integer, Double>> outer = new HashMap<Integer, HashMap<Integer, Double>>();

			while ((cur = br.readLine()) != null) {
				String[] mat = cur.split(" ");
				HashMap<Integer, Double> temp;
				
				if (outer.containsKey(Integer.parseInt(mat[1]))) {
					temp = outer.get(Integer.parseInt(mat[1]));
				} else {
					temp = new HashMap<Integer, Double>();
					outer.put(Integer.parseInt(mat[1]), temp);
				}

				temp.put(Integer.parseInt(mat[0]), Double.parseDouble(mat[2]));

				}
			
			br.close();
			fr.close();
			
			globals.rows=outer.size();
			globals.nzConst=(1-globals.dampFactor)/globals.rows;
		
			//Calculation for M
			for(int i:outer.keySet()){
				HashMap<Integer, Double> temp=outer.get(i);
				
				double value;
				
				for(int j:temp.keySet()){
					value=temp.get(j);
					value/=temp.size();
					value*=globals.dampFactor;
					value+=globals.nzConst;
					temp.put(j, value);					
				}
			}
			
		//Storing the transpose of M in file
			File file = new File("intermediate.txt");
				 
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				PrintWriter pw = new PrintWriter(fw);
				
				for(int i:outer.keySet()){
					HashMap<Integer, Double> temp=outer.get(i);
					
					for(int j:temp.keySet()){
						pw.println(j+" "+i+" "+temp.get(j));
					}
				}
								
				pw.close();
	
				// Creating the map of graph based on intermediate file after all the calculations.
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				cur=null;

				outer = new HashMap<Integer, HashMap<Integer, Double>>();

				while ((cur = br.readLine()) != null) {
					String[] mat = cur.split(" ");
					HashMap<Integer, Double> temp;
					
					if (outer.containsKey(Integer.parseInt(mat[0]))) {
						temp = outer.get(Integer.parseInt(mat[0]));
					} else {
						temp = new HashMap<Integer, Double>();
						outer.put(Integer.parseInt(mat[0]), temp);
					}

					temp.put(Integer.parseInt(mat[1]), Double.parseDouble(mat[2]));

					}
				
				br.close();
				fr.close();
				
				/*for(int i:outer.keySet()){
					HashMap<Integer, Double> temp=outer.get(i);
					
					for(int j:temp.keySet()){
						System.out.println(i+" "+j+" "+temp.get(j));
					}
				}*/
			
			ArrayList<Double> v=new ArrayList<Double>();
						
			for(int i=0;i<globals.rows;i++)
				v.add((double)(1.0/(double)globals.rows));			
			 
			 MatrixMultiplication.Multiply(outer, v);
			
			

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	


	public static void main(String args[]) {
		storeSparseMatrix(globals.filename);
	}

}