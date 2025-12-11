import components.random.Random;
import components.random.Random1L;
import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import java.util.Arrays;

// --- Kernel Method Tests ---

    // constructor test
    @Test
    public final void testConstructor() {
        Blackjack game = new Blackjack1L();
        assertEquals(game.scoreDealer, 0);
        assertEquals(game.score, 0);
        assertEquals(game.norm, 50);
        assertEquals(game.money, 50);
    }

  // addscore test
    @Test
    public final void testAddscore1() {
        Blackjack game = new Blackjack1L();
        game.addscore(0, 1)
        assertEquals(game.scoreDealer, 11);
        assertEquals(game.score, 0);
    }

    @Test
    public final void testAddscore2() {
        Blackjack game = new Blackjack1L();
        game.addscore(0, 1)
        game.addscore(0, 1)
        assertEquals(game.scoreDealer, 12);
        assertEquals(game.score, 0);
    }

    @Test
    public final void testAddscore3() {
        Blackjack game = new Blackjack1L();
        game.addscore(9, 1)
        assertEquals(game.scoreDealer, 10);
        assertEquals(game.score, 0);
    }

    @Test
    public final void testAddscore4() {
        Blackjack game = new Blackjack1L();
        game.addscore(6, 1)
        assertEquals(game.scoreDealer, 7);
        assertEquals(game.score, 0);
    }

    @Test
    public final void testAddscore5() {
        Blackjack game = new Blackjack1L();
        game.addscore(0, 0)
        assertEquals(game.scoreDealer, 0);
        assertEquals(game.score, 11);
    }

    @Test
    public final void testAddscore6() {
        Blackjack game = new Blackjack1L();
        game.addscore(0, 0)
        game.addscore(0, 0)
        assertEquals(game.scoreDealer, 0);
        assertEquals(game.score, 12);
    }

    @Test
    public final void testAddscore7() {
        Blackjack game = new Blackjack1L();
        game.addscore(9, 0)
        assertEquals(game.scoreDealer, 9);
        assertEquals(game.score, 10);
    }

    @Test
    public final void testAddscore8() {
        Blackjack game = new Blackjack1L();
        game.addscore(6, 0)
        assertEquals(game.scoreDealer, 0);
        assertEquals(game.score, 7);
    }

// draw test
    @Test
    public final void testDraw() {
        Blackjack game = new Blackjack1L();
        String curr = game.Draw(0, 1)
        assertEquals(game.scoreDealer, 11);
        assertEquals(game.score, 0);
        assertTrue(game.used.size() == 1);
        assertTrue(game.used.contains(curr));
    }

// money test
  @Test
    public final void testMoney() {
        Blackjack game = new Blackjack1L();
        SimpleWriter out = new SimpleWriter1L();
        SimpleWriter outS = new SimpleWriter1L();
        outS.println("You have $50.")
        game.money(out);
        assertEquals(out, outS);
    }

// refresh test
  @Test
    public final void testRefresh1() {
        Blackjack game = new Blackjack1L();
        game.refresh();
        assertEquals(game.money, 50);
    }

  @Test
    public final void testRefresh2() {
        Blackjack game = new Blackjack1L();
        game.money += 100;
        game.refresh();
        assertEquals(game.money, 50);
    }

// setMoney test
@Test
    public final void testRefresh1() {
        Blackjack game = new Blackjack1L();
        game.setMoney(100);
        assertEquals(game.norm, 100);
    }

@Test
    public final void testRefresh2() {
        Blackjack game = new Blackjack1L();
        game.setMoney(0);
        assertEquals(game.norm, 0);
    }

@Test
    public final void testRefresh3() {
        Blackjack game = new Blackjack1L();
        game.setMoney(100);
        game.refresh();
        assertEquals(game.norm, 100);
        assertEquals(game.money, 100);
    }

// bet test

@Test
    public final void testBet1() {
        Blackjack game = new Blackjack1L();
        SimpleWriter in = new SimpleWriter1L("test/data.txt")
        SimpleWriter out = new SimpleWriter1L();
        SimpleWriter outS = new SimpleWriter1L();
        outS.println("How much are you betting?")
        game.bet(in);
        assertEquals(out, outS);
        assertEquals(game.bet, 20);
    }

// clear test

@Test
    public final void clearTest1() {
        Blackjack game = new Blackjack1L();
        Blackjack game2 = new Blackjack1L();
        game.clear();
        assertEquals(game, game2);
    }

@Test
    public final void clearTest2() {
        Blackjack game = new Blackjack1L();
        Blackjack game2 = new Blackjack1L();
        game.scoreDealer = 20;
        game.score = 12;
        game.norm = 100;
        game.money = 1000;
        game.clear();
        assertEquals(game, game2);
    }

// newinstance test
@Test
    public final void newInstanceTest() {
        Blackjack game = new Blackjack1L();
        Blackjack game2 = game.newInstance();
        assertEquals(game, game2);
    }

// transferFrom test
@Test
    public final void transferFromTest() {
        Blackjack game = new Blackjack1L();
        Blackjack game2 = new Blackjack1L();

        game.money = 500;
        game2.transferFrom(game);
        
        assertEquals(game.money, 50);
        assertEquals(game2.money, 500);
    }
