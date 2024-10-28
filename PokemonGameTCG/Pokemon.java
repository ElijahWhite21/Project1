import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic Pokémon card in the game. Each Pokémon has HP, base
 * attack damage, energy attachments, and type. Specific Pokémon subclasses will
 * define unique attack methods.
 */
public abstract class Pokemon extends Card {
    private int hp;
    private List<Energy> attachedEnergies;
    private String type;
    private String primaryAttackName;
    private String secondaryAttackName;
    private int primaryAttackDamage;
    private int secondaryAttackDamage;
    private Player owner;

    /**
     * Constructor for a Pokémon with name, HP, and type.
     * 
     * @param name The name of the Pokémon.
     * @param hp   The hit points (HP) of the Pokémon.
     * @param type The type of the Pokémon (e.g., "Fire").
     */
    public Pokemon(String name, int hp, String type) {
        super(name);
        this.hp = hp;
        this.attachedEnergies = new ArrayList<>();
        this.type = type;
    }

    public int getHp() {
        return this.hp;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0)
            this.hp = 0;
    }

    public boolean isFainted() {
        return this.hp <= 0;
    }

    public String getType() {
        return type;
    }

    public void attachEnergy(Energy energyCard) {
        attachedEnergies.add(energyCard);
        System.out.println("Energy " + energyCard.getName() + " attached to " + this.getName());
    }

    public int getEnergyCount() {
        return attachedEnergies.size();
    }

    public void attack(Pokemon target) {
        System.out.println(getName() + " attacks " + target.getName() + "!");
    }

    // Getters and Setters for Primary Attack
    public String getPrimaryAttackName() {
        return primaryAttackName;
    }

    public void setPrimaryAttack(String primaryAttackName, int primaryAttackDamage) {
        this.primaryAttackName = primaryAttackName;
        this.primaryAttackDamage = primaryAttackDamage;
    }

    public int getPrimaryAttackDamage() {
        return primaryAttackDamage;
    }

    // Getters and Setters for Secondary Attack
    public String getSecondaryAttackName() {
        return secondaryAttackName;
    }

    public void setSecondaryAttack(String secondaryAttackName, int secondaryAttackDamage) {
        this.secondaryAttackName = secondaryAttackName;
        this.secondaryAttackDamage = secondaryAttackDamage;
    }

    public int getSecondaryAttackDamage() {
        return secondaryAttackDamage;
    }

    /**
     * Sets the owner of this Pokémon.
     * 
     * @param owner
     */
    public void setOwner(Player owner) {

        this.owner = owner;

    }

    /**
     * Gets the owner of this Pokémon.
     * 
     * @return
     */
    public Player getOwner() {

        return owner;
    }

    /**
     * Executes the primary attack of this Pokémon on a target.
     * Each Pokémon subclass should override this with its specific attack logic.
     *
     * @param target The target Pokémon receiving the attack.
     */
    public abstract void primaryAttack(Pokemon target);

    /**
     * Executes a secondary attack of this Pokémon on a target.
     * Each Pokémon subclass should override this with its specific attack logic if
     * it has more than one attack.
     *
     * @param target The target Pokémon receiving the attack.
     */
    public abstract void secondaryAttack(Pokemon target);
}
