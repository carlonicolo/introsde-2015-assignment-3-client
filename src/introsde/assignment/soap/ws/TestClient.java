package introsde.assignment.soap.ws;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import introsde.assignment.soap.ws.Person.CurrentHealth;

public class TestClient{
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://vast-temple-7100.herokuapp.com/ws/people?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.soap.assignment.introsde/","PeopleService");
        Service service = Service.create(url, qname);

        People people = service.getPort(People.class);
        // read person with id 1
        
        final Person person = people.readPerson(2);
        System.out.println("First Person with id=2 before updating ==> " + person.getFirstname());
        
        person.setFirstname("Batman");

        final Holder<Person> holder = new Holder<Person>(person);
        people.updatePerson(holder);
        
        //System.out.println(people.getPeopleList());
        
        Person p = people.readPerson(2);
        System.out.println("First Person with id=2, after updating ==> " + p.getFirstname());
        // change name of the person with id 1
        //String uuid = UUID.randomUUID().toString();
        //p.setFirstname("Veabrechuk New name");
        
        System.out.println(people.getPeopleList());
        List<Person> p1 = people.getPeopleList();
        for(int i=0; i<p1.size(); i++){
        	System.out.println("Id " + p1.get(i).getIdPerson());
        	System.out.println("Firstname " + p1.get(i).getFirstname());
        	System.out.println("Lastname " + p1.get(i).getLastname());
        	System.out.println("Birthdate " + p1.get(i).getBirthdate());
        	if(p1.get(i).getCurrentHealth().getMeasure().isEmpty()){
        		System.out.println("No current Health");
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
        	System.out.println("===============================================");
        	
        	}
        	
        }
        
        //people.updatePerson(p);
        //people.updatePerson(p.);
        //people.updatePerson(p);
        // read again this person
        //p = people.readPerson(2);
        //System.out.println("First Person with id=1 ==> "+p.getFirstname());
    }
}