public class Card {
    public String tokenType;
    public int[] cost;
    public int prestige;

    // Constructor for cards with prestige points
    public Card(String tokenType, int[] cost, int prestige) {
        this.tokenType = tokenType;
        this.cost = cost;
        this.prestige = prestige;
    }

    public void display() {
        System.out.println("Gem Type: " + tokenType);
        System.out.println("Prestige: " + prestige);
        System.out.println("[Gem]\t\t" + "[Cost]");
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.cost[0]);
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.cost[1]);
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.cost[2]);
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.cost[3]);
        System.out.println(ConsoleColors.BLACK + "Onyx:\t\t" + this.cost[4]);
        System.out.println(ConsoleColors.RESET);

    }
}