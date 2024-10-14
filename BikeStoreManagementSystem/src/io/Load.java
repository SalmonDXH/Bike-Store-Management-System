package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.Category;

public class Load {
    static public ArrayList<String> load(String file){
        ArrayList<String> List = null;
        try {
            List = new ArrayList<>();
            File fList = new File(file);
            FileReader frList = new FileReader(fList);
            BufferedReader brCategory = new BufferedReader(frList);
            String line;

            while ((line = brCategory.readLine()) != null) {
                List.add(line);
            }
        } catch (IOException e) {
            System.out.println(" Warning | Category.txt doesn't exist");
        }
        return List;
    }
}
