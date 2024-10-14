package model;

public abstract class Item {
    protected String id;
    protected String name = "none";


    public Item(){
        
    }
    
    public Item(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    
    public void setId(String id){
        this.id = id;
        System.out.println("Update successfully");
    }
    
    public void setName(String name){
        this.name = name;
        System.out.println("Update successfully");
    }
    
    
    
    
    public String toString(){
        return String .format("%s,%s",id,name);
    }
}
