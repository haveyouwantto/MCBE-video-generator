package hywt.mc.videogen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Converter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected boolean ffmpegSelected;
	protected File ffmpegFile;
	protected File videoFile;

	/**
	 * Create the frame.
	 */
	public Converter() {
		setResizable(false);
		setTitle(Messages.getString("Converter.title")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 511, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String[] videos = { "mp4", "flv", "mkv", "3gp", "wmv", "mov", "rmvb", "avi", "rm", "amv", "ts", "mpg", "mpeg" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$
		FileNameExtensionFilter vf = new FileNameExtensionFilter(Messages.getString("Converter.supportedVideoFormats"), //$NON-NLS-1$
				videos);

		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 486, 129);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel videoPanel = new JPanel();
		videoPanel.setBounds(14, 13, 215, 103);
		panel.add(videoPanel);
		videoPanel.setLayout(null);

		JLabel lblVideo = new JLabel(Messages.getString("Converter.videoLabel")); //$NON-NLS-1$
		lblVideo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVideo.setBounds(14, 13, 187, 18);
		videoPanel.add(lblVideo);

		JButton btnVideo = new JButton(Messages.getString("Converter.unselected")); //$NON-NLS-1$
		btnVideo.setBounds(14, 63, 187, 27);
		videoPanel.add(btnVideo);
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("."); //$NON-NLS-1$
				fc.setFileFilter(vf);
				int returnVal = fc.showOpenDialog(Converter.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					videoFile = selectedFile;
					btnVideo.setText(selectedFile.getName());
				}
			}
		});

		JPanel ffmpegPanel = new JPanel();
		ffmpegPanel.setBounds(243, 13, 233, 103);
		panel.add(ffmpegPanel);
		ffmpegPanel.setLayout(null);

		JLabel lblFfmpegExecutable = new JLabel(Messages.getString("Converter.ffmpegLabel")); //$NON-NLS-1$
		lblFfmpegExecutable.setHorizontalAlignment(SwingConstants.CENTER);
		lblFfmpegExecutable.setBounds(14, 13, 205, 18);
		ffmpegPanel.add(lblFfmpegExecutable);

		JButton btnFFmpeg = new JButton(Messages.getString("Converter.unselected")); //$NON-NLS-1$
		btnFFmpeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("."); //$NON-NLS-1$
				int returnVal = fc.showOpenDialog(Converter.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					if (selectedFile.getName().matches("(.*)ffmpeg(.*)")) { //$NON-NLS-1$
						btnFFmpeg.setText(selectedFile.getName());
						ffmpegSelected = true;
						ffmpegFile = selectedFile;
					} else {
						JOptionPane.showMessageDialog(Converter.this, Messages.getString("Converter.invalidFfmpegExec"), //$NON-NLS-1$
								Messages.getString("Window.error"), 0); //$NON-NLS-1$
					}
				}
			}
		});
		btnFFmpeg.setBounds(14, 63, 205, 27);
		ffmpegPanel.add(btnFFmpeg);

		JPanel outputSettings = new JPanel();
		outputSettings.setBounds(5, 147, 486, 93);
		contentPane.add(outputSettings);
		GridBagLayout gbl_outputSettings = new GridBagLayout();
		gbl_outputSettings.columnWidths = new int[] { 206, 277, 0 };
		gbl_outputSettings.rowHeights = new int[] { 18, 28, 31, 0 };
		gbl_outputSettings.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_outputSettings.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		outputSettings.setLayout(gbl_outputSettings);

		JLabel lblOutputSettings = new JLabel(Messages.getString("Converter.lblOutputSettings.text")); //$NON-NLS-1$
		lblOutputSettings.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOutputSettings = new GridBagConstraints();
		gbc_lblOutputSettings.anchor = GridBagConstraints.NORTH;
		gbc_lblOutputSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOutputSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblOutputSettings.gridwidth = 2;
		gbc_lblOutputSettings.gridx = 0;
		gbc_lblOutputSettings.gridy = 0;
		outputSettings.add(lblOutputSettings, gbc_lblOutputSettings);

		JLabel lblResolution = new JLabel(Messages.getString("Converter.lblResolution.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblResolution = new GridBagConstraints();
		gbc_lblResolution.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblResolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblResolution.gridx = 0;
		gbc_lblResolution.gridy = 1;
		outputSettings.add(lblResolution, gbc_lblResolution);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		outputSettings.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 93, 47, 0 };
		gbl_panel_1.rowHeights = new int[] { 24, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(180), null, null, new Integer(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.gridx = 0;
		gbc_spinner.gridy = 0;
		panel_1.add(spinner, gbc_spinner);

		JLabel lblP = new JLabel(Messages.getString("Converter.lblP.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblP = new GridBagConstraints();
		gbc_lblP.anchor = GridBagConstraints.WEST;
		gbc_lblP.gridx = 1;
		gbc_lblP.gridy = 0;
		panel_1.add(lblP, gbc_lblP);

		JLabel lblPixelFormat = new JLabel(Messages.getString("Converter.lblPixelFormat.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPixelFormat = new GridBagConstraints();
		gbc_lblPixelFormat.anchor = GridBagConstraints.EAST;
		gbc_lblPixelFormat.fill = GridBagConstraints.VERTICAL;
		gbc_lblPixelFormat.insets = new Insets(0, 0, 0, 5);
		gbc_lblPixelFormat.gridx = 0;
		gbc_lblPixelFormat.gridy = 2;
		outputSettings.add(lblPixelFormat, gbc_lblPixelFormat);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		outputSettings.add(panel_2, gbc_panel_2);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "rgb24","pal8", "gray","monob" })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		panel_2.add(comboBox);

		JButton btnConvert = new JButton(Messages.getString("Converter.btnConvert.text")); //$NON-NLS-1$
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ffmpegFile != null) {
					File tempdir = new File("./" + videoFile.getName()); //$NON-NLS-1$
					if (!tempdir.exists()) {
						tempdir.mkdirs();
						List<String> command = new ArrayList<String>();
						command.add(ffmpegFile.getAbsolutePath());
						command.add("-i"); //$NON-NLS-1$
						command.add(videoFile.getAbsolutePath());
						command.add("-vf"); //$NON-NLS-1$
						command.add("scale=-1:" + spinner.getValue().toString()); //$NON-NLS-1$
						command.add("-r"); //$NON-NLS-1$
						command.add("20"); //$NON-NLS-1$
						command.add("-pix_fmt"); //$NON-NLS-1$
						command.add(comboBox.getSelectedItem().toString());
						command.add("-sws_flags");
						command.add("neighbor");
						command.add("-sws_dither");
						command.add("a_dither");
						command.add("./" + videoFile.getName() + "/img%06d.png"); //$NON-NLS-1$ //$NON-NLS-2$
						command.add("-vn"); //$NON-NLS-1$
						command.add("./" + videoFile.getName() + "/audio.ogg"); //$NON-NLS-1$ //$NON-NLS-2$
						Runnable r = new Runnable() {
							public void run() {
								ProcessBuilder builder = new ProcessBuilder();
								builder.command(command);
								builder.redirectErrorStream(true);
								try {
									FFmpegOutput fo = new FFmpegOutput();
									fo.setVisible(true);
									Process process = builder.start();

									String line = null;
									BufferedReader br = new BufferedReader(
											new InputStreamReader(process.getInputStream()));
									while ((line = br.readLine()) != null) {
										fo.getTextArea().append(line + "\n"); //$NON-NLS-1$
										fo.getTextArea().setCaretPosition(fo.getTextArea().getText().length());
									}
									JOptionPane.showMessageDialog(Converter.this,
											Messages.getString("Converter.processEnded"), //$NON-NLS-1$
											Messages.getString("Window.info"), 1); //$NON-NLS-1$
									btnConvert.setEnabled(true);
									Project p = new Project();
									p.setSource(tempdir);
									WindowArgumentParser.update(p);
									Main.loadFolder(tempdir);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						};
						try {
							btnConvert.setEnabled(false);
							new Thread(r).start();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							btnConvert.setEnabled(true);
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(Converter.this, "File exists.", //$NON-NLS-1$
								Messages.getString("Window.info"), 1);
						Project p = new Project();
						p.setSource(tempdir);
						WindowArgumentParser.update(p);
						Main.loadFolder(tempdir);
					}

				} else {
					JOptionPane.showMessageDialog(Converter.this, Messages.getString("Converter.ffmpegNotSelected"), //$NON-NLS-1$
							Messages.getString("Window.error"), 0); //$NON-NLS-1$
				}
			}
		});
		btnConvert.setBounds(193, 259, 113, 27);
		contentPane.add(btnConvert);
	}
}
