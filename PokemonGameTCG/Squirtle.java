public class Squirtle extends Pokemon {

    public Squirtle() {
        super("Squirtle", 40, "Water");
        setPrimaryAttack("Water Splash", 10);
        setSecondaryAttack("Shell Attack", 40);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Water Splash on " + target.getName() + " for 10 damage.");
        target.takeDamage(10);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Shell Attack on " + target.getName() + " for 40 damage.");
        target.takeDamage(40);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
