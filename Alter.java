package dbms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alter {
	private static final Pattern PATTERN_ALTER_TABLE_1 = Pattern.compile("alter table (.*) add (.*) (.*)");
	private static final Pattern PATTERN_ALTER_TABLE_2 = Pattern.compile("alter table (.*) drop column (.*)");
	private static final Pattern PATTERN_ALTER_TABLE_3 = Pattern.compile("alter table (.*) alter column (.*) (.*)");
	
	public static boolean Ifalter_table(String s)
	{
		Matcher alter_table=PATTERN_ALTER_TABLE_1.matcher(s);
		if(alter_table.find())
		{
			String tableName;
			tableName=alter_table.group(1);
			File table=new File(tableName+".txt");
			
			String column_name=alter_table.group(2);
			String datatype=alter_table.group(3);
			
			if(table.exists())
			{
				Table.getTable(tableName);
				
				Table.Column.add(column_name);
				Table.Type.add(datatype);
				
				List<Object> a=new ArrayList<>();
				for(int i=0;i<Table.Table.get(Table.Column.get(0)).size();i++)
				{
					a.add("null");
				}
				Table.Table.put(column_name, a);
				Table.inTable(tableName);
			}
			else
			{
				return false;
			}
		}
		alter_table=PATTERN_ALTER_TABLE_2.matcher(s);
		if(alter_table.find())
		{
			String tableName;
			tableName=alter_table.group(1);
			File table=new File(tableName+".txt");
			
			String column_name=alter_table.group(2);
			
			if(table.exists())
			{
				Table.getTable(tableName);
				Table.Table.remove(column_name);
				int i=0;
				while(true)
				{
					if(Table.Column.get(i).equals(column_name))
					{
						Table.Column.remove(i);
						break;
					}
					i++;
				}
				
				Table.Type.remove(i);
				Table.inTable(tableName);
			}
			else
			{
				return false;
			}
		}
		
		alter_table=PATTERN_ALTER_TABLE_3.matcher(s);
		if(alter_table.find())
		{
			String tableName;
			tableName=alter_table.group(1);
			File table=new File(tableName+".txt");
			
			String column_name=alter_table.group(2);
			String datatype=alter_table.group(3);
			
			if(table.exists())
			{
				Table.getTable(tableName);
				int i=0;
				while(true)
				{
					if(Table.Column.get(i).equals(column_name))
					{
						Table.Type.set(i, datatype);
						break;
					}
					i++;
				}
				Table.inTable(tableName);
			}
		}
		
		return alter_table.find();
	}
}
