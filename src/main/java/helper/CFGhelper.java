package helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CFGhelper {
    static String filePath = "src/test/resources/extent.properties";
    public static  Properties getConfigAsInputStream(){
        Properties prop = new Properties();
        try{
            InputStream inputStream = new FileInputStream(filePath);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.out.println("file config notfound??");
            }
        }catch(IOException ex){
            System.out.println("error config helper:"+ex);
        }
        return prop;
    }

    public static String cons(String value)
    {
        String a ="";
        try{
            Properties prop = getConfigAsInputStream();
            a = prop.getProperty(value);
        }catch(Exception ex){
            System.out.println("ERROR:"+ex);
        }
        return a;
    }

    public static void insData(String nmVariable, String value) {
        Properties properties = new Properties();
        // Load properties from file
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        properties.setProperty(nmVariable, value);
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, null);
            System.out.println("Filename updated to: " + filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
