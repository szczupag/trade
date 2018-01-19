package Rynek;

import Kupujacy.Kupujacy;

import java.io.Serializable;

public class KupionaWaluta implements Serializable{
    private Waluta waluta;
    private float ile;
    private Kupujacy kupujacy;
    public KupionaWaluta(Waluta waluta, float ile,Kupujacy kupujacy){
        this.waluta=waluta;
        this.ile=ile;
        this.kupujacy=kupujacy;
    }

    public Waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    public float getIle() {
        return ile;
    }

    public void setIle(float ile) {
        this.ile = ile;
    }

    public Kupujacy getKupujacy() {
        return kupujacy;
    }

    public void setKupujacy(Kupujacy kupujacy) {
        this.kupujacy = kupujacy;
    }
}
