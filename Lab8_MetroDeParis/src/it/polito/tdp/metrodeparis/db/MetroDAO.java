package it.polito.tdp.metrodeparis.db;

import java.sql.*;
import java.util.*;



import it.polito.tdp.metrodeparis.bean.*;
import javafx.scene.shape.Line;

public class MetroDAO {

	public List<Fermata> getFermate() {
		List<Fermata> fermate = new ArrayList<>();
		String sql = "select * from fermata;";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Fermata fermata = new Fermata(res.getInt("id_fermata"), 
						                      res.getString("nome"),
						                      res.getDouble("CoordX"),
			                                  res.getDouble("CoordY"));
				fermate.add(fermata);
			}
			
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Connessione fallita", e);
		}
	return fermate;
	}
	

  
	public List<Linea> getLinee() {
		List<Linea> linee = new ArrayList<>();
		String sql = "select * from linea;";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Linea linea = new Linea(res.getInt("id_linea"), res.getString("nome"), res.getDouble("velocita"),
						res.getDouble("intervallo"), res.getString("colore"));
				linee.add(linea);
			}

		} catch (SQLException e) {
          
			e.printStackTrace();
		}
		return linee;
	}


	public List<Connessione> getConnessioni() {
		List<Connessione> connessioni = new ArrayList<>();
		String sql = "select * from connessione;";
		//LineaDAO daoL = new LineaDAO();
		//FermataDAO daoF = new FermataDAO();
		List<Linea> linee = getLinee();
		List<Fermata> fermate = getFermate();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();
			while (res.next()) {
				Linea linea = null;
				Fermata fermataP = null;
				Fermata fermataA = null;

				int id = res.getInt("id_connessione");

				for (Linea l : linee) {
					if (res.getInt("id_linea") == l.getId_linea())
						linea = l;
				}
				for (Fermata f : fermate) {
					if (res.getInt("id_stazP") == f.getId_fermata())
						fermataP = f;
					else if (res.getInt("id_stazA") == f.getId_fermata())
						fermataA = f;
				}
				Connessione connessione = new Connessione(id, linea, fermataP, fermataA);
				connessioni.add(connessione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di Connessione", e);
		}

		return connessioni;
	}
	
	public List<FermataSulinea> getFermateSulinea(){
		List<FermataSulinea> fermatesl = new ArrayList<>();
		List<Fermata> fermate = getFermate();
		List<Linea> linee = getLinee();
	
	String sql=	"select distinct fermata.id_fermata,linea.id_linea from fermata,linea,connessione where (fermata.id_fermata=id_stazP OR fermata.id_fermata=id_stazA) and linea.id_linea=connessione.id_linea;";
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
		     Fermata fermata = null;
			 Linea linea = null;
			
			 
			 int idFermata = res.getInt("id_fermata");
			 int idLinea = res.getInt("id_linea");
			 
			 fermata = fermate.get(idFermata);
			 linea = linee.get(idLinea);
			 
			 FermataSulinea fsl=new FermataSulinea(fermata.getId_fermata(), fermata.getNome(), fermata.getCoordX(), 
					                                fermata.getCoordY(), linea);
			 
			fermatesl.add(fsl);
		
		   conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di Connessione", e);
		}
	 return fermatesl;
	}

}

