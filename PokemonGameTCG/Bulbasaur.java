public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur", 70, "Grass");
        setPrimaryAttack("Vine Whip", 10);
        setSecondaryAttack("Razor Leaf", 40);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Vine Whip on " + target.getName() + " for 10 damage.");
        target.takeDamage(25);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Razor Leaf on " + target.getName() + " for 40 damage.");
        target.takeDamage(40);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }

}
