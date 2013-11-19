package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.ArrayIndividual;

/**
 * @author Martin Pilat, Filip Bartek
 *
 * Each gene is swapped with probability geneProb.
 */
public class UniformXOver implements Operator {

    double xOverProb = 0;
    double geneProb = 0.5;

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public UniformXOver(double prob, double geneProb) {
        xOverProb = prob;
        this.geneProb = geneProb;
    }


    public void operate(Population parents, Population offspring) {

        int size = parents.getPopulationSize();

        for (int i = 0; i < size / 2; i++) {
            ArrayIndividual p1 = (ArrayIndividual) parents.get(2*i);
            ArrayIndividual p2 = (ArrayIndividual) parents.get(2*i + 1);

            ArrayIndividual o1 = (ArrayIndividual) p1.clone();
            ArrayIndividual o2 = (ArrayIndividual) p2.clone();

            if (rng.nextDouble() < xOverProb) {

                for (int j = 0; j < p1.length(); j++) {
                    if (rng.nextDouble() < geneProb) {
                        Object tmp = o1.get(j);
                        o1.set(j, o2.get(j));
                        o2.set(j, tmp);
                    }
                }

            }

            offspring.add(o1);
            offspring.add(o2);
        }

    }


}
