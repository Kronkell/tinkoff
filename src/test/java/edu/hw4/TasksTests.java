package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static edu.hw4.ValidationError.MistakeType.AGE;
import static edu.hw4.ValidationError.MistakeType.HEIGHT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TasksTests {

    static class AnimalsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                Arguments.of(
                    List.of(
                        new Animal("Luna", CAT, F, 105, 130, 40_000, true),
                        new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
                        new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true),
                        new Animal("Nemo the Goldfish", FISH, M, 1, 15, 1_000, false),
                        new Animal("Uncle Shnuk", SPIDER, M, 20, 7, 100, true),
                        new Animal("Strelka", DOG, F, 3, 80, 18_000, true),
                        new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false),
                        new Animal("Ruby", CAT, F, 4, 70, 3_000, true),
                        new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true)
                    )
                )
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortByHeightTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Uncle Shnuk", SPIDER, M, 20, 7, 100, true),
            new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true),
            new Animal("Nemo the Goldfish", FISH, M, 1, 15, 1_000, false),
            new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false),
            new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true),
            new Animal("Ruby", CAT, F, 4, 70, 3_000, true),
            new Animal("Strelka", DOG, F, 3, 80, 18_000, true),
            new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
            new Animal("Luna", CAT, F, 105, 130, 40_000, true)
        );

        assertThat(Tasks.sortByHeight(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortByWeightDescTest(List<Animal> animals) {
        int k = 3;
        var expectedOutput = List.of(
            new Animal("Luna", CAT, F, 105, 130, 40_000, true),
            new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
            new Animal("Strelka", DOG, F, 3, 80, 18_000, true)
        );

        assertThat(Tasks.sortByWeightDesc(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countPerTypeTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            CAT, 2,
            DOG, 2,
            FISH, 2,
            SPIDER, 2,
            BIRD, 1
        );

        assertThat(Tasks.countPerType(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void longestNamedAnimalTest(List<Animal> animals) {
        var expectedOutput = new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true);

        assertThat(Tasks.longestNamedAnimal(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void largestSexTest(List<Animal> animals) {
        assertThat(Tasks.largestSex(animals)).isEqualTo(M);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestAnimalPerTypeTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            CAT, new Animal("Luna", CAT, F, 105, 130, 40_000, true),
            DOG, new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
            BIRD, new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true),
            FISH, new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false),
            SPIDER, new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true)
        );

        assertThat(Tasks.heaviestAnimalPerType(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void kthOldestAnimalTest(List<Animal> animals) {
        int k = 3;
        var expectedOutput = new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true);

        assertThat(Tasks.kthOldestAnimal(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestAnimalAmongThoseSmallerThanKTest(List<Animal> animals) {
        int k = 11;
        Optional<Animal> expectedOutput =
            Optional.of(new Animal("Uncle Shnuk", SPIDER, M, 20, 7, 100, true));

        assertThat(Tasks.heaviestAnimalAmongThoseSmallerThanK(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countOfPawsTest(List<Animal> animals) {
        int expectedOutput = 34;

        assertThat(Tasks.countOfPaws(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsWhoseAgeNotEqualCountOfPawsTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Luna", CAT, F, 105, 130, 40_000, true),
            new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
            new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true),
            new Animal("Nemo the Goldfish", FISH, M, 1, 15, 1_000, false),
            new Animal("Uncle Shnuk", SPIDER, M, 20, 7, 100, true),
            new Animal("Strelka", DOG, F, 3, 80, 18_000, true),
            new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false),
            new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true)
        );

        assertThat(Tasks.animalsWhoseAgeNotEqualCountOfPaws(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsCanBiteAndBiggerThanMeterTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Luna", CAT, F, 105, 130, 40_000, true)
        );

        assertThat(Tasks.animalsCanBiteAndBiggerThanMeter(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void countOfAnimalsWhoseWeightBiggerThanHeightTest(List<Animal> animals) {
        var expectedOutput = 9;

        assertThat(Tasks.countOfAnimalsWhoseWeightBiggerThanHeight(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsWhoseNamesLongerThanTwoWordsTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Nemo the Goldfish", FISH, M, 1, 15, 1_000, false));

        assertThat(Tasks.animalsWhoseNamesLongerThanTwoWords(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void containsDogBiggerThanKTest(List<Animal> animals) {
        int k = 90;
        var expectedOutput = true;

        assertThat(Tasks.containsDogBiggerThanK(animals, k)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void totalWeightOfCertainAgeRangePerTypeTest(List<Animal> animals) {
        int k = 2;
        int l = 5;
        var expectedOutput = Map.of(
            CAT, 3_000,
            DOG, 38_000,
            BIRD, 0,
            FISH, 3_000,
            SPIDER, 120
        );

        assertThat(Tasks.totalWeightOfCertainAgeRangePerType(animals, k, l)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void sortedByTypeSexNameTest(List<Animal> animals) {
        var expectedOutput = List.of(
            new Animal("Luna", CAT, F, 105, 130, 40_000, true),
            new Animal("Ruby", CAT, F, 4, 70, 3_000, true),
            new Animal("Bobuk", DOG, M, 2, 98, 20_000, true),
            new Animal("Strelka", DOG, F, 3, 80, 18_000, true),
            new Animal("Eagle Sam", BIRD, M, 6, 60, 5_000, true),
            new Animal("Nemo the Goldfish", FISH, M, 1, 15, 1_000, false),
            new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false),
            new Animal("Radioactive Spider", SPIDER, M, 2, 14, 120, true),
            new Animal("Uncle Shnuk", SPIDER, M, 20, 7, 100, true)
        );

        assertThat(Tasks.sortedByTypeSexName(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void doSpidersBiteMoreThanDogsTest(List<Animal> animals) {
        var expectedOutput = false;

        assertThat(Tasks.doSpidersBiteMoreThanDogs(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void heaviestFishTest(List<Animal> animals) {
        var animals2 = List.of(
            new Animal("Lollipop", FISH, F, 1, 20, 1_234, false));
        var expectedOutput = new Animal("Peskar Ivanich", FISH, M, 2, 20, 3_000, false);

        assertThat(Tasks.heaviestFish(List.of(animals, animals2))).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void animalsEntriesWithMistakesTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            "Radioactive Spider", Set.of(new ValidationError("Height cannot be that big!", HEIGHT)),
            "Luna", Set.of(
                new ValidationError("Height cannot be that big!", HEIGHT),
                new ValidationError("Age cannot be that big!", AGE))
        );

        assertThat(Tasks.animalsEntriesWithMistakes(animals)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(AnimalsArgumentsProvider.class)
    public void readableAnimalsEntriesWithMistakesTest(List<Animal> animals) {
        var expectedOutput = Map.of(
            "Radioactive Spider ", "HEIGHT",
            "Luna ", "AGE, HEIGHT"
        );

        assertThat(Tasks.readableAnimalsEntriesWithMistakes(animals)).isEqualTo(expectedOutput);
    }
}
