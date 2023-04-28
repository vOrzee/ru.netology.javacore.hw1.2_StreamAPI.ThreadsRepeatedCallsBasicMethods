package ru.netology;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        List<Person> minors =
                persons.stream()
                        .filter(person -> person.getAge() < 18)
                        .toList();

        List<String> conscripts =
                persons.stream()
                        .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                        .map(Person::getFamily)
                        .toList();

        List<Person> workableWithHigherEducation =
                persons.stream()
                        .filter(person ->
                                person.getAge() >= 18 && person.getAge() < 65
                                        && person.getEducation().equals(Education.HIGHER)
                                        && (person.getSex().equals(Sex.MAN) || (person.getSex().equals(Sex.WOMAN) && person.getAge() < 60))
                        ).sorted(Comparator.comparing(Person::getFamily))
                        .toList();
    }
}