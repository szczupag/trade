package sample;

import Kupujacy.*;
import Rynek.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Ekonomia implements Serializable{
    private static ObservableList<Inwestor> listaInwestorow = FXCollections.observableArrayList();
    private static ObservableList<Spolka> listaSpolek = FXCollections.observableArrayList();
    private static ObservableList<Waluta> listaWalut = FXCollections.observableArrayList();
    private static ObservableList<Indeks> listaIndeksow = FXCollections.observableArrayList();
    private static ObservableList<ObiektGieldy> listaWszystkiego = FXCollections.observableArrayList();
    private static ObservableList<String> listaWydarzen = FXCollections.observableArrayList();
    private static ObservableList<Kurs> listaKursow = FXCollections.observableArrayList();
    private static ObservableList<Akcja> listaWystawionychAkcji = FXCollections.observableArrayList();
    private static ObservableList<Akcja> listaWykupionychAkcji = FXCollections.observableArrayList();
    private static ObservableList<Akcja> listaWszystkichAkcji = FXCollections.observableArrayList();
    private static ObservableList<FunduszInwestycyjny> listaFunduszy = FXCollections.observableArrayList();
    private static ObservableList<Kupujacy> listaKupujacych = FXCollections.observableArrayList();
    private static ObservableList<Surowiec> listaSurowcow = FXCollections.observableArrayList();
    private static ObservableList<KupionaWaluta> listaKupionychWalut = FXCollections.observableArrayList();
    private static int id, licznik;

    public static void zwiekszLicznik(){ licznik++; }

    public static int getLicznik() {
        return licznik;
    }

    public static void dodajDoWszystkiego(ObiektGieldy a){
        listaWszystkiego.add(a);
    }

    public static void dodajDoInwestorow(Inwestor a){
        listaInwestorow.add(a);
        listaWydarzen.add("Dołączył nowy inwestor "+a.getNazwa());
        listaKupujacych.add(a);
    }

    public static void dodajDoFunduszy(FunduszInwestycyjny a){
        listaFunduszy.add(a);
        listaWydarzen.add("Pojawił się nowy fundusz "+a.getNazwa());
        listaKupujacych.add(a);
    }

    public static void dodajDoSpolek(Spolka a){
        listaSpolek.add(a);
        listaWydarzen.add("Dołączyła nowa spółka "+a.getNazwa());
    }

    public static void dodajDoWalut(Waluta a){
        for (Waluta waluta:listaWalut) {
            Kurs kurs1 = new Kurs(a,waluta);
            Kurs kurs2 = new Kurs(waluta,a);
        }
        listaWalut.add(a);
        listaWydarzen.add("Powstała nowa waluta "+a.getNazwa());
    }

    public static void dodajDoSurowcow(Surowiec a){
        listaSurowcow.add(a);
        listaWydarzen.add("Dostępny jest nowy surowiec "+a.getNazwa());
    }

    public static void dodajDoAkcji(Akcja a){
        listaWystawionychAkcji.add(a);
        listaWszystkichAkcji.add(a);
    }

    public static void usunKupionaAkcje(Akcja a){
        listaWystawionychAkcji.remove(a);
        listaWykupionychAkcji.add(a);
    }

    public static void dodajDoIndeksow(Indeks a){
        listaIndeksow.add(a);
    }

    public static void dodajDoKursow(Kurs a){ listaKursow.add(a);}

    public static void dodajDoWydarzen(String a){ listaWydarzen.add(a); }

    public static int generujId(){  return id++;  }

    public static void decId(){ id--; }

    public static void wyswietlInwestorow(){
        System.out.println("id imię naziwsko PESEL budżet");
        for (Inwestor a : listaInwestorow) {
            System.out.println(a.getId()+" "+a.getImie()+" "+a.getNazwisko()+" "+a.getPESEL()+" "+a.getBudzet());
        }
    }

    public static ObservableList<ObiektGieldy> getListaWszystkiego() {
        return listaWszystkiego;
    }

    public static ObservableList<Inwestor> getListaInwestorow() {
        return listaInwestorow;
    }

    public static ObservableList<String> getListaWydarzen() {
        return listaWydarzen;
    }

    public static ObservableList<Waluta> getListaWalut() { return listaWalut;    }

    public static ObservableList<Kurs> getListaKursow() {
        return listaKursow;
    }

    public static ObservableList<Spolka> getListaSpolek() {
        return listaSpolek;
    }

    public static ObservableList<Akcja> getListaWystawionychAkcji() { return listaWystawionychAkcji; }

    public static ObservableList<Akcja> getListaWykupionychAkcji() { return listaWykupionychAkcji; }

    public static ObservableList<Akcja> getListaWszystkichAkcji() { return listaWszystkichAkcji;  }

    public static ObservableList<FunduszInwestycyjny> getListaFunduszy() { return listaFunduszy;  }

    public static ObservableList<Kupujacy> getListaKupujacych() { return listaKupujacych; }

    public static ObservableList<Surowiec> getListaSurowcow() { return listaSurowcow; }

    public static ObservableList<KupionaWaluta> getListaKupionychWalut() {
        return listaKupionychWalut;
    }

    //_________R O Z L I C Z E N I E  O B I E K T O W
    public static void rozliczenie() {
        //rozliczenie spolek
        System.out.println("ROZLICZENIE");

        for (Spolka spolka:listaSpolek) {
            synchronized (spolka){
                spolka.rozliczAkcje();
            }
        }
        for (Inwestor inwestor:listaInwestorow) {
            synchronized (inwestor) {
                inwestor.rozliczAkcje();
            }
        }
        for (FunduszInwestycyjny fundusz:listaFunduszy) {
            synchronized (fundusz) {
                fundusz.rozliczAkcje();
            }
        }
    }


}

