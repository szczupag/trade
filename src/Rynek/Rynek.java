package Rynek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import sample.Ekonomia;
import sample.Losuj;
import sample.ObiektGieldy;
import Kupujacy.Kupujacy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rynek extends ObiektGieldy {
    private String nazwa;
    private float kurs,poprzedniKurs;
    private XYChart.Series seria;
    private float cenaSprzedazy;
    private float zmiana;
    private ObservableList<Float> listaZmian;
    private ObservableList<XYChart.Series<Integer,Float>> lista;
    private Kupujacy wlasciciel;
    private String nazwaWlasciciela;

    public Rynek(){
        wlasciciel=null;
        nazwaWlasciciela="";
        this.kurs= Losuj.losujKurs();
        this.poprzedniKurs=this.kurs;
        listaZmian= FXCollections.observableArrayList();
        seria = new XYChart.Series();
    }
    public void zwiekszKurs(){
        this.seria.getData().add(new XYChart.Data(new Integer(Ekonomia.getLicznik()).toString(),kurs));
        this.poprzedniKurs=this.kurs;
        float nowykurs;
        if(this.getClass().getSimpleName().equals("Surowiec")){
            nowykurs = this.kurs+Losuj.losujKurs()*1000;
        }
        else {
            nowykurs = this.kurs+ Losuj.losujKurs()/100F;
        }
       float zmiana = (new BigDecimal((nowykurs-this.kurs)/this.kurs*100F).setScale(3, RoundingMode.HALF_UP)).floatValue();
        this.addListaZmian(zmiana);
        this.setKurs(nowykurs);
        this.setZmiana(zmiana);
    }

    public void zmniejszKurs(){
        this.seria.getData().add(new XYChart.Data(new Integer(Ekonomia.getLicznik()).toString(),kurs));
        this.poprzedniKurs=this.kurs;
        float nowykurs;
        if(this.getClass().getSimpleName().equals("Surowiec")){
            nowykurs = this.kurs-Losuj.losujInt((int)(this.kurs/10));
        }
        else {
            nowykurs = this.kurs-Losuj.losujKurs()/100F;
        }
        float zmiana = (new BigDecimal((nowykurs-this.kurs)/this.kurs*100F).setScale(3, RoundingMode.HALF_UP)).floatValue();
        this.addListaZmian(zmiana);
        this.setKurs(nowykurs);
       this.setZmiana(zmiana);

    }

    public void setKupujacy(Kupujacy kupil){ this.wlasciciel=kupil;}

    public Kupujacy getWlasciciel() { return wlasciciel; }

    public float getKurs() {
        return kurs;
    }

    public void setKurs(float kurs) {
        this.kurs = (new BigDecimal(kurs).setScale(3, RoundingMode.HALF_UP)).floatValue();
    }

    public float getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(float cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    public ObservableList<Float> getListaZmian() { return listaZmian; }

    public void addListaZmian(Float cena){
        this.listaZmian.add(cena);
    }

    public void setWlasciciel(Kupujacy wlasciciel) {  this.wlasciciel = wlasciciel;  }

    public String getNazwaWlasciciela() {  return nazwaWlasciciela;  }

    public void setNazwaWlasciciela(String nazwaWlasciciela) {
        this.nazwaWlasciciela = nazwaWlasciciela;
    }

   public float getZmiana() { return zmiana; }

   public void setZmiana(float zmiana) { this.zmiana = zmiana; }

    public float getPoprzedniKurs() {
        return poprzedniKurs;
    }

    public void setPoprzedniKurs(float poprzedniKurs) {
        this.poprzedniKurs = poprzedniKurs;
    }

    public XYChart.Series getSeria() {
        return seria;
    }
}