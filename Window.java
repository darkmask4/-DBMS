package dbms;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	     loginButton.setBounds(80, 100, 80, 25);
	     loginButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
//	             弹窗
	            frame.dispose();
				start();
	         }
	     });
	     panel.add(loginButton);
	     
	     JButton registerButton = new JButton("注册");
	     registerButton.setBounds(200, 100, 80, 25);
	     registerButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
//	             弹窗
	            frame.dispose();
				
	         }
	     });
	     panel.add(registerButton);
	     
	 }
	 
	 private static void start()
	 {
		 JFrame frame = new JFrame("天堂DBMS");

	     frame.setBounds (350,90,600,600);
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
		 name.setBounds(150,0,1000,100);
		 name.setFont(new Font("华文行楷",1,50));
		 name.setForeground(Color.red);
		 panel.add(name);
		 
		 JLabel sql=new JLabel("请输入SQL语句：");
		 sql.setBounds(20, 60, 400, 100);
		 panel.add(sql);
		 
		 JTextField sqlText=new JTextField();
		 sqlText.setBounds(120, 100, 400, 25);
		 panel.add(sqlText);
		 
		 JTextArea displayArea=new JTextArea();
		 displayArea.setBounds(50,140,500,400);
		 panel.add(displayArea);
		
		 sqlText.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
						Create.Ifcreate_table(Format.sqlFormat(sqlText.getText()));
						Drop.Ifdrop_table(Format.sqlFormat(sqlText.getText()));
						Alter.Ifalter_table(Format.sqlFormat(sqlText.getText()));
						Insert.Ifinsert(Format.sqlFormat(sqlText.getText()));
						Update.Ifupdate(Format.sqlFormat(sqlText.getText()));
					}
				}
			});
	 }
}
