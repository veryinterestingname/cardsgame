
import java.util.ArrayList;
import processing.core.PApplet;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void removeCard(Card card) {
        cards.remove(card);
    }
    
    public int getBlackjackTotal() {
        int total = 0;
        int aces = 0;
        for (Card c : cards) {
            total += c.getBlackjackValue();
            if (c.value.equals("A")) aces++;
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }
    
    public int getSize() { return cards.size(); }
    public ArrayList<Card> getCards() { return cards; }
    public Card getCard(int i) { return cards.get(i); }
    
    public void positionCards(int startX, int startY, int cardWidth, int cardHeight, int spacing) {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(startX + (i * spacing), startY, cardWidth, cardHeight);
        }
    }

    void draw(PApplet sketch) {
        for (Card card : cards) card.draw(sketch);
    }
}
