import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Person person1 = new Person("Ana", "Agredo", 15);
        Person person2 = new Person("Sara", "Agredo", 45);
        Person person3 = new Person("Sara", "Agredo", 35);
        Person person4 = new Person("Diana", "Sanchez", 35);
        Person person5 = new Person("Isabela", "Santodomingo", 55);
        Person person6 = new Person("Juan", "Valdez", 75);

        List<Person> people = new ArrayList(Arrays.asList(person1,
                person2,
                person3,
                person4,
                person5,
                person6));

        //List of Person
        Stream<Person> stream = people.stream();
        System.out.println("print all people");
        stream.forEach(System.out::println);
        System.out.println("--------------------");

        //Empty stream
        Stream.empty();

        //a singleton stream
        Stream.of("one");

        //a Stream with several elements
        Stream.of("one", "two", "three");

        //a constant Stream
        System.out.println("generating five elements of one");
        Stream<String> streamOfStrings = Stream.generate(()-> "one");
        streamOfStrings.limit(5).forEach(System.out::println);
        System.out.println("--------------------");

        //a growing stream takes to elements
        System.out.println("generating five elements of +");
        Stream<String> streamOfStrings2 = Stream.iterate("+", s -> s + "+");
        streamOfStrings2.limit(5).forEach(System.out::println);
        System.out.println("--------------------");


        //a random Stream
        System.out.println("generating five random elements");
        IntStream streamOfInt = ThreadLocalRandom.current().ints();
        streamOfInt.limit(5).forEach(System.out::println);
        System.out.println("--------------------");

        //a Stream on the letters of a String
        IntStream stream2 = "hello".chars();

        //a Stream on a regular expression
        Stream<String> words = Pattern.compile("[^\\p{javaLetter}]").splitAsStream("book of Java");

        //Stream on the lines of a text file
        //Stream<String> lines = Files.lines(Files(path));


        //The StreamBuilder pattern
        // first build a Stream.Builder
        Stream.Builder<String> builder = Stream.builder();

        //The add data in the builder
        //by the chaining the add() method
        builder.add("one").add("two").add("three");
        //or calling accept()
        builder.accept("four");
        //Then call the stream
        //call the build method
        Stream<String> stream1 = builder.build();
        //And use the stream
        stream1.forEach(System.out::println);

        //Streams don't hold any data
        people.stream()
                .map(person -> person.getAge())
                .filter(age -> age > 20)
                .forEach(System.out::println);

        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(System.out::println);

        //Peek helps to print in each stage, for each can't do that because it doesn't return anything
        //BUT PEEK IT IS ONLY FOR LOGGIN PROPOSE, DON'T USE IN PRODUCTION
        people.stream()
                .map(person -> person.getAge())
                .peek(System.out::println)
                .filter(age -> age > 20)
                .forEach(System.out::println);

        //Skip() and limit()
        people.stream()
                .skip(2)
                .limit(3)
                .filter(person -> person.getAge()> 20)
                .forEach(System.out::println);

        //anyMatch()
        boolean boolean1 = people.stream()
                            .anyMatch(person -> person.getAge() >20);

        //allMatch()
        boolean boolean2 = people.stream()
                                .allMatch(person -> person.getAge() > 20);

        //noneMatch()
        boolean boolean3 = people.stream()
                                .noneMatch(person -> person.getAge() > 20);

        //findFirst()
        Optional<Person> optional1 = people.stream().findFirst();

        //findAny()
        Optional<Person> optinoal2 = people.stream().findAny();

        //reduce()
//        Optional<Integer> maxOfAges = people.stream()
//                            .reduce((person7, person8) -> Integer.max(person7.getAge() + person8.getAge()));






    }
}
