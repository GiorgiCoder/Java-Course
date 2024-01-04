import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatClient {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    // Let's add some colors to the output :)
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private static final String ANSI_WHITE = "\u001B[37;1m";

    public static ArrayList<ChatClient> clients = new ArrayList<>();

    public ChatClient(Socket s, String username){
        try {
            this.socket = s;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(this);
        }catch(IOException e){
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    public void sendMessage(){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner sc = new Scanner(System.in);
            while(socket.isConnected()) {
                String message = sc.nextLine();
                if((!message.equals("") && message.charAt(0) == '@') || message.equals("LOGOUT") || message.equals("WHOIS") ||
                        message.equals("PINGU") || message.equals("MONALISA") || message.equals("AUTOPORTRAIT") || message.equals("JEIRANI") ||
                        message.equals("Rock") || message.equals("Paper") || message.equals("Scissors")) {
                    bufferedWriter.write(message);
                }else {
                    bufferedWriter.write(ANSI_CYAN + username + ANSI_RESET + ": " + message);
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(IOException e){
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    public void readMessage(){
        new Thread(() -> {
            String messageToRead;
            while(socket.isConnected()){
                try{
                    messageToRead = bufferedReader.readLine();
                    if(messageToRead.equals(ANSI_WHITE + "Welcome to the chat!")||
                            messageToRead.equals("Here are things you can do:") ||
                            messageToRead.equals("1. Write 'WHOIS' to see all the other clients") ||
                            messageToRead.equals("2. Write 'PINGU' to get one funny random fact about penguins :)") ||
                            messageToRead.equals("3. Write 'LOGOUT' to leave the chat") ||
                            messageToRead.equals("4. To write a direct message to someone, write @username message") ||
                            messageToRead.equals("5. Type 'MONALISA' and a beautiful picture will be shown to you :)") ||
                            messageToRead.equals("6. Write 'AUTOPORTRAIT' and program will draw an auto portrait of you!") ||
                            messageToRead.equals("Have fun!" + ANSI_RESET) ||
                            messageToRead.equals("7. Write 'JEIRANI' to play Rock Paper Scissors against computer!")){
                        System.out.println(messageToRead);
                    }else if(messageToRead!=null) {
                        System.out.println(LocalTime.now().format(formatter) + " " + messageToRead);
                    }
                }catch(IOException e){
                    closeAll(socket, bufferedWriter, bufferedReader);
                }
            }
        }).start();
    }

    private void closeAll(Socket socket, BufferedWriter bw, BufferedReader br){
        try {
            if (socket != null) {
                socket.close();
            }
            if (bw != null) {
                bw.close();
            }
            if(br != null){
                br.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException{
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String name = sc2.nextLine();
        Socket socket1 = new Socket("localhost", 3000);
        ChatClient client = new ChatClient(socket1, name);
        client.readMessage();
        client.sendMessage();
    }
}
