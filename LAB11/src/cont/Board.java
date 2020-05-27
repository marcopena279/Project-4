package cont;

import java.util.List;

import mod.Card;
import mod.Deck;
import view.Window;

import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class Board {
	private Window _w;
	private int right;
	private int wrong;
	private static final String[] BUTTONS = { "Card is higher", "Card is lower", "EXIT" };
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 1;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};


	/**
	 * The cards on this board.
	 */
	private Card[] cards;

	/**
	 * The deck of cards being used to play the current game.
	 */
	private Deck deck;

	/**
	 * Flag used to control debugging print statements.
	 */

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public Board() {
		 _w = new Window();
		cards = new Card[BOARD_SIZE];
		deck = new Deck(RANKS, SUITS, POINT_VALUES);
		newGame(); 
		Game(); 
	}
	
	public void Game() {
		_w.msg("Welcome to Card Guesser" + "\n You will have to guess if the next card is higher or lower" + "\n If you reach a score of 5 correct you win the game" + "If you guess 3 wrong you start over"
				+ "\n Good Luck");
        while (true) {	
        	Object y = deck.deal(); 
        	int x = _w.option(BUTTONS, y);
        	Object z = deck.deal();
        	if(x == 0) {
        		if(((Card) y).pointValue() < ((Card) z).pointValue()) {
        			right += 1; 
        			_w.msg("The next card was: " + z + "\n You got it right!!" + "\n Score: " + right + " Wrong: " + wrong);
        			right();
        		}
        		else if(((Card) y).pointValue() == ((Card) z).pointValue()) {
        			_w.msg("The next card was: " + z + "\n Neither got it right nor wrong" + "\n Score: " + right + " Wrong: " + wrong);	
        		}
        		else {
        			wrong += 1; 
        			_w.msg("The next card was: " + z + "\n You got it wrong!!" + "\n Score: " + right + " Wrong: " + wrong);
        			wrong();
        		}
        	}
        	else if(x == 1) {
        		if(((Card) y).pointValue() > ((Card) z).pointValue()) {
        			right += 1; 
        			_w.msg("The next card was: " + z + "\n You got it right!!" + "\n Score: " + right + " Wrong: " + wrong);
        			right(); 
        		}
        		else if(((Card) y).pointValue() == ((Card) z).pointValue()) {
        			_w.msg("The next card was: " + z + "\n Neither got it right nor wrong" + "\n Score: " + right + " Wrong: " + wrong);	
        		}
        		else {
        			wrong += 1; 
        			_w.msg("The next card was: " + z + "\n You got it wrong!!" + "\n Score: " + right + " Wrong: " + wrong);
        			wrong();
        		}
        	}
        	else{
        		System.exit(0);
        	}
        }
    }
	
	public void wrong() {
	    if(wrong == 3) {
	    	_w.msg("Start Over");
			right = 0;
			wrong = 0; 
		}
	}
	public void right() {
		if(right == 5) {
			_w.msg("YOU WIN CONGRATSSSS");
			right = 0;
			wrong = 0; 
		}
	}

	/**
	 * Start a new game by shuffling the deck and
	 * dealing some cards to this board.
	 */
	
	/**
	 * Deal cards to this board to start the game.
	 */
	private void dealMyCards() {
		for (int k = 0; k < cards.length; k++) {
			cards[k] = deck.deal();
		}
	}
	
	public void newGame() {
		deck.shuffle();
		dealMyCards();
	}

	/**
	 * Accesses the size of the board.
	 * Note that this is not the number of cards it contains,
	 * which will be smaller near the end of a winning game.
	 * @return the size of the board
	 */
	public int size() {
		return cards.length;
	}

	/**
	 * Determines if the board is empty (has no cards).
	 * @return true if this board is empty; false otherwise.
	 */
	public boolean isEmpty() {
		for (int k = 0; k < cards.length; k++) {
			if (cards[k] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Deal a card to the kth position in this board.
	 * If the deck is empty, the kth card is set to null.
	 * @param k the index of the card to be dealt.
	 */
	public void deal(int k) {
		cards[k] = deck.deal();
	}

	/**
	 * Accesses the deck's size.
	 * @return the number of undealt cards left in the deck.
	 */
	public int deckSize() {
		return deck.size();
	}

	/**
	 * Accesses a card on the board.
	 * @return the card at position k on the board.
	 * @param k is the board position of the card to return.
	 */
	public Card cardAt(int k) {
		return cards[k];
	}

	/**
	 * Replaces selected cards on the board by dealing new cards.
	 * @param selectedCards is a list of the indices of the
	 *        cards to be replaced.
	 */
	public void replaceSelectedCards(List<Integer> selectedCards) {
		for (Integer k : selectedCards) {
			deal(k.intValue());
		}
	}

	/**
	 * Gets the indexes of the actual (non-null) cards on the board.
	 *
	 * @return a List that contains the locations (indexes)
	 *         of the non-null entries on the board.
	 */
	public List<Integer> cardIndexes() {
		List<Integer> selected = new ArrayList<Integer>();
		for (int k = 0; k < cards.length; k++) {
			if (cards[k] != null) {
				selected.add(new Integer(k));
			}
		}
		return selected;
	}

	/**
	 * Generates and returns a string representation of this board.
	 * @return the string version of this board.
	 */
	public String toString() {
		String s = "";
		for (int k = 0; k < cards.length; k++) {
			s = s + k + ": " + cards[k] + "\n";
		}
		return s;
	}

	/**
	 * Determine whether or not the game has been won,
	 * i.e. neither the board nor the deck has any more cards.
	 * @return true when the current game has been won;
	 *         false otherwise.
	 */
	public boolean gameIsWon() {
		if (deck.isEmpty()) {
			for (Card c : cards) {
				if (c != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}