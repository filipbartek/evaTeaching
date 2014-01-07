package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.ArrayIndividual;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Martin Pilat, Filip Bartek
 */
public class EdgeRecombinationXOver implements Operator {

    double xOverProb = 0;

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public EdgeRecombinationXOver(double prob) {
        xOverProb = prob;
    }


    public void operate(Population parents, Population offspring) {

        int size = parents.getPopulationSize();

        for (int i = 0; i < size / 2; i++) {
            ArrayIndividual[] p = new ArrayIndividual[2];
            p[0] = (ArrayIndividual) parents.get(2*i);
            p[1] = (ArrayIndividual) parents.get(2*i + 1);

            assert(p[0].length() == p[1].length());
            int length = p[0].length();

            // datova struktura: pole mnozin celych cisel
            Map<Object, Set<Object>> edges = new Hashtable<Object, Set<Object>>();

            for (int pi = 0; pi < 2; pi++) {
                Object elemPrev;
                Object elemCur = p[pi].get(length - 1); // last element
                Object elemNext = p[pi].get(0); // first element
                for (int elemi = 0; elemi < length; elemi++) {
                    elemPrev = elemCur;
                    elemCur = elemNext;
                    if (elemi + 1 > length) {
                        elemNext = p[pi].get(0); // wrap around
                    } else {
                        elemNext = p[pi].get(elemi + 1);
                    }
                    if (edges.get(elemCur) == null) {
                        edges.put(elemCur, new HashSet<Object>());
                    }
                    edges.get(elemCur).add(elemPrev);
                    edges.get(elemCur).add(elemNext);
                }
            }

            ArrayIndividual o = (ArrayIndividual) p[0].clone(); // clone parent to respect class

            Object elemCur = p[0].get(0);
            for (int elemi = 0; elemi < length; elemi++) {
                o.set(elemi, elemCur);

                // find minimal degree
                int sizeMin = Integer.MAX_VALUE;
                for (Object elemNext : edges.get(elemCur)) {
                    int sizeCur = edges.get(elemNext).size();
                    if (sizeCur < sizeMin) {
                        sizeMin = sizeCur;
                    }
                }

                // get elems with minimal degree
                Set<Object> candidates = new HashSet<Object>();
                for (Object elemNext : edges.get(elemCur)) {
                    int sizeCur = edges.get(elemNext).size();
                    if (sizeCur == sizeMin) {
                        candidates.add(elemNext);
                    }
                }
            }

            offspring.add(o);
            offspring.add(o);
        }

    }


}
