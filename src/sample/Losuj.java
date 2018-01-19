package sample;




import Kupujacy.Inwestor;
import Kupujacy.Kupujacy;
import Rynek.*;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;

public class Losuj {
    private static String[] imie = wczytajPlik("txt/imiona.txt");
    private static String[] nazwisko = wczytajPlik("txt/nazwiska.txt");
    private static String[] miasto = wczytajPlik("txt/miasta.txt");
    private static String[] kraj = wczytajPlik("txt/kraje.txt");
    private static String[] adres = wczytajPlik("txt/adresy.txt");
    private static String[] nazwaSpolki = wczytajPlik("txt/spolki.txt");
    private static String[] surowiec = wczytajPlik("txt/surowce.txt");
    private static char[] waluta = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    static Random generator = new Random();

    public static String[] wczytajPlik(String path) {
        try{
            URL url = Losuj.class.getClassLoader().getResource(path);
            File file = new File(url.getPath());
            Scanner sc = new Scanner(file);
            List<String> lines = new ArrayList<String>();
            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            String[] arr = lines.toArray(new String[0]);
            return arr;
        }catch (FileNotFoundException e){System.out.println("Błąd wczytywania pliku"+path);}
        return null;
    }

    public static String losujImie(){
        return imie[generator.nextInt(imie.length)];
    }

    public static String losujNazwisko(String imieWyl){ return nazwisko[generator.nextInt(nazwisko.length)]; }

    public static int losujBudzet(){
        return (generator.nextInt(1000)+1)*1000;
    }

    public static int losujBudzet(int max){
        return generator.nextInt(max)+1;
    }

    public static Long losujPesel(){
        return (generator.nextInt(4)+6)*10000000000L + (generator.nextInt(10)*1000000000L) + (generator.nextInt(13)*10000000L)+(generator.nextInt(30)+1)*100000+generator.nextInt(100000);
    }

    public static String losujMiasto(){
        return miasto[generator.nextInt(miasto.length)];
    }

    public static String losujKraj(){
        return kraj[generator.nextInt(kraj.length)];
    }

    public static void losujListeKrajow(Set<String> a){
        int ile = generator.nextInt(5)+1;
        for (int i=0; i<ile; i++){
            String kraj = losujKraj();
            a.add(kraj);
        }
    }

    public static String losujAdres(){
        return (generator.nextInt(9)+1)+adres[generator.nextInt(adres.length)]+" "+generator.nextInt(1000000);
    }

    public static String losujNazweSpolki(){
        //while(Ekonomia.getListaSpolek().contains())
        return nazwaSpolki[generator.nextInt(nazwaSpolki.length)];
    }

    public static String losujNazweFunduszu(){
        String pierwszeSlowo[] = (nazwaSpolki[generator.nextInt(nazwaSpolki.length)]).split(" ",2);
        return pierwszeSlowo[0]+" Investment Fund, INC.";
    }

    public static String losujNazweWaluty(){
        String a = String.valueOf(waluta[generator.nextInt(waluta.length)])+String.valueOf(waluta[generator.nextInt(waluta.length)])+String.valueOf(waluta[generator.nextInt(waluta.length)]);
        while(containsNazwaWaluty(Ekonomia.getListaWalut(),a)){
            a = String.valueOf(waluta[generator.nextInt(waluta.length)])+String.valueOf(waluta[generator.nextInt(waluta.length)])+String.valueOf(waluta[generator.nextInt(waluta.length)]);
        }
        return a;
    }

    public static boolean containsNazwaWaluty(ObservableList<Waluta> list, String name){
        return list.stream().filter(o -> o.getNazwa().equals(name)).findFirst().isPresent();
    }

    public static String losujNazweSurowca(){
        String a = surowiec[generator.nextInt(surowiec.length)];
        while(containsNazwaSurowca(Ekonomia.getListaSurowcow(),a)){
            a = surowiec[generator.nextInt(surowiec.length)];
        }
        return a;
    }

    public static boolean containsNazwaSurowca(ObservableList<Surowiec> list, String name){
        return list.stream().filter(o -> o.getNazwa().equals(name)).findFirst().isPresent();
    }

    public static String losujDate(){
        int dzien = generator.nextInt(30)+1;
        int miesiac = generator.nextInt(12)+1;
        int rok = generator.nextInt(68)+1950;
        return dzien+"-"+miesiac+"-"+rok;
    }

    public static String[] getImie() {
        return imie;
    }

    public static float losujKurs(){
        return (new BigDecimal(Float.toString(generator.nextFloat()+0.01F+generator.nextInt(5))).setScale(3, RoundingMode.HALF_UP)).floatValue();
    }

    public static float losujUdzialAkcji(){
        return (new BigDecimal(Float.toString((generator.nextFloat()+0.001F)*10F)).setScale(3, RoundingMode.HALF_UP)).floatValue();
    }

    public static Inwestor losujInwestora(){
        int ind = generator.nextInt(Ekonomia.getListaInwestorow().size());
        return Ekonomia.getListaInwestorow().get(ind);
    }

    public static Akcja losujAkcje(){
        int ind = generator.nextInt(Ekonomia.getListaWystawionychAkcji().size());
        return Ekonomia.getListaWystawionychAkcji().get(ind);
    }

    public static Waluta losujWalute(){
        int ind = generator.nextInt(Ekonomia.getListaWalut().size());
        return Ekonomia.getListaWalut().get(ind);
    }

    public static Spolka losujSpolke(){
        int ind = generator.nextInt(Ekonomia.getListaSpolek().size());
        return Ekonomia.getListaSpolek().get(ind);
    }

    public static Surowiec losujSurowiec(){
        int ind = generator.nextInt(Ekonomia.getListaSurowcow().size());
        return Ekonomia.getListaSurowcow().get(ind);
    }

    public static int losujInt(int zakres){return generator.nextInt(zakres);}

    public static Kupujacy losujKupujacego(){
        int ind = generator.nextInt(Ekonomia.getListaKupujacych().size());
        return Ekonomia.getListaKupujacych().get(ind);
    }

    public static KupionaWaluta losujKupionaWalute(){
        int ind = generator.nextInt(Ekonomia.getListaKupionychWalut().size());
        return Ekonomia.getListaKupionychWalut().get(ind);
    }



}
