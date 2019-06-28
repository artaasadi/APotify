package NetWork;

import logic.FileReader;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader();
        SharedPlayList shared = new SharedPlayList();
        for (int i = 0; i < fr.getFile().length; i++) {
            shared.addSong(fr.getFile()[i]);
        }
        Socket socket = new Socket("172.23.157.249", 9488);
        System.out.println("Connected!");
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ArrayList<File> fileArrayList = shared.getFiles();
        System.out.println("Sending files to the ServerSocket");
        objectOutputStream.writeObject(fileArrayList);
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
