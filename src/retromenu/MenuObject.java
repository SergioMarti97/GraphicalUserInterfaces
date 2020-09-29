package retromenu;

import engine.gfx.Renderer;
import engine.gfx.images.Image;
import engine.gfx.images.ImageTile;
import engine.vectors.points2d.Vec2di;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuObject {

    private final int patch = 8;

    private final Vec2di patchSize = new Vec2di( patch, patch);

    /**
     * The option name
     * What is its name going to be
     */
    private String name;

    /**
     * The object can be selected
     */
    private boolean isEnabled = true;

    /**
     * The id code for the option
     */
    private int id = -1;

    /**
     * The number of rows that make up the table
     */
    private int numTotalRows = 0;

    /**
     * Partner to @numTotalRows, where in the table do
     * we start drawing from
     */
    private int numTopVisibleRows = 0;

    private Vec2di cellTable = new Vec2di(1, 0);

    private Vec2di cellSize = new Vec2di(0, 0);

    private Vec2di cellPadding = new Vec2di(2, 2);

    private Vec2di sizeInPatches = new Vec2di(0, 0);

    private HashMap<String, Integer> itemPointer = new HashMap<>();

    private ArrayList<MenuObject> items = new ArrayList<>();

    public MenuObject() {
        name = "root";
    }

    public MenuObject(String name) {
        this.name = name;
    }

    public MenuObject setTable(int columns, int rows) {
        cellTable = new Vec2di(columns, rows);
        return this;
    }

    public MenuObject setId(int id) {
        this.id = id;
        return this;
    }

    public MenuObject setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Vec2di getSize() {
        return new Vec2di(name.length(), 1);
    }

    public boolean hasChildren() {
        return !itemPointer.isEmpty();
    }

    public HashMap<String, Integer> getItemPointer() {
        return itemPointer;
    }

    public MenuObject getItem(String optionName) {
        return items.get(itemPointer.get(optionName));
    }

    public MenuObject addItem(String optionName) {

        if ( !itemPointer.containsKey(optionName) ) {
            itemPointer.put(optionName, items.size());
            items.add(new MenuObject(optionName));
        }

        return this;
    }

    public void drawSelf(Renderer r, Image image, Vec2di screenOffset) {

        Vec2di patchPos = new Vec2di(0, 0);
        for ( patchPos.setX(0); patchPos.getX() < sizeInPatches.getX(); patchPos.addToX(1) ) {
            for ( patchPos.setY(0); patchPos.getY() < sizeInPatches.getY(); patchPos.addToY(1) ) {

                Vec2di screenLocation = new Vec2di();
                screenLocation.setX(patchPos.getX() * patch + screenOffset.getX());
                screenLocation.setY(patchPos.getY() * patch + screenOffset.getY());

                Vec2di sourcePatch = new Vec2di();
                if ( patchPos.getX() > 0 ) {
                    sourcePatch.setX(1);
                }
                if ( patchPos.getX() == sizeInPatches.getX() - 1 ) {
                    sourcePatch.setX(2);
                }
                if ( patchPos.getY() > 0 ) {
                    sourcePatch.setY(1);
                }
                if ( patchPos.getY() == sizeInPatches.getY() - 1 ) {
                    sourcePatch.setY(2);
                }

                ImageTile imageTile = new ImageTile(image, patch, patch);
                Image imageToDraw = imageTile.getTileImage(sourcePatch.getX() * patch, sourcePatch.getY() * patch);
                r.drawImage(imageToDraw, screenLocation.getX(), screenLocation.getY());

            }
        }

        Vec2di cell = new Vec2di();
        patchPos = new Vec2di(1, 1);

        int topLeftItem = numTopVisibleRows * cellTable.getX();
        int bottomRightItem = cellTable.getY() * cellTable.getX() + topLeftItem;

        bottomRightItem = Math.min(items.size(), bottomRightItem);
        int visibleItems = bottomRightItem - topLeftItem;

        for ( int i = 0; i < visibleItems; i++ ) {
            cell.setX(i % cellTable.getX());
            cell.setY(i / cellTable.getX());

            patchPos.setX(cell.getX() * (cellSize.getX() + cellPadding.getX()) + 1);
            patchPos.setY(cell.getY() * (cellSize.getY() + cellPadding.getY()) + 1);

            Vec2di screenLocation = new Vec2di();
            screenLocation.setX(patchPos.getX() * patch + screenLocation.getX());
            screenLocation.setY(patchPos.getY() * patch + screenLocation.getY());

            r.drawText(items.get(topLeftItem + i).getName(), screenLocation.getX(), screenLocation.getY(),
                    items.get(topLeftItem + i).isEnabled ? 0xffffff00 : 0xff696969);
        }

        /*for ( Map.Entry<String, Integer> m : itemPointer.entrySet() ) {
            patchPos.setX(cell.getX() * (cellSize.getX() + cellPadding.getX()) + 1);
            patchPos.setY(cell.getY() * (cellSize.getY() + cellPadding.getY()) + 1);

            Vec2di screenLocation = new Vec2di();
            screenLocation.setX(patchPos.getX() * patch + screenOffset.getX());
            screenLocation.setY(patchPos.getY() * patch + screenOffset.getY());

            r.drawText(m.getKey(), screenLocation.getX(), screenLocation.getY(), 0xffffff00);

            cell.addToY(1);
        }*/

    }

    /**
     * Recursively build all children, so they can determine their size, use
     * that size to indicate cell size if this object contains more than
     * one item.
     * The longest child name determines cell width
     * Also adjust size of this object (in patches) if it were renderer as a panel
     * Calculate how many rows this item has to hold
     */
    public void build() {
        for ( MenuObject m : items ) {
            if ( m.hasChildren() ) {
                m.build();
            }
            cellSize.setX(Math.max(m.getSize().getX(), cellSize.getX()));
            cellSize.setY(Math.max(m.getSize().getY(), cellSize.getY()));
        }

        sizeInPatches.setX(cellTable.getX() * cellSize.getX() + (cellTable.getX() - 1) * cellPadding.getX() + 2);
        sizeInPatches.setY(cellTable.getY() * cellSize.getY() + (cellTable.getY() - 1) * cellPadding.getY() + 2);

        numTotalRows = (itemPointer.size() / cellTable.getX()) + (((itemPointer.size() % cellTable.getX()) > 0 ) ? 1 : 0);
    }

}
