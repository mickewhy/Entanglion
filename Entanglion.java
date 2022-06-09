import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.ImageIcon.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Entanglion extends JPanel {
	static JFrame frame;
	static boolean mercurialDisplayed;
	static boolean rubiconDisplayed;
	static ImageIcon mercurial = new ImageIcon("./Assets/Boards/mercurial.png");
	static JLabel mercurialLabel = new JLabel(mercurial);
	static ImageIcon rubicon = new ImageIcon("./Assets/Boards/rubicon.png");
	static JLabel rubiconLabel = new JLabel(rubicon);
	static String gameOutput = "مرحبا بك في لعبة التشابك!";
	static int detectionRate = 0;
	static int detectionRateX = 20;
	static int detectionRateY = 230;
	static JTextField textField;
	static ArrayList<String> quantumEventsDeck;
	static ArrayList<String> placedDeck = new ArrayList<String>();
	static ArrayList<String> engineCardsDeck;
	static ArrayList<String> tokens;
	static ArrayList<String> player0Tokens = new ArrayList<String>();
	static ArrayList<String> player1Tokens = new ArrayList<String>();
	static HashMap<Integer, int[]> planetPositions = new HashMap<>();
	static int[] player0Position = new int[2];
	static int[] player1Position = new int[2];
	static ArrayList<String> player0EngineCards = new ArrayList<String>();
	static ArrayList<String> player1EngineCards = new ArrayList<String>();

	public static void placeCard() {
		if (quantumEventsDeck.size() > 0)
			placedDeck.add(quantumEventsDeck.remove(quantumEventsDeck.size() - 1));
	}

	public static void pickEngineCard(ArrayList<String> deck) {
		if (engineCardsDeck.size() > 0)
			deck.add(engineCardsDeck.remove(engineCardsDeck.size() - 1));
	}

	public static void checkEndGame() {
		if (detectionRate == 11) { // LOSE
			frame.setVisible(false);
			JFrame loseFrame = new JFrame("GAME OVER");
			loseFrame = new JFrame("LOSE");
			loseFrame.setSize(1920, 1080);
			loseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			loseFrame.setUndecorated(true);
			loseFrame.setResizable(true);
			loseFrame.getContentPane().setBackground(Color.red);
			loseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loseFrame.setLocationRelativeTo(null);
			loseFrame.setVisible(true);
		}
		if (player0Tokens.size() + player1Tokens.size() == 8) { // WIN
			frame.setVisible(false);
			JFrame winFrame = new JFrame("GAME OVER");
			winFrame = new JFrame("WIN");
			winFrame.setSize(1920, 1080);
			winFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			winFrame.setUndecorated(true);
			winFrame.setResizable(true);
			winFrame.getContentPane().setBackground(Color.green);
			winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			winFrame.setLocationRelativeTo(null);
			winFrame.setVisible(true);
		}
	}

	public static void retrieve(int player) {
		int rand = 1 + (new Random().nextInt(8));
		if (rand > detectionRate) {
			ArrayList<String> currentList;
			int[] currentPosition;
			if(player == 1){ currentList = player0Tokens;currentPosition = player0Position;}
			else {currentList = player1Tokens;currentPosition = player1Position;}
			if(planetPositions.get(0)[0] == currentPosition[0]) 
		} else {
			detectionRate++;
		}
	}

	public static ArrayList<String> shuffleCards(ArrayList<String> toShuffle) {
		ArrayList<String> out = new ArrayList<String>();
		for (int i = 0; i < 3; i++)
			out.add(toShuffle.remove(new Random().nextInt(toShuffle.size())));
		out.add("./Assets/Cards/QuantumShuffle.jpg");
		for (int i = 0; i < 5; i++)
			out.add(toShuffle.remove(new Random().nextInt(toShuffle.size())));
		return out;
	}

	public static ArrayList<String> shuffleEngineCards(ArrayList<String> toShuffle) {
		ArrayList<String> out = new ArrayList<String>();
		out.add("./Assets/EngineCards/probe.png");
		for (int i = 0; i < 23; i++)
			out.add(toShuffle.remove(new Random().nextInt(toShuffle.size())));
		return out;
	}

	public static ArrayList<String> shuffleTokens(ArrayList<String> toShuffle) {
		ArrayList<String> out = new ArrayList<String>();
		for (int i = 0; i < 8; i++)
			out.add(toShuffle.remove(new Random().nextInt(toShuffle.size())));
		return out;
	}

	public void drawBoard(Graphics g) {
		Image img1 = Toolkit.getDefaultToolkit().getImage("./Assets/Boards/board.jpg");
		g.drawImage(img1, 0, 0, getWidth() - 500, getHeight(), this);
	}

	public void drawMercurial(Graphics g) {
		Image img2 = Toolkit.getDefaultToolkit().getImage("./Assets/Boards/mercurial.png");
		g.drawImage(img2, 175, 350, getWidth() - 400, getHeight() - 350, this);
	}

	public void drawRubicon(Graphics g) {
		Image img3 = Toolkit.getDefaultToolkit().getImage("./Assets/Boards/rubicon.png");
		g.drawImage(img3, 175, 350, getWidth() - 400, getHeight() - 350, this);
	}

	public void drawDetectionRate(Graphics g) {
		Image img4 = Toolkit.getDefaultToolkit().getImage("./Assets/GamePieces/detectionRate.png");
		g.drawImage(img4, detectionRateX, detectionRateY, 27, 27, this);
	}

	public void drawquantumEventsDeck(Graphics g) {
		for (int i = 0; i < quantumEventsDeck.size(); i++) {
			Image img = Toolkit.getDefaultToolkit().getImage("./Assets/Cards/back.png");
			g.drawImage(img, 30 + i * 2, 550 + i * 2, getWidth() - 1400, getHeight() - 600, this);
		}
		for (int i = 0; i < placedDeck.size(); i++) {
			Image img = Toolkit.getDefaultToolkit().getImage(placedDeck.get(i));
			g.drawImage(img, 185 + i * 2, 550 + i * 2, getWidth() - 1400, getHeight() - 600, this);
		}
	}

	public void drawEngineCards(Graphics g) {
		for (int i = 0; i < engineCardsDeck.size(); i++) {
			Image img = Toolkit.getDefaultToolkit().getImage("./Assets/EngineCards/back.png");
			g.drawImage(img, 30 + i * 2, 30 + i * 2, 70, 70, this);
		}
		for (int i = 0; i < player0EngineCards.size(); i++) {
			Image img = Toolkit.getDefaultToolkit().getImage(player0EngineCards.get(i));
			g.drawImage(img, 392 + i * 87, 30, 70, 70, this);
		}
		for (int i = 0; i < player1EngineCards.size(); i++) {
			Image img = Toolkit.getDefaultToolkit().getImage(player1EngineCards.get(i));
			g.drawImage(img, 653 + i * 87, 30, 70, 70, this);
		}
	}

	public void drawTokens(Graphics g) {
		for (int i = 0; i < tokens.size(); i++) {
			String imagePath = tokens.get(i);
			Image img = Toolkit.getDefaultToolkit().getImage(imagePath);
			int[] planet = planetPositions.get(i);
			g.drawImage(img, planet[0], planet[1], 90, 90, this);
		}
	}

	public void drawRockets(Graphics g) {
		Image redRocket = Toolkit.getDefaultToolkit().getImage("./Assets/GamePieces/redRocket.png");
		g.drawImage(redRocket, player0Position[0], player0Position[1], 60, 90, this);
		Image blueRocket = Toolkit.getDefaultToolkit().getImage("./Assets/GamePieces/blueRocket.png");
		g.drawImage(blueRocket, player1Position[0], player1Position[1], 60, 90, this);
	}

	public void paint(Graphics g) {
		drawBoard(g);

		drawDetectionRate(g);

		drawquantumEventsDeck(g);

		drawEngineCards(g);

		drawTokens(g);

		drawRockets(g);

		if (mercurialDisplayed)
			drawMercurial(g);

		if (rubiconDisplayed)
			drawRubicon(g);

		// Image img4 = Toolkit.getDefaultToolkit().getImage("img/user3.png");
		// g.drawImage(img4, 190, 20, this);

		JPanel textPanel = new JPanel();
		textField = new JTextField(gameOutput);
		textPanel.add(textField);
		frame.add(textPanel, BorderLayout.NORTH);
		frame.revalidate();

		checkEndGame();
	}

	public static void main(String args[]) {
		quantumEventsDeck = new ArrayList<String>();
		quantumEventsDeck.add("./Assets/Cards/TheMechanic.jpg");
		quantumEventsDeck.add("./Assets/Cards/QuantumTunnel.jpg");
		quantumEventsDeck.add("./Assets/Cards/Bennett.jpg");
		quantumEventsDeck.add("./Assets/Cards/Heisenberg.jpg");
		quantumEventsDeck.add("./Assets/Cards/BitFlipError.jpg");
		quantumEventsDeck.add("./Assets/Cards/WaveFunctionCollapse.jpg");
		quantumEventsDeck.add("./Assets/Cards/Schrodinger.jpg");
		quantumEventsDeck.add("./Assets/Cards/SpookyAction.jpg");
		quantumEventsDeck = shuffleCards(quantumEventsDeck);

		engineCardsDeck = new ArrayList<String>();
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/hGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/cnotGate.png");
		engineCardsDeck.add("./Assets/EngineCards/xGate.png");
		engineCardsDeck.add("./Assets/EngineCards/xGate.png");
		engineCardsDeck.add("./Assets/EngineCards/xGate.png");
		engineCardsDeck.add("./Assets/EngineCards/xGate.png");
		engineCardsDeck.add("./Assets/EngineCards/xGate.png");
		engineCardsDeck.add("./Assets/EngineCards/swap.png");
		engineCardsDeck.add("./Assets/EngineCards/swap.png");
		engineCardsDeck.add("./Assets/EngineCards/swap.png");
		engineCardsDeck = shuffleEngineCards(engineCardsDeck);

		tokens = new ArrayList<String>();
		tokens.add("./Assets/Tokens/QuantumErrorCorrection.png");
		tokens.add("./Assets/Tokens/MagneticShielding.png");
		tokens.add("./Assets/Tokens/QuantumGates.png");
		tokens.add("./Assets/Tokens/QuantumProgramming.png");
		tokens.add("./Assets/Tokens/PhysicalQubits.png");
		tokens.add("./Assets/Tokens/QubitInterconnect.png");
		tokens.add("./Assets/Tokens/DilutionRefrigerator.png");
		tokens.add("./Assets/Tokens/ControlInfrastructure.png");
		tokens = shuffleTokens(tokens);

		planetPositions.put(0, new int[] { 620, 160 });// "Omega Zero"
		planetPositions.put(1, new int[] { 765, 160 });// "Omega One"
		planetPositions.put(2, new int[] { 620, 565 });// "Omega Two"
		planetPositions.put(3, new int[] { 765, 565 });// "Omega Three"
		planetPositions.put(4, new int[] { 540, 290 });// "Psi Plus"
		planetPositions.put(5, new int[] { 845, 290 });// "Psi Minus"
		planetPositions.put(6, new int[] { 540, 450 });// "Phi Plus"
		planetPositions.put(7, new int[] { 845, 450 });// "Phi Minus"

		frame = new JFrame("Entanglion");
		frame.getContentPane().add(new Entanglion());

		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(true);

		frame.getContentPane().setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttons = new JPanel();

		JButton mercurialButton = new JButton(new AbstractAction("تعديل الميركوريال") {
			@Override
			public void actionPerformed(ActionEvent e) {
				mercurialDisplayed = !mercurialDisplayed;
				gameOutput = "تم تعديل الميركوريال";
				frame.repaint();
			}
		});
		buttons.add(mercurialButton);

		JButton rubiconButton = new JButton(new AbstractAction("تعديل الروبيكون") {
			@Override
			public void actionPerformed(ActionEvent e) {
				rubiconDisplayed = !rubiconDisplayed;
				gameOutput = "تم تعديل الروبيكون";
				frame.repaint();
			}
		});
		buttons.add(rubiconButton);

		JButton detectionRateButton = new JButton(new AbstractAction("تعديل معدل الكشف") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (detectionRate < 12)
					detectionRate++;
				switch (detectionRate) {
					case 0:
						break;
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						detectionRateX += 26;
						break;
					case 6:
						detectionRateX = 35;
						detectionRateY = 255;
						break;
					case 7:
					case 8:
					case 9:
					case 10:
					case 11:
						detectionRateX += 26;
						break;
				}
				gameOutput = "تم تعديل معدل الكشف";
				frame.repaint();
			}
		});
		buttons.add(detectionRateButton);

		JButton quantumEventsDeckButton = new JButton(new AbstractAction("أختار كارت حدث") {
			@Override
			public void actionPerformed(ActionEvent e) {
				placeCard();
				gameOutput = "تم اختيار كارت";
				frame.repaint();
			}
		});
		buttons.add(quantumEventsDeckButton);

		JButton engineCardsDeckButton1 = new JButton(new AbstractAction("أختار كارت محرك - لاعب 1") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player0EngineCards.size() < 3) {
					pickEngineCard(player0EngineCards);
					gameOutput = "تم اختيار كارت";
					frame.repaint();
				}
			}
		});
		buttons.add(engineCardsDeckButton1);

		JButton engineCardsDeckButton2 = new JButton(new AbstractAction("أختار كارت محرك - لاعب 2") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player1EngineCards.size() < 3) {
					pickEngineCard(player1EngineCards);
					gameOutput = "تم اختيار كارت";
					frame.repaint();
				}
			}
		});
		buttons.add(engineCardsDeckButton2);

		JButton dice8 = new JButton(new AbstractAction("أختار رقم من 8") {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameOutput = 1 + (new Random().nextInt(8)) + "";
				frame.repaint();
			}
		});
		buttons.add(dice8);

		JButton dice1 = new JButton(new AbstractAction("أختار رقم من 1") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rand = new Random().nextInt(2);
				gameOutput = rand + "";
				if (rand == 0) {
					player0Position = new int[] { 415, 450 };
					player1Position = new int[] { 375, 290 };
				} else {
					player0Position = new int[] { 375, 290 };
					player1Position = new int[] { 415, 450 };
				}
				frame.repaint();
			}
		});
		buttons.add(dice1);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.add(buttons);
		frame.add(southPanel, BorderLayout.SOUTH);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}