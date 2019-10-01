public class Card {
    private String tokenType;
    private int[] cost;
    private int prestige;

    // Constructor for cards with prestige points
    public Card(String tokenType, int[] cost, int prestige) {
        this.tokenType = tokenType;
        this.cost = cost;
        this.prestige = prestige;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public int[] getCost() {
        return this.cost;
    }

    public int getPrestige() {
        return this.prestige;
    }

    public void display() {
        System.out.println("┌───────────────────────┐");
        System.out.println("│ Token: " + tokenType);
        System.out.println("│ Prestige: " + prestige);
        System.out.println("│ [Gem Type]\t" + "[Cost]");
        System.out.println("│ " + ConsoleColors.GREEN + "Emerald:\t" + this.cost[0] + ConsoleColors.RESET);
        System.out.println("│ " + ConsoleColors.BLUE + "Sapphire:\t" + this.cost[1] + ConsoleColors.RESET);
        System.out.println("│ " + ConsoleColors.RED + "Ruby:\t\t" + this.cost[2] + ConsoleColors.RESET);
        System.out.println("│ " + ConsoleColors.WHITE + "Diamond:\t" + this.cost[3] + ConsoleColors.RESET);
        System.out.println("│ " + ConsoleColors.BLACK + "Onyx:\t\t" + this.cost[4] + ConsoleColors.RESET);
        System.out.println("└───────────────────────┘");
    }
}