public class PlayerPenguin extends Penguin {
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
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean move(int newX, int newY){
        if(antarktis[newX][newY]!=null && this.canEat(antarktis[newX][newY])) {
            antarktis[newX][newY].alive = false;
            antarktis[this.x][this.y] = null;
            antarktis[newX][newY] = this;
            this.x = newX;
            this.y = newY;
        }else if(antarktis[newX][newY]== null){
            antarktis[this.x][this.y] = null;
            antarktis[newX][newY] = this;
            this.x = newX;
            this.y = newY;
        }else if(antarktis[newX][newY]!= null && !this.canEat(antarktis[newX][newY])) {
            antarktis[this.x][this.y] = null;
            antarktis[newX][newY] = this;
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}
