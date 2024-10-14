package object;
import model.Product;
import model.Brand;
import model.Category;
import java.util.ArrayList;

public interface IProductDao {
    
    void load();
    
    // FEATURES
    // 1. Add product
    void add();
    
    // 2. Search product by name
    void searchProduct(String name);
    
    // 3. Update product
    void updateProduct(String id);
    
    // 4.  Delete product
    void deleteProduct();
    
    // 5. Save product to file
    void save();
    
    // 6. Print list products from the file
    void printList();
}
