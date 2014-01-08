/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution.tsp;

import evolution.individuals.ArrayIndividual;

// std:
// 220000 marika
// 154000 opt

/**
 *
 * @author bartekfi
 */
public class TspTools {
    public static boolean IsPermutation(ArrayIndividual ind) {
        for (int i = 0; i < ind.length(); i++) {
            Object genei = ind.get(i);
            for (int j = 0; j < ind.length(); j++) {
                if (j != i && ind.get(j) == genei) {
                    return false;
                }
            }
        }
        return true;
    }
}
