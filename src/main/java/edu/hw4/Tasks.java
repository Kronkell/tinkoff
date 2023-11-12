package edu.hw4;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;


public class Tasks {
    private Tasks() {}

    //1
    public static List<Animal> sortByHeight(List<Animal> animals) {

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    //2
    public static List<Animal> sortByWeightDesc(List<Animal> animals, int k) {

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    //3
    public static Map<Animal.Type, Integer> countPerType(List<Animal> animals) {

        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    //4
    public static Animal longestNamedAnimal(List<Animal> animals) {

        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    //5
    public static Animal.Sex largestSex(List<Animal> animals) {

        return animals.stream()
            .collect(groupingBy(Animal::sex, summingInt(e -> 1)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow()
            .getKey();
    }

    //6
    public static Map<Animal.Type, Animal> heaviestAnimalPerType(List<Animal> animals) {

        return Arrays.stream(Animal.Type.values())
            .map(type ->
                new AbstractMap.SimpleEntry<>(
                    type,
                    animals.stream()
                        .filter(animal -> animal.type() == type)
                        .max(Comparator.comparing(Animal::weight)).orElseThrow()
                ))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    //7
    public static Animal kthOldestAnimal(List<Animal> animals, int k) {

        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow();
    }

    //8
    public static Optional<Animal> heaviestAnimalAmongThoseSmallerThanK(List<Animal> animals, int k) {

        return animals.stream()
            .filter(animal -> (animal.height() < k))
            .max(Comparator.comparing(Animal::weight));
    }

    //9
    public static Integer countOfPaws(List<Animal> animals) {

        return animals.stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    //10
    public static List<Animal> animalsWhoseAgeNotEqualCountOfPaws(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    //11
    @SuppressWarnings("MagicNumber")
    public static List<Animal> animalsCanBiteAndBiggerThanMeter(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    //12
    public static Integer countOfAnimalsWhoseWeightBiggerThanHeight(List<Animal> animals) {

        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    //13
    public static List<Animal> animalsWhoseNamesLongerThanTwoWords(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    //14
    public static Boolean containsDogBiggerThanK(List<Animal> animals, int k) {

        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    //15
    public static Map<Animal.Type, Integer> totalWeightOfCertainAgeRangePerType(List<Animal> animals, int k, int l) {

        return Arrays.stream(Animal.Type.values())
            .map(type ->
                new AbstractMap.SimpleEntry<>(
                    type,
                    animals.stream()
                        .filter(animal -> animal.type() == type)
                        .filter(animal -> animal.age() >= k && animal.age() < l)
                        .map(Animal::weight)
                        .reduce(0, Integer::sum)
                ))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    //16
    public static List<Animal> sortedByTypeSexName(List<Animal> animals) {

        return animals.stream()
            .sorted(
                Comparator
                    .comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
            .toList();
    }

    //17
    public static Boolean doSpidersBiteMoreThanDogs(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.SPIDER)
            .count()
            >
            animals.stream()
                .filter(animal -> animal.bites() && animal.type() == Animal.Type.DOG)
                .count();
    }

    //18
    public static Animal heaviestFish(List<List<Animal>> animalList) {

        return animalList.stream()
            .map(animals -> animals.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight))
                .orElse(new Animal("", null, null, 0, 0, 0, false)))
            .max(Comparator.comparing(Animal::weight)).orElseThrow();
    }

    //19
    public static Map<String, Set<ValidationError>> animalsEntriesWithMistakes(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> !ValidationError.isValid(animal))
            .collect(Collectors.toMap(Animal::name, ValidationError::getErrors));
    }

    //20
    public static Map<String, String> readableAnimalsEntriesWithMistakes(List<Animal> animals) {

        return animals.stream()
            .filter(animal -> !ValidationError.isValid(animal))
            .map(animal ->
                new AbstractMap.SimpleEntry<>(
                    animal.name() + " ",
                    ValidationError.getErrors(animal).stream()
                        .map(validationError -> validationError.type().name())
                        .sorted()
                        .reduce("", (a, b) -> String.format("%s, %s", a, b))
                        .replaceFirst(", ", "")
                ))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }
}
