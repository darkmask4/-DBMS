package dbms;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Delete {
    //以delete开头的sql语句
    private static final Pattern pattern1 = 
    Pattern.compile("delete from ([a-z]+) where (.*)");
    private static final Pattern pattern2 = 
    Pattern.compile("delete from ([a-z]+)");
    
    public static boolean ifdelete(String sql){
        Matcher matcher1 = pattern1.matcher(sql);
        if (matcher1.find()) {
            String tableName = matcher1.group(1);  // 获取表名
            //判断表是否存在
            File table = new File(tableName+".txt");
            if(table.exists()){
                Table.getTable(tableName);
                List<Integer> result = Where.judge(matcher1.group(2));
              for(int i = 0; i < Table.Column.size(); i++){
            	  List<Object> a = Table.Table.get(Table.Column.get(i));
            	  for(int j = result.size() - 1; j >= 0; j--){
            		  a.remove((int) result.get(j));
            	  }
              }
                //把删除后的数据写入文件
                Table.inTable(tableName);
                return true;
            }
            else
            	return false;
        }
    
        matcher1 = pattern2.matcher(sql);
        if(matcher1.find()){
            String tableName1 = matcher1.group(1);//获取表名
            //清空整个表，只留下列名和数据类型
            File table2 = new File(tableName1+".txt");
            if(table2.exists()){
                //清空整个表，只留下列名和数据类型
                Table.getTable(tableName1);
                Table.Table.clear();
                Table.inTable(tableName1);
                return true;
        }
        else
        	return false;
    }
    return matcher1.find();
    }
}
