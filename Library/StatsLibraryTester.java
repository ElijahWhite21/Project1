import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatsLibraryTester {
    public static void main(String[] args) {
        // ArrayList holding the set values
        ArrayList<Integer> listOfnumbers = new ArrayList<Integer>(); // List of numbers
        listOfnumbers.add(10);
        listOfnumbers.add(20);
        listOfnumbers.add(30);
        listOfnumbers.add(40);
        listOfnumbers.add(50);

        // Mean Tester
        StatsLibrary mean = new StatsLibrary(); // Mean object
        System.out.println("Mean of value: " + mean.computeMean(listOfnumbers)); // Output of the mean

        // Median Tester
        StatsLibrary median = new StatsLibrary(); // Median object
        System.out.println("Median of value: " + median.computeMedian(listOfnumbers)); // Output of the median

        // Mode Tester
        StatsLibrary mode = new StatsLibrary(); // Mode object
        System.out.println("Mode of value: " + mode.computeMode(listOfnumbers)); // Output of the mode

        // Standard Deviation Tester
        StatsLibrary standardDeviationLibrary = new StatsLibrary(); // Standard Deviation object
        System.out.println(
                "Standard Deviation of value: " + standardDeviationLibrary.computeStandardDeviation(listOfnumbers)); // Output
                                                                                                                     // of
                                                                                                                     // the
                                                                                                                     // standard
                                                                                                                     // deviation

        // Variance Tester
        StatsLibrary variance = new StatsLibrary(); // Variance object
        System.out.println("Variance of value: " + variance.computeVariance(listOfnumbers)); // Output of the variance

        // Deinition 2.6 Tester Testing Axioms 1
        double probabilityA = 0.3; // Probability of A
        System.out.println(
                "2.6 : Is " + probabilityA + " a valid probability? " + StatsLibrary.isValidProbability(probabilityA)); // Output
                                                                                                                        // of
                                                                                                                        // the
                                                                                                                        // probability

        // Test Axioms 2
        double probabilityB = 1.0;
        System.out.println("2.6 : Is " + probabilityB + " the probability of the sample space? "
                + StatsLibrary.isConditionalProbability(probabilityB)); // Output of the probability

        // Test Axioms 3 the event probability
        ArrayList<Double> eventProbabilities = new ArrayList<Double>(); // List of event probabilities
        eventProbabilities.add(0.2); // Adding the event probabilities
        eventProbabilities.add(0.3); // Adding the event probabilities
        eventProbabilities.add(0.5); // Adding the event probabilities

        ArrayList<Boolean> eventResults = new ArrayList<Boolean>(); // List of event results
        eventResults.add(true); // Adding the event results
        eventResults.add(true); // Adding the event results
        eventResults.add(false); // Adding the event results

        boolean areExclusive = StatsLibrary.areMutuallyExclusive(eventProbabilities, eventResults); // Checking if the
                                                                                                    // events are
                                                                                                    // mutually
                                                                                                    // exclusive
        System.out.println("2.6: Are the events mutually exclusive? " + areExclusive); // Output of the events

        if (areExclusive) // If the events are mutually exclusive
        {
            double unionProbability = StatsLibrary.calculateUnionOfMutuallyExclusiveEvents(eventProbabilities); // Calculate
                                                                                                                // the
                                                                                                                // union
                                                                                                                // of
                                                                                                                // the
                                                                                                                // mutually
                                                                                                                // exclusive
                                                                                                                // events
            System.out.println("2.6: Probability of the union of mutually exclusive events: " + unionProbability); // Output
                                                                                                                   // of
                                                                                                                   // the
                                                                                                                   // union
                                                                                                                   // probability
        } else {
            System.out.println("2.6: Thefore the events aren't mutually exclusive!!!"); // Output of the events
        }

        // Definition 2.9 Tester Conditional Probability
        double pAandB = 0.2; // Probability of A and B
        double pBConditional = 0.5; // Probability of B conditional

        try {
            double conditionalProbability = StatsLibrary.calculateConditionalProbability(pAandB, pBConditional); // Calculate
                                                                                                                 // the
                                                                                                                 // conditional
                                                                                                                 // probability
            System.out.println("2.9: Conditional Probability of P(A|B): " + conditionalProbability); // Output of the
                                                                                                     // conditional
                                                                                                     // probability
        } catch (IllegalArgumentException e) // Catch the exception
        {
            System.out.println(e.getMessage()); // Output the exception message
        }

        // Definition 2.10 Tester Example 1 expected output is true
        double probabilityA2 = 0.5; // Probability of A
        double probabilityB2 = 0.5; // Probability of B
        double pAandB2 = 0.25; // Probability of A and B

        boolean areIndependent = StatsLibrary.areIndependent(probabilityA2, probabilityB2, pAandB2); // Check if the
                                                                                                     // events are
                                                                                                     // independent
        System.out.println("2.10 (Example 1 Should be True) : Are A and B independent? " + areIndependent); // Output of
                                                                                                            // the
                                                                                                            // events

        // Definition 2.10 Tetser Example 2 expected output is false
        double probabilityA3 = 0.5; // Probability of A
        double probabilityB3 = 0.4; // Probability of B
        double pAandB3 = 0.1; // Probability of A and B

        boolean areIndependent2 = StatsLibrary.areIndependent(probabilityA3, probabilityB3, pAandB3); // Check if the
                                                                                                      // events are
                                                                                                      // independent
        System.out.println("2.10 (Example 2 Should be False) : Are A and B independent? " + areIndependent2); // Output
                                                                                                              // of the
                                                                                                              // events
        // Definition 2.11 Tester Example 1 expected output is true
        Set<Integer> universe = new HashSet<>(); // Set of the universe
        for (int i = 1; i <= 5; i++) // Loop through the universe
        {
            universe.add(i); // Add the universe
        }

        // Define subsets B1, B2, B3
        Set<Integer> B1 = new HashSet<>(); // Set of B1
        B1.add(1); // Add the value to B1
        B1.add(2);

        Set<Integer> B2 = new HashSet<>(); // Set of B2
        B2.add(3); // Add the value to B2

        Set<Integer> B3 = new HashSet<>(); // Set of B3
        B3.add(4); // Add the value to B3
        B3.add(5);

        // Adding the subsets
        ArrayList<Set<Integer>> sets = new ArrayList<>(); // List of the sets
        sets.add(B1); // Add the set
        sets.add(B2);
        sets.add(B3);

        // Checks if part of sets for partition of s
        boolean isPartition = StatsLibrary.isPartition(sets, universe); // Check if the sets are a partition of the
                                                                        // universe
        System.out.println("2.11 (True Example): Do the sets form a partition of S? " + isPartition); // Output of the
                                                                                                      // sets

        // Definition 2.11 Tester Example 2 expected output is false
        Set<Integer> B1False = new HashSet<>(); // Set of B1
        B1False.add(1); // Add the value to B1
        B1False.add(2);
        B1False.add(3);

        Set<Integer> B2False = new HashSet<>(); // Set of B2
        B2False.add(3); // Add the value to B2
        B2False.add(4);

        Set<Integer> B3False = new HashSet<>(); // Set of B3
        B3False.add(5); // Add the value to B3

        ArrayList<Set<Integer>> setsFalse = new ArrayList<>(); // List of the sets
        setsFalse.add(B1False); // Add the set
        setsFalse.add(B2False);
        setsFalse.add(B3False);

        boolean isPartitionFalse = StatsLibrary.isPartition(setsFalse, universe); // Check if the sets are a partition
                                                                                  // of the universe
        System.out.println("2.11 (False Example): Do the sets form a partition of S? " + isPartitionFalse); // Output of
                                                                                                            // the sets

        // Definition 3.4 Tester
        ArrayList<Double> values = new ArrayList<Double>(); // List of values
        values.add(1.0); // Add the values
        values.add(2.0);
        values.add(3.0);

        ArrayList<Double> probabilities = new ArrayList<Double>(); // List of probabilities
        probabilities.add(0.2); // Add the probabilities
        probabilities.add(0.5);
        probabilities.add(0.3);

        double expectedValue = StatsLibrary.calculateExpectedValue(values, probabilities); // Calculate the expected
                                                                                           // value
        System.out.println("3.4: Expected Value of Y: " + expectedValue); // Output of the expected value

        // Definition 3.5 Tester
        ArrayList<Double> valuesVariance = new ArrayList<Double>(); // List of values
        valuesVariance.add(1.0); // Add the values
        valuesVariance.add(2.0);
        valuesVariance.add(3.0);

        ArrayList<Double> probabilitiesVariance = new ArrayList<Double>(); // List of probabilities
        probabilitiesVariance.add(0.2); // Add the probabilities
        probabilitiesVariance.add(0.5);
        probabilitiesVariance.add(0.3);

        double expectedValueVariance = StatsLibrary.calculateExpectedValue(valuesVariance, probabilitiesVariance); // Calculate
                                                                                                                   // the
                                                                                                                   // expected
                                                                                                                   // value
        System.out.println("Expected Value E(Y): " + expectedValueVariance); // Output of the expected value

        double varianceResult = StatsLibrary.calculateVariance(valuesVariance, probabilitiesVariance); // Calculate the
                                                                                                       // variance
        System.out.println("Variance V(Y): " + varianceResult); // Output of the variance

        double standardDeviationResult = StatsLibrary.calculateStandardDeviation(valuesVariance, probabilitiesVariance); // Calculate
                                                                                                                         // the
                                                                                                                         // standard
                                                                                                                         // deviation
        System.out.println("Standard Deviation of Y: " + standardDeviationResult); // Output of the standard deviation

        // Definition 3.7 Tester
        int n = 5; // Number of trials
        int y = 3; // Number of successes
        double p = 0.6; // Probability of success

        double binomialProbability = StatsLibrary.calculateBinomialProbability(n, y, p); // Calculate the binomial
                                                                                         // probability
        System.out.println("3.7: Binomial Probability: " + binomialProbability); // Output of the binomial probability

        // Defintion 3.8
        int yGeometric = 3; // Number of trials
        double pGeometric = 0.5; // Probability of success

        double geometricProbability = StatsLibrary.calculateGeometricProbability(yGeometric, pGeometric); // Calculate
                                                                                                          // the
                                                                                                          // geometric
                                                                                                          // probability
        System.out.println("3.8: Geometric Probability P(Y = " + yGeometric + "): " + geometricProbability); // Output
                                                                                                             // of the
                                                                                                             // geometric
                                                                                                             // probability
    }
}
