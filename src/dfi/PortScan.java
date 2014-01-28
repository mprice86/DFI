package dfi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class PortScan implements Runnable {
    //creates new date time class
    getDateTime timeStamp = new getDateTime();
    //counters
    private int portCount = 0;
    private int hostCount = 0;
    //Store host
    private String host = null;
    //Store ports
    private int port1 = DFI_Interface.port1;
    private int port2 = DFI_Interface.port2;

    @Override
    public void run() {
        //initialise variables
        InetAddress inet = null;

        try {
            //initialise host variable
            host = DFI_Interface.hostAddress;

            //if host has been set then
            if (host == null) {
                JOptionPane.showMessageDialog(null, "You need to enter a host address first.");
                DFI_Interface.portscanStarted = false;
            } else {
                //get host name
                inet = InetAddress.getByName(host);
            }

            if (!inet.isReachable(3000)) {
                JOptionPane.showMessageDialog(null, "The specified host was not reachable.");
                DFI_Interface.portscanStarted = false;
            } else {
                //Write start of scan to log
                WritePingLog.Write("");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write(timeStamp.get() + " - PORT SCAN ON HOST '" + host + "' BETWEEN PORT " + port1 + " AND PORT " + port2 + " STARTED");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write("");

                //run scan
                scan(inet, port1, port2);
            }
            //catch exceptions, display error messages
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "An UnknownHostException occurred. \n" + e, 
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An IOException occurred. \n" + e, 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public void scan(final InetAddress remote, int port1, int port2) throws IOException {
        //initialise variables
        int port = 0;
        String hostname = remote.getHostAddress();

        //loop until port1 <= port2
        for (port = port1; port <= port2; port++) {
            portCount++;
            try (Socket aSocket = new Socket(hostname, port)) {
                //if there is a response then write positive message to log, increase host count and close connection
                WritePingLog.Write(timeStamp.get() + " - SUCCESS - SERVER IS LISTENING ON PORT " + port + " OF " + hostname);
                aSocket.close();
                hostCount++;
            } catch (IOException e) {
                // if the remote host is not listening on this port write message to log and increase port count
                WritePingLog.Write(timeStamp.get() + " - FAILURE - SERVER IS NOT LISTENING ON PORT " + port + " OF " + hostname);
            }
        }
        //run method to write and display completion message
        scanComplete(host, port1, port2);
    }
    
    private void scanComplete(String host, int port1, int port2) {
        //write completion message to log and show message to user
        try {
            WritePingLog.Write("");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write(timeStamp.get() + " - PORT SCAN ON HOST '" + host + "' BETWEEN PORT " + port1 + " AND PORT " + port2 + " COMPLETED");
            WritePingLog.Write(timeStamp.get() + " - " + (portCount) + " PORTS WERE SCANNED, " + hostCount + " HOSTS WERE FOUND.");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write("");
            JOptionPane.showMessageDialog(null, "Scan Complete! \n\n"
                    + "PORTS SCANNED: " + (portCount) + "\nHOSTS FOUND: " + hostCount, 
                    timeStamp.get() + " - Sweep Complete", JOptionPane.DEFAULT_OPTION);
            DFI_Interface.portscanStarted = false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An IOException occurred. \n" + e, 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
