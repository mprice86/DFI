DFI
===

Windows based Digital Forensics Tool

Running the application will load a GUI based Digital Forensics Tool called DFI, or Defy, if you prefer.

The application can be used to carry out some basic digital forensic investigative proceedures on your local machine.

Different tools are accessed through the tabs and tool tips are available on every button and field, though their purpose should be fairly self explanatory. 

All tools automatically timestamp their actions and write to log files which can be found in the root of the application. 

===
1.0 - File Search
===

This tool allows the user to search any drive they choose for a search term. Text (.txt) files and image files (.jpg, .jpeg, .png, .bmp) are the targets in this first version of the application. Text files will be opened and their contents scanned for the search term. Image files will be checked to see if their filename contains the search term.

This search runs in its own thread to allow you to continue using the application while it runs.

===
2.0 - File Details
===

This tool allows the user to choose a file and find out the date that it was created, last accessed, and modified.

This tool cannot be run while the file search is running as it writes to the same log file.

===
3.0 - MD5 Comparison
===

This tool allows the user to select two files and will then compare the MD5 hash values to make sure that they are matches for each other. 

This tool cannot be run while the file search is running as it writes to the same log file.

===
4.0 - Local Machine Details
===

This tool allows the user to find out the hostname, IP Address, Default Gateway and MAC address of the local machine. 
As this is a networking tool, it can be run with the file search is running, however it cannot run while other networking tools are in operation. 

===
5.0 - Ping Sweep
===

This tool allows the user to scan a network range for devices and attempts to retrieve their MAC addresses.

This runs in its own thread so as not to conflict with other operations which the user may wish to carry out.

===
6.0 - Port Scan
===

This tool allows the user to scan a port range on a specific host, to determine whether the host is listening on that port.

This runs in its own thread so as not to conflict with other operations which the user may wish to carry out.

