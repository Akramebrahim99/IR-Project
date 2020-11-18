/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.lang.*;
import java.util.Collections;

/**
 *
 * @author Akram
 */
public class Free_Text_Query_Search {
    private String Query;
    private Scanner x;
    private Scanner y;
    private Scanner z;
    private DecimalFormat df = new DecimalFormat();
    private ArrayList<ArrayList> R_F_F_IDF = new ArrayList<>();
    private ArrayList<ArrayList> R_F_F_E_L = new ArrayList<>();
    private ArrayList<ArrayList> Doc_E_L = new ArrayList<>();
    private ArrayList<ArrayList> BS = new ArrayList<>();
    ArrayList<ArrayList> save = new ArrayList<>();
    private ArrayList<String> Tokens_Query_With_Fillter = new ArrayList<>();
    private ArrayList<String> Tokens_Query = new ArrayList<>();
    private ArrayList<Double> Query_E_L = new ArrayList<>();
    private ArrayList<String> Docs_name = new ArrayList<>();
    private ArrayList<Double> sim = new ArrayList<>();
    
    public void Query_Search(String Query) {
        this.Query=Query;
        df.setMaximumFractionDigits(2);
         x = new Scanner(Query);
         
         while(x.hasNext()){
             String s1= x.next();
             String replace = s1.replaceAll("[;.,:><*^%!~`(){}|'/]","");
             Tokens_Query_With_Fillter.add(replace.toLowerCase());
             Tokens_Query.add(replace.toLowerCase());
         }
         for(int t=0;t<Tokens_Query_With_Fillter.size()-1;t++){
        for(int x=t+1;x<Tokens_Query_With_Fillter.size();x++){
        if(Tokens_Query_With_Fillter.get(t).equals(Tokens_Query_With_Fillter.get(x))){
                    Tokens_Query_With_Fillter.remove(x);
        }
        }
        }
        for(int t=0;t<Tokens_Query_With_Fillter.size()-1;t++){
        for(int x=t+1;x<Tokens_Query_With_Fillter.size();x++){
        if(Tokens_Query_With_Fillter.get(t).equals(Tokens_Query_With_Fillter.get(x))){
                    Tokens_Query_With_Fillter.remove(t);
        }
        }
        }
        read_from_file_idf();
        Find_TF();
        Find_TF_IDF();
        Find_E_L();
        read_from_file_E_L();
        Find_E_L_F();
        Find_Sim();
        
        
    }
    
    private void Find_TF(){
        for(int i=0;i<R_F_F_IDF.size();i++){
            int count=0;
            for(int j=0;j<Tokens_Query_With_Fillter.size();j++){
            if(R_F_F_IDF.get(i).get(0).equals(Tokens_Query_With_Fillter.get(j))){
                for(int k=0;k<Tokens_Query.size();k++){
                    if(R_F_F_IDF.get(i).get(0).equals(Tokens_Query.get(k))){
                        count++;
                    }
                }
                break;
            }        
            }
            Query_E_L.add((double)count);
        }
        
    }
    
    private void Find_TF_IDF(){
        for(int i=0;i<R_F_F_IDF.size();i++){
            double num =Double.parseDouble(R_F_F_IDF.get(i).get(2).toString())*Query_E_L.get(i);
            Query_E_L.set(i, num);
        }
    }
    
    private void Find_E_L(){
        double sum=0;
        for(int i=0;i<Query_E_L.size();i++){
            sum += Query_E_L.get(i)*Query_E_L.get(i);
        }
        sum = Math.sqrt(sum);
        for(int i=0;i<Query_E_L.size();i++){
            if(sum==0){
                break;
            }
            double s=Query_E_L.get(i)/sum;
            Query_E_L.set(i, Double.parseDouble(df.format(s)));
        }
    }
    
    private void Find_E_L_F(){
        for(int i=0;i<Docs_name.size();i++){
            ArrayList<Double> return_Doc_E_L =new ArrayList<>();
            for(int j=1;j<R_F_F_E_L.size();j++){
                return_Doc_E_L.add(Double.parseDouble(R_F_F_E_L.get(j).get(i+1).toString()));
            }
            Doc_E_L.add(return_Doc_E_L);
        }
        
    }
    
    private void Find_Sim(){
        for(int i=0;i<Doc_E_L.size();i++){
            double sum=0;
            ArrayList<Double> sumd = new ArrayList<>();
            for(int j=0;j<Doc_E_L.get(i).size();j++){
                double y=(double)Doc_E_L.get(i).get(j)*Query_E_L.get(j);
                sumd.add(y);
            }
            for(int k=0;k<sumd.size();k++){
                sum +=sumd.get(k);
            }
            sim.add(Double.parseDouble(df.format(sum)));
        }
    }
    
    public ArrayList<String> best_sim(){
        ArrayList<ArrayList> F_S = new ArrayList<>();
        for(int i=0;i<Docs_name.size();i++){
            ArrayList<String> A = new ArrayList<>();
            A.add(Docs_name.get(i));
            A.add(sim.get(i).toString());
            F_S.add(A);
        }
        
        for(int i=0;i<F_S.size();i++){
            int y=i;
            ArrayList<ArrayList> s =new ArrayList<>();
            for(int j=i+1;j<F_S.size();j++){
                if(Double.parseDouble(F_S.get(y).get(1).toString()) < Double.parseDouble(F_S.get(j).get(1).toString())){
                    y=j; 
                }
            }
            Collections.swap(F_S, i, y);
        }
        ArrayList<String> result =new ArrayList<>();
        for(int i=0;i<F_S.size();i++){
            result.add(F_S.get(i).get(0).toString());
            save.add(F_S.get(i));
        }
        return result;
    }
    
    
    private void read_from_file_idf(){
        try {
            x = new Scanner(new File("IDF.txt"));
            while(x.hasNextLine()){
                ArrayList<String> Dic = new ArrayList<>();
                String line = x.nextLine();
                y = new Scanner(line);
                while(y.hasNext()){
                String No =y.next();
                  Dic.add(No);
                }    
                R_F_F_IDF.add(Dic);
            }
            R_F_F_IDF.remove(0);
        }
        catch (Exception e) {
                 System.out.println("could not find file");
             }
    }
    
    

    private void read_from_file_E_L(){
        try {
            x = new Scanner(new File("TF_E_L.txt"));
            while(x.hasNextLine()){
                ArrayList<String> Dic = new ArrayList<>();
                String line = x.nextLine();
                z = new Scanner(line);
                while(z.hasNext()){
                String No =z.next();
                  Dic.add(No);
                }    
                R_F_F_E_L.add(Dic);
                
            }
            for(int j=0;j<R_F_F_E_L.get(0).size();j++){
                Docs_name.add(R_F_F_E_L.get(0).get(j).toString());
            }
        }
        catch (Exception e) {
                 System.out.println("could not find file");
             }

    }
    
}
