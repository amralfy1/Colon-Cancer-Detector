/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colon.cancer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author non
 */
public class Set {
    private double Id;
    private String Label;
    private ArrayList <Double> features ;

    public Set() {
        features = new ArrayList<>();
    }

    public Set(double Id, String Label, ArrayList<Double> features) {
        this.Id = Id;
        this.Label = Label;
        this.features = features;
    }

    
    public void setId(double Id) {
        this.Id = Id;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public void setFeatures(ArrayList<Double> features) {
        this.features = features;
    }

    
    
    public double getId() {
        return Id;
    }

    public String getLabel() {
        return Label;
    }

    public ArrayList<Double> getFeatures() {
        return features;
    }
    
    public void read_sample(String location)
    {
        String line = "";  
        String splitBy = ",";             
        
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(location)); 
            line = br.readLine();
            String[] Patient = line.split(splitBy);    // use comma as separator              
                
                ArrayList<Double> Feature=new ArrayList<>();
                for (int i = 0; i < Patient.length; i++)
                {
                    double Feature_temp = Double.parseDouble(Patient[i]);
                    Feature.add(Feature_temp);
                    
                }                
                this.features = Feature;
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        } 
    }
}
