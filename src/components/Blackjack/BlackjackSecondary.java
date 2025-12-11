import components.random.Random;
import components.random.Random1L;
import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import java.util.Arrays;

/**
 * Layered implementations of secondary methods for Blackjack
 */
public abstract class BlackjackSecondary implements Blackjack {

    /*
     * Private members --------------------------------------------------------
     */
    private String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
    private String[] vals = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King"};
    private Set<String> used = new Set1L<>();
    private int scoreDealer = 0;
    private int score = 0;
    private int norm = 50;
    private int bet;
    private int money = norm;

    /**
     * Constructs the game of blackjack.
     */
    public BlackjackSecondary() {

    }

    /**
     * plays the actual game of blackjack
     *
     * @updates every private member except norm and the arrays
     */
    @Override
    public void play() {
        //declaring the simple reader and writer
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        
        //print the amount of money before the game begins and getting the amount of money to be bet.
        money(out);
        bet(out, in);
        //the dealer and player each draw 2 cards.
        String cardD1 = draw(1);
        String cardD2 = draw(1);
        String card1 = draw(0);
        String card2 = draw(0);
        //Reveal 1 of the dealer's cards and both of the player's cards, and informs the player of their score.
        out.println("The dealer drew the " + cardD1 + " and the second card is unrevealed.");
        out.println("You drew the " + card1 + " and the " + card2 + ", making a total score of " + this.score + ". Hit? Answer Y for yes, anything else for no.");
        //getting the player's response to if they will hit or not
        String hit = in.nextLine();
    
        while(hit.equals("Y") && this.score <= 21) {
            //draw a card, reveal it to the player, and tell them their updated score.
            String currCard = draw(0);
            out.println("You drew the " + currCard + ", and your total score is now " + score + ".")
            //if you bust, the while loop ends automatically and you lose money equal to the amount bet. Otherwise, ask the player if they want to hit again.
            if (this.score > 21) {
                out.println("Bust! You lost $" + this.bet + " dollars.");
                this.money -= this.bet;
            } else {
                out.println("Hit? Answer Y for yes, anything else for no.");
                hit = in.nextLine();
            }
        }
        //if the player busts, skip the dealer's turn.
        if(this.score <= 21) {
            //remind the player of their score and reveal the dealer's hidden card.
            out.println("Your total score is " + this.score + ".");
            out.println("Dealer's turn!");
            out.println("The dealer's first card was the " + card1 + " and the unrevealed card was the " + card2 + ", making a total score of " + this.scoreDealer + ".);
            //the dealer will continue to draw until its score is greater than or equal to your score or until it busts.
            while(this.scoreDealer < this.score && this.scoreDealer < 21) {
                //draw a card for the dealer, reveal it, and update the dealer's score.
                currCard = draw(1);
                out.println("The dealer drew the " + currCard + ", and his total score is now " + score + " compared to your score of " + this.score + ".");
                //if the dealer busts, you win and gain the money you bet.
                if (this.scoreDealer > 21) {
                    out.println("Bust! You won $" + this.bet + " dollars!");
                    this.money += this.bet;
                }
            }
            //if the dealer has a score more than or equal to yours, they will stop hitting. If the dealer already busted, the score comparison is skipped.
            //the player's score isn't checked because this branch only executes if the player didn't bust.
            if (this.scoreDealer <= 21) {
                out.println("The dealer stands with a score of " + this.scoreDealer + " compared to your score of " + this.score + ".");
                //compare the final scores and give, take, or leave the amount bet as appropriate.
                if (this.scoreDealer > this.score) {
                    out.println("You lose! You lost $" + this.bet + " dollars.");
                    this.money -= this.bet;
                }
                else if (this.scoreDealer == this.score) {
                    out.println("Tie! No money was lost or gained.");
                }
                //even though this scenario is impossible, since the dealer will bust before standing at a score lower than yours, this case was added in case of a bug.
                else if (this.scoreDealer < this.score) {
                    out.println("You win! You gain $" + this.bet + " dollars.");
                    this.money += this.bet;
                }
            }

        }

        //display the money again, reset the scores for the dealer and player.
        money(out);
        this.scoreDealer = 0;
        this.score = 0;
        out.close();
        in.close();
    }


    /**
     * Returns the money won in the game
     *
     * @return a string representation of this
     */
    @Override
    public String toString() {
        return "This game has earned $" + this.money + ".";
    }

    /**
     * checks to see if two games of blackjack have equal money amounts
     *
     * @return true if x and this are equal, false otherwise
     */
    @Override
    public boolean equals(Blackjack x) {
      boolean outp = false;
      //if the money values are the same, the games are counted as equal.
      if(this.money == x.money) {
        outp = true;
      }
      return outp;
    }
}
