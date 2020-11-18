/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Akram
 */
public class Vector_Space_Model {
    private Scanner x;
    private ArrayList<ArrayList> TF = new ArrayList<>();
    private ArrayList<Integer> DF = new ArrayList<>();
    private ArrayList<Double> IDF = new ArrayList<>();
    private ArrayList<ArrayList> TF_IDF = new ArrayList<>();
    private ArrayList<ArrayList> E_L = new ArrayList<>();
    private ArrayList<ArrayList> Terms = new ArrayList<>();
    private ArrayList<String> Files_Names = new ArrayList<>();
    private ArrayList<Integer> Files_number = new ArrayList<>();
    private ArrayList<String> Index =new ArrayList<>();
    File FTF = new File("TF.txt");
    File FIDF = new File("IDF.txt");
    File FTF_IDF = new File("TF_IDF.txt");
    File FE_L = new File("TF_E_L.txt");
    PrintWriter pw ;
    PrintWriter PWIDF ;
    PrintWriter PWTFIDF ;
    PrintWriter PWTEL ;
    DecimalFormat df = new DecimalFormat();
    public void Files_Terms(String filepath,String filename){
        Files_Names.add(filename);
        df.setMaximumFractionDigits(2);
        ArrayList<String> Tokens_File = new ArrayList<>();
        try {
            x = new Scanner(new File(filepath));
            while(x.hasNext()){
                String s1= x.next();
                String replace = s1.replaceAll("[;.,:><*^%!~`(){}|'/]","");
                Tokens_File.add(replace.toLowerCase());
                Index.add(replace.toLowerCase());
            }
            Terms.add(Tokens_File);

            
        } catch (Exception e) {
            System.out.println("could not find file");
        }
        
       for(int t=0;t<Index.size()-1;t++){
        for(int x=t+1;x<Index.size();x++){
        if(Index.get(t).equals(Index.get(x))){
                    Index.remove(x);
        }
        }
        }
        for(int t=0;t<Index.size()-1;t++){
        for(int x=t+1;x<Index.size();x++){
        if(Index.get(t).equals(Index.get(x))){
                    Index.remove(t);
        }
        }
        }
        
        
        
        
    }
    public void TF(){
        
        for(int i=0;i<Index.size();i++){
            ArrayList<String> Token = new ArrayList<>();
            Token.add(Index.get(i));
            for(int j=0;j<Terms.size();j++){
                int counter=0;
                for(int k=0;k<Terms.get(j).size();k++){
                    if(Index.get(i).equals(Terms.get(j).get(k)))
                        counter++;
                }
                Token.add(""+counter);
            }
            TF.add(Token);
        }
        
        
    }
    
     public void fw_TF(){
         
        TF(); 
        int max=0;
        for (int i = 0; i < TF.size(); i++) {
            String St = TF.get(i).get(0).toString();
            if(St.length()>max)
            {
                max=St.length()+2;
            }
        } 
        
        int maxfile=0;
        for (int i = 0; i < Files_Names.size(); i++) {
            String St = Files_Names.get(i).toString();
            if(St.length()>maxfile)
            {
                maxfile=St.length()+2;
            }
        } 

        
             try {
                   pw = new PrintWriter(FTF);
                 for(int i = -1;i<Files_Names.size();i++){
                     
                       if(i==-1){
                             String pa ="";
                             String s1 ="";
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             pw.print(s1);
                         }else{
                             String pa ="";
                             String s1 =Files_Names.get(i).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             pw.print(s1);
                          }
                     
                 }
                  pw.print("\n");
                 for(int i = 0;i<TF.size();i++){
                     for(int j=0;j<TF.get(i).size();j++){
                       if(j==0){
                             String pa ="";
                             String s1 =TF.get(i).get(0).toString();
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             pw.print(s1);
                         }else{
                             String pa ="";
                             String s1 =TF.get(i).get(j).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             pw.print(s1);
                       }
                 }
                     pw.print("\n");
                 }
                 
             } catch (Exception e) {
             }
              pw.close();
         }
     
     
     public void Find_IDF(){
        
         
         for(int i=0;i<TF.size();i++){
             int count = 0;
             for(int j=1;j<TF.get(i).size();j++){
                  String s=TF.get(i).get(j).toString();
                 if(!s.equals("0")){
                     count++;
                 }
             }
             DF.add(count);
             
             double sd = (double)Files_Names.size() / (double)count;
             IDF.add(Double.parseDouble(df.format(Math.log10(sd)))); 
             
         }
         

         
         
     }
     
