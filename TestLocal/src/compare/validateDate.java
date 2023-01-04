package compare;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class validateDate {
    public static void main(String[] args) {
        utcDate();
    }
    public static void utcDate()
    {
        String inputValue = "0000-01-01T23:03:11Z";

        String inputValue1 = "2022-07-11T23:03:11Z";
        String datePattern = "yyyy-MM-dd'T'HH:mm:ssZ";

        try
        {
            SimpleDateFormat utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
           // utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            System.out.println("inputValue ==> " + utcFormatter.parse(inputValue));
            System.out.println("inputValue1 ==> " +utcFormatter.parse(inputValue1));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
