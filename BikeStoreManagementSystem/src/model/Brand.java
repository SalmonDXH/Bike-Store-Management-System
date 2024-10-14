
package model;

public class Brand extends Item{
    protected String location;
    
    public Brand(){
        
    }
    
    public Brand(String id,String name, String location){
        super(id,name);
        this.location = location;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public String getLocation(){
        return location;
    }
    
    @Override
    public String toString(){
        return String.format("%s, %s",super.toString(),location);
    }
}
