public class Noble {
    public int prestige = 3;
    private int[] cardCost;

    // Diamond, Sapphire, Emerald, Ruby, Onyx
    public Noble(int[] cardCost) {
        this.cardCost = cardCost;
    }

    public int getPrestige() {
        return this.prestige;
    }

    public int[] getCardCost() {
        return this.cardCost;
    }
}