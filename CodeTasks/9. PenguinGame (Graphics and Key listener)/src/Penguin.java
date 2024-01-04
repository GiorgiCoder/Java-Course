import java.awt.Toolkit;
import java.io.File;

public class Penguin extends Animal {
    static String filename = "tux.png";
    @Override
    public boolean eatenBy(Penguin penguin){
        return false;
    }
    @Override
    public boolean eatenBy(PlayerPenguin playerPenguin){
        return false;
    }
    @Override
    public boolean eatenBy(Whale whale){
        return true;
    }
    @Override
    public boolean eatenBy(LeopardSeal leopardSeal){
        return false;
    }
    @Override
    public boolean eatenBy(Fish fish){
        return false;
    }
    public void move() {
        super.move();
    }

    public Penguin(int x, int y) {
        super(x, y);


        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}
