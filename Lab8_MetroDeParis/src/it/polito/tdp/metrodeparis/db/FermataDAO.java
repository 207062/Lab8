package it.polito.tdp.metrodeparis.db;

import java.sql.*;
import java.util.*;



import it.polito.tdp.metrodeparis.bean.*;

public class FermataDAO {

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

		} catch (SQLException e) {

			e.printStackTrace();
		}
	return fermate;
	}
	
}
