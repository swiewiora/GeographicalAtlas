package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Panstwo_Na_Kontynencie {
	private IntegerProperty idKontynentu;
	private IntegerProperty idPanstwa;
	private StringProperty nazwaPanstwa;
	
	public Panstwo_Na_Kontynencie(Integer idKontynentu, Integer idPanstwa, 
			String nazwaPanstwa) {
		this.idKontynentu = new SimpleIntegerProperty(idKontynentu);
		this.idPanstwa = new SimpleIntegerProperty(idPanstwa);
		this.nazwaPanstwa = new SimpleStringProperty(nazwaPanstwa);
	}
	
	public final IntegerProperty idKontynentuProperty() {
		return this.idKontynentu;
	}
	
	public final int getIdKontynentu() {
		return this.idKontynentuProperty().get();
	}
	
	public final void setIdKontynentu(final int idKontynentu) {
		this.idKontynentuProperty().set(idKontynentu);
	}
	
	public final IntegerProperty idPanstwaProperty() {
		return this.idPanstwa;
	}
	
	public final int getIdPanstwa() {
		return this.idPanstwaProperty().get();
	}
	
	public final void setIdPanstwa(final int idPanstwa) {
		this.idPanstwaProperty().set(idPanstwa);
	}
	
	public final StringProperty nazwaPanstwaProperty() {
		return this.nazwaPanstwa;
	}
	
	public final String getNazwaPanstwa() {
		return this.nazwaPanstwaProperty().get();
	}
	
	public final void setNazwaPanstwa(final String nazwaPanstwa) {
		this.nazwaPanstwaProperty().set(nazwaPanstwa);
	}
	
	public static void getList (Connection connection, ObservableList<Panstwo_Na_Kontynencie> lista) {
		try {
			final String query = "SELECT A.*, B.Nazwa_Panstwa"
					+ " FROM swiewio1.Panstwo_Na_Kontynencie A"
					+ " INNER JOIN swiewio1.Panstwo B"
					+ " ON (A.Id_Panstwa = B.Id_Panstwa);";
			try (Statement st = connection.createStatement()) {
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					lista.add(
							new Panstwo_Na_Kontynencie(
							rs.getInt("Id_Kontynentu"),
							rs.getInt("Id_Panstwa"),
							rs.getString("Nazwa_Panstwa")
							)
						);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}