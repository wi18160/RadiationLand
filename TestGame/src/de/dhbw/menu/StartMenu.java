package de.dhbw.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.game.Game;
import javax.swing.ImageIcon;

public class StartMenu extends JFrame {

	
	private static final long serialVersionUID = 1L;


	//Hole die Screen-Dimension
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	private JPanel contentPane;
	static final int WIDTH = 1200;
	static final int HEIGHT = 600;
	
	private static final int BUTTONHEIGHT = 200;
	private static final int BUTTONWIDTH = 50;
	private static final int BUTTONGAP = 250;

	public StartMenu() {
		setTitle("Radiation Land - 2D Adventure Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		contentPane = new ImagePanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnStartGame = new JButton("");
		btnStartGame.setIcon(new ImageIcon(StartMenu.class.getResource("/de/dhbw/menu/img/start.png")));
		btnStartGame.setOpaque(false);
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBorderPainted(false);
		btnStartGame.setFocusPainted(false);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStartGame.setBounds(703, 64, 421, 208);
		contentPane.add(btnStartGame);
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new Game().start();
				
			}
		});
		
		JButton btnHowToPlay = new JButton("");
		btnHowToPlay.setIcon(new ImageIcon(StartMenu.class.getResource("/de/dhbw/menu/img/howto.png")));
		btnHowToPlay.setBounds(661, 256, 512, 149);
		btnHowToPlay.setOpaque(false);
		btnHowToPlay.setContentAreaFilled(false);
		btnHowToPlay.setBorderPainted(false);
		btnHowToPlay.setFocusPainted(false);
		contentPane.add(btnHowToPlay);
		btnHowToPlay.setVerticalAlignment(SwingConstants.TOP);
		btnHowToPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Klick!");
			}
		});
		btnHowToPlay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnExitGame = new JButton("");
		btnExitGame.setIcon(new ImageIcon(StartMenu.class.getResource("/de/dhbw/menu/img/exit.png")));
		btnExitGame.setBounds(696, 381, 443, 192);
		btnExitGame.setOpaque(false);
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBorderPainted(false);
		btnExitGame.setFocusPainted(false);
		contentPane.add(btnExitGame);
		btnHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
	}
}
