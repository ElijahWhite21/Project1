public class ThreeDoor {

    public void runSimulations(int trials) {

        int winsWithoutSwitching = simulateNoSwitch(trials); // Simulate the game without switching doors
        int winsWithSwitching = simulateSwitch(trials); // Simulate the game with switching doors
        System.out.println("---------------------------------");

        System.out.println(
                "Win percentage if user doesn't switch " + (winsWithoutSwitching / (double) trials) * 100 + "%"); // Calculate
                                                                                                                  // the
                                                                                                                  // win
                                                                                                                  // percentage
                                                                                                                  // if
                                                                                                                  // the
                                                                                                                  // user
                                                                                                                  // doesn't
                                                                                                                  // switch
                                                                                                                  // doors

        System.out.println("Win percentage if user does switch " + (winsWithSwitching / (double) trials) * 100 + "%"); // Calculate
                                                                                                                       // the
                                                                                                                       // win
                                                                                                                       // percentage
                                                                                                                       // if
                                                                                                                       // the
                                                                                                                       // user
                                                                                                                       // switches
                                                                                                                       // doors

        System.out.println(
                "Difference: " + Math.abs(winsWithoutSwitching - winsWithSwitching) / (double) trials * 100 + "%"); // Calculate
                                                                                                                    // the
                                                                                                                    // difference
                                                                                                                    // in
                                                                                                                    // win
                                                                                                                    // percentage

        System.out.println("---------------------------------");

        System.out.println("Essentially leading us to belive the user should switch doors for better chance"); // Print
                                                                                                               // the
                                                                                                               // conclusion

        System.out.println("---------------------------------");
    }

    /**
     * Simulates the game without switching doors
     * 
     * @param trials
     * @return
     */
    private int simulateNoSwitch(int trials) {
        int wins = 0;

        for (int i = 0; i < trials; i++) { // Loop through the number of trials
            int correctDoor = (int) (Math.random() * 3); // Randomly choose the correct door
            int chosenDoor = (int) (Math.random() * 3); // Randomly choose the door the user chooses
            if (chosenDoor == correctDoor) { // If the user chooses the correct door
                wins++; // Increment the number of wins
            }
        }

        return wins; // Return the number of wins
    }

    /**
     * Simulates the game with switching doors
     * 
     * @param trials
     * @return
     */
    private int simulateSwitch(int trials) { // Simulate the game with switching doors
        int wins = 0; // Initialize the number of wins

        for (int i = 0; i < trials; i++) { // Loop through the number of trials
            int correctDoor = (int) (Math.random() * 3); // Randomly choose the correct door
            int chosenDoor = (int) (Math.random() * 3); // Randomly choose the door the user chooses
            if (chosenDoor != correctDoor) { // If the user chooses the wrong door
                wins++; // Increment the number of wins
            }
        }

        return wins; // Return the number of wins
    }
}