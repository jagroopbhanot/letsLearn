package collections;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalClass {

    public static void main(String[] args) {
        List<String> values = Arrays.asList("J","A","G","R","O","o","P");

        use_of_Optional(values);
    }

    private static void use_of_Optional(List<String> values) {
        Optional<String> s = values.stream().filter(x -> "A".equals(x)).map(String:: toLowerCase).findFirst();
        System.out.println(s.get());
    }

    private static  void list_to_map(List<String> values)
    {

    }
}
