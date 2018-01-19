package controller;

import Kupujacy.FunduszInwestycyjny;
import Kupujacy.Inwestor;
import Kupujacy.Kupujacy;
import Rynek.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;
import javafx.scene.control.ListView;

import java.util.Set;

public class MenuController {

    Thread odswiezanieContentu;

    private volatile boolean czas;

    //private MainController mainController;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private LineChart<?,?> lineChart;


    //__tabele
    @FXML private ListView<String> ListaWydarzen;
    @FXML private TableView<ObiektGieldy> TabWszystkie;
    @FXML private TableView<Waluta> TabWaluty, TabRynekWaluty;
    @FXML private TableView<Inwestor> TabInwestorzy;
    @FXML private TableView<Spolka> TabSpolki;
    @FXML private TableView<Akcja> TabAkcje, TabWszystkieAkcje, TabWykupioneAkcje;
    @FXML private TableView<FunduszInwestycyjny> TabFundusze;
    @FXML private TableView<Kurs> kursWalut;
    @FXML private TableView<Surowiec> TabRynekSurowce;
    //__kolumny obiekt
    @FXML private TableColumn<ObiektGieldy,String> TabWszystkieNazwa, TabWszystkieRodzaj;
    @FXML private TableColumn<ObiektGieldy,Integer> TabWszystkieId;
    //__kolumny inwestorzy
    @FXML private TableColumn<Inwestor,Integer> TabInwestorzyId, TabInwestorzyBudzet;
    @FXML private TableColumn<Inwestor,Long> TabInwestorzyPesel;
    @FXML private TableColumn<Inwestor,String> TabInwestorzyImie, TabInwestorzyNazwisko;
    //__kolumny fundusze
    @FXML private TableColumn<FunduszInwestycyjny,Integer> TabFundId,TabFundBud,TabFundJed;
    @FXML private TableColumn<FunduszInwestycyjny,String> TabFundNaz, TabFundZar, TabFundZys;
    //__kolumny waluty
    @FXML private TableColumn<Waluta,Float> TabRynekWalutyKurs, TabWalutyKurs, TabRynekWalutyZm;
    @FXML private TableColumn<Waluta,String> TabRynekWalutyNazwa, TabWalutyNazwa;
    @FXML private TableColumn<Waluta,Integer> TabRynekWalutyId, TabWalutyId;
    @FXML private TableColumn<Waluta,Set<String>> TabRynekWalutyKraje;
    //__kolumny spolki
    @FXML private TableColumn<Spolka,Integer> tabSpoId, tabSpoWycena, tabSpoLiczbaAkcji, tabSpoObroty;
    @FXML private TableColumn<Spolka,String> tabSpoNazwa;
    //__kolumny akcji
    @FXML private TableColumn<Akcja,Integer> TabAkcjeId,TabWId,TabKId;
    @FXML private TableColumn<Akcja,Float> TabAkcjeNom,TabWNom,TabAkcjeUdz,TabWUdz,TabKUdz;
    @FXML private TableColumn<Akcja,String> TabAkcjeSpo,TabWSpo,TabWZmi,TabAkcjeZm,TabKSpo,TabKZmi,TabKAkc,TabAkcjeStat;
    //__kolumny surowcow
    @FXML private TableColumn<Surowiec,Integer> TabSurId;
    @FXML private TableColumn<Surowiec,String> TabSurNaz;
    @FXML private TableColumn<Surowiec,Float> TabSurKur, TabSurZm;


    //__pole ze szczegolami
    @FXML private TextArea szczegolyText;
    @FXML private Tab tabWsz, tabInw, tabFun, tabSpo, tabWal, tabInd, tabAkc, tabRynekW, tabRynekS;

    //__kurs walut
    //@FXML private ChoiceBox<Waluta> wybierzWalute;
    @FXML private TableColumn<Kurs,String> tabW1;
    @FXML private TableColumn<Kurs,Float> tabZmiana;
    @FXML private TableColumn<Kurs,Float> tabKurs;


    @FXML public void initialize(){

        stworzContent();
        Thread licznik = new Thread(()->{
            while(true){
                try{
                    Thread.sleep(1000);
                    Ekonomia.zwiekszLicznik();
                    if(Ekonomia.getLicznik()==10)Ekonomia.rozliczenie();
                    refreshTabView();
                }catch (InterruptedException e){
                    throw new Error("Interruption",e);
                }}
        });
        licznik.start();
    }


