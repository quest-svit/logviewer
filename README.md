# logViewer Utility


This is a simple utility to view log files on the web-browser.
Typically on the unix/linux hosts, to view the log files , we need to login to the host and view using vi editor or cat/tail unix commands.
If we have a group of Hosts, then viewing the log files is even difficult.

Also if we are using Docker Containers,we need to either map local host folder into the container to put the log files.

This utility is written in springboot.

Running this utlity along side your other applicatios, you can view any log files of the application using the browser.

Features:
You can view log file from TOP/Bottom using direction=HEAD and direction=TAIL
You can prefer to see only n lines of the log file using lines=n e.g. lines=20 to view 20 lines.

Just start the utility and open web browser
https://localhost:8084/viewLog?filePath=<Path to LogFile>/logs/logback.log&lines=20&direction=TAIL
