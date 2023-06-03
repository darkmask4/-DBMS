package DMBS;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Join {
	private static final Pattern PATTERN_JOIN_1 = Pattern.compile("select (.*) from (.*) natural join (.*)");
	private static final Pattern PATTERN_JOIN_2 = Pattern.compile("select (.*) from (.*) natural join (.*) where (.*)");
	private static final Pattern PATTERN_JOIN_3 = Pattern.compile("select (.*) from (.*) natural join (.*) where (.*) order by (.*)");
	private static final Pattern PATTERN_JOIN_4 = Pattern.compile("select (.*) from (.*) natural join (.*) order by (.*)");
	
	public static boolean Ifjoin(String s)
	{
		Matcher join=PATTERN_JOIN_3.matcher(s);
		if(join.find())
		{
			String[] column=join.group(1).split(",");
			String tableName1=join.group(2);
			String tableName2=join.group(3);
			String condition=join.group(4);
			String[] order=join.group(5).split(" ");
			
			File table1=new File(tableName1+".txt");
			File table2=new File(tableName2+".txt");
			if(table1.exists()&&table2.exists())
			{
				String common=new String();
				List<String> column1=new ArrayList<>();
				Map<String,List<Object>> data1=new HashMap<>();
				List<String> column2=new ArrayList<>();
				Map<String,List<Object>> data2=new HashMap<>();
				
				Table.getTable(tableName2);
				data1.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column1.add(Table.Column.get(i));
				}
				
				Table.getTable(tableName1);
				data2.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column2.add(Table.Column.get(i));
				}
				
				for(int i=0;i<column1.size();i++)
				{
					if(!Table.Column.contains(column1.get(i)))
						Table.Column.add(column1.get(i));
				}
				
				Table.Table=new HashMap<>();
				
				for(int i=0;i<column1.size();i++)
				{
					for(int j=0;j<column2.size();j++)
					{
						if(column1.get(i).equals(column2.get(j)))
						{
							common=column1.get(i);
							i=column1.size();
							j=column2.size();
						}
					}
				}
				List<Object> com1=data1.get(common);
				List<Object> com2=data2.get(common);
				List<Integer> seq1=new ArrayList<>();
				List<Integer> seq2=new ArrayList<>();
				for(int i=0;i<com1.size();i++)
				{
					for(int j=0;j<com2.size();j++)
					{
						if(com1.get(i).equals(com2.get(j)))
						{
							seq1.add(i);
							seq2.add(j);
						}
					}
				}
				
				for(int i=0;i<Table.Column.size();i++)
				{
					for(int j=0;j<column1.size();j++)
					{
						if(column1.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq1.size();k++)
							{
								newcolumn.add(data1.get(column1.get(j)).get((int)seq1.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}
					for(int j=0;j<column2.size();j++)
					{
						if(column2.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq2.size();k++)
							{
								newcolumn.add(data2.get(column2.get(j)).get((int)seq2.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}	
				}
				
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
							con.set((int)sort.get(j), con1.get(j));
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
		join=PATTERN_JOIN_2.matcher(s);
		if(join.find())
		{
			String[] column=join.group(1).split(",");
			String tableName1=join.group(2);
			String tableName2=join.group(3);
			String condition=join.group(4);
			
			File table1=new File(tableName1+".txt");
			File table2=new File(tableName2+".txt");
			if(table1.exists()&&table2.exists())
			{
				String common=new String();
				List<String> column1=new ArrayList<>();
				Map<String,List<Object>> data1=new HashMap<>();
				List<String> column2=new ArrayList<>();
				Map<String,List<Object>> data2=new HashMap<>();
				
				Table.getTable(tableName2);
				data1.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column1.add(Table.Column.get(i));
				}
				
				Table.getTable(tableName1);
				data2.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column2.add(Table.Column.get(i));
				}
				
				for(int i=0;i<column1.size();i++)
				{
					if(!Table.Column.contains(column1.get(i)))
						Table.Column.add(column1.get(i));
				}
				
				Table.Table=new HashMap<>();
				
				for(int i=0;i<column1.size();i++)
				{
					for(int j=0;j<column2.size();j++)
					{
						if(column1.get(i).equals(column2.get(j)))
						{
							common=column1.get(i);
							i=column1.size();
							j=column2.size();
						}
					}
				}
				List<Object> com1=data1.get(common);
				List<Object> com2=data2.get(common);
				List<Integer> seq1=new ArrayList<>();
				List<Integer> seq2=new ArrayList<>();
				for(int i=0;i<com1.size();i++)
				{
					for(int j=0;j<com2.size();j++)
					{
						if(com1.get(i).equals(com2.get(j)))
						{
							seq1.add(i);
							seq2.add(j);
						}
					}
				}
				
				for(int i=0;i<Table.Column.size();i++)
				{
					for(int j=0;j<column1.size();j++)
					{
						if(column1.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq1.size();k++)
							{
								newcolumn.add(data1.get(column1.get(j)).get((int)seq1.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}
					for(int j=0;j<column2.size();j++)
					{
						if(column2.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq2.size();k++)
							{
								newcolumn.add(data2.get(column2.get(j)).get((int)seq2.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}	
				}
				
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
		join=PATTERN_JOIN_4.matcher(s);
		if(join.find())
		{
			String[] column=join.group(1).split(",");
			String tableName1=join.group(2);
			String tableName2=join.group(3);
			String[] order=join.group(4).split(" ");
			
			File table1=new File(tableName1+".txt");
			File table2=new File(tableName2+".txt");
			if(table1.exists()&&table2.exists())
			{
				String common=new String();
				List<String> column1=new ArrayList<>();
				Map<String,List<Object>> data1=new HashMap<>();
				List<String> column2=new ArrayList<>();
				Map<String,List<Object>> data2=new HashMap<>();
				
				Table.getTable(tableName2);
				data1.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column1.add(Table.Column.get(i));
				}
				
				Table.getTable(tableName1);
				data2.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column2.add(Table.Column.get(i));
				}
				
				for(int i=0;i<column1.size();i++)
				{
					if(!Table.Column.contains(column1.get(i)))
						Table.Column.add(column1.get(i));
				}
				
				Table.Table=new HashMap<>();
				
				for(int i=0;i<column1.size();i++)
				{
					for(int j=0;j<column2.size();j++)
					{
						if(column1.get(i).equals(column2.get(j)))
						{
							common=column1.get(i);
							i=column1.size();
							j=column2.size();
						}
					}
				}
				List<Object> com1=data1.get(common);
				List<Object> com2=data2.get(common);
				List<Integer> seq1=new ArrayList<>();
				List<Integer> seq2=new ArrayList<>();
				for(int i=0;i<com1.size();i++)
				{
					for(int j=0;j<com2.size();j++)
					{
						if(com1.get(i).equals(com2.get(j)))
						{
							seq1.add(i);
							seq2.add(j);
						}
					}
				}
				
				for(int i=0;i<Table.Column.size();i++)
				{
					for(int j=0;j<column1.size();j++)
					{
						if(column1.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq1.size();k++)
							{
								newcolumn.add(data1.get(column1.get(j)).get((int)seq1.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}
					for(int j=0;j<column2.size();j++)
					{
						if(column2.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq2.size();k++)
							{
								newcolumn.add(data2.get(column2.get(j)).get((int)seq2.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
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
						con.set((int)sort.get(j), con1.get(j));
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
		join=PATTERN_JOIN_1.matcher(s);
		if(join.find())
		{
			String[] column=join.group(1).split(",");
			String tableName1=join.group(2);
			String tableName2=join.group(3);
			File table1=new File(tableName1+".txt");
			File table2=new File(tableName2+".txt");
			
			if(table1.exists()&&table2.exists())
			{
				String common=new String();
				List<String> column1=new ArrayList<>();
				Map<String,List<Object>> data1=new HashMap<>();
				List<String> column2=new ArrayList<>();
				Map<String,List<Object>> data2=new HashMap<>();
				
				Table.getTable(tableName2);
				data1.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column1.add(Table.Column.get(i));
				}
				
				Table.getTable(tableName1);
				data2.putAll(Table.Table);
				for(int i=0;i<Table.Column.size();i++)
				{
					column2.add(Table.Column.get(i));
				}
				
				for(int i=0;i<column1.size();i++)
				{
					if(!Table.Column.contains(column1.get(i)))
						Table.Column.add(column1.get(i));
				}
				
				Table.Table=new HashMap<>();
				
				for(int i=0;i<column1.size();i++)
				{
					for(int j=0;j<column2.size();j++)
					{
						if(column1.get(i).equals(column2.get(j)))
						{
							common=column1.get(i);
							i=column1.size();
							j=column2.size();
						}
					}
				}
				List<Object> com1=data1.get(common);
				List<Object> com2=data2.get(common);
				List<Integer> seq1=new ArrayList<>();
				List<Integer> seq2=new ArrayList<>();
				for(int i=0;i<com1.size();i++)
				{
					for(int j=0;j<com2.size();j++)
					{
						if(com1.get(i).equals(com2.get(j)))
						{
							seq1.add(i);
							seq2.add(j);
						}
					}
				}
				
				for(int i=0;i<Table.Column.size();i++)
				{
					for(int j=0;j<column1.size();j++)
					{
						if(column1.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq1.size();k++)
							{
								newcolumn.add(data1.get(column1.get(j)).get((int)seq1.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
					}
					for(int j=0;j<column2.size();j++)
					{
						if(column2.get(j).equals(Table.Column.get(i)))
						{
							List<Object> newcolumn=new ArrayList<>();
							for(int k=0;k<seq2.size();k++)
							{
								newcolumn.add(data2.get(column2.get(j)).get((int)seq2.get(k)));
							}
							Table.Table.put(Table.Column.get(i),newcolumn);
						}
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
		
		return join.find();
	}
}