    public void stworzContent() {

        //___OKNO WYDARZEN___
        ListaWydarzen.setItems(Ekonomia.getListaWydarzen());

        //___PANEL INFORMACYJNY___
        //______wszystkie
        TabWszystkieNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TabWszystkieId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabWszystkieRodzaj.setCellValueFactory(new PropertyValueFactory<>("rodzaj"));
        TabWszystkie.setItems(Ekonomia.getListaWszystkiego());
        //______inwestorzy
        TabInwestorzyId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabInwestorzyImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TabInwestorzyNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        TabInwestorzyPesel.setCellValueFactory(new PropertyValueFactory<>("PESEL"));
        TabInwestorzyBudzet.setCellValueFactory(new PropertyValueFactory<>("budzet"));
        TabInwestorzy.setItems(Ekonomia.getListaInwestorow());
        //______fundusze
        TabFundId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabFundNaz.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TabFundZar.setCellValueFactory(new PropertyValueFactory<>("zarzadca"));
        TabFundBud.setCellValueFactory(new PropertyValueFactory<>("budzet"));
        TabFundJed.setCellValueFactory(new PropertyValueFactory<>("liczbaJednostek"));
        TabFundZys.setCellValueFactory(new PropertyValueFactory<>("zysk"));
        TabFundusze.setItems(Ekonomia.getListaFunduszy());
        //______spolki
        tabSpoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabSpoNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        tabSpoWycena.setCellValueFactory(new PropertyValueFactory<>("zyskPoRozliczeniu"));
        tabSpoLiczbaAkcji.setCellValueFactory(new PropertyValueFactory<>("liczbaAkcji"));
        tabSpoObroty.setCellValueFactory(new PropertyValueFactory<>("obroty"));
        TabSpolki.setItems(Ekonomia.getListaSpolek());
        //______akcje
        TabAkcjeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabAkcjeNom.setCellValueFactory(new PropertyValueFactory<>("cenaAkcji"));
        TabAkcjeSpo.setCellValueFactory(new PropertyValueFactory<>("nazwaSpolki"));
        TabAkcjeUdz.setCellValueFactory(new PropertyValueFactory<>("procentUdzialu"));
        TabAkcjeZm.setCellValueFactory(new PropertyValueFactory<>("zmiana"));
        TabAkcjeStat.setCellValueFactory(new PropertyValueFactory<>("status"));
        TabAkcje.setItems(Ekonomia.getListaWszystkichAkcji());
        //______waluty
        TabWalutyNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TabWalutyId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabWalutyKurs.setCellValueFactory(new PropertyValueFactory<>("kurs"));
        TabWaluty.setItems(Ekonomia.getListaWalut());

        //___RYNEK___
        //______waluty
        TabRynekWalutyNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TabRynekWalutyId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabRynekWalutyKurs.setCellValueFactory(new PropertyValueFactory<>("kurs"));
        TabRynekWaluty.setItems(Ekonomia.getListaWalut());
        //______surowce
        TabSurId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabSurNaz.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TabSurKur.setCellValueFactory(new PropertyValueFactory<>("kurs"));
        TabSurZm.setCellValueFactory(new PropertyValueFactory<>("zmiana"));
        TabRynekSurowce.setItems(Ekonomia.getListaSurowcow());


        //___WSZYSTKIE___
        //______na sprzedaż
        TabWId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabWNom.setCellValueFactory(new PropertyValueFactory<>("cenaAkcji"));
        TabWSpo.setCellValueFactory(new PropertyValueFactory<>("nazwaSpolki"));
        TabWUdz.setCellValueFactory(new PropertyValueFactory<>("procentUdzialu"));
        TabWZmi.setCellValueFactory(new PropertyValueFactory<>("zmiana"));
        TabWszystkieAkcje.setItems(Ekonomia.getListaWystawionychAkcji());
        //______wykupione
        TabKId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TabKAkc.setCellValueFactory(new PropertyValueFactory<>("nazwaWlasciciela"));
        TabKSpo.setCellValueFactory(new PropertyValueFactory<>("nazwaSpolki"));
        TabKUdz.setCellValueFactory(new PropertyValueFactory<>("procentUdzialu"));
        TabKZmi.setCellValueFactory(new PropertyValueFactory<>("zmiana"));
        TabWykupioneAkcje.setItems(Ekonomia.getListaWykupionychAkcji());
        //______indeksy

        //___KURS WALUT___
        tabW1.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        tabKurs.setCellValueFactory(new PropertyValueFactory<>("kurs"));
        tabZmiana.setCellValueFactory(new PropertyValueFactory<>("zmiana"));
        kursWalut.setItems(Ekonomia.getListaKursow());
    }

