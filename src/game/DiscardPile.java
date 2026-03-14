package game;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

   private List<Card> cards = new ArrayList<Card>();

    public void add(Card card) {
        cards.add(card);
    }

    public Card top() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("La pile de defausse est vide.");
        }
        return cards.get(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    } 
}
