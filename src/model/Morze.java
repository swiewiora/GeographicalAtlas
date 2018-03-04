package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Morze{
	private IntegerProperty idMorza;
	private StringProperty nazwaMorza;
	private IntegerProperty powierzchniaMorza;

	public Morze(Integer idMorza, String nazwaMorza, Integer powierzchniaMorza){
		this.idMorza = new SimpleIntegerProperty(idMorza);
		this.nazwaMorza = new SimpleStringProperty(nazwaMorza);
		this.powierzchniaMorza = new SimpleIntegerProperty(powierzchniaMorza);
	}

	public Integer getIdMorza(){
		return idMorza.get();
	}

	public void setIdMorza(Integer idMorza){
		this.idMorza = new SimpleIntegerProperty(idMorza);
	}

	public String getNazwaMorza(){
		return nazwaMorza.get();
	}

	public void setNazwaMorza(String nazwaMorza){
		this.nazwaMorza = new SimpleStringProperty(nazwaMorza);
	}

	public Integer getPowierzchniaMorza(){
		return powierzchniaMorza.get();
	}

	public void setPowierzchniaMorza(Integer powierzchniaMorza){
		this.powierzchniaMorza = new SimpleIntegerProperty(powierzchniaMorza);
	}

	public IntegerProperty idMorzaProperty(){
		return idMorza;
	}

	public StringProperty nazwaMorzaProperty(){
		return nazwaMorza;
	}

	public IntegerProperty powierzchniaMorzaProperty(){
		return powierzchniaMorza;
	}


}