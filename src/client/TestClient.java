package client;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import introsde.assignment.soap.ws.HealthMeasureHistory;
import introsde.assignment.soap.ws.ParseException_Exception;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.Person;
import introsde.assignment.soap.ws.Person.CurrentHealth;

public class TestClient{
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://vast-temple-7100.herokuapp.com/ws/people?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.soap.assignment.introsde/","PeopleService");
        Service service = Service.create(url, qname);

        People people = service.getPort(People.class);
        
        
        
        request1(people);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request2(people, 2);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request3(people,3,"Jacky");
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request4_1(people,"Billy", "Costacurta", "1972-10-11","2015-12-10","weight","72","Int"); 
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request4(people,"Francesco", "Totti", "1978-10-11"); 
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request5(people);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request6(people, 2, "weight");
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request7(people);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request8(people,2,"weight",5);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        String measureTypeParam = "weight";
        System.out.println(" ");
        System.out.println("#########################################################");
        request9(people, "2015-12-17", measureTypeParam, "188", "Int", 4253);
        System.out.println("#########################################################");
        System.out.println(" ");
        
        System.out.println(" ");
        System.out.println("#########################################################");
        request10(people, "2015-10-16", measureTypeParam, "190", "Integer", 4253);
        System.out.println("#########################################################");
        System.out.println(" ");      
    }
    
    //For each method - print into your log file methods name and number, parameters you passed and response you received
    
    public static void request1(People people){
    	printMethod("readPersonList()", 1, "no param requested");
    	List<Person> p1 = people.readPersonList();
        for(int i=0; i<p1.size(); i++){
        	System.out.println("personId: " + p1.get(i).getPersonId());
        	System.out.println("Firstname: " + p1.get(i).getFirstname());
        	System.out.println("Lastname: " + p1.get(i).getLastname());
        	System.out.println("Birthdate: " + p1.get(i).getBirthdate());
        	if(p1.get(i).getCurrentHealth().getMeasure().isEmpty()){
        		System.out.println("No CurrentHealth");
        		System.out.println(" ");
        		System.out.println(" ");
        	}else{
        	System.out.println("*CurrentHealth*");
        	CurrentHealth x = p1.get(i).getCurrentHealth();
        	List<HealthMeasureHistory> health = x.getMeasure();
        	for(int b=0; b<health.size();b++){
        		System.out.println("mid: " + health.get(b).getMid());
        		System.out.println("dateRegistered: " + health.get(b).getDateRegistered());
        		System.out.println("measureType: " + health.get(b).getMeasureType());
        		System.out.println("measureValue: " + health.get(b).getMeasureValue());
        		System.out.println("measureValueType: " + health.get(b).getMeasureValueType());
        	}
        	System.out.println(" ");
        	System.out.println(" ");
        	
        	}
        	
        }
    }
    
    
    
    //readPerson(Long id)
    public static void request2(People people, int id){
    	printMethod("readPerson(Long id)", 2, "personId=2");
    	Person p = people.readPerson(id);
        	System.out.println("personId: " + p.getPersonId());
        	System.out.println("Firstname: " + p.getFirstname());
        	System.out.println("Lastname: " + p.getLastname());
        	System.out.println("Birthdate: " + p.getBirthdate());
        	if(p.getCurrentHealth().getMeasure().isEmpty()){
        		System.out.println("No CurrentHealth");
        		System.out.println(" ");
        		System.out.println(" ");
        	}else{
        	System.out.println("*CurrentHealth*");
        	CurrentHealth x = p.getCurrentHealth();
        	List<HealthMeasureHistory> health = x.getMeasure();
        	for(int b=0; b<health.size();b++){
        		System.out.println("mid: " + health.get(b).getMid());
        		System.out.println("dateRegistered: " + health.get(b).getDateRegistered());
        		System.out.println("measureType: " + health.get(b).getMeasureType());
        		System.out.println("measureValue: " + health.get(b).getMeasureValue());
        		System.out.println("measureValueType: " + health.get(b).getMeasureValueType());
        	}
        	System.out.println(" ");
        	System.out.println(" ");
        	
        	}
        	
        }
    	
    public static void request3(People people, int id, String firstname) throws ParseException_Exception{
    	printMethod("updatePerson(Person p)", 3, "personId=2");
    	final Person person = people.readPerson(id);
        System.out.println("Person with id=2 before updating ==> " + person.getFirstname());
        
        person.setFirstname(firstname);

        final Holder<Person> holder = new Holder<Person>(person);
        people.updatePerson(holder);
        
        Person p = people.readPerson(id);
        System.out.println("Person with id=2, after updating ==> " + p.getFirstname());
        System.out.println(" ");
    }
    
    
    public static void request4_1(People people, String firstname, String lastname, String birthdate, String dateRegistered, String measureType, String measureValue, String measureValueType) throws ParseException_Exception{
    	String param =  "Firstname: " + firstname + ", Lastname: " + lastname + ", Birthdate: " + birthdate + ", dateRegistered: " + dateRegistered +  ", measureType: " + measureType + ", measureValue: " + measureValue + ", measureValueType: " + measureValueType;
    	printMethod("createFullPerson(Person p)", 11, param);
    	Person person = new Person();
    	person.setFirstname(firstname);
    	person.setLastname(lastname);
    	person.setBirthdate(birthdate);
    	
    	final Holder<Person> holder = new Holder<Person>(person);
    	//people.createPerson(holder);
    	
    	HealthMeasureHistory currentHealth = new HealthMeasureHistory();
    	currentHealth.setDateRegistered(dateRegistered);
    	currentHealth.setMeasureType(measureType);
    	currentHealth.setMeasureValue(measureValue);
    	currentHealth.setMeasureValueType(measureValueType);
    	
    	
    	people.createFullPerson(holder, currentHealth);
    	
    	
    	List<Person> person1 = people.readPersonList();
    	int size = person1.size() - 1;
    	int id_person = person1.get(size).getPersonId();
    	
    	
    	
    	Person p = people.readPerson(id_person);
    	System.out.println("personId: " + p.getPersonId());
    	System.out.println("Firstname: " + p.getFirstname());
    	System.out.println("Lastname: " + p.getLastname());
    	System.out.println("Birthdate: " + p.getBirthdate());
    	if(p.getCurrentHealth().getMeasure().isEmpty()){
    		System.out.println("No CurrentHealth");
    		System.out.println(" ");
    		System.out.println(" ");
    	}else{
    	System.out.println("*CurrentHealth*");
    	CurrentHealth x = p.getCurrentHealth();
    	List<HealthMeasureHistory> health = x.getMeasure();
    	for(int b=0; b<health.size();b++){
    		System.out.println("mid: " + health.get(b).getMid());
    		System.out.println("dateRegistered: " + health.get(b).getDateRegistered());
    		System.out.println("measureType: " + health.get(b).getMeasureType());
    		System.out.println("measureValue: " + health.get(b).getMeasureValue());
    		System.out.println("measureValueType: " + health.get(b).getMeasureValueType());
    		}
    	}
    	
    	/*
    	Person p = people.readPerson(id);
    	System.out.println("personId: " + p.getPersonId());
    	System.out.println("Firstname: " + p.getFirstname());
    	System.out.println("Lastname: " + p.getLastname());
    	System.out.println("Birthdate: " + p.getBirthdate());
    	System.out.println(" ");
    	*/
    	
    	
    }
    
    
    
    public static void request4(People people, String firstname, String lastname, String birthdate) throws ParseException_Exception{
    	String param =  "Firstname: " + firstname + ", Lastname: " + lastname + ", Birthdate: " + birthdate;
    	printMethod("createPerson(Person p)", 4, param);
    	Person person = new Person();
    	person.setFirstname(firstname);
    	person.setLastname(lastname);
    	person.setBirthdate(birthdate);
    	
    	final Holder<Person> holder = new Holder<Person>(person);
    	people.createPerson(holder);
    	
    	List<Person> person1 = people.readPersonList();
    	int size = person1.size() - 1;
    	int id = person1.get(size).getPersonId();
    	
    	Person p = people.readPerson(id);
    	System.out.println("personId: " + p.getPersonId());
    	System.out.println("Firstname: " + p.getFirstname());
    	System.out.println("Lastname: " + p.getLastname());
    	System.out.println("Birthdate: " + p.getBirthdate());
    	System.out.println(" ");
    
    }
    
    
    
    
    /**
     * In this method i execute the deletePerson(int id) method
     * To be sure that the id i'm passing trought the method exists 
     * i decided to choose an existing id and in particular the last personId
     * inserted
     * 
     * @param people
     */
    public static void request5(People people){
    	printMethod("deletePerson(int id)", 5, "last personId in the database");
    	List<Person> person = people.readPersonList();
    	int size = person.size() - 1;
    	int id = person.get(size).getPersonId();
    	people.deletePerson(id);
    	System.out.println("The person with id: " + id + " have been deleted.");
    	System.out.println(" ");
    }
    
    public static void request6(People people, int id, String measureType){
    	printMethod("readPersonHistory(int id, String measureType)", 6, "personId=2, measureType=weight");
    	List<HealthMeasureHistory> person_history = people.readPersonHistory(id, measureType);
    	for(int i=0; i<person_history.size(); i++){
    		System.out.println("mid: " + person_history.get(i).getMid());
    		System.out.println("dateRegistered: " + person_history.get(i).getDateRegistered());
    		System.out.println("measureType: " + person_history.get(i).getMeasureType());
    		System.out.println("measureValue: " + person_history.get(i).getMeasureValue());
    		System.out.println("measureValueType: " + person_history.get(i).getMeasureValueType());
    	}
    	
    	System.out.println(" ");
    }
    
    
    public static void request7(People people){
    	printMethod("readMeasureTypes()", 7, "no parameters");
    	List<String> measures = people.readMeasureTypes();
    	for (String element : measures) {
    	    System.out.println(element);
    	}
    	
    	System.out.println(" ");
    }
    
    //readPersonMeasure(Long id, String measureType, Long mid)
    public static void request8(People people, int id, String measureType, int mid){
    	String param = "personId= "+id+", measureType= "+measureType+", mid= "+mid;
    	printMethod("readPersonMeasure(int id, String measureType, int mid)", 8, param);
    	int measureValue = people.readPersonMeasure(2, measureType, 5);
    	System.out.println("measureValue: " + measureValue);
    	
    	System.out.println(" ");
    }
    
    
    public static void request9(People people, String dateRegistered, String measureType, String measureValue, String measureValueType, int id) throws ParseException_Exception{
    	String param =  "personId: " + id + ", dateRegistered: " + dateRegistered + ", measureType: " + measureType + ", measureValue: " + measureValue +  ", measureValueType: " + measureValueType ;
    	printMethod("savePersonMeasure(int id, Measure m)", 9, param);
    	
    	
    	HealthMeasureHistory measure_history = new HealthMeasureHistory();
    	measure_history.setDateRegistered(dateRegistered);
    	measure_history.setMeasureType(measureType);
    	measure_history.setMeasureValue(measureValue);
    	measure_history.setMeasureValueType(measureValueType);
    	
    	people.savePersonMeasure(id, measure_history);
    	
    	
    	List<HealthMeasureHistory> person_history = people.readPersonHistory(id, measureType);
    	int last_measure = person_history.size() - 1;

    	System.out.println("mid: " + person_history.get(last_measure).getMid());
    	System.out.println("dateRegistered: " + person_history.get(last_measure).getDateRegistered());
    	System.out.println("measureType: " + person_history.get(last_measure).getMeasureType());
    	System.out.println("measureValue: " + person_history.get(last_measure).getMeasureValue());
    	System.out.println("measureValueType: " + person_history.get(last_measure).getMeasureValueType());
    	
    	System.out.println(" ");

    	
    }
    
    
    
    
    //updatePersonMeasure(Long id, Measure m)
    
    public static void request10(People people, String dateRegistered, String measureType, String measureValue, String measureValueType, int id) throws ParseException_Exception{
    	String param =  "personId: " + id + ", dateRegistered: " + dateRegistered + ", measureType: " + measureType + ", measureValue: " + measureValue +  ", measureValueType: " + measureValueType ;
    	printMethod("updatePersonMeasure(int id, Measure m)", 10, param);
    	
    	List<HealthMeasureHistory> person_history_request9 = people.readPersonHistory(id, measureType);
    	int last_measure = person_history_request9.size() - 1;
    	
    	// In this way i'm sure that i will take the same mid that i used before
    	int mid = person_history_request9.get(last_measure).getMid();
    	
    	
    	HealthMeasureHistory measure_history = new HealthMeasureHistory();
    	measure_history.setMid(mid);
    	measure_history.setDateRegistered(dateRegistered);
    	measure_history.setMeasureType(measureType);
    	measure_history.setMeasureValue(measureValue);
    	measure_history.setMeasureValueType(measureValueType);
    	
    	final Holder<HealthMeasureHistory> holder = new Holder<HealthMeasureHistory>(measure_history);
        people.updatePersonMeasure(id, holder);
    	
        
        List<HealthMeasureHistory> person_history = people.readPersonHistory(id, measureType);
        for(int i=0; i<person_history.size(); i++){
        	if(person_history.get(i).getMid() == mid){
        		System.out.println("mid: " + person_history.get(i).getMid());
            	System.out.println("dateRegistered: " + person_history.get(i).getDateRegistered());
            	System.out.println("measureType: " + person_history.get(i).getMeasureType());
            	System.out.println("measureValue: " + person_history.get(i).getMeasureValue());
            	System.out.println("measureValueType: " + person_history.get(i).getMeasureValueType());
        	}
        }
    	
        System.out.println(" ");
    }
    
    
    
    public static void printMethod(String method_name, int method_number, String param_passed){
    	System.out.println(" ");
    	System.out.println("Method ==> [" + method_name + "]");
    	System.out.println("Method# ==> [" + method_number + "]");
    	System.out.println("Param ==> [" + param_passed + "]");
    	System.out.println(" ");
    }
    
    
    
    
}