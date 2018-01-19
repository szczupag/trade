package sample;

import java.io.Serializable;

public class ObiektGieldy implements Serializable{
    private int id;
    private String nazwa;
    private String rodzaj;

    public ObiektGieldy(){
        this.rodzaj=this.getClass().getSimpleName();
        this.id = Ekonomia.generujId();
        Ekonomia.dodajDoWszystkiego(this);
    }

    public int getId(){
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String a) {this.nazwa = a;}

    //!!!
    public String getRodzaj(){ return rodzaj;}

    public String wyswietlSzczegoly(){
        String text = ("\t"+this.getRodzaj()+"\nid:\n\t"+this.getId()+"\nnazwa:\n\t"+this.getNazwa());
        return text;
    }

}
