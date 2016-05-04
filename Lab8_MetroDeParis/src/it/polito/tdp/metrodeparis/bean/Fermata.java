package it.polito.tdp.metrodeparis.bean;

public class Fermata {
 private int id_fermata;
 private String nome;
 private double CoordX;
 private double CoordY;

 
 public Fermata(int id_fermata, String nome, double coordX, double coordY) {
	super();
	this.id_fermata = id_fermata;
	this.nome = nome;
	CoordX = coordX;
	CoordY = coordY;
 }


public int getId_fermata() {
	return id_fermata;
}


public void setId_fermata(int id_fermata) {
	this.id_fermata = id_fermata;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public double getCoordX() {
	return CoordX;
}


public void setCoordX(double coordX) {
	CoordX = coordX;
}


public double getCoordY() {
	return CoordY;
}


public void setCoordY(double coordY) {
	CoordY = coordY;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id_fermata;
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
	Fermata other = (Fermata) obj;
	if (id_fermata != other.id_fermata)
		return false;
	return true;
}


@Override
public String toString() {
	return nome; 
}
 
 
}
