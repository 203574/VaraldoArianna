package it.polito.tdp.ariannavaraldo.aircapmgt;

import java.net.URL;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.ariannavaraldo.core.AirportSimulator;
import it.polito.tdp.ariannavaraldo.core.ArrivalAirportSimulator;
import it.polito.tdp.ariannavaraldo.model.Airport;
import it.polito.tdp.ariannavaraldo.model.Commons;
import it.polito.tdp.ariannavaraldo.model.ConfigArrival;
import it.polito.tdp.ariannavaraldo.model.ConfigDeparture;
import it.polito.tdp.ariannavaraldo.model.Flight;
import it.polito.tdp.ariannavaraldo.model.Model;
import it.polito.tdp.ariannavaraldo.model.PersonArea;
import it.polito.tdp.ariannavaraldo.model.PersonArrival;
import it.polito.tdp.ariannavaraldo.model.PersonDeparture;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
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
    private TableColumn<Flight, Integer> colDepartureSeat;

    @FXML
    private TableColumn<Flight, Integer> colDeparturePax;

    @FXML
    private TableColumn<Flight, String> colDepartureEsito;

    @FXML
    private PieChart pieTimeDeparture;

    @FXML
    private Button btnSimulationDeparture;

    @FXML
    private TableView<ConfigArrival> tabConfigArrival;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colNumBaggageReclaimUnit;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colTimeReopenBaggageReclaimUnit;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colMinTransferArrival;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colMaxTransferArrival;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colMinTimeInBaggageArea;

    @FXML
    private TableColumn<ConfigArrival, Integer> colMaxTimeInBaggageArea;
    
    @FXML
    private TableColumn<ConfigArrival, Integer> colMinTimeForExit;

    @FXML
    private TableColumn<ConfigArrival, Integer> colMaxTimeForExit;
    
    
    @FXML
    private TableView<ConfigDeparture> tabConfigDeparture;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colPaxCheckinDesk;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colNumCheckinDesk;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colTimeReopenCheckinDesk;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colNumSecurityDesk;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinArrivalTime;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxArrivalTime;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinTimeInArrivalArea;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxTimeInArrivalArea;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinTimeForCheckin;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxTimeForCheckin;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinTimeToSecurityArea;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxTimeToSecurityArea;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinTimeForSecurityCheck;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxTimeForSecurityCheck;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colTimeInDutyfree;
    
    @FXML
    private TableColumn<ConfigDeparture, Integer> colMinTimeInEmbarc;

    @FXML
    private TableColumn<ConfigDeparture, Integer> colMaxTimeInEmbarc;


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
    private TableColumn<Flight, Integer> colArrivalSeat;

    @FXML
    private TableColumn<Flight, Integer> colArrivalPax;

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
	    	if(f==null)
	    		return;
	    	f.calculateStatistics();
	    	if(f.getStatistics()==null)
	    		return;
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
    void doArrivalSelected(MouseEvent event) 
    {
    	Flight f = tabArrival.getSelectionModel().getSelectedItem();
    	if(f==null)
    		return;
    	f.calculateArrivalStatistics();
    	if(f.getStatistics()==null)
    		return;
    	Map<Integer, Integer> mapTime = f.getStatistics().getPieArrivalAreasData();
    	ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("In arrivo", mapTime.get(1)),
                new PieChart.Data("Baggage reclaim", mapTime.get(2)),
                new PieChart.Data("Exit", mapTime.get(3)));
    	pieTimeArrival.setData(pieChartData);
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
    	AirportSimulator simulator = new AirportSimulator(m.getConfigDeparture());
    	List<Flight> departureFlights = tabDeparture.getItems().subList(0, tabDeparture.getItems().size());
		for(Flight f:departureFlights){
			f.getPersons().clear();
		}

    	try {
			simulator.startSimulation(departureFlights);
			tabDeparture.refresh();
			txtResultDeparture.clear();
			txtResultDeparture.appendText("Dalle ore\talle ore\t\tcoda checkin\t\tcoda security\t\tarea dutyfree\t\tarea imbarco\n");
			Calendar instance = Calendar.getInstance();
			int offset = instance.get(Calendar.ZONE_OFFSET);
			Time start = new Time(0*Commons.HOUR-offset);
			Time end = new Time(endBox.getValue()*Commons.HOUR-offset);
			while(start.compareTo(end)<=0){
				Time t1 = new Time(start.getTime());
				Time t2 = new Time(start.getTime()+Commons.MINUTE*14+1000*59);
				int numPaxCheckIn = 0;
				int numPaxSecurity= 0;
				int numPaxDuty= 0;
				int numPaxEmbarc= 0;
				for(Flight f:departureFlights){
					
					List<PersonDeparture> passengers = f.getPersons();
					for(PersonDeparture p : passengers){
						if(p.getArea(Commons.AREA_CODA_CHECK_IN)!=null && personInArea(t1,t2,p.getArea(Commons.AREA_CODA_CHECK_IN)))
							numPaxCheckIn++;
						if(personInArea(t1,t2,p.getArea(Commons.AREA_CODA_SECURITY)))
							numPaxSecurity++;
						if(personInArea(t1,t2,p.getArea(Commons.AREA_DUTY)))
							numPaxDuty++;
						if(personInArea(t1,t2,p.getArea(Commons.AREA_EMBARC)))
							numPaxEmbarc++;
					}
				}
				if(numPaxCheckIn+numPaxSecurity+numPaxDuty+numPaxEmbarc>0)
					txtResultDeparture.appendText(t1 + "\t" + t2 + "\t\t" + numPaxCheckIn+ "\t\t\t" + numPaxSecurity+ "\t\t\t" + numPaxDuty+ "\t\t\t" + numPaxEmbarc+"\n");
				start = new Time(start.getTime()+Commons.MINUTE*15);
			}
		} catch (Exception e) {
			txtResultDeparture.clear();
			txtResultDeparture.appendText(e.getMessage());
		}
    	
    	

    }
    
    private boolean personInArea(Time t1, Time t2, PersonArea a) {
    	
    	//t1 e t2 sono la fascia da considerare
    	if((a.getStart().compareTo(t1)>=0 && a.getStart().compareTo(t2)<0)  //inizio permanenza nell'area all'interno della fascia
				||(a.getEnd().compareTo(t1)>=0 && a.getEnd().compareTo(t2)<0) //fine permanenza nell'area all'interno della fascia
				||(a.getStart().compareTo(t1)<0 && a.getEnd().compareTo(t2)>0)) //fascia interamente compresa tra inizio e fine permanenza
    		return true;
		return false;
	}
	@FXML
    void doSimulationArrival(ActionEvent event) {
    	ArrivalAirportSimulator simulator = new ArrivalAirportSimulator(m.getConfigArrival());
    	List<Flight> arrivalFlights = tabArrival.getItems().subList(0, tabArrival.getItems().size());
		for(Flight f:arrivalFlights){
			f.getPersons().clear();
		}

    	try {
			simulator.startSimulation(arrivalFlights);
			tabArrival.refresh();
			txtResultArrival.clear();
			txtResultArrival.appendText("Dalle ore\talle ore\t\tin arrivo\t\tbaggage claim\t\texit area\n");
			Calendar instance = Calendar.getInstance();
			int offset = instance.get(Calendar.ZONE_OFFSET);
			Time start = new Time(0*Commons.HOUR-offset);
			Time end = new Time(endBox.getValue()*Commons.HOUR-offset);
			while(start.compareTo(end)<=0){
				Time t1 = new Time(start.getTime());
				Time t2 = new Time(start.getTime()+Commons.MINUTE*14+1000*59);
				int numPaxTransfer = 0;
				int numPaxBaggage= 0;
				int numPaxExit= 0;
				for(Flight f:arrivalFlights){
					
					List<PersonArrival> passengers = f.getArrivalPersons();
					for(PersonArrival p : passengers){
						if(p.getArea(Commons.AREA_BAGGAGE_RECLAIM)!=null && personInArea(t1,t2,p.getArea(Commons.AREA_BAGGAGE_RECLAIM)))
							numPaxBaggage++;
						if(personInArea(t1,t2,p.getArea(Commons.AREA_TRANSFER_TO_ARRIVAL)))
							numPaxTransfer++;
						if(personInArea(t1,t2,p.getArea(Commons.AREA_EXIT)))
							numPaxExit++;
					}
				}
				//Stampo se ha un senso
				if(numPaxBaggage+numPaxTransfer+numPaxExit>0)
					txtResultArrival.appendText(t1 + "\t" + t2 + "\t\t" + numPaxTransfer+ "\t\t\t" + numPaxBaggage+ "\t\t\t" + numPaxExit+ "\n");
				start = new Time(start.getTime()+Commons.MINUTE*15);
			}
		} catch (Exception e) {
			txtResultArrival.clear();
			txtResultArrival.appendText(e.getMessage());
		}
    	
    	


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
        assert colDepartureSeat != null : "fx:id=\"colDepartureSeat\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDeparturePax != null : "fx:id=\"colDeparturePax\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colDepartureEsito != null : "fx:id=\"colDepartureEsito\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert pieTimeDeparture != null : "fx:id=\"pieTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert btnSimulationDeparture != null : "fx:id=\"btnSimulation\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert tabConfigDeparture != null : "fx:id=\"tabConfigDeparture\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colPaxCheckinDesk != null : "fx:id=\"colPaxCheckinDesk\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colNumCheckinDesk != null : "fx:id=\"colNumCheckinDesk\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colTimeReopenCheckinDesk != null : "fx:id=\"colTimeReopenCheckinDesk\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colNumSecurityDesk != null : "fx:id=\"colNumSecurityDesk\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinArrivalTime != null : "fx:id=\"colMinArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxArrivalTime != null : "fx:id=\"colMxArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinTimeInArrivalArea != null : "fx:id=\"colMinArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxTimeInArrivalArea != null : "fx:id=\"colMxArrivalTime\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinTimeForCheckin != null : "fx:id=\"colMinTimeForCheckin\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxTimeForCheckin != null : "fx:id=\"colMaxTimeForCheckin\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinTimeToSecurityArea != null : "fx:id=\"colMinTimeToSecurityArea\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxTimeToSecurityArea != null : "fx:id=\"colMaxTimeToSecurityArea\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinTimeForSecurityCheck != null : "fx:id=\"colMinTimeForSecurityCheck\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxTimeForSecurityCheck != null : "fx:id=\"colMaxTimeForSecurityCheck\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colTimeInDutyfree != null : "fx:id=\"colTimeInDutyfree\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMinTimeInEmbarc != null : "fx:id=\"colMinTimeInEmbarc\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
        assert colMaxTimeInEmbarc != null : "fx:id=\"colMaxTimeInEmbarc\" was not injected: check your FXML file 'AirCapMgt.fxml'.";
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

        
        pieTimeDeparture.setLabelsVisible(false);
        pieTimeDeparture.setLegendSide(Side.RIGHT);
        
        
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
	    colDepartureSeat.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seats"));	
	    colDeparturePax.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("passengers"));	
	    colDepartureEsito.setCellValueFactory(new PropertyValueFactory<Flight,String>("esito"));	
	    
	    configureTabConfigDeparture();
	    
	    colArrivalCarrier.setCellValueFactory(new PropertyValueFactory<Flight,String>("carrier"));
	    colArrivalFlightNum.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("flightNum"));	
	    colArrivalFrom.setCellValueFactory(new PropertyValueFactory<Flight,String>("airportDescr"));	
	    colArrivalTime.setCellValueFactory(new PropertyValueFactory<Flight,Time>("arrivalTime"));	
	    colArrivalSeat.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seats"));	
	    colArrivalPax.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("passengers"));	
	    colArrivalEsito.setCellValueFactory(new PropertyValueFactory<Flight,String>("esito"));	

	    configureTabConfigArrival();
	
	}

	private void configureTabConfigArrival() {

		tabConfigArrival.getItems().add(m.getConfigArrival());

		colNumBaggageReclaimUnit.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("numBaggageReclaim"));	
		colNumBaggageReclaimUnit.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colNumBaggageReclaimUnit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setNumBaggageReclaim(event.getNewValue());				
			}
		});

		colTimeReopenBaggageReclaimUnit.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("timeReopenBaggageReclaim"));	
		colTimeReopenBaggageReclaimUnit.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colTimeReopenBaggageReclaimUnit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setTimeReopenBaggageReclaim(event.getNewValue());				
			}
		});

		colMinTransferArrival.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("minTimeInArrivalArea"));	
		colMinTransferArrival.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTransferArrival.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInArrivalArea(event.getNewValue());				
			}
		});

		colMaxTransferArrival.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("maxTimeInArrivalArea"));	
		colMaxTransferArrival.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMaxTransferArrival.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeInArrivalArea(event.getNewValue());				
			}
		});

		colMinTimeInBaggageArea.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("minTimeInBaggageArea"));	
		colMinTimeInBaggageArea.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeInBaggageArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInBaggageArea(event.getNewValue());				
			}
		});
		
		
		colMaxTimeInBaggageArea.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("maxTimeInBaggageArea"));	
		colMaxTimeInBaggageArea.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMaxTimeInBaggageArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
						).setMaxTimeInBaggageArea(event.getNewValue());				
			}
		});

		colMinTimeForExit.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("minTimeInExitArea"));	
		colMinTimeForExit.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeForExit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInExitArea(event.getNewValue());				
			}
		});
		
		
		colMaxTimeForExit.setCellValueFactory(new PropertyValueFactory<ConfigArrival,Integer>("maxTimeInExitArea"));	
		colMaxTimeForExit.setCellFactory(TextFieldTableCell.<ConfigArrival,Integer>forTableColumn(new IntegerStringConverter()));
		colMaxTimeForExit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigArrival,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigArrival, Integer> event) {
				((ConfigArrival) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
						).setMaxTimeInExitArea(event.getNewValue());				
			}
		});
	}
	private void configureTabConfigDeparture() {

		tabConfigDeparture.getItems().add(m.getConfigDeparture());

		colPaxCheckinDesk.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("paxCheckInDesk"));	
		colPaxCheckinDesk.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colPaxCheckinDesk.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setPaxCheckInDesk(event.getNewValue());				
			}
		});

		colNumCheckinDesk.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("numCheckInDesk"));	
		colNumCheckinDesk.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colNumCheckinDesk.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setNumCheckInDesk(event.getNewValue());				
			}
		});

		colTimeReopenCheckinDesk.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("timeReopenCheckinDesk"));	
		colTimeReopenCheckinDesk.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colTimeReopenCheckinDesk.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setTimeReopenCheckinDesk(event.getNewValue());				
			}
		});

		colNumSecurityDesk.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("numSecurityDesk"));	
		colNumSecurityDesk.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colNumSecurityDesk.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setNumSecurityDesk(event.getNewValue());				
			}
		});
		
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
	    
		colMinTimeInArrivalArea.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeInArrivalArea"));	
		colMinTimeInArrivalArea.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeInArrivalArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInArrivalArea(event.getNewValue());				
			}
		});
	    colMaxTimeInArrivalArea.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxTimeInArrivalArea"));	
	    colMaxTimeInArrivalArea.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxTimeInArrivalArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeInArrivalArea(event.getNewValue());				
			}
		});
	    
		colMinTimeForCheckin.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeForCheckIn"));	
		colMinTimeForCheckin.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeForCheckin.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeForCheckIn(event.getNewValue());				
			}
		});
	    colMaxTimeForCheckin.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxTimeForCheckIn"));	
	    colMaxTimeForCheckin.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxTimeForCheckin.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeForCheckIn(event.getNewValue());				
			}
		});
	    
		colMinTimeToSecurityArea.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeToSecurityArea"));	
		colMinTimeToSecurityArea.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeToSecurityArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeToSecurityArea(event.getNewValue());				
			}
		});
	    colMaxTimeToSecurityArea.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxTimeToSecurityArea"));	
	    colMaxTimeToSecurityArea.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxTimeToSecurityArea.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeToSecurityArea(event.getNewValue());				
			}
		});
	    
		colMinTimeForSecurityCheck.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeForSecurityCheck"));	
		colMinTimeForSecurityCheck.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeForSecurityCheck.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeForSecurityCheck(event.getNewValue());				
			}
		});
	    colMaxTimeForSecurityCheck.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxTimeForSecurityCheck"));	
	    colMaxTimeForSecurityCheck.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxTimeForSecurityCheck.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeForSecurityCheck(event.getNewValue());				
			}
		});
	    
		colTimeInDutyfree.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeInDutyFree"));	
		colTimeInDutyfree.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colTimeInDutyfree.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInDutyFree(event.getNewValue());				
			}
		});
	    
		colMinTimeInEmbarc.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("minTimeInEmbarc"));	
		colMinTimeInEmbarc.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
		colMinTimeInEmbarc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMinTimeInEmbarc(event.getNewValue());				
			}
		});
	    colMaxTimeInEmbarc.setCellValueFactory(new PropertyValueFactory<ConfigDeparture,Integer>("maxTimeInEmbarc"));	
	    colMaxTimeInEmbarc.setCellFactory(TextFieldTableCell.<ConfigDeparture,Integer>forTableColumn(new IntegerStringConverter()));
	    colMaxTimeInEmbarc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ConfigDeparture,Integer>>() {
			@Override
			public void handle(CellEditEvent<ConfigDeparture, Integer> event) {
				((ConfigDeparture) event.getTableView().getItems().get(
						event.getTablePosition().getRow())
                        ).setMaxTimeInEmbarc(event.getNewValue());				
			}
		});

		
	}
}
