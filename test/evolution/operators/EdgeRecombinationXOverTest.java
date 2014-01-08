/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution.operators;

import evolution.Population;
import evolution.individuals.ArrayIndividual;
import evolution.individuals.IntegerIndividual;
import evolution.tsp.TspTools;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bartekfi
 */
public class EdgeRecombinationXOverTest {

    public EdgeRecombinationXOverTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of operate method, of class EdgeRecombinationXOver.
     */
    @Test
    public void testOperate() {
        System.out.println("operate");
        int popSize = 2;
        double xOverProb = 1.0;
        int individualLength = 9;
        // http://eva.martinpilat.com/mod/resource/view.php?id=54 : 100
        IntegerIndividual sampleIndividual = new IntegerIndividual(individualLength, 1, 10);
        for (int genei = 0; genei < individualLength; genei++) {
            sampleIndividual.set(genei, genei + 1);
        }
        Population parents = new Population();
        parents.setSampleIndividual(sampleIndividual);
        parents.setPopulationSize(popSize);
        parents.add(sampleIndividual);
        parents.add(sampleIndividual);
        Population offspring = new Population();
        //offspring.setSampleIndividual(sampleIndividual);
        //offspring.setPopulationSize(popSize);
        EdgeRecombinationXOver instance = new EdgeRecombinationXOver(xOverProb);
        instance.operate(parents, offspring);
        assertEquals(popSize, offspring.getPopulationSize());
        for (int i = 0; i < offspring.getPopulationSize(); i++) {
            ArrayIndividual ind = (ArrayIndividual) offspring.get(i);
            assertTrue(TspTools.IsPermutation(ind));
        }
    }

}