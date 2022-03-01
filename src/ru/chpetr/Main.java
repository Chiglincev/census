package ru.chpetr;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_0; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //  Найти количество несовершеннолетних:
        int numberOfChildren = (int)persons.stream()
                                        .filter(person -> person.getAge() < 18)
                                        .count();

        //Получить список фамилий призывников:
        List<String> conscriptsList = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();

        //Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке:
        List<Person> higherEducationWorkersList = new ArrayList<>();
        higherEducationWorkersList.addAll(
                 persons.stream()
                         .filter(person -> (person.getSex().equals(Sex.MAN) &&
                            person.getEducation().equals(Education.HIGHER) &&
                            person.getAge() >= 18 &&
                            person.getAge() <= 65) ||
                                 (person.getSex().equals(Sex.WOMAN) &&
                                  person.getEducation().equals(Education.HIGHER) &&
                                  person.getAge() >= 18 &&
                                  person.getAge() <= 60))
                         .sorted()
                         .toList()
        );

    }
}
