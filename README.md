# Introsde 2015 Assignment#3(CLIENT): SOAP Web Services
This is the third assignment for the "Introduction to Service Design and Engineering" course. The assignment is composed by two parts:
* The **Client** that call each of server implemented services and prints the result. ***This README is about the client***   
* The [**Server**](https://github.com/carlonicolo/introsde-2015-assignment-3) that exposes SOAP Web Services, and that is deployied on Heroku. [**Here**](https://github.com/carlonicolo/introsde-2015-assignment-3/blob/master/README.md) there is the README for the client.

In this assignment is requested to implement a server and a client calling this server. </br>
The server is deployed on Heroku.</br>
This assignment covers:
* [LAB07](https://github.com/IntroSDE/lab07): Reading and writing from Databases & JPA (Java Persistence API)
* [LAB08](https://github.com/IntroSDE/lab08): SOAP Web Services(1)
* [LAB09](https://github.com/IntroSDE/lab09): SOAP Web Services(2)



**Endpoint** </br>
**Service Name**:  http://ws.soap.assignment.introsde/}PeopleService </br> 
**Port Name**: http://ws.soap.assignment.introsde/}PeopleImplPort  </br>

**Information** </br>
**Address**: http://vast-temple-7100.herokuapp.com/ws/people </br>
**WSDL**: http://vast-temple-7100.herokuapp.com/ws/people?wsdl </br>

**Implementation class**: introsde.assignment.soap.ws.PeopleImpl

### Server
The Github repository of the server that i made to connect with this client is: [https://github.com/carlonicolo/introsde-2015-assignment-3](https://github.com/carlonicolo/introsde-2015-assignment-3)


## The code
In order to use the services offered by the server that i made and hosted on Heroku, 

## How to execute


## Log file examination











/usr/lib/jvm/jdk1.8.0/bin/wsimport -keep http://vast-temple-7100.herokuapp.com/ws/people?wsdl
 
