# Zendesk-Ticket-Viewer
Welcome to my ticket viewer! It is a CLI based viewer that connects to the Zendesk API.

# Credentials
This program takes advantage of System Variables so, make sure to add system variables called "SUBDOMAIN" & "OAUTHTOKEN" with your subdomain and oauth token respectively. For instructions on how to add system variables, please go to this link for Windows: http://www.dowdandassociates.com/blog/content/howto-set-an-environment-variable-in-windows-command-line-and-registry/ and go to this one for Mac: https://phoenixnap.com/kb/set-environment-variable-mac

# Requirements:  
- The easiest option to run the program is to download vscode and follow the instructions on this website: https://code.visualstudio.com/docs/java/java-tutorial. Then download the libraries used(Referenced Below) and add them to your referenced libraries. <br />
- The second option would be to use an IDE of your choice and after downloading the code, the libraries(Referenced Below) and adding the libraries to your build/class path you can hit run on your respective IDE and you should be good to go.

# Libraries
-org.json.jar Download: http://www.java2s.com/Code/Jar/o/Downloadorgjsonjar.htm <br />
-junit-platform-console-standalone-1.7.0-M1.jar Download: https://search.maven.org/artifact/org.junit.platform/junit-platform-console-standalone/1.7.0-M1/jar <br />
-jar_files systemrules\\system-rules-1.16.0.jar Download: https://jar-download.com/artifacts/com.github.stefanbirkner/system-rules/1.16.0/source-code <br />

# Usage
After running the program you will be greeted and asked to choose one of the options displayed. The options are self explanatory and look something like this: (notice I clicked 1 and as the option says, you can view the tickets starting at page 1) You get a maximum of 25 tickets per page. The program fetches the tickets from the api often so you can be assured that the information is up to date!
![ticketviewer 1](https://user-images.githubusercontent.com/61366482/143821114-f7c29f90-985b-47a7-b269-f6583f4a8283.PNG)
