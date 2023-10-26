package edu.hw4;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record ValidationError(String info, MistakeType type) {

    enum MistakeType {
        WEIGHT, HEIGHT, AGE
    }

    @SuppressWarnings("MagicNumber")
    static Map<Animal.Type, List<Integer>> animalInfo = Map.of(
        Animal.Type.FISH, List.of(10_000, 100, 3),
        Animal.Type.DOG, List.of(100_000, 120, 20),
        Animal.Type.CAT, List.of(100_000, 100, 18),
        Animal.Type.BIRD, List.of(18_000, 90, 100),
        Animal.Type.SPIDER, List.of(175, 13, 43)
    );

    public static Boolean isValid(Animal animal) {
        var type = animal.type();

        return animal.weight() <= animalInfo.get(type).get(0)
                && animal.height() <= animalInfo.get(type).get(1)
                && animal.age() <= animalInfo.get(type).get(2);
    }

    public static Set<ValidationError> getErrors(Animal animal) {
        var type = animal.type();
        var errors = new HashSet<ValidationError>();

        if (animal.weight() > animalInfo.get(type).get(0)) {
            errors.add(new ValidationError("Weight cannot be that big!", MistakeType.WEIGHT));
        }
        if (animal.height() > animalInfo.get(type).get(1)) {
            errors.add(new ValidationError("Height cannot be that big!", MistakeType.HEIGHT));
        }
        if (animal.age() > animalInfo.get(type).get(2)) {
            errors.add(new ValidationError("Age cannot be that big!", MistakeType.AGE));
        }

        return errors;
    }
}
