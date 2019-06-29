package NetWork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static String friendName = null;
    private ServerSocket ss;
    private static String in;
    private static boolean isFinished = false;

    public Server(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
        in = br.readLine();
        int bytes = Integer.parseInt(br.readLine());
        friendName = br.readLine();
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream(in.concat(".mp3"));
        in = in.concat(".mp3");
        byte[] buffer = new byte[bytes];

        int filesize = bytes; // Send file size in separate msg
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
        }
        isFinished = true;
        fos.close();
        dis.close();
    }

//    public static void main(String[] args) {
//        Server fs = new Server(1988);
//        fs.start();
//    }

    public static String getFriendName() {
        return friendName;
    }

    public static String getIn() {
        return in;
    }

    public static boolean isIsFinished() {
        return isFinished;
    }
}