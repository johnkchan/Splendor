import java.util.*;

public class Environment {
    private Hashtable<String, Integer> gemTokens;
    private Noble[] nobles;
    private Deck[] decks = new Deck[3];
    private Card[][] table;
    private Player[] players;

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
        String[] typesOfGems = { "diamond", "sapphire", "emerald", "ruby", "onyx" };
        this.gemTokens = new Hashtable<String, Integer>();

        for (String gem : typesOfGems) {
            this.gemTokens.put(gem, (7 - tokenRemoval));
        }

        // Add 5 Gold Joker Tokens
        this.gemTokens.put("gold joker", 5);
    }

    // initialize random Nobles based on # of players
    private void initializeNobles(int playerCount) {
        int noblesCount = playerCount + 1;
        this.nobles = new Noble[noblesCount];
        Noble[] allNobles = new Noble[10];

        // Diamond, Sapphire, Emerald, Ruby, Onyx
        int[][] nobleCosts = { { 4, 4, 0, 0, 0 }, { 0, 4, 4, 0, 0 }, { 0, 4, 0, 4, 0 }, { 4, 0, 0, 0, 4 },
                { 0, 0, 0, 4, 4 }, { 3, 3, 3, 0, 0 }, { 3, 3, 0, 0, 3 }, { 0, 3, 3, 3, 0 }, { 3, 0, 0, 3, 3 },
                { 0, 0, 3, 3, 3 } };

        for (int i = 0; i < 10; i++) {
            allNobles[i] = new Noble(nobleCosts[i]);
        }

        // Shuffle all nobles
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {

            // Random for remaining positions.
            int r = i + rand.nextInt(10 - i);

            // swapping the elements
            Noble temp = allNobles[r];
            allNobles[r] = allNobles[i];
            allNobles[i] = temp;
        }

        // Select nobles from shuffled nobles array equal to player count + 1
        for (int i = 0; i < noblesCount; i++) {
            this.nobles[i] = allNobles[i];
        }
    }

    // initialize tier 1,2,3 decks
    private void initializeDecks() {
        this.table = new Card[3][4];

        for (int i = 0; i < 3; i++) {
            this.decks[i] = new Deck(i + 1);
        }

        // Tier 1 Deck
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 1, 1, 1, 1 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 1, 2, 1, 1 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 0, 0, 2, 1 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 2, 0, 0, 2 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 3, 0, 0, 0 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 2, 2, 0, 1 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 3, 1, 0, 0, 1 }, 0));
        this.decks[0].addCard(new Card("diamond", new int[] { 0, 0, 4, 0, 0 }, 1));
        this.decks[0].addCard(new Card("sapphire", new int[] { 1, 0, 1, 1, 1 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 1, 0, 1, 2, 1 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 0, 1, 3, 1, 0 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 1, 0, 2, 2, 0 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 0, 0, 2, 0, 2 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 1, 0, 0, 0, 2 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 0, 0, 0, 0, 3 }, 0));
        this.decks[0].addCard(new Card("sapphire", new int[] { 0, 0, 0, 4, 0 }, 1));
        this.decks[0].addCard(new Card("emerald", new int[] { 1, 1, 0, 1, 1 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 1, 1, 0, 1, 2 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 1, 3, 1, 0, 0 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 0, 1, 0, 2, 2 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 0, 2, 0, 2, 0 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 2, 1, 0, 0, 0 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 0, 0, 0, 3, 0 }, 0));
        this.decks[0].addCard(new Card("emerald", new int[] { 0, 0, 0, 0, 4 }, 1));
        this.decks[0].addCard(new Card("ruby", new int[] { 1, 1, 1, 0, 1 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 2, 1, 1, 0, 1 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 1, 0, 0, 1, 3 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 2, 0, 0, 2, 0 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 0, 2, 1, 0, 0 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 3, 0, 0, 0, 0 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 2, 0, 1, 0, 2 }, 0));
        this.decks[0].addCard(new Card("ruby", new int[] { 4, 0, 0, 0, 0 }, 1));
        this.decks[0].addCard(new Card("onyx", new int[] { 1, 2, 1, 1, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 1, 1, 1, 1, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 0, 0, 1, 3, 1 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 2, 2, 0, 1, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 2, 0, 2, 0, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 0, 0, 2, 1, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 0, 0, 3, 0, 0 }, 0));
        this.decks[0].addCard(new Card("onyx", new int[] { 0, 4, 0, 0, 0 }, 1));

        // Tier 2 Deck
        this.decks[1].addCard(new Card("diamond", new int[] { 0, 0, 3, 2, 2 }, 1));
        this.decks[1].addCard(new Card("diamond", new int[] { 2, 3, 0, 3, 0 }, 1));
        this.decks[1].addCard(new Card("diamond", new int[] { 0, 0, 1, 4, 2 }, 2));
        this.decks[1].addCard(new Card("diamond", new int[] { 0, 0, 0, 5, 3 }, 2));
        this.decks[1].addCard(new Card("diamond", new int[] { 0, 0, 0, 5, 0 }, 2));
        this.decks[1].addCard(new Card("diamond", new int[] { 6, 0, 0, 0, 0 }, 3));
        this.decks[1].addCard(new Card("sapphire", new int[] { 0, 2, 2, 3, 0 }, 1));
        this.decks[1].addCard(new Card("sapphire", new int[] { 0, 2, 3, 0, 3 }, 1));
        this.decks[1].addCard(new Card("sapphire", new int[] { 2, 0, 0, 1, 4 }, 2));
        this.decks[1].addCard(new Card("sapphire", new int[] { 5, 3, 0, 0, 0 }, 2));
        this.decks[1].addCard(new Card("sapphire", new int[] { 0, 5, 0, 0, 0 }, 2));
        this.decks[1].addCard(new Card("sapphire", new int[] { 0, 6, 0, 0, 0 }, 3));
        this.decks[1].addCard(new Card("emerald", new int[] { 2, 3, 0, 0, 2 }, 1));
        this.decks[1].addCard(new Card("emerald", new int[] { 3, 0, 2, 3, 0 }, 1));
        this.decks[1].addCard(new Card("emerald", new int[] { 4, 2, 0, 0, 1 }, 2));
        this.decks[1].addCard(new Card("emerald", new int[] { 0, 5, 3, 0, 0 }, 2));
        this.decks[1].addCard(new Card("emerald", new int[] { 0, 5, 0, 0, 0 }, 2));
        this.decks[1].addCard(new Card("emerald", new int[] { 0, 0, 6, 0, 0 }, 3));
        this.decks[1].addCard(new Card("ruby", new int[] { 2, 0, 0, 2, 3 }, 1));
        this.decks[1].addCard(new Card("ruby", new int[] { 0, 3, 0, 2, 3 }, 1));
        this.decks[1].addCard(new Card("ruby", new int[] { 1, 4, 2, 0, 0 }, 2));
        this.decks[1].addCard(new Card("ruby", new int[] { 3, 0, 0, 0, 5 }, 2));
        this.decks[1].addCard(new Card("ruby", new int[] { 0, 0, 0, 0, 5 }, 2));
        this.decks[1].addCard(new Card("ruby", new int[] { 0, 0, 0, 6, 0 }, 3));
        this.decks[1].addCard(new Card("onyx", new int[] { 3, 2, 2, 0, 0 }, 1));
        this.decks[1].addCard(new Card("onyx", new int[] { 3, 0, 3, 0, 2 }, 1));
        this.decks[1].addCard(new Card("onyx", new int[] { 0, 1, 4, 2, 0 }, 2));
        this.decks[1].addCard(new Card("onyx", new int[] { 0, 0, 5, 3, 0 }, 2));
        this.decks[1].addCard(new Card("onyx", new int[] { 5, 0, 0, 0, 0 }, 2));
        this.decks[1].addCard(new Card("onyx", new int[] { 0, 0, 0, 0, 6 }, 3));

        // Tier 3 Deck
        this.decks[2].addCard(new Card("diamond", new int[] { 0, 3, 3, 5, 3 }, 3));
        this.decks[2].addCard(new Card("diamond", new int[] { 3, 0, 0, 3, 6 }, 4));
        this.decks[2].addCard(new Card("diamond", new int[] { 0, 0, 0, 0, 7 }, 4));
        this.decks[2].addCard(new Card("diamond", new int[] { 3, 0, 0, 0, 7 }, 5));
        this.decks[2].addCard(new Card("sapphire", new int[] { 3, 0, 3, 3, 5 }, 3));
        this.decks[2].addCard(new Card("sapphire", new int[] { 6, 3, 0, 0, 3 }, 4));
        this.decks[2].addCard(new Card("sapphire", new int[] { 7, 0, 0, 0, 0 }, 4));
        this.decks[2].addCard(new Card("sapphire", new int[] { 7, 3, 0, 0, 0 }, 5));
        this.decks[2].addCard(new Card("emerald", new int[] { 5, 3, 0, 3, 3 }, 3));
        this.decks[2].addCard(new Card("emerald", new int[] { 3, 6, 3, 0, 0 }, 4));
        this.decks[2].addCard(new Card("emerald", new int[] { 0, 7, 0, 0, 0 }, 4));
        this.decks[2].addCard(new Card("emerald", new int[] { 0, 7, 3, 0, 0 }, 5));
        this.decks[2].addCard(new Card("ruby", new int[] { 3, 5, 3, 0, 3 }, 3));
        this.decks[2].addCard(new Card("ruby", new int[] { 0, 3, 6, 3, 0 }, 4));
        this.decks[2].addCard(new Card("ruby", new int[] { 0, 0, 7, 0, 0 }, 4));
        this.decks[2].addCard(new Card("ruby", new int[] { 0, 0, 7, 3, 0 }, 5));
        this.decks[2].addCard(new Card("onyx", new int[] { 3, 3, 5, 3, 0 }, 3));
        this.decks[2].addCard(new Card("onyx", new int[] { 0, 3, 0, 6, 3 }, 4));
        this.decks[2].addCard(new Card("onyx", new int[] { 0, 0, 0, 7, 0 }, 4));
        this.decks[2].addCard(new Card("onyx", new int[] { 0, 0, 0, 7, 3 }, 5));

        // Shuffle & Select Top 4 Cards from each Deck
        for (int i = 0; i < 3; i++) {
            this.decks[i].shuffle();
            for (int j = 0; j < 4; j++) {
                this.table[i][j] = this.decks[i].draw();
            }
        }
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
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.gemTokens.get("diamond"));
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.gemTokens.get("sapphire"));
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.gemTokens.get("emerald"));
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.gemTokens.get("ruby"));
        System.out.println(ConsoleColors.BLACK + "Onyx:\t\t" + this.gemTokens.get("onyx"));
        System.out.println(ConsoleColors.RESET);
    }

    public void displayNobles() {
        for (Noble nbl : this.nobles) {
            nbl.display();
        }
    }

    public void displayTable() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Tier " + this.decks[i].getTier());
            for (int j = 0; j < 4; j++) {
                this.table[i][j].display();
            }
        }
    }

    private void initializeGame(int playerCount) {
        boolean isEnd = false;

        while (!isEnd) {
            for (Player player : this.players) {
                this.displayTable();
                player.actions(this);
                player.incrementTurns();
                // Check if any players has accumulated 15+ prestige points
                if (player.getPrestige() >= 15) {
                    isEnd = true;
                }
            }

        }

    }
}