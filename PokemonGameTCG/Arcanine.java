public class Arcanine extends Pokemon {

    public Arcanine() {
        super("Arcanine", 130, "Fire");
        setPrimaryAttack("Crunch", 30);
        setSecondaryAttack("Fire Mane", 120);
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
