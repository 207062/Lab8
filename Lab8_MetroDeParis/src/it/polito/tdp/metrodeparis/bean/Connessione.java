package it.polito.tdp.metrodeparis.bean;

public class Connessione {
 private int id_connessione;
 private Linea linea;
 private Fermata fermataP;
 private Fermata fermataA;

 
 public Connessione(int id_connessione,Linea linea, Fermata fermataP, Fermata fermataA) {
	super();
	this.id_connessione = id_connessione;
	this.linea = linea;
	this.fermataP = fermataP;
	this.fermataA = fermataA;
			
	
}
public int getId_connessione() {
	return id_connessione;
}
public void setId_connessione(int id_connessione) {
	this.id_connessione = id_connessione;
}
public Fermata getFermataA() {
	return fermataA;
}
public void setFermataA(Fermata fermataA) {
	this.fermataA = fermataA;
}
public Fermata getFermataP() {
	return fermataP;
}
public void setFermataP(Fermata fermataP) {
	this.fermataP = fermataP;
}
public Linea getLinea() {
	return linea;
}
public void setLinea(Linea linea) {
	this.linea = linea;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id_connessione;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Connessione other = (Connessione) obj;
	if (id_connessione != other.id_connessione)
		return false;
	return true;
}

}
