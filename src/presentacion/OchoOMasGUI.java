package presentacion;

import aplicacion.OchoOmas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
	private JLabel stepsLabel;
	private JButton[][] gamePanel;
	private JPanel buttonPanel;
	private JPanel buttons;
	private JPanel board;
	private JButton play;
	private JButton reset;
	private Color colorButton;
	private int ySize;
	private int xSize;
	private int steps;
	private JFileChooser explorer;
	private OchoOmas logical;

	public static void main(String[] args) {
		OchoOMasGUI gui = new OchoOMasGUI();
	}

	private OchoOMasGUI() {
		super();
		colorButton=Color.green;
		ySize = 3;
		xSize = 3;
		setVisible(true);
		setSize((int) screensize.width * 1 / 2, (int) screensize.height * 1 / 2);
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
		steps = 0;
		logical = new OchoOmas(xSize, ySize);
		board = new JPanel();
		board.setLayout(new BorderLayout());
		resetButtons();
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		play = new JButton("Play !");
		reset = new JButton("Reset");
		buttons.add(play);
		buttons.add(reset);
		board.add(buttons, BorderLayout.SOUTH);
		stepsLabel = new JLabel("Steps: " + steps);
		board.add(stepsLabel, BorderLayout.NORTH);

	}

	private void resetButtons() {
		if(buttonPanel!=null)
			board.remove(buttonPanel);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(ySize, xSize));
		gamePanel = new JButton[ySize][xSize];
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				addButton(logical.getNumber(i, j), i, j);
			}
		}
		board.add(buttonPanel, BorderLayout.CENTER);
	}

	private void changeid() {
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				int a = logical.getNumber(i, j);
				gamePanel[i][j].setText(a != 0 ? Integer.toString(a) : " ");
			}
		}
		stepsLabel.setText("Steps: " + steps);
	}

	private void addButton(int i, int y, int x) {
		JButton temp;
		if (i == 0)
			temp = new JButton(" ");
		else
			temp = new JButton(Integer.toString(i));
		temp.setBackground(Color.white);
		temp.setOpaque(true);
		temp.setContentAreaFilled(true);
		temp.setFont(new Font("Courier", Font.BOLD, 28)); 
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change(temp.getText());
			}
		});
		buttonPanel.add(temp);
		gamePanel[y][x] = (temp);
	}

	private void change(String a) {
		if (!a.equals(" ") && logical.changeOrder(Integer.parseInt(a)))
			steps++;
		changeid();
		refresque();
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
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetee();
			}
		});
		changeSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSize();
			}
		});

	}
	private void resetee(){
		remove(board);
		prepareElementos();
		prepareAcciones();
	}
	private void colorChooser() {
		colorButton = JColorChooser.showDialog(this, "hola ", Color.white);
	}

	private void Seleccione() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Open...");
		explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (explorer.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(this, "Metodo open en construccion, pero el nombre del archivo es: "
					+ explorer.getSelectedFile().getName());
		}
	}

	private void guarde() {
		explorer = new JFileChooser();
		explorer.setDialogTitle("Save...");
		explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (explorer.showSaveDialog(open) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(this, "Metodo save en construccion, pero el nombre del archivo es: "
					+ explorer.getSelectedFile().getName());
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
	
	private void changeSize(){
		JPanel doubleInt= new JPanel();
		JTextField xField=new JTextField(2);
		JTextField yField=new JTextField(2);
		doubleInt.add(new JLabel("x: "));
		doubleInt.add(xField);
		doubleInt.add(Box.createHorizontalStrut(15));
		doubleInt.add(new JLabel("y: "));
		doubleInt.add(yField);
		int result = JOptionPane.showConfirmDialog(null, doubleInt, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	         xSize=Integer.parseInt(xField.getText());
	         ySize=Integer.parseInt(yField.getText());
	      }
	      resetee();
	}
	
	private void centre() {
		setLocation((screensize.width - getSize().width) / 2, (screensize.height - getSize().height) / 2);
	}

	private void regenereColor() {
		for (JButton[] a : gamePanel) {
			for (JButton b : a) {
				if (logical.isSolved(b.getText().equals(" ")?0:Integer.parseInt(b.getText())))
					b.setBackground(colorButton);
				else
					b.setBackground(Color.white);
			}
		}
	}

}
