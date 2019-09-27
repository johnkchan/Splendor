class Card {
    public int prestige;
    public int tier;
    public int[] cost;
    public string tokenType;

    public Card(int prestige, int tier, int[] cost, string tokenType) {
        this.prestige = prestige;
        this.tier = tier;
        this.cost = cost;
        this.tokenType = tokenType;
    }

}