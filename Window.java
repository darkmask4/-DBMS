package dbms;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Window {

	public void init()
	{
		 // ���� JFrame ʵ��
		 JFrame frame = new JFrame("��¼����");
		 // Setting the width and height of frame
		 frame.setBounds (450,250,350,200);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
        
		 JPanel panel = new JPanel();    
		 // ������
		 frame.add(panel);
		 
		 placeComponents(panel,frame);

		 // ���ý���ɼ�
		 frame.setVisible(true);
	}
	
	 private static void placeComponents(JPanel panel,JFrame frame) 
	 {       
		 panel.setLayout(null);

	     // ���� JLabel
	     JLabel userLabel = new JLabel("�û���:");
	        
	     userLabel.setBounds(10,20,80,25);
	     panel.add(userLabel);

	     JTextField userText = new JTextField(20);
	     userText.setBounds(100,20,165,25);
	     panel.add(userText);

	     // ����������ı���
	     JLabel passwordLabel = new JLabel("����:");
	     passwordLabel.setBounds(10,50,80,25);
	     panel.add(passwordLabel);
	        
	     JPasswordField passwordText = new JPasswordField(20);
	     passwordText.setBounds(100,50,165,25);
	     panel.add(passwordText);
	        	        
	     JButton loginButton = new JButton("��¼");
	     loginButton.setBounds(120, 100, 80, 25);
	     loginButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
//	             ����
	            if(User.login(userText.getText(), new String(passwordText.getPassword())))
	            {
	            	frame.dispose();
	            	start();
	            }
	            else
	            {
	            	start1();
	            }
	            	
	         }
	     });
	     panel.add(loginButton);
	     
	     
	 }
	 
	 private static void start1()
	 {
		 JFrame frame = new JFrame("����");
		 frame.setBounds (490,300,300,100);
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 frame.setResizable(false);
		 
		 JLabel error = new JLabel("�û������������");
	        
	     error.setBounds(10,20,80,25);
	     frame.add(error);
	     frame.setVisible(true);
	 }
	 
	 private static void start()
	 {
		 JFrame frame = new JFrame("����DBMS");

	     frame.setBounds (50,0,1200,740);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setResizable(false);
	        
	     JPanel panel = new JPanel();    
	     // ������
	     frame.add(panel);
	     placeComponents_2(panel,frame);
	     frame.setVisible(true);
	 }
	 
	 private static void placeComponents_2(JPanel panel,JFrame frame)
	 {
		 panel.setLayout(null);
		 panel.setBackground(Color.GREEN);
		 
		 JLabel name=new JLabel("����DBMS");
		 name.setBounds(450,0,1000,100);
		 name.setFont(new Font("�����п�",1,50));
		 name.setForeground(Color.red);
		 panel.add(name);
		 
		 JLabel sql=new JLabel("������SQL��䣺");
		 sql.setBounds(20, 60, 500, 100);
		 sql.setFont(new Font("����",1,30));
		 panel.add(sql);
		 
		 JTextField sqlText=new JTextField();
		 sqlText.setBounds(245, 95, 900, 35);
		 sqlText.setFont(new Font("����",1,25));
		 panel.add(sqlText);
		 
		 JTable table=new JTable();
		 DefaultTableModel tModel = new DefaultTableModel();
		 table = new JTable(tModel);
		 
		 table.setForeground(Color.BLACK);                   // ������ɫ
	     table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
	     table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
	     table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
	     table.setGridColor(Color.GRAY);                     // ������ɫ
	 
	     // ���ñ�ͷ
	     table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
	     table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
	     table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
	     table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������

	     // �����и�
	     table.setRowHeight(30);
	 
	     JScrollPane jScrollPane=  new JScrollPane(table);
	     jScrollPane.setBounds(50,140,1100,500);
	     panel.add(jScrollPane);
		
	     JTextField remindText=new JTextField();
	     remindText.setBounds(100, 650, 1000, 35);
	     remindText.setFont(new Font("����",1,25));
		 panel.add(remindText);
	     
		 sqlText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
						remindText.setText("");
						if(Grant.If_grantRight(Format.sqlFormat(sqlText.getText())))
							remindText.setText("����Ȩ�޳ɹ�");
						if(Revoke.If_revokeRight(Format.sqlFormat(sqlText.getText())))
							remindText.setText("����Ȩ�޳ɹ�");
						if(User.access.charAt(4)=='1'||User.access.charAt(4)=='2')
							if(Create.Ifcreate_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("�����ɹ�");
						if(User.access.charAt(5)=='1'||User.access.charAt(5)=='2')
							if(Drop.Ifdrop_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("ɾ���ɹ�");
						if(User.access.charAt(6)=='1'||User.access.charAt(6)=='2')	
							if(Alter.Ifalter_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("�޸ĳɹ�");
						if(User.access.charAt(1)=='1'||User.access.charAt(1)=='2')	
							if(Insert.Ifinsert(Format.sqlFormat(sqlText.getText())))
								remindText.setText("����ɹ�");
						if(User.access.charAt(3)=='1'||User.access.charAt(3)=='2')	
							if(Delete.ifdelete(Format.sqlFormat(sqlText.getText())))
								remindText.setText("ɾ���ɹ�");
						if(User.access.charAt(2)=='1'||User.access.charAt(2)=='2')	
							if(Update.Ifupdate(Format.sqlFormat(sqlText.getText())))
								remindText.setText("���³ɹ�");
						if(User.access.charAt(0)=='1'||User.access.charAt(0)=='2')	
						if(Select.Ifselect(Format.sqlFormat(sqlText.getText())))
						{
							int rowCount = Table.Table.get(Table.Column.get(0)).size();
							Object[][] container=new Object[rowCount][];
							String[] columnName = Table.Column.toArray(new String[Table.Column.size()]);
							
							for(int i=0;i<rowCount;i++)
							{
								List<Object> a=new ArrayList<>();
								for(int j=0;j<Table.Column.size();j++)
								{
									a.add(Table.Table.get(Table.Column.get(j)).get(i));
								}
								Object[] b=a.toArray(new Object[a.size()]);
								container[i]=b;
							}
							tModel.setDataVector(container, columnName);
							remindText.setText("��ѯ�ɹ�");
						}
						if(remindText.getText().equals(""))
							remindText.setText("����ʧ��");
					}
				}
			});
	 }
}
