public class ProfessorsResearch extends Trainer {

    public ProfessorsResearch() {
        super("Professor's Research", "Discard your hand and draw 7 cards.");
    }

    @Override
    public void play(Player player1, Player player2) {
        System.out.println(player1.getName() + " uses Professor's Research!");
        player1.discardHandAndDraw(7); // Discard hand and draw 7 new cards
    }
}
