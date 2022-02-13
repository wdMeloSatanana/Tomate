package janela;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
	private JLabel usuario;
	private JLabel senha;
	private JTextField usuarioInput;
	private JPasswordField senhaInput;
	private JButton entrar;
	private JButton sair;
	
	
	public Login () {
		usuario = new JLabel("Usuário:");
		usuario.setBounds(5, 5, 100, 25);
		
		usuarioInput = new JTextField();
		usuarioInput.setBounds(55, 5 , 100, 25);
		
		senha = new JLabel("Senha:");
		senha.setBounds(5, 30, 100, 25);
		
		senhaInput = new JPasswordField();
		senhaInput.setBounds(55, 30, 100, 25);
		
		entrar = new JButton("Entrar");
		entrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String senhaTxt = String.valueOf(senhaInput.getPassword());
				if (usuarioInput.getText().equals("berg") && senhaTxt.equals("123")) {
					setVisible(false);
					new Relogio();
				}else {
					JOptionPane.showMessageDialog(null, "Login ou senha errados");
				}
				
			}
			
		});
		
		entrar.setBounds(15, 70, 75, 25);
		
		sair = new JButton("Sair");
		sair.setBounds(100, 70, 75, 25);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setIconImage(new ImageIcon("./imagens/sair.png").getImage());
		setLayout(null);
		setSize(250, 150);
		setLocation(800,300);
		setTitle("Login");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(usuario);
		add(usuarioInput);
		add(senha);
		add(senhaInput);
		add(entrar);
		add(sair);
		getContentPane().setBackground(new Color(241, 128, 125));

		setVisible(true);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
