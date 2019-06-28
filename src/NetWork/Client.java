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
        Socket socket = new Socket("192.168.1.22", 1994);
        System.out.println("Connected!");
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream stream = new BufferedOutputStream(outputStream);
        ArrayList<File> fileArrayList = shared.getFiles();
        BufferedInputStream bufferedInputStream=new BufferedInputStream(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
        System.out.println("Sending files to the ServerSocket");
//        while () {
//
//        }
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
