
import java.util.List;

public class Main {

    // Filter people using PersonRule
    public static List<Person> filterPeople(
            List<Person> people, PersonRule rule) {

        return people.stream()
                .filter(rule::test)
                .toList();
    }

    public static void main(String[] args) {

        PersonData data = new PersonData();

        // Rules
        PersonRule activeRule = person -> person.isActive();
        PersonRule adultRule = person -> person.getAge() >= 18;
        PersonRule stockholmRule = person ->
                person.getCity().equals("Stockholm");

        // Filter people
        List<Person> activePeople =
                filterPeople(data.getPeople(), activeRule);
        List<Person> adults =
                filterPeople(data.getPeople(), adultRule);
        List<Person> stockholmPeople =
                filterPeople(data.getPeople(), stockholmRule);


        System.out.println("Active people:");
        activePeople.forEach(System.out::println);

        System.out.println("\nAdults:");
        adults.forEach(System.out::println);

        System.out.println("\nPeople in Stockholm:");
        stockholmPeople.forEach(System.out::println);


        // Create two PersonAction lambdas

        // Action 1: Print name
        PersonAction printName = person ->
                System.out.println(person.getName());

        // Action 2: Print email message
        PersonAction sendEmail = person ->
                System.out.println("Send email to " + person.getName());


        // Execute actions on active people
        System.out.println("\nPrinting names of active people:");

        activePeople.forEach(printName::execute);

        System.out.println("\nSending emails to active people:");

        activePeople.forEach(sendEmail::execute);
    }
}
