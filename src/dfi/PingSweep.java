package dfi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class PingSweep implements Runnable {
    //creates new date time class
    getDateTime timeStamp = new getDateTime();
    
    @Override
    public void run() {
        //initialise ip addresses
        IPAddress iP1 = new IPAddress(DFI_Interface.ip1);
        IPAddress iP2 = new IPAddress(DFI_Interface.ip2);

        //Store IP octets to integers
        int ip1a = iP1.getOctet(3), ip1b = iP1.getOctet(2), 
                ip1c = iP1.getOctet(1), ip1d = iP1.getOctet(0);

        int ip2a = iP2.getOctet(3), ip2b = iP2.getOctet(2), 
                ip2c = iP2.getOctet(1), ip2d = iP2.getOctet(0);

        //This IF statement validates that the user has entered a valid IP range
        //i.e. that the ending IP address is within the same network class and is 
        //correctly formatted, and comes after the starting IP address

        //Class A networks
        if (ip1a >= 0 && ip1a <= 127) {
            if (ip1a == ip2a) {
                if (ip1b <= ip2b) {
                    if (ip1c <= ip2c) {
                        if (ip1d < ip2d) {
                            sweepIP(iP1, iP2);
                        } else {
                            JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                            DFI_Interface.sweepStarted = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                        DFI_Interface.sweepStarted = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                    DFI_Interface.sweepStarted = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is a Class A network - The first octets must match.");
                DFI_Interface.sweepStarted = false;
            }
        // Class B networks
        } else if (ip1a >= 128 && ip1a <= 191) {
            if (ip1a == ip2a && ip1b == ip2b) {
                if (ip1c <= ip2c) {
                    if (ip1d < ip2d) {
                        sweepIP(iP1, iP2);
                    } else {
                        JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                        DFI_Interface.sweepStarted = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                    DFI_Interface.sweepStarted = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is a Class B network - The first and second octets must match.");
                DFI_Interface.sweepStarted = false;
            }
        //Class C networks
        } else if (ip1a >= 192 && ip1a <= 223) {
            if (ip1a == ip2a && ip1b == ip2b && ip1c == ip2c) {
                if (ip1d < ip2d) {
                    sweepIP(iP1, iP2);
                } else {
                    JOptionPane.showMessageDialog(null, "End IP must come after Start IP to be a valid range.");
                    DFI_Interface.sweepStarted = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is a Class C network - The first, second and third octets must match.");
                DFI_Interface.sweepStarted = false;
            }
        //Class D
        } else if (iP1.getOctet(0) >= 224 && iP1.getOctet(0) <= 239 || 
                iP2.getOctet(0) >= 224 && iP2.getOctet(0) <= 239) {
            JOptionPane.showMessageDialog(null, "Please enter a valid IP address starting between 0 and 223");
            DFI_Interface.sweepStarted = false;
        //Class E
        } else if (iP1.getOctet(0) >= 240 && iP1.getOctet(0) <= 255 || 
                iP2.getOctet(0) >= 224 && iP2.getOctet(0) <= 239) {
            JOptionPane.showMessageDialog(null, "Please enter a valid IP address starting between 0 and 223");
            DFI_Interface.sweepStarted = false;
        //Out of IP ranges
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid IP address starting between 0 and 223");
            DFI_Interface.sweepStarted = false;
        }
    }

    private void sweepIP(IPAddress ip1, IPAddress ip2) {
        //Will hold MAC Address later
        String MAC = "";
        //Store the original IP addresses for later use
        String oldIP1 = ip1.toString();
        String oldIP2 = ip2.toString();
        //counters
        int scannedCount = 0;
        int foundCount = 0;

        try {
            //Write the start of the sweep to the log
            WritePingLog.Write("");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write(timeStamp.get() + " - PING SWEEP STARTED BETWEEN: '" + ip1 + "' AND  '" + ip2 + "'");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write("");

            //increase value of IP2 to ensure that the final IP is checked before the thread ends
            ip2 = ip2.next();

            do {
                //inet address to hold IP value
                InetAddress inet;
                //whether the pinged address responds
                boolean pingResult = false;

                try {
                    //checks if the host is reachable
                    inet = InetAddress.getByName(ip1.toString());
                    pingResult = (inet.isReachable(5000));
                    scannedCount++;
                    //if not then writes failed message to log
                    if (!pingResult) {
                        WritePingLog.Write(timeStamp.get() + " - THE HOST AT: " + ip1.toString() + " WAS NOT REACHABLE.");
                    } else {
                        //otherwise, gets MAC address of host and writes success message to log
                        NetworkInterface ni = NetworkInterface.getByInetAddress(inet);
                        if (ni != null) {
                            //stores mac in byte array
                            byte[] mac = ni.getHardwareAddress();
                            //formats the byte array into a usable string
                            for (int i = 0; i < mac.length; i++) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                                String macAdd = new String(sb);
                                MAC = MAC + macAdd;
                            }
                        }
                        //N.B. MAC Addresses cannot be retrieved over WiFi, through an IP Router, or over any other Layer 3 device.
                        WritePingLog.Write(timeStamp.get() + " - THE HOST AT: " + ip1.toString() + " WAS REACHABLE - DEVICE MAC ADDRESS: " + MAC);
                        MAC = "";
                        foundCount++;
                    }
                    //increments the IP address by one
                    ip1 = ip1.next();
                } catch (UnknownHostException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } while (!ip1.equals(ip2));
            //Once ip1 = ip2 runs method to write completion of sweep to log
            sweepComplete(oldIP1, oldIP2, scannedCount, foundCount);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sweepComplete(String ip1, String ip2, int hostsScanned, int hostsFound) throws IOException {
        //Writes a completion message to the log and displays a message to the user.
        WritePingLog.Write("");
        WritePingLog.Write("---------------------------------------------------------------------------------------");
        WritePingLog.Write(timeStamp.get() + " - SWEEP COMPLETED BETWEEN '" + ip1 + "' AND '" + ip2 + "'");
        WritePingLog.Write(timeStamp.get() + " - HOSTS SCANNED: " + hostsScanned + " | HOSTS FOUND: " + hostsFound);
        WritePingLog.Write("---------------------------------------------------------------------------------------");
        WritePingLog.Write("");
        JOptionPane.showMessageDialog(null, "Sweep Complete! \n\n"
                + "HOSTS SCANNED: " + hostsScanned + "\nHOSTS FOUND: " + hostsFound, 
                timeStamp.get() + " - Sweep Complete", JOptionPane.DEFAULT_OPTION);
        DFI_Interface.sweepStarted = false;
    }
}
