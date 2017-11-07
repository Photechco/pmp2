package PMP2_2_1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class String_Refactor {

    private final static Function<String, String> trim = String::trim;
    private final static Function<String, String> toUppercase = String::toUpperCase;
    private final static Function<String, String> replace = e -> e.replaceAll("Ä", "AE")
            .replaceAll("Ö", "OE")
            .replaceAll("Ü", "UE")
            .replaceAll("ß", "SS");

    private final static Function<String, String> shorten = e -> (e.length() > 8) ? e.substring(0, 8) : e;

    public static void main(String[] args) {
        List<String> out = refactor_Sting("Hallo ", "Äußeres ", null, "Straßen-Feger", " ein Haus");
        out.forEach(System.out::println);
    }

    public static List<String> refactor_Sting(String... string_array) {
        Stream<String> stream = Arrays.stream(string_array);
        return stream
                .filter(Objects::nonNull)
                .map(trim)
                .map(toUppercase)
                .map(replace)
                .map(shorten)
                .collect(Collectors.toList());
    }
}
