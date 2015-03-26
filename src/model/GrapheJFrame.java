package model;

import  org.jfree.chart.ChartFactory;
import  org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import  org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import  org.jfree.data.general.DefaultPieDataset;
import service.PerformanceService;
import service.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by driss on 25/03/2015.
 */


    public class GrapheJFrame extends JFrame {

        private JDialog grapheJdialog;

        private class GrapheAction extends AbstractAction {
            public int a=1;
            private GrapheAction() {
                super();
            }


            @Override
           /* public void actionPerformed(ActionEvent arg0) {
                System.out.println("coucou");
            }*/
            public void actionPerformed(ActionEvent e) {
                grapheJdialog = new JDialog();
                grapheJdialog.setTitle("Graphe Ratios Exos");

                List<ExerciceSession> exerc = PerformanceService.getPerfFromSessionId(a);
                List<Double> duree=new ArrayList<Double>();
                List<Double> repet=new ArrayList<Double>();

                for (int i=0;i<exerc.size();i++) {
                    duree.add(Math.ceil((double)exerc.get(i).getRatioDuree()));
                    repet.add(Math.ceil((double)exerc.get(i).getRatioRepet()));
                }

                /*final Map<Integer, Integer> duree = new HashMap<Integer, Integer>();
                final Map<Integer, Integer> repet = new HashMap<Integer, Integer>();
                   for (int i = 0; i <= 20; i++) {
                    repartitionHomme.put(i, 0);
                    repartitionFemme.put(i, 0);
                }


                for (ExerciceSession ex : exerc) {
                    incrementNb(ex.getRatioDuree(), duree);

                    incrementNb(ex.getRatioRepet(), repet);
                }*/

                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                for (int i = 0; i < exerc.size(); i++) {
                    dataset.addValue(duree.get(i), "RatioDuree", new Integer(i));
                }
                for (int i = 0; i < exerc.size(); i++) {
                    dataset.addValue(repet.get(i), "RatioRepet", new Integer(i));
                }

                final JFreeChart barChart = ChartFactory.createBarChart("Ratio d une seance", "Ratio Duree et Repet", "%",
                        dataset, PlotOrientation.VERTICAL, true, true, false);

                final ChartPanel cPanel = new ChartPanel(barChart);

                grapheJdialog.getContentPane().add(cPanel);


                grapheJdialog.pack();
                grapheJdialog.setVisible(true);

           }
    }
        private void incrementNb(float ratio, Map<Integer, Integer> repartition) {

            int ceil = (int) Math.ceil(ratio);
            Integer nb = repartition.get(ceil);
            nb++;
            repartition.put(ceil, nb);
        }

            public GrapheJFrame() {

                JPanel boutons = new JPanel();
                boutons.add(new JButton(new GrapheAction()));

                getContentPane().add(boutons);
            }
}


