package hywt.mc.videogen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import hywt.mc.videogen.RefStrings.Str;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class Window {

	public static File dir;
	public static File output;
	private static JFrame frame;
	static JTextPane Info = new JTextPane();
	static JProgressBar progressBar = new JProgressBar();
	static JButton genpack = new JButton(Str.GENERATE_PACK.getStr());
	private JTextField srcpath;
	private JTextField destpath;
	protected static JTextField pnameField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JTextField txtX;
	private static JTextField txtY;
	private static JTextField txtZ;
	private static JTextField sizeX;
	private static JTextField sizeY;
	private static JRadioButton rdbtnHoverAtTarget;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static JRadioButton rdbtnPositiveZ;
	private static JRadioButton rdbtnNegativeX;
	private static JRadioButton rdbtnNegativeZ;
	private static JRadioButton rdbtnPositiveX;
	protected static JEditorPane dtrpnPackagedescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					Window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Runnable r = new Runnable(){
			public void run() {
				try {
					Main.genPack(dir, output);
				} catch (IOException e) {
					showError(e.toString());
					setEnable();
					e.printStackTrace();
				}catch(NullPointerException e) {
					showWarning(Str.NO_OUTPUT.getStr());
			    	setEnable();
			    	e.printStackTrace();
				}catch(Error e) {
					showError(e.toString());
					setEnable();
					e.printStackTrace();
				}
			}
			
		};
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(Str.TITLE.getStr());
		frame.setBounds(100, 100, 640, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(151, 100, 471, 198);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{126, 261, 0};
		gbl_panel.rowHeights = new int[]{36, 101, 29, 0, 34, 0, 0, 53, 0, 31, 28, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPackageId = new JLabel(Str.PACKAGE_ID_LABEL.getStr());
		lblPackageId.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPackageId = new GridBagConstraints();
		gbc_lblPackageId.fill = GridBagConstraints.BOTH;
		gbc_lblPackageId.insets = new Insets(0, 0, 5, 5);
		gbc_lblPackageId.gridx = 0;
		gbc_lblPackageId.gridy = 0;
		panel.add(lblPackageId, gbc_lblPackageId);
		
		pnameField = new JTextField();
		GridBagConstraints gbc_pnameField = new GridBagConstraints();
		gbc_pnameField.fill = GridBagConstraints.BOTH;
		gbc_pnameField.insets = new Insets(0, 0, 5, 0);
		gbc_pnameField.gridx = 1;
		gbc_pnameField.gridy = 0;
		panel.add(pnameField, gbc_pnameField);
		pnameField.setText(Str.PACKAGE_ID.getStr());
		pnameField.setColumns(1);
		
		JLabel lblPackageDescription = new JLabel(Str.PACKAGE_DESCRIPTION_LABEL.getStr());
		lblPackageDescription.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPackageDescription = new GridBagConstraints();
		gbc_lblPackageDescription.fill = GridBagConstraints.BOTH;
		gbc_lblPackageDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblPackageDescription.gridx = 0;
		gbc_lblPackageDescription.gridy = 1;
		panel.add(lblPackageDescription, gbc_lblPackageDescription);
		
		dtrpnPackagedescription = new JEditorPane();
		GridBagConstraints gbc_dtrpnPackagedescription = new GridBagConstraints();
		gbc_dtrpnPackagedescription.fill = GridBagConstraints.BOTH;
		gbc_dtrpnPackagedescription.insets = new Insets(0, 0, 5, 0);
		gbc_dtrpnPackagedescription.gridx = 1;
		gbc_dtrpnPackagedescription.gridy = 1;
		panel.add(dtrpnPackagedescription, gbc_dtrpnPackagedescription);
		dtrpnPackagedescription.setText(Str.PACKAGE_DESCRIPTION.getStr());
		
		JLabel lblDisplayMode = new JLabel(Str.DISPLAY_MODE.getStr());
		GridBagConstraints gbc_lblDisplayMode = new GridBagConstraints();
		gbc_lblDisplayMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisplayMode.gridx = 0;
		gbc_lblDisplayMode.gridy = 2;
		panel.add(lblDisplayMode, gbc_lblDisplayMode);
		
		JPanel dispmode = new JPanel();
		GridBagConstraints gbc_dispmode = new GridBagConstraints();
		gbc_dispmode.insets = new Insets(0, 0, 5, 0);
		gbc_dispmode.fill = GridBagConstraints.BOTH;
		gbc_dispmode.gridx = 1;
		gbc_dispmode.gridy = 2;
		panel.add(dispmode, gbc_dispmode);
		dispmode.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnHoverAtTarget = new JRadioButton(Str.DISPLAY_MODE_HOVER_AT_TARGET.getStr());
		rdbtnHoverAtTarget.setSelected(true);
		buttonGroup.add(rdbtnHoverAtTarget);
		dispmode.add(rdbtnHoverAtTarget);
		
		JRadioButton rdbtnStatic = new JRadioButton(Str.DISPLAY_MODE_STATIC.getStr());
		buttonGroup.add(rdbtnStatic);
		dispmode.add(rdbtnStatic);
		
		JLabel lblCoordinates = new JLabel(Str.COORDINATES.getStr());
		GridBagConstraints gbc_lblCoordinates = new GridBagConstraints();
		gbc_lblCoordinates.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinates.gridx = 0;
		gbc_lblCoordinates.gridy = 4;
		panel.add(lblCoordinates, gbc_lblCoordinates);
		
		JPanel coordinates = new JPanel();
		GridBagConstraints gbc_coordinates = new GridBagConstraints();
		gbc_coordinates.insets = new Insets(0, 0, 5, 0);
		gbc_coordinates.fill = GridBagConstraints.VERTICAL;
		gbc_coordinates.gridx = 1;
		gbc_coordinates.gridy = 4;
		panel.add(coordinates, gbc_coordinates);
		coordinates.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtX = new JTextField();
		txtX.setText("^");
		coordinates.add(txtX);
		txtX.setColumns(6);
		
		txtY = new JTextField();
		txtY.setText("^1.62");
		coordinates.add(txtY);
		txtY.setColumns(6);
		
		txtZ = new JTextField();
		txtZ.setText("^3");
		coordinates.add(txtZ);
		txtZ.setColumns(6);
		
		JLabel lblScreenSize = new JLabel(Str.SCREEN_SIZE.getStr());
		GridBagConstraints gbc_lblScreenSize = new GridBagConstraints();
		gbc_lblScreenSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblScreenSize.gridx = 0;
		gbc_lblScreenSize.gridy = 5;
		panel.add(lblScreenSize, gbc_lblScreenSize);
		
		JPanel screensize = new JPanel();
		GridBagConstraints gbc_screensize = new GridBagConstraints();
		gbc_screensize.insets = new Insets(0, 0, 5, 0);
		gbc_screensize.fill = GridBagConstraints.BOTH;
		gbc_screensize.gridx = 1;
		gbc_screensize.gridy = 5;
		panel.add(screensize, gbc_screensize);
		
		sizeX = new JTextField();
		sizeX.setText("3.5555555555555");
		screensize.add(sizeX);
		sizeX.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		screensize.add(lblX);
		
		sizeY = new JTextField();
		sizeY.setText("2");
		screensize.add(sizeY);
		sizeY.setColumns(10);
		
		JLabel staticmode = new JLabel(Str.STATIC_MODE_ONLY.getStr());
		GridBagConstraints gbc_staticmode = new GridBagConstraints();
		gbc_staticmode.gridwidth = 2;
		gbc_staticmode.insets = new Insets(0, 0, 5, 0);
		gbc_staticmode.gridx = 0;
		gbc_staticmode.gridy = 6;
		panel.add(staticmode, gbc_staticmode);
		
		JPanel facingMode = new JPanel();
		GridBagConstraints gbc_facingMode = new GridBagConstraints();
		gbc_facingMode.insets = new Insets(0, 0, 5, 0);
		gbc_facingMode.fill = GridBagConstraints.BOTH;
		gbc_facingMode.gridx = 1;
		gbc_facingMode.gridy = 7;
		panel.add(facingMode, gbc_facingMode);
		facingMode.setLayout(new GridLayout(2, 4, 0, 0));
		
		rdbtnPositiveX = new JRadioButton(Str.POSITIVE_X.getStr());
		rdbtnPositiveX.setSelected(true);
		buttonGroup_1.add(rdbtnPositiveX);
		facingMode.add(rdbtnPositiveX);
		
		rdbtnNegativeX = new JRadioButton(Str.NEGATIVE_X.getStr());
		buttonGroup_1.add(rdbtnNegativeX);
		facingMode.add(rdbtnNegativeX);
		
		rdbtnPositiveZ = new JRadioButton(Str.POSITIVE_Z.getStr());
		buttonGroup_1.add(rdbtnPositiveZ);
		facingMode.add(rdbtnPositiveZ);
		
		rdbtnNegativeZ = new JRadioButton(Str.NEGATIVE_Z.getStr());
		buttonGroup_1.add(rdbtnNegativeZ);
		facingMode.add(rdbtnNegativeZ);
		
		JLabel lblProjects = new JLabel("Projects");
		GridBagConstraints gbc_lblProjects = new GridBagConstraints();
		gbc_lblProjects.gridwidth = 2;
		gbc_lblProjects.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjects.gridx = 0;
		gbc_lblProjects.gridy = 9;
		panel.add(lblProjects, gbc_lblProjects);
		
		JPanel projects = new JPanel();
		GridBagConstraints gbc_projects = new GridBagConstraints();
		gbc_projects.gridwidth = 2;
		gbc_projects.fill = GridBagConstraints.BOTH;
		gbc_projects.gridx = 0;
		gbc_projects.gridy = 10;
		panel.add(projects, gbc_projects);
		
		JButton btnSaveSettings = new JButton(Str.SAVE_SETTINGS.getStr());
		btnSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		projects.add(btnSaveSettings);
		
		JButton btnLoadSettings = new JButton(Str.LOAD_SETTINGS.getStr());
		projects.add(btnLoadSettings);

		srcpath = new JTextField();
		srcpath.setBounds(57, 11, 432, 22);
		frame.getContentPane().add(srcpath);
		srcpath.setColumns(10);

		destpath = new JTextField();
		destpath.setBounds(57, 44, 432, 21);
		frame.getContentPane().add(destpath);
		destpath.setColumns(10);

		JButton selectFolder = new JButton(Str.SELECT_FOLDER.getStr());
		selectFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					dir = fc.getSelectedFile();
					srcpath.setText(dir.getPath());
					Main.loadFolder(dir);
				}
			}
		});
		selectFolder.setBounds(501, 10, 121, 23);
		frame.getContentPane().add(selectFolder);


		genpack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				genpack.setEnabled(false);
				new Thread(r).start();
			}
		});
		genpack.setBounds(248, 310, 131, 23);
		frame.getContentPane().add(genpack);

		JButton seldestpath = new JButton(Str.BROWSE.getStr());
		seldestpath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser(".");
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(false);
				int returnVal = filechooser.showSaveDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					output = filechooser.getSelectedFile();
					destpath.setText(output.getPath());
				}
			}
		});
		seldestpath.setBounds(501, 43, 121, 22);
		frame.getContentPane().add(seldestpath);

		JLabel lblSource = new JLabel(Str.SOURCE.getStr());
		lblSource.setBounds(10, 14, 54, 15);
		frame.getContentPane().add(lblSource);

		JLabel lblOutput = new JLabel(Str.OUTPUT.getStr());
		lblOutput.setBounds(10, 47, 54, 15);
		frame.getContentPane().add(lblOutput);
		progressBar.setMaximum(1000);
		
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 345, 612, 14);
		frame.getContentPane().add(progressBar);
		Info.setBounds(10, 100, 131, 198);
		frame.getContentPane().add(Info);
		Info.setEditable(false);
		
		JLabel lblInformation = new JLabel(Str.INFORMATION.getStr());
		lblInformation.setBounds(10, 75, 131, 15);
		frame.getContentPane().add(lblInformation);
		
		JLabel lblOutputSettings = new JLabel(Str.OUTPUT_SETTINGS.getStr());
		lblOutputSettings.setBounds(151, 75, 463, 15);
		frame.getContentPane().add(lblOutputSettings);

	}

	public static void showError(String msg) {
		JOptionPane.showMessageDialog(frame, msg, Str.ERROR.getStr(), 0);
	}

	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(frame, msg, Str.INFO.getStr(), 1);
	}
	
	public static void showWarning(String msg) {
		JOptionPane.showMessageDialog(frame, msg, Str.WARNING.getStr(), 2);
	}

	public static void dirInfo(int frames, long size) {
		String duration = Main.formatSeconds(frames / 20);
		Info.setText(Str.FRAMES.getStr()+" "+ frames + "\n"+Str.SIZE.getStr()+" " + Main.readableFileSize(size) + "\n"+Str.LENGTH.getStr()+" " + duration);
	}
	public static void setProgress(int progress,int max) {
		progressBar.setValue((int) (((float)(progress)/max)*1000));
		progressBar.setString(progress+" / "+max);
	}
	public static void setEnable() {
		genpack.setEnabled(true);
	}
	public static JTextField getTxtX() {
		return txtX;
	}
	public static JTextField getTxtY() {
		return txtY;
	}
	public static JTextField getTxtZ() {
		return txtZ;
	}
	public static JTextField getSizeX() {
		return sizeX;
	}
	public static JTextField getSizeY() {
		return sizeY;
	}
	public static JRadioButton getRdbtnHoverAtTarget() {
		return rdbtnHoverAtTarget;
	}
	public static JRadioButton getRdbtnPositiveZ() {
		return rdbtnPositiveZ;
	}
	public static JRadioButton getRdbtnNegativeX() {
		return rdbtnNegativeX;
	}
	public static JRadioButton getRdbtnNegativeZ() {
		return rdbtnNegativeZ;
	}
	public static JRadioButton getRdbtnPositiveX() {
		return rdbtnPositiveX;
	}
	protected JEditorPane getDtrpnPackagedescription() {
		return dtrpnPackagedescription;
	}
	protected JTextField getPnameField() {
		return pnameField;
	}
}
