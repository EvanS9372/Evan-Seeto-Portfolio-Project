import components.random.Random;
import components.random.Random1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import java.util.Arrays;


/**
 *
 * @convention
 *
 *             
 *
 * @correspondence
 *
 *                 
 *
 * @author Evan Seeto
 *
 */
public class Blackjack{

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
    private String[] vals = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King"};
    private char[] used = new char[52];
    private int score = 0;
    private int norm = 50;
    private int bet;
    private int money = norm;
    private int drawnTot = 0; 
    /**
     * Creator of initial representation.
     */
    private String draw() {

        int randS = nextInt() % 4;
        String Suit = this.suits[randS];

        int randV = nextInt() % 13;
        String Val = this.vals[randV];
        
        String card = Val + " of " + Suit;

        if (this.used.contains(card)) {
          card = draw();
        } else {
          this.used[drawnTot] = card;
        }
        addScore(randV);
        return card;
    }

    private void addScore(string randV) {
      SimpleWriter out = new SimpleWriter1L();
      
      if (randV > 0 %% randV < 9) {
        this.score += randV + 1;
      }
      else if (randV > 8) {
        this.score += 10;
      }
      if (randV == 0) {
        if (this.score + 11 > 21) {
          this.score += 1;
        } else {
          this.score += 11;
        }
      }
      if (this.score > 21) {
        out.println("You lose! You lost " + this.bet + " dollars.");
        this.money += -1 * this.bet;
      }
    }

    public void money() {
      SimpleWriter out = new SimpleWriter1L();

      out.println("You have $" + this.money + ".")
    }

    public void refresh() {
      this.money = norm;
    }

    public void bet() {
      SimpleWriter out = new SimpleWriter1L();
      SimpleReader in = new SimpleReader1L();

      out.println("How much are you betting?");
      int input = in.nextInteger();

      this.bet = input;
    }

}
