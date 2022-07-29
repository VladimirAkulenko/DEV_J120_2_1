/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dev_j120_2_1;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        MyProperties mp = new MyProperties();
        System.out.println("key1 =" + mp.getValue("key1"));
        mp.setValue("key2","value2");
        System.out.println("key2 =" + mp.getValue("key2"));
        mp.saveToFile();
        mp.setValue("key3","value3");
        mp.saveToFile();
        mp.removingPropertyWithGivenName("key4");
        mp.saveToFile();
        System.out.println("key4 =" + mp.getValue("key4"));
        System.out.println(mp.containsKey("key1"));
        mp.saveToFile(new File("prop.prop"));
    }
    
}
