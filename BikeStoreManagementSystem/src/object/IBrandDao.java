package object;
import model.Brand;
import java.util.ArrayList;

public interface IBrandDao {
    
    void setBrands(String file);
    
    ArrayList<Brand> getBrandList();
    
    
}
