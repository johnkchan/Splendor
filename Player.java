import java.util.*;

public class Player {
    private int prestige;
    private String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
    private Hashtable<String, Integer> gemTokens;
    private Hashtable<String, Integer> cardTokens;
    private Card[] reserve;
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

    public void addToken(int amnt, String token) {
        this.gemTokens.replace(token, this.gemTokens.get(token) + amnt);
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

        if (action == "1") {
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
        } else if (action == "2") {
            do {
                System.out.print("Gem Type: ");
                token = System.console().readLine().toLowerCase();
            } while (!this.isValidToken(token) || availableGems.get(token) <= 3);

            takeTwoTokens(token, env);
        } else if (action == "3") {
            reserveDevelopmentCard(env);
        } else if (action == "4") {

        }

        env.displayGems();
    }

    // Helper Function to Validate Token Input
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
            this.addToken(1, token);
            env.takeGemTokens(1, token);
        }
    }

    public void takeTwoTokens(String token, Environment env) {
        this.addToken(2, token);
        env.takeGemTokens(2, token);
    }

    private void reserveDevelopmentCard(Environment env) {
        // TODO: Add reserve functionality
        if (env.getGemTokens().get("gold joker") >= 1) {
            this.addToken(1, "gold joker");
            env.takeGemTokens(1, "gold joker");
        }
    }

    private void purchaseDevelopmentCard(Environment env) {
        // TODO: Implement Function
    }
}