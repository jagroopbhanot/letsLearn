public class Student {
    public static void main(String args[])
    {
        System.out.println("Hello");
        System.out.println(printARightTriangle(10));
    }
    public static String printARightTriangle(int N) {
        StringBuilder result = new StringBuilder();
        for (int r = 1; r <= N; r++) {
            for (int j = 1; j <= r; j++) {
                result.append("*");
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}