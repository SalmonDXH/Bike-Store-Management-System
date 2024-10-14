package object;

import io.Load;
import java.util.ArrayList;
import model.Brand;

public class BrandDao implements IBrandDao {

    ArrayList<Brand> Brand_List;
    final String file = "src/input/Brand.txt";

    public BrandDao() {
        setBrands(file);
    }

    @Override
    public void setBrands(String file) {
        ArrayList<Brand> List = new ArrayList<>();
        ArrayList<String> List_String = Load.load(file);
        
        for (String line : List_String) {
            String[] brand_attributes = line.split(", "); // ID, NAME, LOCATION
            String bId = brand_attributes[0];
            String bName = brand_attributes[1];
            String bLocation = brand_attributes[2];

            Brand b = new Brand(bId, bName, bLocation);
            List.add(b);
        }

        this.Brand_List = List;
    }

    @Override
    public ArrayList<Brand> getBrandList() {
        return Brand_List;
    }
}
