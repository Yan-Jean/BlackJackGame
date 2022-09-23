import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private String user;



    public Hand(String user) {
        this.user = user;
        this.hand = new ArrayList<Card>();
    }

    public ArrayList<Card> getCard() {
        return this.hand;
    }

    // retourne la somme des cartes dans le tableau hand. Si la somme est >21, il faut recompter les cartes pour verifier si il y a un ACE. Si oui on le considere comme 1, sinon on ajoute la somme des points.
    public int getPoints() {
        int total = 0;

        for (Card c : hand) {
            total += c.getPoints();
            if (total > 21) {
                total = 0;
                for (Card a : hand) {
                    if (a.isAce())
                        total += 1;
                    else
                        total += a.getPoints();
                }
            }
        }
        return total;
    }

    public String showCardValue(Card card) {
        return card.getValue();
    }

    public String showCardColor(Card card) {
        return card.getColor();
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public boolean isBlackjack() {
        return this.getPoints() == 21 && this.hand.size() == 2;
    }

    public boolean isBust() {
        return this.getPoints() > 21;
    }

    public void display() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(" -----\t");
            // Skip to next printable line.
            if (i == hand.size() - 1)
                System.out.println();
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.print("   " + showCardValue(hand.get(i)) + "   \t");
            // Skip to next printable line.
            if (i == hand.size() - 1)
                System.out.println();
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.print("   " + showCardColor(hand.get(i)) + "   \t");
            // Skip to next printable line.
            if (i == hand.size() - 1)
                System.out.println();
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(" -----\t");
            // Skip to next printable line.
            if (i == hand.size() - 1)
                System.out.println();
        }

    }

    public void throwCards() {
        this.hand = new ArrayList<Card>();
    }
}
