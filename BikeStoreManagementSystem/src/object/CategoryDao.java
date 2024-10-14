package object;

import io.Load;
import model.Category;
import java.util.ArrayList;

public class CategoryDao implements ICategoryDao {

    ArrayList<Category> Category_List = null;
    final String path = "src/input/Category.txt";

    public CategoryDao() {
        setCategories(path);
    }

    @Override
    public void setCategories(String file) {
        Category_List = new ArrayList<>();
        ArrayList<String> lines = Load.load(file);
        for (String line : lines) {
            String[] brand_attributes = line.split(", "); // ID, NAME
            String cId = brand_attributes[0];
            String cName = brand_attributes[1];

            Category b = new Category(cId, cName);
            Category_List.add(b);
        }

    }

    @Override
    public ArrayList<Category> getCategoryList() {
        return Category_List;
    }

}
