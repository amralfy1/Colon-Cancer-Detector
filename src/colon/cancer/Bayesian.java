/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colon.cancer;

import java.util.ArrayList;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 *
 * @author non
 */
public class Bayesian extends Classifier
{
    private ArrayList<Double> normal_mean;
    private ArrayList<Double> not_normal_mean;
    private ArrayList<Double> normal_variance;
    private ArrayList<Double> not_normal_variance;
    double norm_count ;
    double not_norm_count ;

    public Bayesian(Data_Set data) 
    {
        normal_mean = new ArrayList<>();
        not_normal_mean = new ArrayList<>();
        normal_variance = new ArrayList<>();
        not_normal_variance = new ArrayList<>();
        norm_count = 0;
        not_norm_count = 0;
        calc_mean_var(data);
    }
    
    public void calc_mean_var(Data_Set data)
    {
        
        
        for (int i = 0; i < data.getTraining_Set().get(0).getFeatures().size(); i++) 
        {
            double norm = 0;
            double not_norm = 0;            
            norm_count = 0;
            not_norm_count = 0;
            for (int j = 0; j < data.getTraining_Set().size(); j++) 
            {
                if(data.getTraining_Set().get(j).getLabel().equals("Normal"))
                {
                    norm += data.getTraining_Set().get(j).getFeatures().get(i);
                    ++ norm_count ;
                }
                else
                {
                    not_norm +=  data.getTraining_Set().get(j).getFeatures().get(i);
                    ++ not_norm_count ;
                }
            }
            norm = norm / norm_count;
            not_norm = not_norm / not_norm_count;
            normal_mean.add(norm);
            not_normal_mean.add(not_norm);
            
        }
//        System.out.println(normal_mean.size());
//        System.out.println(not_normal_mean.size());
        for (int i = 0; i < data.getTraining_Set().get(0).getFeatures().size(); i++) 
        {
            
            double norm_var = 0;            
            double not_norm_var = 0;
            
            for (int j = 0; j < data.getTraining_Set().size(); j++) 
            {
                double temp = 0 ;
                if(data.getTraining_Set().get(j).getLabel().equals("Normal"))
                {
                    temp = data.getTraining_Set().get(j).getFeatures().get(i)- normal_mean.get(i);
                    temp *= temp ;    
                    norm_var += temp;
                   
                }
                else
                {
                    temp = data.getTraining_Set().get(j).getFeatures().get(i)- not_normal_mean.get(i);
                    temp *= temp ;    
                    not_norm_var += temp;
                  
                }
            }
            norm_var = Math.sqrt(norm_var/norm_count);
            not_norm_var = Math.sqrt(not_norm_var/not_norm_count);
            normal_variance.add(norm_var);
            not_normal_variance.add(not_norm_var);
            
//            System.out.println(norm_count);
//            System.out.println(not_norm_count);            
            
        }
//        System.out.println(normal_variance.size());
//        System.out.println(not_normal_variance.size());
    }
    
    public  String predict(Data_Set data , Set sample)
    {
        double prob_normal = 1 ; 
        double prob_not_normal = 1 ;
        double temp ; 
        DecimalFormat df = new DecimalFormat("#.###########");
        for (int i = 0; i < sample.getFeatures().size(); i++) 
        {
            temp = (1/(normal_variance.get(i)*Math.sqrt(Math.PI * 2)))*Math.exp(-(Math.pow((sample.getFeatures().get(i)-normal_mean.get(i)), 2))/(2*normal_variance.get(i)*normal_variance.get(i)) );
//            temp = Double.valueOf(df.format(temp));
            prob_normal *= temp*1000;
//            prob_normal = Double.valueOf(df.format(prob_normal));
//            System.out.println(temp);
//            System.out.println(i+"  "+ prob_normal);
            temp = (1/(not_normal_variance.get(i)*Math.sqrt(Math.PI * 2)))*Math.exp(-(Math.pow((sample.getFeatures().get(i)-not_normal_mean.get(i)), 2))/(2*not_normal_variance.get(i)*not_normal_variance.get(i)) );
            temp = Double.valueOf(df.format(temp));
            prob_not_normal *= temp*1000;
        }
        
        prob_normal *= (norm_count/(norm_count+not_norm_count));
        prob_not_normal *= (not_norm_count/(norm_count+not_norm_count));
//        System.out.println(norm_count);
//        System.out.println(not_norm_count);
        if(prob_normal > prob_not_normal)
        {
            
            return "Normal";
            
        }
        else
        {
            
            return "Abnormal";
        }
    }
    
    public double Accuracy(Data_Set data)
    { 
        for (int i = 0; i < data.getTesting_Set().size(); i++) 
        {
            String result = predict(data, data.getTesting_Set().get(i));
//            System.out.println(result);
//            System.out.println(data.getTraining_Set().get(i).getLabel());
            if(result.equals(data.getTesting_Set().get(i).getLabel()))
                continue;
            Misclassification++ ;

//            System.out.println(Misclassification);
        }
        Accuracy = ((data.getTesting_Set().size()-Misclassification) / data.getTesting_Set().size())*100;
        return Accuracy;
    
    }
}
