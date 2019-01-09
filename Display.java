import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {
//this class is used to generate the GUI display and uses the rules found in the RulesOfLife class
	
	private JFrame frame;
	JPanel panelGraphics;
	int width = 600;
	int height = 650;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RulesOfLife.randomGrid(RulesOfLife.gridHeight, RulesOfLife.gridWidth, 0.25);
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Display() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 50, width-4, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panelGraphics = new GPanel();
		frame.getContentPane().add(panelGraphics, BorderLayout.CENTER);
	}

	public class GPanel extends JPanel  implements ActionListener {
		
		public GPanel() {
			//default constructor for the panel starts a timer to schedule the next update
			Timer time = new Timer(100, this);
			time.start();	
		}
		
		public void paint(Graphics g) {
			//draws the grid lines and then fills in the squares based of the true values found in the 2D array "currentGrid"
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			for (int i = 0; i <= (width/10); i++) {
				g.drawLine((10*i), 0, (10*i), height);
			}
			for (int i = 0; i <= (height/10); i++) {
				g.drawLine(0, (10*i), width, (10*i));
			}
			g.setColor(Color.GRAY);
			for (int i = 0; i <= RulesOfLife.currentGrid.length-1; i++) {
				for (int j = 0; j <= RulesOfLife.currentGrid[0].length-1; j++) {
					if (RulesOfLife.currentGrid[i][j] == true) {
						g.fillRect((10*i), (10*j), 10, 10);
					}
				}
			}
		}
		
		public void actionPerformed(ActionEvent event) {
			//when the timer goes off these events are performed, scanning of the grid and the repaint of the GUI display
			RulesOfLife.gridScan();
			RulesOfLife.generation++;
			repaint();
		}
		
	}
	
}
