package Rynek;

import java.io.Serializable;

public class KupionySurowiec implements Serializable {
    private Surowiec surowiec;
    private float ilosc;
    public KupionySurowiec(Surowiec surowiec, float ilosc){
        this.surowiec=surowiec;
        this.ilosc=ilosc;
    }

    public Surowiec getSurowiec() {
        return surowiec;
    }

    public void setSurowiec(Surowiec surowiec) {
        this.surowiec = surowiec;
    }

    public float getIlosc() {
        return ilosc;
    }

    public void setIlosc(float ilosc) {
        this.ilosc = ilosc;
    }
}
