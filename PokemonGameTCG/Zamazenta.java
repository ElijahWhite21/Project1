public class Zamazenta extends Pokemon {

    public Zamazenta() {
        super("Zamazenta", 140, "Steel");
        setPrimaryAttack("Iron Head", 30);
        setSecondaryAttack("Behemoth Bash", 120);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Iron Head on " + target.getName() + " for 30 damage.");
        target.takeDamage(30);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Behemoth Bash on " + target.getName() + " for 120 damage.");
        target.takeDamage(120);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}