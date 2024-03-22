package ee.taltech.iti0202.stream.kittens;

import java.util.*;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(final List<Kitten> kittens) {
        this.kittens = kittens;
    }

    public OptionalDouble findKittensAverageAge() {
        List<Integer> kittensAges = kittens.stream()
                .map(Kitten::getAge)
                .toList();

        return kittensAges.stream()
                .mapToDouble(Integer::doubleValue)
                .average();
    }

    public Optional<Kitten> findOldestKitten() {
        return kittens.stream()
                .max((kitten1, kitten2) -> Integer.compare(kitten1.getAge(), kitten2.getAge()));
    }

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

    public List<Kitten> findKittensAccordingToGender(final Kitten.Gender gender) {
         return kittens.stream()
                .filter(kitten -> kitten.getGender() == gender)
                .collect(Collectors.toList());
    }

    public List<Kitten> findKittensBetweenAges(final int minAge, final int maxAge) {
        return kittens.stream()
                .filter(kitten -> kitten.getAge() < maxAge && kitten.getAge() > minAge)
                .collect(Collectors.toList());
    }

    public Optional<Kitten> findFirstKittenWithGivenName(final String givenName) {
        return kittens.stream()
                .filter(kitten -> kitten.getName().equals(givenName))
                .findFirst();
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream()
                .sorted(
                        Comparator.comparingInt(kitten -> kitten.getAge())
                )
                .collect(Collectors.toList());
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens.stream()
                .sorted(
                        Comparator.comparing(kitten -> kitten.getAge(), Comparator.reverseOrder())
                )
                .collect(Collectors.toList());
    }
}