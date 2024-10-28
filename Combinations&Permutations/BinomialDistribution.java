public class BinomialDistribution {
    public static double binomialDistribution(int n, int k, double p) {
        int combinations = MathOperations.combinations(n, k);
        double successTerm = Math.pow(p, k);
        double failureTerm = Math.pow(1 - p, n - k);
        return combinations * successTerm * failureTerm;
    }

    public static void main(String[] args) {

        int n = 5;
        int k = 3;
        double p = 0.5;

        double result = binomialDistribution(n, k, p);
        System.out.println("Binomial Distribution result: " + result);
    }
}