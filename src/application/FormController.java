package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class FormController implements Initializable {
	//Komponenty GUI
	@FXML protected ComboBox<Kontynent> comboKontynenty;
	@FXML protected TableView<Panstwo> tablePanstwa;
	@FXML protected TableView<Kontynent> tableKontynenty;
	@FXML protected ComboBox<Panstwo> comboPanstwa;
	@FXML protected TableView<Panstwo_Na_Kontynencie> tablePanstwaKontynenty;
	//Pola tekstowe
	@FXML TextField idPanstwa, nazwaPanstwa, powierzchniaPanstwa, liczbaLudnosci,
		jednostkaMonetarna, ustroj, infoDodatkowe; 
	@FXML TextField idkon, nazwakon, powbezwysp, powzwysp; 
	//Kolumny Panstwa
	@FXML protected TableColumn<Panstwo, Integer> cId, cLiczba;
	@FXML protected TableColumn<Panstwo, String> cNazwa, cJednostka, cUstroj, cInfo,
		cKontynent;
	@FXML protected TableColumn<Panstwo, Double> cPowierzchnia;
	//Kolumny Kontynenty
	@FXML protected TableColumn<Kontynent, Integer> cidkon;
	@FXML protected TableColumn<Kontynent, Double> cpowbezwysp, cpowzwysp;
	@FXML protected TableColumn<Kontynent, String> cnazwakon;
	//Kolumny PanstwaKontynenty
	@FXML protected TableColumn<Panstwo_Na_Kontynencie, Integer> cPKidkon, cPKidpan;
	@FXML protected TableColumn<Panstwo_Na_Kontynencie, String> cPKnazwapan;
	// listy
	protected ObservableList<Kontynent> listaKontynent;
	protected ObservableList<Panstwo> listaPanstwo;
	protected ObservableList<Panstwo_Na_Kontynencie> listaPanstwoKontynent;
	
	protected MySQL connection;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connection = new MySQL();
		connection.establishConnection();

		listaKontynent = FXCollections.observableArrayList();
		listaPanstwo = FXCollections.observableArrayList();
		listaPanstwoKontynent = FXCollections.observableArrayList();
		Kontynent.getList(connection.getConnection(), listaKontynent);
		Panstwo.getList(connection.getConnection(), listaPanstwo);
		Panstwo_Na_Kontynencie.getList(connection.getConnection(), listaPanstwoKontynent);
		
		
		new PanstwaController().initPanstwa(this);
		new KontynentyController().initKontynenty(this);
		new KontynentyController().initPanstwoKontynent(this);
	}
	
	//Panstwa
	public void select() {
		new PanstwaController().selectPanstwa(this);
	}
	public void anuluj() {
		new PanstwaController().cancelPanstwa(this);
	}
	public void delete() {
		new PanstwaController().deletePanstwa(this);
	}
	public void add() {
		new PanstwaController().addPanstwa(this);
	}
	public void addPanstwaKontynent() {
		new PanstwaController().addPanstwaKontynent(this);
	}
	public void pull() {
		new PanstwaController().pullPanstwa(this);
	}
	//Kontynenty
	public void selectKontynent() {
		new KontynentyController().selectKontynenty(this);
	}
	public void cancelKontynent() {
		new KontynentyController().cancelKontynenty(this);
	}
	public void deleteKontynent() {
		new KontynentyController().deleteKontynenty(this);
	}
	public void addKontynent() {
		new KontynentyController().addKontynenty(this);
	}
	public void addKontynentPanstwo() {
		new KontynentyController().addKontynentPanstwo(this);
	}
	public void pullKontynent() {
		new KontynentyController().pullKontynenty(this);
	}
	public void deselectPanstwoKontynent() {
		new KontynentyController().deselectPanstwoKontynent(this);
	}
	public void deselectKontynent() {
		new KontynentyController().deselectKontynent(this);
	}
}
