package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JComboBox;

public class NewRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField DescriptionTextField;
	private JTextField CityTextField;
	private JCheckBox chckbxMonte;
	private JCheckBox chckbxPlaya;
	private JCheckBox chckbxUrbano;
	private JCheckBox chckbxRural;
	private JLabel feedBack;
	private JLabel lblDormitorios;
	private JLabel lblBanos;
	private JLabel lblCocinas;
	private JLabel lblComedores;
	private JLabel lblPlazasDeGaraje;
	private JComboBox<Integer> comboBox_Banos;
	private JComboBox<Integer> comboBox_Cocinas;
	private JComboBox<Integer> comboBox_Dormitorios;
	private JComboBox<Integer> comboBox_Garaje;
	private JComboBox<Integer> comboBox_Comedores;
	private DefaultComboBoxModel<Integer> banosinf = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> cocinasinf = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> dormitoriosinf = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> pgarajesinf = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> comedoresinf = new DefaultComboBoxModel<>();
	private JTextField textField;
	private JButton btnAdir;
	private JLabel lblFotoCasa;
	private String imgpath = "";




	

	/**
	 * Create the frame.
	 */
	public NewRuralHouseGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnAdir = new JButton("Anadir Imagen");
		btnAdir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int rec = fc.showOpenDialog(contentPane);
				if(rec == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					textField.setText(file.getAbsolutePath());
					ImageIcon icon = new ImageIcon(file.getAbsolutePath());
					Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFotoCasa.getWidth(),
							lblFotoCasa.getHeight(),Image.SCALE_DEFAULT));
					imgpath = "resources/Assets/"+file.getName();
					lblFotoCasa.setIcon(icono);
					
					}
				}
			
		});
		btnAdir.setBounds(508, 7, 133, 23);
		contentPane.add(btnAdir);
		
		JLabel DescriptionLabel = new JLabel("Direccion");
		DescriptionLabel.setForeground(Color.WHITE);
		DescriptionLabel.setBounds(10, 11, 74, 14);
		contentPane.add(DescriptionLabel);
		
		JLabel CityLabel = new JLabel("City");
		CityLabel.setForeground(Color.WHITE);
		CityLabel.setBounds(10, 45, 46, 14);
		contentPane.add(CityLabel);
		
		DescriptionTextField = new JTextField();
		DescriptionTextField.setBounds(94, 8, 133, 20);
		contentPane.add(DescriptionTextField);
		DescriptionTextField.setColumns(10);
		
		CityTextField = new JTextField();
		CityTextField.setBounds(94, 42, 133, 20);
		contentPane.add(CityTextField);
		CityTextField.setColumns(10);
		
		JButton CreateButton = new JButton("CREATE");
		CreateButton.setBounds(168, 205, 119, 23);
		CreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//description,city,numBanos,numDormitorios,numCocinas,numComedores,numPlazasGaraje,fl
				ApplicationFacadeInterfaceWS facades = MainGUI.getBusinessLogic();
				String des = DescriptionTextField.getText();
				String city = CityTextField.getText();
				Vector<String> filters = new Vector<String>();
				if (chckbxMonte.isSelected()){filters.addElement("Monte");} if(chckbxPlaya.isSelected()){filters.addElement("Playa");}
				if(chckbxRural.isSelected()){filters.addElement("Rural");} if(chckbxUrbano.isSelected()){filters.addElement("Urbano");}
				facades.createRuralHouse(des, city,(Integer)banosinf.getSelectedItem(),(Integer)dormitoriosinf.getSelectedItem()
						,(Integer)cocinasinf.getSelectedItem(),(Integer)comedoresinf.getSelectedItem(),(Integer)pgarajesinf.getSelectedItem(),
						filters,imgpath);
				JOptionPane.showMessageDialog(contentPane, "Casa creada");
				dispose();
				System.out.println(DescriptionTextField.getText());
				
				
			}
		});
		contentPane.add(CreateButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(168, 227, 119, 20);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		chckbxMonte = new JCheckBox("Monte");
		chckbxMonte.setBounds(10, 152, 97, 23);
		contentPane.add(chckbxMonte);
		
		chckbxPlaya = new JCheckBox("Playa");
		chckbxPlaya.setBounds(10, 178, 97, 23);
		contentPane.add(chckbxPlaya);
		
		chckbxUrbano = new JCheckBox("Urbano");
		chckbxUrbano.setBounds(10, 201, 97, 23);
		contentPane.add(chckbxUrbano);
		
		chckbxRural = new JCheckBox("Rural");
		chckbxRural.setBounds(10, 226, 97, 23);
		contentPane.add(chckbxRural);
		
		feedBack = new JLabel("");
		feedBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		feedBack.setForeground(Color.RED);
		feedBack.setHorizontalAlignment(SwingConstants.CENTER);
		feedBack.setBounds(39, 161, 364, 33);
		contentPane.add(feedBack);
		
		lblDormitorios = new JLabel("Dormitorios");
		lblDormitorios.setForeground(Color.WHITE);
		lblDormitorios.setBounds(10, 71, 68, 14);
		contentPane.add(lblDormitorios);
		
		lblBanos = new JLabel("Ba\u00F1os");
		lblBanos.setForeground(Color.WHITE);
		lblBanos.setBounds(10, 103, 46, 14);
		contentPane.add(lblBanos);
		
		lblCocinas = new JLabel("Cocinas");
		lblCocinas.setForeground(Color.WHITE);
		lblCocinas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCocinas.setBounds(138, 73, 46, 14);
		contentPane.add(lblCocinas);
		
		lblComedores = new JLabel("Comedores");
		lblComedores.setForeground(Color.WHITE);
		lblComedores.setBounds(138, 102, 74, 14);
		contentPane.add(lblComedores);
		
		lblPlazasDeGaraje = new JLabel("Plazas de garaje");
		lblPlazasDeGaraje.setForeground(Color.WHITE);
		lblPlazasDeGaraje.setBounds(10, 131, 97, 14);
		contentPane.add(lblPlazasDeGaraje);
		banosinf.addElement(2);
		banosinf.addElement(3);
		banosinf.addElement(4);
		banosinf.addElement(5);
		comboBox_Banos = new JComboBox();
		comboBox_Banos.setBounds(88, 100, 34, 20);
		comboBox_Banos.setModel(banosinf);
		contentPane.add(comboBox_Banos);
		
		cocinasinf.addElement(1);
		cocinasinf.addElement(2);
		cocinasinf.addElement(3);
		cocinasinf.addElement(4);
		comboBox_Cocinas = new JComboBox();
		comboBox_Cocinas.setBounds(226, 70, 46, 20);
		comboBox_Cocinas.setModel(cocinasinf);
		contentPane.add(comboBox_Cocinas);
		
		dormitoriosinf.addElement(3);
		dormitoriosinf.addElement(4);
		dormitoriosinf.addElement(5);
		dormitoriosinf.addElement(6);
		dormitoriosinf.addElement(7);
		dormitoriosinf.addElement(8);
		comboBox_Dormitorios = new JComboBox();
		comboBox_Dormitorios.setBounds(88, 73, 34, 20);
		comboBox_Dormitorios.setModel(dormitoriosinf);
		contentPane.add(comboBox_Dormitorios);
		
		pgarajesinf.addElement(0);
		pgarajesinf.addElement(1);
		pgarajesinf.addElement(2);
		pgarajesinf.addElement(3);
		comboBox_Garaje = new JComboBox();
		comboBox_Garaje.setBounds(109, 130, 39, 20);
		contentPane.add(comboBox_Garaje);
		comboBox_Garaje.setModel(pgarajesinf);
		
		comboBox_Comedores = new JComboBox();
		comboBox_Comedores.setBounds(226, 100, 46, 20);
		comboBox_Comedores.setModel(comedoresinf);
		contentPane.add(comboBox_Comedores);
		
		textField = new JTextField();
		textField.setBounds(329, 8, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		lblFotoCasa = new JLabel();
		lblFotoCasa.setBounds(311, 45, 333, 213);
		contentPane.add(lblFotoCasa);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 644, 258);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
		
		
		comedoresinf.addElement(0);
		comedoresinf.addElement(1);
		comedoresinf.addElement(2);
	}
}
