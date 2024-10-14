package object;

// Models
import model.Product;
import model.Brand;
import model.Category;

// Utility Support
import validation.Input_Validation;
import validation.UISupport;

// Java Library

import java.util.ArrayList;

// Read and write 
import io.Load;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductDao implements IProductDao {

    // ATRIBUTES
    ArrayList<Product> ProductList;
    IBrandDao Brand_List = new BrandDao();
    ICategoryDao Category_List = new CategoryDao();

    // ID_FORMAT =  P0000 | format would be like this
    final String NAME_FORMAT = "[a-zA-Z ]{3,20}$"; // 3 to 20 words with only characters and numbers
    final String file_path = "src/output/Product.txt";
    
    // CONSTRUCTOR
    public ProductDao() {
        ProductList = new ArrayList<>();

    }

    // GETTER AND SETTER
    public void setProductList(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
    }

    public ArrayList<Product> getProductList() {
        return ProductList;
    }

    // PRIVATE METHOD
    // PRINT LIST
    public void load() {
        ArrayList<String> lines = Load.load(file_path);

        for (String line : lines) {
            String[] s = line.split(","); // ID, NAME, BRAND, CATEGORY, YEAR, LIST PRICE
            String id = s[0];
            String name = s[1];
            String brandName = s[2];
            Brand iBrand = null;
            for (Brand b : Brand_List.getBrandList()) {
                if (b.getName().equalsIgnoreCase(brandName)) {
                    iBrand = b;
                    break;
                }
            }
            String categoryName = s[3];
            Category iCategory = null;
            for (Category c : Category_List.getCategoryList()) {
                if (c.getName().equalsIgnoreCase(categoryName)) {
                    iCategory = c;
                    break;
                }
            }
            int year = Integer.parseInt(s[4]);
            double listPrice = Double.parseDouble(s[5]);
            Product p = new Product(id, name, iBrand, iCategory, year, listPrice);
            ProductList.add(p);
        }
    }

// EXIST
// 1. get Exist Id
    public int existId(String id) {
        if (ProductList.isEmpty()) {
            return -1;
        }

        for (Product p : ProductList) {
            if (p.getId().equalsIgnoreCase(id)) {
                return ProductList.indexOf(p);
            }
        }
        return -1;
    }

    // 2. get Exist Brand
    public boolean existBrand(String brand) {
        if (Brand_List.getBrandList() == null || Brand_List.getBrandList().isEmpty()) {
            return true;
        }

        for (Brand b : Brand_List.getBrandList()) {
            if (b.getId().equalsIgnoreCase(brand) || b.getName().equalsIgnoreCase(brand)) {
                return true;
            }
        }
        return false;
    }

    // 3. get Exist Category
    public boolean existCategory(String category) {
        if (Category_List.getCategoryList() == null || Category_List.getCategoryList().isEmpty()) {
            return true;
        } // Check null

        for (Category c : Category_List.getCategoryList()) {
            if (c.getName().equalsIgnoreCase(category) || c.getId().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    // Method
    // 1. Get id
    public String generateId() { // P0000 random unique id

        if (!ProductList.isEmpty()) {
            String Output = "P";

            // DESCRIPTION FOR THE VARIABLE SIZE
            // 1. Get the latest Product in the ProductList
            // 2. Get the ID and trim it 4 last characters to get the number
            // NOTICE 2: Because the format for id is P0000 so we take the last 4 digit and there is no sort function in the question so the latest Product always have the highest ID 
            // 3. Transform 4 Digits (Character) to 4 Digits (Integer)
            // size array: 3
            // => index = 2
            // 0     1     2
            // P0005 P0006 P0007
            // 8
            int size = Integer.parseInt(ProductList.get(ProductList.size() - 1).getId().substring(1, 5)) + 1;

            int temp_size = size;
            int num_space = 0;

            while (temp_size > 0) { // getting number of spare space for 0 to replace
                temp_size /= 10;
                num_space++;
            }
            for (int i = 0; i < 4 - num_space; i++) {
                Output += "0";
            }
            Output += size;
            return Output;
        } else {
            return "P0000";
            // if the ProductList is null then it will generate the first ID ever made which is P0000
        }

    }

    // 2. Get name
    public String inputName() {
        do {
            String Output = Input_Validation.getString("+ Name").trim();
            if (Output.matches(NAME_FORMAT)) {
                return Output;
            } else {
                System.out.println("WARNING | Invalid format, the valid should be characters with length between 3-20");
            }
        } while (true);
    }

    // 3. Get brand
    public Brand inputBrand() {
        if (Brand_List.getBrandList() == null || Brand_List.getBrandList().isEmpty()) {
            return null;
        }
        UISupport.displayBrands(Brand_List.getBrandList());
        int index = Input_Validation.getInt("+ Brands Index", 0, Brand_List.getBrandList().size());
        return Brand_List.getBrandList().get(index - 1);
    }

    // 4.  Get category
    public Category inputCategory() {
        if (Category_List.getCategoryList() == null || Category_List.getCategoryList().isEmpty()) {
            return null;
        }
        UISupport.displayCategories(Category_List.getCategoryList());
        int index = Input_Validation.getInt("+ Category Index", 0, Category_List.getCategoryList().size());
        return Category_List.getCategoryList().get(index - 1);
    }

    // 5. Get year
    public int inputYear() {
        return Input_Validation.getInt("+ Year", 1945, 2024);
    }

    // 6. Get list price
    public double getListPrice() {
        return Input_Validation.getDouble("+ List Price", 0, 100000000);
    }

    // FEATURES
    // 1. Add product
    @Override
    public void add() {
        // Creating Product
        Product p = null; // id, name, brand, category, year, list price
        if (ProductList == null) {
            ProductList = new ArrayList<>();
        }
        // 1. Getting attributes for new Product
        String id = generateId();
        String name = inputName();
        Brand brand = inputBrand();
        Category category = inputCategory();
        int year = inputYear();
        double listPrice = getListPrice();

        // 2. Create Product with information above
        p = new Product(id, name, brand, category, year, listPrice);

        // adding to list
        ProductList.add(p);
    }

    // 2. Search product by name
    @Override
    public void searchProduct(String name) {
        // CHECKING NULL
        if (ProductList == null) {
            System.out.println("Have no product in the current list");
            return;
        }

        // ATTRIBUTES
        ArrayList<Product> Output_List = null;
        /* Array of available product with name */

        // Checking and adding product to the array if there it has the name
        for (Product p : ProductList) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                if (Output_List == null) { // Checking null
                    Output_List = new ArrayList<>();
                    Output_List.add(p);
                } else {
                    Output_List.add(p);
                }
            }
        }

        if (Output_List == null) { // checking null
            // return because the array is empty
            System.out.println(String.format("There is no product has '%s' in its name", name));

        } else {
            // print out all the Product in array
            UISupport.displayProduct(Output_List, 1);
        }
    }

    // 3. Update product
    @Override
    public void updateProduct(String id) {
        // PRINT MENU FOR UPDATE
        // 1. NAME
        // 2. BRAND
        // 3. CATEGORY
        // 4. YEAR
        // 5. LIST PRICE
        // 6. QUIT
        int ProIndex = existId(id);
        if (ProIndex == -1) {
            System.out.println("# Warning This ID doesn't exist");

            return;
        }
        Product updated_product = ProductList.get(ProIndex);

        IMenu updateMenu = new Menu();
        updateMenu.addElement("Name");
        updateMenu.addElement("Brand ID");
        updateMenu.addElement("Category ID");
        updateMenu.addElement("Model year");
        updateMenu.addElement("List price");
        updateMenu.addElement("Quit");

        do {
            UISupport.displaySingle(updated_product);

            updateMenu.printList("UPDATE MENU");

            int choice = Input_Validation.getInt("+ Choice", 1, 6);
            switch (choice) {
                case 1: // update name
                    // Get new name
                    String name = inputName();

                    // Get confirmation to update name from the user
                    if (Input_Validation.getBoolean("Would you like to update this new name to the product")) {
                        updated_product.setName(name);
                    }

                    break;
                case 2: // update brand
                    // display the available brands
                    System.out.println("Here is the available brands");
                    UISupport.displayBrands(Brand_List.getBrandList());

                    // Get new brand
                    Brand brand = inputBrand();

                    // Get confirmation to update brand from the user
                    if (Input_Validation.getBoolean("Would you like to update this new brand to the product")) {
                        updated_product.setBrand(brand);
                    }

                    break;
                case 3: // Update category
                    System.out.println("Here is the available categories");
                    UISupport.displayCategories(Category_List.getCategoryList());

                    // get new category
                    Category category = inputCategory();

                    // Get confirmation to update
                    if (Input_Validation.getBoolean("Would you like to update this new year to the product")) {
                        updated_product.setCategory(category);

                    }

                    break;
                case 4: // Update year
                    // Get new year
                    int year = inputYear();

                    // Get confirmation to update
                    if (Input_Validation.getBoolean("Would you like to update this new model year")) {
                        updated_product.setYear(year);
                    }

                    break;
                case 5: // update list price
                    // get new list price
                    double listPrice = getListPrice();

                    // Get confirmation to update
                    if (Input_Validation.getBoolean("Would you like to update this new list price")) {
                        updated_product.setListPrice(listPrice);

                    }

                    break;
                case 6:
                    return;
            }
            UISupport.displaySingle(updated_product);
            if (Input_Validation.getBoolean("Would you like to update this product")) {
                ProductList.set(ProIndex, updated_product);
                System.out.println("Update successfully");
            }

        } while (!Input_Validation.getBoolean("Would you like to return to Menu"));
    }

    // 4. Delete product
    @Override
    public void deleteProduct() {

        IMenu deleteMenu = new Menu();
        deleteMenu.addElement("Delete by ID      | ID format: Pxxxx");
        deleteMenu.addElement("Delete by Product");
        deleteMenu.addElement("Quit");
        deleteMenu.printList("DELETE MENU");

        int choice = Input_Validation.getInt("Choice", 1, 3);

        switch (choice) {
            case 1: // Delete by id
                if (ProductList == null || ProductList.isEmpty()) {
                    System.out.println("The list is Empty");
                    break;
                }
                // get Id
                String id = Input_Validation.getString("- Id: ");
                int ProIndex = existId(id);
                while (ProIndex == -1) {
                    System.out.println("WARNING | This id doesn't exist");
                    UISupport.displayProduct(ProductList, 0);
                    Input_Validation.getString("- Re-enter Id: ");
                }
                UISupport.displaySingle(ProductList.get(ProIndex));
                if (Input_Validation.getBoolean("Would you like to delete this product")) {
                    ProductList.remove(ProIndex);
                }
                break;
            case 2: // delete by product
                if (ProductList == null || ProductList.isEmpty()) {
                    System.out.println("The list is Empty");
                    break;
                }
                UISupport.displayProduct(ProductList, 0);
                // get index
                int index = Input_Validation.getInt("- Index: ", 1, ProductList.size());

                // Check available
                while (index > ProductList.size() || index < 0) {
                    System.out.println("WARNING | invalid index");
                    index = Input_Validation.getInt("- re-enter index: ", 1, ProductList.size());
                }

                // Confirmation
                UISupport.displaySingle(ProductList.get(index - 1));
                if (Input_Validation.getBoolean("Would you like to delete this product")) {
                    ProductList.remove(index - 1);
                }
                break;
            case 3:
                return;

        }
    }

    // 5. Save product to file
    @Override
    public void save() {
        try {
            File f = new File(file_path);
            FileWriter fw = new FileWriter(f);

            for (Product p : ProductList) {
                fw.write(p.toString() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(String.format("# Warning | %s doesn't exist"));
            return;
        } finally {
            System.out.println("Successfully save\n");
        }
    }

    // 6. Print list product from files
    @Override
    public void printList() {
        UISupport.displayProduct(ProductList, 0);
    }
}
