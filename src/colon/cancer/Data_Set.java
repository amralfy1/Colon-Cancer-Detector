/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colon.cancer;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author non
 */
public class Data_Set {
    private String location;
    public ArrayList<Set> All_Sets;
    private ArrayList<Set> Training_Set;
    private ArrayList<Set> Testing_Set;

    public ArrayList<colon.cancer.Set> getTraining_Set() {
        return Training_Set;
    }

    public ArrayList<colon.cancer.Set> getTesting_Set() {
        return Testing_Set;
    }
    
    
    
    
    public Data_Set(String location) {
        this.location = location;
        All_Sets = new ArrayList<>();
        Training_Set = new ArrayList<>();
        Testing_Set = new ArrayList<>();
    }
    
    public void Reading_Data()
    {
        String line = "";  
        String splitBy = ",";
              
        
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(location)); 
            br.readLine();
        while ((line = br.readLine()) != null)   //returns a Boolean value  
            {   
                
                String[] Patient = line.split(splitBy);    // use comma as separator                
                double id = Double.parseDouble(Patient[0]); 
                ArrayList<Double> Feature=new ArrayList<>();
                Integer i=1;
                while(i<202)
                {
                    double Feature_temp = Double.parseDouble(Patient[i]);
                    Feature.add(Feature_temp);
                    i+=1;
                }                
                String Labels = Patient[202];
                Set Temp = new Set(id, Labels, Feature);
                All_Sets.add(Temp);
            }  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
//        System.out.println(IDs);
//         for (int i = 0; i < Features.size(); i++) { 
//  
//            System.out.print(Features.get(i) + " "); 
//        } 
        //System.out.println(Labels);
    }
    
    public void Split_Data()
    {
        
        int normal = 0;
        int not_normal = 0;
        for (int i =0 ; i < All_Sets.size();++i) {
            if (All_Sets.get(i).getLabel().equals( "Normal"))
            {
             if (normal<12)
             {
                 Training_Set.add(All_Sets.get(i));
                 ++ normal;
                 
             }
             else
             {
                 Testing_Set.add(All_Sets.get(i));
                 ++normal;
                 
             }
            }
            else
            {
                if (not_normal<20)
             {
                 Training_Set.add(All_Sets.get(i));
                 ++ not_normal;
             }
             else
             {
                 Testing_Set.add(All_Sets.get(i));
                 ++not_normal;
                 
             }
            }
            
        }
//        System.out.println(normal+"  "+not_normal);

    }
    public double d ()
    {
        
        return (Training_Set.get(0).getFeatures().size());
    }
}
