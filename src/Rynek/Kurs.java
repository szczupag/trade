package Rynek;

import Rynek.Waluta;
import sample.Ekonomia;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kurs {
    private Waluta waluta1, waluta2;
    private String nazwa;
    private Float kurs;
    private float zmiana;
    public Kurs(Waluta waluta1,Waluta waluta2){
        this.waluta1=waluta1;
        this.waluta2=waluta2;
        this.nazwa=waluta1.getNazwa()+"/"+waluta2.getNazwa();
        this.kurs=(new BigDecimal(Float.toString(waluta1.getKurs()/waluta2.getKurs())).setScale(3, RoundingMode.HALF_UP)).floatValue();
        this.zmiana=0F;
        Ekonomia.dodajDoKursow(this);
    }

    public String getNazwa() {
        return nazwa;
    }

    public Waluta getWaluta1() {
        return waluta1;
    }

    public Waluta getWaluta2() { return waluta2; }

    public Float getKurs() {
        return (new BigDecimal(Float.toString(waluta1.getKurs()/waluta2.getKurs())).setScale(3, RoundingMode.HALF_UP)).floatValue();
    }

    public Float getPoprzedniKurs(){
        return (new BigDecimal(Float.toString(waluta1.getPoprzedniKurs()/waluta2.getPoprzedniKurs())).setScale(3, RoundingMode.HALF_UP)).floatValue();
    }

    public Float getZmiana() {
        return (new BigDecimal((getKurs()-getPoprzedniKurs())/getPoprzedniKurs()*100F).setScale(3,RoundingMode.HALF_UP)).floatValue();
    }
}
