import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatsLibrary {

    // Mean

    /**
     * @param listOfnumbers
     * @return
     */
    public double computeMean(ArrayList<Integer> listOfnumbers) {
        int sum = 0; // Initialize sum to 0

        for (int singleNumber : listOfnumbers) // Loop through the list of numbers
        {
            sum = sum + singleNumber; // Add each number to the sum
        }
        return sum / (double) listOfnumbers.size(); // Return the sum divided by the number of elements in the list
    }

    // Median

    /**
     * @param listOfnumbers
     * @return
     */
    public double computeMedian(ArrayList<Integer> listOfnumbers) // Method to calculate the median
    {
        int size = listOfnumbers.size(); // Get the size of the list
        double median; // Initialize the median variable
        if (size % 2 == 0) // Check if the size of the list is even
        {
            median = (listOfnumbers.get(size / 2) + listOfnumbers.get(size / 2 - 1)) / 2.0; // Calculate the median
        } else {
            median = listOfnumbers.get(size / 2); // Calculate the median
        }
        return median; // Return the median
    }

    // Mode

    /**
     * @param listOfnumbers
     * @return
     */
    public int computeMode(ArrayList<Integer> listOfnumbers) // Method to calculate the mode
    {
        int mode = 0; // Initialize the mode to 0
        int maxCount = 0; // Initialize the maximum count to 0
        for (int i = 0; i < listOfnumbers.size(); i++) // Loop through the list of numbers
        {
            int count = 0; // Initialize the count to 0
            for (int j = 0; j < listOfnumbers.size(); j++) // Loop through the list of numbers
            {
                if (listOfnumbers.get(j) == listOfnumbers.get(i)) // Check if the numbers are equal
                {
                    count++; // Increment the count
                }
            }
            if (count > maxCount) // Check if the count is greater than the maximum count
            {
                maxCount = count; // Update the maximum count
                mode = listOfnumbers.get(i); // Update the mode
            }
        }
        return mode; // Return the mode
    }

    // Standard Deviation
    /**
     *
     * @param listOfnumbers
     * 
     * @return
     */
    public double computeStandardDeviation(ArrayList<Integer> listOfnumbers) // Method to calculate the standard
                                                                             // deviation
    {
        double mean = computeMean(listOfnumbers); // Calculate the mean
        double sum = 0; // Initialize the sum to 0

        for (int number : listOfnumbers) // Loop through the list of numbers
        {
            sum = sum + Math.pow(number - mean, 2); // Calculate the sum
        }
        return Math.sqrt(sum / listOfnumbers.size()); // Return the square root of the sum divided by the number of
                                                      // elements in the list
    }

    // Definition Variance 1.2
    /**
     * @param listOfnumbers
     * @return
     */
    public double computeVariance(ArrayList<Integer> listOfnumbers) // Method to calculate the variance
    {
        double mean = computeMean(listOfnumbers); // Calculate the mean
        double sum = 0; // Initialize the sum to 0
        for (int number : listOfnumbers) // Loop through the list of numbers
        {
            sum = sum + Math.pow(number - mean, 2); // Calculate the sum
        }
        return sum / listOfnumbers.size(); // Return the sum divided by the number of elements in the list
    }

    // Probability Axioms 1
    /**
     * @param probability
     * @return
     */
    public static boolean isValidProbability(double probability) // Method to check if a probability is valid
    {
        return probability >= 0; // Check if the probability is greater than or equal to 0
    }

    // Probability Axiom 2
    /**
     * @param probability
     * @return
     */
    public static boolean isConditionalProbability(double probability) // Method to check if a probability is a
                                                                       // conditional probability
    {
        return probability == 1.0; // Check if the probability is equal to 1
    }

    // Probaility Axiom 3
    /**
     * @param probabilities
     * @param mutuallyExclusiveFlags
     * @return
     */
    public static boolean areMutuallyExclusive(ArrayList<Double> probabilities,
            ArrayList<Boolean> mutuallyExclusiveFlags) // Method to check if a set of events are mutually exclusive
    {
        for (Boolean flag : mutuallyExclusiveFlags) // Loop through the list of flags
        {
            if (!flag) // Check if the flag is false
            {
                return false; // Return false
            }
        }
        return true; // Return true
    }

    // Calculating the probability of the union
    /**
     * @param probabilities
     * @return
     */
    public static double calculateUnionOfMutuallyExclusiveEvents(ArrayList<Double> probabilities) // Method to calculate
                                                                                                  // the probability of
                                                                                                  // the union of
                                                                                                  // mutually exclusive
                                                                                                  // events
    {
        double sum = 0; // Initialize the sum to 0
        for (double probability : probabilities) // Loop through the list of probabilities
        {
            sum = sum + probability; // Calculate the sum
        }
        return sum; // Return the sum
    }

    // Definition 2.9: Conditional Probability
    /**
     * @param pAandB
     * @param pB
     * @return
     */
    public static double calculateConditionalProbability(double pAandB, double pB) // Method to calculate the
                                                                                   // conditional probability
    {
        if (pB == 0) // Check if the probability of B is 0
        {
            throw new IllegalArgumentException("P(B) must be greater than 0 to calculate conditional probability."); // Throw
                                                                                                                     // an
                                                                                                                     // exception
        }
        return pAandB / pB; // Return the conditional probability
    }

    // Definition 2.10 Checking if A and B are independent
    /**
     * @param pA
     * @param pB
     * @param pAandB
     * @return
     */
    public static boolean areIndependent(double pA, double pB, double pAandB) // Method to check if A and B are
                                                                              // independent
    {
        if (pAandB == pA * pB) // Check if the probability of A and B is equal to the product of the
                               // probabilities of A and B
        {
            return true; // Return true
        }
        if (pB > 0 && (pAandB / pB) == pA) // Check if the probability of B is greater than 0 and the conditional
                                           // probability of A given B is equal to the probability of A
        {
            return true; // Return true
        }
        if (pA > 0 && (pAandB / pA) == pB) // Check if the probability of A is greater than 0 and the conditional
                                           // probability of B given A is equal to the probability of B
        {
            return true; // Return true
        }
        return false; // Return false
    }

    // Definition 2.11 Checking if a set of sets is a partition of a universe
    /**
     * @param sets
     * @param universe
     * @return
     */
    public static boolean isPartition(ArrayList<Set<Integer>> sets, Set<Integer> universe) // Method to check if a set
                                                                                           // of sets is a partition of
                                                                                           // a universe
    {

        Set<Integer> unionOfSets = new HashSet<>(); // Initialize the union of sets to an empty set

        for (Set<Integer> set : sets) // Loop through the list of sets
        {
            unionOfSets.addAll(set); // Add the elements of the set to the union of sets
        }
        if (!unionOfSets.equals(universe)) // Check if the union of sets is not equal to the universe
        {
            return false; // Return false
        }
        for (int i = 0; i < sets.size(); i++) // Loop through the list of sets
        {
            for (int j = i + 1; j < sets.size(); j++) // Loop through the list of sets
            {
                Set<Integer> intersection = new HashSet<>(sets.get(i)); // Initialize the intersection to a copy of the
                                                                        // first set
                intersection.retainAll(sets.get(j)); // Find the intersection of the two sets
                if (!intersection.isEmpty()) // Check if the intersection is not empty
                {
                    return false; // Return false
                }
            }
        }
        return true; // Return true
    }

    // Definition 3.4 Calculating the expected value of E(Y) = Σy * p(y)
    /**
     * @param values
     * @param probabilities
     * @return
     */
    public static double calculateExpectedValue(ArrayList<Double> values, ArrayList<Double> probabilities) // Method to
                                                                                                           // calculate
                                                                                                           // the
                                                                                                           // expected
                                                                                                           // value
    {
        if (values.size() != probabilities.size()) // Check if the sizes of the values and probabilities are not equal
        {
            throw new IllegalArgumentException("The sizes of values and probabilities must match"); // Throw an
                                                                                                    // exception
        }

        double expectedValue = 0; // Initialize the expected value to 0
        for (int i = 0; i < values.size(); i++) // Loop through the list of values
        {
            expectedValue += values.get(i) * probabilities.get(i); // Calculate the expected value
        }
        return expectedValue; // Return the expected value
    }

    // Definition 3.5 Calculating the variance V(Y) = E[(Y - μ)^2]
    /**
     * @param values
     * @param probabilities
     * @return
     */
    public static double calculateVariance(ArrayList<Double> values, ArrayList<Double> probabilities) // Method to
                                                                                                      // calculate the
                                                                                                      // variance
    {
        double mean = calculateExpectedValue(values, probabilities); // Calculate the mean
        double variance = 0; // Initialize the variance to 0

        for (int i = 0; i < values.size(); i++) // Loop through the list of values
        {
            double deviation = values.get(i) - mean; // Calculate the deviation
            variance += Math.pow(deviation, 2) * probabilities.get(i); // Calculate the variance
        }
        return variance; // Return the variance
    }

    // Calculating the standard deviation of a random variable
    /**
     * @param values
     * @param probabilities
     * @return
     */
    public static double calculateStandardDeviation(ArrayList<Double> values, ArrayList<Double> probabilities) // Method
                                                                                                               // to
                                                                                                               // calculate
                                                                                                               // the
                                                                                                               // standard
                                                                                                               // deviation
    {
        return Math.sqrt(calculateVariance(values, probabilities)); // Return the square root of the variance
    }

    // Method for factorial calculation
    /**
     * @param num
     * @return
     */
    private static long factorial(int num) // Method to calculate the factorial of a number
    {
        long result = 1; // Initialize the result to 1
        for (int i = 1; i <= num; i++) // Loop through the numbers from 1 to num
        {
            result *= i; // Calculate the factorial
        }
        return result; // Return the factorial
    }

    // Definition 3.7
    /**
     * @param n
     * @param y
     * @return
     */
    public static double binomialCoefficient(int n, int y) // Method to calculate the binomial coefficient
    {
        return factorial(n) / (factorial(y) * factorial(n - y)); // Return the binomial coefficient
    }

    /**
     * @param n
     * @param y
     * @param p
     * @return
     */
    public static double calculateBinomialProbability(int n, int y, double p) // Method to calculate the binomial
                                                                              // probability
    {
        if (p < 0 || p > 1) // Check if the probability is between 0 and 1
        {
            throw new IllegalArgumentException("The probability must be between 0 and 1"); // Throw an exception
        }
        if (y < 0 || y > n) // Check if the number of successes is between 0 and n
        {
            throw new IllegalArgumentException("The number of successes must be between 0 and n"); // Throw an exception
        }

        double q = 1 - p; // Calculate the probability of failure
        double binomialCoefficient = binomialCoefficient(n, y); // Calculate the binomial coefficient

        return binomialCoefficient * Math.pow(p, y) * Math.pow(q, n - y); // Return the binomial probability
    }

    // Definition 3.8: Calculate geometric probability p(y) for first success on
    // y-th trial
    /**
     * @param y
     * @param p
     * @return
     */
    public static double calculateGeometricProbability(int y, double p) // Method to calculate the geometric probability
    {
        if (p < 0 || p > 1) // Check if the probability is between 0 and 1
        {
            throw new IllegalArgumentException("Probability p must be between 0 and 1."); // Throw an exception
        }
        if (y < 1) // Check if the trial number is 1 or greater
        {
            throw new IllegalArgumentException("The trial number y must be 1 or greater."); // Throw an exception
        }

        double q = 1 - p; // Probability of failure
        return Math.pow(q, y - 1) * p; // Return the geometric probability
    }

}
