import java.io.*;
import java.util.*;
import javax.print.DocFlavor.STRING;
 public class User{
   String id;
   String password;
   public static String access;

   User(String id, String password){
     this.id = id;
     this.password = password;
   }

    public String getId(){
      return id;
    }
    
    public String getPassword(){
      return password;
    }

    //将用户信息写入文件
    public void writeToFile(){
      try{
        File file = new File("user.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(id + " " + password);
        bw.newLine();
        bw.close();
        fw.close();
      }catch(IOException e){
        e.printStackTrace();
      }
    }

    //从文件中读取用户信息
    public static ArrayList<User> readFromFile(String id, String password){
      ArrayList<User> users = new ArrayList<User>();
      try{
        File file = new File("user.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while((line = br.readLine()) != null){
          String[] info = line.split(" ");
          User user = new User(info[0], info[1]);
          users.add(user);
        }
        br.close();
        fr.close();
      }catch(IOException e){
        e.printStackTrace();
      }
      return users;
    }

    public static boolean login(String id,String password){
      ArrayList<User> users = readFromFile(id, password);
      for(User user : users){
        if(user.getId().equals(id) && user.getPassword().equals(password)){
          return true;
        }
      }
      return false;
    }
    public static boolean register(String id){
      //如果用户名已存在，返回false
      ArrayList<User> users = readFromFile(id, "");
      for(User user : users){
        if(user.getId().equals(id)){
          return false;
        }
      }
      return true;
    }
    
    
 }
 
