public class BirthdayProgram {

    /**
     * simulation to calculate the probability of shared birthdays
     * 
     * @param classSize
     * @param numberOfRuns
     */
    public void runSimulation(int classSize, int numberOfRuns) {

        int matchingBirthdaysCount = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            if (hasSharedBirthday(classSize)) {
                matchingBirthdaysCount++;
            }
        }

        // Calculate and displays probability of shared birthdays throughot the class
        double probability = (double) matchingBirthdaysCount / numberOfRuns * 100;
        System.out.println("Probability of someone in the class having the same birthday out of " + classSize
                + " students: " + probability + "%");
    }

    /**
     * Checks if there are shared birthdays in the class
     * 
     * @param classSize
     * @return
     */
    private boolean hasSharedBirthday(int classSize) {

        Student[] people = new Student[classSize];
        int[] birthdayTracker = new int[365];

        // Initialize student and count each birthday
        for (int i = 0; i < classSize; i++) {
            people[i] = new Student();
            int birthday = people[i].getBirthday();

            // If birthday already exists in tracker, return true for a match
            if (birthdayTracker[birthday - 1] > 0) {
                return true;
            }
            birthdayTracker[birthday - 1]++;
        }
        return false;
    }
}