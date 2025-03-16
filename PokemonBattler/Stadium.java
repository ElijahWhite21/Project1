public class Stadium {
    /**
     * * This is the main method that runs the battle simulation between two
     * Pokemon.
     * It initializes two Pokemon (Pikachu and Charmander) and simulates their
     * battle until one of them faints.
     * * The battle logic is based on the speed of the Pokemon, determining which
     * one attacks first.
     * The damage calculation is done using the calculateDamage method from the
     * Pokemon class.
     * The battle continues until one of the Pokemon's HP drops to 0 or below,
     * indicating that it has fainted.
     * Finally, the winner is announced based on which Pokemon is still alive.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Pokemon pikachu = new Pikachu(); // Initialize Pikachu with its specific stats
        Pokemon charmander = new Charmander(); // Initialize Charmander with its specific stats

        System.out.println("Battle Begin!");

        while (pikachu.isAlive() && charmander.isAlive()) // Continue the battle until one Pokemon faints
        {
            if (pikachu.speed > charmander.speed) // Check which Pokemon is faster to determine the order
            {
                // Pikachu attacks first
                int damage = pikachu.calculateDamage(charmander); // Calculate damage dealt to Charmander
                charmander.takeDamage(damage); // Charmander takes damage
                System.out.println(pikachu.name + " attacks " + charmander.name + " for " + damage + " damage!");
                System.out.println(charmander.name + " has " + charmander.hp + " HP left.");

                if (!charmander.isAlive()) // Check if Charmander has fainted
                {
                    System.out.println(charmander.name + " fainted!");
                    break; // End the battle if Charmander has fainted
                }

                // Charmander attacks next
                damage = charmander.calculateDamage(pikachu); // Calculate damage dealt to Pikachu
                pikachu.takeDamage(damage); // Pikachu takes damage
                System.out.println(charmander.name + " attacks " + pikachu.name + " for " + damage + " damage!");
                System.out.println(pikachu.name + " has " + pikachu.hp + " HP left.");

                if (!pikachu.isAlive()) // Check if Pikachu has fainted
                {
                    System.out.println(pikachu.name + " fainted!");
                    break; // End the battle if Pikachu has fainted
                }
            } else {
                // Charmander attacks first
                int damage = charmander.calculateDamage(pikachu); // Calculate damage dealt to Pikachu
                pikachu.takeDamage(damage); // Pikachu takes damage
                System.out.println(charmander.name + " attacks " + pikachu.name + " for " + damage + " damage!");
                System.out.println(pikachu.name + " has " + pikachu.hp + " HP left.");

                if (!pikachu.isAlive()) // Check if Pikachu has fainted
                {
                    System.out.println(pikachu.name + " fainted!");
                    break; // End the battle if Pikachu has fainted
                }

                // Pikachu attacks next
                damage = pikachu.calculateDamage(charmander); // Calculate damage dealt to Charmander
                charmander.takeDamage(damage); // Charmander takes damage
                System.out.println(pikachu.name + " attacks " + charmander.name + " for " + damage + " damage!");
                System.out.println(charmander.name + " has " + charmander.hp + " HP left.");

                if (!charmander.isAlive()) // Check if Charmander has fainted
                {
                    System.out.println(charmander.name + " fainted!");
                    break; // End the battle if Charmander has fainted
                }
            }
        }

        if (pikachu.isAlive()) {
            System.out.println(pikachu.name + " has won the battle!");
        } else {
            System.out.println(charmander.name + " has won the battle!");
        }
    }
}
