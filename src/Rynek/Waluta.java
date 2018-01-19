package Rynek;


import sample.Losuj;
import sample.Ekonomia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Waluta extends Rynek {

    private Set<String> listaKrajow = new HashSet<>();
    public Waluta() {
        Losuj.losujListeKrajow(listaKrajow);
        this.setZmiana(0F);
        this.setNazwa(Losuj.losujNazweWaluty());
        Ekonomia.dodajDoWalut(this);
    }


    public Set<String> getListaKrajow() {
        return listaKrajow;
    }

    public String wyswietlSzczegoly(){
        String lista = "\t";
        for(String s:listaKrajow){
            lista+=s+"\n\t";
        }
        String text = (
                "\t"+this.getRodzaj()+
                "\nid:\n\t"+this.getId()+
                "\nnazwa:\n\t"+this.getNazwa()+
                "\nkurs:\n\t"+this.getKurs()+
                "\nkraje:\n"+lista
        );
        return text;
    }
}
