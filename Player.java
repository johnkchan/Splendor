import java.util.*;

public class Player {
    private int prestige;
    private String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
    private Hashtable<String, Integer> gemTokens;
    private Hashtable<String, Integer> developmentTokens;
    private Card[] reserve = new Card[3];
    private int turns = 0;

    public Player() {
        this.prestige = 0;

        this.gemTokens = new Hashtable<String, Integer>();
        for (String gem : this.gemTypes) {
            this.gemTokens.put(gem, 0);
        }

        this.developmentTokens = new Hashtable<String, Integer>();
        for (String gem : this.gemTypes) {
            this.developmentTokens.put(gem, 0);
        }
    }

    public int getPrestige() {
        return this.prestige;
    }

    public void addPrestige(int prestige) {
        this.prestige += prestige;
    }

    public void getGemTokens() {
        System.out.println(this.gemTokens);
    }

    public void addToken(int amnt, String token) {
        this.gemTokens.replace(token, this.gemTokens.get(token) + amnt);
    }

    public int getTurns() {
        return this.turns;
    }

    public void incrementTurns() {
        turns++;
    }

    public void displayTokens() {
        System.out.println(ConsoleColors.WHITE_BOLD + "Player Inventory:");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(ConsoleColors.YELLOW + "[Gem Types]\t[Tokens]\t[Dev. Cards]\t[Total]");
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.gemTokens.get("diamond") + "\t\t"
                + this.developmentTokens.get("diamond") + "\t\t"
                + (this.gemTokens.get("diamond") + this.developmentTokens.get("diamond")));
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.gemTokens.get("sapphire") + "\t\t"
                + this.developmentTokens.get("sapphire") + "\t\t"
                + (this.gemTokens.get("sapphire") + this.developmentTokens.get("sapphire")));
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.gemTokens.get("emerald") + "\t\t"
                + this.developmentTokens.get("emerald") + "\t\t"
                + (this.gemTokens.get("emerald") + this.developmentTokens.get("emerald")));
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.gemTokens.get("ruby") + "\t\t"
                + this.developmentTokens.get("ruby") + "\t\t"
                + (this.gemTokens.get("ruby") + this.developmentTokens.get("ruby")));
        System.out.println(ConsoleColors.PURPLE + "Onyx:\t\t" + this.gemTokens.get("onyx") + "\t\t"
                + this.developmentTokens.get("onyx") + "\t\t"
                + (this.gemTokens.get("onyx") + this.developmentTokens.get("onyx")));
        System.out.println(ConsoleColors.YELLOW + "Gold:\t\t" + this.gemTokens.get("gold joker") + "\t\t" + "N/A"
                + "\t\t" + this.gemTokens.get("gold joker"));
        System.out.println(ConsoleColors.RESET);
    }

    public void actions(Environment env) {
        System.out.println("Available Actions:");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(
                "[1] Take 3 gem tokens of different colors.\t[3] Reserve 1 development card and take 1 gold token (joker).");
        System.out.println(
                "[2] Take 2 gem tokens of the same color.\t[4] Purchase 1 face-up development card from the middle of the table or a previously reserved one.");

        String action;
        String token;
        String[] validActions = { "1", "2", "3", "4" };
        Hashtable<String, Integer> availableGems = env.getGemTokens();
        boolean isValidAction = false;

        do {
            System.out.print("Enter Action #: ");
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
            // Check if Gem input has at least 4 Tokens
            do {
                System.out.print("Gem Type: ");
                token = System.console().readLine().toLowerCase();
            } while (!this.isValidToken(token) || availableGems.get(token) <= 3);

            takeTwoTokens(token, env);
            break;
        case "3":
            reserveDevelopmentCard(env);
            break;
        case "4":
            break;
        }
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

    private void purchaseDevelopmentCard(Card card, Environment env) {
        // TODO: Implement Function

    }
}