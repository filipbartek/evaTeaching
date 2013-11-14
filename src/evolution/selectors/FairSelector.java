package evolution.selectors;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Martin Pilat
 */
public class FairSelector implements Selector {

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public void select(int howMany, Population from, Population to) {

        double fitnessSum = 0.0;

        NavigableMap<Double, Integer> intermediate = new TreeMap<Double, Integer>();

        for (int i = 0; i < from.getPopulationSize(); i++) {
            double fitness = from.get(i).getFitnessValue();
            fitnessSum += fitness;
            intermediate.put(fitness, i);
        }

        double step = fitnessSum / howMany;

        for (int i = 0; i < howMany; i++) {

            if (intermediate.isEmpty()) {
                break;
            }
            Map.Entry<Double, Integer> bestEntry = intermediate.pollLastEntry();
            Double bestWeight = bestEntry.getKey();
            Integer j = bestEntry.getValue();
            to.add((Individual) from.get(j).clone());
            Double newWeight = bestWeight - step;
            if (newWeight > 0) {
                intermediate.put(newWeight, j);
            }

        }

        assert(to.getPopulationSize() == howMany);

    }

}
