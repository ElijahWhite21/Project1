public class MathOperations {

    // Factorial
    public static int factorial(int n) {

        int result = 1; // Initialize result

        for (int i = 1; i <= n; i++) // Loop through all numbers from 1 to n
        {
            result *= i; // Multiply the result by the current number
        }

        return result; // Return the result
    }

    // Combinations
    public static int combinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r)); // Using the factorial method to calculate the
                                                                 // combinations
    }

    // Permutations
    public static int permutations(int n, int r) // Using the factorial method to calculate the permutations
    {
        return factorial(n) / factorial(n - r); // Using the factorial method to calculate the permutations
    }

    public static void main(String[] args) {
        System.out.println("Combinations examples:" + "\n" + combinations(5, 3)); // Print the result of the
                                                                                  // combinations method
        System.out.println("Permutations examples:" + "\n" + permutations(5, 3)); // Print the result of the
                                                                                  // permutations method
    }
}
