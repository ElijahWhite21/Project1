public class Snorlax extends Pokemon {

    public Snorlax() {
        super("Snorlax", 160, "Normal");
        setPrimaryAttack("Body Slam", 30);
        setSecondaryAttack("Hyper Beam", 120);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Body Slam on " + target.getName() + " for 30 damage.");
        target.takeDamage(30);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Hyper Beam on " + target.getName() + " for 120 damage.");
        target.takeDamage(120);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
