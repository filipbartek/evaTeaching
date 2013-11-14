package evolution.binPacking;

import evolution.individuals.Individual;

import java.util.Vector;

public class HromadkyFitnessErr extends HromadkyFitness {

    public HromadkyFitnessErr(Vector<Double> weights, int K) {
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

        Double opt = getWeightsSum() / K;
        Double err = 0.0;

        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            if (binWeights[i] < min) {
                min = binWeights[i];
            }
            if (binWeights[i] > max) {
                max = binWeights[i];
            }
            //err = err + Math.abs(opt - binWeights[i]);
            err = err + Math.pow(opt - binWeights[i], 2);
            // TODO: Finish!
        }

        ind.setObjectiveValue(max - min);    // tohle doporucuji zachovat

        return 1 / err;
    }
}
