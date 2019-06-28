package NetWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9488);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<File> receivedFiles = (ArrayList<File>) objectInputStream.readObject();
        System.out.println("Received [" + receivedFiles.size() + "] files from: " + socket);

        System.out.println("All files");
        for (File f : receivedFiles) {
            System.out.println(f.getName());
        }
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}
