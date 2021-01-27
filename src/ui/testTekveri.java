package ui;

import Db.DataSetDal;
import classes.ConvertToArff;
import classes.NumericToNominal;
import entities.Haber;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class testTekveri extends JFrame{
    private JPanel panel1;
    private JTextArea sonuclar;
    private JTextField cevapSayisi;
    private JTextField rtSayisi;
    private JTextField likeSayisi;
    private JButton veriyiTestEtButton;
    private JTextField kacGun;
    private JTextField piyasaDegeri;
    private JComboBox hesapTipi;
    private JTextField followers;
    private JComboBox sinif;
    private JComboBox kullanici;
    private JComboBox kulup;
    private JComboBox futbolcu;

    public testTekveri(){
        add(panel1);
        setSize(1000,800);
        veriyiTestEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cvp,rt,like,date,pdeger,follower;
                String paylasan,futbolcuAdi,iddiaEdilenKulup,tipi,haberSinifi;
                cvp=Integer.parseInt(cevapSayisi.getText().trim());
                rt = Integer.parseInt(rtSayisi.getText().trim());
                like = Integer.parseInt(likeSayisi.getText().trim());
                date=Integer.parseInt(kacGun.getText().trim());
                pdeger=Integer.parseInt(piyasaDegeri.getText().trim());
                follower=Integer.parseInt(followers.getText().trim());
                paylasan = kullanici.getSelectedItem().toString();
                futbolcuAdi=futbolcu.getSelectedItem().toString();
                iddiaEdilenKulup=kulup.getSelectedItem().toString();
                tipi=hesapTipi.getSelectedItem().toString();
                haberSinifi=sinif.getSelectedItem().toString();

                Haber haber = new Haber(NumericToNominal.repNominal(cvp,like),
                        NumericToNominal.rtNominal(rt,like),
                        NumericToNominal.likeNominal(like,follower),
                        paylasan,
                        NumericToNominal.dateNominal(date),
                        futbolcuAdi,iddiaEdilenKulup,
                        NumericToNominal.valueNominal(pdeger),
                        tipi,
                        NumericToNominal.followerNominal(follower),
                        haberSinifi
                        );

                ConvertToArff arff = new ConvertToArff();
                try {
                    arff.convertOneStepTestFile(haber);

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/verimadenciligi","root","1234");
                    Statement myState = connection.createStatement();
                    DataSetDal dataSetDal = new DataSetDal(connection,myState);
                    dataSetDal.addEntity(haber);

                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
                try{
                    MultilayerPerceptron mb = (MultilayerPerceptron) weka.core.SerializationHelper.read("ilk_model");
                    BufferedReader reader = new BufferedReader(new FileReader("OneStepTest.arff"));
                    Instances test = new Instances(reader);

                    test.setClassIndex(test.numAttributes()-1);

                    double actualValue = test.instance(0).classValue();
                    Instance newInst = (Instance) test.instance(0);
                    double predYSA = mb.classifyInstance(newInst);
                    System.out.println(actualValue+" , "+predYSA);


                    Evaluation eval = new Evaluation(test);
                    // eval.crossValidateModel(mb,test,10,new Random());
                    eval.evaluateModel(mb,test);

                    System.out.println("Correct : "+eval.pctCorrect());
                    System.out.println("in correct : "+ eval.pctIncorrect());
                    System.out.println(eval.toSummaryString("\nResults\n=====\n",true));
                    System.out.println(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1) +eval.truePositiveRate(1));
                    sonuclar.setText(eval.toSummaryString("Sonuçlar\n",true)+"\n"+eval.toMatrixString()
                    );
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}
