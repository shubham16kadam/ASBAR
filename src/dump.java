
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import project.upload;


public class dump implements Runnable {

    private String appName, appName2;
    private static String bucketName = "asbarbucket";
    private static String keyName = "";
    int cnt;
    boolean temp2;
    static String username="";
    private dump(String appName, int cnt) {
        this.appName = appName;
        this.cnt = cnt;

    }

    public void username() throws IOException{
        try {
            FileInputStream fstream = null;
            fstream = new FileInputStream("username");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            username=br.readLine();
            
            fstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(dump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void condition() throws IOException{
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("flags");
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(dump.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public static void main(String ra[]) {
        // new Thread(new dump("vim",-1)).start();
        new Thread(new dump("C", 0)).start();
        new Thread(new dump("nano", 0)).start();
        new Thread(new dump("vim", 0)).start();
        new Thread(new dump("pico", 0)).start();
        new Thread(new dump("java", 0)).start();
        new Thread(new dump("python", 0)).start();
        new Thread(new dump("gedit", 0)).start();

    }

    @Override
    public void run() {
        try {
            username();
            System.out.println(username);
            //AWSCredentials Credentials = new BasicAWSCredentials("AKIAJ7S5ZE2KQFBKFUBA","cSiWdW8hMSKv9CyFSXgqdCO0fLZSKrtSEl0Ozk9k");

            //AmazonS3 s3client = new AmazonS3Client(Credentials);
            Process p = Runtime.getRuntime().exec("whoami");
            
            InputStream stream = p.getInputStream();
            BufferedReader username1 = new BufferedReader(new InputStreamReader(stream));
            String user = username1.readLine();

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
            File dirmain1=new File("/home/"+user+"/Asbar/"+username);
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
            File dirmain2=new File("/home/"+user+"/Asbar/"+username+"/SystemApp");
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
            condition();
            while(temp2)
            {
                String cmd[] = {"/bin/sh", "-c", ""};
                String fileCmd[] = {"/bin/sh", "-c", ""};
                out.println(appName);
                if (appName.equals("C")) {
                    appName2 = "CPRGM";
                    String com = "\\./[a-z]";
                    cmd[2] = "bash t.sh";
                    //fileCmd[2]="ps -ef | grep "+""+"\"\""+com+"\"\""+" | awk '{print $9}'";
                } else {
                    appName2 = appName;
                    cmd[2] = "ps aux | awk '{print $11,$2}' | grep '" + "^" + appName + "'|awk '{print $2}'";
                    
                    //fileCmd[2]="ps -ef | grep '"+appName +"'| awk '{print $9}' ";
                }

                Process process = null, process1 = null, process2 = null;
                process = Runtime.getRuntime().exec(cmd);
                BufferedReader idReader = null, fileReader = null;
                idReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                //process1=Runtime.getRuntime().exec(fileCmd);
                //fileReader=new BufferedReader(new InputStreamReader(process1.getInputStream()));
                String str;
                List<String> idSet = new ArrayList<>();
                List<String> fileSet = new ArrayList<>();
                String fileName = null;
                while ((str = idReader.readLine()) != null) {
                    fileName = str;
                    out.println(str);
                    ++cnt;
                    idSet.add(str);
                    fileSet.add(fileName);
                    //out.println(fileName);
                }
                out.println("No of " + appName2 + " files opened:" + idSet.size());
                out.println(cnt);
                cnt = cnt - 2;
                int cnt2 = cnt;
                boolean successful = false;
                String path;
                File dir = null;

                for (String file : fileSet) {
                    dir = new File("/home/" + user + "/Asbar/"+username+"/SystemApp/" + appName2);
                    out.println(dir);
                    if (!dir.exists()) {
                        successful = dir.mkdir();  // attempt to create the directory here
                    }
                    if (successful) {
                  // creating the directory succeed

                        System.out.println("directory was created successfully");
                    } else {
                        // creating the directory failed
                        System.out.println("already exist / failed trying to create the directory");
                    }
                    new File("/home/" + user + "/Asbar/"+username+"/SystemApp/" + appName2 + "/" + file).mkdir();
                }
                int count = 0;

                for (String id : idSet) {
                    out.println("file name::" + fileSet.get(count));
                    String folderName = appName2 + "/" + fileSet.get(count) + "/";
                    out.println("**********" + folderName);
                    String criu[] = {"/bin/sh", "-c", "sudo criu dump -v4 -t '" + id + "' -D /home/'" + user + "'/Asbar/'" + username + "'/SystemApp/'" + folderName + "'/ -o dump.log --leave-running --shell-job "};
                    //String restore[]={"/bin/sh","-c","criu restore -v4  -D d -o rst.log --shell-job"};
                    out.println("now running criu");
                    process2 = Runtime.getRuntime().exec(criu);

                    Process ps1 = null, ps2 = null, ps3=null,ps4=null;
                    ps1 = Runtime.getRuntime().exec("ls", null, new File("/home/" + user + "/Asbar/"+username+"/SystemApp/" + folderName + "/"));
                    BufferedReader fileReader1 = null;
                    //idReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                    // process1=Runtime.getRuntime().exec(fileCmd);
                    fileReader1 = new BufferedReader(new InputStreamReader(ps1.getInputStream()));
                    String str1 = "";
                    //List<String> idSet=new ArrayList<>();
                    //List<String> fileSet1=new ArrayList<>();4
                    String cmd1[] = {"/bin/sh", "-c", "sudo chmod 777 /home/'" + user + "'/Asbar/'" + username + "'/SystemApp/'" + folderName + "'dump.log"};
                    String cmd2[] = {"/bin/sh", "-c", "sudo chmod 777 /home/'" + user + "'/Asbar/'" + username + "'/SystemApp/'" + folderName + "'stats-dump"};
                    ps2 = Runtime.getRuntime().exec(cmd1);
                    ps3 = Runtime.getRuntime().exec(cmd2);
                    String fileName1 = "";

                    while ((str1 = fileReader1.readLine()) != null) {

                        fileName1 = str1;
                         String cmd3[] = {"/bin/sh", "-c", "sudo chmod 777 /home/'" + user + "'/Asbar/'" + username + "'/SystemApp/'" +folderName+"''"+str1+"'"};
                         ps4 = Runtime.getRuntime().exec(cmd3);
                        out.println(str1);
                        try{
                        File file = new File("/home/" + user + "/Asbar/"+username+"/SystemApp/" + folderName + "" + fileName1);
                        
                        //s3client.putObject(new PutObjectRequest(
                        //        bucketName, "/" +username +"/SystemApp/"+folderName + "" + fileName1, file));
                        
                        }
                        catch(SdkClientException e){
                        }
                    }

                    --cnt2;
                    ++count;
                }
                condition();
            }
        } catch (IOException ex) {
            Logger.getLogger(dump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
