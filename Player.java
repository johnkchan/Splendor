import java.util.*;

public class Player {
    private int prestige;
    private String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
    private Hashtable<String, Integer> gemTokens;
    private Hashtable<String, Integer> cardTokens;
    private int turns = 0;

    public Player() {
        this.prestige = 0;

        this.gemTokens = new Hashtable<String, Integer>();
        for (String gem : this.gemTypes) {
            this.gemTokens.put(gem, 0);
        }

        this.cardTokens = new Hashtable<String, Integer>();
        for (String gem : this.gemTypes) {
            this.cardTokens.put(gem, 0);
        }
    }

    public int getPrestige() {
        return this.prestige;
    }

    public void addPrestige(int prestige) {
        this.prestige += prestige;
    }

    public void getGemCount() {
        System.out.println(this.gemTokens);
    }

    public int getTurns() {
        return this.turns;
    }

    public void incrementTurns() {
        turns++;
    }

    public void actions(Environment env) {
        System.out.println(
                "============================================================================================================");
        System.out.println("Available Actions:");
        System.out.println(
                "============================================================================================================");
        System.out.println("[1] Take 3 gem tokens of different colors.");
        System.out.println("[2] Take 2 gem tokens of the same color.");
        System.out.println("[3] Reserve 1 development card and take 1 gold token (joker).");
        System.out.println(
                "[4] Purchase 1 face-up development card from the middle of the table or a previously reserved one.");

        String action;
        String token;
        String[] validActions = { "1", "2", "3", "4" };
        Hashtable<String, Integer> availableGems = env.getGemTokens();
        boolean isValidAction = false;

        do {
            System.out.print("Please Enter Action #: ");
            action = System.console().readLine();

            for (String validAction : validActions) {
                if (action.equals(validAction)) {
                    isValidAction = true;
                }
            }
        } while (!isValidAction);

        switch (action) {
        case "1":
            String[] tokens = new String[3];
            boolean isRepeated;

            for (int i = 0; i < 3; i++) {
                do {
                    System.out.print("Gem Type #" + (i + 1) + ": ");
                    token = System.console().readLine().toLowerCase();
                    isRepeated = false;

                    for (String tkn : tokens) {
                        if (token.equals(tkn)) {
                            System.out.println("Please Enter 3 Unique Gem Types.");
                            isRepeated = true;
                        }
                    }
                } while (!this.isValidToken(token) || isRepeated);
                tokens[i] = token;
            }
            takeThreeTokens(tokens, env);
            break;
        case "2":
            do {
                System.out.print("Gem Type: ");
                token = System.console().readLine().toLowerCase();
            } while (!this.isValidToken(token) || availableGems.get(token) <= 3);

            takeTwoTokens(token, env);
            break;
        case "3":
            break;
        case "4":
            break;
        default:
            // System.out.Println("Invalid action");
        }

        env.displayGems();

    }

    // Helper function to validate token input
    private boolean isValidToken(String token) {
        boolean isValidToken = false;
        for (String gem : this.gemTypes) {
            if (token.equals(gem.toLowerCase())) {
                isValidToken = true;
            }
        }
        return isValidToken;
    }

    public void takeThreeTokens(String[] tokens, Environment env) {
        for (String token : tokens) {
            this.gemTokens.replace(token, this.gemTokens.get(token) + 1);
            env.takeGemTokens(token, 1);
        }
    }

    public void takeTwoTokens(String token, Environment env) {
        this.gemTokens.replace(token, this.gemTokens.get(token) + 2);
        env.takeGemTokens(token, 2);
    }
}