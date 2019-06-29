package NetWork;

import GUI.MainWindow;
import com.mpatric.mp3agic.Mp3File;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket s;
    private Mp3File song;
    private int sizeOfFile;

    public Client(String host, int port, String file) {
        try {
            s = new Socket(host, port);
            song = new Mp3File(file);
            sizeOfFile = (int) song.getLength();
            sendFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String file) throws IOException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        pw.println(song.getId3v1Tag().getTitle());
        pw.flush();
        pw.println(song.getLength());
        pw.flush();
        pw.println(MainWindow.getUserName());
        pw.flush();
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[sizeOfFile];
        dos.write(sizeOfFile);
        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }
        fis.close();
        dos.close();
    }

    public static void main(String[] args) {
        Client fc = new Client("localhost", 1988, "Over_the_Horizon.mp3");
    }

}