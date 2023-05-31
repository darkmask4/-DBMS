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
		 // 创建 JFrame 实例
		 JFrame frame = new JFrame("登录界面");
		 // Setting the width and height of frame
		 frame.setBounds (450,250,350,200);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
        
		 JPanel panel = new JPanel();    
		 // 添加面板
		 frame.add(panel);
		 
		 placeComponents(panel,frame);

		 // 设置界面可见
		 frame.setVisible(true);
	}
	
	 private static void placeComponents(JPanel panel,JFrame frame) 
	 {       
		 panel.setLayout(null);

	     // 创建 JLabel
	     JLabel userLabel = new JLabel("用户名:");
	        
	     userLabel.setBounds(10,20,80,25);
	     panel.add(userLabel);

	     JTextField userText = new JTextField(20);
	     userText.setBounds(100,20,165,25);
	     panel.add(userText);

	     // 输入密码的文本域
	     JLabel passwordLabel = new JLabel("密码:");
	     passwordLabel.setBounds(10,50,80,25);
	     panel.add(passwordLabel);
	        
	     JPasswordField passwordText = new JPasswordField(20);
	     passwordText.setBounds(100,50,165,25);
	     panel.add(passwordText);
	        	        
	     JButton loginButton = new JButton("登录");
	     loginButton.setBounds(120, 100, 80, 25);
	     loginButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
//	             弹窗
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
		 JFrame frame = new JFrame("错误");
		 frame.setBounds (490,300,300,100);
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 frame.setResizable(false);
		 
		 JLabel error = new JLabel("用户名或密码错误。");
	        
	     error.setBounds(10,20,80,25);
	     frame.add(error);
	     frame.setVisible(true);
	 }
	 
	 private static void start()
	 {
		 JFrame frame = new JFrame("天堂DBMS");

	     frame.setBounds (50,0,1200,740);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setResizable(false);
	        
	     JPanel panel = new JPanel();    
	     // 添加面板
	     frame.add(panel);
	     placeComponents_2(panel,frame);
	     frame.setVisible(true);
	 }
	 
	 private static void placeComponents_2(JPanel panel,JFrame frame)
	 {
		 panel.setLayout(null);
		 panel.setBackground(Color.GREEN);
		 
		 JLabel name=new JLabel("天堂DBMS");
		 name.setBounds(450,0,1000,100);
		 name.setFont(new Font("华文行楷",1,50));
		 name.setForeground(Color.red);
		 panel.add(name);
		 
		 JLabel sql=new JLabel("请输入SQL语句：");
		 sql.setBounds(20, 60, 500, 100);
		 sql.setFont(new Font("黑体",1,30));
		 panel.add(sql);
		 
		 JTextField sqlText=new JTextField();
		 sqlText.setBounds(245, 95, 900, 35);
		 sqlText.setFont(new Font("黑体",1,25));
		 panel.add(sqlText);
		 
		 JTable table=new JTable();
		 DefaultTableModel tModel = new DefaultTableModel();
		 table = new JTable(tModel);
		 
		 table.setForeground(Color.BLACK);                   // 字体颜色
	     table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
	     table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
	     table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
	     table.setGridColor(Color.GRAY);                     // 网格颜色
	 
	     // 设置表头
	     table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
	     table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
	     table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
	     table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

	     // 设置行高
	     table.setRowHeight(30);
	 
	     JScrollPane jScrollPane=  new JScrollPane(table);
	     jScrollPane.setBounds(50,140,1100,500);
	     panel.add(jScrollPane);
		
	     JTextField remindText=new JTextField();
	     remindText.setBounds(100, 650, 1000, 35);
	     remindText.setFont(new Font("黑体",1,25));
		 panel.add(remindText);
	     
		 sqlText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
						remindText.setText("");
						if(Grant.If_grantRight(Format.sqlFormat(sqlText.getText())))
							remindText.setText("赋予权限成功");
						if(Revoke.If_revokeRight(Format.sqlFormat(sqlText.getText())))
							remindText.setText("撤销权限成功");
						if(User.access.charAt(4)=='1'||User.access.charAt(4)=='2')
							if(Create.Ifcreate_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("创建成功");
						if(User.access.charAt(5)=='1'||User.access.charAt(5)=='2')
							if(Drop.Ifdrop_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("删除成功");
						if(User.access.charAt(6)=='1'||User.access.charAt(6)=='2')	
							if(Alter.Ifalter_table(Format.sqlFormat(sqlText.getText())))
								remindText.setText("修改成功");
						if(User.access.charAt(1)=='1'||User.access.charAt(1)=='2')	
							if(Insert.Ifinsert(Format.sqlFormat(sqlText.getText())))
								remindText.setText("插入成功");
						if(User.access.charAt(3)=='1'||User.access.charAt(3)=='2')	
							if(Delete.ifdelete(Format.sqlFormat(sqlText.getText())))
								remindText.setText("删除成功");
						if(User.access.charAt(2)=='1'||User.access.charAt(2)=='2')	
							if(Update.Ifupdate(Format.sqlFormat(sqlText.getText())))
								remindText.setText("更新成功");
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
							remindText.setText("查询成功");
						}
						if(remindText.getText().equals(""))
							remindText.setText("操作失败");
					}
				}
			});
	 }
}
