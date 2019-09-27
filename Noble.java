public class Noble {
    public int prestige = 3;
    private int[] cardCost;

    // Emerald, Sapphire, Ruby, Diamond, Onyx
    public Noble(int[] cardCost) {
        this.cardCost = cardCost;
    }

    public int getPrestige() {
        return this.prestige;
    }

    public void display() {
        System.out.println("[Gem]\t\t" + "[Cost]");
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.cardCost[0]);
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.cardCost[1]);
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.cardCost[2]);
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.cardCost[3]);
        System.out.println(ConsoleColors.BLACK + "Onyx:\t\t" + this.cardCost[4]);
        System.out.println(ConsoleColors.RESET);

    }
}