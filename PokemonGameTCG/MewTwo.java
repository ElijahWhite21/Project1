public class MewTwo extends Pokemon {

    public MewTwo() {
        super("MewTwo", 130, "Psychic");
        setPrimaryAttack("Ancient Power", 40);
        setSecondaryAttack("Psychic Cut", 35);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Anchient Power on " + target.getName() + " for 40 damage.");
        target.takeDamage(40);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Psychic Cut on " + target.getName() + " for 35 damage.");
        target.takeDamage(35);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
