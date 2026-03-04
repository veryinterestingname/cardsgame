import processing.core.PApplet;

public class App extends PApplet {

    CardGame cardGame = new Blackjack();
    static int gameWidth = 1000;
    private int timer;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    @Override
    public void settings() {
        size(gameWidth, 800);   
    }

    @Override
    public void draw() {
        background(255);
        

        cardGame.playerOneHand.draw(this);

        cardGame.playerTwoHand.draw(this);
        

        cardGame.drawButton.draw(this);


        fill(0);
        textSize(16);
        textAlign(CENTER, CENTER);
        text("Current Player: " + cardGame.getCurrentPlayer(), width / 2, 20);


        text("Deck Size: " + cardGame.getDeckSize(), width / 2, height - 20);


        if (cardGame.getCurrentPlayer().equals("Player Two") && cardGame.gameActive) {
            fill(0);
            textSize(16);
            text("Dealer is thinking...", width / 2, height / 2 + 80);
            timer++;
            if (timer == 100) {
                cardGame.handleComputerTurn();
                timer = 0;
            }
        }

        cardGame.drawChoices(this);
        cardGame.drawPlayAgain(this);
    }

    @Override
    public void mousePressed() {
        cardGame.handleDrawButtonClick(mouseX, mouseY);
        

        if (cardGame instanceof Blackjack) {
            ((Blackjack) cardGame).handleStandClick(mouseX, mouseY);
        }
        
        cardGame.handleCardClick(mouseX, mouseY);
        cardGame.handlePlayAgainClick(mouseX, mouseY);
    }
}
