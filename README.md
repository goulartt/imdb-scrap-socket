# imdb-scrap-socket
A test case to scrap imdb and return titles listening from TCP port 

### Installation

This application has a client and a server app. The server app is a gradle project and the client is a normal java one.

Both projects connect into port 9876, if you have some app running in, stop it or change the code to run into another port.

After clone the project, you will first execute server jar:

```sh
$ cd /imdb-scrap-socket/imdb-query-server
$ java -jar server.jar
```

and after, you will execute the client:

```sh
$ cd /imdb-scrap-socket/imdb-query-client
$ java -jar client.jar
```

To import both projects on Eclipse, follow these steps:
Open Eclipse IDE and import Gradle project for imdb-query-server. 
Open Eclipse IDE and "Open Project From File System" for imdb-query-client. 

imdb-query-client - Run the main method from ImdbClient class.
imdb-query-server - Run the main method from ImdbServer class.

