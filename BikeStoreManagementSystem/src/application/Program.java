package application;
import validation.Input_Validation;
import features.Features;
import features.IFeatures;
import object.IMenu;
import object.Menu;

public class Program {
    public static void main(String[] args) {
        boolean run = false;
        IFeatures service = new Features();
        IMenu mainMenu = new Menu();
        mainMenu.addElement("Add a product"); //1
        mainMenu.addElement("Search product by name"); //2
        mainMenu.addElement("Update product"); //3
        mainMenu.addElement("Delete product"); //4
        mainMenu.addElement("Save");//5
        mainMenu.addElement("Print list");//6
        mainMenu.addElement("Quit");//7
        service.load();
        
        do {
            mainMenu.printList("MENU");
            // 1 . Add
            // 2 . Search
            // 3 . Update
            // 4 . Delete
            // 5 . Save
            // 6 . Print
            // 7 . Quit
            int menuChoice = Input_Validation.getInt("Choice", 1, 7);
            switch(menuChoice){
                case 1:
                    service.add();
                    break;
                case 2:
                    service.searchProduct();
                    break;
                case 3:
                    service.update();
                    break;
                case 4:
                    service.delete();
                    break;
                case 5:
                    service.save();
                    break;
                case 6:
                    service.printList();
                    break;
                case 7:
                    run = !run;
                    break;
            }
            
        } while (!run);
    }
    
}
