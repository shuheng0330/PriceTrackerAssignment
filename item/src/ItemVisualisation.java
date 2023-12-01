import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ItemVisualisation {

    private List<String[]> priceCatcherData;
    private List<String[]> lookupPremiseData;
    private List<String[]> lookupItemData;

    public ItemVisualisation(List<String[]> priceCatcherData, List<String[]> lookupPremiseData, List<String[]> lookupItemData) {
        this.priceCatcherData = priceCatcherData;
        this.lookupPremiseData = lookupPremiseData;
        this.lookupItemData = lookupItemData;
        //constructor that hold these lists
        //enable us to access these lists
    }

    public void displayTopFiveCheapestSellers(String itemName, String unit) {
        String itemCode = getItemCode(itemName, unit);
        if (itemCode != null) {
            List<String[]> sellers = new ArrayList<>();
            for (String[] row : priceCatcherData) {
                if (row[2].equals(itemCode)) {
                    sellers.add(row);
                }
            }

            if (sellers.isEmpty()) {
                System.out.println("No sellers found for " + itemName + " (" + unit + ") in pricecatcherData.");

            }

            sellers.sort(Comparator.comparingDouble(row -> Double.parseDouble(row[3])));
            //sort the price in ascending order

            int count = 0;
            List<String> displayedPremises = new ArrayList<>(); // Track displayed premises
            for (String[] seller : sellers) {
                if (count >= 5) {
                    break; // Stop if already displayed 5 sellers
                }
                String premiseCode = seller[1];
                String premiseDetails = getPremiseDetails(premiseCode);
                if (premiseDetails != null && !displayedPremises.contains(premiseDetails)) { //ensure no repeated sellers exists for specified item
                    System.out.println((count + 1) + ". " + premiseDetails);
                    System.out.println("Price: $" + seller[3]);
                    System.out.println();
                    displayedPremises.add(premiseDetails); // Add displayed premise
                    count++;
                }
            }
        } else {
            System.out.println("Item not found!");
        }
    }


    private String getItemCode(String itemName, String unit) {
        for (String[] item : lookupItemData) {
            if (item[1].equalsIgnoreCase(itemName) && item[2].equalsIgnoreCase(unit)) {
                return item[0];

            }
        }
        return null;
    }

    private String getPremiseDetails(String premiseCode) {
        for (String[] premise : lookupPremiseData) {
            if (premise[0].equals(premiseCode)) {
                return premise[1] + "\nAddress: " + premise[2];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        
        String priceCatcherFilePath = "C:\\Users\\User2022\\Downloads\\pricecatcher_2023-08 (1).csv";
        String lookupPremiseFilePath = "C:\\Users\\User2022\\Downloads\\lookup_premise.csv";
        String lookupItemFilePath = "C:\\Users\\User2022\\Downloads\\lookup_item.csv";
        //read three csv files
        List<String[]> priceCatcherData = CSVDataReader.readCSV(priceCatcherFilePath);
        List<String[]> lookupPremiseData = CSVDataReader.readCSV(lookupPremiseFilePath);
        List<String[]> lookupItemData = CSVDataReader.readCSV(lookupItemFilePath);

        // Create an instance of ItemVisualisation
        ItemVisualisation visualisation = new ItemVisualisation(priceCatcherData, lookupPremiseData, lookupItemData);

        // Take user input for item name and unit
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter the unit: ");
        String unit = scanner.nextLine();

        System.out.println("Top 5 Cheapest Sellers for " + itemName + " (" + unit + ")");

        //displayTopFiveCheapestSellers method that pass arguments itemName and unit
        visualisation.displayTopFiveCheapestSellers(itemName, unit);

        scanner.close();
    }
}
