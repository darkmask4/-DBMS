package dbms;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Revoke {
    private static final Pattern PATTERN_REVOKE1 = Pattern.compile("revoke grant option for (.*) from (.*)");
    private static final Pattern PATTERN_REVOKE2 = Pattern.compile("revoke (.*) from (.*)");

    public static String getUserPower(String userName) {
        File file = new File("user.txt");
        String line = null;
        String userPower = null;
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                String[] info = line.split(" ");
                if(info[0].equals(userName)){
                    userPower = info[2];
                }
            }
            br.close();
            fr.close();}catch(IOException e){
                e.printStackTrace();
            }
        return userPower;
    }

    public static void updatePermission(String userName, String power1) {
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
        for(int i = 0;i < User.size();i++){
            String[] info = User.get(i).split(" ");
            if(info[0].equals(userName)){
                User.set(i,userName+" "+info[1]+" "+power1);
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
        
    }

    public static boolean If_revokeRight(String sql){
        Matcher revoke1 = PATTERN_REVOKE1.matcher(sql);
        if(revoke1.find()){
            String[]right = revoke1.group(1).split(",");
            String userName = revoke1.group(2);
            //根据用户名找到该用户的权限
            String userPower = getUserPower(userName);
            //将被修改人的权限转化为字符数组
            char []user_Power = new char[7];
            for(int i = 0;i < userPower.length();i++){
                user_Power[i] = userPower.charAt(i);
            }
            //将登入用户的权限转化为字符数组
            char []power = new char[7];
            for(int i = 0;i < 7;i++){
                char c = User.access.charAt(i);
                power[i] = c;
            }
            for(int i = 0;i < right.length;i++){
                if(right[i].equals("select")&&power[0] == '2'&&user_Power[0] != '0'){
                    user_Power[0] = '1';
                }
                else if(right[i].equals("insert")&&power[1] == '2'&&user_Power[1] != '0'){
                    user_Power[1] = '1';
                }
                else if(right[i].equals("update")&&power[2] == '2'&&user_Power[2] != '0'){
                    user_Power[2] = '1';
                }
                else if(right[i].equals("delete")&&power[3] == '2'&&user_Power[3] != '0'){
                    user_Power[3] = '1';
                }
                else if(right[i].equals("create")&&power[4] == '2'&&user_Power[4] != '0'){
                    user_Power[4] = '1';
                }
                else if(right[i].equals("drop")&&power[5] == '2'&&user_Power[5] != '0'){
                    user_Power[5] = '1';
                }
                else if(right[i].equals("alter")&&power[6] == '2'&&user_Power[6] != '0'){
                    user_Power[6] = '1';
                }
                else if(right[i].equals("all")&&User.access.equals("2222222")){
                    for(int j = 0;j < 7;j++){
                        user_Power[j] = '1';
                    }
                }
                }
            String newPower = String.valueOf(user_Power);
            //将修改后的权限写入文件
            updatePermission(userName,newPower);
            return true;

        }
        Matcher revoke2 = PATTERN_REVOKE2.matcher(sql);
        if(revoke2.find()){
            String[]right = revoke2.group(1).split(",");
            String userName = revoke2.group(2);
            //根据用户名找到该用户的权限
            String userPower = getUserPower(userName);
            //将被修改人的权限转化为字符数组
            char []user_Power = new char[7];
            for(int i = 0;i < userPower.length();i++){
                user_Power[i] = userPower.charAt(i);
            }
            //将登入用户的权限转化为字符数组
            char []power = new char[7];
            for(int i = 0;i < 7;i++){
                char c = User.access.charAt(i);
                power[i] = c;
            }
            for(int i = 0;i < right.length;i++){
                if(right[i].equals("select")&&power[0] == '2'&&user_Power[0] != '0'){
                    user_Power[0] = '0';
                }
                else if(right[i].equals("insert")&&power[1] == '2'&&user_Power[1] != '0'){
                    user_Power[1] = '0';
                }
                else if(right[i].equals("update")&&power[2] == '2'&&user_Power[2] != '0'){
                    user_Power[2] = '0';
                }
                else if(right[i].equals("delete")&&power[3] == '2'&&user_Power[3] != '0'){
                    user_Power[3] = '0';
                }
                else if(right[i].equals("create")&&power[4] == '2'&&user_Power[4] != '0'){
                    user_Power[4] = '0';
                }
                else if(right[i].equals("drop")&&power[5] == '2'&&user_Power[5] != '0'){
                    user_Power[5] = '0';
                }
                else if(right[i].equals("alter")&&power[6] == '2'&&user_Power[6] != '0'){
                    user_Power[6] = '0';
                }
                else if(right[i].equals("all")&&User.access.equals("2222222")){
                    for(int j = 0;j < 7;j++){
                        user_Power[j] = '0';
                    }
                }
                }
            String newPower = String.valueOf(user_Power);
            //将修改后的权限写入文件
            updatePermission(userName,newPower);
            return true;

        }
        return false;
    }
}