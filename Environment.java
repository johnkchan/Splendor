import java.util.*;

public class Environment {
    private Hashtable<String, Integer> gemTokens;
    private Noble[] nobles;
    private Player[] players;
    private Deck[] decks;
    private int turns = 0;

    public Environment(int playerCount) {
        this.initializeTokens(playerCount);
        this.initializeNobles(playerCount);
        this.initializeDecks();
        this.initializePlayers(playerCount);
        this.initializeGame(playerCount);
    }

    // initialize tokens based on # of players
    private void initializeTokens(int playerCount) {
        int tokenRemoval = 0;

        if (playerCount == 3) {
            tokenRemoval = 2;
        } else if (playerCount == 2) {
            tokenRemoval = 3;
        }

        // Add Gem Tokens Based on Amount of Players
        String[] typesOfGems = { "emerald", "sapphire", "ruby", "diamond", "onyx" };
        this.gemTokens = new Hashtable<String, Integer>();

        for (String gem : typesOfGems) {
            this.gemTokens.put(gem, (7 - tokenRemoval));
        }

        // Add 5 Gold Joker Tokens
        this.gemTokens.put("gold joker", 5);
    }

    // initialize random Nobles based on # of players
    private void initializeNobles(int playerCount) {
        Noble[] allNobles = new Noble[10];

        // Emerald, Sapphire, Ruby, Diamond, Onyx
        allNobles[0] = new Noble(new int[] { 4, 4, 0, 0, 0 });
        allNobles[1] = new Noble(new int[] { 0, 4, 4, 0, 0 });
        allNobles[2] = new Noble(new int[] { 0, 4, 0, 4, 0 });
        allNobles[3] = new Noble(new int[] { 0, 0, 4, 0, 4 });
        allNobles[4] = new Noble(new int[] { 0, 0, 0, 4, 4 });
        allNobles[5] = new Noble(new int[] { 3, 3, 3, 0, 0 });
        allNobles[6] = new Noble(new int[] { 3, 3, 0, 3, 0 });
        allNobles[7] = new Noble(new int[] { 3, 0, 3, 0, 3 });
        allNobles[8] = new Noble(new int[] { 0, 3, 0, 3, 3 });
        allNobles[9] = new Noble(new int[] { 0, 0, 3, 3, 3 });

        int noblesCount = playerCount + 1;
        this.nobles = new Noble[noblesCount];
        int[] randIntList = new int[noblesCount];

        Random rand = new Random();
        int randInt = rand.nextInt(10);
        boolean isRepeated;

        for (int i = 0; i < noblesCount; i++) {
            isRepeated = true;
            while (isRepeated) {
                randInt = rand.nextInt(10);
                isRepeated = false;

                // Check if random integer has been generated before
                for (int num : randIntList) {
                    if (num == randInt) {
                        isRepeated = true;
                    }
                }
            }
            if (!isRepeated) {
                randIntList[i] = randInt;
                this.nobles[i] = allNobles[randInt];
            }
        }
    }

    // initialize tier 1,2,3 decks
    private void initializeDecks() {
        Deck tier1 = new Deck(1);
        Deck tier2 = new Deck(2);
        Deck tier3 = new Deck(3);
    }

    // initialize players based on # of players
    private void initializePlayers(int playerCount) {
        this.players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            this.players[i] = new Player();
            this.players[i].getPrestige();
        }
    }

    public Hashtable<String, Integer> getGemTokens() {
        return this.gemTokens;
    }

    public void takeGemTokens(String token, int amnt) {
        this.gemTokens.replace(token, this.gemTokens.get(token) - amnt);
    }

    public void displayGems() {
        System.out.println("[Gem]\t\t" + "[Count]");
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.gemTokens.get("emerald"));
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.gemTokens.get("sapphire"));
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.gemTokens.get("ruby"));
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.gemTokens.get("diamond"));
        System.out.println(ConsoleColors.BLACK + "Onyx:\t\t" + this.gemTokens.get("onyx"));
        System.out.println(ConsoleColors.RESET);
    }

    public void displayNobles() {
        for (Noble nbl : this.nobles) {
            nbl.display();
        }
    }

    private void initializeGame(int playerCount) {
        boolean isEnd = false;

        while (!isEnd) {
            for (Player plyr : this.players) {
                plyr.actions(this);

                // Check if any players has accumulated 15+ prestige points
                if (plyr.getPrestige() >= 15) {
                    isEnd = true;
                }
            }

        }

    }
}