    public synchronized void refreshTabView(){
        TabWszystkie.refresh();
        TabWaluty.refresh();
        TabRynekWaluty.refresh();
        TabInwestorzy.refresh();
        TabSpolki.refresh();
        TabAkcje.refresh();
        TabWszystkieAkcje.refresh();
        TabWykupioneAkcje.refresh();
        TabFundusze.refresh();
        kursWalut.refresh();
        szczegolyText.redo();
        TabRynekSurowce.refresh();
    }

    /*@FXML
    public void dodawanieInwestora(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/DodawanieInwestora.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        DodawanieInwController dodawanieInwController = loader.getController();
        dodawanieInwController.setMainController(mainController);
        mainController.setScreen(pane);
    }*/

    @FXML
    public void noweWydarzenie(String a){
    }

    public static void addListaObiektow(ObiektGieldy a) {
    }


    /*public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }*/

    //______________________PRZYCISKI PANELU KONTORLNEGO______________________

    @FXML
    public void saveExit(){
        Platform.exit();
    }

    //______szybkie dodawanie

    public void dodajInwestora(){  Inwestor inwestor = new Inwestor();  }

    public void dodajFundusz(){ FunduszInwestycyjny funduszInwestycyjny = new FunduszInwestycyjny(); }

    public void dodajWalute(){ Waluta waluta = new Waluta(); }

    public void dodajSpolke() {  Spolka spolka = new Spolka();  }

    public void dodajSurowiec(){ Surowiec surowiec = new Surowiec(); }

    public void dodajAkcje(){
        if(Ekonomia.getListaSpolek().isEmpty()){
            dodajSpolke();
        }else{
            Spolka spolka = Losuj.losujSpolke();
            spolka.generujNowaAkcje();
        }
    }

    //_______generuj działanie

    public void kupAkcje() {
        try {
            Inwestor inwestor = Losuj.losujInwestora();
            Akcja akcja = Losuj.losujAkcje();
            inwestor.kupAkcje(akcja);
        }catch (IllegalArgumentException e){}
    }

    public void kupSurowiec(){
        try {
            Inwestor inwestor = Losuj.losujInwestora();
            Surowiec surowiec = Losuj.losujSurowiec();
            inwestor.kupSurowiec(surowiec);
        }catch (IllegalArgumentException e){}
    }

    public void kupWalute() {
        try {
            Inwestor inwestor = Losuj.losujInwestora();
            Waluta waluta = Losuj.losujWalute();
            inwestor.kupWalute(waluta);
        } catch (IllegalArgumentException e){}
    }

    //______________________

    public void sprzedajWalute(){
        try {
            KupionaWaluta kupionaWaluta = Losuj.losujKupionaWalute();
            kupionaWaluta.getKupujacy().sprzedajWalute(kupionaWaluta);

        } catch (IllegalArgumentException e){}
    }

    public void sprzedajAkcje(){}

    public void sprzedajSurowiec(){}

    //______________________

    public void pokazWykres(){
        lineChart.getData().removeAll();
        if(tabRynekS.isSelected()){
            Surowiec surowiec = TabRynekSurowce.getSelectionModel().getSelectedItem();
            try{
                lineChart.getData().add(surowiec.getSeria());
            }catch(IllegalArgumentException e){}
        }
        else if(tabRynekW.isSelected()){
            Waluta waluta = TabRynekWaluty.getSelectionModel().getSelectedItem();
            try{
                lineChart.getData().add(waluta.getSeria());
            }catch(IllegalArgumentException e){}
        }
    }

    public void pokazSzczegoly(){
        ObiektGieldy obiekt = null;
        if(tabWsz.isSelected()){
            obiekt = TabWszystkie.getSelectionModel().getSelectedItem();
        }
        else if(tabInw.isSelected()){
            obiekt = TabInwestorzy.getSelectionModel().getSelectedItem();
        }
        else if(tabWal.isSelected()){
            obiekt = TabWaluty.getSelectionModel().getSelectedItem();
        }
        else if(tabSpo.isSelected()){
            obiekt = TabSpolki.getSelectionModel().getSelectedItem();
        }
        else if(tabAkc.isSelected()){
            obiekt = TabAkcje.getSelectionModel().getSelectedItem();
        }
        else if(tabFun.isSelected()){
            obiekt = TabFundusze.getSelectionModel().getSelectedItem();
        }
        else;
        szczegolyText.clear();
        if(obiekt != null)szczegolyText.setText(obiekt.wyswietlSzczegoly());
    }

