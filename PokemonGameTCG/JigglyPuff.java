public class JigglyPuff extends Pokemon {

    public JigglyPuff() {
        super("Jigglypuff", 40, "Fairy");
        setPrimaryAttack("Mumble", 25);
        setSecondaryAttack("Moon Kick", 20);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Mumble on " + target.getName() + " for 25 damage.");
        target.takeDamage(25);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Moon Kick " + target.getName() + " for 20 damage.");
        target.takeDamage(20);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
