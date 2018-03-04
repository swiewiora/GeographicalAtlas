package model;

import java.sql.Connection;
import java.sql.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Kontynent{
	private DoubleProperty powierzchniaBezWysp;
	private IntegerProperty idKontynentu;
	private StringProperty nazwaKontynentu;
	private DoubleProperty powierzchniaZWyspami;

	public Kontynent(Integer idKontynentu, 
			String nazwaKontynentu, Double powierzchniaBezWysp, Double powierzchniaZWyspami){
		this.powierzchniaBezWysp = new SimpleDoubleProperty(powierzchniaBezWysp);
		this.idKontynentu = new SimpleIntegerProperty(idKontynentu);
		this.nazwaKontynentu = new SimpleStringProperty(nazwaKontynentu);
		this.powierzchniaZWyspami = new SimpleDoubleProperty(powierzchniaZWyspami);
	}

	public Double getPowierzchniaBezWysp(){
		return powierzchniaBezWysp.get();
	}

	public void setPowierzchniaBezWysp(Double powierzchniaBezWysp){
		this.powierzchniaBezWysp = new SimpleDoubleProperty(powierzchniaBezWysp);
	}

	public Integer getIdKontynentu(){
		return idKontynentu.get();
	}

	public void setIdKontynentu(Integer idKontynentu){
		this.idKontynentu = new SimpleIntegerProperty(idKontynentu);
	}

	public String getNazwaKontynentu(){
		return nazwaKontynentu.get();
	}

	public void setNazwaKontynentu(String nazwaKontynentu){
		this.nazwaKontynentu = new SimpleStringProperty(nazwaKontynentu);
	}

	public Double getPowierzchniaZWyspami(){
		return powierzchniaZWyspami.get();
	}

	public void setPowierzchniaZWyspami(Double powierzchniaZWyspami){
		this.powierzchniaZWyspami = new SimpleDoubleProperty(powierzchniaZWyspami);
	}

	public DoubleProperty powierzchniaBezWyspProperty(){
		return powierzchniaBezWysp;
	}

	public IntegerProperty idKontynentuProperty(){
		return idKontynentu;
	}

	public StringProperty nazwaKontynentuProperty(){
		return nazwaKontynentu;
	}

	public DoubleProperty powierzchniaZWyspamiProperty(){
		return powierzchniaZWyspami;
	}

	@Override
	public String toString() {
		return idKontynentu.get() + ". " + nazwaKontynentu.get() + " (" + powierzchniaBezWysp.get()
		+ ", " + powierzchniaZWyspami.get() + ")";
	}
	
	public static void getList (Connection connection, ObservableList<Kontynent> lista) {
		try {
			final String query = "SELECT * FROM swiewio1.Kontynent;";
			try (Statement st = connection.createStatement()) {
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					lista.add(
						new Kontynent(
							rs.getInt("Id_Kontynentu"),
							rs.getString("Nazwa_Kontynentu"),
							rs.getDouble("Powierzchnia_Bez_wysp"),
							rs.getDouble("Powierzchnia_Z_Wyspami")
						)
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}