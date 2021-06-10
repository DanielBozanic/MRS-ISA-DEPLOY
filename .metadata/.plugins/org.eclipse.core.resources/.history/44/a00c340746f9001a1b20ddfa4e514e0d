package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.Constants;
import model.Grupa;
import model.Pojedinacanizvodjac;
import model.Pol;
import model.Sesija;
import model.Zanr;

public class RegistracijaIzvodjaca extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUmetnickoIme;
	private JTextField txtIme;
	private JTextField txtPrezime;
	//@SuppressWarnings("rawtypes")
	private ComboZanr cmbZanr;
	private JPanel pnlGrupa;
	private JDatePickerImpl dtDob;
	private JDatePickerImpl dtDod;
	private JDatePickerImpl dtDor;
	private JDatePickerImpl dtDof;
	private JSpinner spnBrojClanova;
	private SpringLayout sl_dtDob;
	private SpringLayout sl_dtDod;
	private SpringLayout sl_dtDof;
	private SpringLayout sl_dtDor;
	public Sesija sesija;
	private JRadioButton rbPojedinacniIzvodjac;
	private JRadioButton rbGrupa;
	private JTextArea txtOpis;
	private JRadioButton rbMuski;
	private JRadioButton rbtZenski;
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistracijaIzvodjaca(Sesija sesija) throws Exception {
		this.setSize(392, 492);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBackground(Color.BLACK);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		
		this.sesija = sesija;
		setTitle("Registracija izvodjaca");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Umetnicko ime:");
		lblNewLabel.setBounds(20, 16, 95, 14);
		getContentPane().add(lblNewLabel);
		
		txtUmetnickoIme = new JTextField();
		txtUmetnickoIme.setBounds(103, 8, 270, 30);
		getContentPane().add(txtUmetnickoIme);
		txtUmetnickoIme.setColumns(10);
		
		
		JPanel pnlPojedinacniIzvodjac = new JPanel();
		rbPojedinacniIzvodjac = new JRadioButton("Pojedinacni izvodjac");
		rbPojedinacniIzvodjac.setSelected(true);
		rbPojedinacniIzvodjac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPojedinacniIzvodjac.setVisible(true);
				pnlGrupa.setVisible(false);
				rbGrupa.setSelected(false);	
			}
		});
		rbPojedinacniIzvodjac.setBounds(20, 48, 151, 23);
		getContentPane().add(rbPojedinacniIzvodjac);
		
		rbGrupa = new JRadioButton("Grupa");
		rbGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGrupa.setVisible(true);
				pnlPojedinacniIzvodjac.setVisible(false);
				rbPojedinacniIzvodjac.setSelected(false);
			}
		});
		rbGrupa.setBounds(177, 48, 109, 23);
		getContentPane().add(rbGrupa);
		
		pnlPojedinacniIzvodjac.setBounds(10, 78, 363, 295);
		getContentPane().add(pnlPojedinacniIzvodjac);
		pnlPojedinacniIzvodjac.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(10, 11, 27, 14);
		pnlPojedinacniIzvodjac.add(lblIme);
		
		txtIme = new JTextField();
		txtIme.setBounds(178, 6, 175, 25);
		pnlPojedinacniIzvodjac.add(txtIme);
		txtIme.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(10, 57, 48, 14);
		pnlPojedinacniIzvodjac.add(lblPrezime);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(178, 52, 175, 25);
		pnlPojedinacniIzvodjac.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDob = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDob = new SpringLayout();
		sl_dtDob.putConstraint(SpringLayout.NORTH, dtDob.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDob);
		sl_dtDob.putConstraint(SpringLayout.WEST, dtDob.getJFormattedTextField(), 33, SpringLayout.WEST, dtDob);
		sl_dtDob.putConstraint(SpringLayout.EAST, dtDob.getJFormattedTextField(), 211, SpringLayout.WEST, dtDob);
		sl_dtDob = (SpringLayout) dtDob.getLayout();
		dtDob.setBounds(178, 100, 175, 25);
		pnlPojedinacniIzvodjac.add(dtDob);
		
		JLabel lblNewLabel_1 = new JLabel("Datum rodjenja");
		lblNewLabel_1.setBounds(10, 100, 97, 25);
		pnlPojedinacniIzvodjac.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Datum smrti");
		lblNewLabel_2.setBounds(10, 153, 75, 25);
		pnlPojedinacniIzvodjac.add(lblNewLabel_2);
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl date2Panel = new JDatePanelImpl(model2, p);
		dtDod = new JDatePickerImpl(date2Panel, new DataLabelFormatter());
		sl_dtDod = new SpringLayout();
		sl_dtDod.putConstraint(SpringLayout.NORTH, dtDod.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDod);
		sl_dtDod.putConstraint(SpringLayout.WEST, dtDod.getJFormattedTextField(), 33, SpringLayout.WEST, dtDod);
		sl_dtDod.putConstraint(SpringLayout.EAST, dtDod.getJFormattedTextField(), 211, SpringLayout.WEST, dtDod);
		sl_dtDod = (SpringLayout) dtDod.getLayout();
		dtDod.setBounds(178, 153, 175, 25);
		pnlPojedinacniIzvodjac.add(dtDod);
		
		pnlGrupa = new JPanel();
		pnlGrupa.setSize(363, 295);
		pnlGrupa.setLocation(403, 78);
		pnlGrupa.setLayout(null);
		getContentPane().add(pnlGrupa);
		
		JLabel lblBrojClanova = new JLabel("Broj clanova");
		lblBrojClanova.setBounds(10, 84, 94, 14);
		pnlGrupa.add(lblBrojClanova);
		
		spnBrojClanova = new JSpinner();
		spnBrojClanova.setModel(new SpinnerNumberModel(2, 2, 200, 1));
		spnBrojClanova.setBounds(164, 79, 51, 25);
		pnlGrupa.add(spnBrojClanova);
		
		JLabel lblDatumOsnivanja = new JLabel("Datum osnivanja");
		lblDatumOsnivanja.setBounds(10, 131, 94, 25);
		pnlGrupa.add(lblDatumOsnivanja);
		
		UtilDateModel model3 = new UtilDateModel();
		JDatePanelImpl date3Panel = new JDatePanelImpl(model3, p);
		dtDof = new JDatePickerImpl(date3Panel, new DataLabelFormatter());
		sl_dtDof = new SpringLayout();
		sl_dtDof.putConstraint(SpringLayout.NORTH, dtDof.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDof);
		sl_dtDof.putConstraint(SpringLayout.WEST, dtDof.getJFormattedTextField(), 33, SpringLayout.WEST, dtDof);
		sl_dtDof.putConstraint(SpringLayout.EAST, dtDof.getJFormattedTextField(), 211, SpringLayout.WEST, dtDof);
		sl_dtDof = (SpringLayout) dtDof.getLayout();
		dtDof.setBounds(164, 131, 189, 25);
		pnlGrupa.add(dtDof);
		
		UtilDateModel model4 = new UtilDateModel();
		JDatePanelImpl date4Panel = new JDatePanelImpl(model4, p);
		dtDor = new JDatePickerImpl(date4Panel, new DataLabelFormatter());
		sl_dtDor = new SpringLayout();
		sl_dtDor.putConstraint(SpringLayout.NORTH, dtDor.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDor);
		sl_dtDor.putConstraint(SpringLayout.WEST, dtDor.getJFormattedTextField(), 33, SpringLayout.WEST, dtDor);
		sl_dtDor.putConstraint(SpringLayout.EAST, dtDor.getJFormattedTextField(), 211, SpringLayout.WEST, dtDor);
		sl_dtDor = (SpringLayout) dtDor.getLayout();
		dtDor.setBounds(164, 191, 189, 25);
		pnlGrupa.add(dtDor);
		
		JLabel lblDatumRaspada = new JLabel("Datum raspada");
		lblDatumRaspada.setBounds(10, 191, 86, 25);
		pnlGrupa.add(lblDatumRaspada);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 259, 48, 14);
		pnlPojedinacniIzvodjac.add(lblOpis);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setBounds(10, 205, 48, 14);
		pnlPojedinacniIzvodjac.add(lblPol);
		
		rbMuski = new JRadioButton("Muski");
		rbMuski.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtZenski.setSelected(false);
			}
		});
		rbMuski.setSelected(true);
		rbMuski.setBounds(178, 201, 61, 23);
		pnlPojedinacniIzvodjac.add(rbMuski);
		
		rbtZenski = new JRadioButton("Zenski");
		rbtZenski.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbMuski.setSelected(false);
			}
		});
		rbtZenski.setBounds(286, 201, 67, 23);
		pnlPojedinacniIzvodjac.add(rbtZenski);
		
		txtOpis = new JTextArea();
		txtOpis.setColumns(30);
		txtOpis.setWrapStyleWord(true);
		txtOpis.setRows(4);
		txtOpis.setLineWrap(true);
		txtOpis.setBounds(178, 245, 175, 39);
		pnlPojedinacniIzvodjac.add(txtOpis);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		txtOpis.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			
		JButton btnRegistruj = new JButton("Registruj");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registruj();
			}
		});
		btnRegistruj.setBounds(284, 409, 89, 30);
		getContentPane().add(btnRegistruj);
		
		JLabel lblZanr = new JLabel("Zanr:");
		lblZanr.setBounds(20, 394, 48, 14);
		getContentPane().add(lblZanr);
		
		cmbZanr =  new ComboZanr();
		cmbZanr.setBounds(55, 385, 180, 30);
		cmbZanr.kreirajSadrzaj(sesija.getZanroviMenadzer().getZanrovi());
		getContentPane().add(cmbZanr);

		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				pnlGrupa.setBounds(pnlPojedinacniIzvodjac.getBounds().x, pnlPojedinacniIzvodjac.getBounds().y, pnlGrupa.getBounds().width, pnlGrupa.getBounds().height);
			}
		});
		
		pnlGrupa.setVisible(false);;
	}
	
	private void registruj() {
		if (txtUmetnickoIme.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Umetnicko ime je obavezno polje.");
			return;
		}
		ArrayList<Zanr> zanrovi =  (ArrayList<Zanr>) cmbZanr.vratiSelektovaneZanrove();
		String msg;
		if (rbPojedinacniIzvodjac.isSelected())
		{
			try {
				msg = validirajPojedinacnogIzvodjaca();
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
					return;
				}
				if (!dtDod.getJFormattedTextField().getText().isEmpty()) {
					registrujIzvodjaca(txtUmetnickoIme.getText(), zanrovi, txtIme.getText(), txtPrezime.getText(), 
							dtDob.getJFormattedTextField().getText(), 
							dtDod.getJFormattedTextField().getText(), 
							rbMuski.isSelected() ? Pol.muski.name() : Pol.zenski.name(), txtOpis.getText());
				}
				else {
					registrujIzvodjaca(txtUmetnickoIme.getText(),zanrovi, txtIme.getText(), txtPrezime.getText(), 
							dtDob.getJFormattedTextField().getText(), null, 
							rbMuski.isSelected() ? Pol.muski.name() : Pol.zenski.name(), txtOpis.getText());
				}
			} 
			catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else
		{
			
			try {
				msg = validirajGrupu();
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
					return;
				}
					
				if (!dtDor.getJFormattedTextField().getText().isEmpty()) {
					registrujGrupu(txtUmetnickoIme.getText(), zanrovi, 
							((Integer)spnBrojClanova.getValue()), dtDof.getJFormattedTextField().getText(), 
							dtDor.getJFormattedTextField().getText());
				}
				else {
					registrujGrupu(txtUmetnickoIme.getText(), zanrovi, 
							((Integer)spnBrojClanova.getValue()), dtDof.getJFormattedTextField().getText(), 
							null);
				}
			} 
			catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void registrujIzvodjaca(String umetnickoIme, ArrayList<Zanr> zanr, String ime, String prezime, String dob, String dod, String pol, String opis) throws ParseException
	{ 
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String dob2 = sdf2.format(sdf1.parse(dob));
		Pol p = Pol.valueOf(pol);
		Pojedinacanizvodjac pi = null;
		if (dod != null) { 
			
			String dod2 = sdf2.format(sdf1.parse(dod));
			pi = new Pojedinacanizvodjac(false, umetnickoIme, zanr, true, ime, prezime, Constants.NATASIN_FORMAT_ZA_DATUM.parse(dob2),
			Constants.NATASIN_FORMAT_ZA_DATUM.parse(dod2), opis, p);
		}
		else {


			pi = new Pojedinacanizvodjac(false, umetnickoIme, zanr, true, ime, prezime, Constants.NATASIN_FORMAT_ZA_DATUM.parse(dob2),
					null, opis, p);
		}
			if (!sesija.addUmetnici(pi))
				JOptionPane.showMessageDialog(null, "Izvodjac vec postoji.");
			
		JOptionPane.showMessageDialog(null, "Registrovan je pojedinacan izvodjac " + umetnickoIme);
	}
	
	private void registrujGrupu(String umetnickoIme, ArrayList<Zanr> zanr, int brojClanova, String dof, String dor) throws ParseException
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String dof2 = sdf2.format(sdf1.parse(dof));
		Grupa g = null;
		if (dor != null) {
			
			String dor2 = sdf2.format(sdf1.parse(dor));
			g = new Grupa(false,umetnickoIme, zanr, true,  brojClanova, Constants.NATASIN_FORMAT_ZA_DATUM.parse(dof2), 
			Constants.NATASIN_FORMAT_ZA_DATUM.parse(dor2));

		}
		else {

			g = new Grupa(false, umetnickoIme, zanr, true,  brojClanova, Constants.NATASIN_FORMAT_ZA_DATUM.parse(dof2),
					null);
		}
		if (!sesija.addGrupe(g))
			JOptionPane.showMessageDialog(null, "Grupa vec postoji");
		
		JOptionPane.showMessageDialog(null, "Registrovana je grupa " + umetnickoIme);
	}
	
	private String validirajPojedinacnogIzvodjaca() {
		if (txtIme.getText().isEmpty())
		{
			return "Ime je obavezno polje.";
		}
		if (txtPrezime.getText().isEmpty())
		{
			return "Prezime je obavezno polje.";
		}
		if (dtDob.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati datum rodjenja.";
		}
		return "";
	}
	
	private String validirajGrupu() {
		if ((Integer)spnBrojClanova.getValue() < 2)
		{
			return "Broj clanova mora biti veci od jedan.";
		}
		if (dtDof.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati datum formiranja.";
		}
		return "";
	}
}
