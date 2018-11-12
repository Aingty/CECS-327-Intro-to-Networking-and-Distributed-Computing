import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client
{
    public static void main(String args[])
    { 
        // args give message contents and server hostname
        DatagramSocket aSocket = null;
        byte[] buffer;
        // Scanner for user's input
        Scanner input = new Scanner(System.in);

        // Booleans used to test different functionality through out the code
        boolean keepGoing = true; // Check to keep the whole program running
        boolean ipProvided = false; // Check if valid ip already provided

        // Strings used in the program
        String hostIP=""; // Will become the user's inputted host ip address
        String userMessage="";
        String serverReply;

        System.out.println("Welcome to the UDP Client Program!!");
        while (keepGoing)
        {
            if (!ipProvided)
            {
                ipProvided = true;
                System.out.print("\tPlease provide Host IP Address: ");
                hostIP = input.nextLine();

                // Validing user's IP address
                if (!hostIP.equalsIgnoreCase("localhost"))
                {
                    int ipCount = 1;
                    int decimalCount = 0;
                    for (int i = 0; i < hostIP.length(); i++)
                    {
                        if (hostIP.charAt(i) == '.' && ipCount != 1 && decimalCount < 3)
                        {
                            ipCount = 1;
                            decimalCount++;
                        }
                        else if (Character.isDigit(hostIP.charAt(i)) && ipCount < 4)
                        {
                            ipCount++;
                        }
                        else
                        {
                            System.out.println("You have entered an Invalid IP Address!!!!");
                            ipProvided = false;
                            break;
                        }
                    }
                }
                else
                {
                    hostIP = hostIP.toLowerCase();
                }
            }
            if (!ipProvided)
            {
                continue;
            }
            try 
            {
                aSocket = new DatagramSocket();
                byte [] m = userMessage.getBytes();
                System.out.print("\tEnter a message to Host (or quit): ");
                userMessage = input.nextLine();
                if (userMessage.equalsIgnoreCase("quit"))
                {
                    keepGoing = false;
                }
                InetAddress aHost = InetAddress.getByName(hostIP);
                int serverPort = 6789;
                DatagramPacket request = new DatagramPacket(m,  m.length, aHost, serverPort);
                aSocket.send(request);
                buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                buffer = reply.getData();
                serverReply = new String(buffer, 0, reply.getLength());
                System.out.println("Reply from Server: " + serverReply);
            }
            catch (SocketException e)
            {
                System.out.println("Socket: " + e.getMessage());
            }
            catch (IOException e)
            {
                System.out.println("IO: " + e.getMessage());
            }
            finally 
            {
                if(aSocket != null) 
                {
                    aSocket.close();
                }    
            }
        }
    } 
}