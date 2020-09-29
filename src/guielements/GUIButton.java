package guielements;

import engine.Input;
import engine.gfx.Renderer;

public class GUIButton extends GUIElement {

    private int color;

    private boolean isPressed = false;

    private boolean isReleased = false;

    private boolean isHeld = false;

    public GUIButton(Input input, int posX, int posY, int width, int height, int color) {
        super(input, posX, posY, width, height);
        this.color = color;
    }

    public void drawYourSelf(Renderer r) {
        r.drawFillRect(posX, posY, width, height, color);
    }

    public int getColor() {
        return color;
    }

    public boolean isPressed() {
        if ( input.isButtonDown(1) ) {
            return isPointInside(input.getMouseX(), input.getMouseY());
        } else {
            return false;
        }
    }

    public boolean isReleased() {
        if ( input.isButtonUp(1) ) {
            return isPointInside(input.getMouseX(), input.getMouseY());
        } else {
            return false;
        }
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }

    public void setHeld(boolean held) {
        isHeld = held;
    }

}
