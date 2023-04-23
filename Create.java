package dbms;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Create {
	private static final Pattern PATTERN_CREATE_TABLE = Pattern.compile("create table (\\w) (.*)");
	
	public static boolean Ifcreate_table(String s)
	{
		Matcher create_table=PATTERN_CREATE_TABLE.matcher(s);
		if(create_table.find()) {
		System.out.println("БэУћ:"+create_table.group(1));
		System.out.println("Са:"+create_table.group(2));
		}
		return create_table.find();
	}
}
