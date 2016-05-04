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

	}

	public void setModel(MetroDeParisModel model) {
		List<Fermata> fermate = model.getStaz();
		
		this.model = model;
		boxArrivo.getItems().addAll(fermate);
		boxPartenza.getItems().addAll(fermate);
	}

	@FXML
	void initialize() {
		assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert btnCalcolaDist != null : "fx:id=\"btnCalcolaDist\" was not injected: check your FXML file 'fxml8.fxml'.";
		assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'fxml8.fxml'.";

	}
}
