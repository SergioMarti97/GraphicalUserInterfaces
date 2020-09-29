package guielements;

import engine.Input;
import engine.gfx.Renderer;
import engine.gfx.font.Font;

public class GUIWindow extends GUIElement {

    private final int BACKGROUND_COLOR = 0xff024a86;

    private final int FRONT_COLOR = 0xffbba9bb;

    private final int HEADER_HEIGHT = 20;

    private GUIWindowHeader header;

    private float offSetX = 0.0f;

    private float offSetY = 0.0f;

    private Font font = Font.STANDARD;

    private String headerTitle = "title";

    private boolean isSelected = false;

    public GUIWindow(Input input, int posX, int posY, int width, int height) {
        super(input, posX, posY, width, height);
        header = new GUIWindowHeader(input, posX, posY, width, HEADER_HEIGHT, headerTitle, FRONT_COLOR);
    }

    public GUIWindow(Input input, int posX, int posY, int width, int height, String headerTitle) {
        super(input, posX, posY, width, height);
        this.headerTitle = headerTitle;
        header = new GUIWindowHeader(input, posX, posY, width, HEADER_HEIGHT, headerTitle, FRONT_COLOR);
    }

    @Override
    public boolean isPointInside(float x, float y) {
        if ( super.isPointInside(x, y) ) {
            if ( header.isPointInside(x, y) ) {
                offSetX = x - posX;
                offSetY = y - posY;
                header.setOffSetX(offSetX);
                header.setOffSetY(offSetY);
            }
            return true;
        } else {
            offSetX = 0.0f;
            offSetY = 0.0f;
            header.setOffSetX(0.0f);
            header.setOffSetY(0.0f);
            return false;
        }
    }

    public void drawYourSelf(Renderer r) {
        r.drawFillRect(posX, posY, width, height, BACKGROUND_COLOR);
        header.drawYourSelf(r);
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX((int)(posX - offSetX));
        header.setPosX(posX);
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY((int)(posY - offSetY));
        header.setPosY(posY);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        header.setSelected(selected);
    }

}
