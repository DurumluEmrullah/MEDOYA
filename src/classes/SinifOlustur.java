package classes;
import entities.Haber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



public class SinifOlustur {

    public SinifOlustur() throws IOException {


    }

    public static void trainSinifi(ArrayList<Haber> dogruHaberler, ArrayList<Haber> bilincsizYanlisHaberler, ArrayList<Haber> bilincliYanlisHaberler) throws IOException {

        int d, by, bzy;
        d= dogruHaberler.size();
        by=bilincliYanlisHaberler.size();
        bzy=bilincsizYanlisHaberler.size();
        ArrayList<Haber> training = new ArrayList<>();

        boolean [] dogru= new boolean[d];
        boolean[] bilincli = new boolean[by];
        boolean[] bilincsiz = new boolean[bzy];

        int enKucuk = bzy<(d<by?d:by)?bzy:(d<by?d:by);
        int enBuyuk = bzy>(d>by?d:by)?bzy:(d>by?d:by);
        for (int i =0;i<enKucuk;i++) {
            Random rand= new Random();
            int a =(int) rand.nextInt(enBuyuk);
            System.out.println(a);
            dogru[a%dogruHaberler.size()]=true;
            bilincli[a%bilincliYanlisHaberler.size()]=true;
            bilincsiz[a%bilincsizYanlisHaberler.size()]=true;
            training.add(dogruHaberler.get(a%dogruHaberler.size()));
            training.add(bilincliYanlisHaberler.get(a%bilincliYanlisHaberler.size()));
            training.add(bilincsizYanlisHaberler.get(a%bilincsizYanlisHaberler.size()));
        }

        ConvertToArff arff = new ConvertToArff();
        arff.convertTrainFile(training);

        testSinifiOlustur(dogruHaberler, bilincsizYanlisHaberler, bilincliYanlisHaberler, dogru, bilincli, bilincsiz);

    }

    private static void  testSinifiOlustur(ArrayList<Haber> dogruHaberler,ArrayList<Haber> bilincsizYanlisHaberler,ArrayList<Haber> bilincliYanlisHaberler,boolean []dogru,boolean []bilincli,boolean []bilincsiz) throws IOException
    {
        ArrayList <Haber> test = new ArrayList<>();

        for(int i =0;i<dogru.length;i++) {
            if(!dogru[i]) {
                test.add(dogruHaberler.get(i));
            }
        }

        for(int i =0;i<bilincli.length;i++) {
            if(!bilincli[i]) {
                test.add(bilincliYanlisHaberler.get(i));
            }
        }
        for(int i =0;i<bilincsiz.length;i++) {
            if(!bilincsiz[i])
                test.add(bilincsizYanlisHaberler.get(i));
        }

        ConvertToArff arff = new ConvertToArff();
        arff.convertTestFile(test);

    }
}
