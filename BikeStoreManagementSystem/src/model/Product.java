package model;

public class Product extends Item {
    protected Brand brand;
    protected Category category;
    protected int year;
    protected double listPrice;
    
    // Constructor
    public Product(){
        
    }
    
    public Product(String id, String name, Brand brand, Category category, int year,double listPrice){
        super(id,name);
        this.listPrice = listPrice;
        this.brand = brand;
        this.category = category;
        this.year = year;
        
    }
    
    
    
    // setter and getters
    public double getListPrice() {
        return listPrice;
    }
    
    public void setListPrice(double listPrice){
        this.listPrice = listPrice;
        System.out.println("Update successfully");
    }
    
    public void setYear(int year){
        this.year = year;
        System.out.println("Update successfully");
    }
    
    public int getYear(){
        return year;
    }
    
        public Brand getBrand(){
        return brand;
    }
    
    public void setBrand(Brand brand){
        this.brand = brand;
        System.out.println("Update successfully");
    }
    
    public Category getCategory(){
        return category;
    }
    
    public void setCategory(Category category){
        this.category = category;
        System.out.println("Update successfully");
    }
    
    // Update to String
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%d,%s",super.toString(),brand.getName(),category.getName(),year,listPrice);
        
    }
}
