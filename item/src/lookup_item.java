import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lookup_item {

    public static void main(String[] args) {
        String line = "";
        String splitBy = ",";
        int expectedFields = 5;

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User2022\\Downloads\\lookup_item.csv"));
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] item = line.split(splitBy);

                // Check if the array has the expected number of fields
                if (item.length == expectedFields) {
                    System.out.println("Item code: " + item[0] +
                            "\tItem: " + item[1] +
                            "\tUnit: " + item[2] +
                            "\tItem group: " + item[3] +
                            "\tItem category: " + item[4]);
                } else {
                    System.out.println("Skipping invalid data in line (expected " + expectedFields + " fields): " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
