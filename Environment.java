import java.util.*;

public class Environment {
    private Hashtable<String, Integer> gemTokens;
    private Noble[] nobles;
    private Player[] players;
    private int turns = 0;

    public Environment(int playerCount) {
        this.initializeTokens(playerCount);
        this.initializeNobles(playerCount);
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
        String[] typesOfGems = { "Emerald", "Sapphire", "Ruby", "Diamond", "Onyx" };
        this.gemTokens = new Hashtable<String, Integer>();

        for (String gem : typesOfGems) {
            this.gemTokens.put(gem, (7 - tokenRemoval));
        }

        // Add 5 Gold Joker Tokens
        this.gemTokens.put("Gold Joker", 5);
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

    // initialize players based on # of players
    private void initializePlayers(int playerCount) {
        this.players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            this.players[i] = new Player();
            this.players[i].getPrestige();
        }
    }

    public HashTable<String, Integer> getGemTokens() {
        return gemTokens;
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