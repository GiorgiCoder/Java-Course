import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// to run the code, first run this class. Then run ChatClient class n (number of users you want) times. Enter usernames for all
// users and use all the functions that are suggested to you :)
public class ChatServer {

    private ServerSocket serverSocket;

    // :)
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");

    public ChatServer(ServerSocket ss){
        this.serverSocket = ss;
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket s = serverSocket.accept();
                System.out.println(LocalTime.now().format(formatter) + " A new client has joined!");
                ClientHandler ch = new ClientHandler(s);
                Thread t = new Thread(ch);
                ExecutorService threadPool = Executors.newFixedThreadPool(50);
                ThreadPoolExecutor executor = (ThreadPoolExecutor) threadPool;
                executor.setMaximumPoolSize(50);
                t.start();

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeServer(){
        try {
            if(serverSocket != null)
                serverSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket1 = new ServerSocket(3000);
        ChatServer server = new ChatServer(serverSocket1);
        server.startServer();

    }

}