package evolution.selectors;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Filip Bartek
 */
public class FairSelector implements Selector {

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public void select(int howMany, Population from, Population to) {

        assert(to.getPopulationSize() == 0);
        assert(howMany >= 0);

        if (howMany == 0) return;

        double fitnessSum = 0.0;

        // <fitness, <index in `from`>>
        NavigableMap<Double, Set<Integer>> heap = new TreeMap<Double, Set<Integer>>();

        // Fill `heap` with fitness values and indexes
        for (int i = 0; i < from.getPopulationSize(); i++) {
            double fitness = from.get(i).getFitnessValue();
            fitnessSum += fitness;
            Set<Integer> indexes = heap.get(fitness);
            if (indexes == null) {
                indexes = new HashSet<Integer>();
            }
            indexes.add(i);
            heap.put(fitness, indexes);
        }

        double step = fitnessSum / howMany;

        // Pull the `howMany` heaviest individuals from the heap
        int i = 0;
        while (!heap.isEmpty()) {
            Map.Entry<Double, Set<Integer>> bestEntry = heap.pollLastEntry();
            Set<Integer> indexes = bestEntry.getValue();
            for (Integer index : indexes) {
                to.add((Individual) from.get(index).clone());
                ++i;
                if (i >= howMany) break;
            }
            if (i >= howMany) break;
            Double bestWeight = bestEntry.getKey();
            Double newWeight = bestWeight - step;
            if (newWeight > 0) {
                heap.put(newWeight, indexes);
            }
        }

        assert(i == howMany);

        assert(to.getPopulationSize() == howMany);

    }

}
