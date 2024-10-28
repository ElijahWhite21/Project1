public class Charmander extends Pokemon {

    public Charmander() {
        super("Charmander", 60, "Fire");
        setPrimaryAttack("Ember", 25);
        setSecondaryAttack("Flamethrower", 40);
    }

    /**
     * Primary attack: Ember - Deals 10 damage to the opponent's Pokémon.
     *
     * @param target The Pokémon being attacked.
     */
    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Ember on " + target.getName() + " for 25 damage.");
        target.takeDamage(25);
    }

    /**
     * Secondary attack: Flamethrower - Deals 30 damage to the opponent's Pokémon.
     * Costs additional energy if using a detailed energy system.
     *
     * @param target The Pokémon being attacked.
     */
    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Flamethrower on " + target.getName() + " for 40 damage.");
        target.takeDamage(40);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
