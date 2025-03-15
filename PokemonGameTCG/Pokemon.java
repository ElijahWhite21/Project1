import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic Pokémon card in the game. Each Pokémon has HP, base
 * attack damage, energy attachments, and type. Specific Pokémon subclasses will
 * define unique attack methods.
 */
public abstract class Pokemon extends Card {
    private int hp; // Hit points
    private List<Energy> attachedEnergies; // Energies attached to this Pokémon
    private String type; // Type of the Pokémon
    private String primaryAttackName; // Name of the primary attack
    private String secondaryAttackName; // Name of the secondary attack
    private int primaryAttackDamage; // Damage of the primary attack
    private int secondaryAttackDamage; // Damage of the secondary attack
    private Player owner; // Owner of this Pokémon

    /**
     * Constructor for a Pokémon with name, HP, and type.
     * 
     * @param name The name of the Pokémon.
     * @param hp   The hit points (HP) of the Pokémon.
     * @param type The type of the Pokémon (e.g., "Fire").
     */
    public Pokemon(String name, int hp, String type) {
        super(name); // Call the superclass constructor
        this.hp = hp; // Initialize the HP
        this.attachedEnergies = new ArrayList<>(); // Initialize the list of attached energies
        this.type = type; // Initialize the type
    }

    public int getHp() // Getter for HP
    {
        return this.hp; // Return the HP
    }

    public void takeDamage(int damage) {
        this.hp -= damage; // Subtract the damage from the HP
        if (this.hp < 0) // If the HP is negative
            this.hp = 0; // Set the HP to 0
    }

    public boolean isFainted() // Check if the Pokémon has fainted
    {
        return this.hp <= 0; // Return true if the HP is less than or equal to 0
    }

    public String getType() // Getter for type
    {
        return type; // Return the type
    }

    public void attachEnergy(Energy energyCard) // Attach an energy card to the Pokémon
    {
        attachedEnergies.add(energyCard); // Add the energy card to the list of attached energies
        System.out.println("Energy " + energyCard.getName() + " attached to " + this.getName());
    }

    public int getEnergyCount() // Get the number of attached energies
    {
        return attachedEnergies.size(); // Return the size of the list of attached energies
    }

    public void attack(Pokemon target) // Attack a target Pokémon
    {
        System.out.println(getName() + " attacks " + target.getName() + "!");
    }

    // Getters and Setters for Primary Attack
    public String getPrimaryAttackName() {
        return primaryAttackName; // Return the name of the primary attack
    }

    public void setPrimaryAttack(String primaryAttackName, int primaryAttackDamage) {
        this.primaryAttackName = primaryAttackName; // Set the name of the primary attack
        this.primaryAttackDamage = primaryAttackDamage; // Set the damage of the primary attack
    }

    public int getPrimaryAttackDamage() {
        return primaryAttackDamage; // Return the damage of the primary attack
    }

    // Getters and Setters for Secondary Attack
    public String getSecondaryAttackName() {
        return secondaryAttackName; // Return the name of the secondary attack
    }

    public void setSecondaryAttack(String secondaryAttackName, int secondaryAttackDamage) {
        this.secondaryAttackName = secondaryAttackName; // Set the name of the secondary attack
        this.secondaryAttackDamage = secondaryAttackDamage; // Set the damage of the secondary attack
    }

    public int getSecondaryAttackDamage() {
        return secondaryAttackDamage; // Return the damage of the secondary attack
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
