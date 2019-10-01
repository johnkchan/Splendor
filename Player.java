import java.util.*;

public class Player {
    private int playerNum;
    private int prestige = 0;
    private int[] gemTokens = { 0, 0, 0, 0, 0, 0 };
    private int[] cardTokens = { 0, 0, 0, 0, 0 };
    private Card[] reserve = new Card[3];
    private int reserveCount = 0;
    private int turns = 0;

    public Player(int num) {
        this.playerNum = num;
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
        String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
        int index = -1;

        for (int i = 0; i < 6; i++) {
            if (token.equals(gemTypes[i])) {
                index = i;
            }
        }

        this.gemTokens[index] += amnt;
    }

    public int getTurns() {
        return this.turns;
    }

    public void incrementTurns() {
        this.turns++;
    }

    public void displayTokens() {
        System.out.println(ConsoleColors.WHITE_BOLD + "Player " + playerNum + " Inventory:");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(ConsoleColors.YELLOW + "[Gem Types]\t[Tokens]\t[Dev. Cards]\t[Total]");
        System.out.println(ConsoleColors.WHITE + "Diamond:\t\t" + this.gemTokens[0] + "\t\t" + this.cardTokens[0]
                + "\t\t" + (this.gemTokens[0] + this.cardTokens[0]));
        System.out.println(ConsoleColors.BLUE + "Sapphire:\t" + this.gemTokens[1] + "\t\t" + this.cardTokens[1] + "\t\t"
                + (this.gemTokens[1] + this.cardTokens[0]));
        System.out.println(ConsoleColors.GREEN + "Emerald:\t\t" + this.gemTokens[2] + "\t\t" + this.cardTokens[2]
                + "\t\t" + (this.gemTokens[2] + this.cardTokens[0]));
        System.out.println(ConsoleColors.RED + "Ruby:\t\t" + this.gemTokens[3] + "\t\t" + this.cardTokens[3] + "\t\t"
                + (this.gemTokens[3] + this.cardTokens[0]));
        System.out.println(ConsoleColors.PURPLE + "Onyx:\t\t" + this.gemTokens[4] + "\t\t" + this.cardTokens[4] + "\t\t"
                + (this.gemTokens[4] + this.cardTokens[0]));
        System.out.println(
                ConsoleColors.YELLOW + "Gold:\t\t" + this.gemTokens[5] + "\t\t" + "N/A" + "\t\t" + this.gemTokens[5]);
        System.out.println(ConsoleColors.RESET);
    }

    public void actions(Environment env) {
        System.out.println("Player " + playerNum + " Actions:");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(
                "[1] Take 3 gem tokens of different colors.\t[3] Reserve 1 development card and take 1 gold token (joker).");
        System.out.println(
                "[2] Take 2 gem tokens of the same color.\t[4] Purchase 1 face-up development card from the middle of the table or a previously reserved one.");

        String action;
        String token;
        Card selectedCard;
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

            if (!isValidAction) {
                System.out.println("Please Enter Valid Action: 1, 2, 3, 4");
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

                    // Warn user if requested gem type is not valid
                    for (String tkn : tokens) {
                        if (token.equals(tkn)) {
                            System.out.println("Enter 3 Unique Gem Types: Diamond, Sapphire, Emerald, Ruby, Onyx");
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

                // Warn user if requested gem type is not valid
                if (availableGems.get(token) <= 3) {
                    System.out.println("Selected Gem Has Less Than 4 Tokens Available, Reselect Different Gem Type");
                }
            } while (!this.isValidToken(token) || availableGems.get(token) <= 3);

            takeTwoTokens(token, env);
            break;
        case "3":
            // Check if player has less than 3 reserved cards
            if (this.reserveCount < 3) {
                System.out.println("Reservation Limit Has Been Reached");
            } else {
                // reserveDevelopmentCard(card, env);
            }
            break;
        case "4":
            break;
        }
    }

    // Helper Function to Validate Token Input
    private boolean isValidToken(String token) {
        String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
        boolean isValidToken = false;
        for (String gem : gemTypes) {
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

    private void reserveDevelopmentCard(Card card, Environment env) {
        this.reserve[this.reserveCount] = card;
        this.reserveCount++;

        if (env.getGemTokens().get("gold joker") >= 1) {
            this.addToken(1, "gold joker");
            env.takeGemTokens(1, "gold joker");
        }
    }

    private void purchaseDevelopmentCard(Card card, Environment env) {
        String[] gemTypes = { "diamond", "sapphire", "emerald", "ruby", "onyx", "gold joker" };
        String tokenType = card.getTokenType();
        int[] cost = card.getCost();
        int index;

        // Deduct cost from player's inventory
        for (int i = 0; i < 5; i++) {
            this.gemTokens[i] -= (cost[i] - this.cardTokens[i]);
        }

        // Check if gold joker tokens are required to complete purchase
        this.gemTokens[5] -= 0;

        index = -1;
        for (int i = 0; i < 6; i++) {
            if (tokenType.equals(gemTypes[i])) {
                index = i;
            }
        }

        this.prestige += card.getPrestige();
        this.cardTokens[index] += 1;
    }
}