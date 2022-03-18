import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LambdaApp {

    public static void main(String[] args) {

        Person person = new Person("Артем", "Владимирыч", "Владимирович", new Date(1900), 11, "123");
        Person person2 = new Person("Вася", "Василич", "Васильевич", new Date(1900), 9, "123");
        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person2);
        list.add(person2);
        list.add(person);
        Map<String, Person> streams = Streams.of(list)
                .filter(p -> p.getAge() > 8)
                .map(p -> new Person(p.getName(), p.getSurname(), p.getMiddleName(), p.getBirthdayDate(), p.getAge() + 30, p.getPassword()))
                .toMap(Person::getName, p -> p);

        List<Person> customList = Streams.of(list).distinct().toList();
        System.out.println(customList);
        System.out.println(Streams.of(list).findFirst());
        System.out.println(Streams.of(list).limit(3).toList());
        System.out.println(Streams.of(list).count());
        System.out.println(streams);
        System.out.println(streams.size());
        Streams.of(list).forEach(p -> System.out.println(p));
    }
}
