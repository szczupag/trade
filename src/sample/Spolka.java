package sample;

import Rynek.Akcja;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.lang.Thread.sleep;

public class Spolka extends ObiektGieldy implements Runnable{

    private volatile boolean generuj;
    private static float udzialwZysku = 0.3F;
    private String dataPierwszejWyceny;
    private int kursOtwarcia, kursAktualny, kursMinimalny, kursMaksymalny,
            liczbaAkcji, zyskPoRozliczeniu, przychodOdSprzedanychAkcji, kapitalWlasny, kapitalZakladowy, wolumen, obroty,
            udzialAkcjonariuszy;
    private float procent;
    private ObservableList<Integer> zmianaWarotsciSpolki;

    public Spolka(){
        this.procent = 0;
        this.zmianaWarotsciSpolki = FXCollections.observableArrayList();
        this.setNazwa(Losuj.losujNazweSpolki());
        this.dataPierwszejWyceny= Losuj.losujDate();
        this.kapitalWlasny= Losuj.losujBudzet()*100;
        this.kapitalZakladowy= Losuj.losujBudzet()*100;
        this.zyskPoRozliczeniu =0;
        this.przychodOdSprzedanychAkcji =0;
        this.wolumen=0;
        this.obroty=0;
        this.liczbaAkcji=0;
        this.udzialAkcjonariuszy=0;
        Ekonomia.dodajDoSpolek(this);
        Platform.runLater(this);
    }
    //aktualizacja wartosci
    public void update(){}

    public void generujNowaAkcje(){
        if(this.procent<100){
            float procent = Losuj.losujUdzialAkcji();
            this.procent+=procent;
            float cena = this.kapitalWlasny*procent/20F;
            Akcja akcja = new Akcja(this,procent,cena);
            this.liczbaAkcji++;
        }
    }

    public void wykupAkcje(){

    }

    public void setLiczbaAkcji(int liczbaAkcji) { this.liczbaAkcji = liczbaAkcji; }

    public String getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    public int getKursOtwarcia() {
        return kursOtwarcia;
    }

    public int getKursAktualny() { return kursAktualny; }

    public int getKursMinimalny() {
        return kursMinimalny;
    }

    public int getKursMaksymalny() {
        return kursMaksymalny;
    }

    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    public int getZyskPoRozliczeniu() {
        return zyskPoRozliczeniu;
    }

    public int getPrzychodOdSprzedanychAkcji() {
        return przychodOdSprzedanychAkcji;
    }

    public void zwiekszPrzychod(int kwota){ this.przychodOdSprzedanychAkcji +=kwota; }

    public int getKapitalWlasny() {
        return kapitalWlasny;
    }

    public int getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    public int getWolumen() {
        return wolumen;
    }

    public int getObroty() {
        return obroty;
    }

    public void setObroty(int obroty) { this.obroty = obroty; }

    public int getUdzialAkcjonariuszy() {  return udzialAkcjonariuszy;  }

    public void setZyskPoRozliczeniu(int zyskPoRozliczeniu) {  this.zyskPoRozliczeniu = zyskPoRozliczeniu; }

    public String wyswietlSzczegoly(){
        String text = (
                "\t"+this.getRodzaj()+
                "\nid:\n\t"+this.getId()+
                "\nnazwa spółki:\n\t"+this.getNazwa()+
                "\ndata pierwszej wyceny:\n\t"+this.getDataPierwszejWyceny()
        );
        return text;
    }

    //______rozliczenie

    public void rozliczAkcje(){
        int przychodZewnetrzny = (Losuj.losujBudzet())*100;
        int lacznyPrzychod = this.przychodOdSprzedanychAkcji + przychodZewnetrzny;
        float pr = 1-(procent/100F);
        float dlaAkcjonariuszy = lacznyPrzychod*this.udzialwZysku*pr;
        this.setZyskPoRozliczeniu(lacznyPrzychod-(int)dlaAkcjonariuszy);
        this.zmianaWarotsciSpolki.add(this.zyskPoRozliczeniu);
        this.udzialAkcjonariuszy=(int)dlaAkcjonariuszy;
    }

    public void run(){
        /*new Thread(()->{
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{this.generujNowaAkcje();});
        }).start();*/
    }
}
