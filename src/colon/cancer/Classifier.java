/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colon.cancer;

/**
 *
 * @author non
 */
public abstract class Classifier {
    protected double Accuracy;
    protected double Misclassification;
    protected double Prediction;

    public Classifier() 
    {
        Misclassification = 0;
    }
    
        
    public abstract String predict(Data_Set data , Set sample);
    public abstract double Accuracy(Data_Set data);
}
