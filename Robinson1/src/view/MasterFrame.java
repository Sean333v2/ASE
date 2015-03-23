package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.MainController;

/*
 * MasterFrame : a little MDI skeleton that has communication from child to JInternalFrame 
 * 					and from child to the top-level JFrame (MasterFrame)  
 */
public class MasterFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private int newFrameX = 0, newFrameY = 0; //used to cascade or stagger starting x,y of JInternalFrames
	
	public MasterFrame(String title) {
		super(title);
		
		//create menu for adding inner frames
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.dispatchEvent(new WindowEvent(MasterFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Windows");
		menuItem = new JMenuItem("Parts Window");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.startMainFrame();
				openMDIChild(MainController.getListPartsFrame().getFrame());
			}
		});
		JMenuItem menuItem2 = new JMenuItem("Products Window");
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.startProductFrame();
				openMDIChild(MainController.productFrame.getFrame());
			}
		});
		menu.add(menuItem);
		menu.add(menuItem2);
		menuBar.add(menu);

		setJMenuBar(menuBar);
		   
		//create the MDI desktop
		desktop = new JDesktopPane();
		add(desktop);
	}
	
	//create the child panel, insert it into a JInternalFrame and show it
	public void openMDIChild(JPanel child) {
		JInternalFrame frame = new JInternalFrame("TEST"/*child.getTitle()*/, true, true, true, true );
		frame.add(child, BorderLayout.CENTER);
		frame.pack();
		//wimpy little cascade for new frame starting x and y
		frame.setLocation(newFrameX, newFrameY);
		newFrameX = (newFrameX + 10) % desktop.getWidth(); 
		newFrameY = (newFrameY + 10) % desktop.getHeight(); 
		desktop.add(frame);
		frame.setVisible(true);
	}

	//display a child's message in a dialog centered on MDI frame
	public void displayChildMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	//creates and displays the JFrame
	public static void createAndShowGUI() {
		MasterFrame frame = new MasterFrame("MDI Skeleton");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
	}

	//main: launch the JFrame on the EDT
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	//ChildPanel : the GUI stuff that will display the inner frames
	//				all it does is update its title when you press the button (wow)
	private class ChildPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private MasterFrame master;//container of inner frame parent
		private int counter = 0;//used for inner frame title
		private String myTitle;
		JButton button;
		
		public ChildPanel(MasterFrame m) {
			master = m;
			
			this.setLayout(new BorderLayout());
			
			button = new JButton("Increment Title");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//update title
					incrementTitle();
					//tell master to display a message
					master.displayChildMessage("Child title changed to " + myTitle);
				}
			});
			
			JPanel emptyPanel = new JPanel();
			emptyPanel.add(new JLabel(""));
			this.add(emptyPanel, BorderLayout.CENTER);
			this.add(button, BorderLayout.SOUTH);
			
			//give panel some room (pack makes things kind of cramped)
			this.setPreferredSize(new Dimension(300, 300));
			
			incrementTitle();
		}
		
		//set the title of the containing JInternalFrame
		private void setInternalFrameTitle(String t) {
			Container parent = this;
			//get climbing parent hierarchy until we find the JInnerFrame
			while(!(parent instanceof JInternalFrame) && parent != null) 
	            parent = parent.getParent();
			if(parent != null)
				((JInternalFrame) parent).setTitle(t);
		}
		
		//
		private void incrementTitle() {
			myTitle = "Title Counter: " + counter;
			//update my title using getParent
			button.setText("Increment Title to " + counter);
			counter++;
			//use getParent to find JInternalFrame container and set its title
			setInternalFrameTitle(myTitle);
		}
		
		//useful for JInternalFrame starting with the child panel's title
		//child panel is instantiated before the JInternalFrame
		//so JInternalFrame calls this method to set its own title when it is created 
		public String getTitle() {
			return myTitle;
		}
	}
}