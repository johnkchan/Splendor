class Noble {
    public int prestige = 3;
    // Emerald, Sapphire, Ruby, Diamond, Onyx
    public int[] cardCost;

    public Noble(int[] cardCost) {
        this.cardCost = cardCost;
    }

    public void display() {
        System.out.println("[Gem]\t\t" + "[Cost]");
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + cardCost[0]);
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + cardCost[1]);
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + cardCost[2]);
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + cardCost[3]);
        System.out.println(ConsoleColors.BLACK + "Onyx:\t\t" + cardCost[4] + ConsoleColors.RESET);
    }
}