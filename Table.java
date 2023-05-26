package dbms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Table {
	public static List<String> Type;
	public static List<String> Column;
	public static Map<String,List<Object>> Table;
	
	public static void getTable(String s)
	{
		Type=new ArrayList<>();
		Column=new ArrayList<>();
		Table=new HashMap<>();
		
		try {
			File table=new File(s+".txt");
			FileReader f=new FileReader(table);//文件读取对象
			BufferedReader f1=new BufferedReader(f);//字符流对象
			
			String str=null;
			while((str=f1.readLine())!=null) {
				List<Object> column_1=new ArrayList<>();
				String [] column=str.split("       ");
				Type.add(column[1]);
				Column.add(column[0]);
				if(column[1].equals("int"))
				{
					for(int i=2;i<column.length;i++)
					{
						if(column[i].equals("null"))
							column_1.add(column[i]);
						else
							column_1.add(Integer.parseInt(column[i]));
					}
				}
				
				if(column[1].equals("char"))
				{
					for(int i=2;i<column.length;i++)
					{
						if(column[i].equals("null"))
							column_1.add(column[i]);
						else
							column_1.add(column[i].charAt(0));
					}
				}
				
				if(column[1].contains("char("))
				{
					for(int i=2;i<column.length;i++)
					{
						column_1.add(column[i]);
					}
				}
				
				if(column[1].equals("date"))
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					for(int i=2;i<column.length;i++)
					{
						if(column[i].equals("null"))
							column_1.add(column[i]);
						else
							column_1.add(formatter.parse(column[i]));
					}
				}
				
				Table.put(column[0], column_1);
			}
			f1.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("problem");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void inTable(String tableName)
	{
		FileWriter f=null;//创建文件写入对象
		BufferedWriter f1=null;//创建字符流写入对象
		
		try {
		f=new FileWriter(tableName+".txt");
		f1=new BufferedWriter(f);
		
		for(int i=0;i<Column.size();i++)
		{
			List<Object> a=new ArrayList<>();
			f1.write(Column.get(i)+"       "+Type.get(i)+"       ");
			a=Table.get(Column.get(i));
			for(int j=0;j<a.size();j++)
			{	
				f1.write(String.valueOf(a.get(j))+"       ");	
			}
			f1.newLine();
		}
		}catch(Exception e){
			
		}finally {//如果没有catch 异常，程序最终会执行到这里
			try {
			f1.close();
			f.close();//关闭文件
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	}
}
