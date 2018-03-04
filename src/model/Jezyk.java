package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Jezyk{
	private IntegerProperty idJezyka;
	private StringProperty jezyk;

	public Jezyk(Integer idJezyka, String jezyk){
		this.idJezyka = new SimpleIntegerProperty(idJezyka);
		this.jezyk = new SimpleStringProperty(jezyk);
	}

	public Integer getIdJezyka(){
		return idJezyka.get();
	}

	public void setIdJezyka(Integer idJezyka){
		this.idJezyka = new SimpleIntegerProperty(idJezyka);
	}

	public String getJezyk(){
		return jezyk.get();
	}

	public void setJezyk(String jezyk){
		this.jezyk = new SimpleStringProperty(jezyk);
	}

	public IntegerProperty idJezykaProperty(){
		return idJezyka;
	}

	public StringProperty jezykProperty(){
		return jezyk;
	}


}