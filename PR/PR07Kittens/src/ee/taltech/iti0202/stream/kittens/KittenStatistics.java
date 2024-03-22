package ee.taltech.iti0202.stream.kittens;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;
    
    /**
     * Sets the list of kittens.
     *
     * @param kittens The list of kittens to set.
     */
    public void setKittens(final List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     * Finds the average age of kittens.
     *
     * @return The optional average age of kittens.
     */
    public OptionalDouble findKittensAverageAge() {
        List<Integer> kittensAges = kittens.stream()
                .map(Kitten::getAge)
                .toList();

        return kittensAges.stream()
                .mapToDouble(Integer::doubleValue)
                .average();
    }

    /**
     * Finds the oldest kitten.
     *
     * @return The optional oldest kitten.
     */
    public Optional<Kitten> findOldestKitten() {
        return kittens.stream()
                .max((kitten1, kitten2) -> Integer.compare(kitten1.getAge(), kitten2.getAge()));
    }

    /**
     * Finds the youngest kittens.
     *
     * @return The list of youngest kittens.
     */
    public List<Kitten> findYoungestKittens() {
        Optional<Integer> minAge = kittens.stream()
                .map(Kitten::getAge)
                .min(Integer::compareTo);

        List<Kitten> youngestKittens = new ArrayList<>();
        minAge.ifPresent(min -> {
            youngestKittens.addAll(kittens.stream()
                    .filter(kitten -> kitten.getAge() == min)
                    .toList());
        });
        return youngestKittens;
    }

    /**
     * Finds kittens according to gender.
     *
     * @param gender The gender of kittens to find.
     * @return The list of kittens with the specified gender.
     */
    public List<Kitten> findKittensAccordingToGender(final Kitten.Gender gender) {
        return kittens.stream()
                .filter(kitten -> kitten.getGender() == gender)
                .collect(Collectors.toList());
    }

    /**
     * Finds kittens between a specified age range.
     *
     * @param minAge The minimum age.
     * @param maxAge The maximum age.
     * @return The list of kittens between the specified age range.
     */
    public List<Kitten> findKittensBetweenAges(final int minAge, final int maxAge) {
        return kittens.stream()
                .filter(kitten -> kitten.getAge() <= maxAge && kitten.getAge() >= minAge)
                .collect(Collectors.toList());
    }

    /**
     * Finds the first kitten with the given name.
     *
     * @param givenName The name to search for.
     * @return The optional first kitten with the given name.
     */
    public Optional<Kitten> findFirstKittenWithGivenName(final String givenName) {
        return kittens.stream()
                .filter(kitten -> kitten.getName().equals(givenName))
                .findFirst();
    }

    /**
     * Sorts kittens by age in ascending order.
     *
     * @return The list of kittens sorted by age (younger first).
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream()
                .sorted(
                        Comparator.comparingInt(Kitten::getAge)
                )
                .collect(Collectors.toList());
    }

    /**
     * Sorts kittens by age in descending order.
     *
     * @return The list of kittens sorted by age (older first).
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream()
                .sorted(
                        Comparator.comparingInt(Kitten::getAge).reversed()
                )
                .collect(Collectors.toList());
    }
}
