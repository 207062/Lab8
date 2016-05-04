package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.bean.Linea;

public class LineaDAO {

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

}
