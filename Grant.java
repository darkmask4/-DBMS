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

public class Grant {
    //��grant��ͷ��sql���,����WITH GRANT OPTION
    private static final Pattern PATTERN_GRANT1 = Pattern.compile("grant (.*) to (.*) with check option");
    private static final Pattern PATTERN_GRANT2 = Pattern.compile("grant (.*) to (.*)");

     public static void updatePermission(String userName, String power1) {
        File file = new File("user.txt");
        List<String> User = new ArrayList<>();
        //��user.txt�е����ݶ��뵽User��
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
        //��User�е�����д�뵽user.txt��
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

    public static boolean If_grantRight(String sql){
        Matcher grant1 = PATTERN_GRANT1.matcher(sql);
        if(grant1.find()){
            String[]right = grant1.group(1).split(",");
            String userName = grant1.group(2);
            //�����û����ҵ����û���Ȩ��
            String userPower = getUserPower(userName);
            //�����޸��˵�Ȩ��ת��Ϊ�ַ�����
            char []user_Power = new char[7];
            try{
            for(int i = 0;i < 7;i++){
                char c = userPower.charAt(i);
                user_Power[i] = c;
            }}catch(IndexOutOfBoundsException e){
                System.out.println("���û�������");
                return false;
            }
            //�������û���Ȩ��ת��Ϊ�ַ�����
            char []power = new char[7];
            for(int i = 0;i < 7;i++){
                char c = User.access.charAt(i);
                power[i] = c;
            }

            for(int i = 0;i < right.length;i++){
                if(right[i].equals("select")&&power[0] == '2'){
                    user_Power[0] = '2';
                }
                else if(right[i].equals("insert")&&power[1] == '2'){
                    user_Power[1] = '2';
                }
                else if(right[i].equals("update")&&power[2] == '2'){
                    user_Power[2] = '2';
                }
                else if(right[i].equals("delete")&&power[3] == '2'){
                    user_Power[3] = '2';
                }
                else if(right[i].equals("create")&&power[4] == '2'){
                    user_Power[4] = '2';
                }
                else if(right[i].equals("drop")&&power[5] == '2'){
                    user_Power[5] = '2';
                }
                else if(right[i].equals("alter")&&power[6] == '2'){
                    user_Power[6] = '2';
                }
                else if(right[i].equals("all")&&User.access.equals("2222222")){
                    for(int j = 0;j < 7;j++){
                        user_Power[j] = '2';
                    }
                }
            }
            //��powerת��Ϊ�ַ���
            String power1 = "";
            for(int i = 0;i < 7;i++){
                power1 += user_Power[i];
            }
            //��power1�޸�ԭ�����û���Ȩ�ޣ�д��user.txt
            updatePermission(userName,power1);
            return true;

    }
    Matcher grant2 = PATTERN_GRANT2.matcher(sql);
    if(grant2.find()){
            String[]right = grant2.group(1).split(",");
            String userName = grant2.group(2);
             //�����û����ҵ����û���Ȩ��
            String userPower = getUserPower(userName);
            //�����޸��˵�Ȩ��ת��Ϊ�ַ�����
            char []user_Power = new char[7];
            try{
            for(int i = 0;i < 7;i++){
                char c = userPower.charAt(i);
                user_Power[i] = c;
            }}catch(IndexOutOfBoundsException e){
                System.out.println("���û�������");
                return false;
            }
            //�������û���Ȩ��ת��Ϊ�ַ�����
            char []power = new char[7];
            for(int i = 0;i < 7;i++){
                char c = User.access.charAt(i);
                power[i] = c;
            }

            for(int i = 0;i < right.length;i++){
                if(right[i].equals("select")&&power[0] == '2'&&user_Power[0] != '2'){
                    user_Power[0] = '1';
                }
                else if(right[i].equals("insert")&&power[1] == '2'&&user_Power[1] != '2'){
                    user_Power[1] = '1';
                }
                else if(right[i].equals("update")&&power[2] == '2'&&user_Power[2] != '2'){
                    user_Power[2] = '1';
                }
                else if(right[i].equals("delete")&&power[3] == '2'&&user_Power[3] != '2'){
                    user_Power[3] = '1';
                }
                else if(right[i].equals("create")&&power[4] == '2'&&user_Power[4] != '2'){
                    user_Power[4] = '1';
                }
                else if(right[i].equals("drop")&&power[5] == '2'&&user_Power[5] != '2'){
                    user_Power[5] = '1';
                }
                else if(right[i].equals("alter")&&power[6] == '2'&&user_Power[6] != '2'){
                    user_Power[6] = '1';
                }
                else if(right[i].equals("all")&&User.access.equals("2222222")&&!userPower.equals("2222222")){
                    for(int j = 0;j < 7;j++){
                        user_Power[j] = '1';
                    }
                }
            }
            //��powerת��Ϊ�ַ���
            String power1 = "";
            for(int i = 0;i < 7;i++){
                power1 += user_Power[i];
            }
            //��power1�޸�ԭ�����û���Ȩ�ޣ�д��user.txt
            updatePermission(userName,power1);
            return true;

        }
    
return false;
}}