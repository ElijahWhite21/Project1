public class MathOperations {

    // Factorial
    public static int factorial(int n) {

        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Combinations
    public static int combinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // Permutations
    public static int permutations(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    public static void main(String[] args) {
        System.out.println("Combinations examples:" + "\n" + combinations(5, 3));
        System.out.println("Permutations examples:" + "\n" + permutations(5, 3));
    }
}
