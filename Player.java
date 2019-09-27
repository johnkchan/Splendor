import java.util.*;

public class Player {
    private int prestige;
    private String[] gemTypes = { "Emerald", "Sapphire", "Ruby", "Diamond", "Onyx", "Gold Joker" };
    private Hashtable<String, Integer> gemTokens;
    private Hashtable<String, Integer> cardTokens;

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
        // do {
        System.out.print("Please Enter Action #: ");
        action = System.console().readLine();

        // if (action != "1" && action != "2" && action != "3" && action != "4") {
        // System.out.println("Invalid Action");
        // }
        // } while (action != "1" && action != "2" && action != "3" && action != "4");

        switch (action) {
        case "1":
            // do {
            // System.out.println("Gem Type #1: ");
            // String token1 = System.console().readLine();
            // System.out.println("Gem Type #2: ");
            // String token2 = System.console().readLine();
            // System.out.println("Gem Type #3: ");
            // String token3 = System.console().readLine();
            // } while (token1 == token2 || token1 == token3 || token2 == token3);
            // takeThreeTokens(token1, token2, token3);
            break;
        case "2":
            String token;
            // System.out.print("Gem Type: ");
            // token = System.console().readLine();
            boolean isValidToken = false;
            do {
                System.out.print("Gem Type: ");
                token = System.console().readLine();
                for (String gem : this.gemTypes) {
                    if (token == gem) {
                        isValidToken = true;
                    }
                }
            } while (!isValidToken);

            takeTwoTokens(token);
            break;
        case "3":
            break;
        case "4":
            break;
        default:
            // System.out.Println("Invalid action");
        }
    }

    public boolean takeThreeTokens(String token1, String token2, String token3) {
        this.gemTokens.replace(token, this.gemTokens.get(token1) + 1);
        this.gemTokens.replace(token, this.gemTokens.get(token2) + 1);
        this.gemTokens.replace(token, this.gemTokens.get(token3) + 1);
        return true;
    }

    public void takeTwoTokens(String token) {
        this.gemTokens.replace(token, this.gemTokens.get(token) + 2);
        System.out.println("2 " + token + " tokens have been added.");
    }
}