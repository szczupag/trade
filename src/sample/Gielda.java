package sample;

import Rynek.Waluta;

import java.util.ArrayList;
import java.util.List;

public class Gielda {
    private String nazwa, kraj, miasto;
    private Waluta waluta;
    private String adres;
    private List<Indeks> listaIndeksow = new ArrayList<>();
    private int marza;

    public String getNazwa(){ return nazwa; }

    public String getKraj() {
        return kraj;
    }

    public String getMiasto() {
        return miasto;
    }

    public Waluta getWaluta() {
        return waluta;
    }

    public String getAdres() {
        return adres;
    }

    public int getMarza(){return marza;}

    public void nowaAkcja(){

    }
}
