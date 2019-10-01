import java.util.*;

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

    public int getCount() {
        return this.count;
    }

    public void addCard(Card card) {
        this.cards[this.count] = card;
        this.count++;
    }

    public void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < this.count; i++) {

            // Random for remaining positions.
            int r = i + rand.nextInt(this.count - i);

            // swapping the elements
            Card temp = this.cards[r];
            this.cards[r] = this.cards[i];
            this.cards[i] = temp;
        }
    }

    public Card draw() {
        this.count--;
        return this.cards[this.count];
    }
}