    public void usunObiekt(){
        ObiektGieldy obiekt = null;
        if(tabWsz.isSelected()){
            obiekt = TabWszystkie.getSelectionModel().getSelectedItem();
            switch (obiekt.getClass().getSimpleName()) {
                case("Waluta"):
                    usunWalute(obiekt);
                    break;
                case("Inwestor"):
                    usunInwestora(obiekt);
                    break;
                case("FunduszInwestycyjny"):
                    usunFundusz(obiekt);
                    break;
                case("Spolka"):
                    usunSpolke(obiekt);
                    break;
                case("Akcja"):
                    usunAkcje(obiekt);
                    break;
                case("Surowiec"):
                    usunSurowiec(obiekt);
                    break;
                default:
                    break;
            }
        }else if (tabInw.isSelected()){
            obiekt = TabWszystkie.getSelectionModel().getSelectedItem();
            usunWalute(obiekt);
        }else if(tabSpo.isSelected()){
            obiekt = TabSpolki.getSelectionModel().getSelectedItem();
            usunSpolke(obiekt);
        }
        else if(tabAkc.isSelected()){
            obiekt = TabAkcje.getSelectionModel().getSelectedItem();
            usunAkcje(obiekt);
        }
        else if(tabFun.isSelected()){
            obiekt = TabFundusze.getSelectionModel().getSelectedItem();
            usunFundusz(obiekt);
        }
        Ekonomia.dodajDoWydarzen("Usunięto obiekt "+obiekt.getClass().getSimpleName());

    }
        /*
        else if(tabInw.isSelected()){
            obiekt = TabInwestorzy.getSelectionModel().getSelectedItem();
        }
        else if(tabWal.isSelected()){
            obiekt = TabWaluty.getSelectionModel().getSelectedItem();
            usunWalute(obiekt);
        }
        else if(tabSpo.isSelected()){
            obiekt = TabSpolki.getSelectionModel().getSelectedItem();
        }
        else if(tabAkc.isSelected()){
            obiekt = TabAkcje.getSelectionModel().getSelectedItem();
        }
        else (tabFun.isSelected()){
            obiekt = TabFundusze.getSelectionModel().getSelectedItem();
        }*/
        //Ekonomia.usunObiekt(obiekt);


    public void usunWalute(ObiektGieldy obiekt){
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaWalut().remove(obiekt);
        for (int i = 0; i < Ekonomia.getListaKursow().size(); i++) {
            if (obiekt == Ekonomia.getListaKursow().get(i).getWaluta1() || obiekt == Ekonomia.getListaKursow().get(i).getWaluta2()){
                Ekonomia.getListaKursow().remove(Ekonomia.getListaKursow().get(i));
            }
        }
        for(Kupujacy kupujacy:Ekonomia.getListaKupujacych()){
            for (int i = 0; i < kupujacy.getListaKupionychWalut().size() ; i++) {
                if(kupujacy.getListaKupionychWalut().get(i).getWaluta()==obiekt){
                    kupujacy.getListaKupionychWalut().remove(kupujacy.getListaKupionychWalut().get(i));
                }
            }
        }
    }

    public void usunInwestora(ObiektGieldy obiekt) {
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaInwestorow().remove(obiekt);
        Ekonomia.getListaKupujacych().remove(obiekt);
        //obiekt = new ObiektGieldy();
        //Ekonomia.getListaWszystkiego().remove(obiekt);
        //Ekonomia.decId();
    }

    public void usunFundusz(ObiektGieldy obiekt){
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaFunduszy().remove(obiekt);
        Ekonomia.getListaKupujacych().remove(obiekt);
    }

    public void usunSpolke(ObiektGieldy obiekt){
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaSpolek().remove(obiekt);
        for (int i = 0; i <Ekonomia.getListaWszystkichAkcji().size() ; i++) {
            if(Ekonomia.getListaWszystkichAkcji().get(i).getSpolka()==obiekt){
                usunAkcje(Ekonomia.getListaWszystkichAkcji().get(i));
            }
        }
    }

    public void usunAkcje(ObiektGieldy obiekt){
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaWszystkichAkcji().remove(obiekt);
        Ekonomia.getListaWystawionychAkcji().remove(obiekt);
        Ekonomia.getListaWykupionychAkcji().remove(obiekt);
        for(Kupujacy kupujacy:Ekonomia.getListaKupujacych()){
            kupujacy.getListaKupionychAkcji().remove(obiekt);
        }
    }

    public void usunSurowiec(ObiektGieldy obiekt){
        Ekonomia.getListaWszystkiego().remove(obiekt);
        Ekonomia.getListaSurowcow().remove(obiekt);
        for(Kupujacy kupujacy:Ekonomia.getListaKupujacych()){
            kupujacy.getListaKupionychSurowcow().remove(obiekt);
        }
    }

    public void rozliczenie(){
         System.out.println("tu wstrzymane");
         Ekonomia.rozliczenie();
         System.out.println("tu wznowione");
    }

}
