package collections.ListToMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertMapToList {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10,"Apple");
        map.put(11,"Banana");
        map.put(12,"Orange");
        map.put(13,"kiwi");
        map.put(14,"pear");

        List<String> mylist = map.values().stream().collect(Collectors.toList());
       mylist.forEach(System.out::println);
    }
}
