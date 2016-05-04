package it.polito.tdp.metrodeparis.bean;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.ConnessioneDAO;
import it.polito.tdp.metrodeparis.db.FermataDAO;

public class MetroDeParisModel {

	private List<Fermata> fermate;
	private List<Linea> linee;
	private List<Connessione> connessioni;
	private WeightedGraph<Fermata, DefaultWeightedEdge> graph;

	public List<Fermata> getStaz() {
		FermataDAO dao = new FermataDAO();
		
		fermate = dao.getFermate();
		
		return fermate;
	}

	public List<String> getStaz_P() {
		ConnessioneDAO dao = new ConnessioneDAO();
		List<String> stops = new ArrayList<>();
		String s = null;
		connessioni = dao.getConnessioni();
		for (Connessione c : connessioni) {
			s = c.getFermataP().getNome();
			stops.add(s);
		}
		return stops;
	}

	public double distanceMinima(Fermata f1, Fermata f2) {
		return LatLngTool.distance(new LatLng(f1.getCoordX(), f2.getCoordY()), new LatLng(f2.getCoordX(), f2.getCoordY()),
				LengthUnit.KILOMETER);
		//Graphs.addEdge(g, sourceVertex, targetVertex, weight)
	}

}
