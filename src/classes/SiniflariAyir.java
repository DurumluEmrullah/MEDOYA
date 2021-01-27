package classes;

import entities.Haber;

import java.util.ArrayList;

public class SiniflariAyir {

    public static ArrayList<ArrayList<Haber>> siniflar(ArrayList<Haber> data){
        ArrayList<ArrayList<Haber>> siniflar = new ArrayList<>();
        ArrayList<Haber> dogruHaberler = new ArrayList<>();
        ArrayList<Haber> bilincsizYanlisHaberler=new ArrayList<Haber>();
        ArrayList<Haber> bilincliYanlisHaberler=new ArrayList<Haber>();
        siniflar.add(dogruHaberler);
        siniflar.add(bilincliYanlisHaberler);
        siniflar.add(bilincsizYanlisHaberler);

        for (int i = 0 ; i< data.size();i++){

            if(data.get(i).getSinif().trim().equals("BZY")){
                bilincsizYanlisHaberler.add(data.get(i));
            }
            if(data.get(i).getSinif().trim().equals("BY")){
                bilincliYanlisHaberler.add(data.get(i));
            }
            if(data.get(i).getSinif().trim().equals("D")){
                dogruHaberler.add(data.get(i));
            }
        }


        return siniflar;
    }
}
