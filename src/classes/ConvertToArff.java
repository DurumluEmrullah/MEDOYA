package classes;

import entities.Haber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// backup
public  class ConvertToArff {

    public void convertTrainFile(ArrayList<Haber> entities) throws IOException {

        File file = new File("train.arff");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        readFile(bWriter,entities);
    }

    public  void convertTestFile(ArrayList<Haber> entities) throws IOException{
        File file = new File("test.arff");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        readFile(bWriter,entities);
    }

    public void convertOneStepTestFile(Haber entity) throws IOException{
        File file = new File("OneStepTest.arff");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        ArrayList<Haber> tweets= new ArrayList<>();
        tweets.add(entity);
        readFile(bWriter,tweets);
    }

    private void readFile( BufferedWriter bWriter,ArrayList<Haber> entities) throws IOException {
        bWriter.write("@RELATION test\n" +
                "@ATTRIBUTE \uFEFFcevaplar {AZ,COKAZ,COKYUKSEK,ORTA,YUKSEK}\n" +
                "@ATTRIBUTE retweet {AZ,COKAZ,COKYUKSEK,ORTA,YUKSEK}\n" +
                "@ATTRIBUTE like {AZ,COKAZ,COKYUKSEK,ORTA,YUKSEK}\n" +
                "@ATTRIBUTE kullanici {ASpor,AhmetKonanc,BBOSports,BesiktasSport,BursasporHaberlerii,CimbomFacts,Doktor,Duhuliye,ErsinSezer,FENERBAHCE,FenerEditor,Fenerkolik,Feneronline,GUTI,GalataSarayiEfendileri,GalatasaraydanSonDakikalar,GokmenOzcan,Goztepe.Net,GoztepeTV,HaberKartal,HakanDede,HoloskoReyiz,KramponSpor,KramponluPisagor,MedyaFener,NTVSpor#MaskeniTak,ObjektifSpor,SiyahCoraplilar,Siyahcoraplilar,SoccerCimbom,SonerKan,TRTSpor,Taraftar,TrScouts,TrabzonsporGazetesi,TransferHaberleri,beINSPORTSTurkiye,cArsi,oMERBARLIK}\n" +
                "@ATTRIBUTE date {BAS,ORTA,SON}\n" +
                "@ATTRIBUTE futbolcu {Abdulkadiromur,Aboubakar,AdemBuyuk,AlexanderSorloth,AllahyarSayyadmanesh,AnasTahiri,AnastasiosBakasetas,AndersonTalisca,Andre,AntonioColak,ArdaTuran,Bakasetas,BalazsMegyeri,Balotelli,BarisSungur,Bastos,Begovic,BenjaminTetteh,BerkanİsmailKutlu,BerkayPolat,Berkayozcan,BrahimDarri,BrownIdeye,BurakYilmaz,CameronJerome,CanerErkin,Cengizunder,CenkTosun,Cenkozkacar,ChrisSmalling,Cisse,DaCosta,DaniloBarthel,DarylMacon,DavidSilva,DenizKadah,DiegoGodin,DiegoPerotti,DimitriosLimnios,DomagojVida,DyshawnPierre,DzenanBurekovic,EdinVisca,EdinsonCavani,EdisonCavani,EfkanBekiroglu,EmreKilinc,EnnerValencia,EnzoRoco,EranZahavi,EricMaximChoupo-Moting,Fatihozturk,FernandoZuqui,FodaKoita,FranciscoMontero,GarryRodrigues,Giuliano,GokhanGonul,GokhanTore,Guilherme,HasanAliKaldirim,HugoRodallega,JanVertonghen,JeanMichaëlSeri,JeremainLens,JesseSekidika,JimmyDurmaz,JodyLukoki,JohnathanHamilton,JoseSosa,JosefDeSouza,JosefdeSouza,KaiHavertz,KeremAkturkoglu,KeremCanAkyuz,KevinStrootman,KostasSloukas,KévinGameiro,LewisBaker,LoicRemy,LucasBiglia,LusizGustavo,MalangSarr,MameThiam,Mandzukic,ManuelFernandes,Marcao,MarcelTisserand,MarioBalotelli,MauricioLemos,MbwanaSamatta,MertHakanYandas,MichaelFrey,MihaZajc,Milik,MirceaLucescu,Moha,MuhammedBayir,MuhammetTahaTepe,MustafaKapi,NabilDirar,NazimSangare,NevenSubotic,NicolaKalinic,ObinnaNwobodo,OghenekaroEtebo,Ogulcancaglayan,OguzhanBerber,OkayYokuslu,OliverGiroud,PapissCisse,PapissDembaCissé,RadjaNainggolan,Rafael,Rizacalimbay,RyanDonk,SalomónRondón,SamuelKalu,Sedatsahinturk,SemihKaya,SinanGumus,SonerAydogdu,StefanoOkaka,SteliosKitsiou,TolgaCigerci,TolgayArslan,Ugurcancakir,Ulanovas,ValentinRosier,VedatMuriqi,VincentAboubakar,ZeLuis,omerErdogan,omerFarukBeyaz}\n" +
                "@ATTRIBUTE İddiaedilentakım {AdanaDemirspor,Alanyaspor,Antalyaspor,Avrupa,AytemizAlanyaspor,Basaksehir,Benfica,Besiktas,Boluspor,Chelsea,DemirGrupSivasspor,Denizlispor,DinamoKiev,Fenerbahce,Fulham,Galatasaray,Gaziantep,Goztepe,Hamburg,Hatayspor,Kasimpasa,Kozanspor,Kulupsuz,Lazio,Leipzig,Lille,Lyon,Malatyaspor,Napoli,Olimpiakos,Olimpiyakos,PSV,Rennes,Roma,Samsunspor,Sivasspor,Toulouse,Trabzonspor,UdineseCalcio,Y.Malatyaspor,YeniMalatyaSpor,kulupsuz}\n" +
                "@ATTRIBUTE degeri {BONSERVISYOK,COKYUKSEK,DUSUK,ORTA,YUKSEK}\n" +
                "@ATTRIBUTE haberkaynağı {FanSayfasi,Gazeteci,SporKanali,SporSitesi,Taraftar}\n" +
                "@ATTRIBUTE takipcisayısı {AZ,COKAZ,COKYUKSEK,ORTA,YUKSEK}\n" +
                "@ATTRIBUTE class {BY,BZY,D}\n"+
                "@DATA\n");
        System.out.println(entities.size());
        for(int i=0;i<entities.size();i++){
            bWriter.write(entities.get(i).getCevaplar()+","+
                    entities.get(i).getRt()+","+
                    entities.get(i).getLike()+","+
                    entities.get(i).getKullanici()+","+
                    entities.get(i).getDate()+","+
                    entities.get(i).getFutbolcu()+","+
                    entities.get(i).getIddiaEdilenKulup()+","+
                    entities.get(i).getDegeri()+","+
                    entities.get(i).getHaberKaynagi()+","+
                    entities.get(i).getTakipciSayisi()+","+ entities.get(i).getSinif()+"\n"
            );
        }
        bWriter.close();
    }
}
