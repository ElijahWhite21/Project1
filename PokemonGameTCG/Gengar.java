public class Gengar extends Pokemon {
    public Gengar() {
        super("Gengar", 80, "Psychic");
        setPrimaryAttack("Night Attack", 40);
        setSecondaryAttack("Dark Corridor", 50);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Night Attack on " + target.getName() + " for 40 damage.");
        target.takeDamage(40);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Dark Corridor on " + target.getName() + " for 50 damage.");
        target.takeDamage(50);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }

}
