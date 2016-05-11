package it.polito.tdp.metrodeparis.bean;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.MetroDAO;


public class MetroDeParisModel {

	private List<Fermata> fermate;
	//private List<Linea> linee;
	private List<Connessione> connessioni;
	private WeightedGraph<Fermata, DefaultWeightedEdge> graph;
	private MetroDAO dao ;
	
    
	private List<DefaultWeightedEdge> pathEdges = null;
	private double pathLength = 0.0;

	public List<String> getStaz_P() {
		 dao = new MetroDAO();
		List<String> stops = new ArrayList<>();
		String s = null;
		connessioni = dao.getConnessioni();
		for (Connessione c : connessioni) {
			s = c.getFermataP().getNome();
			stops.add(s);
		}
		return stops;
	}

	 
		//Graphs.addEdge(g, sourceVertex, targetVertex, weight)
	
	
	public void buildGraph(){
	  graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	  
	  double distanza =0.0;
	  double speed =0.0;
	  double time = 0.0;

	   dao = new MetroDAO();
	 
	 
	  fermate = dao.getFermate();
	  //linee = dao.getLinee();
	  connessioni= dao.getConnessioni();
	  
	 	Graphs.addAllVertices(graph, fermate);
	 	
	 	for(Connessione c : connessioni){
	      speed = c.getLinea().getVelocita();
	      distanza = distance(c.getFermataP(),c.getFermataA());
	      time = (distanza/speed)*60*60;
	      
	    Graphs.addEdge(graph, c.getFermataP(), c.getFermataA(), time);
	   
	   }
	}
	
	public double distance(Fermata f1,Fermata f2){
	 return LatLngTool.distance(new LatLng(f1.getCoordX(), f2.getCoordY()), new LatLng(f2.getCoordX(), f2.getCoordY()),
				LengthUnit.KILOMETER);
	}
	public List<Fermata> getStaz() {
		dao =  new MetroDAO();
	return dao.getFermate();
	}
	public List<Linea> getLinee(){
	  dao = new MetroDAO();
	 return dao.getLinee();	
	}
	public List<Connessione> getConnessioni(){
       dao = new MetroDAO();
	 return dao.getConnessioni();
	}
    public Graph<Fermata, DefaultWeightedEdge> getGraph(){
     return graph;
    }
	public static void main(String[] args){
	 MetroDeParisModel model = new MetroDeParisModel();
	  model.buildGraph();
		 System.out.println("Grafo creato : "+model.getGraph().vertexSet().size()+" nodi e "+model.getGraph().edgeSet().size()+" archi");
 }
	public boolean calcolaPercorso(Fermata partenza , Fermata arrivo){
		
		boolean connesso =false;
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(graph, partenza, arrivo);
 		GraphPath<Fermata, DefaultWeightedEdge> path= dijkstra.getPath();
 		  if(path!=null){
 			pathEdges = path.getEdgeList();
 			pathLength = path.getWeight();
 			connesso = true;
 		   }
     return connesso;
	}
   
	public String percorsoToString(Fermata partenza,Fermata arrivo){
	   StringBuilder sb = new StringBuilder();
	   sb.append("Percorso: [");
	 if(calcolaPercorso(partenza, arrivo) && pathEdges!=null) {
		for(DefaultWeightedEdge edge : pathEdges){
		 sb.append(graph.getEdgeTarget(edge).getNome());
		 sb.append(", ");
		}
		sb.setLength(sb.length()-2);
		sb.append("]");
	 }
	 return sb.toString();
   }
	public double getPercorsoTempoTotale(Fermata partenza,Fermata arrivo){
	  double tempoTotale=0.0;

	  if(calcolaPercorso(partenza, arrivo) && pathLength!=0.0){
		tempoTotale = pathLength + (pathEdges.size()-1)*30;  
	  }
	return tempoTotale; 
	}
}
