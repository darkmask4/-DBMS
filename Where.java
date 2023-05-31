package DMBS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Where {
	public static List<Integer> judge(String s)
	{
		if(s.contains(" not in "))
		{
			s=s.replace(" not in ", "!=");
		}
		
		String [] condition=s.split(" or ");
		List<Integer> b=new ArrayList<>();
		for(int i=0;i<condition.length;i++)
		{
			String[]condition_1=condition[i].split(" and ");
			List<Integer> a=new ArrayList<>();
			
			for(int j=0;j<condition_1.length;j++)
			{	
				if(condition_1[j].contains("!="))
				{
					String[] comp;
					if(condition_1[j].contains("("))
						comp=condition_1[j].split("!=\\(|\\)|,");
					else
						comp=condition_1[j].split("!=");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						int e=1;
						int x=1;
						while(e<comp.length)
						{
							if(!c.get(w).toString().equals(comp[e]))
							{
								x++;
							}
							e++;
						}
						if(e==x)
							a.add(w);
					}
					continue;
				}
				if(condition_1[j].contains("<="))
				{
					String[]comp=condition_1[j].split("<=");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						if(Integer.parseInt(c.get(w).toString())<=Integer.parseInt(comp[1]))
						{
							a.add(w);
						}
					}
					continue;
				}
				if(condition_1[j].contains(">="))
				{
					String[]comp=condition_1[j].split(">=");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						if(Integer.parseInt(c.get(w).toString())>=Integer.parseInt(comp[1]))
						{
							a.add(w);
						}
					}
					continue;
				}
				if(condition_1[j].contains("<"))
				{
					String[]comp=condition_1[j].split("<");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						if(Integer.parseInt(c.get(w).toString())<Integer.parseInt(comp[1]))
						{
							a.add(w);
						}
					}
					continue;
				}
				if(condition_1[j].contains(">"))
				{
					String[]comp=condition_1[j].split(">");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						if(Integer.parseInt(c.get(w).toString())>Integer.parseInt(comp[1]))
						{
							a.add(w);
						}
					}
					continue;
				}
				if(condition_1[j].contains("="))
				{
					String[]comp=condition_1[j].split("=");
					List<Object> c=Table.Table.get(comp[0]);
					
					for(int w=0;w<c.size();w++)
					{
						if(c.get(w).toString().equals(comp[1]))
						{
							a.add(w);
						}
					}
					continue;
				}
			}
			
			for(int k=0;k<Table.Column.size();k++)
			{
				if(Collections.frequency(a,k)==condition_1.length)
				{
					b.add(k);
				}
			}
		}
		
		List<Integer> result = new ArrayList<>();
		for (int cd:b)
		{
			if(!result.contains(cd))
			{ //主动判断是否包含重复元素 
				result.add(cd);
			}
		}
		result.sort(null);
		return result;
	}
}
