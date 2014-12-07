/*This class is written to check the condition of convergence where results generating 2 similar 
matrices is produced. Final result is printed in this class. As the result has to be printed in
sorted order of rank scores, I have used a HashMap to store the score for each page and then sorted
it using Comparator function on value.*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Convergence {
	
// To check whether all the elements of the 2 list are equal or not
	public static boolean compareListElements(ArrayList<Double> a,ArrayList<Double> b){
		
		if(a==null && b==null)
			return true;
		
		if(a.size()!=b.size())
			return false;
		
		int i=0,j,k;
		
		while(i<a.size())
		{
			j=(int) (100*a.get(i));
			k=(int) (100*b.get(i));
			
			if(j!=k)
				return false;
			else
				i++;
		}
		
		return true;
	}
	
	static ArrayList<Double> v_Prev=new ArrayList<Double>();
	static int count=1;
	
	public static void converge(HashMap<Integer, HashMap<Integer, Double>> M,ArrayList<Double> M_v){	
		
		if(compareListElements(v_Prev,M_v))
			{
				HashMap<Integer,Double> rank=new HashMap<Integer,Double>();
				int k=0;
				
				for(double i:M_v){
					k++;
					rank.put(k, i);
				}
				
				
			ValueComparator bvc = new ValueComparator(rank);
			TreeMap<Integer, Double> sorted_map = new TreeMap<Integer, Double>(bvc);
			sorted_map.putAll(rank);
			
			k=0;
			System.out.println("Ranking is:");			
			
					for(Entry<Integer, Double> i : sorted_map.entrySet()){
						if(k<100)
							{							
							System.out.println(i.getKey()+" - "+i.getValue());
							k++;
							}
						else
							break;
				}				
					
			}
			else
			{
				v_Prev=M_v;
				count++;
				MatrixMultiplication.Multiply(M, M_v);
			}
	}


}