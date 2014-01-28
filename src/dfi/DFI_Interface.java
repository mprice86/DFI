package dfi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

public class DFI_Interface extends javax.swing.JFrame implements Runnable {
    //Create threads to be started later
    private Thread Search;
    private Thread Sweep;
    private Thread PortScan;
    //Variables to keep track of running threads
    public static boolean searchStarted = false;
    public static boolean sweepStarted = false;
    public static boolean portscanStarted = false;
    //Strings to store MD5 values
    String MD2 = "";
    String MD1 = "";
    //Files to be used to access logs
    static File aFile = new File("SearchLog.txt");
    static File bFile = new File("PingLog.txt");
    //Strings to store ip addresses for ping sweep
    public static String ip1;
    public static String ip2;
    //variables to store ports and host name for port scan
    public static int port1 = 0;
    public static int port2 = 0;
    public static String hostAddress = "";
    //instantiates date time class
    getDateTime timeStamp = new getDateTime();

    public DFI_Interface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFile_FilePicker = new javax.swing.JFileChooser();
        jTPne_Features = new javax.swing.JTabbedPane();
        jPne_FileSearch = new javax.swing.JPanel();
        jTxt_Directory = new javax.swing.JTextField();
        jTxt_SearchTerm = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtn_Search = new javax.swing.JButton();
        jBtn_CancelSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTar_Output = new javax.swing.JTextArea();
        jBtn_Search_Reset = new javax.swing.JButton();
        jBtn_Search_Log = new javax.swing.JButton();
        jPne_FileDetails = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTxt_FilePicker = new javax.swing.JTextField();
        jBtn_PickFile = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTar_FileDetail_Output = new javax.swing.JTextArea();
        jBtn_FileDetail_Reset = new javax.swing.JButton();
        jPne_MD5 = new javax.swing.JPanel();
        jBtn_FilePickOne = new javax.swing.JButton();
        jTxt_FileOne = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jBtn_FilePickTwo = new javax.swing.JButton();
        jTxt_FileTwo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jBtn_Reset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTar_MD5_Output = new javax.swing.JTextArea();
        jPne_LocalMachine = new javax.swing.JPanel();
        jBtn_LM_Search = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTar_LM_Output = new javax.swing.JTextArea();
        jPne_PingSweep = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTxt_IPTwo = new javax.swing.JTextField();
        jTxt_IPOne = new javax.swing.JTextField();
        jBtn_IP_Search = new javax.swing.JButton();
        jBtn_IP_CancelSearch = new javax.swing.JButton();
        jBtn_IP_Reset = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTar_IP_Output = new javax.swing.JTextArea();
        jBtn_IP_Log = new javax.swing.JButton();
        jPne_PortScan = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTxt_Port_One = new javax.swing.JTextField();
        jTxt_Port_Host = new javax.swing.JTextField();
        jBtn_Port_Scan = new javax.swing.JButton();
        jBtn_Port_Cancel = new javax.swing.JButton();
        jBtn_Port_Reset = new javax.swing.JButton();
        jBtn_Port_Log = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTar_Port_Output = new javax.swing.JTextArea();
        jTxt_Port_Two = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLbl_Header = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTPne_Features.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTPne_FeaturesStateChanged(evt);
            }
        });

        jPne_FileSearch.setName("File Search"); // NOI18N

        jTxt_Directory.setText("C:\\");
            jTxt_Directory.setToolTipText("Enter the directory you want to scan");

            jTxt_SearchTerm.setText("[ENTER SEARCH TERM HERE]");
            jTxt_SearchTerm.setToolTipText("Enter the search term you want to look for");

            jLabel1.setText("Directory:");

            jLabel2.setText("Search Term:");

            jBtn_Search.setText("Search");
            jBtn_Search.setToolTipText("Start searching the directory");
            jBtn_Search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_SearchActionPerformed(evt);
                }
            });

            jBtn_CancelSearch.setText("Cancel");
            jBtn_CancelSearch.setToolTipText("Cancel the search");
            jBtn_CancelSearch.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_CancelSearch.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_CancelSearchActionPerformed(evt);
                }
            });

            jTar_Output.setEditable(false);
            jTar_Output.setColumns(20);
            jTar_Output.setRows(5);
            jTar_Output.setToolTipText("The file log will appear here");
            jScrollPane1.setViewportView(jTar_Output);

            jBtn_Search_Reset.setText("Reset");
            jBtn_Search_Reset.setToolTipText("Reset the form");
            jBtn_Search_Reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Search_ResetActionPerformed(evt);
                }
            });

            jBtn_Search_Log.setText("Show Log");
            jBtn_Search_Log.setToolTipText("Show file log in the text area below");
            jBtn_Search_Log.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Search_LogActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPne_FileSearchLayout = new javax.swing.GroupLayout(jPne_FileSearch);
            jPne_FileSearch.setLayout(jPne_FileSearchLayout);
            jPne_FileSearchLayout.setHorizontalGroup(
                jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                            .addComponent(jTxt_Directory, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jBtn_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                            .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTxt_SearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jBtn_Search_Log, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jBtn_CancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(jBtn_Search_Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())
            );
            jPne_FileSearchLayout.setVerticalGroup(
                jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_FileSearchLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_Directory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jBtn_Search))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_SearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jBtn_CancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_FileSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtn_Search_Reset)
                        .addComponent(jBtn_Search_Log))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("File Search", jPne_FileSearch);

            jPne_FileDetails.setName("File Details"); // NOI18N

            jLabel6.setText("Choose file:");

            jTxt_FilePicker.setEditable(false);
            jTxt_FilePicker.setToolTipText("Choose the file you want to examine by clicking the \"Choose File\" button to the right");

            jBtn_PickFile.setText("Choose File");
            jBtn_PickFile.setToolTipText("Open file browser to choose the file which you want to examine");
            jBtn_PickFile.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_PickFileActionPerformed(evt);
                }
            });

            jTar_FileDetail_Output.setEditable(false);
            jTar_FileDetail_Output.setColumns(20);
            jTar_FileDetail_Output.setRows(5);
            jTar_FileDetail_Output.setToolTipText("The file log will appear here once the file's details have been gathered");
            jScrollPane3.setViewportView(jTar_FileDetail_Output);

            jBtn_FileDetail_Reset.setText("Reset");
            jBtn_FileDetail_Reset.setToolTipText("Reset the form");
            jBtn_FileDetail_Reset.setPreferredSize(new java.awt.Dimension(87, 23));
            jBtn_FileDetail_Reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_FileDetail_ResetActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPne_FileDetailsLayout = new javax.swing.GroupLayout(jPne_FileDetails);
            jPne_FileDetails.setLayout(jPne_FileDetailsLayout);
            jPne_FileDetailsLayout.setHorizontalGroup(
                jPne_FileDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_FileDetailsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_FileDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3)
                        .addGroup(jPne_FileDetailsLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTxt_FilePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(jBtn_PickFile))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPne_FileDetailsLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jBtn_FileDetail_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPne_FileDetailsLayout.setVerticalGroup(
                jPne_FileDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_FileDetailsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_FileDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTxt_FilePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_PickFile))
                    .addGap(45, 45, 45)
                    .addComponent(jBtn_FileDetail_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("File Details", jPne_FileDetails);

            jPne_MD5.setName("MD5 Comparison"); // NOI18N

            jBtn_FilePickOne.setText("Choose File");
            jBtn_FilePickOne.setToolTipText("Open file browser to choose the first file");
            jBtn_FilePickOne.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_FilePickOneActionPerformed(evt);
                }
            });

            jTxt_FileOne.setEditable(false);
            jTxt_FileOne.setToolTipText("Choose the first file you want to examine by clicking the \"Choose File\" button to the right");

            jLabel4.setText("Choose original file:");

            jBtn_FilePickTwo.setText("Choose File");
            jBtn_FilePickTwo.setToolTipText("Open file browser to choose the second file");
            jBtn_FilePickTwo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_FilePickTwoActionPerformed(evt);
                }
            });

            jTxt_FileTwo.setEditable(false);
            jTxt_FileTwo.setToolTipText("Choose the second file you want to examine  by clicking the \"Choose File\" button to the right");

            jLabel5.setText("Choose alternate file:");

            jBtn_Reset.setText("Reset");
            jBtn_Reset.setToolTipText("Reset the form");
            jBtn_Reset.setPreferredSize(new java.awt.Dimension(87, 23));
            jBtn_Reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_ResetActionPerformed(evt);
                }
            });

            jTar_MD5_Output.setEditable(false);
            jTar_MD5_Output.setColumns(20);
            jTar_MD5_Output.setRows(5);
            jTar_MD5_Output.setToolTipText("The file log will appear here once the comparisson is complete");
            jScrollPane2.setViewportView(jTar_MD5_Output);

            javax.swing.GroupLayout jPne_MD5Layout = new javax.swing.GroupLayout(jPne_MD5);
            jPne_MD5.setLayout(jPne_MD5Layout);
            jPne_MD5Layout.setHorizontalGroup(
                jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_MD5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPne_MD5Layout.createSequentialGroup()
                            .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTxt_FileTwo, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                .addComponent(jTxt_FileOne))
                            .addGap(18, 18, 18)
                            .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jBtn_Reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtn_FilePickOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtn_FilePickTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())
            );
            jPne_MD5Layout.setVerticalGroup(
                jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_MD5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTxt_FileOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_FilePickOne))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_MD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTxt_FileTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_FilePickTwo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jBtn_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("MD5 Comparison", jPne_MD5);

            jPne_LocalMachine.setName("Local Machine Details"); // NOI18N

            jBtn_LM_Search.setText("Get Local Machine Details");
            jBtn_LM_Search.setToolTipText("Gets the hostname, IP Address, default gateway and MAC Address of the local machine");
            jBtn_LM_Search.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_LM_Search.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_LM_Search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_LM_SearchActionPerformed(evt);
                }
            });

            jTar_LM_Output.setEditable(false);
            jTar_LM_Output.setColumns(20);
            jTar_LM_Output.setRows(5);
            jTar_LM_Output.setToolTipText("The details of the local machine will appear here");
            jScrollPane5.setViewportView(jTar_LM_Output);

            javax.swing.GroupLayout jPne_LocalMachineLayout = new javax.swing.GroupLayout(jPne_LocalMachine);
            jPne_LocalMachine.setLayout(jPne_LocalMachineLayout);
            jPne_LocalMachineLayout.setHorizontalGroup(
                jPne_LocalMachineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_LocalMachineLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_LocalMachineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBtn_LM_Search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPne_LocalMachineLayout.setVerticalGroup(
                jPne_LocalMachineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_LocalMachineLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jBtn_LM_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(79, 79, 79)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("Local Machine Details", jPne_LocalMachine);

            jPne_PingSweep.setName("Ping Sweep"); // NOI18N

            jLabel7.setText("Starting IP Address:");

            jLabel8.setText("Ending IP Address:");

            jTxt_IPTwo.setText("10.238.215.153");
            jTxt_IPTwo.setToolTipText("Enter the ending IP Address");

            jTxt_IPOne.setText("10.238.215.151");
            jTxt_IPOne.setToolTipText("Enter the starting IP Address");

            jBtn_IP_Search.setText("Search");
            jBtn_IP_Search.setToolTipText("Start the Ping Sweep");
            jBtn_IP_Search.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Search.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_IP_SearchActionPerformed(evt);
                }
            });

            jBtn_IP_CancelSearch.setText("Cancel");
            jBtn_IP_CancelSearch.setToolTipText("Cancel the Ping Sweep");
            jBtn_IP_CancelSearch.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_CancelSearch.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_CancelSearch.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_IP_CancelSearchActionPerformed(evt);
                }
            });

            jBtn_IP_Reset.setText("Reset");
            jBtn_IP_Reset.setToolTipText("Reset the form");
            jBtn_IP_Reset.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Reset.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_IP_ResetActionPerformed(evt);
                }
            });

            jTar_IP_Output.setEditable(false);
            jTar_IP_Output.setColumns(20);
            jTar_IP_Output.setRows(5);
            jTar_IP_Output.setToolTipText("The ping log will be displayed here");
            jScrollPane4.setViewportView(jTar_IP_Output);

            jBtn_IP_Log.setText("Show Log");
            jBtn_IP_Log.setToolTipText("Show the ping log in the text area below");
            jBtn_IP_Log.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Log.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_IP_Log.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_IP_LogActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPne_PingSweepLayout = new javax.swing.GroupLayout(jPne_PingSweep);
            jPne_PingSweep.setLayout(jPne_PingSweepLayout);
            jPne_PingSweepLayout.setHorizontalGroup(
                jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4)
                        .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                            .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(24, 24, 24)
                                    .addComponent(jTxt_IPTwo))
                                .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jBtn_IP_Log, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jBtn_IP_CancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(jBtn_IP_Reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(jTxt_IPOne, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(jBtn_IP_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPne_PingSweepLayout.setVerticalGroup(
                jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_PingSweepLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_IPOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jBtn_IP_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_IPTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jBtn_IP_CancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_PingSweepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtn_IP_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_IP_Log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("Ping Sweep", jPne_PingSweep);

            jPne_PortScan.setName("Port Scan"); // NOI18N

            jLabel9.setText("Host Address:");

            jLabel10.setText("Starting Port:");

            jTxt_Port_One.setText("10");
            jTxt_Port_One.setToolTipText("Enter the starting port for the scan");

            jTxt_Port_Host.setText("10.238.215.151");
            jTxt_Port_Host.setToolTipText("Enter the IP Address of the host");

            jBtn_Port_Scan.setText("Search");
            jBtn_Port_Scan.setToolTipText("Start the scan");
            jBtn_Port_Scan.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Scan.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Scan.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Port_ScanActionPerformed(evt);
                }
            });

            jBtn_Port_Cancel.setText("Cancel");
            jBtn_Port_Cancel.setToolTipText("Cancel the ccan");
            jBtn_Port_Cancel.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Cancel.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Cancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Port_CancelActionPerformed(evt);
                }
            });

            jBtn_Port_Reset.setText("Reset");
            jBtn_Port_Reset.setToolTipText("Reset the form");
            jBtn_Port_Reset.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Reset.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Reset.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Port_ResetActionPerformed(evt);
                }
            });

            jBtn_Port_Log.setText("Show Log");
            jBtn_Port_Log.setToolTipText("Show the ping log in the text area below");
            jBtn_Port_Log.setMaximumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Log.setMinimumSize(new java.awt.Dimension(87, 23));
            jBtn_Port_Log.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jBtn_Port_LogActionPerformed(evt);
                }
            });

            jTar_Port_Output.setEditable(false);
            jTar_Port_Output.setColumns(20);
            jTar_Port_Output.setRows(5);
            jTar_Port_Output.setToolTipText("The results of the scan will be displayed here");
            jScrollPane6.setViewportView(jTar_Port_Output);

            jTxt_Port_Two.setText("200");
            jTxt_Port_Two.setToolTipText("Enter the ending port for the scan");

            jLabel11.setText("Ending Port:");

            javax.swing.GroupLayout jPne_PortScanLayout = new javax.swing.GroupLayout(jPne_PortScan);
            jPne_PortScan.setLayout(jPne_PortScanLayout);
            jPne_PortScanLayout.setHorizontalGroup(
                jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPne_PortScanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6)
                        .addGroup(jPne_PortScanLayout.createSequentialGroup()
                            .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPne_PortScanLayout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(21, 21, 21)
                                    .addComponent(jTxt_Port_One, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTxt_Port_Two, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPne_PortScanLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jBtn_Port_Log, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jBtn_Port_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtn_Port_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPne_PortScanLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jTxt_Port_Host)
                            .addGap(18, 18, 18)
                            .addComponent(jBtn_Port_Scan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPne_PortScanLayout.setVerticalGroup(
                jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPne_PortScanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxt_Port_Host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jBtn_Port_Scan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jBtn_Port_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxt_Port_Two, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTxt_Port_One, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPne_PortScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtn_Port_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtn_Port_Log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
            );

            jTPne_Features.addTab("Port Scan", jPne_PortScan);

            jLbl_Header.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            jLbl_Header.setText("File Search");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel3.setText("DFI ");
            jLabel3.setToolTipText("");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTPne_Features)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLbl_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLbl_Header)
                    .addGap(10, 10, 10)
                    .addComponent(jTPne_Features))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jBtn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_SearchActionPerformed
        // Sets a message in the output window
        jTar_Output.setText("Search started. A message will display when it is complete, then press the \"Show Log\" button to view the result");
        //if search is not already running
        if (!searchStarted) {
            //(new Thread(new FileSearch(jTxt_Directory.getText(), jTxt_SearchTerm.getText()))).start();
            // Starts search in new thread so user can continue to operate program while system is scanned.
            FileSearch SearchForFile = new FileSearch(jTxt_Directory.getText(), jTxt_SearchTerm.getText());
            Search = new Thread(SearchForFile);
            Search.start();
            searchStarted = true;
        } else {
            JOptionPane.showMessageDialog(null, "Search is already running.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtn_SearchActionPerformed

    private void jBtn_CancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_CancelSearchActionPerformed
        //if search running, run method to stop it
        if (Search.isAlive()) {
            //Aware that .stop is Deprecated however in the given time frame it was not
            //possible to find a suitablely effective replacement and .interrupt() could
            //not be implemented successfully as the thread does not rely on a loop.
            try {
                Search.stop();
                //Write message to the log then show the log in the output field
                WriteSearchLog.Write("");
                WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                WriteSearchLog.Write(timeStamp.get() + " - SEARCH FOR PHRASE '" + jTxt_SearchTerm.getText() + "' IN DIRECTORY '" + jTxt_Directory.getText() + "' WAS CANCELLED BY THE USER");
                WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                WriteSearchLog.Write("");

                jTar_Output.setText(WriteSearchLog.getContents(aFile));
                searchStarted = false;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An IOException occurred. \n"
                        + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtn_CancelSearchActionPerformed

    private void jTPne_FeaturesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTPne_FeaturesStateChanged
        //Sets the title to the name of the currently selected feature
        int index = jTPne_Features.getSelectedIndex();
        jLbl_Header.setText(jTPne_Features.getComponentAt(index).getName());
    }//GEN-LAST:event_jTPne_FeaturesStateChanged

    private void jBtn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_ResetActionPerformed
        //Resets all MD5 comparisson values on the form
        MD1 = "";
        MD2 = "";
        jTxt_FileOne.setText("");
        jTxt_FileTwo.setText("");
        jTar_MD5_Output.setText("");
    }//GEN-LAST:event_jBtn_ResetActionPerformed

    private void jBtn_FilePickTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_FilePickTwoActionPerformed
        //opens a new file picker and gets a return value based on user action
        int returnVal = jFile_FilePicker.showOpenDialog(this);
        //if they choose a file then get the file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFile_FilePicker.getSelectedFile();
            try {
                // display the file path in the textfield
                jTxt_FileTwo.setText(file.getAbsolutePath().toString());
            } finally {
                try {
                    //get the MD5 of the file and store it
                    MD2 = getMD5(file);
                    //if MD1 is not empty then
                    if (!MD1.equals("")) {
                        //check if they match and display corresponding message
                        if (MD1.equals(MD2)) {
                            writeMD5Log("THE COMPARISSON YIELDED A MATCH");
                        } else {
                            writeMD5Log("THE COMPARISSON DID NOT YIELD A MATCH");
                        }
                    }
                    //catch exceptions
                } catch (IOException | NoSuchAlgorithmException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred: \n"
                            + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            //if user cancels then do nothing
        }
    }//GEN-LAST:event_jBtn_FilePickTwoActionPerformed

    private void jBtn_FilePickOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_FilePickOneActionPerformed
        //opens a new file picker and gets a return value based on user action
        int returnVal = jFile_FilePicker.showOpenDialog(this);
        //if they choose a file then get the file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFile_FilePicker.getSelectedFile();
            try {
                // display the file path in the textfield
                jTxt_FileOne.setText(file.getAbsolutePath().toString());
            } finally {
                try {
                    //get the MD5 of the file and store it
                    MD1 = getMD5(file);
                    //if MD2 is not empty then
                    if (!MD2.equals("")) {
                        //check if they match and display corresponding message
                        if (MD1.equals(MD2)) {
                            writeMD5Log("THE COMPARISSON YIELDED A MATCH");
                        } else {
                            writeMD5Log("THE COMPARISSON DID NOT YIELD A MATCH");
                        }
                    }
                    //catch exceptions
                } catch (IOException | NoSuchAlgorithmException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred: \n"
                            + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            //if user cancels then do nothing
        }
    }//GEN-LAST:event_jBtn_FilePickOneActionPerformed

    private void jBtn_Search_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Search_ResetActionPerformed
        //resets the values and fields relevant to the file search
        jTar_Output.setText("");
        jTxt_Directory.setText("C:\\");
        jTxt_SearchTerm.setText("[ENTER SEARCH TERM HERE]");
    }//GEN-LAST:event_jBtn_Search_ResetActionPerformed

    private void jBtn_PickFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_PickFileActionPerformed
        //opens a new file picker and gets a return value based on user action
        int returnVal = jFile_FilePicker.showOpenDialog(this);
        //if they choose a file then get the file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFile_FilePicker.getSelectedFile();
            try {
                // display the file path in the textfield
                jTxt_FilePicker.setText(file.getAbsolutePath().toString());
            } finally {
                try {
                    if (searchStarted) {
                        JOptionPane.showMessageDialog(null, "Cannot get file details while search is running.");
                    } else {
                        //converts file to path
                        Path afile = file.toPath();
                        //get basic file attributes of the file
                        BasicFileAttributes fileAttributes = Files.readAttributes(afile, BasicFileAttributes.class);
                        //Writes to the log and then outputs log to GUI
                        WriteSearchLog.Write("");
                        WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                        WriteSearchLog.Write(timeStamp.get() + " - FILE DETAILS REQUESTED FOR '" + file + "'");
                        WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                        WriteSearchLog.Write("");
                        WriteSearchLog.Write(timeStamp.get() + " - FILE CREATED: " + fileDetailFormat(fileAttributes.creationTime().toString()));
                        WriteSearchLog.Write(timeStamp.get() + " - FILE LAST ACCESSED: " + fileDetailFormat(fileAttributes.lastAccessTime().toString()));
                        WriteSearchLog.Write(timeStamp.get() + " - FILE LAST MODIFIED: " + fileDetailFormat(fileAttributes.lastModifiedTime().toString()));
                        WriteSearchLog.Write("");
                        WriteSearchLog.Write("---------------------------------------------------------------------------------------");
                        WriteSearchLog.Write("");
                        jTar_FileDetail_Output.setText(WriteSearchLog.getContents(aFile));
                    }
                    //catch exceptions
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred. \n"
                            + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            //if user cancels then do nothing
        }
    }//GEN-LAST:event_jBtn_PickFileActionPerformed

    private void jBtn_IP_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_IP_SearchActionPerformed
        //if the sweep isn't already running then
        if (portscanStarted) {
            JOptionPane.showMessageDialog(null, "Cannot run ping sweep while port scan is running.");
        } else if (!sweepStarted) {
            //check that the two IPs are not blank
            if (jTxt_IPOne.getText().equals("") || jTxt_IPTwo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need to enter two IP Addresses before starting a sweep.");
                //and that they are valid addresses
            } else if (!validateIP(jTxt_IPOne.getText()) || !validateIP(jTxt_IPTwo.getText())) {
                JOptionPane.showMessageDialog(null, "You need to format IP Addresses correctly.");
            } else {
                //otherwise, show message and start the sweep
                jTar_IP_Output.setText("Ping Sweep started. A message will be displayed when it completes, then click \"Show Log\" to view the results");
                ip1 = jTxt_IPOne.getText();
                ip2 = jTxt_IPTwo.getText();
                sweepStarted = true;
                PingSweep runSweep = new PingSweep();
                Sweep = new Thread(runSweep);
                Sweep.start();
            }
        } else {
            //if sweep is already running show message
            JOptionPane.showMessageDialog(null, "Ping Sweep is already running.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtn_IP_SearchActionPerformed

    public boolean validateIP(String ip) {
        //Check that the IP Address has been correctly formatted
        StringTokenizer st = new StringTokenizer(ip, ".");
        int count = 0;
        //While there are more tokens in the string
        while (st.hasMoreTokens()) {
            //store the next character
            String token = st.nextToken();
            try {
                //if the character is not between 0 and 255 then it is not suitable
                int i = new Integer(token);
                if (i < 0 || i > 255) {
                    return false;
                }
                //if not a number then return false
            } catch (NumberFormatException e) {
                return false;
            }
            count++;
        }
        return count == 4;
    }

    private void jBtn_IP_CancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_IP_CancelSearchActionPerformed
        //if sweep is running, run method to stop it
        if (Sweep.isAlive() || sweepStarted) {
            try {
                //Aware that .stop is Deprecated however in the given time frame it was not
                //possible to find a suitablely effective replacement and .interrupt() could
                //not be implemented successfully.
                Sweep.stop();
                //Write message to the log then show the log in the output field
                WritePingLog.Write("");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write(timeStamp.get() + " - PING SWEEP BETWEEN '" + ip1 + "' AND '" + ip2 + "' WAS CANCELLED BY THE USER");
                WritePingLog.Write("---------------------------------------------------------------------------------------");
                WritePingLog.Write("");
                jTar_IP_Output.setText(WritePingLog.getContents(bFile));
                sweepStarted = false;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred. \n"
                        + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtn_IP_CancelSearchActionPerformed

    private void jBtn_IP_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_IP_ResetActionPerformed
        //Clear all fields and variables related to the IP Sweep
        jTxt_IPOne.setText("");
        jTxt_IPTwo.setText("");
        jTar_IP_Output.setText("");
        ip1 = "";
        ip2 = "";
    }//GEN-LAST:event_jBtn_IP_ResetActionPerformed

    private void jBtn_Search_LogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Search_LogActionPerformed
        //outputs search log to gui
        jTar_Output.setText(WriteSearchLog.getContents(aFile));
    }//GEN-LAST:event_jBtn_Search_LogActionPerformed

    private void jBtn_IP_LogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_IP_LogActionPerformed
        //outputs IP log to gui
        jTar_IP_Output.setText(WritePingLog.getContents(bFile));
    }//GEN-LAST:event_jBtn_IP_LogActionPerformed

    private void jBtn_LM_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_LM_SearchActionPerformed
        //if sweep or scan are running then show errors, otherwise run method
        if (sweepStarted) {
            JOptionPane.showMessageDialog(null, "Cannot run local machine test while Ping Sweep is running.");
        } else if (portscanStarted) {
            JOptionPane.showMessageDialog(null, "Cannot run local machine test while Port Scan is running.");
        } else {
            getLocalData getData = new getLocalData();
            jTar_LM_Output.setText(getData.run());
        }
    }//GEN-LAST:event_jBtn_LM_SearchActionPerformed

    private void jBtn_Port_LogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Port_LogActionPerformed
        //shows the ping log in the gui
        jTar_Port_Output.setText(WritePingLog.getContents(bFile));
    }//GEN-LAST:event_jBtn_Port_LogActionPerformed

    private void jBtn_Port_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Port_ResetActionPerformed
        //resets all fields and values relating to port scanner
        jTar_Port_Output.setText("");
        jTxt_Port_One.setText("");
        jTxt_Port_Two.setText("");
        jTxt_Port_Host.setText("");
        hostAddress = "";
        port1 = 0;
        port2 = 0;
    }//GEN-LAST:event_jBtn_Port_ResetActionPerformed

    private void jBtn_Port_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Port_CancelActionPerformed
        //if port scan is running then run method to stop it
        if (PortScan.isAlive()) {
            cancelPortButton();
        }
    }//GEN-LAST:event_jBtn_Port_CancelActionPerformed

    private void jBtn_Port_ScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_Port_ScanActionPerformed
        //if sweep is running then show error, otherwise run method if port scan is not already running
        jTar_Port_Output.setText("Search started. A message will display when it is complete, "
                + "then press the \"Show Log\" button to view the result");
        try {
            if (sweepStarted) {
                JOptionPane.showMessageDialog(null, "Cannot run port scan while Ping Sweep is running.");
            } else if (!portscanStarted) {
                //check all fields have been filled
                if (jTxt_Port_Host.getText().equals("") || jTxt_Port_One.getText().equals("") || jTxt_Port_Two.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "You need to complete all fields before beginning a scan.");
                    //check the ip address is valid
                } else if (!validateIP(jTxt_Port_Host.getText())) {
                    JOptionPane.showMessageDialog(null, "You need to format IP Addresses correctly.");
                    //check that the 2nd port is higher than the first
                } else if (Integer.parseInt(jTxt_Port_One.getText()) > Integer.parseInt(jTxt_Port_Two.getText())) {
                    JOptionPane.showMessageDialog(null, "The ending port needs to be of higer value than the beginning port.");
                    //check both ports are numerical
                } else if (Integer.parseInt(jTxt_Port_One.getText()) < 0 || Integer.parseInt(jTxt_Port_One.getText()) > 65535 || Integer.parseInt(jTxt_Port_Two.getText()) < 0 || Integer.parseInt(jTxt_Port_Two.getText()) > 65535) {
                    JOptionPane.showMessageDialog(null, "Ports must be between 0 and 65535.");
                } else {
                    //Sets public values for use in scan
                    hostAddress = jTxt_Port_Host.getText();
                    port1 = Integer.parseInt(jTxt_Port_One.getText());
                    port2 = Integer.parseInt(jTxt_Port_Two.getText());
                    // Starts search in new thread so user can continue to operate program while system is scanned.
                    PortScan scan = new PortScan();
                    PortScan = new Thread(scan);
                    portscanStarted = true;
                    PortScan.start();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Port Scan is already running.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Port numbers must be numerical.");
        }
    }//GEN-LAST:event_jBtn_Port_ScanActionPerformed

    private void jBtn_FileDetail_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_FileDetail_ResetActionPerformed
        //Clear fields relating to file details form
        jTxt_FilePicker.setText("");
        jTar_FileDetail_Output.setText("");
    }//GEN-LAST:event_jBtn_FileDetail_ResetActionPerformed

    private void cancelPortButton() {
        try {
            //Aware that .stop is Deprecated however in the given time frame it was not
            //possible to find a suitablely effective replacement and .interrupt() could
            //not be implemented successfully.
            PortScan.stop();
            //Write message to the log then show the log in the output field
            WritePingLog.Write("");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write(timeStamp.get() + " - PORT SCAN BETWEEN '" + port1 + "' AND '" + port2 + "' ON HOST '" + hostAddress + "' WAS CANCELLED BY THE USER");
            WritePingLog.Write("---------------------------------------------------------------------------------------");
            WritePingLog.Write("");
            jTar_Port_Output.setText(WritePingLog.getContents(bFile));
            portscanStarted = false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred. \n"
                    + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String fileDetailFormat(String details) {
        //formats the file details correctly for display
        details = details.replace("T", " - ");
        int i = details.length() - 8;
        details = details.substring(0, i);
        return details;
    }

    private void writeMD5Log(String MD5Result) throws IOException {
        //if search is not running then
        if (!searchStarted) {
            //writes the MD5 details of the files to the log then displays the log in GUI
            WriteSearchLog.Write("");
            WriteSearchLog.Write("---------------------------------------------------------------------------------------");
            WriteSearchLog.Write(timeStamp.get() + " - MD5 COMPARISSON PERFORMED ON '" + jTxt_FileOne.getText() + "' AND '" + jTxt_FileTwo.getText() + "'");
            WriteSearchLog.Write("---------------------------------------------------------------------------------------");
            WriteSearchLog.Write("");
            WriteSearchLog.Write(timeStamp.get() + " - HASH VALUES WERE '" + MD1 + "' AND '" + MD2 + "' RESPECTIVELY");
            WriteSearchLog.Write(timeStamp.get() + " - " + MD5Result);
            WriteSearchLog.Write("");
            WriteSearchLog.Write("---------------------------------------------------------------------------------------");
            WriteSearchLog.Write("");
            jTar_MD5_Output.setText(WriteSearchLog.getContents(aFile));
            //otherwise show error message
        } else {
            JOptionPane.showMessageDialog(null, "Cannot perform MD5 Comparisson while search is running.");
        }
    }

    //sub to get MD5 values of files 
    private String getMD5(File file) throws IOException, NoSuchAlgorithmException {
        //creates new message digest and file input stream
        MessageDigest mesDigest = MessageDigest.getInstance("MD5");
        FileInputStream fileIS = new FileInputStream(file.getAbsolutePath());

        byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = fileIS.read(dataBytes)) != -1) {
            mesDigest.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = mesDigest.digest();
        //convert the byte to hex format using string builder
        StringBuilder md5 = new StringBuilder();
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return (md5.toString());
    }

    @Override
    public void run() {
        //show the interface
        new DFI_Interface().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn_CancelSearch;
    private javax.swing.JButton jBtn_FileDetail_Reset;
    private javax.swing.JButton jBtn_FilePickOne;
    private javax.swing.JButton jBtn_FilePickTwo;
    private javax.swing.JButton jBtn_IP_CancelSearch;
    private javax.swing.JButton jBtn_IP_Log;
    private javax.swing.JButton jBtn_IP_Reset;
    private javax.swing.JButton jBtn_IP_Search;
    private javax.swing.JButton jBtn_LM_Search;
    private javax.swing.JButton jBtn_PickFile;
    private javax.swing.JButton jBtn_Port_Cancel;
    private javax.swing.JButton jBtn_Port_Log;
    private javax.swing.JButton jBtn_Port_Reset;
    private javax.swing.JButton jBtn_Port_Scan;
    private javax.swing.JButton jBtn_Reset;
    private javax.swing.JButton jBtn_Search;
    private javax.swing.JButton jBtn_Search_Log;
    private javax.swing.JButton jBtn_Search_Reset;
    private javax.swing.JFileChooser jFile_FilePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbl_Header;
    private javax.swing.JPanel jPne_FileDetails;
    private javax.swing.JPanel jPne_FileSearch;
    private javax.swing.JPanel jPne_LocalMachine;
    private javax.swing.JPanel jPne_MD5;
    private javax.swing.JPanel jPne_PingSweep;
    private javax.swing.JPanel jPne_PortScan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTPne_Features;
    private javax.swing.JTextArea jTar_FileDetail_Output;
    public javax.swing.JTextArea jTar_IP_Output;
    public javax.swing.JTextArea jTar_LM_Output;
    private javax.swing.JTextArea jTar_MD5_Output;
    public javax.swing.JTextArea jTar_Output;
    public javax.swing.JTextArea jTar_Port_Output;
    private javax.swing.JTextField jTxt_Directory;
    private javax.swing.JTextField jTxt_FileOne;
    private javax.swing.JTextField jTxt_FilePicker;
    private javax.swing.JTextField jTxt_FileTwo;
    private javax.swing.JTextField jTxt_IPOne;
    private javax.swing.JTextField jTxt_IPTwo;
    private javax.swing.JTextField jTxt_Port_Host;
    private javax.swing.JTextField jTxt_Port_One;
    private javax.swing.JTextField jTxt_Port_Two;
    private javax.swing.JTextField jTxt_SearchTerm;
    // End of variables declaration//GEN-END:variables
}