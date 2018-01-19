package Kupujacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Losuj;
import sample.Ekonomia;

public class FunduszInwestycyjny extends Kupujacy{
    private String zarzadca;
    private String zysk;
    private ObservableList<Float> zmianaZysku;

    public FunduszInwestycyjny(){
        this.zmianaZysku = FXCollections.observableArrayList();
        this.zarzadca=this.getImie()+" "+getNazwisko();
        this.setNazwa(Losuj.losujNazweFunduszu());
        Ekonomia.dodajDoFunduszy(this);
    }

    public String wyswietlSzczegoly(){
        String text = (
                "\t"+"Fundusz Inwestycyjny"+
                "\nid:\n\t"+this.getId()+
                "\nnazwa:\n\t"+this.getNazwa()+
                "\nzarządca:\n\t"+this.zarzadca+
                "\nbudżet:\n\t"+this.getBudzet()+
                "\nilość jednostek:\n\t"+this.getLiczbaJednostek()
        );
        return text;
    }

    public String getZarzadca() { return zarzadca; }

    public String getZysk() {
        if (zmianaZysku.size()>0){
            if(zmianaZysku.get(zmianaZysku.size()-1)>=0){
                return "+"+zmianaZysku.get(zmianaZysku.size()-1)+"%";
            }else return "-"+zmianaZysku.get(zmianaZysku.size()-1)+"%";
        }
        else return "";
    }
}
