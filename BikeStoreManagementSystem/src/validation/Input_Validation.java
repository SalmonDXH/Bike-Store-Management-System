package validation;

import java.util.Scanner;

public class Input_Validation {
    
    // Declare Scanner (input)
    static Scanner sc = new Scanner(System.in);
    
    // invalid format warning for inputing
    static String invalid_format = "\n# Warning | Invalid format";
    
    // Valid String
    public static String getString(String content){
        boolean not_valid = true;
        String Output;
        do {
            System.out.print(String.format("%s: ",content));
            Output = sc.nextLine();
            if (Output.isEmpty()){
                System.out.println(String.format("\n# Warning | %s shouldn't be blank",content));
            } else if (Output.matches("[a-zA-Z0-9 ]+")){
                Output.trim();
                not_valid = false;
            } else {
                System.out.println(String.format("\n# Warning | %s should be character only",content));
            }
        } while (not_valid);
        System.out.println("");
        return Output;
    }
    
    // Valid integer with min max
    public static int getInt(String content,int min, int max){
        boolean not_valid = true;
        int Output = 0;
        
        do{
            try {
                System.out.print(String.format("%s: ",content));
                Output = Integer.parseInt(sc.nextLine());
                if (Output < min || Output > max){
                    System.out.println(String.format("\n# Warning | %s must be between %d - %d",content,min,max));
                } else {
                    not_valid = false;
                }
            } catch (Exception e){
                System.out.println(invalid_format);
            }
        } while(not_valid);
        System.out.println("");
        return Output;
    }
    
    // Valid double with min max
    public static double getDouble(String content, double min, double max){
        boolean not_valid = true;
        double Output = 0;
        
        do{
            try {
                System.out.print(String.format("%s: ",content));
                Output = Double.parseDouble(sc.nextLine());
                if (Output < min || Output > max){
                    System.out.println(String.format("\n# Warning | %s must be between %.2f - %.2f",content,min,max));
                } else {
                    not_valid = false;
                }
            } catch (Exception e){
                System.out.println(invalid_format);
            }
        } while(not_valid);
        System.out.println("");
        return Output;
    }
    
    public static boolean getBoolean(String content){
        boolean not_valid = true;
        boolean choices = false;
        do {
            String Input_User;
            System.out.print(String.format("%s (y-n):\n> ",content));
            Input_User = sc.nextLine();
            
            if (Input_User.equalsIgnoreCase("y")){
                not_valid = !not_valid;
                choices = true;
                
            } else if (Input_User.equalsIgnoreCase("n")){
                not_valid = !not_valid;
                choices = false;
            } else {
                System.out.println("# Warning | Wrong format please enter Y or N");
            }
        } while (not_valid);
        System.out.println("");
        return choices;
    }
}
