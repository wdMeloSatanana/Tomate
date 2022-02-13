package janela;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Relogio extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuDuracao menuTempo;
	private JLabel tempo;
	private JButton iniciar;
	private JButton sair;
	private JButton opcoesDuracao;
	private JLabel tempoFocado;
	private JButton resetarTempoFocado;
	
	
	private int tempoTotal = 60 * 40;
	private int tempoSessao = 0;
	
	private int segundosSessao = 0;
	private int minutosSessao = 0;
	private int horasSessao = 0;
	private String string_horas_sessao = String.format("%02d", horasSessao);
	private String string_segundos_sessao = String.format("%02d", segundosSessao);
	private String string_minutos_sessao = String.format("%02d", minutosSessao);
	
	private int segundos = tempoTotal % 60;
	private int minutos = (tempoTotal / 60) % 60;
	private String string_segundos = String.format("%02d", segundos);
	private String string_minutos = String.format("%02d", minutos);
	
	private boolean pausa = false;
	

	 
	
	
	public Relogio() {
 
		inicializarComponentes();
		reactions();

	}
	
	public void inicializarComponentes() {
		
		setIconImage(new ImageIcon("./imagens/sair.png").getImage());
		setSize(600, 600);
		setTitle("Tomate");
		setLocation(800, 300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 114, 111));
		
		tempoFocado = new JLabel("Tempo em sessão: 00:00:00");
		tempoFocado.setHorizontalAlignment(JTextField.CENTER);
		tempoFocado.setBounds(210, 25, 175, 100);
		
		resetarTempoFocado = new JButton();
		resetarTempoFocado.setText("Limpar");
		resetarTempoFocado.setOpaque(true);
		resetarTempoFocado.setBackground(new Color(255, 204, 203));
		resetarTempoFocado.setBounds(35, 65, 98, 25);
		resetarTempoFocado.setBorder(BorderFactory.createEmptyBorder());
		
		tempo = new JLabel();
		tempo.setText(string_minutos + ":" + string_segundos);
		tempo.setHorizontalAlignment(JTextField.CENTER);
		tempo.setBounds(200, 150, 200, 200);
		tempo.setOpaque(true);
		tempo.setFont(new Font("Verdana", Font.PLAIN, 35));
		tempo.setBorder(BorderFactory.createBevelBorder(1));
		tempo.setBackground(new Color(241, 128, 125));
		
		iniciar = new JButton("Iniciar");
		iniciar.setFont(new Font("Verdana", Font.PLAIN, 15));
		iniciar.setBounds(50, 120, 95, 40);
		iniciar.setOpaque(true);
		iniciar.setBackground(new Color(255, 204, 203));
		 
		sair = new JButton(new ImageIcon("./imagens/sair.png"));
		sair.setOpaque(true);
		sair.setBackground(new Color(255, 114, 111));
		sair.setBounds(250, 450, 100,100);
		sair.setBorder(BorderFactory.createEmptyBorder());
		
		 
		
		menuTempo = new MenuDuracao();
		
		opcoesDuracao = new JButton("Duração");
		opcoesDuracao.setBounds(250, 360, 100, 100);
		opcoesDuracao.setBorder(BorderFactory.createEmptyBorder());
		opcoesDuracao.setOpaque(true);
		opcoesDuracao.setBackground(new Color(255, 204, 203));
		opcoesDuracao.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				menuTempo.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		
		add(opcoesDuracao);
		tempoFocado.add(resetarTempoFocado);
		tempo.add(iniciar);
		add(sair);
		add(tempoFocado);
		add(tempo);

		
		temaTarefa();
		atualizarRelogio();
		setVisible(true);
	}
	
	public void reactions() {
		iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(iniciar.getText().equals("Iniciar")) {
					iniciar.setText("Parar");
					start();
				}else {
					iniciar.setText("Iniciar");
					stop();
				}			
			}
		});
		resetarTempoFocado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
				tempoTotal = 50 * 60;
				iniciar.setText("Iniciar");
				atualizarRelogio();
				pausa=false;
				temaTarefa();
			}
		});
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		menuTempo.m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//tempoTotal = 25 * 60;
				tempoTotal = 25 * 60;
				atualizarRelogio();
				temaTarefa();
			}
			 
		});

		menuTempo.m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempoTotal = 30 * 60;
				atualizarRelogio();
				temaTarefa();
			}
			 
		});
		menuTempo.m3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tempoTotal = 35 * 60;
				atualizarRelogio();
				temaTarefa();
			}
			 
		});
		menuTempo.m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempoTotal = 40 * 60;
				atualizarRelogio();
				temaTarefa();
			}
			 
		});
		menuTempo.m5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempoTotal = 50 * 60;
				atualizarRelogio();
				temaTarefa();
			} 
		});
	}
	
	
	
	public Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tempoTotal -= 1;
			tempoSessao += 1;
			segundos = tempoTotal % 60;
			minutos = (tempoTotal / 60) % 60;
			string_segundos = String.format("%02d", segundos);
			string_minutos = String.format("%02d", minutos);
			
			segundosSessao = tempoSessao % 60;
			minutosSessao = (tempoSessao / 60) % 60;
			horasSessao = minutosSessao % 60;
			string_horas_sessao = String.format("%02d", horasSessao);
			string_segundos_sessao = String.format("%02d", segundosSessao);
			string_minutos_sessao = String.format("%02d", minutosSessao);
			
			tempoFocado.setText("Tempo em sessão: " + string_horas_sessao + ":"  + string_minutos_sessao + ":" + string_segundos_sessao);
			tempo.setText(string_minutos + ":" + string_segundos);
			
			if (tempoTotal == 0) {
				if(!pausa) {
					//tempoTotal = 10 * 60;
					tempoTotal = 3;
					atualizarRelogio();
					stop();
					iniciar.setText("Iniciar");
					setPausa(true);
					temaPausa();
					playSound("./alarmes/za_warudo.wav");
					
				}else {
					//tempoTotal = 50 * 60;
					tempoTotal = 50*60;
					atualizarRelogio();
					stop();
					iniciar.setText("Iniciar");
					setPausa(false);
					temaTarefa();
					playSound("./alarmes/alarm-digital.wav");

				}
			}
		}
	} );
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
		
	}
	public void restart() {
		timer.stop();
		tempoTotal = 0;
		segundos = 0;
		minutos = 0;
		
		tempoSessao=0;
		segundosSessao = 0;
		minutosSessao = 0;
		
		string_segundos = String.format("%02d", segundos);
		string_minutos = String.format("%02d", minutos);
		
		string_horas_sessao = String.format("%02d", horasSessao);
		string_segundos_sessao = String.format("%02d", segundosSessao);
		string_minutos_sessao = String.format("%02d", minutosSessao);
		
		
		tempoFocado.setText("Tempo em sessão: " + string_horas_sessao + ":"  + string_minutos_sessao + ":" + string_segundos_sessao);
		tempo.setText(string_minutos + ":" + string_segundos);
	}
	
	public void atualizarRelogio() {
		segundos = tempoTotal % 60;
		minutos = (tempoTotal / 60) % 60;
		string_segundos = String.format("%02d", segundos);
		string_minutos = String.format("%02d", minutos);
		tempo.setText(string_minutos + ":" + string_segundos);
	}
	
	public void temaPausa() {
		getContentPane().setBackground(new Color(177, 210, 255));
		resetarTempoFocado.setBackground(new Color(176, 196, 222));
		tempo.setBackground(new Color(120, 154, 199));
		iniciar.setBackground(new Color(176, 196, 222));
		sair.setBackground(new Color(177, 210, 255));
		opcoesDuracao.setBackground(new Color(176, 196, 222));
	}
	
	public void temaTarefa() {
		getContentPane().setBackground(new Color(255, 114, 111));
		resetarTempoFocado.setBackground(new Color(255, 204, 203));
		tempo.setBackground(new Color(241, 128, 125));
		iniciar.setBackground(new Color(255, 204, 203));
		sair.setBackground(new Color(255, 114, 111));
		opcoesDuracao.setBackground(new Color(255, 204, 203));

	}
	public boolean isPausa() {
		return pausa;
	}
	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}
	
	public void playSound(String soundFile) {
	    File f = new File("./" + soundFile);
	    try {
	    	AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioIn);
		    clip.start();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
}
