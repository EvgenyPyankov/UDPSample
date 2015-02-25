import java.io.*;
import java.net.*;

public class Client extends Thread{
    static int port = 8888;
    static DatagramSocket clientSocket;
    public static void main(String[] args){
        try {
            clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(100);
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                InetAddress IPAdress = InetAddress.getByName("localhost");
                byte[] sendData;
                byte[] receiveData = new byte[1024];
                String sentence = inFromUser.readLine();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String receivedSentence = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + receivedSentence);
        }
        catch (Exception e) {
            System.out.println("Time out!!");
        }
        finally {
            clientSocket.close();
        }
    }
}
