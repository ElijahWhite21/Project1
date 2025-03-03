public class BirthdayProgram {

    /**
     * simulation to calculate the probability of shared birthdays
     * 
     * @param classSize
     * @param numberOfRuns
     */
    public void runSimulation(int classSize, int numberOfRuns) {

        int matchingBirthdaysCount = 0; // Counter for matching birthdays

        for (int i = 0; i < numberOfRuns; i++) { // Run simulation for number of runs
            if (hasSharedBirthday(classSize)) { // Check if there are shared birthdays
                matchingBirthdaysCount++; // Increment counter if shared birthdays are found
            }
        }

        // Calculate and displays probability of shared birthdays throughot the class
        double probability = (double) matchingBirthdaysCount / numberOfRuns * 100; // Calculate probability
        System.out.println("Probability of someone in the class having the same birthday out of " + classSize // Display
                                                                                                              // probability
                + " students: " + probability + "%");
    }

    /**
     * Checks if there are shared birthdays in the class
     * 
     * @param classSize
     * @return
     */
    private boolean hasSharedBirthday(int classSize) {

        Student[] people = new Student[classSize]; // Array of students
        int[] birthdayTracker = new int[365]; // Array to track birthdays

        // Initialize student and count each birthday
        for (int i = 0; i < classSize; i++) { // Loop through each student
            people[i] = new Student(); // Initialize student
            int birthday = people[i].getBirthday(); // Get student's birthday

            // If birthday already exists in tracker, return true for a match
            if (birthdayTracker[birthday - 1] > 0) { // Check if birthday already exists
                return true; // Return true if birthday already exists
            }
            birthdayTracker[birthday - 1]++; // Increment birthday count
        }
        return false; // Return false if no shared birthdays are found
    }
}