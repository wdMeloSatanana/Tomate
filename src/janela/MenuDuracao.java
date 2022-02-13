package janela;

import javax.swing.*;

public class MenuDuracao extends JPopupMenu {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JMenuItem m1;
	public JMenuItem m2;
	public JMenuItem m3;
	public JMenuItem m4;
	public JMenuItem m5;
	
	public MenuDuracao() {
			m1 = new JMenuItem("25:00");
			m2 = new JMenuItem("30:00");
			m3 = new JMenuItem("35:00");
			m4 = new JMenuItem("40:00");
			m5 = new JMenuItem("50:00");
			
			
			add(m1);
			add(m2);
			add(m3);
			add(m4);
			add(m5);
			
		}
}
