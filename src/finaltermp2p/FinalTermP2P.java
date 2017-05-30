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
/**
 *
 * @author luca
 */
public class FinalTermP2P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("PUSH");
        try{
         int out_d;
         
         BVGraph graph = BVGraph.load("/home/luca/NetBeansProjects/JavaApplication11/bvtime0");
         ComputeStatistics stat = new ComputeStatistics(graph);
         stat.number_of_mutual_connections();
        }catch(Exception e)
        {}
    }
    
}
