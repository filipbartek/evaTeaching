package evolution.sga;

import evolution.FitnessFunction;
import evolution.individuals.BooleanIndividual;
import evolution.individuals.Individual;

public class FitnessFunctionAlternate implements FitnessFunction {

    public double evaluate(Individual ind) {

        BooleanIndividual bi = (BooleanIndividual) ind;
        boolean[] genes = bi.toBooleanArray();

        int fitness = 0;

        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == ((i % 2) == 1))
                fitness += 1;
        }

        double fitnessDouble = fitness;

        ind.setObjectiveValue(fitnessDouble); //nastavuje hodnotu optimalizovaneho kriteria, nemusi se (obecne) rovnat primo fitness

        return fitnessDouble;
    }

}
