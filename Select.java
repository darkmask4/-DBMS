package DMBS;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Select {
	private static final Pattern PATTERN_SELECT_1 = Pattern.compile("select (.*) from (.*)");
	private static final Pattern PATTERN_SELECT_2 = Pattern.compile("select (.*) from (.*) where (.*)");
	private static final Pattern PATTERN_SELECT_3 = Pattern.compile("select (.*) from (.*) where (.*) order by (.*)");
	private static final Pattern PATTERN_SELECT_4 = Pattern.compile("select (.*) from (.*) order by (.*)");
	
	public static boolean Ifselect(String s)
	{
		Matcher select=PATTERN_SELECT_3.matcher(s);
		if(select.find())
		{
			String[] column=select.group(1).split(",");
			String tableName=select.group(2);
			String condition=select.group(3);
			String[] order=select.group(4).split(" ");
			
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				//where条件判断
				List<Integer> result=new ArrayList<>();
				for(int i=0;i<Table.Table.get(Table.Column.get(0)).size();i++)
				{
					result.add(i);
				}
				result.removeAll(Where.judge(condition));
				
				for(int i=0;i<Table.Column.size();i++)
				{
					List<Object> a=Table.Table.get(Table.Column.get(i));
					for(int j=result.size()-1;j>=0;j--)
					{
						a.remove((int)result.get(j));
					}
				}
				//排序
				
					List<Integer> sort=new ArrayList<>();
					List<Object> c=new ArrayList<>();
					List<Object> b=new ArrayList<>();
					List<Object> a=Table.Table.get(order[0]);
					for(int i=0;i<a.size();i++)
					{
						c.add(a.get(i));
						b.add(a.get(i));
					}
					if(order.length==1)
						c.sort(null);
					else
					{
						if(order[1].equals("asc"))
							c.sort(null);
						if(order[1].equals("desc"))
						{
							c.sort(null);
							Collections.reverse(c);
						}
					}
					for(int i=0;i<b.size();i++)
					{
						for(int j=0;j<c.size();j++)
						{
							if(c.get(j).equals(b.get(i)))
							{
								c.set(j, "Ricardo");
								sort.add(j);
								break;
							}
						}
					}
					
					for(int i=0;i<Table.Column.size();i++)
					{
						List<Object> con=Table.Table.get(Table.Column.get(i));
						List<Object> con1=new ArrayList<>();
						for(int j=0;j<con.size();j++)
						{
							con1.add(con.get(j));
						}
						for(int j=0;j<con.size();j++)
						{
							con.set(j, con1.get((int)sort.get(j)));
						}
					}
					
				//选择列
				if(column[0].equals("*"))
				{
					return true;
				}
				else
				{
					List<String> a1=new ArrayList<>();
					List<String> b1=new ArrayList<>();
					for(int i=0;i<column.length;i++)
					{
						a1.add(column[i]);
					}
					for(int i=0;i<Table.Column.size();i++)
					{
						b1.add(Table.Column.get(i));
					}
					b1.removeAll(a1);
					Table.Column.removeAll(b1);
				}
			}
			else
				return false;
			return true;
		}
		
		select=PATTERN_SELECT_2.matcher(s);
		
		if(select.find())
		{
			String[] column=select.group(1).split(",");
			String tableName=select.group(2);
			String condition=select.group(3);
			
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				//where条件判断
				List<Integer> result=new ArrayList<>();
				for(int i=0;i<Table.Table.get(Table.Column.get(0)).size();i++)
				{
					result.add(i);
				}
				result.removeAll(Where.judge(condition));
				
				for(int i=0;i<Table.Column.size();i++)
				{
					List<Object> a=Table.Table.get(Table.Column.get(i));
					for(int j=result.size()-1;j>=0;j--)
					{
						a.remove((int)result.get(j));
					}
				}
				
				//选择列
				if(column[0].equals("*"))
				{
					return true;
				}
				else
				{
					List<String> a1=new ArrayList<>();
					List<String> b1=new ArrayList<>();
					for(int i=0;i<column.length;i++)
					{
						a1.add(column[i]);
					}
					for(int i=0;i<Table.Column.size();i++)
					{
						b1.add(Table.Column.get(i));
					}
					b1.removeAll(a1);
					Table.Column.removeAll(b1);
				}
			}
			else
				return false;
			return true;
		}
		
		select=PATTERN_SELECT_4.matcher(s);
		if(select.find())
		{
			String[] column=select.group(1).split(",");
			String tableName=select.group(2);
			String[] order=select.group(3).split(" ");
			
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				//排序
				
				List<Integer> sort=new ArrayList<>();
				List<Object> c=new ArrayList<>();
				List<Object> b=new ArrayList<>();
				List<Object> a=Table.Table.get(order[0]);
				for(int i=0;i<a.size();i++)
				{
					c.add(a.get(i));
					b.add(a.get(i));
				}
				
				if(order.length==1)
					c.sort(null);
				else
				{
					if(order[1].equals("asc"))
						c.sort(null);
					if(order[1].equals("desc"))
					{
						c.sort(null);
						Collections.reverse(c);
					}
				}
				
				for(int i=0;i<b.size();i++)
				{
					for(int j=0;j<c.size();j++)
					{
						if(c.get(j).equals(b.get(i)))
						{
							c.set(j, "Ricardo");
							sort.add(j);
							break;
						}
					}
				}
				
				for(int i=0;i<Table.Column.size();i++)
				{
					List<Object> con=Table.Table.get(Table.Column.get(i));
					List<Object> con1=new ArrayList<>();
					for(int j=0;j<con.size();j++)
					{
						con1.add(con.get(j));
					}
					for(int j=0;j<con.size();j++)
					{
						con.set(j, con1.get((int)sort.get(j)));
					}
				}
				
			//选择列
			if(column[0].equals("*"))
			{
				return true;
			}
			else
			{
				List<String> a1=new ArrayList<>();
				List<String> b1=new ArrayList<>();
				for(int i=0;i<column.length;i++)
				{
					a1.add(column[i]);
				}
				for(int i=0;i<Table.Column.size();i++)
				{
					b1.add(Table.Column.get(i));
				}
				b1.removeAll(a1);
				Table.Column.removeAll(b1);
			}
			}
			else
				return false;
			return true;
		}
		
		select=PATTERN_SELECT_1.matcher(s);
		if(select.find())
		{
			String[] column=select.group(1).split(",");
			String tableName=select.group(2);
			File table=new File(tableName+".txt");
			if(table.exists())
			{
				Table.getTable(tableName);
				if(column[0].equals("*"))
				{
					return true;
				}
				else
				{
					List<String> a1=new ArrayList<>();
					List<String> b1=new ArrayList<>();
					for(int i=0;i<column.length;i++)
					{
						a1.add(column[i]);
					}
					for(int i=0;i<Table.Column.size();i++)
					{
						b1.add(Table.Column.get(i));
					}
					b1.removeAll(a1);
					Table.Column.removeAll(b1);
				}
			}
			else
				return false;
			return true;
		}
		
		return select.find(); 
	}
}