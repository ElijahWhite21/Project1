public class Pokemon {
    String name; // Name of the Pokemon
    int hp; // Hit points (health) of the Pokemon
    int attack; // Attack power of the Pokemon
    int defense; // Defense power of the Pokemon
    int speed; // Speed of the Pokemon, determines the order of attack in battle

    /**
     * This constructor sets the name, hp, attack, defense, and speed of the Pokemon.
     * It is used to create a new Pokemon instance with specific attributes.
     * This is a generic Pokemon class that can be extended by specific Pokemon types like Pikachu or Charmander.
     * It allows for the creation of Pokemon with different stats and behaviors.
     * The constructor takes parameters for the name, hp, attack, defense, and speed of the Pokemon.
     * @param name
     * @param hp
     * @param attack
     * @param defense
     * @param speed
     */
    public Pokemon(String name, int hp, int attack, int defense, int speed) {
        this.name = name; // Name of the Pokemon
        this.hp = hp; // Hit points (health) of the Pokemon
        this.attack = attack; // Attack power of the Pokemon 
        this.defense = defense; // Defense power of the Pokemon
        this.speed = speed; // Speed of the Pokemon, determines the order of attack in battle
    }

    public void takeDamage(int damage) {
        this.hp -= damage; // Reduce the Pokemon's HP by the damage taken
        if (this.hp < 0) // If HP drops below 0, set it to 0 (fainted) 
        {
            this.hp = 0; // Fainted condition
        }
    }

    public boolean isAlive() // Check if the Pokemon is still alive
    {
        return this.hp > 0; // Check if the Pokemon is still alive (HP > 0)
    }

    public int calculateDamage(Pokemon opponent) // Calculate the damage dealt to the opponent
    {
        return Math.max(1, this.attack - opponent.defense); // Minimum damage is 1
    }
}
