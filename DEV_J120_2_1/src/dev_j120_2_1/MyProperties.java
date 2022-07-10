/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_2_1;

/**
 *
 * @author USER
 */

import java.io.*;
import java.util.Properties;

public class MyProperties {
    Properties prop = new Properties();
    File propFile;

    public MyProperties () {
        propFile = new File("properti.prop");
        if (!propFile.exists()) {
            try {
                propFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public MyProperties (File propFile) {
        if (propFile == null)
            System.out.println("Аргумент не может быть null");
        else if (propFile.exists()){
            this.propFile = propFile;
            propertyLoading();
        } else
            System.out.println("Файл не существует");
    }

    public MyProperties(String path){
        if (path != null) {
            propFile = new File(path);
            if (!propFile.exists())
                System.out.println("Файл не существует");
            else
                propertyLoading();
        }else
            System.out.println("Аргумент не можкт быть null");
    }
    //возвращающий значение свойства с заданными именем
    public String getValue (String key) throws IOException {
        String value ="";
        if (propFile.exists()){
            prop.load(new FileReader(propFile));
            value = prop.getProperty(key);
        }
        return value;

    }

    //устанавливающий значение свойства с заданными именем
    public void setValue (String key, String value){
        prop.setProperty(key,value);
    }

    

    //удаляющий свойство с заданными именем; если свойства с таким именем нет, то метод ничего не делает
    public void removingPropertyWithGivenName(String key){
        prop.remove(key);
    }
    //проверяющий наличие свойства с заданными именем
    public boolean containsKey (String key){
        return prop.containsKey(key);
    }
    public  boolean containsValue (String value){
        return prop.containsValue(value);
    }

    //сохраняющий набор свойств в файл
    public void saveToFile (){
        try (Writer writer = new FileWriter(propFile)) {
            prop.store(writer, null);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    //сохраняющий набор свойств в другой файл
    public void saveToFile(File file) throws FileNotFoundException {
        if (file != null && file.exists()){
            try (Writer writer = new FileWriter(file)) {
                prop.store(writer, null);
            } catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
        else if (file != null && !file.exists()){
            try (Writer writer = new FileWriter(file)) {
                file.createNewFile();
                prop.store(writer, null);
            } catch (IOException ex){
                throw new RuntimeException(ex);
            }
        } else {
            throw new FileNotFoundException("Файл не найден");
        }
        // удаляем файл по-умолчанию, если ничего в него не сохраняли
        if (this.propFile.length() == 0)
            this.propFile.delete();
    }

    private void initialize(){
        for (int i =0; i <Integer.MAX_VALUE; i++){
            try{
                if (emtyFileCreation(i))
                    break;
            } catch (IOException ex){
                System.err.println("I/O exception");
                break;
            }
        }
    }

    private boolean emtyFileCreation (int number) throws IOException {
        File resourcesDir = new File("src/");
        resourcesDir.mkdir();
        propFile = new File(resourcesDir,"default_" + number + ".prop");
        return propFile.createNewFile();
    }

    private void propertyLoading(){
        if (propFile != null && propFile.exists()){
            try (FileInputStream fileInputStream = new FileInputStream(propFile)) {
                prop.load(fileInputStream);
            } catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
