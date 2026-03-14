package game;

public class Player {

    private final String name;
    private final Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public boolean play(Card card) {
        return hand.removeCard(card);
    }
}
