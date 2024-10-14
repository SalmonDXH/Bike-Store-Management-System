
package validation;

import model.Product;
import model.Brand;
import model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UISupport {

    static public void displayProduct(ArrayList<Product> pl, int choice) {
        ArrayList<Product> input_array = pl;

        if (pl == null || pl.isEmpty()) {
            System.out.println("There is no product in the list");
            return;
        }
        switch (choice) {
            case 1: // 1. year ascending
                Collections.sort(input_array, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return Integer.compare(p1.getYear(), p2.getYear());
                    }
                });
                break;
            case 2: // 2. price descending - name ascending
                Collections.sort(input_array, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        int nums = Double.compare(p2.getListPrice(), p1.getListPrice());
                        if (nums == 0) {
                            return p1.getName().compareTo(p2.getName());
                        } else {
                            return nums;
                        }
                    }
                });
                break;
        }
        
        System.out.println("Here is PRODUCT list");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("| INDEX   | ID    | NAME              | BRAND             | CATEGORY          | MODEL_YEAR        | LIST_PRICE        |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        int index = 1;
        for (Product p : input_array) {
            System.out.println(String.format("| %-8d| %-6s| %-18s| %-18s| %-18s| %-18d| %-18.2f|", index,p.getId(), p.getName(), p.getBrand().getName(), p.getCategory().getName(), p.getYear(), p.getListPrice()));
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            index++;
        }
        System.out.println("");

    }

    static public void customDisplay(ArrayList<String> list, String context) {
        if (list == null || list.isEmpty()) {
            System.out.println("This list is empty");
            return;
        }
        System.out.println("----------------------------------------------------");
        System.out.printf("| INDEX     | LIST OF %-28s |\n", context.toUpperCase());
        System.out.println("----------------------------------------------------");

        int index = 1;
        for (String e : list) {
            System.out.printf("| %-10d | %-38s |\n", index, e);
            System.out.println("----------------------------------------------------");
            index++;
        }
        System.out.println("");
    }

    static public void displaySingle(Product p) {
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID    | NAME              | BRAND             | CATEGORY          | MODEL_YEAR        | LIST_PRICE        |");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("| %-6s| %-18s| %-18s| %-18s| %-18d| %-18.2f|", p.getId(), p.getName(), p.getBrand().getName(), p.getCategory().getName(), p.getYear(), p.getListPrice()));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }
    
    static public void displayBrands(ArrayList<Brand> list){
        if (list == null || list.isEmpty()){
            System.out.println("This list is empty");
            return;
        }
        System.out.println("Here is BRAND list");
        System.out.println("------------------------------------------------------------");
        System.out.println("| INDEX   | ID    | NAME              | LOCATION           |");
        System.out.println("------------------------------------------------------------");
        int index = 1;
        for (Brand b : list){
            System.out.println(String.format("| %-8d| %-6s| %-18s| %-19s|",index,b.getId(), b.getName(),b.getLocation()));
            System.out.println("------------------------------------------------------------");
            index++;
        }
        System.out.println("\n");
    }
    
    static public void displayCategories(ArrayList<Category> list){
        if (list == null || list.isEmpty()){
            System.out.println("This list is empty");
            return;
        }
        System.out.println("Here is CATEGORY list");
        System.out.println("---------------------------------------");
        System.out.println("| INDEX   | ID    | NAME              |");
        System.out.println("---------------------------------------");
        int index = 1;
        for (Category c : list){
            System.out.println(String.format("| %-8d| %-6s| %-18s|",index,c.getId(),c.getName()));
            System.out.println("---------------------------------------");
            index++;
        }
        System.out.println("\n");
    }
}
