package application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Kontynent;
import model.Panstwo;

public class PanstwaController {
	public void initPanstwa(FormController parent) {
		parent.comboKontynenty.setItems(parent.listaKontynent);
		parent.tablePanstwa.setItems(parent.listaPanstwo);
		
		parent.cId.setCellValueFactory(new PropertyValueFactory<Panstwo, Integer>("idPanstwa"));
		parent.cNazwa.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("nazwaPanstwa"));
		parent.cPowierzchnia.setCellValueFactory(new PropertyValueFactory<Panstwo, Double>("powierzchniaPanstwa"));
		parent.cLiczba.setCellValueFactory(new PropertyValueFactory<Panstwo, Integer>("liczbaLudnosci"));
		parent.cJednostka.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("jednostkaMonetarna"));
		parent.cUstroj.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("ustroj"));
		parent.cInfo.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("infoDodatkowe"));
		parent.cKontynent.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("nazwaKontynentu"));
	}
	public void pullPanstwa(FormController parent) {
		parent.listaPanstwo.removeAll(parent.listaPanstwo);
		parent.listaKontynent.removeAll(parent.listaKontynent);
		Kontynent.getList(parent.connection.getConnection(), parent.listaKontynent);
		Panstwo.getList(parent.connection.getConnection(), parent.listaPanstwo);
		parent.comboKontynenty.setItems(parent.listaKontynent);
		parent.tablePanstwa.setItems(parent.listaPanstwo);
		
		parent.cId.setCellValueFactory(new PropertyValueFactory<Panstwo, Integer>("idPanstwa"));
		parent.cNazwa.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("nazwaPanstwa"));
		parent.cPowierzchnia.setCellValueFactory(new PropertyValueFactory<Panstwo, Double>("powierzchniaPanstwa"));
		parent.cLiczba.setCellValueFactory(new PropertyValueFactory<Panstwo, Integer>("liczbaLudnosci"));
		parent.cJednostka.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("jednostkaMonetarna"));
		parent.cUstroj.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("ustroj"));
		parent.cInfo.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("infoDodatkowe"));
		parent.cKontynent.setCellValueFactory(new PropertyValueFactory<Panstwo, String>("nazwaKontynentu"));
	}
	
	public void addPanstwa(FormController parent) {
		try {
			String sql = "insert into `swiewio1`.`Panstwo` "
					+ "(`Id_Panstwa`, `Nazwa_Panstwa`, `Powierzchnia_Panstwa`,"
					+ " `Liczba_Ludnosci`, `Jednostka_Monetarna`, `Ustroj`,"
					+ " `Informacje_Dodatkowe`) VALUES"
					+ " (?,?,?,?,?,?,?)";
			PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, Integer.parseInt(parent.idPanstwa.getText()));
			st.setString (2, parent.nazwaPanstwa.getText());
			st.setDouble (3, Double.parseDouble(parent.powierzchniaPanstwa.getText()));
			st.setInt (4, Integer.parseInt(parent.liczbaLudnosci.getText()));
			st.setString (5, parent.jednostkaMonetarna.getText());
			st.setString (6, parent.ustroj.getText());
			st.setString (7, parent.infoDodatkowe.getText());
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		parent.pull();
	}
	
	public void addPanstwaKontynent(FormController parent) {
		try {
			Kontynent kontynent = parent.comboKontynenty.getValue();
			String sql = "insert into `swiewio1`.`Panstwo_Na_Kontynencie` "
				+ "(`Id_Panstwa`, `Id_Kontynentu`) VALUES"
				+ " (?,?)";
			PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, Integer.parseInt(parent.idPanstwa.getText()));
			st.setInt (2, kontynent.getIdKontynentu());
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		parent.pull();
	}
	
	public void cancelPanstwa(FormController parent) {
		parent.idPanstwa.clear();
		parent.nazwaPanstwa.clear();
		parent.powierzchniaPanstwa.clear();
		parent.liczbaLudnosci.clear();
		parent.jednostkaMonetarna.clear();
		parent.ustroj.clear();
		parent.infoDodatkowe.clear();
	}
	
	public void deletePanstwa(FormController parent) {
		if(parent.tablePanstwa.getSelectionModel().getSelectedItem() != null)  {
			Panstwo panstwo = parent.tablePanstwa.getSelectionModel().getSelectedItem();
			try {
				String sql = "DELETE FROM `swiewio1`.`Panstwo` WHERE"
						+ " Id_Panstwa = ?";
				
				PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
				st.setInt (1, panstwo.getIdPanstwa());
				st.execute();
				sql = "DELETE FROM `swiewio1`.`Panstwo_Na_Kontynencie` WHERE"
						+ " Id_Panstwa = ?";
				
				st = parent.connection.getConnection().prepareStatement(sql);
				st.setInt (1, panstwo.getIdPanstwa());
				st.execute();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			parent.pull();
		}
	}
	
	public void selectPanstwa(FormController parent) {
		if(parent.tablePanstwa.getSelectionModel().getSelectedItem() != null)  {
			Panstwo panstwo = parent.tablePanstwa.getSelectionModel().getSelectedItem(); 
			parent.idPanstwa.setText(panstwo.getIdPanstwa().toString());
			parent.nazwaPanstwa.setText(panstwo.getNazwaPanstwa());
			parent.powierzchniaPanstwa.setText(panstwo.getPowierzchniaPanstwa().toString());
			parent.liczbaLudnosci.setText(panstwo.getLiczbaLudnosci().toString());
			parent.jednostkaMonetarna.setText(panstwo.getJednostkaMonetarna());
			parent.ustroj.setText(panstwo.getUstroj());
			parent.infoDodatkowe.setText(panstwo.getInfoDodatkowe());
		}
	}
}