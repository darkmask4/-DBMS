package DMBS;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert {
	private static final Pattern PATTERN_INSERT_1 = Pattern.compile("insert into (.*) values \\( (.*) \\)");
	private static final Pattern PATTERN_INSERT_2 = Pattern.compile("insert into (.*) \\( (.*) \\) values \\( (.*) \\)");
	public static boolean Ifinsert(String s)
	{
		Matcher insert=PATTERN_INSERT_2.matcher(s);
		if(insert.find())
		{
			String tableName;
			tableName=insert.group(1);
			
			String [] column_1=insert.group(2).split(",");
			String [] column=insert.group(3).split(",");
			
			File table=new File(tableName+".txt");
			
			if(table.exists())
			{
				Table.getTable(tableName);
				int j=0;
				for(int i=0;i<Table.Column.size();i++)
				{
					if(j<column_1.length)
					{
						if(column_1[j].equals(Table.Column.get(i)))//有的赋相应值
						{
							List<Object> value=Table.Table.get(Table.Column.get(i));
							value.add(column[j]);
							j++;
						}
						else//把没有的设成null
						{
							List<Object> value=Table.Table.get(Table.Column.get(i));
							value.add("null");
						}
					}
					else
					{
						List<Object> value=Table.Table.get(Table.Column.get(i));
						value.add("null");
					}
				}
				Table.inTable(tableName);
			}
			else 
			{
				return false;
			}
			return true;
		}
		
		insert=PATTERN_INSERT_1.matcher(s);
		if(insert.find())
		{
			String tableName;
			tableName=insert.group(1);
			
			String [] column=insert.group(2).split(",");
			
			File table=new File(tableName+".txt");
			
			if(table.exists())//修改Table里的内容
			{
				Table.getTable(tableName);
				for(int i=0;i<Table.Column.size();i++) {
					List<Object> value=Table.Table.get(Table.Column.get(i));
					value.add(column[i]);
				}
				Table.inTable(tableName);
			
			}
			else 
			{
				return false;
			}
			return true;
		}
		
			
		return insert.find();	
	}
}
