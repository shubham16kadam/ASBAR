/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Cookie;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;


/**
 *
 * @author pk
 */
public class webdump {
    WebElement get,value;
    WebDriver driver;
    int textSize=0,size=0;
    String type=null;
    String textContent=null;
    static String username1;
    static String urlStore=null,urlStoreNew=null;
    Set<String> attribute,content;
    static boolean temp2;
    JSONObject obj = new JSONObject();
    
    
    //global variables for storing values of user to retrive from crash
    String firstName;
    String lastName;
    String regEmail;
    String regPassword;
    public webdump() throws InterruptedException{
        try {
            username();
            //AWSCredentials Credentials = new BasicAWSCredentials("AKIAJ7S5ZE2KQFBKFUBA","cSiWdW8hMSKv9CyFSXgqdCO0fLZSKrtSEl0Ozk9k");

            //AmazonS3 s3client = new AmazonS3Client(Credentials);
            condition();
            Process p1 = Runtime.getRuntime().exec("whoami");
            
            InputStream stream = p1.getInputStream();
            BufferedReader username = new BufferedReader(new InputStreamReader(stream));
            String user = username.readLine();

            //username = new BufferedReader(new InputStreamReader(user.getInputStream()));
            out.println("" + user);
            boolean successful1=false;
            File dirmain=new File("/home/"+user+"/Asbar");
            if (!dirmain.exists()) {
                successful1 = dirmain.mkdir();  // attempt to create the directory here
            }
            if (successful1) {
                // creating the directory succeed
                
                System.out.println("directory was created successfully");
            } else {
                // creating the directory failed
                System.out.println("already exist / failed trying to create the directory");
            }
            boolean successful2=false;
            File dirmain1=new File("/home/"+user+"/Asbar/"+username1);
            if (!dirmain1.exists()) {
                successful2 = dirmain1.mkdir();  // attempt to create the directory here
            }
            if (successful2) {
                // creating the directory succeed
                
                System.out.println("directory was created successfully");
            } else {
                // creating the directory failed
                System.out.println("already exist / failed trying to create the directory");
            }
            boolean successful3=false;
            File dirmain2=new File("/home/"+user+"/Asbar/"+username1+"/WebApp");
            if (!dirmain2.exists()) {
                successful3 = dirmain2.mkdir();  // attempt to create the directory here
            }
            if (successful3) {
                // creating the directory succeed
                
                System.out.println("directory was created successfully");
            } else {
                // creating the directory failed
                System.out.println("already exist / failed trying to create the directory");
            }
            System.setProperty("webdriver.chrome.driver","chromedriver");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            String folder="mkdir ";
            String dom="";
            while(temp2){
                try{
                    urlStore=driver.getCurrentUrl();
                    Thread.sleep(10000);
                    
                    
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    dom = (String) js.executeScript("return document.domain");
                    System.out.println("domain  : " + dom);
                    //folder=folder+" "+dom;
                    //Process p = Runtime.getRuntime().exec(folder);
                    boolean successful14=false;
                    File dirmain3=new File("/home/"+user+"/Asbar/"+username1+"/WebApp/"+dom);
                    if (!dirmain3.exists()) {
                        successful14 = dirmain3.mkdir();  // attempt to create the directory here
                    }
                    if (successful14) {
                        // creating the directory succeed

                        System.out.println("directory was created successfully");
                    } else {
                        // creating the directory failed
                        System.out.println("already exist / failed trying to create the directory");
                    }
                    Thread.sleep(2000);
                    urlStore=driver.getCurrentUrl();
                    FileWriter urll = new FileWriter("/home/"+user+"/Asbar/"+username1+"/WebApp/"+dom+"/url.txt");
                    
                    urll.write(""+urlStore);
                    urll.flush();
                    
                    File file=new File("/home/"+user+"/Asbar/"+username1+"/WebApp/"+dom+"/session.data");
                    file.delete();
                    file.createNewFile();
                    FileWriter fos=new FileWriter(file);
                    BufferedWriter bos=new BufferedWriter(fos);
                    for(Cookie ck: driver.manage().getCookies())
                    {
                        bos.write((ck.getName()+";"
                                +ck.getValue()+";"
                                +ck.getDomain()+";"  
                                +ck.getPath()+";"
                                +ck.getExpiry()+";"
                                +ck.isSecure()));
                        System.out.println(ck);
                        bos.newLine();
                        
                        
                    }
                    
                    bos.flush();bos.close();
                    fos.close();
                    
                    if(urlStore.equals("data;"))
                    {
                        System.out.println("NuLL");
                    }
                    else
                    {
                        
                        urlStore=driver.getCurrentUrl();
                        
                        List<WebElement> myTagsWithType = driver.findElements(By.cssSelector("[type]"));
                        
                        List<String>textVal= new ArrayList<String>();
                        //outer:
                        {
                        
                        // now printing all id values one by one
                        size=myTagsWithType.size();
                        System.out.println("Total tags with id as one of the attribute is : " + myTagsWithType.size());
                        
                        for(int i=0;i<size;i++){
                            
                            type=myTagsWithType.get(i).getAttribute("type");
                            if(type.equals("text") || type.equals("password") || type.equals("email") || type.equals("tel") ){
                                textVal.add(myTagsWithType.get(i).getAttribute("name"));
                                ++textSize;
                            }
                        }
                        
                        
                        
                        try {
                            for(int i=0;i<textSize;++i)
                            {
                                value=driver.findElement(By.name(textVal.get(i)));
                                textContent=value.getAttribute("value");
                                
                                urlStore=driver.getCurrentUrl();
                                {
                                out.println("Value of "+textVal.get(i)+" field::"+textContent);
                                obj.put(textVal.get(i),textContent);
                                //obj.put("url",urlStore);
                            }
                                
                                
                                
                            }
                            
                            
                            FileWriter file2 = new FileWriter("/home/"+user+"/Asbar/"+username1+"/WebApp/"+dom+"/Form.json");
                            file2.write(obj.toString());
                            file2.flush();
                            Thread.sleep(100);
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(webdump.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    }
                    
                    
                    condition();
                }
                catch(Exception e){}               
                
                //driver.manage().deleteAllCookies();
            }
        } catch (IOException ex) {
            Logger.getLogger(webdump.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    public static void username() throws IOException{
        try {
            FileInputStream fstream = null;
            fstream = new FileInputStream("username");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            username1=br.readLine();
            
            fstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(webdump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void condition() throws IOException{
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("flags2");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            strLine=br.readLine();
            int temp;
            
            temp=Integer.parseInt(strLine);
            if(temp==0){
                temp2=false;
            }
            else{
                temp2=true;
            }
             fstream.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(webdump.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public static void main(String[] args) throws InterruptedException {
       webdump s=new webdump();
        
    }
    
}
