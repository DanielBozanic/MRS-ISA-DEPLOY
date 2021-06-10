package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.IzvodjacMenadzer;
import controler.MuzickiSadrzajMenadzer;
import controler.ZanroviMenadzer;
import model.Album;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Sesija;
import model.Urednik;
import model.Zanr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class RegistarcijaAlbuma extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtNaziv;
	private JTable pesme;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbIzvodjac;
	private SpringLayout sl_dtDor;
	private JDatePickerImpl dtDor;
	private Sesija sesija;
	private JButton btnDodajPesmu;
	private String title;
	private ComboZanr cmbZanr;
	private JTextField txtOpis;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistarcijaAlbuma(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setResizable(false);
		setTitle(title);
		getContentPane().setLayout(null);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setBounds(24, 21, 48, 14);
		getContentPane().add(lblNaziv);
		
		txtNaziv = new JTextField();
		txtNaziv.setBounds(24, 36, 627, 35);
		getContentPane().add(txtNaziv);
		txtNaziv.setColumns(10);
		
		JLabel lblPesme = new JLabel("Pesme:");
		lblPesme.setBounds(24, 279, 48, 14);
		getContentPane().add(lblPesme);
		
		pesme = new JTable();
		pesme.setBorder(null);
		pesme.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pesme.getTableHeader().setReorderingAllowed(false);
		pesme.getTableHeader().setResizingAllowed(false);
		pesme.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(pesme);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(24, 294, 627, 139);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		pesme.setFillsViewportHeight(true);
		
		JLabel lblDatumRegistracije = new JLabel("Datum registracije:");
		lblDatumRegistracije.setBounds(24, 219, 107, 20);
		getContentPane().add(lblDatumRegistracije);
		
		JButton btnRegistruj = new JButton("Registruj");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrujAlbum();
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistruj.setBounds(562, 444, 89, 23);
		getContentPane().add(btnRegistruj);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDor = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDor = new SpringLayout();
		sl_dtDor.putConstraint(SpringLayout.NORTH, dtDor.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDor);
		sl_dtDor.putConstraint(SpringLayout.WEST, dtDor.getJFormattedTextField(), 33, SpringLayout.WEST, dtDor);
		sl_dtDor.putConstraint(SpringLayout.EAST, dtDor.getJFormattedTextField(), 211, SpringLayout.WEST, dtDor);
		sl_dtDor = (SpringLayout) dtDor.getLayout();
		dtDor.setBounds(24, 234, 274, 25);
		getContentPane().add(dtDor);
		
		JLabel lblIzvodjaci = new JLabel("Izvodjac:");
		lblIzvodjaci.setBounds(24, 172, 48, 14);
		getContentPane().add(lblIzvodjaci);
		
		cmbIzvodjac = new JComboBox();
		cmbIzvodjac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cmbIzvodjac.getSelectedIndex() == -1) {
						btnDodajPesmu.setEnabled(false);
						return;
					}
					ucitajPesme((String)cmbIzvodjac.getSelectedItem());
					btnDodajPesmu.setEnabled(true);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbIzvodjac.setMaximumRowCount(20);
		cmbIzvodjac.setBounds(24, 186, 338, 22);
		getContentPane().add(cmbIzvodjac);
		
		btnDodajPesmu = new JButton("Dodaj pesmu");
		btnDodajPesmu.setEnabled(false);
		btnDodajPesmu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dodajPesmu((String)cmbIzvodjac.getSelectedItem());
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDodajPesmu.setBounds(24, 444, 107, 23);
		getContentPane().add(btnDodajPesmu);
		
		cmbZanr = new ComboZanr();
		cmbZanr.setBounds(464, 186, 187, 97);
		cmbZanr.kreirajSadrzaj(sesija.getZanroviMenadzer().getZanrovi());
		getContentPane().add(cmbZanr);
		
		JLabel lblZanrovi = new JLabel("Zanrovi");
		lblZanrovi.setBounds(544, 172, 54, 14);
		getContentPane().add(lblZanrovi);
		
		txtOpis = new JTextField();
		txtOpis.setBounds(24, 109, 627, 35);
		getContentPane().add(txtOpis);
		txtOpis.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(24, 93, 48, 14);
		getContentPane().add(lblOpis);
		
	
		
		IzvodjacMenadzer im = sesija.getIzvodjacMenadzer();
		for (Izvodjac i : im.getSvi())
		{
			if (i.isOdobrenost())
				cmbIzvodjac.addItem(i.getUmetnickoIme());
		}
		
		setVisible(true);
	}
	
	private void ucitajPesme(String umetnickoIme) throws Exception {
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		Izvodjac i = sesija.getIzvodjac(umetnickoIme);
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela(i);
		pesme.setModel(tmw);
	}
	
	private void registrujAlbum() throws ParseException {
		String msg = validiraj();
		if (!msg.equals("")) {
			JOptionPane.showMessageDialog(null, msg);
			return;
		}
		String naziv = txtNaziv.getText();
		String opis = txtOpis.getText();
		ArrayList<MuzickoDelo> dela = new ArrayList<MuzickoDelo>();
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		int[] redovi = pesme.getSelectedRows();
		for (int i = 0; i < redovi.length; i++) {
			for (MuzickoDelo m : mdm.getMuzickaDela()) {
				if (pesme.getValueAt(redovi[i], 0).equals(m.getNaslov())) {
					dela.add(m);
				}
			}
		}
		String txtRegistracije = dtDor.getJFormattedTextField().getText();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String txtRegistracije2 = sdf2.format(sdf1.parse(txtRegistracije));
		Date danRegistracije = new SimpleDateFormat("dd.MM.yyyy.").parse(txtRegistracije2);
		Izvodjac izvodjac = sesija.getIzvodjac((String)cmbIzvodjac.getSelectedItem());
		ArrayList<Zanr> zanrovi =  (ArrayList<Zanr>) cmbZanr.vratiSelektovaneZanrove();
		Album noviAlbum = new Album(naziv, opis, danRegistracije,  izvodjac, (Urednik)Sesija.getTrenutniKorisnik(), true, zanrovi, dela, false); // NA OVO SE VRATITI
		//Album noviAlbum = new Album(txtRegistracije2, txtRegistracije2, danRegistracije, izvodjac, urednik, rootPaneCheckingEnabled);
		sesija.getMuzickiSadrzajMenadzer().getMuzickiSadrzaj().add(noviAlbum);
		JOptionPane.showMessageDialog(null, "Album je registrovan.");
	}
	
	private String validiraj() {
		if (txtNaziv.getText().isEmpty()) {
			return "Naziv je obavezno polje.";
		}
		if (cmbIzvodjac.getSelectedIndex() == -1) {
			return "Morate odabrati izvodjaca.";
		}
		if (dtDor.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati datum registracije.";
		}
		if (pesme.getSelectionModel().isSelectionEmpty()) {
			return "Morate odabrati pesmu.";
		}
		return "";
	}
	
	private void dodajPesmu(String umetnickoIme) throws Exception {
		new DodajMuzickoDelo(sesija, "Dodavanje muzickog dela", 422, 422, sesija.getIzvodjac(umetnickoIme), 0);
		ucitajPesme(umetnickoIme);
	}
}
