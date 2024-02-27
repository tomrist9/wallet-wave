package tcp_client;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("localhost", 6789);

        OutputStream outputStream=socket.getOutputStream();
        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);

    outputStream.write("hey~".getBytes());
    socket.close();


    }
}
