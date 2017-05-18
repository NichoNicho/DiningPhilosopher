import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Main {

	private JFrame diningTableFrame;
	JButton startButton;
	private final JTextArea tableText = new JTextArea();

	private ArrayList<Philosopher> philsList = new ArrayList<Philosopher>();
	private ArrayList<Fork> forksList = new ArrayList<Fork>();
	private ArrayList<JLabel> hands = new ArrayList<JLabel>();
	private ArrayList<JLabel> faces = new ArrayList<JLabel>();

	public Main() {

		for (int i = 0; i < 5; i++) {
			Philosopher ph = new Philosopher("Philosopher_" + (i + 1), this);
			philsList.add(ph);
			forksList.add(new Fork());
		}

		for (int i = 0; i < 5; i++) {
			philsList.get(i).Left = forksList.get(i);
			if (i != 0)
				philsList.get(i).Right = forksList.get(i - 1);
			else
				philsList.get(i).Right = forksList.get(4);
		}

		Create();
	}

	private boolean Deadlocked() {
		for (int i = 0; i < 5; i++) {
			Philosopher p = philsList.get(i);
			if (!p.state.equals("Hungry"))
				return false;
		}
		return true;
	}


	private void assign() {
		for (int i = 0; i < 5; i++) {
			Philosopher p = philsList.get(i);

			// hands
			hands.get(i * 2).setEnabled(p.lefthold);
			hands.get((i * 2) + 1).setEnabled(p.righthold);

			// state
			faces.get(i).setText(p.state);
		}

		if (Deadlocked()) {
			for(int i=0;i<5;i++)
			philsList.get(i).isRunning = false;
		}
	}

	public void paintAgain() {
		assign();
		diningTableFrame.revalidate();
		diningTableFrame.repaint();
	}

	public void addtoTable(String text) {

		tableText.append(text + "\n");
		tableText.setCaretPosition(tableText.getDocument().getLength());
		paintAgain();

		System.out.println(text);
	}

	public void Create() {

		diningTableFrame = new JFrame("Dining Table");

		diningTableFrame.setBounds(600, 200, 620, 590);
		diningTableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		diningTableFrame.getContentPane().add(panel, BorderLayout.NORTH);

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Start Button
				for (int i = 0; i < 5; i++) {

					philsList.get(i).start();

				}
			}
		});
		panel.add(startButton);

		JButton endButton = new JButton("End");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Start Button
				for (int i = 0; i < 5; i++) {
					startButton.setEnabled(false);
					philsList.get(i).isRunning = false;

				}
			}
		});
		panel.add(endButton);

		JPanel panel_1 = new JPanel();
		diningTableFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		// table
		ImageIcon ic = new ImageIcon(
				Main.class.getResource("/Resources/table.png"));
		JLabel jlt = new JLabel();
		jlt.setIcon(ic);
		jlt.setBounds(150, 10, 504, 360);
		jlt.setHorizontalTextPosition(JLabel.CENTER);
		jlt.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlt);

		// ph1
		ImageIcon ic1 = new ImageIcon(
				Main.class.getResource("/Resources/ph1.png"));
		JLabel jlPh_1 = new JLabel("Mirandola");
		jlPh_1.setIcon(ic1);
		jlPh_1.setBounds(116, 0, 67, 100);
		jlPh_1.setHorizontalTextPosition(JLabel.CENTER);
		jlPh_1.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlPh_1);
		faces.add(jlPh_1);

		// ph2
		ImageIcon ic2 = new ImageIcon(
				Main.class.getResource("/Resources/ph2.png"));
		JLabel jlPh_2 = new JLabel("Aristotle");
		jlPh_2.setIcon(ic2);
		jlPh_2.setBounds(102, 223, 67, 100);
		jlPh_2.setHorizontalTextPosition(JLabel.CENTER);
		jlPh_2.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlPh_2);
		faces.add(jlPh_2);

		// ph3
		ImageIcon ic3 = new ImageIcon(
				Main.class.getResource("/Resources/ph3.png"));
		JLabel jlPh_3 = new JLabel("Newton");
		jlPh_3.setIcon(ic3);
		jlPh_3.setBounds(272, 334, 67, 100);
		jlPh_3.setHorizontalTextPosition(JLabel.CENTER);
		jlPh_3.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlPh_3);
		faces.add(jlPh_3);

		// ph4
		ImageIcon ic4 = new ImageIcon(
				Main.class.getResource("/Resources/ph4.png"));
		JLabel jlPh_4 = new JLabel("Socrates");
		jlPh_4.setIcon(ic4);
		jlPh_4.setBounds(458, 210, 67, 100);
		jlPh_4.setHorizontalTextPosition(JLabel.CENTER);
		jlPh_4.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlPh_4);
		faces.add(jlPh_4);

		// ph5
		ImageIcon ic5 = new ImageIcon(
				Main.class.getResource("/Resources/ph5.png"));
		JLabel jlPh_5 = new JLabel("Khayyam");
		jlPh_5.setIcon(ic5);
		jlPh_5.setBounds(413, 0, 67, 100);
		jlPh_5.setHorizontalTextPosition(JLabel.CENTER);
		jlPh_5.setVerticalTextPosition(JLabel.BOTTOM);
		panel_1.add(jlPh_5);
		faces.add(jlPh_5);

		// ph1RightFork

		JLabel ph1rf = new JLabel("");
		ph1rf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-150.png")));
		ph1rf.setBounds(188, 77, 28, 39);
		panel_1.add(ph1rf);
		hands.add(ph1rf);

		// ph1LeftFork

		JLabel ph1lf = new JLabel("");
		ph1lf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-120.png")));
		ph1lf.setBounds(215, 54, 39, 27);
		panel_1.add(ph1lf);
		hands.add(ph1lf);

		// ph2RightFork

		JLabel ph2rf = new JLabel("");
		ph2rf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-90.png")));
		ph2rf.setBounds(174, 250, 40, 7);
		panel_1.add(ph2rf);
		hands.add(ph2rf);

		// ph2LeftFork

		JLabel ph2lf = new JLabel("");
		ph2lf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-30.png")));
		ph2lf.setBounds(152, 190, 27, 39);
		panel_1.add(ph2lf);
		hands.add(ph2lf);

		// ph3RightFork

		JLabel ph3rf = new JLabel("");
		ph3rf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-30.png")));
		ph3rf.setBounds(320, 291, 27, 39);
		panel_1.add(ph3rf);
		hands.add(ph3rf);

		// ph3LeftFork

		JLabel ph3lf = new JLabel("");
		ph3lf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-r30.png")));
		ph3lf.setBounds(260, 291, 27, 39);
		panel_1.add(ph3lf);
		hands.add(ph3lf);

		// ph4RightFork

		JLabel ph4rf = new JLabel("");
		ph4rf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-r60.png")));
		ph4rf.setBounds(421, 189, 39, 27);
		panel_1.add(ph4rf);
		hands.add(ph4rf);

		// ph4LeftFork

		JLabel ph4lf = new JLabel("");
		ph4lf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-r90.png")));
		ph4lf.setBounds(408, 243, 40, 7);
		panel_1.add(ph4lf);
		hands.add(ph4lf);

		// ph5leftFork
		JLabel ph5lf = new JLabel("");
		ph5lf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-r150.png")));
		ph5lf.setBounds(393, 77, 27, 39);
		panel_1.add(ph5lf);
		hands.add(ph5lf);

		// ph5RighttFork

		JLabel ph5rf = new JLabel("");
		ph5rf.setIcon(new ImageIcon(Main.class
				.getResource("/Resources/Fork-r120.png")));
		ph5rf.setBounds(360, 57, 39, 27);
		panel_1.add(ph5rf);
		hands.add(ph5rf);

		JPanel panel2 = new JPanel();
		diningTableFrame.getContentPane().add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.add(scrollPane);
		tableText.setEditable(false);
		tableText.setRows(5);
		scrollPane.setViewportView(tableText);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.diningTableFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
