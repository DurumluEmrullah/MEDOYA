package ui;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.util.Random;

public class testForm extends JFrame {
    private JPanel testPanel;
    private JButton testEtButton;
    private JTextArea testSonuclari;
    private JButton sonucGor;

    public  testForm() {
        add(testPanel);
        setSize(1200,800);


        setLocation(250,50);

        testEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MultilayerPerceptron mb = (MultilayerPerceptron) weka.core.SerializationHelper.read("ilk_model");
                    BufferedReader reader = new BufferedReader(new FileReader("test.arff"));
                    Instances test = new Instances(reader);

                    test.setClassIndex(test.numAttributes()-1);

                    double actualValue = test.instance(0).classValue();
                    Instance newInst = (Instance) test.instance(0);
                    double predYSA = mb.classifyInstance(newInst);
                    System.out.println(actualValue+" , "+predYSA);


                    Evaluation eval = new Evaluation(test);
                   // eval.crossValidateModel(mb,test,10,new Random());
                    eval.evaluateModel(mb,test);


                    testSonuclari.setText(eval.toSummaryString("Sonuçlar\n",true)+"\n"+
                            "\nF mesure : "+eval.fMeasure(1)+"\n"+
                            eval.toClassDetailsString()+"\n"+eval.toMatrixString()
                    );


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

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
    }
}
