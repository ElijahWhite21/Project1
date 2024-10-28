import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StatsLibrary {

    // Mean
    public double computeMean(ArrayList<Integer> listOfnumbers) {
        int sum = 0;

        for (int singleNumber : listOfnumbers) {
            sum = sum + singleNumber;
        }
        return sum / (double) listOfnumbers.size();
    }

    // Median
    public double computeMedian(ArrayList<Integer> listOfnumbers) {
        int size = listOfnumbers.size();
        double median;
        if (size % 2 == 0) {
            median = (listOfnumbers.get(size / 2) + listOfnumbers.get(size / 2 - 1)) / 2.0;
        } else {
            median = listOfnumbers.get(size / 2);
        }
        return median;
    }

    // Mode
    public int computeMode(ArrayList<Integer> listOfnumbers) {
        int mode = 0;
        int maxCount = 0;
        for (int i = 0; i < listOfnumbers.size(); i++) {
            int count = 0;
            for (int j = 0; j < listOfnumbers.size(); j++) {
                if (listOfnumbers.get(j) == listOfnumbers.get(i)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mode = listOfnumbers.get(i);
            }
        }
        return mode;
    }

    // Standard Deviation
    public double computeStandardDeviation(ArrayList<Integer> listOfnumbers) {
        double mean = computeMean(listOfnumbers);
        double sum = 0;
        for (int number : listOfnumbers) {
            sum = sum + Math.pow(number - mean, 2);
        }
        return Math.sqrt(sum / listOfnumbers.size());
    }

    // Definition Variance 1.2
    /**
     * @param listOfnumbers
     * @return
     */
    public double computeVariance(ArrayList<Integer> listOfnumbers) {
        double mean = computeMean(listOfnumbers);
        double sum = 0;
        for (int number : listOfnumbers) {
            sum = sum + Math.pow(number - mean, 2);
        }
        return sum / listOfnumbers.size();
    }

    // Probability Axioms 1
    public static boolean isValidProbability(double probability) {
        return probability >= 0;
    }

    // Probability Axiom 2
    public static boolean isConditionalProbability(double probability) {
        return probability == 1.0;
    }

    // Probaility Axiom 3
    public static boolean areMutuallyExclusive(ArrayList<Double> probabilities,
            ArrayList<Boolean> mutuallyExclusiveFlags) {
        for (Boolean flag : mutuallyExclusiveFlags) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    // Calculating the probability of the union
    /**
     * @param probabilities
     * @return
     */
    public static double calculateUnionOfMutuallyExclusiveEvents(ArrayList<Double> probabilities) {
        double sum = 0;
        for (double probability : probabilities) {
            sum = sum + probability;
        }
        return sum;
    }

    // Definition 2.9: Conditional Probability
    /**
     * @param pAandB
     * @param pB
     * @return
     */
    public static double calculateConditionalProbability(double pAandB, double pB) {
        if (pB == 0) {
            throw new IllegalArgumentException("P(B) must be greater than 0 to calculate conditional probability.");
        }
        return pAandB / pB;
    }

    // Definition 2.10 Checking if A and B are independent
    /**
     * @param pA
     * @param pB
     * @param pAandB
     * @return
     */
    public static boolean areIndependent(double pA, double pB, double pAandB) {
        if (pAandB == pA * pB) {
            return true;
        }
        if (pB > 0 && (pAandB / pB) == pA) {
            return true;
        }
        if (pA > 0 && (pAandB / pA) == pB) {
            return true;
        }
        return false;
    }

    // Definition 2.11 Checking if a set of sets is a partition of a universe
    /**
     * @param sets
     * @param universe
     * @return
     */
    public static boolean isPartition(ArrayList<Set<Integer>> sets, Set<Integer> universe) {

        Set<Integer> unionOfSets = new HashSet<>();
        for (Set<Integer> set : sets) {
            unionOfSets.addAll(set);
        }
        if (!unionOfSets.equals(universe)) {
            return false;
        }
        for (int i = 0; i < sets.size(); i++) {
            for (int j = i + 1; j < sets.size(); j++) {
                Set<Integer> intersection = new HashSet<>(sets.get(i));
                intersection.retainAll(sets.get(j));
                if (!intersection.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Definition 3.4 Calculating the expected value of E(Y) = Σy * p(y)
    /**
     * @param values
     * @param probabilities
     * @return
     */
    public static double calculateExpectedValue(ArrayList<Double> values, ArrayList<Double> probabilities) {
        if (values.size() != probabilities.size()) {
            throw new IllegalArgumentException("The sizes of values and probabilities must match");
        }

        double expectedValue = 0;
        for (int i = 0; i < values.size(); i++) {
            expectedValue += values.get(i) * probabilities.get(i);
        }
        return expectedValue;
    }

    // Definition 3.5 Calculating the variance V(Y) = E[(Y - μ)^2]
    /**
     * @param values
     * @param probabilities
     * @return
     */
    public static double calculateVariance(ArrayList<Double> values, ArrayList<Double> probabilities) {
        double mean = calculateExpectedValue(values, probabilities);
        double variance = 0;

        for (int i = 0; i < values.size(); i++) {
            double deviation = values.get(i) - mean;
            variance += Math.pow(deviation, 2) * probabilities.get(i);
        }
        return variance;
    }

    // Calculating the standard deviation of a random variable
    public static double calculateStandardDeviation(ArrayList<Double> values, ArrayList<Double> probabilities) {
        return Math.sqrt(calculateVariance(values, probabilities));
    }

    // Method for factorial calculation
    /**
     * @param num
     * @return
     */
    private static long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    // Definition 3.7
    /**
     * @param n
     * @param y
     * @return
     */
    public static double binomialCoefficient(int n, int y) {
        return factorial(n) / (factorial(y) * factorial(n - y));
    }

    /**
     * @param n
     * @param y
     * @param p
     * @return
     */
    public static double calculateBinomialProbability(int n, int y, double p) {
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("The probability must be between 0 and 1");
        }
        if (y < 0 || y > n) {
            throw new IllegalArgumentException("The number of successes must be between 0 and n");
        }

        double q = 1 - p;
        double binomialCoefficient = binomialCoefficient(n, y);

        return binomialCoefficient * Math.pow(p, y) * Math.pow(q, n - y);
    }

    // Definition 3.8: Calculate geometric probability p(y) for first success on
    // y-th trial
    /**
     * @param y
     * @param p
     * @return
     */
    public static double calculateGeometricProbability(int y, double p) {
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be between 0 and 1.");
        }
        if (y < 1) {
            throw new IllegalArgumentException("The trial number y must be 1 or greater.");
        }

        double q = 1 - p; // Probability of failure
        return Math.pow(q, y - 1) * p;
    }

}
