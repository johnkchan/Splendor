import java.util.*;

class Environment {
    public int playerCount;
    public Hashtable<String, Integer> gemTokens;
    public Noble[] nobles;

    public Environment(int playerCount) {
        this.playerCount = playerCount;
        this.initializeTokens(playerCount);
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

    private void initializeNobles(int playerCount) {
        Noble[] allNobles = new Noble[10];

        // Emerald, Sapphire, Ruby, Diamond, Onyx
        this.nobles[0] = new Noble(new int[] { 4, 4, 0, 0, 0 });
        this.nobles[1] = new Noble(new int[] { 0, 4, 4, 0, 0 });
        this.nobles[2] = new Noble(new int[] { 0, 4, 0, 4, 0 });
        this.nobles[3] = new Noble(new int[] { 0, 0, 4, 0, 4 });
        this.nobles[4] = new Noble(new int[] { 0, 0, 0, 4, 4 });
        this.nobles[5] = new Noble(new int[] { 3, 3, 3, 0, 0 });
        this.nobles[6] = new Noble(new int[] { 3, 3, 0, 3, 0 });
        this.nobles[7] = new Noble(new int[] { 3, 0, 3, 0, 3 });
        this.nobles[8] = new Noble(new int[] { 0, 3, 0, 3, 3 });
        this.nobles[9] = new Noble(new int[] { 0, 0, 3, 3, 3 });

        Random rand = new Random();
        int rand_int = rand.nextInt(10);

        boolean isRepeated = false;
        Noble[] noblesInPlay = new Nobles[playerCount + 1];
        
        while (!isRepeated) {
            rand_int = rand.nextInt(10);
        }
        Noble
       

        this.nobles = noblesInPlay;
    }

}