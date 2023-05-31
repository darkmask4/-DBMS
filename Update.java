package DMBS;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update {
	private static final Pattern PATTERN_UPDATE_1 = Pattern.compile("update (.*) set (.*)");
	private static final Pattern PATTERN_UPDATE_2 = Pattern.compile("update (.*) set (.*) where (.*)");
	
	public static boolean Ifupdate(String s)
	{
		Matcher update=PATTERN_UPDATE_2.matcher(s);
		
		if(update.find())
		{
			String tableName=update.group(1);
			String []column=update.group(2).split(",|=");
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				List<Integer> result=Where.judge(update.group(3));
				
				int i=0;
				while(i<column.length)
				{
					List<Object> a=Table.Table.get(column[i]);
					
					for(int j=0;j<a.size();j++)
					{
						if(result.contains(j))
							a.set(j,column[i+1]);
					}
					
					i=i+2;
				}
				
				Table.inTable(tableName);
			}
			else 
				return false;
			
			return true;
		}
		
		update=PATTERN_UPDATE_1.matcher(s);
		if(update.find())
		{
			String tableName=update.group(1);
			String []column=update.group(2).split(",|=");
			
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				int i=0;
				while(i<column.length)
				{
					List<Object> a=Table.Table.get(column[i]);
					
					for(int j=0;j<a.size();j++)
					{
						a.set(j,column[i+1]);
					}
					
					i=i+2;
				}
				Table.inTable(tableName);
			}
			else
				return false;
			
			return true;
		}
		
		return update.find();
	}
}
