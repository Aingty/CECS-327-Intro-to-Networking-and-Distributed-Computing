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
        boolean portProvided = false; // Check if port ip was correct

        // Strings used in the program
        String hostIP=""; // Will become the user's inputted host ip address
        String serverPort=""; // Declaring Server port
        String userMessage="";
        String serverReply;

        System.out.println("\nWelcome to the UDP Client Program!!");
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
                            System.out.println("\nYou have entered an Invalid IP Address!!!!\n");
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
            if (!portProvided && ipProvided)
            {
                System.out.print("\tPlease provide Host Port Number: ");
                serverPort = input.nextLine();

                // Checking to see if port number is parsable into integer
                try
                {
                    Integer.parseInt(serverPort);
                    portProvided = true;
                }
                catch (NumberFormatException e)
                {
                    portProvided = false;
                    System.out.println("\nYou have entered an Invalid Port Number!!!!\n");
                }
            }
            
            // Program keeps going without establishing connection due to invalid ip or port
            if (!ipProvided || !portProvided)
            {
                continue;
            }
            try 
            {
                System.out.print("\n");
                System.out.print("\tEnter a message to Host (or quit): ");
                userMessage = input.nextLine();
                aSocket = new DatagramSocket();
                byte [] m = userMessage.getBytes();
                if (userMessage.equalsIgnoreCase("quit"))
                {
                    keepGoing = false;
                }
                InetAddress aHost = InetAddress.getByName(hostIP);
                DatagramPacket request = new DatagramPacket(m,  m.length, aHost, Integer.parseInt(serverPort));
                aSocket.send(request);
                buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                buffer = reply.getData();
                serverReply = new String(buffer, 0, reply.getLength());
                System.out.println("\t\tEcho from Server: " + serverReply);
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