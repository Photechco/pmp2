package PMP2_2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class String_Refactor {

    private static String_Refactor_I trim = e -> e.trim();
    private static String_Refactor_I toUppercase = e -> e.toUpperCase();
    private static String_Refactor_I repalce = e -> {e.replaceAll("Ä","AE");
                                                        e.replaceAll("Ö","OE");
                                                        e.replaceAll("Ü","UE");
                                                        e.replaceAll("ß","SS");
                                                        return e;};

    public static void main(String[] args) {
        List<String> out= new ArrayList<>();
        out = refactor_Sting("Hallo ", "Äußeres ", null, "Straßen-Feger", " ein Haus");
        out.forEach(e -> System.out.println(e));
    }

    public static List<String> refactor_Sting (String ... string_array) {



        Stream<String> string = Arrays.stream(string_array);
        List<String> stringList = new ArrayList<>();
        string.forEach(e -> {
            if (e == null) return;
            e = trim.refactor(e);
            e = toUppercase.refactor(e);
            e = repalce.refactor(e);
            if (e.length() > 8) e = e.substring(0,8);
            stringList.add(e);
        });
        return stringList;
    }
}
