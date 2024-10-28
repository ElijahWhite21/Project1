public class ThreeDoor {

    public void runSimulations(int trials) {

        int winsWithoutSwitching = simulateNoSwitch(trials);
        int winsWithSwitching = simulateSwitch(trials);
        System.out.println("---------------------------------");
        System.out.println(
                "Win percentage if user doesn't switch " + (winsWithoutSwitching / (double) trials) * 100 + "%");
        System.out.println("Win percentage if user does switch " + (winsWithSwitching / (double) trials) * 100 + "%");
        System.out.println(
                "Difference: " + Math.abs(winsWithoutSwitching - winsWithSwitching) / (double) trials * 100 + "%");
        System.out.println("Essentially leading us to belive the user should switch doors for better chance");
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

        for (int i = 0; i < trials; i++) {
            int correctDoor = (int) (Math.random() * 3);
            int chosenDoor = (int) (Math.random() * 3);
            if (chosenDoor == correctDoor) {
                wins++;
            }
        }

        return wins;
    }

    /**
     * Simulates the game with switching doors
     * 
     * @param trials
     * @return
     */
    private int simulateSwitch(int trials) {
        int wins = 0;

        for (int i = 0; i < trials; i++) {
            int correctDoor = (int) (Math.random() * 3);
            int chosenDoor = (int) (Math.random() * 3);
            if (chosenDoor != correctDoor) {
                wins++;
            }
        }

        return wins;
    }
}