package homework;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.TableUI;

public class ChoiceMain extends JFrame implements ItemListener{
	Choice chooser;
	JTable table,table2;
	JScrollPane scroll;
	Conn conn;
	Conn2 conn2;
	UIManager uiManager;
	
	
	
	public ChoiceMain() {
		
		chooser = new Choice();
		chooser.add("사원");
		chooser.add("부서");
		
		conn = new Conn();
		conn2 = new Conn2();
		table = new JTable(conn2);
		scroll = new JScrollPane(table);
		
		chooser.addItemListener(this);
		uiManager = new UIManager();
		
		uiManager.getClass();
		try {
			uiManager.setLookAndFeel("Conn2");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setLayout(new BorderLayout());
		add(chooser, BorderLayout.WEST);
		add(scroll, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new ChoiceMain();
	}


	
	public void itemStateChanged(ItemEvent e) {
		System.out.println("나 눌러어?");
		System.out.println(e.getItem());
		
		table.updateUI();
		
		try {
			UIManager.setLookAndFeel(new LookAndFeel() {
				
				@Override
				public boolean isSupportedLookAndFeel() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isNativeLookAndFeel() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getID() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return null;
				}
			});
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		table.setUI(new TableUI() {
			
			public void installUI(JComponent c) {			
				table.setUI(this);
				c.updateUI();
			}
		});
		
	}
}
