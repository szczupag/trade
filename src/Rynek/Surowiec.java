package Rynek;

import sample.Ekonomia;
import sample.Losuj;

public class Surowiec extends Rynek {

    public Surowiec(){
        this.setNazwa(Losuj.losujNazweSurowca());
        this.setKurs(this.getKurs()*Losuj.losujInt(1000));
        Ekonomia.dodajDoSurowcow(this);
    }

    public String wyswietlSzczegoly(){
        String text = (
                "\t"+this.getRodzaj()+
                "\nid:\n\t"+this.getId()+
                "\nnazwa:\n\t"+this.getNazwa()+
                "\nkurs:\n\t"+this.getKurs()+
                        "\njednostka:\n\tuncja"
        );
        return text;
    }
}
