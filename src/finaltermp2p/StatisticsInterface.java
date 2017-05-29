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
public interface StatisticsInterface {
    int number_of_nodes();
    int number_of_edges();
    int number_of_mutual_connections();
    int number_of_nodes_with_zero_indegree();
    int number_of_nodes_with_zero_outdegree();
}
