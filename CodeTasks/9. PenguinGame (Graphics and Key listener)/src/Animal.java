import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

public abstract class Animal {
    protected int x, y;
    static String filename;
    protected File f;
    protected Image image;

    protected boolean alive = true;

    protected static Animal[][] antarktis;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean checkFields(int x, int y, Animal animal){  //checking danger
        if ((antarktis[teleport(x - 1)][y]!=null && antarktis[teleport(x - 1)][y].canEat(animal))||
                (antarktis[x][teleport(y -1 )]!=null && antarktis[x][teleport(y - 1)].canEat(animal))||
                (antarktis[teleport(x + 1)][y]!=null && antarktis[teleport(x + 1)][y].canEat(animal))||
                (antarktis[x][teleport(y + 1)]!=null && antarktis[x][teleport(y + 1)].canEat(animal)))
            return false;
        else return true;
    }

    public int teleport(int z){  //to teleport if an animal gets to the edge
        if(z < 0)
            return antarktis.length-1;
        if(z > antarktis.length-1)
            return 0;
        else return z;
    }

    public void move() { //checking safety and food. If food is found, exit from if-else. If not, we go to first safe place
        if(checkFields(teleport(this.x-1),this.y,this)&&
                antarktis[teleport(this.x-1)][this.y]!=null &&
                this.canEat(antarktis[teleport(this.x-1)][this.y])){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x-1)][this.y].alive = false;
            antarktis[teleport(this.x-1)][this.y]=this;
            this.x=teleport(this.x-1);
        }else if(checkFields(this.x, teleport(this.y-1), this)&&
                antarktis[this.x][teleport(this.y-1)]!=null &&
                this.canEat(antarktis[this.x][teleport(this.y-1)])){
            antarktis[this.x][this.y]=null;
            antarktis[this.x][teleport(this.y-1)].alive = false;
            antarktis[this.x][teleport(this.y-1)]=this;
            this.y=teleport(this.y-1);
        }else if(checkFields(teleport(this.x+1),this.y,this) &&
                antarktis[teleport(this.x+1)][this.y]!=null &&
                this.canEat(antarktis[teleport(this.x+1)][y])){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x+1)][this.y].alive = false;
            antarktis[teleport(this.x+1)][this.y]=this;
            this.x=teleport(this.x+1);
        }else if(checkFields(this.x, teleport(this.y+1), this)&&
                antarktis[this.x][teleport(this.y+1)]!=null &&
                this.canEat(antarktis[this.x][teleport(this.y+1)])){
            antarktis[this.x][this.y]=null;
            antarktis[this.x][teleport(this.y+1)].alive = false;
            antarktis[this.x][teleport(this.y+1)]=this;
            this.y=teleport(this.y+1);
        }else if(checkFields(teleport(this.x-1),this.y,this)&&
                antarktis[teleport(this.x-1)][y]==null){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x-1)][this.y]=this;
            this.x=teleport(this.x-1);
        }else if(checkFields(this.x, teleport(this.y-1), this)&&
                antarktis[x][teleport(this.y-1)]==null){
            antarktis[this.x][this.y]=null;
            antarktis[this.x][teleport(this.y-1)]=this;
            this.y=teleport(this.y-1);
        }else if(checkFields(teleport(this.x+1),this.y,this)&&
                antarktis[teleport(this.x+1)][y]==null){
            antarktis[this.x][this.y]=null;
            antarktis[teleport(this.x+1)][this.y]=this;
            this.x=teleport(this.x+1);
        }else if(checkFields(this.x, teleport(this.y+1), this)&&
                antarktis[x][teleport(this.y+1)]==null){
            antarktis[this.x][this.y]=null;
            antarktis[this.x][teleport(this.y+1)]=this;
            this.y=teleport(this.y+1);
        }else antarktis[this.x][this.y]=this;
    }

    public abstract boolean canEat(Animal animal);
    protected abstract boolean eatenBy(Penguin penguin);
    protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
    protected abstract boolean eatenBy(Whale whale);
    protected abstract boolean eatenBy(LeopardSeal leopardSeal);
    protected abstract boolean eatenBy(Fish fish);

    public static void setAntarktis(Animal[][] antarktis) {
        Animal.antarktis = antarktis;
    }
    // Graphics Stuff - You don't have to do anything here

    private void paintSymbol(Graphics g, Color c, int height, int width) {
        GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
        ((Graphics2D) g).setPaint(gradient);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
                (int) (height * 0.5));
    }

    public void draw(Graphics g, int height, int width) {
        if (image == null) {
            paintSymbol(g, Color.YELLOW, height, width);
            return;
        }
        ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
                image.getHeight(null), null);
    }
}