package dbms;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class User{
	 public static String access;

    //注册时将用户信息写入文件
	 public static boolean Register(String id, String password){
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
              br.close();
              fr.close();
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
              br.close();
              fr.close();
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
    
    public static boolean logoff(String id)
    {
    		boolean a=false;
            File file = new File("user.txt");
            List<String> User = new ArrayList<>();
            //将user.txt中的内容读入到User中
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = null;
                while((line = br.readLine()) != null){
                    User.add(line);
                }
                br.close();
                fr.close();}catch(IOException e){
                    e.printStackTrace();
                }
            for(int i = User.size()-1;i>=0;i--){
                String[] info = User.get(i).split(" ");
                if(info[0].equals(id)){
                	a=true;
                    User.remove(i);
                }
            }
            //将User中的内容写入到user.txt中
            try{
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i = 0;i < User.size();i++){
                    bw.write(User.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();}catch(IOException e){
                    e.printStackTrace();
                }
    	
    return a;
 }
}
 