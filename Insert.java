package dbms;

import java.io.File;
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
		}
//		insert=PATTERN_INSERT_2.matcher(s);
//		if(insert.find())
//		{
//			
//		}
			
		return insert.find();	
	}
}
