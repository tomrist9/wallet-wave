import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ourFirstServerSocker= new ServerSocket(6789);
        while(true){
            Socket connectionSocket=ourFirstServerSocker.accept();
            InputStream is=connectionSocket.getInputStream();
            InputStreamReader reader= new InputStreamReader(is);
            BufferedReader bufferedReadereader= new BufferedReader(reader);
            String messageFromClient= bufferedReadereader.readLine();
            System.out.println(messageFromClient);
        }
    }
}
