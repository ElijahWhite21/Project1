public class BinomialDistribution {
    /**
     * @param n
     * @param k
     * @param p
     * @return
     */
    public static double binomialDistribution(int n, int k, double p) // Method to calculate the binomial distribution
    {
        int combinations = MathOperations.combinations(n, k); // Using the combinations method from MathOperations.java
        double successTerm = Math.pow(p, k); // Using the Math.pow method to calculate the power of p
        double failureTerm = Math.pow(1 - p, n - k); // Using the Math.pow method to calculate the power of 1 - p
        return combinations * successTerm * failureTerm; // Returning the result of the binomial distribution formula
    }

    /**
     * @param args
     */
    public static void main(String[] args) // Main methodS
    {

        int n = 5; // Number of trials
        int k = 3; // Number of successful trials
        double p = 0.5; // Probability of success

        double result = binomialDistribution(n, k, p); // Using the binomialDistribution method to calculate the result
        System.out.println("Binomial Distribution result: " + result); // Printing the result
    }
}