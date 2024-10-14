package object;
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements IMenu {
    @Override
    public void addElement(String s){
        this.add(s);
    }
    
    @Override
    public void printList(String Header){
        if (this == null || this.isEmpty()){return;}
        String head = String.format("----------- %s -----------",Header);
        System.out.println(head);
        int index = 1;
        for (String s : this){
            System.out.println(String.format("%-2d. %s",index,s));
            index++;
        }
        for (int i = 0; i < head.length(); i++){
            System.out.print("-");
        }
        
        System.out.println("");
    }
    
}
