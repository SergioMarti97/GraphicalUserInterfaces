import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Renderer;
import engine.gfx.images.Image;
import engine.vectors.points2d.Vec2di;
import retromenu.MenuObject;

public class RetroMenu extends AbstractGame {

    private MenuObject menuObject;

    private Image image;

    private RetroMenu(String title) {
        super(title);
    }

    @Override
    public void initialize(GameContainer gc) {

        image = new Image("/RetroMenu.png");

        menuObject = new MenuObject();

        //menuObject.addItem("Magic").addItem("Black").addItem("Fire").setId(101).setEnabled(false).setTable(2, 4);
        //menuObject.addItem("Magic").addItem("Black").addItem("Ice").setId(101).setEnabled(false).setTable(2, 4);
        menuObject.setTable(3, 5);
        menuObject.addItem("Attack").setId(101);
        menuObject.addItem("Magic").setId(102);
        menuObject.addItem("Defend").setId(103);
        menuObject.addItem("Items").setId(104);
        menuObject.addItem("Escape").setId(105);
        menuObject.addItem("Dummy1").setId(105);
        menuObject.addItem("Dummy2").setId(105);
        menuObject.addItem("Dummy3").setId(105);
        menuObject.addItem("Dummy4").setId(105);
        menuObject.addItem("Dummy5").setId(105);
        menuObject.addItem("Dummy6").setId(105);
        menuObject.addItem("Dummy7").setId(105);
        menuObject.addItem("Dummy8").setId(105);
        menuObject.addItem("Dummy9").setId(105);
        menuObject.build();
    }

    @Override
    public void update(GameContainer gc, float dt) {
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        menuObject.drawSelf(r, image, new Vec2di(10, 10));
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new RetroMenu("Retro Menu"));
        gc.start();
    }

}
