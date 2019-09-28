public class Card {
    public int[] cost;
    public String tokenType;
    public int prestige = 0;

    // Constructor for cards with prestige points
    public Card(int[] cost, String tokenType, int prestige) {
        this.cost = cost;
        this.tokenType = tokenType;
        this.prestige = prestige;
    }

    // Constructor for cards without prestige points
    public Card(int[] cost, String tokenType) {
        this.cost = cost;
        this.tokenType = tokenType;
    }
}