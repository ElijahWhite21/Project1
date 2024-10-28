import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatsLibraryTester {
    public static void main(String[] args) {
        // ArrayList holding the set values
        ArrayList<Integer> listOfnumbers = new ArrayList<Integer>();
        listOfnumbers.add(10);
        listOfnumbers.add(20);
        listOfnumbers.add(30);
        listOfnumbers.add(40);
        listOfnumbers.add(50);

        // Mean Tester
        StatsLibrary mean = new StatsLibrary();
        System.out.println("Mean of value: " + mean.computeMean(listOfnumbers));

        // Median Tester
        StatsLibrary median = new StatsLibrary();
        System.out.println("Median of value: " + median.computeMedian(listOfnumbers));

        // Mode Tester
        StatsLibrary mode = new StatsLibrary();
        System.out.println("Mode of value: " + mode.computeMode(listOfnumbers));

        // Standard Deviation Tester
        StatsLibrary standardDeviationLibrary = new StatsLibrary();
        System.out.println(
                "Standard Deviation of value: " + standardDeviationLibrary.computeStandardDeviation(listOfnumbers));

        // Variance Tester
        StatsLibrary variance = new StatsLibrary();
        System.out.println("Variance of value: " + variance.computeVariance(listOfnumbers));

        // Deinition 2.6 Tester Testing Axioms 1
        double probabilityA = 0.3;
        System.out.println(
                "2.6 : Is " + probabilityA + " a valid probability? " + StatsLibrary.isValidProbability(probabilityA));

        // Test Axioms 2
        double probabilityB = 1.0;
        System.out.println(
                "2.6 : Is " + probabilityB + " the probability of the sample space? "
                        + StatsLibrary.isConditionalProbability(probabilityB));

        // Test Axioms 3 the event probability
        ArrayList<Double> eventProbabilities = new ArrayList<Double>();
        eventProbabilities.add(0.2);
        eventProbabilities.add(0.3);
        eventProbabilities.add(0.5);

        ArrayList<Boolean> eventResults = new ArrayList<Boolean>();
        eventResults.add(true);
        eventResults.add(true);
        eventResults.add(false);

        boolean areExclusive = StatsLibrary.areMutuallyExclusive(eventProbabilities, eventResults);
        System.out.println("2.6: Are the events mutually exclusive? " + areExclusive);

        if (areExclusive) {
            double unionProbability = StatsLibrary.calculateUnionOfMutuallyExclusiveEvents(eventProbabilities);
            System.out.println("2.6: Probability of the union of mutually exclusive events: " + unionProbability);
        } else {
            System.out.println(
                    "2.6: Thefore the events aren't mutually exclusive!!!");
        }

        // Definition 2.9 Tester Conditional Probability
        double pAandB = 0.2;
        double pBConditional = 0.5;

        try {
            double conditionalProbability = StatsLibrary.calculateConditionalProbability(pAandB, pBConditional);
            System.out.println("2.9: Conditional Probability of P(A|B): " + conditionalProbability);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Definition 2.10 Tester Example 1 expected output is true
        double probabilityA2 = 0.5;
        double probabilityB2 = 0.5;
        double pAandB2 = 0.25;

        boolean areIndependent = StatsLibrary.areIndependent(probabilityA2, probabilityB2, pAandB2);
        System.out.println("2.10 (Example 1 Should be True) : Are A and B independent? " + areIndependent);

        // Definition 2.10 Tetser Example 2 expected output is false
        double probabilityA3 = 0.5;
        double probabilityB3 = 0.4;
        double pAandB3 = 0.1;

        boolean areIndependent2 = StatsLibrary.areIndependent(probabilityA3, probabilityB3, pAandB3);
        System.out.println("2.10 (Example 2 Should be False) : Are A and B independent? " + areIndependent2);

        // Definition 2.11 Tester Example 1 expected output is true
        Set<Integer> universe = new HashSet<>();
        for (int i = 1; i <= 5; i++) {
            universe.add(i);
        }

        // Define subsets B1, B2, B3
        Set<Integer> B1 = new HashSet<>();
        B1.add(1);
        B1.add(2);

        Set<Integer> B2 = new HashSet<>();
        B2.add(3);

        Set<Integer> B3 = new HashSet<>();
        B3.add(4);
        B3.add(5);

        // Adding the subsets
        ArrayList<Set<Integer>> sets = new ArrayList<>();
        sets.add(B1);
        sets.add(B2);
        sets.add(B3);

        // Checks if part of sets for partition of s
        boolean isPartition = StatsLibrary.isPartition(sets, universe);
        System.out.println("2.11 (True Example): Do the sets form a partition of S? " + isPartition);

        // Definition 2.11 Tester Example 2 expected output is false
        Set<Integer> B1False = new HashSet<>();
        B1False.add(1);
        B1False.add(2);
        B1False.add(3);

        Set<Integer> B2False = new HashSet<>();
        B2False.add(3);
        B2False.add(4);

        Set<Integer> B3False = new HashSet<>();
        B3False.add(5);

        ArrayList<Set<Integer>> setsFalse = new ArrayList<>();
        setsFalse.add(B1False);
        setsFalse.add(B2False);
        setsFalse.add(B3False);

        boolean isPartitionFalse = StatsLibrary.isPartition(setsFalse, universe);
        System.out.println("2.11 (False Example): Do the sets form a partition of S? " + isPartitionFalse);

        // Definition 3.4 Tester
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(1.0);
        values.add(2.0);
        values.add(3.0);

        ArrayList<Double> probabilities = new ArrayList<Double>();
        probabilities.add(0.2);
        probabilities.add(0.5);
        probabilities.add(0.3);

        double expectedValue = StatsLibrary.calculateExpectedValue(values, probabilities);
        System.out.println("3.4: Expected Value of Y: " + expectedValue);

        // Definition 3.5 Tester
        ArrayList<Double> valuesVariance = new ArrayList<Double>();
        valuesVariance.add(1.0);
        valuesVariance.add(2.0);
        valuesVariance.add(3.0);

        ArrayList<Double> probabilitiesVariance = new ArrayList<Double>();
        probabilitiesVariance.add(0.2);
        probabilitiesVariance.add(0.5);
        probabilitiesVariance.add(0.3);

        double expectedValueVariance = StatsLibrary.calculateExpectedValue(valuesVariance, probabilitiesVariance);
        System.out.println("Expected Value E(Y): " + expectedValueVariance);

        double varianceResult = StatsLibrary.calculateVariance(valuesVariance, probabilitiesVariance);
        System.out.println("Variance V(Y): " + varianceResult);

        double standardDeviationResult = StatsLibrary.calculateStandardDeviation(valuesVariance, probabilitiesVariance);
        System.out.println("Standard Deviation of Y: " + standardDeviationResult);

        // Definition 3.7 Tester
        int n = 5;
        int y = 3;
        double p = 0.6;

        double binomialProbability = StatsLibrary.calculateBinomialProbability(n, y, p);
        System.out.println("3.7: Binomial Probability: " + binomialProbability);

        // Defintion 3.8
        int yGeometric = 3;
        double pGeometric = 0.5;

        double geometricProbability = StatsLibrary.calculateGeometricProbability(yGeometric, pGeometric);
        System.out.println("3.8: Geometric Probability P(Y = " + yGeometric + "): " + geometricProbability);
    }
}
