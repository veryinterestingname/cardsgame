import processing.core.PImage;
import processing.core.PApplet;

public class Card extends ClickableRectangle {
    String value;
    String suit;
    PImage img;
    boolean turned = false;
    private int clickableWidth = 30; 
    private boolean selected = false;
    private int baseY;
    private boolean hasBaseY = false;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getBlackjackValue() {
        if (value.equals("A")) return 11;
        if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) return 10;
        return Integer.parseInt(value);
    }

    public void setTurned(boolean turned) {
        this.turned = turned;
    }

    public void setClickableWidth(int width) {
        this.clickableWidth = width;
    }

    public void setSelected(boolean selected, int raiseAmount) {
        if (selected && !this.selected) {
            baseY = y;
            hasBaseY = true;
            y = baseY - raiseAmount;
        } else if (!selected && this.selected && hasBaseY) {
            y = baseY;
        }
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + clickableWidth &&
                mouseY >= y && mouseY <= y + height;
    }

    public void setPosition(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(PApplet sketch) {
        sketch.stroke(0);
        if (turned) {
            sketch.fill(100, 100, 200);
            sketch.rect(x, y, width, height);
            sketch.fill(255);
            sketch.textAlign(sketch.CENTER, sketch.CENTER);
            sketch.text("?", x + width/2, y + height/2);
            return;
        }
        
        sketch.fill(255);
        if (isSelected()) sketch.strokeWeight(4);
        sketch.rect(x, y, width, height);
        sketch.strokeWeight(1);
        
        sketch.fill(suit.equals("Hearts") || suit.equals("Diamonds") ? 255 : 0);
        sketch.fill(0);
        sketch.textAlign(sketch.LEFT, sketch.TOP);
        sketch.text(value + "\n" + suit, x + 5, y + 5);
    }
}

