public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        setupMaze();
        Animal.antarktis = antarktis;
        gameLoop();
        closeFrame();
    }
    public int teleport(int z){  //to teleport if an animal gets to the edge
        if(z < 0)
            return antarktis.length-1;
        if(z > antarktis.length-1)
            return 0;
        else return z;
    }

    private static void gameLoop() {
        while (true) {
            while (currentEvent == NOTHING) {
                draw();
                if (currentEvent == LEFT)
                    playerPenguin.move(playerPenguin.teleport(playerPenguin.x - 1), playerPenguin.y);
                else if (currentEvent == UP)
                    playerPenguin.move(playerPenguin.x, playerPenguin.teleport(playerPenguin.y - 1));
                else if (currentEvent == RIGHT)
                    playerPenguin.move(playerPenguin.teleport(playerPenguin.x + 1), playerPenguin.y);
                else if (currentEvent == DOWN)
                    playerPenguin.move(playerPenguin.x, playerPenguin.teleport(playerPenguin.y + 1));

                if (playerPenguin.x == lostPenguin.x && playerPenguin.y == lostPenguin.y) {
                    System.out.println("YOU WON! Penguin won't be lonely anymore!");
                    System.exit(0);
                }
                if(!playerPenguin.alive)
                    System.exit(0);
                if(!playerPenguin.move(playerPenguin.x, playerPenguin.y))
                    System.exit(0);
            }
            moveAll();
            currentEvent = NOTHING;
        }
    }

    private static void moveAll() {
        for(int i=0; i< whales.length; i++){
            if(whales[i].alive)
                whales[i].move();
        }
        for(int i=0; i< leopardSeals.length; i++){
            if(leopardSeals[i].alive)
                leopardSeals[i].move();
        }
        lostPenguin.move();

        for(int i=0; i< fishes.length; i++){
            if(fishes[i].alive)
                fishes[i].move();
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }
        if(antarktis[20][20]!= null) {
            antarktis[20][20].alive = false;
            antarktis[20][20] = null;
        }
        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
