import java.io.*;
 public class User{
   public static String access;

    //注册时将用户信息写入文件
    public boolean Register(String id, String password){
      try{
        File file = new File("user.txt");
        FileReader fr = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fr)) {
          String line = null;
          while((line = br.readLine()) != null){
            String[] info = line.split(" ");
            //判断用户名是否已存在
            if(info[0].equals(id)){
              System.out.println("该用户已存在");
              return false;
            }
          }
          br.close();
        }
        fr.close();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(id + " " + password+" "+"1000000");
        bw.newLine();
        bw.close();
        fw.close();
      }catch(IOException e){
        e.printStackTrace();
      }
      return true;
    }
    //登入验证
    public static boolean login(String id,String password){
      try{
        File file = new File("user.txt");
        FileReader fr = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fr)) {
          String line = null;
          while((line = br.readLine()) != null){
            String[] info = line.split(" ");
            //判断用户名和密码是否正确
            if(id.equals(info[0]) && password.equals(info[1])){
              access = info[2];
              return true;
            }
          }
          br.close();
        }
        fr.close();
      }catch(IOException e){
        e.printStackTrace();
      }
      return false;
    }
    
    
 }
 
