import java.util.ArrayList;
import java.util.Collections;
import processing.core.PApplet;

public class CardGame {
    ArrayList<Card> deck = new ArrayList<>();
    Hand playerOneHand;
    Hand playerTwoHand;
    ArrayList<Card> discardPile = new ArrayList<>();
    
    boolean playerOneTurn = true;
    boolean gameActive = true;

    ClickableRectangle drawButton;
    int drawButtonX = 250;
    int drawButtonY = 400;
    int drawButtonWidth = 100;
    int drawButtonHeight = 35;
    ClickableRectangle playAgain;

    public CardGame() {
        initializeGame();
    }

    protected void initializeGame() {
        drawButton = new ClickableRectangle(drawButtonX, drawButtonY, drawButtonWidth, drawButtonHeight, "Draw");
        playAgain = new ClickableRectangle(App.gameWidth / 2 - 50, 310, 100, 35, "Play Again");

        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        playerOneHand = new Hand();
        playerTwoHand = new Hand();
        
       
        playerOneTurn = true;

        createDeck();

    }

    protected void createDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }

    protected void dealCards(int numCards) {
        Collections.shuffle(deck);
        for (int i = 0; i < numCards; i++) {
            playerOneHand.addCard(deck.remove(0));
            Card card = deck.remove(0);
            card.setTurned(true);
            playerTwoHand.addCard(card);
        }
        playerOneHand.positionCards(50, 450, 80, 120, 20);
        playerTwoHand.positionCards(50, 50, 80, 120, 20);
    }

    public void handleDrawButtonClick(int mouseX, int mouseY) {
        if (drawButton.isClicked(mouseX, mouseY) && playerOneTurn && gameActive) {
            playerOneHand.addCard(deck.remove(0));
            switchTurns();
        }
    }

    public void handlePlayAgainClick(int mouseX, int mouseY) {
        if (playAgain.isClicked(mouseX, mouseY) && !gameActive) {
            initializeGame();
        }
    }

    public void switchTurns() {
        playerOneTurn = !playerOneTurn;
    }

    public String getCurrentPlayer() {
        return playerOneTurn ? "Player One" : "Player Two";
    }

    public int getDeckSize() {
        return deck.size();
    }

    public void handleComputerTurn() {}
    public void drawChoices(PApplet app) {}
    public void checkWinCondition() {}
    public void handleCardClick(int mouseX, int mouseY) {}

    public void drawPlayAgain(PApplet app) {
        if (gameActive) return;
        app.fill(0, 150);
        app.rect(0, 0, app.width, app.height);
        app.fill(255);
        app.textAlign(app.CENTER, app.CENTER);
        app.textSize(32);
        app.text("GAME OVER", app.width / 2, 250);
        playAgain.draw(app);
    }
}