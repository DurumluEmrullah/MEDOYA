package classes;

public class NumericToNominal {

    public static String dateNominal(int date){
        String kalan="";
        if(date>=0 && date<26) {
            kalan ="SON";
        }

        if(date>=26 && date<53) {
            kalan="ORTA";
        }
        if(date>=53 && date<90) {
            kalan="BAS";
        }
        return kalan;
    }
    public static String repNominal(int rep,int like) {
        String sinif;
        float percent = ((float)rep/(float)like)*100;
        //System.out.println(percent);
        if(percent <0.5)
            sinif ="COKAZ";
        else if(percent <1)
            sinif ="AZ";
        else if(percent<7)
            sinif="ORTA";
        else if(percent<25)
            sinif="YUKSEK";
        else
            sinif="COKYUKSEK";
        return sinif;
    }

    public static String rtNominal(int rt,int like) {
        String sinif;
        float percent = ((float)rt/(float)like)*100;
        //System.out.println(percent);
        if(percent <0.5)
            sinif ="COKAZ";
        else if(percent <1)
            sinif ="AZ";
        else if(percent<7)
            sinif="ORTA";
        else if(percent<25)
            sinif="YUKSEK";
        else
            sinif="COKYUKSEK";
        return sinif;
    }

    public static String likeNominal(int like,int followers) {
        String sinif;
        float percent = ((float)like/(float)followers)*100;
        //System.out.println(percent);
        if(percent <0.5)
            sinif ="COKAZ";
        else if(percent <1)
            sinif ="AZ";
        else if(percent<7)
            sinif="ORTA";
        else if(percent<25)
            sinif="YUKSEK";
        else
            sinif="COKYUKSEK";

        return sinif;
    }
    public static String followerNominal(int followers) {
        String sinif;
        if(followers<500) {
            sinif="COKAZ";
        }else if(followers<15000) {
            sinif="AZ";
        }else if(followers<250000) {
            sinif="ORTA";
        }else if(followers<1500000) {
            sinif="YUKSEK";
        }else {
            sinif="COKYUKSEK";
        }

        return sinif;
    }

    public static String valueNominal(int value) {
        if(value <0)
            value =0;
        String sinif;
        if(value ==0)
            sinif="BONSERVISYOK";
        else if(value <=1000000)
            sinif="DUSUK";
        else if(value <= 7000000)
            sinif="ORTA";
        else if(value <= 15000000)
            sinif="YUKSEK";
        else
            sinif="COKYUKSEK";
        return sinif;
    }
}
