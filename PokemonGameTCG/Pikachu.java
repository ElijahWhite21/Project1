public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", 50, "Lightning");
        setPrimaryAttack("ThunderShock", 35);
        setSecondaryAttack("Thunderbolt", 30);
    }

    @Override
    public void primaryAttack(Pokemon target) {
        System.out.println(getName() + " uses ThunderShock on " + target.getName() + " for 35 damage.");
        target.takeDamage(35);
    }

    @Override
    public void secondaryAttack(Pokemon target) {
        System.out.println(getName() + " uses Thunderbolt on " + target.getName() + " for 30 damage.");
        target.takeDamage(30);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is now in play!");
    }
}
