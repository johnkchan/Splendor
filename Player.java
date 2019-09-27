import java.util.*;

public class Player {
    private int prestige;
    public Hashtable<String, Integer> gemTokens;
    public Hashtable<String, Integer> cardTokens;

    public Player() {
        this.prestige = 0;

        String[] typesOfGems = { "Emerald", "Sapphire", "Ruby", "Diamond", "Onyx", "Gold Joker" };

        this.gemTokens = new Hashtable<String, Integer>();
        for (String gem : typesOfGems) {
            this.gemTokens.put(gem, 0);
        }

        this.cardTokens = new Hashtable<String, Integer>();
        for (String gem : typesOfGems) {
            this.cardTokens.put(gem, 0);
        }
    }

    public int getPrestige() {
        return this.prestige;
    }

    public void getGemCount() {
        System.out.println(this.gemTokens);
    }

    public void actions() {
        System.out.println("Available Actions:");
        System.out.println("[1] Take 3 gem tokens of different colors.");
        System.out.println("[2] Take 2 gem tokens of the same color.");
        System.out.println("[3] Reserve 1 development card and take 1 gold token (joker).");
        System.out.println(
                "[4] Purchase 1 face-up development card from the middle of the table or a previously reserved one.");

        String action;
        do {
            System.out.print("Please Enter Action #: ");
            action = System.console().readLine();

            if (action != "1" && action != "2" && action != "3" && action != "4") {
                System.out.println("Invalid Action");
            }
        } while (action != "1" && action != "2" && action != "3" && action != "4");

        switch (action) {
        case "1":
            String token1 = System.console().readLine();
            String token2 = System.console().readLine();
            String token3 = System.console().readLine();
            takeThreeTokens(token1, token2, token3);
        case "2":
            String token = System.console().readLine();
            takeTwoTokens(token);
        case "3":
        case "4":
        default:
            System.Out.Println("Invalid action");
        }
    }

    public void takeTwoTokens(String token) {
        this.gemTokens.replace(token, this.gemTokens.get(token) + 2);
    }

    public boolean takeThreeTokens(String token1, String token2, String token3) {
        if (token1 == token2 || token1 == token3 || token2 == token3) {
            System.out.println("Please select 3 unique gem tokens.");
            return false;
        }
        this.gemTokens.replace(token, this.gemTokens.get(token1) + 1);
        this.gemTokens.replace(token, this.gemTokens.get(token2) + 1);
        this.gemTokens.replace(token, this.gemTokens.get(token3) + 1);
        return true;
    }
}