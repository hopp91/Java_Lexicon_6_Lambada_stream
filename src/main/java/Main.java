
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

        // Create two PersonAction

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


        //PART 2 TASK
        //Filter all active people.
        //Map all names.
        //Count adults.
        //Sort by age.
        //Find first active person in Stockholm.

        // 1. Filter all active people
        System.out.println("\n1. Active people:");

        data.getPeople().stream()
                .filter(activeRule::test)
                .forEach(System.out::println);


        // 2. Map all names
        System.out.println("\n2. All names:");

        data.getPeople().stream()
                .map(Person::getName)
                .forEach(System.out::println);


        // 3. Count adults
        System.out.println("\n3. Number of adults:");

        long adultCount = data.getPeople().stream()
                .filter(adultRule::test)
                .count();

        System.out.println(adultCount);


        // 4. Sort people by age
        System.out.println("\n4. People sorted by age:");

        data.getPeople().stream()
                .sorted((p1, p2) ->
                        Integer.compare(p1.getAge(), p2.getAge()))
                .forEach(System.out::println);


        // 5. Find first active person in Stockholm
        System.out.println("\n5. First active person in Stockholm:");

        data.getPeople().stream()
                .filter(activeRule::test)
                .filter(stockholmRule::test)
                .findFirst()
                .ifPresent(System.out::println);


    }
}

