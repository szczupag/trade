package Kupujacy;

import sample.Losuj;
import sample.Ekonomia;

public class Inwestor extends Kupujacy{
    private Long PESEL;

    public Inwestor(){
        this.PESEL = Losuj.losujPesel();
        this.setNazwa(this.getImie()+" "+this.getNazwisko());
        Ekonomia.dodajDoInwestorow(this);
    }

    public void zwiekszBudzet(){
        this.setBudzet(this.getBudzet() + Losuj.losujBudzet());
        System.out.println("Inwestor "+this.getImie()+" "+this.getNazwisko()+" zwiększył swój budżet");
    }
    public Long getPESEL(){
        return PESEL;
    }

    public String wyswietlSzczegoly(){
        String text = (
                "\t"+this.getRodzaj()+
                "\nid:\n\t"+this.getId()+
                "\nimię i nazwisko:\n\t"+this.getNazwa()+
                "\nPESEL:\n\t"+this.getPESEL()+
                "\nbudżet:\n\t"+this.getBudzet()

        );
        return text;
    }



}