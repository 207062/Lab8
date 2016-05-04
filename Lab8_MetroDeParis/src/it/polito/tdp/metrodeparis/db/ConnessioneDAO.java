package it.polito.tdp.metrodeparis.db;

import it.polito.tdp.metrodeparis.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ConnessioneDAO {

	public List<Connessione> getConnessioni() {
		List<Connessione> connessioni = new ArrayList<>();
		String sql = "select * from connessione;";
		LineaDAO daoL = new LineaDAO();
		FermataDAO daoF = new FermataDAO();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();
			while (res.next()) {
				Linea linea = null;
				Fermata fermataP = null;
				Fermata fermataA = null;

				int id = res.getInt("id_connessione");

				for (Linea l : daoL.getLinee()) {
					if (res.getInt("id_linea") == l.getId_linea())
						linea = l;
				}
				for (Fermata f : daoF.getFermate()) {
					if (res.getInt("staz_P") == f.getId_fermata())
						fermataP = f;
					else if (res.getInt("staz_A") == f.getId_fermata())
						fermataA = f;
				}
				Connessione connessione = new Connessione(id, linea, fermataP, fermataA);
				connessioni.add(connessione);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connessioni;
	}

}
