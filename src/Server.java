import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.lang.*;
import java.net.InetAddress;
import java.net.Socket;

public class Server extends Thread{
    static DatagramPacket receivePacket;
    static byte[]receiveData;
    static byte[]sendData;
    static DatagramSocket serverSocket;
    String sentence;
    static InetAddress IPAdress;
    static int port;

    public Server(String sentence){
        this.sentence=sentence;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void run()
    {
        try {
            sendData = sentence.toUpperCase().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);
            serverSocket.send(sendPacket);
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }
    public static void main(String[]args) throws Exception{
        serverSocket = new DatagramSocket(8888);
        sendData = new byte[1024];

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            IPAdress=receivePacket.getAddress();
            port=receivePacket.getPort();
            String sentence = new String(receivePacket.getData());
            System.out.println(sentence);
            new Server(sentence);
        }

    }
}
