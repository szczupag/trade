package Rynek;

import Kupujacy.Kupujacy;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Ekonomia;
import sample.Spolka;

import java.beans.PropertyChangeSupport;


public class Akcja extends Rynek {
    private Spolka spolka;
    private String nazwaSpolki,status;
    private float cenaAkcji, procentUdzialu;

    public Akcja(Spolka spolka,Float procent, Float cena){
        this.spolka=spolka;
        this.nazwaSpolki=spolka.getNazwa();
        this.cenaAkcji=cena;
        this.procentUdzialu=procent;
        this.setZmiana(0F);
        this.status="na sprzedaż";
        this.setNazwa("akcja spółki "+nazwaSpolki);
        Ekonomia.dodajDoAkcji(this);
        Ekonomia.dodajDoWydarzen("Spółka "+this.nazwaSpolki+" wypuściła nową akcję o nominale "+this.cenaAkcji);
    }
    public String wyswietlSzczegoly(){
        String text = (
                "\t"+this.getRodzaj()+
                "\nid:\n\t"+this.getId()+
                "\nspółka:\n\t"+this.getNazwaSpolki()+
                "\nnominał:\n\t"+this.getCenaAkcji()+
                "\nudział w zysku:\n\t"+this.getProcentUdzialu()+
                "\nstatus:\n\t"+this.status
        );
        return text;
    }

    public String getNazwaSpolki() { return nazwaSpolki; }

    public Spolka getSpolka() {
        return spolka;
    }

    public float getCenaAkcji() {
        return cenaAkcji;
    }

    public float getProcentUdzialu() {
        return procentUdzialu;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {  this.status = status;  }


}
