package dbms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert {
	private static final Pattern PATTERN_INSERT_1 = Pattern.compile("insert into (\\w) values \\( (.*) \\)");
	private static final Pattern PATTERN_INSERT_2 = Pattern.compile("insert into (\\w) \\( (.*) \\) values \\( (.*) \\)");
	public static boolean Ifinsert(String s)
	{
		Matcher insert=PATTERN_INSERT_1.matcher(s);
		if(insert.find())
		{
			String tableName;
			tableName=insert.group(1);
			
			String [] column=insert.group(2).split(",");
			
			File table=new File(tableName+".txt");
			
			if(table.exists())
			{

				FileWriter f=null;//创建文件写入对象
				BufferedWriter f1=null;//创建字符流写入对象
				Table.getTable(tableName);
				try {
					//这里把文件写入对象和字符流写入对象分开写了
					f=new FileWriter(tableName+".txt");
					f1=new BufferedWriter(f);
					
					for(int i=0;i<Table.Column.size();i++) {
						List<Object> value=Table.Table.get(Table.Column.get(i));
						value.add(column[i]);
					}
					for(int i=0;i<Table.Column.size();i++)
					{
						List<Object> a=new ArrayList<>();
						f1.write(Table.Column.get(i)+"       "+Table.Type.get(i)+"       ");
						a=Table.Table.get(Table.Column.get(i));
						for(int j=0;j<a.size();j++)
						{	
							f1.write(String.valueOf(a.get(j))+"       ");	
						}
						f1.newLine();
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {//如果没有catch 异常，程序最终会执行到这里
					try {
						f1.close();
						f.close();//关闭文件
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			
			}
			else 
			{
				return false;
			}
		}
//		insert=PATTERN_INSERT_2.matcher(s);
//		if(insert.find())
//		{
//			
//		}
			
		return insert.find();	
	}
}
