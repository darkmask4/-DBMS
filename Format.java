package dbms;

public class Format {
    public static String sqlFormat(String input){
        String sql = "";
        //ȥ���ַ���ǰ�����˵Ŀո�
        sql = input.trim();
        //���ַ�����ת��ΪСд
        String string = sql.toLowerCase();
        //ͨ��������ʽ������ո�ת��Ϊһ���ո�
        String str = string.replaceAll("\\s{2,}", " ");
        //ȥ��;ǰ�Ŀո�
        String s = str.replaceFirst("( ;)$", ";");
        //ȥ������;
        s=s.replaceAll(";", "");
        //System.out.println(s);
        return s;
    }
}

