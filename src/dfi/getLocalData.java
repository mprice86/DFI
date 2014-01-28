package dfi;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class getLocalData {
    //Variables to store necessary values
    String MAC = "";
    String hostName = "";
    String ip = "";
    String defaultGateway = "";
    String gateway = "";
    //create new instance of date time class
    getDateTime timeStamp = new getDateTime();
    //sets file name
    static File bFile = new File("PingLog.txt");

    public String run() {
        try {
            //Create new address set to local host
            InetAddress inet = InetAddress.getLocalHost();
            //If the address is reachable...
            if (inet.isReachable(3000)) {
                // Add start information to the log
                WritePingLog.Write("");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write(timeStamp.get() + " - SCAN FOR LOCAL MACHINE DETAILS STARTED");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write("");
                
                //...get Host Name and IP...
                hostName = inet.getHostName();
                ip = inet.getHostAddress();
                //...print the Host Name...
                WritePingLog.Write(timeStamp.get() + " - HOST NAME: " + hostName);
                //...print the IP Address...
                WritePingLog.Write(timeStamp.get() + " - IP ADDRESS: " + ip); //want to remove length of host name from start of this
                //...get and print the Default Gateway...
                getDefaultGateway();
                //...get and print the MAC Address
                NetworkInterface ni = NetworkInterface.getByInetAddress(inet);
                if (ni != null) {
                    byte[] mac = ni.getHardwareAddress();
                    //formats the byte array into a usable string
                    for (int i = 0; i < mac.length; i++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        String macAdd = new String(sb);
                        MAC = MAC + macAdd;
                    }
                    WritePingLog.Write(timeStamp.get() + " - MAC ADDRESS: " + MAC);
                }
            } else {
                JOptionPane.showMessageDialog(null, "The host was not reachable.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "An unknown host exception occurred.\n"
                    + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An IO exception occurred.\n"
                    + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        scanComplete();
        return WritePingLog.getContents(bFile);
    }

    private void scanComplete() {
        //writes completed message to log
        try {
            WritePingLog.Write("");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write(timeStamp.get() + " - SCAN COMPLETED");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred. \n" + e, 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Sub to get the Default Gateway using a Netstat -rn command

    private void getDefaultGateway() throws UnknownHostException, IOException {
        //Variable to store output line by line
        String check;

        //Runs the netstat -rn command
        Process result = Runtime.getRuntime().exec("netstat -rn");
        //Captures the result of the netstat -rn command
        Scanner output = new Scanner(new InputStreamReader(result.getInputStream()));
        //Loops through the output to find the line beginning "0.0.0.0"
        for (int i = 0; i < 30; i++) {
            check = output.findInLine("0.0.0.0");
            //If matching text is not found then go to next line
            if (check == null) {
                output.nextLine();
            } //Otherwise, jump to next item and store the rest of the line
            else {
                output.next();
                gateway = output.nextLine();
                break;
            }
        }
        //Break the result out of the rest of the line - can be unreliable
        gateway = gateway.substring(4, 20);
        //Print the result
        WritePingLog.Write(timeStamp.get() + " - DEFAULT GATEWAY: " + gateway);
    }
}
