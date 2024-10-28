import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Card> hand;
    private List<Card> prizePile; // Track prize cards
    private List<Pokemon> bench = new ArrayList<>(); // Track benched Pokémon
    private Pokemon activePokemon;
    private List<Card> discardPile; // Track discarded cards
    private List<Card> deck; // Reference to the deck

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to initialize a player with a specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.prizePile = new ArrayList<>(); // Ensure prizePile is initialized here
    }

    /**
     * @param card
     */
    public void drawCard(Card card) {
        hand.add(card);
    }

    /**
     * @param card
     */
    public void addPrizeCard(Card card) {
        prizePile.add(card); // Add prize card to initialized prizePile
    }

    /**
     * Claim a prize card from the prize pile and add it to the player's hand.
     */
    public void claimPrizeCard() {
        if (!prizePile.isEmpty()) {
            Card prizeCard = prizePile.remove(0);
            this.hand.add(prizeCard);
            System.out.println(name + " claimed a prize card: " + prizeCard.getName());
        } else {
            System.out.println("No prize cards left to claim!");
        }
    }

    /**
     * @return
     */
    public int getRemainingPrizeCards() {
        return this.prizePile.size();
    }

    /**
     * @param The deck to draw cards from to fill the player's hand and prize pile
     *            with cards
     */
    public void drawPrizePile(List<Card> deck) {
        for (int i = 0; i < 6; i++) {
            if (!deck.isEmpty()) {
                this.prizePile.add(deck.remove(0));
            }
        }
    }

    /**
     * @param The card to play from the player's hand
     */
    public void playCard(Card card) {
        card.play();
        if (card instanceof Pokemon) {
            setActivePokemon((Pokemon) card);
        }
        this.hand.remove(card);
    }

    /**
     * @param opponent player to attack with the player's active Pokémon against
     *                 their active Pokémon
     * @return
     */
    public boolean attack(Player opponent) {

        Pokemon opponentActivePokemon = opponent.getActivePokemon();
        Pokemon playerActivePokemon = this.getActivePokemon();

        if (opponentActivePokemon != null && playerActivePokemon != null) {
            System.out.println("Choose an attack:");
            System.out.println("1. Primary Attack" + " " + getActivePokemon().getPrimaryAttackName() + " "
                    + getActivePokemon().getPrimaryAttackDamage() + " " + "damage");
            System.out.println("2. Secondary Attack" + " " + getActivePokemon().getSecondaryAttackName() + " "
                    + getActivePokemon().getSecondaryAttackDamage() + " " + "damage");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    this.activePokemon.primaryAttack(opponent.getActivePokemon());
                    System.out.println(opponent.getActivePokemon().getName() + "'s remaining HP: "
                            + opponent.getActivePokemon().getHp());
                }
                case 2 -> {
                    this.activePokemon.secondaryAttack(opponent.getActivePokemon());
                    System.out.println(opponent.getActivePokemon().getName() + "'s remaining HP:/n "
                            + opponent.getActivePokemon().getHp());
                }
                default -> System.out.println("Invalid choice.");
            }

            if (opponent.getActivePokemon().isFainted()) {
                System.out.println(opponent.getActivePokemon().getName() + " has fainted!");
                opponent.benchActivePokemon();
                // claimPrizeCard();
                return true;
            }
        } else {
            System.out.println("No active Pokémon available to attack!");
        }
        return false;
    }

    public void attachEnergy(Energy energyCard) {
        if (this.activePokemon != null) {
            this.activePokemon.attachEnergy(energyCard);
            System.out.println(name + " attached " + energyCard.getName() + " to " + this.activePokemon.getName());
        } else {
            System.out.println("No active Pokémon to attach energy to!");
        }
    }

    public void benchActivePokemon() {
        if (this.activePokemon != null) {
            hand.add(this.activePokemon);
            System.out.println(activePokemon.getName() + " has been eliminated from the game\n");
            this.activePokemon = null;
        }
    }

    public void setActivePokemon(Pokemon newActive) {
        if (this.activePokemon != null) {
            this.hand.add(this.activePokemon);
        }
        this.activePokemon = newActive;
        this.hand.remove(newActive);
        System.out.println(newActive.getName() + " is now the active Pokémon");
    }

    public boolean hasBenchedPokemon() {
        return !bench.isEmpty();
    }

    public void addToBench(Pokemon pokemon) {
        bench.add(pokemon);
    }

    public void switchActiveWithBenched() {
        if (!hasBenchedPokemon()) {
            System.out.println("No benched Pokémon to switch with.");
            return;
        }

        // Swap the active Pokémon with a random one on the bench
        Pokemon benchedPokemon = bench.remove(0);
        if (activePokemon != null) {
            bench.add(activePokemon);
        }
        activePokemon = benchedPokemon;

        System.out.println("Switched active Pokémon with " + activePokemon.getName());
    }

    /**
     * Discard the player's hand and draw a specified number of cards from the deck.
     * 
     * @param numCards
     */
    public void discardHandAndDraw(int numCards) {
        // Move all cards in hand to the discard pile
        discardPile.addAll(hand);
        hand.clear();

        // Draw the specified number of cards from the deck
        for (int i = 0; i < numCards && !deck.isEmpty(); i++) {
            Card drawnCard = deck.remove(0);
            hand.add(drawnCard);
            System.out.println(name + " drew a card: " + drawnCard.getName());
        }

        System.out.println(name + " discarded their hand and drew " + numCards + " new cards.");
    }

    public Pokemon getActivePokemon() {
        return this.activePokemon;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public String getName() {
        return name;
    }

    public boolean hasWon() {
        return prizePile.isEmpty();
    }

    /**
     * @param newActivePokemon
     */
    public void retreatActivePokemon(Pokemon newActivePokemon) {

        if (this.activePokemon != null) {

            this.bench.add(this.activePokemon); // Move current active Pokémon to the bench

        }

        this.activePokemon = newActivePokemon; // Set the new active Pokémon

        this.hand.remove(newActivePokemon); // Remove the new active Pokémon from the hand

    }
}
