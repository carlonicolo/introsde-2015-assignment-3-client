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

### Methods
Using JAX-WS, implement CRUD services for the following model including the following operations:

* **Method #1: readPersonList() => List** | should list all the people in the database (see below Person model to know what data to return here) in your database
* **Method #2: readPerson(Long id) => Person** | should give all the Personal information plus current measures of one Person identified by {id} (e.g., current measures means current healthProfile)
* **Method #3: updatePerson(Person p) => Person** | should update the Personal information of the Person identified by {id} (e.g., only the Person's information, not the measures of the health profile)
* **Method #4: createPerson(Person p) => Person** | should create a new Person and return the newly created Person with its assigned id (if a health profile is included, create also those measurements for the new Person).
* **Method #5: deletePerson(Long id)** should delete the Person identified by {id} from the system
* **Method #6: readPersonHistory(Long id, String measureType) => List** should return the list of values (the history) of {measureType} (e.g. weight) for Person identified by {id}
* **Method #7: readMeasureTypes() => List** should return the list of measures
* **Method #8: readPersonMeasure(Long id, String measureType, Long mid) => Measure** should return the value of {measureType} (e.g. weight) identified by {mid} for Person identified by {id}
* **Method #9: savePersonMeasure(Long id, Measure m)** =>should save a new measure object {m} (e.g. weight) of Person identified by {id} and archive the old value in the history
* **Method #10: updatePersonMeasure(Long id, Measure m) => Measure** | should update the measure identified with {m.mid}, related to the Person identified by {id}



## The code
In order to use the services offered by the server that i made and hosted on Heroku, i deployied this client taking advantages by the tool wsimport
that generated the classes from the WSLD http://vast-temple-7100.herokuapp.com/ws/people?wsdl :
```
/usr/lib/jvm/jdk1.8.0/bin/wsimport -keep http://vast-temple-7100.herokuapp.com/ws/people?wsdl
```
where "/usr/lib/jvm/jdk1.8.0/bin/wsimport" is the location of the tool in my case. </br>
***wsimport is located in the jdk folder***

After executing this tool i had all classes for starting to implement my own client able to consume the server services of my server hosted in heroku.</br>
The class [TestClient.java](https://github.com/carlonicolo/introsde-2015-assignment-3-client/blob/master/src/client/TestClient.java) is composed by a main and #11 methods that are all the methods requested by the assignment plus another one that give the possibility to create a person with healthprofile. </br>
This class has the methods all commented then i will only remark some important piece of code, before to show how to execute the client and explain looking at the code, how it works.

---
### Create the service
```java
URL url = new URL("http://vast-temple-7100.herokuapp.com/ws/people?wsdl");
//1st argument service URI, refer to wsdl document above
//2nd argument is service name, refer to wsdl document above
QName qname = new QName("http://ws.soap.assignment.introsde/","PeopleService");
Service service = Service.create(url, qname);
People people = service.getPort(People.class);
```
---
### Format the output
```java
public static void printMethod(String method_name, int method_number, String param_passed){
    System.out.println(" ");
    System.out.println("Method ==> [" + method_name + "]");
    System.out.println("Method# ==> [" + method_number + "]");
    System.out.println("Param ==> [" + param_passed + "]");
    System.out.println(" ");
    }
```
This function is used to format the output in a standard way.

---

### Use of the Holder
In the code below is showed the use of Holder and how i deal with it in order to perform the request3(People people, int id, String firstname), in which i executed the server method#3 **updatePerson(Person p)**, used to update the "firstname" of a person.

```
/**
     * This method is used to updated a person identified by "id"
     * using for exmaple a new "firstname".
     * Creates a new person with the new firstname and then executes the
     * method updatePerson(Person p)
     * 
     * Method#3: updatePerson(Person p)
     * 
     * @param people
     * @param id is the id of the person for which we want update the info
     * @param firstname is the new firstname
     * @throws ParseException_Exception
     */
    public static void request3(People people, int id, String firstname) throws ParseException_Exception{
    	String param = "personId: " + id; 
    	printMethod("updatePerson(Person p)", 3, param);
    	final Person person = people.readPerson(id);
        System.out.println("Person with id=2 before updating ==> " + person.getFirstname());
        
        person.setFirstname(firstname);

        final Holder<Person> holder = new Holder<Person>(person);
        people.updatePerson(holder);
        
        Person p = people.readPerson(id);
        System.out.println("Person with id=2, after updating ==> " + p.getFirstname());
        System.out.println(" ");
    }
```

## How to execute
To execute the client is necessery to perform these commands:
* clone the repository:
```
git clone https://github.com/carlonicolo/introsde-2015-assignment-3-client.git
```

* change the directory:
```
cd introsde-2015-assignment-3-client/
```

* execute the client using ant target execute.client
```
ant execute.client
```

Now in the folder will be created a file client.log where are stored all the informations about the executions requests and responses.
In the next section i will show some piece of the client.log file.


## How it works: log file examination
In this section i decided to show some parts of the [**log.file**](https://github.com/carlonicolo/introsde-2015-assignment-3-client/blob/master/client.log) for giving an idea of how the client works, how the log.file is structured and most important also domonstrate that the client works fine.</br>
The information showed below are written in the client.log[https://github.com/carlonicolo/introsde-2015-assignment-3-client/blob/master/client.log]

Below i execute the method#2 in the main of TestClient.java where i call the method request2(people, 2)

```java
request2(people, 2);
``` 

and this is the output as expected in the file client.log:

```
[java] #########################################################
[java]  
[java] Method ==> [readPerson(Long id)]
[java] Method# ==> [2]
[java] Param ==> [personId=2]
[java]  
[java] personId: 2
[java] Firstname: Rud
[java] Lastname: Gullit
[java] Birthdate: 1970-11-23
[java] *CurrentHealth*
[java] mid: 2
[java] dateRegistered: 2015-10-09
[java] measureType: weight
[java] measureValue: 78
[java] measureValueType: Int
[java]  
[java]  
[java] #########################################################
```

* Method: identifies the name of the service called readPerson(Long id)
* Method#: identifies the number of the method executed according with the list in the assignment, in this case 2.
* Param: identifies the param passed to the method readPerson(2) in this case

After these three tags there is the body response.
