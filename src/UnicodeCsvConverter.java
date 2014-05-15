import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by jfuentes on 5/9/14.
 */
public class UnicodeCsvConverter {

    public static void main(String[] args) throws FileNotFoundException {
        convertWithScanner(args[0]);

    }

    public static void convertWithReader(String filename) throws FileNotFoundException {
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)),Charset.defaultCharset()));
        int r;
        try{
            while ((r = reader.read()) != -1) {
                char ch = (char) r;
                System.out.print((""+(""+ch).replaceAll("\u0001","|")).replaceAll("\u0002","\n"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertWithScanner(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename),Charset.defaultCharset().name());
        Pattern pattern = Pattern.compile("\u0002");
        scanner.useDelimiter(pattern);

        while(scanner.hasNext()) {
            System.out.println(scanner.next().replaceAll("\u0001","|"));
        }
    }
}
