/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colon.cancer;
import java.util.ArrayList;
import java.lang.Math;
/**
 *
 * @author non
 */
public class Nearest_Neighbor extends Classifier
{
    public  String predict(Data_Set data , Set sample)
    {
        ArrayList<Set> Train_set = data.getTraining_Set();
        double min = 10000000 ; 
        String predicted = "";
        for (int i = 0; i < Train_set.size(); i++) 
        {
            ArrayList<Double> train_features = data.getTraining_Set().get(i).getFeatures();
            double distance = 0;
            for (int j = 0; j < sample.getFeatures().size(); j++) 
            {
                double temp = train_features.get(j)- sample.getFeatures().get(j);
                temp = temp * temp ;
                distance += temp ;                       
                
            }
            distance = Math.sqrt(distance);
            if(distance <= min)
            {
                min = distance;
                predicted = data.getTraining_Set().get(i).getLabel();
            }
            
        }
        return predicted;
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
