/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltermp2p;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.NodeIterator;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;
import it.unimi.dsi.big.webgraph.algo.StronglyConnectedComponents;
import it.unimi.dsi.webgraph.BVGraph;
import java.util.TreeMap;
/**
 *
 * @author luca
 */
public class FinalTermP2P {

    /**
     * @param args the command line arguments
     */
    public static int binary_search(int a[],int key,int low,int high)
    {
        int mid;
        while (low <= high) {
        mid = (low + high) / 2;
        if (a[mid] > key) {
            high = mid - 1;
        } else if (a[mid] < key) {
            low = mid + 1;
        } else {
            return mid;
        }
    }
        return -1;
    }
    public static int min(int a, int b)
    {
       if(a>b)
           return b;
       else
       {
          if(a<b)
              return a;
          else
              return a-1; // they are equal
       }
    }
    public static void doubling_search(int a[],int n,int b[],int m)
    {
      int i = 0;
      int new_i = 0;
      int k = 0;
      int power = (int)Math.pow(2, k);
        for (int j = 0; j < m; j++) {
            k=0;
            while((i+power)<n && b[j]>a[i+power])
            {    
             k++;
             power = (int)Math.pow(2, k);
            }
            new_i = binary_search(a,b[j],i+1,min(power,n));
            if(new_i!=-1)
                System.out.println(b[j]);
    }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("PUSH");
        TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
        tmap.put(4, 5);
        tmap.put(6, 5);
        tmap.put(9, 5);
        tmap.put(5,4);
        tmap.put(8, 5);
        tmap.put(7, 5);
        System.out.println(tmap.size());
        int array[] = {1,4,6,7,9,12,15,18,21,48};
        int array1[] = {3,4,5,8,12,15,48};
        //doubling_search(array,array.length,array1,array1.length);
        //int v = binary_search(array,21,0,array.length-1);
        //System.out.println(v);
        
        try{
         int out_d;
         
         BVGraph graph = BVGraph.load("/home/luca/NetBeansProjects/JavaApplication11/bvtime0");
         ComputeStatistics stat = new ComputeStatistics(graph);
         stat.number_of_mutual_connections();
      //   System.out.println(n);
        }catch(Exception e)
        {
        e.printStackTrace();
        }

    }
    
}
