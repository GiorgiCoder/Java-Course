import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
    static String filename = "fish.png";
    @Override
    public boolean eatenBy(Penguin penguin){
        return true;
    }
    @Override
    public boolean eatenBy(PlayerPenguin playerPenguin){
        return true;
    }
    @Override
    public boolean eatenBy(Whale whale){
        return false;
    }
    @Override
    public boolean eatenBy(LeopardSeal leopardSeal){
        return true;
    }
    @Override
    public boolean eatenBy(Fish fish){
        return false;
    }

    public Fish(int x, int y) {
        super(x, y);

        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }

    @Override
    public void move() {  //Fish has different movement, so we override the move method
        if(checkFields(this.x, teleport(this.y-1), this)&&
                antarktis[this.x][teleport(this.y-1)]==null){
            antarktis[this.x][this.y]=null;
            antarktis[this.x][teleport(this.y-1)]=this;
            y=teleport(this.y-1);
        }else if(checkFields(teleport(this.x+1),this.y,this)&&
                antarktis[teleport(this.x+1)][this.y]==null){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x+1)][this.y]=this;
            x=teleport(x+1);
        }else if(checkFields(this.x, teleport(this.y+1), this)&&
                antarktis[this.x][teleport(this.y+1)]==null){
            antarktis[this.x][this.y] = null;
            antarktis[this.x][teleport(this.y + 1)] = this;
            y=teleport(this.y+1);
        }else if(checkFields(teleport(this.x-1),this.y,this)&&
                antarktis[teleport(this.x-1)][this.y]==null){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x-1)][this.y]=this;
            x=teleport(x-1);
        }else antarktis[this.x][this.y]=this;
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}