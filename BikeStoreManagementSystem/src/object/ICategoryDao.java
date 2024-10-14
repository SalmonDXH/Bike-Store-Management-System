package object;
import java.util.ArrayList;
import model.Category;

public interface ICategoryDao {
    void setCategories(String file);
    
    ArrayList<Category> getCategoryList();
}
