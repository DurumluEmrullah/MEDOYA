package entities;



public class Haber {
    private String cevaplar;
    private String rt;
    private String like;
    private String kullanici;
    private String date;
    private String futbolcu;
    private String iddiaEdilenKulup;
    private String degeri;
    private String haberKaynagi;
    private String takipciSayisi;
    private String sinif;
    public Haber(String cevaplar, String rt, String like, String kullanici, String date, String futbolcu,
                 String iddiaEdilenKulup, String degeri, String haberKaynagi, String takipciSayisi, String sinif) {
        super();
        this.cevaplar = cevaplar;
        this.rt = rt;
        this.like = like;
        this.kullanici = kullanici;
        this.date = date;
        this.futbolcu = futbolcu;
        this.iddiaEdilenKulup = iddiaEdilenKulup;
        this.degeri = degeri;
        this.haberKaynagi = haberKaynagi;
        this.takipciSayisi = takipciSayisi;
        this.sinif = sinif;
    }
    public String getCevaplar() {
        return cevaplar;
    }
    public void setCevaplar(String cevaplar) {
        this.cevaplar = cevaplar;
    }
    public String getRt() {
        return rt;
    }
    public void setRt(String rt) {
        this.rt = rt;
    }
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public String getKullanici() {
        return kullanici;
    }
    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getFutbolcu() {
        return futbolcu;
    }
    public void setFutbolcu(String futbolcu) {
        this.futbolcu = futbolcu;
    }
    public String getIddiaEdilenKulup() {
        return iddiaEdilenKulup;
    }
    public void setIddiaEdilenKulup(String iddiaEdilenKulup) {
        this.iddiaEdilenKulup = iddiaEdilenKulup;
    }
    public String getDegeri() {
        return degeri;
    }
    public void setDegeri(String degeri) {
        this.degeri = degeri;
    }
    public String getHaberKaynagi() {
        return haberKaynagi;
    }
    public void setHaberKaynagi(String haberKaynagi) {
        this.haberKaynagi = haberKaynagi;
    }
    public String getTakipciSayisi() {
        return takipciSayisi;
    }
    public void setTakipciSayisi(String takipciSayisi) {
        this.takipciSayisi = takipciSayisi;
    }
    public String getSinif() {
        return sinif;
    }
    public void setSinif(String sinif) {
        this.sinif = sinif;
    }


}
