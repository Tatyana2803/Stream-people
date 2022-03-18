import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        long count = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .count();
        System.out.println(count);

        List<String> fitForTheArmy = persons.stream()
                .filter(p -> p.getSex() == Sex.Man)
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(p -> p.getName())
                .collect(Collectors.toList());
        System.out.println("Годны для призыва " + fitForTheArmy);

        List<Person> canWork = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.Man && p.getAge() >= 18 && p.getAge() <= 65) || (p.getSex() == Sex.Woman && p.getAge() >= 18 && p.getAge() <= 60))
                .sorted(Comparator.comparing(p -> p.getName()))
                .collect(Collectors.toList());
        System.out.println("Список работоспособных с высшимобразованием " + canWork);

    }


}
