package ui;

import Db.DataSetDal;
import classes.SinifOlustur;
import classes.SiniflariAyir;
import com.mysql.cj.MysqlConnection;
import entities.Haber;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.net.search.fixed.NaiveBayes;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.experiment.DatabaseUtils;
import weka.experiment.InstanceQuery;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;



public class agiEgit extends JFrame {
    private JButton sonuclarıGorButton;
    private JTextArea sonucGor;
    private JPanel agPanel;
    private JButton sinifLariOlustur;
    String PATH;

/*    {
        try {


             InstanceQuery query = new InstanceQuery();
                query.setUsername("root");
                query.setPassword("1234");
                query.setQuery("select * from ogrenci");
            Instances data = query.retrieveInstances();


//            myCon = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme","root","1234");
//            Statement myStatee =(Statement) myCon.createStatement();
//            ResultSet myRs = myStatee.executeQuery("Select * from ogrenci");
//            while (myRs.next()){
//                System.out.println(myRs.getString("idogrenci")+myRs.getString("ogrenciAdi"));
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public agiEgit() {
        add(agPanel);
        setSize(1200,800);


        setLocation(250,50);


        sonuclarıGorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try
                    {

                        BufferedReader reader = new BufferedReader(new FileReader("train.arff"));
                        Instances train = new Instances(reader);
                        train.setClassIndex(train.numAttributes()-1);


                        reader.close();
                        MultilayerPerceptron nb = new MultilayerPerceptron();
                        nb.buildClassifier(train);

                        weka.core.SerializationHelper.write("ilk_model",nb);

                        Evaluation eval = new Evaluation(train);
                        eval.crossValidateModel(nb,train,10,new Random());
                        System.out.println(eval.pctCorrect());
                        System.out.println(eval.pctIncorrect());

                        System.out.println(eval.toSummaryString("\nResults\n=====\n",true));
                        System.out.println(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1) +eval.truePositiveRate(1));


                        ThresholdCurve tc = new ThresholdCurve();
                        int classIndex = 0;
                        Instances result = tc.getCurve(eval.predictions(), classIndex);


                        ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
                        vmc.setROCString("(Area under ROC = " +
                                Utils.doubleToString(tc.getROCArea(result), 4) + ")");
                        vmc.setName(result.relationName());
                        PlotData2D tempd = new PlotData2D(result);
                        tempd.setPlotName(result.relationName());
                        tempd.addInstanceNumberAttribute();

                        boolean[] cp = new boolean[result.numInstances()];
                        for (int n = 1; n < cp.length; n++)
                            cp[n] = true;
                        tempd.setConnectPoints(cp);
                        // add plot
                        vmc.addPlot(tempd);

                        String plotName = vmc.getName();
                        final javax.swing.JFrame jf =
                                new javax.swing.JFrame("Veri Madenciliği Proje Ödevi "+plotName);
                        jf.setSize(500,400);
                        jf.getContentPane().setLayout(new BorderLayout());
                        jf.getContentPane().add(vmc, BorderLayout.CENTER);
                        jf.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                jf.dispose();
                            }
                        });
                        jf.setVisible(true);

                        sonucGor.setVisible(true);
                        sonucGor.setText(eval.toSummaryString("\nSonuçlar \n " +
                                "============================================\n" ,true)+
                                "\nF mesure : "+eval.fMeasure(1)+"\n"+
                                eval.toClassDetailsString()+"\n"+"\n"+eval.toMatrixString());

                    }catch (Exception exp){
                        exp.printStackTrace();
                    }

                }

        });
        sinifLariOlustur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/verimadenciligi","root","1234");
                    Statement myState = connection.createStatement();
                    DataSetDal dataSetDal = new DataSetDal(connection,myState);
                    ArrayList<Haber> tweets =dataSetDal.getData();
                    ArrayList<ArrayList<Haber>> siniflar =SiniflariAyir.siniflar(tweets);
                    SinifOlustur.trainSinifi(siniflar.get(0),siniflar.get(1),siniflar.get(2));
                    JOptionPane.showMessageDialog(getContentPane(),"Sınıflar Oluşturuldu Ağı Eğite bilirsiniz !!!");

                }catch (Exception exp){
                    exp.printStackTrace();
                }

            }
        });
    }
}
