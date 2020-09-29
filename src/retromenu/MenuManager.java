package retromenu;

import engine.gfx.Renderer;
import engine.gfx.images.Image;
import engine.vectors.points2d.Vec2di;

import java.util.List;

public class MenuManager {

    private List<MenuObject> panels;

    public MenuManager() {

    }

    public void close() {
        panels.clear();
    }

    public void open(MenuObject menuObject) {
        close();
        panels.add(menuObject);
    }

    /*public void onUp() {
        if ( !panels.isEmpty() ) {
            panels.get(panels.size() - 1).onUp();
        }
    }

    public void onBack() {
        if ( !panels.isEmpty() ) {
            panels.remove(panels.size() - 1);
        }
    }

    public MenuObject onConfirm() {
        
    }*/

    public void draw(Renderer r, Image image, Vec2di screenOffset) {
        if ( panels.isEmpty() ) {
            return;
        }
        for ( MenuObject panel : panels ) {
            panel.drawSelf(r, image, screenOffset);
            screenOffset.add(10);
        }
    }

}
