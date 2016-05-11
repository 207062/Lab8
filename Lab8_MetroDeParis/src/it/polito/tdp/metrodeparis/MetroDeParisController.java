package it.polito.tdp.metrodeparis;

import java.util.List;
import it.polito.tdp.metrodeparis.bean.Fermata;
import it.polito.tdp.metrodeparis.bean.MetroDeParisModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

	private MetroDeParisModel model;

	@FXML
	private ComboBox<Fermata> boxPartenza;

	@FXML
	private ComboBox<Fermata> boxArrivo;

	@FXML
	private Button btnCalcolaDist;

	@FXML
	private TextArea txtOutput;

	@FXML
	void doCalcola(ActionEvent event) {
	 boolean percorso=false;
	 Fermata stazArrivo = boxArrivo.getValue();
	 Fermata stazPartenza = boxPartenza.getValue();
     if(stazPartenza==null || stazArrivo==null){
      txtOutput.appendText("Scegliere una Stazione di Arrivo e una di partenza !!");
     }
     else if(stazArrivo.equals(stazPartenza)){
        txtOutput.appendText("Le stazioni di Partenza e Arrivo non devono coincidere!!");	
      }
     else{
      percorso=model.calcolaPercorso(stazPartenza, stazArrivo);
       if(percorso){
    	int tempoTotInSecondi = (int) model.getPercorsoTempoTotale(stazPartenza, stazArrivo);
    	int ore = tempoTotInSecondi/3600;
    	int min = (tempoTotInSecondi%3600)/60;
    	int secondi = tempoTotInSecondi%60;    // int secondi = (tempoTotInSecondi%3600)%60;
    	
    	StringBuilder sb = new StringBuilder();
    	String stime = String.format("%02d:%02d:%02d", ore,min,secondi);
    	sb.append(model.percorsoToString(stazPartenza, stazArrivo));
    	sb.append("\n\nTempo di Percorenza stimato: "+stime+"\n");
    	txtOutput.setText(sb.toString());;
       }
     }
	}

	public void setModel(MetroDeParisModel model) {
		List<Fermata> fermate = model.getStaz();
		
		this.model = model;
		boxArrivo.getItems().addAll(fermate);
		boxPartenza.getItems().addAll(fermate);
		
		model.buildGraph();
	}

	@FXML
	void initialize() {
		assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert btnCalcolaDist != null : "fx:id=\"btnCalcolaDist\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'fxml8.fxml'.";

	}
}
