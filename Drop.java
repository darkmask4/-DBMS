package dbms;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drop {
	private static final Pattern PATTERN_DROP_TABLE = Pattern.compile("drop table (\\w)");
	public static boolean Ifdrop_table(String s)
	{
		Matcher drop_table=PATTERN_DROP_TABLE.matcher(s);
		if(drop_table.find())
		{
			String tableName;
			tableName=drop_table.group(1);
			File table=new File(tableName+".txt");
			
			if(table.exists())
			{
				table.delete();
			}
		}
		return drop_table.find();
	}
}
