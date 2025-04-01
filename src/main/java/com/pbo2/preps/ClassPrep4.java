package com.pbo2.preps;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.text.*;

/**
 * Represents a product with attributes such as invoice number, stock code,
 * description, quantity, invoice date, unit price, customer ID, and country.
 */
class Product {
    private List<String> invoiceNo = new ArrayList<>();
    private String stockCode;
    private String description;
    private List<Integer> quantity = new ArrayList<>();
    private List<LocalDateTime> invoiceDate = new ArrayList<>();
    private List<Double> unitPrice = new ArrayList<>();
    private List<Integer> customerID = new ArrayList<>();
    private List<String> country = new ArrayList<>();

    /**
     * Parameterized constructor for Product class
     * 
     * @param invoiceNo   Invoice number
     * @param stockCode   Stock code
     * @param description Product description
     * @param quantity    Quantity of product
     * @param invoiceDate Date and time of invoice
     * @param unitPrice   Price per unit
     * @param customerID  Customer ID
     * @param country     Country of customer
     */
    public Product(String invoiceNo, String stockCode, String description, int quantity, LocalDateTime invoiceDate,
            double unitPrice, int customerID, String country) {
        this.invoiceNo.add(invoiceNo);
        this.stockCode = stockCode;
        this.description = description;
        this.quantity.add(quantity);
        this.invoiceDate.add(invoiceDate);
        this.unitPrice.add(unitPrice);
        this.customerID.add(customerID);
        this.country.add(country);
    }

    /**
     * Copy constructor for the Product class.
     * Creates a new Product object by duplicating the attributes of an existing
     * Product instance.
     * Ensures that mutable list attributes are deep copied to prevent unintended
     * modifications.
     * 
     * @param other The Product object to copy from.
     *              - {@code invoiceNo}: List of invoice numbers.
     *              - {@code stockCode}: Unique stock code of the product.
     *              - {@code description}: Description of the product.
     *              - {@code quantity}: List of quantities corresponding to each
     *              invoice.
     *              - {@code invoiceDate}: List of invoice dates and times.
     *              - {@code unitPrice}: List of unit prices for each invoice entry.
     *              - {@code customerID}: List of customer IDs associated with the
     *              invoices.
     *              - {@code country}: List of countries where the purchases were
     *              made.
     */
    public Product(Product other) {
        this.invoiceNo = new ArrayList<>(other.invoiceNo);
        this.stockCode = other.stockCode;
        this.description = other.description;
        this.quantity = new ArrayList<>(other.quantity);
        this.invoiceDate = new ArrayList<>(other.invoiceDate);
        this.unitPrice = new ArrayList<>(other.unitPrice);
        this.customerID = new ArrayList<>(other.customerID);
        this.country = new ArrayList<>(other.country);
    }

    /**
     * Retrieves the list of invoice numbers.
     * 
     * @return List of invoice numbers.
     */
    public List<String> getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * Retrieves the stock code of the product.
     * 
     * @return Stock code of the product.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Retrieves the product description.
     * 
     * @return Description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the list of quantities for each transaction.
     * 
     * @return List of product quantities.
     */
    public List<Integer> getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the list of invoice dates.
     * 
     * @return List of invoice dates.
     */
    public List<LocalDateTime> getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Retrieves the list of unit prices for each transaction.
     * 
     * @return List of unit prices.
     */
    public List<Double> getUnitPrice() {
        return unitPrice;
    }

    /**
     * Retrieves the list of customer IDs associated with transactions.
     * 
     * @return List of customer IDs.
     */
    public List<Integer> getCustomerID() {
        return customerID;
    }

    /**
     * Retrieves the list of countries where transactions took place.
     * 
     * @return List of countries.
     */
    public List<String> getCountry() {
        return country;
    }

    // Setter methods to modify the values of product attributes

