package Db;

import entities.Haber;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public  class DataSetDal {

    private Connection connection;
    private Statement statement;

    public DataSetDal(Connection connection,Statement statement){

        this.connection =connection;
        this.statement =statement;

    }
    public ArrayList<Haber> getData() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from dataset");
        ArrayList<Haber> tweets  = new ArrayList<>();
        while(resultSet.next()){
           // System.out.println(resultSet.getString("Cevaplar"));
            tweets.add(new Haber(resultSet.getString("Cevaplar"),
                                resultSet.getString("Retweet"),
                    resultSet.getString("like"),
                    resultSet.getString("Kullanici"),
                    resultSet.getString("date"),
                    resultSet.getString("futbolcu"),
                    resultSet.getString("iddiaedilentakım"),
                    resultSet.getString("degeri"),
                    resultSet.getString("haberkaynagi"),
                    resultSet.getString("takipcisayisi"),
                    resultSet.getString("class")
                    ));
        }

        return tweets;
    }
    public void addEntity(Haber haber) throws SQLException {
        String sorgu="INSERT INTO dataset (`Cevaplar`, `Retweet`, `like`, `Kullanici`, `date`, `futbolcu`, `İddiaedilentakım`, `degeri`, `haberkaynagi`, `takipcisayisi`, `class`) VALUES (" +
                "'"+haber.getCevaplar()+"',"+ "'"+haber.getRt()+"',"+"'"+haber.getLike()+"',"+ "'"+haber.getKullanici()+"',"+" '"+haber.getDate()+"',"+"'"+haber.getFutbolcu()+"',"+"'"+haber.getIddiaEdilenKulup()+"',"+"'"+haber.getDegeri()+"',"+ "'"+haber.getHaberKaynagi()+"',"+"'"+haber.getTakipciSayisi()+"'," +"'"+haber.getSinif()+"');";
        statement.executeUpdate(sorgu);
    }



}
