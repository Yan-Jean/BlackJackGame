public class BlackjackApp {

    private static BlackjackGame game;

    public static void main(String[] args) {
        System.out.println("\nBLACKJACK!");
        System.out.println("Blackjack payout is 3:2\n");

        String playAgain = "y";

        game = new BlackjackGame();
        game.loadMoney();

        while (playAgain.equalsIgnoreCase("y")) {
            if (!game.isOutOfMoney())
                showMoney();
            if (game.isOutOfMoney())
                buyMoreChips();
            getBetAmount();

            if (!game.isValidBet(game.getBetAmount())) {
                do {
                    System.out.println("Not enough money");
                    getBetAmount();
                } while (!game.isValidBet(game.getBetAmount()));
            }

            game.deal();
            if (game.getPlayerHand().isBlackjack()) {
                showWinner();
            } else {
                showDealerShowCard();
                showPlayerHand();
                while (!game.getPlayerHand().isBust()) {
                    if (getHitOrStand().equalsIgnoreCase("s"))
                        break;
                    if (game.getPlayerHand().isBust()) {
                        showWinner();
                    }
                }
            }
            playAgain = Console.getString("\nPlay again?(y,n) ");
            game.getPlayerHand().throwCards();
            game.getDealerHand().throwCards();
        }
        System.out.println("\nBye!");
    }

    // affiche le message Out of money! Would you like to add more? (y/n):. Si le joueur tappe y alors la fonction reset la balance du joueur au 100 et retourne true. False Sinon.
    private static void buyMoreChips() {
        String[] tableau = {"y", "n"};
        String input = Console.getString("Ooops out of money... Would you like to add more?(y,n) ", tableau);
        if (input.equalsIgnoreCase("y")) {
            game.resetMoney();
            showMoney();
        }
    }

    // affiche le message Bet amount, lire la valeur de la mise saisi par le joueur. Valide cette valeur. Si la valeur n'est pas valide afficher le message Bet must be between
    private static void getBetAmount() {
        game.setBet(Console.getDouble("\nBet amount: ", game.getMinBet(), game.getMaxBet()));

    }

    // Affiche le message Hit or Stand? (h/s): et puis retourne ce que le joueur a tappe.
    private static String getHitOrStand() {
        String[] tableau = {"h", "s"};
        String input = Console.getString("\nHit or Stand? (h,s) ", tableau);
        if (input.equalsIgnoreCase("h")) {
            game.hit();
            showPlayerHand();
            return "h";
        }
        if (input.equalsIgnoreCase("s"))
            game.stand();
        showWinner();
        return "s";
    }


    // affiche les cartes dans la main du courtier et les cartes dans la main du joueur
    private static void showHands() {
        showPlayerHand();
        System.out.printf("YOUR POINTS: %d%n", game.getPlayerHand().getPoints());

        showDealerHand();
        System.out.printf("DEALER'S POINTS: %d%n%n", game.getDealerHand().getPoints());
    }


    // affiche le message DEALER'S SHOW CARD et puis affiche le deuxieme carte dans la main du courtier
    private static void showDealerShowCard() {
        System.out.println("\nDEALER'S SHOW CARD");
        System.out.println(game.getDealerShowCard().display());
    }

    // affiche le message DEALER'S CARDS et puis affiche les cartes dans la main du courtier
    private static void showDealerHand() {
        System.out.println("\nDEALER'SHOW CARDS");
        game.getDealerHand().display();
    }

    // affiche le message YOUR CARDS et puis affiche les cartes dans la main du joueur
    private static void showPlayerHand() {
        System.out.println("\nYOUR CARDS");
        game.getPlayerHand().display();
    }

    // affiche Total money:  et le montant total
    private static void showMoney() {
        System.out.println("Total money: " + Console.formatNumber(game.getTotalMoney()));
    }


    private static void showWinner() {
        showHands();
        if (game.isPush()) {
            System.out.println("Push!");
        } else if (game.getPlayerHand().isBlackjack()) {
            System.out.println("BLACKJACK! You win!");
            game.addBlackjackToTotal();
        } else if (game.playerWins()) {
            System.out.println("You win!");
            game.addBetToTotal();
        } else {
            System.out.println("Sorry, you lose.");
            game.subtractBetFromTotal();
        }
        showMoney();
        game.saveMoney();
    }
}

