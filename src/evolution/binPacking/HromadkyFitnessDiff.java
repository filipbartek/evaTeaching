package evolution.binPacking;

import evolution.individuals.Individual;

import java.util.Vector;

public class HromadkyFitnessDiff extends HromadkyFitness {

    public HromadkyFitnessDiff(Vector<Double> weights, int K) {
        super(weights, K);
    }

    private Double getWeightsSum() {
        Double sum = 0.0;
        for (int i = 0; i < weights.size(); i++) {
            sum = sum + weights.get(i);
        }
        return sum;
    }

    @Override
    public double evaluate(Individual ind) {

        int[] binWeights = getBinWeights(ind);

        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            if (binWeights[i] < min) {
                min = binWeights[i];
            }
            if (binWeights[i] > max) {
                max = binWeights[i];
            }
        }

        ind.setObjectiveValue(max - min);    // tohle doporucuji zachovat

        //sem muzete vlozit vlastni vypocet fitness, muzete samozrejme vyuzit spocitane hmotnosti hromadek

        return getWeightsSum() - (max - min);
    }
}
