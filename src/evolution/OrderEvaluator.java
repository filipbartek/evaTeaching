package evolution;

import evolution.individuals.Individual;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class OrderEvaluator implements FitnessEvaluator {

    FitnessFunction fitness;

    public OrderEvaluator(FitnessFunction fitness) {
        this.fitness = fitness;
    }

    public void evaluate(Population pop) {

        NavigableMap<Double, Set<Integer>> heap =
                new TreeMap<Double, Set<Integer>>();

        for (int i = 0; i < pop.getPopulationSize(); i++) {
            double value = fitness.evaluate(pop.get(i));
            Set<Integer> indexes = heap.get(value);
            if (indexes == null) {
                indexes = new HashSet<Integer>();
            }
            indexes.add(i);
            heap.put(value, indexes);
        }

        int j = 1;

        for (Set<Integer> indexes : heap.values()) {
            for (Integer index : indexes) {
                pop.get(index).setFitnessValue(j);
                ++j;
            }
        }

    }

}
