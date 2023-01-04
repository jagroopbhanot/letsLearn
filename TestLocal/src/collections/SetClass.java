package collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetClass {
    //HashSet :- unique elements
    //LinkedHashSet :- Maintain insertion order along with unique elements
    //TreeSet :-  sort the elements with unique elements in the set
    public static void main(String[] args) {
        use_of_HashSet();
        use_of_LinkedHashSet();
        use_of_TreeSet();
    }

    private static void use_of_TreeSet()
    {
        System.out.println("########### use of Tree set [Start]");
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("AA");
        treeSet.add("BB");
        treeSet.add("DD");
        treeSet.add("SS");
        treeSet.add("CC");
        treeSet.add("AA");

        treeSet.stream().forEach(System.out::println);
        System.out.println("########### use of Tree set [End]");

    }

    private static void use_of_LinkedHashSet()
    {
        System.out.println("########### use of Linked Hash set [Start]");

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("AA");
        linkedHashSet.add("BB");
        linkedHashSet.add("DD");
        linkedHashSet.add("SS");
        linkedHashSet.add("CC");
        linkedHashSet.add("AA");

        linkedHashSet.stream().forEach(System.out::println);
        System.out.println("########### use of Linked Hash set [End]");

    }
    private static void use_of_HashSet()
    {
        System.out.println("########### use of Hash set [Start]");

        HashSet<String> set = new HashSet<>();
        set.add("Jagroop");
        set.add("Bhanot");
        set.add("Isha");
        set.add("Aarav");
        set.add("Jagroop");
        set.add("isha");

        set.stream().forEach(System.out::println);
        System.out.println("########### use of Hash set [Start]");

    }
}
