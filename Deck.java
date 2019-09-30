import java.util.*;

// TODO: Make Deck into Stack Data Structure
public class Deck {
    private int tier;
    private int count = 0;
    private Card[] cards;

    public Deck(int tier) {
        this.tier = tier;

        if (tier == 1) {
            this.cards = new Card[40];
        } else if (tier == 2) {
            this.cards = new Card[30];
        } else if (tier == 3) {
            this.cards = new Card[20];
        }
    }

    public int getTier() {
        return this.tier;
    }

    public void shuffle() {
        Random rand = new Random();
        int randInt = rand.nextInt(10);
    }

    public void addCard(Card[] card) {
        this.cards[count] = card;
        count++;
    }

}