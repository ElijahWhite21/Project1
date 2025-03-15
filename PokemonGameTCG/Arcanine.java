public class Arcanine extends Pokemon {

    public Arcanine() {
        super("Arcanine", 130, "Fire"); // Call the superclass constructor
        setPrimaryAttack("Crunch", 30); // Set the primary attack
        setSecondaryAttack("Fire Mane", 120); // Set the secondary attack
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Crunch on " + target.getName() + " for 30 damage.");
        target.takeDamage(30);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Fire Mane on " + target.getName() + " for 120 damage.");
        target.takeDamage(120);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
