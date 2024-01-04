import java.awt.*;
import java.io.File;

public class LeopardSeal extends Animal {
    static String filename = "leopard.png";
    public void move() {
        super.move();
    }

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

    public LeopardSeal(int x, int y) {
        super(x, y);

        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}