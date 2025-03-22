package com.pbo2.preps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {
    private String invoiceNo;
    private String stockCode;
    private String description;
    private int quantity;
    private LocalDateTime invoiceDate;
    private double unitPrice;
    private int customerID;
    private String country;

    public Product() {
    };

    public Product(String invoiceNo, String stockCode, String description, int quantity, LocalDateTime invoiceDate,
            double unitPrice, int customerID, String country) {
        this.invoiceNo = invoiceNo;
        this.stockCode = stockCode;
        this.description = description;
        this.quantity = quantity;
        this.invoiceDate = invoiceDate;
        this.unitPrice = unitPrice;
        this.customerID = customerID;
        this.country = country;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCountry() {
        return country;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-9s | %-36s | %-6s | %-16s | %-9s | %-10s | %-20s |",
                invoiceNo, stockCode, description, quantity,
                invoiceDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                unitPrice, customerID, country);
    }
}

class ProductController {
    /*
     * - `List<Product>` untuk menyimpan daftar produk ke dalam bentuk list
     * - `Set<String>` untuk mendapatkan daftar unik negara pelanggan
     * - `Map<String, Integer>` untuk menghitung total produk yang terjual
     * berdasarkan `StockCode`
     * - `Map<String, Double>` untuk menghitung total pendapatan per negara
     * - `Map<String, Product>` untuk mencari produk berdasarkan `StockCode`
     */

    private List<Product> products = new ArrayList<>();
    private Map<String, Integer> totalProductsSold = new HashMap<>();
    private Map<String, Double> totalRevenue = new HashMap<>();

    public ProductController() {
    }

    public void LoadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(ClassPrep4.class.getResourceAsStream(filename)))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                products.add(ParseCSVLine(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading online_retail.csv");
        }

        PrintProductsTable(products);
    }

    public void PrintProductsTable(List<Product> list) {
        System.out.println(
                String.format("| %-7s | %-9s | %-36s | %-6s | %-16s | %-9s | %-10s | %-20s |",
                        "InvcNo", "StockCode", "Description", "Qty", "InvoiceDate", "UnitPrice", "CustomerID",
                        "Country"));
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------");
        for (Product product : list) {
            System.out.println(product.toString());
        }
    }

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

    public void CountTotalProductsSold() {
        for (Product product : products) {
            totalProductsSold.put(product.getStockCode(), totalProductsSold.getOrDefault(product.getStockCode(), 0) + product.getQuantity());
        }
    }

    public void CountTotalRevenue() {
        for (Product product : products) {
            Double a = product.getQuantity() * product.getUnitPrice();
            totalRevenue.put(product.getCountry(), totalRevenue.getOrDefault(product.getCountry(), 0.0) + a);
        }
    }

    public void GenerateBusinessReport() {
        CountTotalProductsSold();
        CountTotalRevenue();

        System.out.println("\n===== Business Analysis Report =====\n");

        System.out.println("Total Products Sold Table By StockCode");
        System.out.println("+------------------+----------------------+");
        System.out.println(String.format("| %-16s | %-20s |", "StockCode", "Total Products Sold"));
        System.out.println("+------------------+----------------------+");
        totalProductsSold.forEach((StockCode, total) -> 
            System.out.println(String.format("| %-16s | %-20d |", StockCode, total)));
        System.out.println("+------------------+----------------------+\n");

        System.out.println("Total Revenue Table By Country");
        System.out.println("+-----------------------+----------------------+");
        System.out.println(String.format("| %-21s | %-20s |", "Country", "Total Revenue"));
        System.out.println("+-----------------------+----------------------+");
        totalRevenue.forEach((Country, a) ->
            System.out.println(String.format("| %-21s | %-20.2f |", Country, a)));
        System.out.println("+-----------------------+----------------------+\n");
    }
}

public class ClassPrep4 {
    public static void _main(String[] args) {
        ProductController controller = new ProductController();
        controller.LoadFromCSV("/online_retail.csv");
        controller.GenerateBusinessReport();
    }
}
