// Use case 2 is a bit less intended... since the draw function draws a card, it could be adapted into any card game!
import components.set.Set;
import components.set.Set1L;

public static void main(String[] args) {
        Blackjack game = new Blackjack();
        Set<String> hand = new Set1L<>();
        Set<String> down = new Set1L<>();
        for (int i = 0; i < 2; i++;) {
          hand.add(game.draw(0));
        }
        for (int i = 0; i < 5; i++;) {
          down.add(game.draw(0));
        }
        //just like that, we have a semi-setup for texas hold 'em.
    }
