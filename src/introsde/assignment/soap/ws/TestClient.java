package introsde.assignment.soap.ws;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

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
        
        person.setFirstname("robin");

        final Holder<Person> holder = new Holder<Person>(person);
        people.updatePerson(holder);
        
        
        
        Person p = people.readPerson(2);
        System.out.println("First Person with id=2, after updating ==> " + p.getFirstname());
        // change name of the person with id 1
        //String uuid = UUID.randomUUID().toString();
        //p.setFirstname("Veabrechuk New name");
        
        
        //people.updatePerson(p);
        //people.updatePerson(p.);
        //people.updatePerson(p);
        // read again this person
        //p = people.readPerson(2);
        //System.out.println("First Person with id=1 ==> "+p.getFirstname());
    }
}