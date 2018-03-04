package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Panstwo{
	private IntegerProperty idPanstwa;
	private StringProperty nazwaPanstwa;
	private DoubleProperty powierzchniaPanstwa;
	private IntegerProperty liczbaLudnosci;
	private StringProperty jednostkaMonetarna;
	private StringProperty ustroj;
	private StringProperty infoDodatkowe;
	private StringProperty nazwaKontynentu;

	public Panstwo(Integer idPanstwa, String nazwaPanstwa, 
			Double powierzchniaPanstwa, Integer liczbaLudnosci, 
			String jednostkaMonetarna, String ustroj, String infoDodatkowe,
			String nazwaKontynentu){
		this.idPanstwa = new SimpleIntegerProperty(idPanstwa);
		this.nazwaPanstwa = new SimpleStringProperty(nazwaPanstwa);
		this.powierzchniaPanstwa = new SimpleDoubleProperty(powierzchniaPanstwa);
		this.liczbaLudnosci = new SimpleIntegerProperty(liczbaLudnosci);
		this.jednostkaMonetarna = new SimpleStringProperty(jednostkaMonetarna);
		this.ustroj = new SimpleStringProperty(ustroj);
		this.infoDodatkowe = new SimpleStringProperty(infoDodatkowe);
		this.nazwaKontynentu = new SimpleStringProperty(nazwaKontynentu);
	}

	public String getNazwaKontynentu() {
		return nazwaKontynentu.get();
	}

	public void setNazwaKontynentu(String nazwaKontynentu) {
		this.nazwaKontynentu = new SimpleStringProperty(nazwaKontynentu);
	}

	public Integer getIdPanstwa(){
		return idPanstwa.get();
	}

	public void setIdPanstwa(Integer idPanstwa){
		this.idPanstwa = new SimpleIntegerProperty(idPanstwa);
	}

	public String getNazwaPanstwa(){
		return nazwaPanstwa.get();
	}

	public void setNazwaPanstwa(String nazwaPanstwa){
		this.nazwaPanstwa = new SimpleStringProperty(nazwaPanstwa);
	}

	public Double getPowierzchniaPanstwa(){
		return powierzchniaPanstwa.get();
	}

	public void setPowierzchniaPanstwa(Double powierzchniaPanstwa){
		this.powierzchniaPanstwa = new SimpleDoubleProperty(powierzchniaPanstwa);
	}

	public Integer getLiczbaLudnosci(){
		return liczbaLudnosci.get();
	}

	public void setLiczbaLudnosci(Integer liczbaLudnosci){
		this.liczbaLudnosci = new SimpleIntegerProperty(liczbaLudnosci);
	}

	public String getJednostkaMonetarna(){
		return jednostkaMonetarna.get();
	}

	public void setJednostkaMonetarna(String jednostkaMonetarna){
		this.jednostkaMonetarna = new SimpleStringProperty(jednostkaMonetarna);
	}

	public String getUstroj(){
		return ustroj.get();
	}

	public void setUstroj(String ustroj){
		this.ustroj = new SimpleStringProperty(ustroj);
	}

	public String getInfoDodatkowe(){
		return infoDodatkowe.get();
	}

	public void setInfoDodatkowe(String infoDodatkowe){
		this.infoDodatkowe = new SimpleStringProperty(infoDodatkowe);
	}

	public IntegerProperty idPanstwaProperty(){
		return idPanstwa;
	}

	public StringProperty nazwaPanstwaProperty(){
		return nazwaPanstwa;
	}

	public DoubleProperty powierzchniaPanstwaProperty(){
		return powierzchniaPanstwa;
	}

	public IntegerProperty liczbaLudnosciProperty(){
		return liczbaLudnosci;
	}

	public StringProperty jednostkaMonetarnaProperty(){
		return jednostkaMonetarna;
	}

	public StringProperty ustrojProperty(){
		return ustroj;
	}

	public StringProperty infoDodatkoweProperty(){
		return infoDodatkowe;
	}
	
	public StringProperty nazwaKontynentuProperty(){
		return nazwaKontynentu;
	}

	public static void getList (Connection connection, ObservableList<Panstwo> lista) {
		try {
			final String query = "SELECT A.*, C.Nazwa_Kontynentu"
				+ " FROM swiewio1.Panstwo A"
				+ " Left JOIN swiewio1.Panstwo_Na_Kontynencie B"
				+ " ON (A.Id_Panstwa = B.Id_Panstwa)"
				+ " LEFT JOIN swiewio1.Kontynent C"
				+ " ON (B.Id_Kontynentu = C.Id_Kontynentu);";
			try (Statement st = connection.createStatement()) {
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					lista.add(
						new Panstwo(
							rs.getInt("Id_Panstwa"),
							rs.getString("Nazwa_Panstwa"),
							rs.getDouble("Powierzchnia_Panstwa"),
							rs.getInt("Liczba_Ludnosci"),
							rs.getString("Jednostka_Monetarna"),
							rs.getString("Ustroj"),
							rs.getString("Informacje_Dodatkowe"),
							rs.getString("Nazwa_Kontynentu")
						)
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return idPanstwa.get() + ". " + nazwaPanstwa.get();
	}
}