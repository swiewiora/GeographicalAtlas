package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Miasto{
	private IntegerProperty idMiasta;
	private StringProperty nazwaMiasta;
	private IntegerProperty liczbaMieszkancow;
	private boolean czyStolica;
	private IntegerProperty idPanstwa;

	public Miasto(Integer idMiasta, String nazwaMiasta, Integer liczbaMieszkancow, boolean czyStolica, Integer idPanstwa){
		this.idMiasta = new SimpleIntegerProperty(idMiasta);
		this.nazwaMiasta = new SimpleStringProperty(nazwaMiasta);
		this.liczbaMieszkancow = new SimpleIntegerProperty(liczbaMieszkancow);
		this.czyStolica = czyStolica;
		this.idPanstwa = new SimpleIntegerProperty(idPanstwa);
	}

	public Integer getIdMiasta(){
		return idMiasta.get();
	}

	public void setIdMiasta(Integer idMiasta){
		this.idMiasta = new SimpleIntegerProperty(idMiasta);
	}

	public String getNazwaMiasta(){
		return nazwaMiasta.get();
	}

	public void setNazwaMiasta(String nazwaMiasta){
		this.nazwaMiasta = new SimpleStringProperty(nazwaMiasta);
	}

	public Integer getLiczbaMieszkancow(){
		return liczbaMieszkancow.get();
	}

	public void setLiczbaMieszkancow(Integer liczbaMieszkancow){
		this.liczbaMieszkancow = new SimpleIntegerProperty(liczbaMieszkancow);
	}

	public boolean getCzyStolica(){
		return czyStolica;
	}

	public void setCzyStolica(boolean czyStolica){
		this.czyStolica = czyStolica;
	}

	public Integer getIdPanstwa(){
		return idPanstwa.get();
	}

	public void setIdPanstwa(Integer idPanstwa){
		this.idPanstwa = new SimpleIntegerProperty(idPanstwa);
	}

	public IntegerProperty idMiastaProperty(){
		return idMiasta;
	}

	public StringProperty nazwaMiastaProperty(){
		return nazwaMiasta;
	}

	public IntegerProperty liczbaMieszkancowProperty(){
		return liczbaMieszkancow;
	}

	public IntegerProperty idPanstwaProperty(){
		return idPanstwa;
	}


}