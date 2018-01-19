package Kupujacy;

import Rynek.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Losuj;
import sample.Ekonomia;
import sample.ObiektGieldy;

public class Kupujacy extends ObiektGieldy implements Runnable{

    private String imie, nazwisko;
    private int budzet;
    private ObservableList<Akcja> listaKupionychAkcji;
    private ObservableList<KupionaWaluta> listaKupionychWalut;
    private ObservableList<KupionySurowiec> listaKupionychSurowcow;
    private int liczbaJednostek;

    public Kupujacy(){
        this.liczbaJednostek = 0;
        this.imie = Losuj.losujImie();
        this.nazwisko = Losuj.losujNazwisko(this.imie);
        this.budzet = Losuj.losujBudzet();
        listaKupionychAkcji = FXCollections.observableArrayList();
        listaKupionychWalut = FXCollections.observableArrayList();
        listaKupionychSurowcow = FXCollections.observableArrayList();

    }

    //dodac marżę!
    public synchronized void kupAkcje(Akcja akcja){
        synchronized (akcja){
            if (this.getBudzet() > akcja.getKurs()) {
                this.setBudzet(this.budzet - (int) akcja.getKurs());
                akcja.getSpolka().zwiekszPrzychod((int) akcja.getCenaAkcji());
                Ekonomia.usunKupionaAkcje(akcja);
                akcja.setKupujacy(this);
                akcja.setNazwaWlasciciela(this.getNazwa());
                akcja.setStatus("wykupiona");
                Ekonomia.dodajDoWydarzen(this.getClass().getSimpleName() + " " + this.getNazwa() + " kupił akcję spółki " + akcja.getSpolka().getNazwa() + " za kwotę " + akcja.getCenaAkcji());
                listaKupionychAkcji.add(akcja);
                akcja.getSpolka().setObroty(akcja.getSpolka().getObroty()+(int)akcja.getKurs());
                Platform.runLater(this);
            }
        }
    }
    public void kupSurowiec(Surowiec surowiec){
        synchronized (surowiec){
            int doWydania = Losuj.losujBudzet(this.budzet);
            float iloscSurowca = doWydania/surowiec.getKurs();
            int cenaKupna = doWydania;//+marza
            this.setBudzet(this.budzet-cenaKupna);
            boolean czyjest=true;
            for(KupionySurowiec surowiecNaLiscie:listaKupionychSurowcow){
                if(surowiec.equals(surowiecNaLiscie.getSurowiec())){
                    surowiecNaLiscie.setIlosc(surowiecNaLiscie.getIlosc()+iloscSurowca);
                    czyjest=false;
                    break;
                }
            }
            if(czyjest){
                KupionySurowiec kupionySurowiec = new KupionySurowiec(surowiec,iloscSurowca);
                listaKupionychSurowcow.add(kupionySurowiec);
            }
            Ekonomia.dodajDoWydarzen(this.getClass().getSimpleName()+" "+this.getNazwa()+" kupił "+iloscSurowca+" jednostek surowca "+surowiec.getNazwa());
            surowiec.zwiekszKurs();
        }
    }

    //dodac marżę!!
    public void kupWalute(Waluta waluta){
        synchronized (waluta){
            int doWydania = Losuj.losujBudzet(this.budzet/3);
            float iloscWaluty = doWydania/waluta.getKurs();
            int cenaKupna = doWydania; //+marża
            this.setBudzet(this.budzet-cenaKupna);
            boolean czyjest=true;
            for (KupionaWaluta walutaNaLiscie:listaKupionychWalut) {
                if(waluta.equals(walutaNaLiscie.getWaluta())){
                    walutaNaLiscie.setIle(walutaNaLiscie.getIle()+iloscWaluty);
                    czyjest=false;
                    break;
                }
            }
            if (czyjest){
                KupionaWaluta kupionaWaluta = new KupionaWaluta(waluta,iloscWaluty,this);
                Ekonomia.getListaKupionychWalut().add(kupionaWaluta);
                listaKupionychWalut.add(kupionaWaluta);
            }
            Ekonomia.dodajDoWydarzen(this.getClass().getSimpleName()+" "+this.getNazwa()+" kupił "+iloscWaluty+" jednostek waluty "+waluta.getNazwa());
            waluta.zwiekszKurs();
        }
    }

    public void sprzedajWalute(KupionaWaluta kupionaWaluta){
        int i = (int)(this.budzet+kupionaWaluta.getIle()*kupionaWaluta.getWaluta().getKurs());
        this.setBudzet(i);
        Ekonomia.getListaKupionychWalut().removeAll(kupionaWaluta);
        this.getListaKupionychWalut().remove(kupionaWaluta);
        kupionaWaluta.getWaluta().zmniejszKurs();
        Ekonomia.dodajDoWydarzen(this.getClass().getSimpleName()+" "+this.getNazwa()+" sprzedał walutę "+kupionaWaluta.getWaluta().getNazwa()+" za "+i);
    }

    public void sprzedajSurowiec(KupionySurowiec kupionySurowiec){

    }

    public String getImie(){
        return imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public int getBudzet(){return budzet;}

    public synchronized void setBudzet(int budzet) {
        this.budzet = budzet;
    }

    public int getLiczbaJednostek(){ return this.listaKupionychAkcji.size()+this.listaKupionychWalut.size();}

    //______rozliczenie

    public synchronized void rozliczAkcje(){
        for (Akcja akcja: listaKupionychAkcji) {
            int zysk = (int)(akcja.getSpolka().getUdzialAkcjonariuszy()*(1-(akcja.getProcentUdzialu()/100F)));
            if(zysk>0)this.setBudzet(this.getBudzet()+zysk);
        }
    }

    public ObservableList<Akcja> getListaKupionychAkcji() {
        return listaKupionychAkcji;
    }

    public ObservableList<KupionaWaluta> getListaKupionychWalut() {
        return listaKupionychWalut;
    }

    public ObservableList<KupionySurowiec> getListaKupionychSurowcow() {
        return listaKupionychSurowcow;
    }

    @Override
    public void run(){
        /*while(true){
            try {
                Akcja akcja = Losuj.losujAkcje();
                this.kupAkcje(akcja);
            }catch (IllegalArgumentException e){}
        }*/
    }


}