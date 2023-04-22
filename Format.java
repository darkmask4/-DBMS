package dbms;

public class Format {
    public static String sqlFormat(String input){
        String sql = "";
        //去掉字符串前后两端的空格
        sql = input.trim();
        //将字符串都转化为小写
        String string = sql.toLowerCase();
        //通过正则表达式将多个空格转换为一个空格
        String str = string.replaceAll("\\s{2,}", " ");
        //去掉;前的空格
        String s = str.replaceFirst("( ;)$", ";");
        //去掉最后的;
        s=s.replaceAll(";", "");
        //System.out.println(s);
        return s;
    }
}

