package guielements;

import engine.Input;

public abstract class GUIElement {

    protected Input input;

    protected int posX;

    protected int posY;

    protected int width;

    protected int height;

    public GUIElement(Input input, int posX, int posY, int width, int height) {
        this.input = input;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public boolean isPointInside(float x, float y) {
        return x > posX && x <= (posX + width) &&
                y > posY && y <= (posY + height);
    }

    public Input getInput() {
        return input;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
