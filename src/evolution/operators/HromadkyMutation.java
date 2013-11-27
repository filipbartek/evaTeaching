package evolution.operators;

import evolution.Population;
import evolution.RandomNumberGenerator;
import evolution.individuals.Individual;
import evolution.individuals.IntegerIndividual;
import java.util.Vector;

/**
 * @author Martin Pilat, Filip Bartek
 */
public class HromadkyMutation implements Operator {

    double mutationProbability;
    double geneChangeProbability;
    Vector<Double> weights;
    int K;

    RandomNumberGenerator rng = RandomNumberGenerator.getInstance();

    public HromadkyMutation(double mutationProbability, double geneChangeProbability, Vector<Double> weights, int K) {
        this.mutationProbability = mutationProbability;
        this.geneChangeProbability = geneChangeProbability;
        this.weights = weights;
        this.K = K;
    }

    private double[] getBinWeights(Individual ind) {
        double[] binWeights = new double[K];
        int[] bins = ((IntegerIndividual) ind).toIntArray();
        for (int i = 0; i < bins.length; i++) {
            binWeights[bins[i]] += weights.get(i);
        }
        return binWeights;
    }

    public void operate(Population parents, Population offspring) {

        int size = parents.getPopulationSize();

        for (int i = 0; i < size; i++) {

            IntegerIndividual p1 = (IntegerIndividual) parents.get(i);
            IntegerIndividual o1 = (IntegerIndividual) p1.clone();

            if (rng.nextDouble() < mutationProbability) {
                double[] binWeights = getBinWeights(o1);

                double minWeight = Double.MAX_VALUE;
                int minBin = o1.getMin();
                double maxWeight = Double.MIN_VALUE;
                int maxBin = o1.getMin();
                for (int bin = 0; bin < K; bin++) {
                    if (binWeights[bin] < minWeight) {
                        minWeight = binWeights[bin];
                        minBin = bin;
                    }
                    if (binWeights[bin] > maxWeight) {
                        maxWeight = binWeights[bin];
                        maxBin = bin;
                    }
                }

                double optSwap = (maxWeight - minWeight) / 2;

                for (int j = 0; j < o1.length(); j++) {
                    int bin = (Integer)o1.get(j);
                    if (bin == maxBin) {
                        double weight = weights.get(j);
                        double prob = (optSwap / weight) * geneChangeProbability * K / o1.length();
                        if (rng.nextDouble() < prob) {
                            o1.set(j, minBin);
                        }
                    }
                }
            }

            offspring.add(o1);
        }
    }

}
