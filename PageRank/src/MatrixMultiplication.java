/*I have written this class for multiplying M with v. As my matrix is in the form of map
therefore I was not able to use any inbuilt matrix multiplication library. Using existing
libraries will create huge sparse matrix for huge data and resulting in memory shortage.
Thus, this technique of using HashMap is more memory efficient.*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MatrixMultiplication {
	
	public static void Multiply(HashMap<Integer, HashMap<Integer, Double>> M,ArrayList<Double> M_v){
		
		ArrayList<Double> prod=new ArrayList<Double>();
		double subProd;
		Iterator<Integer> i = M.keySet().iterator();
		Integer jkey=null;
		Integer key=null;
		
		for(int index=1;index<=M_v.size();index++){	
			subProd=0;
			jkey=null;
			
			if(key==null)
				key=i.next();
			
			if(key==index){
				HashMap<Integer, Double> temp=M.get(key);
				Iterator<Integer> j = temp.keySet().iterator();
					
				int cindex=1;					
					
				while(cindex <= M_v.size()){
					if(jkey==null)
						jkey=j.next();					
						
					if(jkey==cindex){
						subProd+=(temp.get(jkey) * M_v.get(cindex-1));
						if(j.hasNext())
							jkey=j.next();
					}							
					else
						subProd+=globals.nzConst * M_v.get(cindex-1);
								
					cindex++;						
						}
				if(i.hasNext())
					key=i.next();
				}
				else{
					for(int cindex=1;cindex<M_v.size();cindex++){
						subProd+=globals.nzConst * M_v.get(cindex);
						}					
					}
			prod.add(subProd);
			
			}
		
		//prod now contains the product of M and M_v
		/*for(double i1:prod)
			System.out.println(i1);*/
		
		
		Convergence.converge(M, prod);
		
		}

}
