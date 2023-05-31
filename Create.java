package dbms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Create {
	private static final Pattern PATTERN_CREATE_TABLE = Pattern.compile("create table (.*) \\( (.*) \\)");
	private static final Pattern PATTERN_CREATE_USER = Pattern.compile("create user (.*) (.*)");
	public static boolean Ifcreate_table(String s)
	{
		Matcher create_table=PATTERN_CREATE_TABLE.matcher(s);
		if(create_table.find()) {
		String tableName;
		tableName=create_table.group(1);
		
		String [] column=create_table.group(2).split(",| ");
		
		File table=new File(tableName+".txt");
		
		if(table.exists())
		{
			return false;
		}
		else
		{
			try {
			table.createNewFile();
			}
			catch(Exception e)
			{
				
			}
		}
		
		FileWriter f=null;//�����ļ�д�����
		BufferedWriter f1=null;//�����ַ���д�����
	
		try {
			//������ļ�д�������ַ���д�����ֿ�д��
			f=new FileWriter(tableName+".txt");
			f1=new BufferedWriter(f);
			
			int i=0;
			while(i < column.length) {
				f1.write(column[i]+"       ");
				i++;
				f1.write(column[i]+"       ");
				i++;
				f1.newLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {//���û��catch �쳣���������ջ�ִ�е�����
			try {
				f1.close();
				f.close();//�ر��ļ�
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return true;
		}
		
		Matcher create_user=PATTERN_CREATE_USER.matcher(s);
		if(create_user.find())
		{
			String username=create_user.group(1);
			String password=create_user.group(2);
			return User.Register(username, password);
		}
		
		return (create_user.find()||create_table.find());
	}
}
