package hywt.mc.videogen;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FFmpegOutput extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtrE;
	/**
	 * Create the frame.
	 */
	public FFmpegOutput() {
		setTitle(Messages.getString("FFmpegOutput.title")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txtrE = new JTextArea();
		txtrE.setWrapStyleWord(true);
		txtrE.setEditable(false);
		txtrE.setLineWrap(true);
		scrollPane.setViewportView(txtrE);
		txtrE.setColumns(10);
	}

	public JTextArea getTextArea() {
		return txtrE;
	}
}
