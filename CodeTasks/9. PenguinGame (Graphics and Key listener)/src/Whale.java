import java.awt.Toolkit;
import java.io.File;

public class Whale extends Animal {
    static String filename = "whale.png";
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
        return false;
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

    public Whale(int x, int y) {
        super(x, y);

        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}
