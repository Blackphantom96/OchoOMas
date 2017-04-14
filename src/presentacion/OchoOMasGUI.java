package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class OchoOMasGUI extends JFrame {
	public static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenuBar menu;
	private JMenu cont;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;
	private JMenu options;
	private JMenuItem changeColor;
	private JMenuItem changeSize;
	private JLabel steps;
	private ArrayList<JButton> gamePanel;
	private JPanel buttonPanel;
	private JPanel buttons;
	private JPanel board;
	private JButton play;
	private JButton reset;
	private Color buttonWin;
	private Color buttonLose;
	private int xSize;
	private int ySize;
	private JFileChooser explorer;
	private JColorChooser selectColor;
	
	public static void main(String[] args) {
		OchoOMasGUI gui = new OchoOMasGUI();
	}

	private OchoOMasGUI() {
		super();
		buttonWin = Color.GREEN;
		buttonLose = Color.gray;
		gamePanel = new ArrayList<>();
		xSize = 3;
		ySize = 3;
		setVisible(true);
		setSize((int) screensize.width * 1 / 4, (int) screensize.height * 1 / 4);
		setTitle("OchoOMas");
		prepareElementos();
		prepareAcciones();
	}

	private void prepareElementos() {
		centre();
		prepareElementosMenu();
		add(menu, BorderLayout.NORTH);
		prepareElementosTablero();
		add(board, BorderLayout.CENTER);
		refresque();
	}

	private void refresque() {
		SwingUtilities.updateComponentTreeUI(this);
		regenereColor();
	}

	private void prepareElementosTablero() {
		board = new JPanel();
		board.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(xSize, ySize));
		ArrayList<Integer> numbers = getRandomNumbers(xSize, ySize);
		while (!isAlmostOrdered(numbers)) {
			numbers = getRandomNumbers(xSize, ySize);
		}
		for (int i : numbers) {
			addButton(i);
		}
		addButton(0);
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		play = new JButton("Play !");
		reset = new JButton("Reset");
		buttons.add(play);
		buttons.add(reset);
		board.add(buttonPanel, BorderLayout.CENTER);
		board.add(buttons, BorderLayout.SOUTH);
		steps = new JLabel("Steps: Int");
		board.add(steps, BorderLayout.NORTH);

	}
	private void addButton(int i){
		JButton temp;
		if (i==0)
			temp = new JButton(" ");
		else
			temp = new JButton(Integer.toString(i));
		temp.setBackground(buttonLose);
		temp.setOpaque(true);
		temp.setContentAreaFilled(true);
		buttonPanel.add(temp);
		gamePanel.add(temp);
	}
	private boolean isAlmostOrdered(ArrayList<Integer> numbers) {
		int j = numbers.get(0);
		for (int i : numbers) {
			if (i - 1 == j) {
				return true;
			}
			j = i;
		}
		return false;
	}

	private ArrayList<Integer> getRandomNumbers(int xSize2, int ySize2) {
		ArrayList<Integer> res = new ArrayList<>();
		Random inte = new Random();
		while (res.size() != (xSize2 * ySize2) - 1) {
			int a = inte.nextInt((xSize2 * ySize2) - 1) + 1;
			if (res.indexOf(a) < 0) {
				res.add(a);
			}
		}
		return res;
	}

	private void prepareAcciones() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				salga();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salga();
			}
		});
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccione();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guarde();
			}
		});
		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorChooser();
			}
		});

	}
	private void colorChooser(){
		buttonLose = JColorChooser.showDialog(this, "hola ", Color.white);
		refresque();
	}
	
	private void Seleccione() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Open");
		explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (explorer.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(this, 
					"Metodo open en Construccion,pero el nombre del archivo es: " + explorer.getSelectedFile().getName());
		}
	}
	private void guarde() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Open");
		explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
		explorer.showOpenDialog(this);
		if (explorer.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(this, 
					"Metodo open en Construccion,pero el nombre del archivo es: " + explorer.getSelectedFile().getName());
		}
	}
	private void salga() {
		int option = JOptionPane.showConfirmDialog(this, "Exit OchoOMas?", "Exit", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void prepareElementosMenu() {
		menu = new JMenuBar();
		cont = new JMenu("Menu");
		{
			open = new JMenuItem("Open");
			save = new JMenuItem("Save");
			options = new JMenu("Options");
			{
				changeColor = new JMenuItem("Change Color");
				changeSize = new JMenuItem("Change Size");
				options.add(changeColor);
				options.add(changeSize);
			}
			exit = new JMenuItem("Exit");
			cont.add(open);
			cont.add(save);
			cont.add(options);
			cont.add(exit);
		}
		menu.add(cont);
	}

	private void centre() {
		setLocation((screensize.width - getSize().width) / 2, (screensize.height - getSize().height) / 2);
	}
	private void regenereColor(){
		for(JButton a: gamePanel){
			a.setBackground(buttonLose);
		}
	}

}
