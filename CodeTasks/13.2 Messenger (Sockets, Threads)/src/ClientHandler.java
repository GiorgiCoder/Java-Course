import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Random;

public class ClientHandler implements Runnable {

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    // Let's add some colors to the output :)

    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[32;1m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BRIGHT_WHITE = "\u001B[37;1m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BRIGHT_YELLOW = "\u001B[33;1m";
    private static final String ANSI_BRIGHT_RED = "\u001B[31;1m";
    private static final String ANSI_BRIGHT_PURPLE = "\u001B[35;1m";


    private String joiningTime;
    private Socket socket;
    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    public String username;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");

    public ClientHandler(Socket s) {
        try {
            this.socket = s;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            this.joiningTime = LocalTime.now().format(formatter);
            this.bufferedWriter.write(ANSI_BRIGHT_WHITE + "Welcome to the chat!" +
                    "\nHere are things you can do:\n1. Write 'WHOIS' to see all" +
                    " the other clients\n2. Write 'PINGU' to get one funny random fact about penguins :)" +
                    "\n3. Write 'LOGOUT' to leave the chat\n4. To write a direct message to someone, write " +
                    "@username message\n5. Type 'MONALISA' and a beautiful picture will be shown to you :)" +
                    "\n6. Write 'AUTOPORTRAIT' and program will draw an auto portrait of you!" +
                    "\n7. Write 'JEIRANI' to play Rock Paper Scissors against computer!\nHave fun!" + ANSI_RESET);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            clientHandlers.add(this);
            write( ANSI_CYAN + username + ANSI_RESET + " has joined the chat room");
        } catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }


    @Override
    public void run() {
        String message;
        while (socket.isConnected()) {
            try {
                message = bufferedReader.readLine();
                if (message.charAt(0) == '@') {
                    int indexOfSpace = message.indexOf(" ");
                    String[] arr = message.split(" ");
                    writeDM(this.username, arr[0].substring(1), message.substring(indexOfSpace + 1));
                } else if (message.equals("LOGOUT")) {
                    closeAll(this.socket, this.bufferedWriter, this.bufferedReader);
                } else if (message.equals("WHOIS")) {
                    whoIs();
                } else if (message.equals("PINGU")) {
                    penguinFacts();
                } else if (message.equals("MONALISA")) {
                    drawMonaLisa();
                }else if(message.equals("AUTOPORTRAIT")){
                    drawAutoPortrait();
                }else if(message.equals("JEIRANI")){
                    rockPaperScissors();
                }else {
                    write(message);
                }
            } catch (IOException e) {
                closeAll2(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }

    private void writeDM(String sender, String username, String messageToSend) {
        for (ClientHandler ch : clientHandlers) {
            try {
                if (ch.username.equals(username)) {
                    ch.bufferedWriter.write(ANSI_YELLOW + "DM from " + sender + ": " + ANSI_RESET + messageToSend);
                    ch.bufferedWriter.newLine();
                    ch.bufferedWriter.flush();
                    break;
                }
            } catch (IOException e) {
                closeAll(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    private void write(String messageToSend) {
        for (ClientHandler ch : clientHandlers) {
            try {
                ch.bufferedWriter.write(messageToSend);
                ch.bufferedWriter.newLine();
                ch.bufferedWriter.flush();
            } catch (IOException e) {
                closeAll(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    private void penguinFacts() {
        try {
            String[] funnyFacts = {
                    "Penguins don't fart (it's actually true)",
                    "Mr. Helmut somehow managed to make all the students hate penguins in just 3 months :)",
                    "Penguins poop every 20 minutes",
                    "Penguins don't have teeth",
                    "A group of penguins is called ,,a group of penguins'' *shocked*",
                    "Legally, a penguin is allowed to own a gun :)"
            };
            Random random = new Random();
            int index = random.nextInt(funnyFacts.length);
            this.bufferedWriter.write(ANSI_BLUE + funnyFacts[index] + ANSI_RESET);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }



    private void whoIs() {
        try {
            this.bufferedWriter.write(ANSI_PURPLE + "Online users:" + ANSI_RESET + "\n");
            for (ClientHandler ch : clientHandlers) {
                this.bufferedWriter.write(ch.username + " since " + ch.joiningTime);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    private void rockPaperScissors(){
        try {
            String[] rps = {"Rock", "Paper", "Scissors" };
            String pcMove = rps[new Random().nextInt(rps.length)];
            String playerMove;
            this.bufferedWriter.write("Enter your move: (Rock, Paper or Scissors)");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            while(true) {
                playerMove = this.bufferedReader.readLine();
                if(playerMove.equals("Rock") || playerMove.equals("Paper") || playerMove.equals("Scissors")){
                    break;
                }else{
                    this.bufferedWriter.write(ANSI_RED + playerMove +ANSI_RESET+ " is not a valid move! Try again: (Rock, Paper or Scissors)");
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }
            }
            this.bufferedWriter.write("Computer played " + ANSI_CYAN + pcMove + ANSI_RESET);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            this.bufferedWriter.write("You played " + ANSI_CYAN + playerMove + ANSI_RESET);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            if(playerMove.equals(pcMove)){
                this.bufferedWriter.write(ANSI_BRIGHT_PURPLE + "A tie!" + ANSI_RESET);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }else if(playerMove.equals("Rock")){
                if(pcMove.equals("Paper")){
                    this.bufferedWriter.write(ANSI_BRIGHT_RED + "You lose :(" + ANSI_RESET);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }else
                    this.bufferedWriter.write(ANSI_BRIGHT_GREEN + "You won :)" + ANSI_RESET);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }else if(playerMove.equals("Paper")){
                if(pcMove.equals("Scissors")){
                    this.bufferedWriter.write(ANSI_BRIGHT_RED + "You lose :(" + ANSI_RESET);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }else
                    this.bufferedWriter.write(ANSI_BRIGHT_GREEN + "You won :)" + ANSI_RESET);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }else if(playerMove.equals("Scissors")){
                if(pcMove.equals("Rock")){
                    this.bufferedWriter.write(ANSI_BRIGHT_RED + "You lose :(" + ANSI_RESET);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }else
                    this.bufferedWriter.write(ANSI_BRIGHT_GREEN + "You won :)" + ANSI_RESET);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
        }catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    private void drawMonaLisa(){
        try {
            this.bufferedWriter.write("          ____  \n" +
                    "        o8%8888,    \n" +
                    "      o88%8888888.  \n" +
                    "     8'-    -:8888b   \n" +
                    "    8'         8888  \n" +
                    "   d8.-=. ,==-.:888b  \n" +
                    "   >8 `~` :`~' d8888   \n" +
                    "   88         ,88888   \n" +
                    "   88b. `-~  ':88888  \n" +
                    "   888b ~==~ .:88888 \n" +
                    "   88888o--:':::8888      \n" +
                    "   `88888| :::' 8888b  \n" +
                    "   8888^^'       8888b  \n" +
                    "  d888           ,%888b.   \n" +
                    " d88%            %%%8--'-.  \n" +
                    "/88:.__ ,       _%-' ---  -  \n" +
                    "    '''::===..-'   =  --.  -\n" +
                    "I know it's weird :)");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    private void drawAutoPortrait(){
        try {
            this.bufferedWriter.write("⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠛⠛⠛⠋⠉⠈⠉⠉⠉⠉⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⡏⣀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿\n" +
                    "⣿⣿⣿⢏⣴⣿⣷⠀⠀⠀⠀⠀⢾⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿\n" +
                    "⣿⣿⣟⣾⣿⡟⠁⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣷⢢⠀⠀⠀⠀⠀⠀⠀⢸⣿\n" +
                    "⣿⣿⣿⣿⣟⠀⡴⠄⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⣿\n" +
                    "⣿⣿⣿⠟⠻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠶⢴⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⣿\n" +
                    "⣿⣁⡀⠀⠀⢰⢠⣦⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⡄⠀⣴⣶⣿⡄⣿\n" +
                    "⣿⡋⠀⠀⠀⠎⢸⣿⡆⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⠗⢘⣿⣟⠛⠿⣼\n" +
                    "⣿⣿⠋⢀⡌⢰⣿⡿⢿⡀⠀⠀⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣧⢀⣼\n" +
                    "⣿⣿⣷⢻⠄⠘⠛⠋⠛⠃⠀⠀⠀⠀⠀⢿⣧⠈⠉⠙⠛⠋⠀⠀⠀⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣧⠀⠈⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠟⠀⠀⠀⠀⢀⢃⠀⠀⢸⣿⣿⣿⣿\n" +
                    "⣿⣿⡿⠀⠴⢗⣠⣤⣴⡶⠶⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡸⠀⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⡀⢠⣾⣿⠏⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠉⠀⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣧⠈⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⡄⠈⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣦⣄⣀⣀⣀⣀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠙⣿⣿⡟⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠁⠀⠀⠹⣿⠃⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⢐⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⠿⠛⠉⠉⠁⠀⢻⣿⡇⠀⠀⠀⠀⠀⠀⢀⠈⣿⣿⡿⠉⠛⠛⠛⠉⠉\n" +
                    "⣿⡿⠋⠁⠀⠀⢀⣀⣠⡴⣸⣿⣇⡄⠀⠀⠀⠀⢀⡿⠄⠙⠛⠀⣀⣠⣤⣤⠄⠀");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }catch (IOException e) {
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    private void closeAll(Socket socket, BufferedWriter bf, BufferedReader br) {
        try {
            clientHandlers.remove(this);
            write(ANSI_RED + this.username + " has left the chat :(" + ANSI_RESET);
            if (socket != null) {
                socket.close();
            }
            if (bf != null) {
                bf.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //closeAll which doesn't print anything (needed in run method)
    private void closeAll2(Socket socket, BufferedWriter bf, BufferedReader br) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bf != null) {
                bf.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
