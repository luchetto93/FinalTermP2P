/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltermp2p;

/**
 *
 * @author luca
 */
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.NodeIterator;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;
import it.unimi.dsi.big.webgraph.algo.StronglyConnectedComponents;
import it.unimi.dsi.logging.ProgressLogger;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;

/**
 *
 * @author luca
 */
 public class ComputeStatistics implements StatisticsInterface{
    private int number_nodes;
    private int number_edges;
    private ImmutableGraph graph;
    private boolean is_scanned;
    private int number_of_components;
    ComputeStatistics(ImmutableGraph gr)
   {
    try
    {
      number_nodes = gr.numNodes();
      number_edges = (int)gr.numArcs();
      graph = gr;
      is_scanned = false;
      number_of_components = -1;
    }catch(Exception e)
    {
       System.err.println("The input graph instance is not valid");
    }
   }
    
    public int number_of_nodes()
    {
       return number_nodes;
    }
    public int number_of_edges()
    {
       return number_edges;
    }
    public int number_of_nodes_with_zero_outdegree()
    {
        // PRE:  graph != NULL 
        // POST: return the number of nodes in the graph with out degree equal to 0
         int count = 0;
         int out_degree = 0;
         try
         {
            
            NodeIterator nodeIt = graph.nodeIterator();
            for(int i=0;i<number_nodes;i++)
            {
             nodeIt.nextInt();
             out_degree = nodeIt.outdegree();
             if(out_degree == 0)
               count++;
            }
         }catch(Exception e)
         {
           System.err.println("The graph instance is not valid");
         }
         is_scanned = true;
         return count;
    }
    private int count_false_in_array(int array[])
    {
       int count = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]==0)
                count++;
        }
       return count;
    }
    public int number_of_nodes_with_zero_indegree()
       
    {
      // PRE:  graph != NULL && and it has been previously scanned
      // POST: return the number of nodes with zero indegree
      int out_degree = 0;
      int appo_val = 0;
      int appo_vector []= new int [number_nodes];
      int i = 0;
      int j = 0;
      try
      {
         NodeIterator nodeIt = graph.nodeIterator();
     
         for(i=0;i<number_nodes;i++)
         {
            nodeIt.nextInt();
            out_degree = nodeIt.outdegree();
            int adjacent[] = nodeIt.successorArray();
            for(j=0;j<out_degree;j++)
            {
               appo_vector[adjacent[j]]++;
            }
         }
      }
      catch(Exception e)
      {
      
      }
      is_scanned = true;
      return count_false_in_array(appo_vector);
    }
    private void scann_graph()
    {
       NodeIterator nodI = graph.nodeIterator();
        for (int i = 0; i < number_nodes; i++) {
            nodI.nextInt();
        }
    }
    private int check_if_is_mutual(int node_id,int out_degree,int successor[],int n_bidirectional)
    {
      
      boolean bidirectional = false;
      int successor_out_degree;  
         for (int i = 0; i < out_degree; i++) {
             int successors_of_successor[] = graph.successorArray(successor[i]);
             successor_out_degree = graph.outdegree(successor[i]);
             for (int j = 0; j < successor_out_degree; j++) {
                 if(successors_of_successor[j]==node_id)
                 {
                   n_bidirectional++;
                   break; /*item has been found, the search is stopped*/
                 }
             }
        }
      return n_bidirectional;
    }
    public int number_of_mutual_connections()
    {
       // PRE:  graph != null && it must be alreay scanned with a proper iterator
       // POST: It returns the number of mutual connections (bidirectional links)
     
      /*
         Idea to improve could be to use a MapTree <K,V>,
         each pair <K,V> should represent an edge.
        You scan the graph and insert the key <K,V> in the Map,
        the check for mutual connection is performed only if K < V
      */  
       int n_id = 0;
       int ch_id =0;
       boolean mutual_connection = false;
       int out_degree = 0;
       int count = 0;
       NodeIterator nodI = graph.nodeIterator();
       TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();

        for (int i = 0; i < this.number_nodes; i++) {
            n_id = nodI.nextInt();
            out_degree = nodI.outdegree();
            int adjacent []= nodI.successorArray();
            for (int j = 0; j < out_degree; j++) {
                ch_id = adjacent[j];
                tmap.put(n_id, ch_id);
                if (n_id<ch_id) {
                    if(tmap.remove(ch_id, n_id))
                        count++;
                }
            }
        }
    return count;
    }
    
    public ConnectedComponents compute_connected_component(int n_threads)
    {
      ProgressLogger pl = new ProgressLogger();
      ConnectedComponents connected = ConnectedComponents.compute(graph, n_threads, pl);
      return connected;
    }
    public int [] sorted_connected_components(int n_threads)
    {
      // PRE:  graph != NULL && graph is indirected
      /* POST: it returns the size of each connected components 
         i.e. connected_components[0]= the size of connected_component 0
              connected_components[1]= the size of connected_component 1
      */
      ConnectedComponents connected = compute_connected_component(n_threads);
      int connected_components[] = connected.computeSizes();
      connected.sortBySize(connected_components);
      number_of_components = connected.numberOfComponents;
      return connected_components;
    }
    public int get_number_of_connected_components(int n_thread)
    {
       int result = 0;
       ConnectedComponents connected;
       if(number_of_components == -1)
       {
           connected = compute_connected_component(n_thread);
           result = connected.numberOfComponents;
       }
        else
           result = number_of_components;
       return result;
    }
    public ImmutableGraph largest_connected_components(int n_thread)
    {
     // PRE:  graph != NULL
     // POST: It returns the largest connected component as an ImmutableGraph
      ProgressLogger pl = new ProgressLogger();
      ImmutableGraph largest_connected_component = ConnectedComponents.getLargestComponent(graph, n_thread, pl);
      return largest_connected_component;
    }
    
    public int [] compute_indegree_per_node()
               {
      // PRE:  graph != NULL
      /* POST: it computes the indegree of each node
               i.e. indegree_per_node[i] = the indegree of node i
      */
      int indegree_per_node []= new int [this.number_nodes];
      int out_degree = 0;
      if(!is_scanned)
          scann_graph();
      else
      {
         NodeIterator nodIt = graph.nodeIterator();
          for (int i = 0; i < number_nodes; i++) {
             nodIt.nextInt();
             int successors[] = nodIt.successorArray();
             out_degree = nodIt.outdegree();
              for (int j = 0; j < out_degree; j++) {
                  indegree_per_node[successors[j]]++;
              }
          }
      
      }
      return indegree_per_node;
    }
    
    
    
 }
