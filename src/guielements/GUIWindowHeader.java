package guielements;

import engine.Input;
import engine.gfx.Renderer;
import engine.gfx.font.Font;

public class GUIWindowHeader extends GUIElement {

    private final int SELECTED_COLOR = 0xffe7d40a;

    private Font font = Font.STANDARD;

    private String text;

    private float offSetX = 0.0f;

    private float offSetY = 0.0f;

    private int color;

    private boolean isSelected = false;

    public GUIWindowHeader(Input input, int posX, int posY, int width, int height, String text, int color) {
        super(input, posX, posY, width, height);
        this.text = text;
        this.color = color;
    }

    @Override
    public boolean isPointInside(float x, float y) {
        return super.isPointInside(x, y);
    }

    public void drawYourSelf(Renderer r) {
        r.drawFillRect(posX, posY, width, height, color);
        String title = text + " X: " + posX + " Y: " + posY;
        r.drawText(title, posX, posY, 0xff000000, font);
        if ( isSelected ) {
            r.drawRect(posX, posY, width, height, SELECTED_COLOR);
        }
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX((int)(posX - offSetX));
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY((int)(posY - offSetY));
    }

    public void setOffSetX(float offSetX) {
        this.offSetX = offSetX;
    }

    public void setOffSetY(float offSetY) {
        this.offSetY = offSetY;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
