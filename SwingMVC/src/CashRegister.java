import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Manages the cash register operations including product scanning and total calculation.

public class CashRegister {
    // List of products that have been scanned during the current transaction
    private List<Product> scannedItems;
    // Catalog mapping UPC codes to product information
    private Map<String, Product> productCatalog;

    
    // Initializes the cash register and loads the product catalog.
    public CashRegister() {
        scannedItems = new ArrayList<>();
        productCatalog = new HashMap<>();
        loadProducts();
    }

    //Loads product data from the products.txt file into the catalog. Expected format: UPC,Name,Price
    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Validate that the line has all required fields
                if (parts.length == 3) {
                    productCatalog.put(parts[0], new Product(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading products.txt: " + e.getMessage());
        }
    }

    //Adds a product to the transaction by UPC code.
     
    public void addItemByUPC(String upc) {
        Product product = productCatalog.get(upc);
        if (product != null) {
            scannedItems.add(product);
        } else {
            System.err.println("Product not found for UPC: " + upc);
        }
    }

    // Returns a copy of the list of scanned items
    public List<Product> getScannedItems() {
        return new ArrayList<>(scannedItems);
    }

    //Returns the current subtotal of all scanned items.
    public double getSubtotal() {
        return scannedItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Clears all scanned items and resets the subtotal to zero
    public void clear() {
        scannedItems.clear();
    }
}
