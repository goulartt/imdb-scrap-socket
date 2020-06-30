# imdb-scrap-socket
A test case to scrap imdb and return titles listening from TCP port 

### Installation

This application has a client and a server app. The server app is a gradle project and the client is a normal java one.

Both projects connect into port 9876, if you have some app running in stop it or change the code to run into another port.

Open Eclipse IDE and import Gradle project to server app, for client app jusst open as "Open Project From File System". Run the main method from ImdbClient to Client App and Run the main method from ImdbServer to Server App.