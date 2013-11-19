package evolution.selectors;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;

/**
 * @author Martin Pilat, Filip Bartek
 */
public class SusSelector implements Selector {

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public void select(int howMany, Population from, Population to) {

        double fitnessSum = 0.0;

        for (int i = 0; i < from.getPopulationSize(); i++) {
            fitnessSum += from.get(i).getFitnessValue();
        }

        double[] fitnesses = new double[from.getPopulationSize()];

        for (int i = 0; i < fitnesses.length; i++) {
            fitnesses[i] = from.get(i).getFitnessValue();
        }

        double step = fitnessSum / howMany;

        double ball = rng.nextDouble() * step;
        double sum = 0;

        for (int j = 0; j < fitnesses.length; j++) {
            sum += fitnesses[j];
            while (sum > ball) {
                to.add((Individual) from.get(j).clone());
                from.get(j).setLogNotes(from.get(j).getLogNotes() + " " + this.getClass().getCanonicalName());
                ball += step;
            }
        }

        assert(to.getPopulationSize() == howMany);

    }

}
