import java.io.*;
import java.net.*;

public class Client extends Thread{
    static int port = 8888;
    public static void main(String[] args){
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(100);
            while (true) {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                InetAddress IPAdress = InetAddress.getByName("localhost");
                byte[] sendData = new byte[1024];
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
        }
        catch (Exception e){
            System.out.println("Time out!!");
        }
        //clientSocket.close();
    }
}
