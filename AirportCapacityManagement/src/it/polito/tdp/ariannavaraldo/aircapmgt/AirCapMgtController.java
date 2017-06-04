package it.polito.tdp.ariannavaraldo.aircapmgt;

import java.net.URL;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.ariannavaraldo.core.AirportSimulator;
import it.polito.tdp.ariannavaraldo.model.Airport;
import it.polito.tdp.ariannavaraldo.model.Commons;
import it.polito.tdp.ariannavaraldo.model.ConfigDeparture;
import it.polito.tdp.ariannavaraldo.model.Flight;
import it.polito.tdp.ariannavaraldo.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AirCapMgtController 
{
	Model m = new Model();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Airport> airportBox;

    @FXML
    private ComboBox<Month> meseBox;

    @FXML
    private ComboBox<DayOfWeek> giornoBox;

    @FXML
    private ComboBox<Integer> startBox;

    @FXML
    private ComboBox<Integer> endBox;

    @FXML
    private Button btnCerca;

    @FXML
    private TableView<Flight> tabDeparture;

    @FXML
    private TableColumn<Flight, String> colDepartureCarrier;

    @FXML
    private TableColumn<Flight, Integer> colDepartureFlightNum;

    @FXML
    private TableColumn<Flight, String> colDepartureDestination;

    @FXML
    private TableColumn<Flight, Time> colDepartureTime;

    @FXML
    private TableColumn<Flight, String> colDepartureEsito;

    @FXML
    private PieChart pieTimeDeparture;

    @FXML
    private Button btnSimulationDeparture;

    @FXML
    private TableView<ConfigDeparture> tabConfigDeparture;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinArrivalTime;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxArrivalTime;

    @FXML
    private TextArea txtResultDeparture;

    @FXML
    private TableView<Flight> tabArrival;

    @FXML
    private TableColumn<Flight, String> colArrivalCarrier;

    @FXML
    private TableColumn<Flight, Integer> colArrivalFlightNum;

    @FXML
    private TableColumn<Flight, String> colArrivalFrom;

    @FXML
    private TableColumn<Flight, Time> colArrivalTime;

    @FXML
    private TableColumn<Flight, String> colArrivalEsito;

    @FXML
    private PieChart pieTimeArrival;

    @FXML
    private Button btnSimulationArrival;

    @FXML
    private TextArea txtResultArrival;

    @FXML
    void doDepartureSelected(MouseEvent event) 
    {
    	Flight f = tabDeparture.getSelectionModel().getSelectedItem();
    	f.calculateStatistics();
    	Map<Integer, Integer> mapTime = f.getStatistics().getPieAreasData();
    	ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Coda al check-in", mapTime.get(1)),
                new PieChart.Data("Coda security", mapTime.get(2)),
                new PieChart.Data("Duty free", mapTime.get(3)),
                new PieChart.Data("Area imbarco", mapTime.get(4)));
    	pieTimeDeparture.setData(pieChartData);
    }
    @FXML
    void doGetVoli(ActionEvent event) 
    {
    	txtResultArrival.clear();
    	txtResultDeparture.clear();
    	Airport a = airportBox.getValue();
     	int mm = meseBox.getValue().getValue();
     	int dd = giornoBox.getValue().getValue();
     	int start = startBox.getValue();
     	int end = endBox.getValue();
     	if(tabDeparture.getItems()!=null)
     		tabDeparture.getItems().clear();
     	if(tabArrival.getItems()!=null)	
     		tabArrival.getItems().clear();
     	List<Flight> depFlights = m.getDepartureFlights(a.getIata(), mm, dd, start, end);
     	List<Flight> arrFlights = m.getArrivalFLights(a.getIata(), mm, dd, start, end);
     	if(depFlights.isEmpty())
     	{
     		txtResultDeparture.appendText("Spiacenti, non ci sono voli in partenza in questa fascia oraria nell'aeroporto selezionato");
     	}
     	else
     	{
     		tabDeparture.getItems().addAll(depFlights);
     	}
     	if(arrFlights.isEmpty())
     	{
     		txtResultArrival.appendText("Spiacenti, non ci sono voli in arrivo in questa fascia oraria nell'aeroporto selezionato");
     	}
     	else
     	{
     		tabArrival.getItems().addAll(arrFlights);
     	}
     	
    }

    @FXML
    void doSimulationDeparture(ActionEvent event){
    	System.out.println(m.getConfigDeparture().getMinAdvanceArrival());
    	AirportSimulator simulator = new AirportSimulator(m.getConfigDeparture());
    	try {
			simulator.startSimulation(tabDeparture.getItems().subList(0, tabDeparture.getItems().size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void doSimulationArrival(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert airportBox != null : "fx:id=\"airportBox\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert meseBox != null : "fx:id=\"meseBox\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert giornoBox != null : "fx:id=\"giornoBox\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert startBox != null : "fx:id=\"startBox\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert endBox != null : "fx:id=\"endBox\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert tabDeparture != null : "fx:id=\"tabDeparture\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureCarrier != null : "fx:id=\"colDepartureCarrier\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureFlightNum != null : "fx:id=\"colDepartureFlightNum\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureDestination != null : "fx:id=\"colDepartureDestination\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureTime != null : "fx:id=\"colDepartureTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureEsito != null : "fx:id=\"colDepartureEsito\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert pieTimeDeparture != null : "fx:id=\"pieTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert btnSimulationDeparture != null : "fx:id=\"btnSimulation\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert tabConfigDeparture != null : "fx:id=\"tabConfigDeparture\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinArrivalTime != null : "fx:id=\"colMinArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxArrivalTime != null : "fx:id=\"colMxArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert txtResultDeparture != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert tabArrival != null : "fx:id=\"tabArrival\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colArrivalCarrier != null : "fx:id=\"colArrivalCarrier\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colArrivalFlightNum != null : "fx:id=\"colArrivalFlightNum\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colArrivalFrom != null : "fx:id=\"colArrivalFrom\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colArrivalTime != null : "fx:id=\"colArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colArrivalEsito != null : "fx:id=\"colArrivalEsito\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert pieTimeArrival != null : "fx:id=\"pieTimeArrival\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert btnSimulationArrival != null : "fx:id=\"btnSimulationArrival\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert txtResultArrival != null : "fx:id=\"txtResultArrival\" was not injected: check your FXML file 'AirCapMgt.fxml'.";

    }

	public void setModel(Model m)
	{
		if(airportBox.getItems()!=null)
			airportBox.getItems().clear();
		if(meseBox.getItems()!=null)
			meseBox.getItems().clear();
		this.m = m;
		airportBox.getItems().addAll(m.getAirports());
		meseBox.getItems().addAll(Month.values());
		giornoBox.getItems().addAll(DayOfWeek.values());
		Integer[] hours = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
		startBox.getItems().addAll(hours);
		endBox.getItems().addAll(hours);
	
				
		colDepartureCarrier.setCellValueFactory(new PropertyValueFactory<Flight,String>("carrier"));
	    colDepartureFlightNum.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("flightNum"));	
	    colDepartureDestination.setCellValueFactory(new PropertyValueFactory<Flight,String>("airportDescr"));	
	    colDepartureTime.setCellValueFactory(new PropertyValueFactory<Flight,Time>("departureTime"));	
	    colDepartureEsito.setCellValueFactory(new PropertyValueFactory<Flight,String>("esito"));	
	    
	    configureTabConfigDeparture();
	    
	    colArrivalCarrier.setCellValueFactory(new PropertyValueFactory<Flight,String>("carrier"));
	    colArrivalFlightNum.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("flightNum"));	
	    colArrivalFrom.setCellValueFactory(new PropertyValueFactory<Flight,String>("airportDescr"));	
	    colArrivalTime.setCellValueFactory(new PropertyValueFactory<Flight,Time>("arrivalTime"));	
	    colArrivalEsito.setCellValueFactory(new PropertyValueFactory<Flight,String>("esito"));	

	
	}
	private void configureTabConfigDeparture() {

		tabConfigDeparture.getItems().add(m.getConfigDeparture());
	    colMinArrivalTime.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minAdvanceArrival"));	
	    colMinArrivalTime.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMinArrivalTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinAdvanceArrival(event.getNewValue());				
			}
		});
	    colMaxArrivalTime.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxAdvanceArrival"));	
	    colMaxArrivalTime.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxArrivalTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxAdvanceArrival(event.getNewValue());				
			}
		});

		
	}
}