    /**
     * Sets the list of invoice numbers.
     * 
     * @param invoiceNo List of invoice numbers to set.
     */
    public void setInvoiceNo(List<String> invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * Sets the stock code of the product.
     * 
     * @param stockCode Stock code to set.
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    /**
     * Sets the product description.
     * 
     * @param description Description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the list of quantities for each transaction.
     * 
     * @param quantity List of product quantities to set.
     */
    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the list of invoice dates.
     * 
     * @param invoiceDate List of invoice dates to set.
     */
    public void setInvoiceDate(List<LocalDateTime> invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * Sets the list of unit prices for each transaction.
     * 
     * @param unitPrice List of unit prices to set.
     */
    public void setUnitPrice(List<Double> unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Sets the list of customer IDs associated with transactions.
     * 
     * @param customerID List of customer IDs to set.
     */
    public void setCustomerID(List<Integer> customerID) {
        this.customerID = customerID;
    }

    /**
     * Sets the list of countries where transactions took place.
     * 
     * @param country List of countries to set.
     */
    public void setCountry(List<String> country) {
        this.country = country;
    }

    /**
     * Overrides the toString method to generate a formatted string representation
     * of the product details in a tabular format.
     * 
     * @return A string containing formatted product details.
     */
    @Override
    public String toString() {
        String temp = "";

        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        for (int i = 0; i < invoiceNo.size(); i++) {
            temp += String.format("| %-7s | %-12s | %-36s | %-6s | %-16s | %-9s | %-10s | %-20s |",
                    invoiceNo.get(i), stockCode, description, quantity.get(i),
                    invoiceDate.get(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    nf.format(unitPrice.get(i)), customerID.get(i), country.get(i));

            if ((i + 1) != invoiceNo.size())
                temp += "\n";
        }

        return temp;
    }
}

// Controller class for managing product operations
class ProductController {
    private List<Product> products = new ArrayList<>();
    private Map<String, Integer> totalProductsSold = new HashMap<>();
    private Map<String, Double> totalRevenue = new HashMap<>();
    private Set<String> uniqueCountries = new HashSet<>();
    private Map<String, Product> productsMap = new HashMap<>();

    /**
     * Default constructor.
     */
    public ProductController() {
    }

    /**
     * Loads product data from a CSV file.
     * 
     * @param filename Name of the CSV file.
     */
    public void LoadFromCSV(String filename) {
        String urlText = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new URI(urlText).toURL().openStream()))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                Product product = ParseCSVLine(line);
                if (product != null) {
                    products.add(product);
                    uniqueCountries.addAll(product.getCountry());

                    Product existingProduct = null;
                    if (productsMap.containsKey(product.getStockCode())) {
                        existingProduct = new Product(productsMap.get(product.getStockCode()));

                        existingProduct.getInvoiceNo().addAll(product.getInvoiceNo());
                        existingProduct.getQuantity().addAll(product.getQuantity());
                        existingProduct.getInvoiceDate().addAll(product.getInvoiceDate());
                        existingProduct.getUnitPrice().addAll(product.getUnitPrice());
                        existingProduct.getCustomerID().addAll(product.getCustomerID());
                        existingProduct.getCountry().addAll(product.getCountry());
                    } else {
                        existingProduct = new Product(product);
                    }

                    productsMap.put(product.getStockCode(), existingProduct);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading online_retail.csv");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the list of products in a table format.
     * 
     * @param list The list of products to be printed.
     */
    public void PrintProductsTable(List<Product> list) {
        System.out.println(
                "\n============================================================== PRODUCTS TABLE ===============================================================");
        System.out.println(
                "+---------+--------------+--------------------------------------+--------+------------------+-----------+------------+----------------------+");
        System.out.println(
                String.format("| %-7s | %-12s | %-36s | %-6s | %-16s | %-9s | %-10s | %-20s |",
                        "InvcNo", "StockCode", "Description", "Qty", "InvoiceDate", "UnitPrice", "CustomerID",
                        "Country"));
        System.out.println(
                "+---------+--------------+--------------------------------------+--------+------------------+-----------+------------+----------------------+");

        for (Product product : list) {
            System.out.println(product.toString());
        }

        System.out.println(
                "+---------+--------------+--------------------------------------+--------+------------------+-----------+------------+----------------------+");
    }

    /**
     * Method to print the products table using the available product list.
     * Calls the PrintProductsTable method with the list of products as a parameter.
     */
    public void PrintProductsTable() {
        PrintProductsTable(products);
    }

    /**
     * Displays a list of unique customer countries.
     */
    public void PrintUniqueCountries() {
        System.out.println("Unique Countries: ");
        uniqueCountries.forEach(country -> System.out.println("- " + country));
    }

    /**
     * Searches for a product based on StockCode.
     * 
     * @param stockCode The stock code of the product.
     * @return Product object if found, null otherwise.
     */
    public Product SearchProduct(String stockCode) {
        if (!productsMap.containsKey(stockCode)) {
            System.out.println("Product with StockCode '" + stockCode + "' not found!");
            return null;
        }
        return productsMap.get(stockCode);
    }

    /**
     * Parses a single CSV line into a Product object.
     * 
     * @param line The CSV line.
     * @return A Product object.
     */
    private Product ParseCSVLine(String line) {
        String[] result = new String[8]; // Fixed 8 columns
        StringBuilder sb = new StringBuilder(line.length()); // Preallocate buffer
        boolean inQuotes = false;
        int columnIndex = 0;

        for (int i = 0, len = line.length(); i < len; i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result[columnIndex++] = sb.toString();
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        result[columnIndex] = sb.toString();

        return new Product(result[0],
                result[1],
                result[2],
                Integer.parseInt(result[3]),
                LocalDateTime.parse(result[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Double.parseDouble(result[5]),
                result[6].isEmpty() ? 0 : (int) Double.parseDouble(result[6]),
                result[7]);
    }

    /**
     * Calculates the total number of products sold based on StockCode.
     */
    public void CountTotalProductsSold() {
        for (Product product : products) {
            String c = product.getStockCode();
            int v = product.getQuantity().get(0);

            totalProductsSold.put(c, totalProductsSold.getOrDefault(c, 0) + v);
        }
    }

    /**
     * Calculates the total revenue per country.
     */
    public void CountTotalRevenue() {
        for (Product product : products) {
            String c = product.getCountry().get(0);
            Double v = product.getQuantity().get(0) * product.getUnitPrice().get(0);

            totalRevenue.put(c, totalRevenue.getOrDefault(c, 0.0) + v);
        }
    }

    /**
     * Generates a business report including total products sold and total revenue.
     */
    public void GenerateBusinessReport() {
        CountTotalProductsSold();
        CountTotalRevenue();

        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        System.out.println("\n======== TOTAL PRODUCTS SOLD TABLE ========");
        System.out.println("+------------------+----------------------+");
        System.out.println(String.format("| %-16s | %-20s |", "StockCode", "Total Products Sold"));
        System.out.println("+------------------+----------------------+");
        totalProductsSold.forEach(
                (StockCode, total) -> System.out
                        .println(String.format("| %-16s | %-20s |", StockCode, nf.format(total))));
        System.out.println("+------------------+----------------------+");

        System.out.println("\n============= TOTAL REVENUE TABLE =============");
        System.out.println("+-----------------------+---------------------+");
        System.out.println(String.format("| %-21s | %-19s |", "Country", "Total Revenue"));
        System.out.println("+-----------------------+---------------------+");
        totalRevenue.forEach(
                (Country, a) -> System.out.println(String.format("| %-21s | %-19s |", Country, nf.format(a))));
        System.out.println("+-----------------------+---------------------+");
    }
}

// Main class
public class ClassPrep4 {
    public static void _main(String[] args) {
        ProductController controller = new ProductController();
        controller.LoadFromCSV("/online_retail.csv");
        try (Scanner sc = new Scanner(System.in)) {
            Boolean first = true;
            do {
                if (!first)
                    System.out.println(
                            "\n\n=============================================================================================================================================\n\n");

                first = false;

                System.out.println("+----------------------------------------------+");
                System.out.println("|      Welcome To Sales Management System      |");
                System.out.println("+----------------------------------------------+");
                System.out.println("|            Please choose an option           |");
                System.out.println("|                                              |");
                System.out.println("| 1. Print Products Table                      |");
                System.out.println("| 2. Print Unique Countries                    |");
                System.out.println("| 3. Search Product by StockCode               |");
                System.out.println("| 4. Generate Business Report                  |");
                System.out.println("| 5. Exit                                      |");
                System.out.println("|                                              |");
                System.out.println("+----------------------------------------------+");
                System.out.print("Enter your choice: ");

                String input = sc.next();
                int choice = Integer.parseInt(input);

                if (choice == 1) {
                    controller.PrintProductsTable();
                } else if (choice == 2) {
                    controller.PrintUniqueCountries();
                } else if (choice == 3) {
                    System.out.print("Enter StockCode of a product to search: ");
                    sc.nextLine();
                    String stockCode = sc.nextLine();
                    Product result = controller.SearchProduct(stockCode);

                    if (result != null) {
                        System.out.print("\nProduct found!");
                        controller.PrintProductsTable(Arrays.asList(result));
                    }
                } else if (choice == 4) {
                    controller.GenerateBusinessReport();
                } else if (choice == 5) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } while (true);
        }
    }
}
