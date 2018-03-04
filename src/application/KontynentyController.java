package application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Kontynent;
import model.Panstwo;
import model.Panstwo_Na_Kontynencie;

public class KontynentyController {
	public void initKontynenty(FormController parent) {
		parent.comboPanstwa.setItems(parent.listaPanstwo);
		parent.tableKontynenty.setItems(parent.listaKontynent);
		
		parent.cidkon.setCellValueFactory(new PropertyValueFactory<Kontynent, Integer>("idKontynentu"));
		parent.cnazwakon.setCellValueFactory(new PropertyValueFactory<Kontynent, String>("nazwaKontynentu"));
		parent.cpowbezwysp.setCellValueFactory(new PropertyValueFactory<Kontynent, Double>("powierzchniaBezWysp"));
		parent.cpowzwysp.setCellValueFactory(new PropertyValueFactory<Kontynent, Double>("powierzchniaZWyspami"));
	}
	
	public void initPanstwoKontynent(FormController parent) {
		parent.tablePanstwaKontynenty.setItems(parent.listaPanstwoKontynent);
		
		parent.cPKidkon.setCellValueFactory(new PropertyValueFactory<Panstwo_Na_Kontynencie, Integer>("idKontynentu"));
		parent.cPKidpan.setCellValueFactory(new PropertyValueFactory<Panstwo_Na_Kontynencie, Integer>("idPanstwa"));
		parent.cPKnazwapan.setCellValueFactory(new PropertyValueFactory<Panstwo_Na_Kontynencie, String>("nazwaPanstwa"));
	}
	
	public void pullKontynenty(FormController parent) {
		parent.listaPanstwo.removeAll(parent.listaPanstwo);
		parent.listaKontynent.removeAll(parent.listaKontynent);
		parent.listaPanstwoKontynent.removeAll(parent.listaPanstwoKontynent);
		Kontynent.getList(parent.connection.getConnection(), parent.listaKontynent);
		Panstwo.getList(parent.connection.getConnection(), parent.listaPanstwo);
		Panstwo_Na_Kontynencie.getList(parent.connection.getConnection(), parent.listaPanstwoKontynent);
		
		parent.tableKontynenty.setItems(parent.listaKontynent);
		parent.comboPanstwa.setItems(parent.listaPanstwo);
		parent.tablePanstwaKontynenty.setItems(parent.listaPanstwoKontynent);
		
		parent.cidkon.setCellValueFactory(new PropertyValueFactory<Kontynent, Integer>("idKontynentu"));
		parent.cnazwakon.setCellValueFactory(new PropertyValueFactory<Kontynent, String>("nazwaKontynentu"));
		parent.cpowbezwysp.setCellValueFactory(new PropertyValueFactory<Kontynent, Double>("powierzchniaBezWysp"));
		parent.cpowzwysp.setCellValueFactory(new PropertyValueFactory<Kontynent, Double>("powierzchniaZWyspami"));
	}
	
	public void addKontynenty(FormController parent) {
		try {
			String sql = "insert into `swiewio1`.`Kontynent` "
					+ "(`Id_Kontynentu`, `Nazwa_Kontynentu`,"
					+ " `Powierzchnia_Bez_Wysp`, `Powierzchnia_Z_Wyspami`) VALUES"
					+ " (?,?,?,?)";
			PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, Integer.parseInt(parent.idkon.getText()));
			st.setString (2, parent.nazwakon.getText());
			st.setDouble (3, Double.parseDouble(parent.powbezwysp.getText()));
			st.setDouble (4, Double.parseDouble(parent.powzwysp.getText()));
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		parent.pullKontynent();
	}
	
	public void addKontynentPanstwo(FormController parent) {
		try {
			Panstwo panstwo = parent.comboPanstwa.getValue();
			String sql = "insert into `swiewio1`.`Panstwo_Na_Kontynencie` "
				+ "(`Id_Panstwa`, `Id_Kontynentu`) VALUES"
				+ " (?,?)";
			PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, panstwo.getIdPanstwa());
			st.setInt (2, Integer.parseInt(parent.idkon.getText()));
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		parent.pullKontynent();
	}
	
	public void cancelKontynenty(FormController parent) {
		parent.idkon.clear();
		parent.nazwakon.clear();
		parent.powbezwysp.clear();
		parent.powzwysp.clear();
		parent.comboPanstwa.setValue(null);
	}
	
	public void deleteKontynenty(FormController parent) {
		if(parent.tableKontynenty.getSelectionModel().getSelectedItem() != null)  {
			Kontynent kontynent = parent.tableKontynenty.getSelectionModel().getSelectedItem();
			try {
				String sql = "DELETE FROM `swiewio1`.`Kontynent` WHERE"
						+ " Id_Kontynentu = ?";
				
				PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
				st.setInt (1, kontynent.getIdKontynentu());
				st.execute();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if(parent.tablePanstwaKontynenty.getSelectionModel().getSelectedItem() != null)  {
			Panstwo_Na_Kontynencie pnk = parent.tablePanstwaKontynenty.getSelectionModel().getSelectedItem();
			try {
				String sql = "DELETE FROM `swiewio1`.`Panstwo_Na_Kontynencie` WHERE"
						+ " Id_Kontynentu = ?";
				
				PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
				st.setInt (1, pnk.getIdKontynentu());
				st.execute();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		parent.pullKontynent();
	}
	public void deselectPanstwoKontynent(FormController parent) {
		if(parent.tablePanstwaKontynenty.getSelectionModel().getSelectedItem() != null)
			parent.tablePanstwaKontynenty.getSelectionModel().clearSelection();
	}
	public void deselectKontynent(FormController parent) {
		if(parent.tableKontynenty.getSelectionModel().getSelectedItem() != null)
			parent.tableKontynenty.getSelectionModel().clearSelection();
	}
	public void selectKontynenty(FormController parent) {
		if(parent.tableKontynenty.getSelectionModel().getSelectedItem() != null)  {
			Kontynent kontynent = parent.tableKontynenty.getSelectionModel().getSelectedItem(); 
			parent.idkon.setText(kontynent.getIdKontynentu().toString());
			parent.nazwakon.setText(kontynent.getNazwaKontynentu());
			parent.powbezwysp.setText(kontynent.getPowierzchniaBezWysp().toString());
			parent.powzwysp.setText(kontynent.getPowierzchniaZWyspami().toString());
		}
	}
}


/*public void deleteKontynentyPanstwo(FormController parent) {
	if(parent.tableKonPan.getSelectionModel().getSelectedItem() != null)  {
		Kontynent kontynent = parent.tableKontynenty.getSelectionModel().getSelectedItem();
		try {
			String sql = "DELETE FROM `swiewio1`.`Panstwo` WHERE"
					+ " Id_Panstwa = ?, Id_Kontynentu";
			
			PreparedStatement st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, kontynent.getIdKontynentu());
			st.execute();
			sql = "DELETE FROM `swiewio1`.`Panstwo_Na_Kontynencie` WHERE"
					+ " Id_Kontynentu = ?";
			
			st = parent.connection.getConnection().prepareStatement(sql);
			st.setInt (1, kontynent.getIdKontynentu());
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		parent.pull();
	}
}*/
