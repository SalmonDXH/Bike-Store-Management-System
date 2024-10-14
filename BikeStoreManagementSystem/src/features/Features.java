package features;

import object.IProductDao;
import object.ProductDao;

import validation.Input_Validation;

public class Features implements IFeatures {

    IProductDao p = new ProductDao();

    // 1. ADD PRODUCT
    @Override
    public void add() {
        do {
            System.out.println("-------- ADD NEW PRODUCT --------");
            p.add();
        } while (!Input_Validation.getBoolean("Would you like to return to Menu"));
    }

    // 2. SEARCH PRODUCT BY NAME
    @Override
    public void searchProduct() {
        do {
            System.out.println("-------- SEARCH PRODUCT --------");
            String name = Input_Validation.getString("+ Name");
            p.searchProduct(name);
        } while (!Input_Validation.getBoolean("Would you like to return to Menu"));
    }

    // 3. UPDATE PRODUCT
    @Override
    public void update() {
        System.out.println("-------- UPDATE PRODUCT --------");
        String id = Input_Validation.getString("+ ID");
        p.updateProduct(id);
    }

    // 4. DELETE PRODUCT :
    // - Id: Giving back product details
    // - Product: Confirm
    @Override
    public void delete() {
        do {
            p.deleteProduct();
        } while (!Input_Validation.getBoolean("Would you like to return to Menu"));
    }

    // 5. SAVE FILE
    @Override
    public void save() {
        p.save();
    }

    // 6. PRINT LIST
    @Override
    public void printList() {
        p.printList();
    }

    // 7. READ FILE
    @Override
    public void load() {
        p.load();
    }
}
