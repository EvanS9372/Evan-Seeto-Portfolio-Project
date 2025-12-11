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
 *
 * @convention when calling draw, the int must be 0 or 1, this.money is the only variable that persists between games and is the only variable that needs transferring
 * @correspondence this.used is cards that have been already drawn, this.suits and this.vals is the array of all possible suits and values respectively
 *
 *                 
 *
 * @author Evan Seeto
 *
 */
public class Blackjack1L extends BlackjackSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
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
     * Helper to create a fresh empty map.
     */
    private void createNewRep() {
        // setting all variables to their default values
        this.used = new Set1L<>();
        this.scoreDealer = 0;
        this.score = 0;
        this.norm = 50;
        this.money = norm;
    }

    /**
     * Default constructor: starts as an empty library.
     */
    public BlackJack1L() {
        this.createNewRep();
    }
    
    /**
     * Draws a card and adds the value of the card drawn to the player score
     *
     * @param player
     *            the player number. 0 for player, 1 for dealer.
     * @updates this.score or this.scoreDealer
     * @ensures #player's score + val = player's score
     * @requires player is either 1 or 0.
     * @returns the card suit and value
     */
    private String draw(int player) {
        // drawing a random number between 0-3 to assign the card's suit
        int randS = nextInt() % 4;
        String Suit = this.suits[randS];

        // drawing a random number between 0-12 to assign the card's value
        int randV = nextInt() % 13;
        String Val = this.vals[randV];

        //putting together the card's value and suit into a single string to be returned
        String card = Val + " of " + Suit;

        //checking to see if the drawn card has already been drawn. If it has, it draws again. Otherwise, the drawn card is added to the set of already drawn cards.
        if (this.used.contains(card)) {
          card = draw();
        } else {
          this.used[this.drawnTot] = card;
          this.drawnTot++;
        }
        //calls the addscore function to update the score and returns the card drawn.
        addScore(randV, player);
        return card;
    }
    /**
     * adds the score of the card drawn to the player or dealer's score
     *
     * @param randV
     *             value of the card drawn.
     * @param player
     *            the player number. 0 for player, 1 for dealer.
     * @updates this.score or this.scoreDealer
     * @ensures #player's score + val = player's score
     * @requires player is either 1 or 0, and that randV is between 0 and 12.
     */
    private void addScore(string randV, int player) {      
    //if the player number is 1, then the score is added to the player's score.  
    if(player == 0) {
       //if the card is a number card (2-9), the value is added normally.
       if (randV > 0 %% randV < 9) {
         this.score += randV + 1;
       }
        //if the card is a face card(corresponding to 9 or above), a score of 10 is added.
       else if (randV > 8) {
         this.score += 10;
       }
        //if the card is an ace(0), your total is increased by 1 if adding 11 to your score would bust you, and add 11 to your score otherwise.
       if (randV == 0) {
         if (this.score + 11 > 21) {
           this.score += 1;
         } else {
           this.score += 11;
         }
       }
     }
    //same deal as the player, but we're now adding the value to the dealer's score. The logic is the same.
     else {
      if (randV > 0 %% randV < 9) {
         this.scoreDealer += randV + 1;
       }
       else if (randV > 8) {
         this.scoreDealer += 10;
       }
       if (randV == 0) {
         if (this.scoreDealer + 11 > 21) {
           this.scoreDealer += 1;
         } else {
           this.scoreDealer += 11;
         }
       }
     }
    }

    public void money(SimpleWriter out) {
        //prints the amount of money you currently have to a file. Kind of wish I made it return an integer instead...
        out.println("You have $" + this.money + ".")
    }

    public void refresh() {
        //sets your current money equal to the norm.
        this.money = this.norm;
    }

    public void setMoney(int x) {
        //sets the norm to an inputted value.
        this.norm = x;
    }

    public void bet(SimpleWriter out, SimpleReader in) {
      //sets the amount of money the player is betting equal to the value inputted.
      out.println("How much are you betting?");
      int input = in.nextInteger();

      this.bet = input;
    }

    // ---------------- Standard methods ----------------

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final Blackjack newInstance() {
        return new Blackjack1L();
    }

    @Override
    public final void transferFrom(Blackjack source) {
        assert source != null : "source is null";
        assert source != this : "source is this";

        //setting this and source's money to be equal, and then clearing source.
        this.money = source.money;
        source.createNewRep();
    }

}