      public void fw_IDF(){
        Find_IDF();  
        int max=0;
        for (int i = 0; i < TF.size(); i++) {
            String St = TF.get(i).get(0).toString();
            if(St.length()>max)
            {
                max=St.length()+2;
            }
        } 
        
        int maxfile=0;
        for (int i = 0; i < Files_Names.size(); i++) {
            String St = Files_Names.get(i).toString();
            if(St.length()>maxfile)
            {
                maxfile=St.length()+2;
            }
        } 

        
             try {
                   PWIDF = new PrintWriter(FIDF);
                   
                   String pa ="";
                   String s1 ="";
                   for(int h=0;h<=(max-s1.length());h++){
                   pa = pa+" ";
                   }
                   s1=s1+pa;
                   PWIDF.print(s1);
                   
                   String paa ="";
                   String s11 ="DF";
                   for(int h=0;h<=(maxfile-s11.length());h++){
                   paa = paa+" ";
                   }
                   s11=s11+paa;
                   PWIDF.print(s11);
                   
                   String paaa ="";
                   String s111 ="IDF";
                   for(int h=0;h<=(maxfile-s111.length());h++){
                   paaa = paaa+" ";
                   }
                   s111=s111+paaa;
                   PWIDF.print(s111);
                   
                  PWIDF.print("\n");
//-----------------------------------------------------------------------------------------                  
                 for(int i = 0;i<Index.size();i++){
                     String paaaa ="";
                     String s1111 =Index.get(i).toString();
                     for(int h=0;h<=(max-s1111.length());h++){
                                 paaaa = paaaa+" ";
                     }
                     s1111=s1111+paaaa;
                     PWIDF.print(s1111);
                     //--------------------------
                     String paaaaa ="";
                     String s11111 =DF.get(i).toString();
                     for(int h=0;h<=(maxfile-s11111.length());h++){
                               paaaaa = paaaaa+" ";
                     }
                     s11111=s11111+paaaaa;
                     PWIDF.print(s11111);
                     //--------------------------
                     String paaaaaa ="";
                     String s111111 =IDF.get(i).toString();
                     for(int h=0;h<=(maxfile-s111111.length());h++){
                               paaaaaa = paaaaaa+" ";
                     }
                     s111111=s111111+paaaaaa;
                     PWIDF.print(s111111);
                     //--------------------------
                     
                     PWIDF.print("\n");
                 }
                 
             } catch (Exception e) {
             }
              PWIDF.close();
         }
      
      
      public void Find_TF_IDF(){
          
          for(int i=0;i<TF.size();i++){
              ArrayList<String> Take = new  ArrayList<>();
              for(int j=0;j<TF.get(i).size();j++){
                  if(j==0){
                      Take.add(TF.get(i).get(j).toString());
                  }else{
                      double num = (Double.parseDouble(TF.get(i).get(j).toString()))*(IDF.get(i));
                      Take.add(""+df.format(num));
                  }    
                  
              }
              TF_IDF.add(Take);
          }
      }
      
      
      public void fw_TF_IDF(){

        Find_TF_IDF();
        int max=0;
        for (int i = 0; i < TF_IDF.size(); i++) {
            String St = TF_IDF.get(i).get(0).toString();
            if(St.length()>max)
            {
                max=St.length()+2;
            }
        } 
        
        int maxfile=0;
        for (int i = 0; i < Files_Names.size(); i++) {
            String St = Files_Names.get(i).toString();
            if(St.length()>maxfile)
            {
                maxfile=St.length()+2;
            }
        } 

        
             try {
                   PWTFIDF = new PrintWriter(FTF_IDF);
                 for(int i = -1;i<Files_Names.size();i++){
                     
                       if(i==-1){
                             String pa ="";
                             String s1 ="";
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTFIDF.print(s1);
                         }else{
                             String pa ="";
                             String s1 =Files_Names.get(i).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTFIDF.print(s1);
                          }
                     
                 }
                  PWTFIDF.print("\n");
                 for(int i = 0;i<TF_IDF.size();i++){
                     for(int j=0;j<TF_IDF.get(i).size();j++){
                       if(j==0){
                             String pa ="";
                             String s1 =TF_IDF.get(i).get(0).toString();
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTFIDF.print(s1);
                         }else{
                             String pa ="";
                             String s1 =TF_IDF.get(i).get(j).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTFIDF.print(s1);
                       }
                 }
                     PWTFIDF.print("\n");
                 }
                 
             } catch (Exception e) {
             }
              PWTFIDF.close();
              
         }
      
      
      public void Find_E_L(){
          ArrayList<ArrayList> sqr_F = new ArrayList<>();
          ArrayList<Double> sum = new ArrayList<>();
          for(int i=0;i<TF_IDF.size();i++){
              ArrayList<String> sqr = new ArrayList<>();
              for(int j=0;j<TF_IDF.get(i).size();j++){
                  if(j==0){
                      sqr.add(TF_IDF.get(i).get(j).toString());
                  }
                  else{
                      double s =Double.parseDouble(TF_IDF.get(i).get(j).toString())*Double.parseDouble(TF_IDF.get(i).get(j).toString());
                      sqr.add(""+df.format(s));
                  }
              }
              sqr_F.add(sqr);
          }
          //System.out.println(sqr_F);
          for(int i=0;i<sqr_F.size();i++){
              for(int j=1;j<sqr_F.get(i).size();j++){
                  if(i==0){
                      sum.add(Double.parseDouble(sqr_F.get(i).get(j).toString()));
                  }
                  else{
                      double y=sum.get(j-1)+(Double.parseDouble(sqr_F.get(i).get(j).toString()));
                      sum.set(j-1, y);         //get(j-1)=sum.get(j-1)+sum.get(j);
              
                  }
              }      
          }
          //System.out.println(sum);
          for(int i=0;i<sum.size();i++){
              double s = Math.sqrt(sum.get(i));
              sum.set(i, Double.parseDouble(df.format(s)));
          }
          System.out.println(sum);
          for(int i=0;i<TF_IDF.size();i++){
              ArrayList<String> div = new ArrayList<>();
              for(int j=0;j<TF_IDF.get(i).size();j++){
                  if(j==0){
                      div.add(TF_IDF.get(i).get(j).toString());
                  }
                  else{
                      if(sum.get(j-1).equals(0.0)){
                          div.add("0");
                      }
                      else{
                      double u=Double.parseDouble(TF_IDF.get(i).get(j).toString())/sum.get(j-1);
                      div.add(df.format(u));
                      }
                  }
              }
              E_L.add(div);
          }
      }
      
      
       public void fw_E_L(){

        Find_E_L();
        int max=0;
        for (int i = 0; i < TF_IDF.size(); i++) {
            String St = TF_IDF.get(i).get(0).toString();
            if(St.length()>max)
            {
                max=St.length()+2;
            }
        } 
        
        int maxfile=0;
        for (int i = 0; i < Files_Names.size(); i++) {
            String St = Files_Names.get(i).toString();
            if(St.length()>maxfile)
            {
                maxfile=St.length()+2;
            }
        } 

        
             try {
                   PWTEL = new PrintWriter(FE_L);
                 for(int i = -1;i<Files_Names.size();i++){
                     
                       if(i==-1){
                             String pa ="";
                             String s1 ="";
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTEL.print(s1);
                         }else{
                             String pa ="";
                             String s1 =Files_Names.get(i).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTEL.print(s1);
                          }
                     
                 }
                  PWTEL.print("\n");
                 for(int i = 0;i<E_L.size();i++){
                     for(int j=0;j<E_L.get(i).size();j++){
                       if(j==0){
                             String pa ="";
                             String s1 =E_L.get(i).get(0).toString();
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTEL.print(s1);
                         }else{
                             String pa ="";
                             String s1 =E_L.get(i).get(j).toString();
                             for(int h=0;h<=(maxfile-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             PWTEL.print(s1);
                       }
                 }
                     PWTEL.print("\n");
                 }
                 
             } catch (Exception e) {
             }
              PWTEL.close();
              
         }
      
      
       public boolean check_File() {
      
             if(FTF.exists()&&FIDF.exists()&&FTF_IDF.exists())
                 return true;
             else
                 return false;
         }
       
}
