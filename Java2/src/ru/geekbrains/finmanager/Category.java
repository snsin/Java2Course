package ru.geekbrains.finmanager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Category {
    private static final Set<String> names = 
            new HashSet<String>(Arrays.asList("other"));
    String name = "other";
    String description;
    
    public Category(String name, String description) {
        if (names.contains(name.toLowerCase())) {
            this.name = name;
        }
        this.description = description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public static String[] getCategories() {
        return names.toArray(new String[0]);
    }
    
    public static boolean addCategory(String name) {
        boolean result = false; 
        if (name != null) {
            result = names.add(name.toLowerCase());
        }   
        return result;
    }
}
