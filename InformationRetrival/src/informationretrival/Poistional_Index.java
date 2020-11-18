/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Akram
 */
public class Poistional_Index {
         private Scanner x;
         public ArrayList<ArrayList> posting = new ArrayList<>();
         private int position=1;
         File FN = new File("Positional.txt");
         PrintWriter pw ;
         
         
         
         
         public void frtokenizer(String filepath,String filename){
         try {
                x = new Scanner(new File(filepath));
             
         while(x.hasNext()){
            String s1= x.next();
            String replace = s1.replaceAll("[;.,:><*^%!~`(){}|'/]","");
            ArrayList<String> Tokens = new ArrayList<>();
            Tokens.add( replace.toLowerCase()+ " --> ");
            Tokens.add( filename +" : " );
            Tokens.add( " "+position );
            posting.add(Tokens);
            for(int i=0;i<posting.size();i++){
                for(int j=i+1;j<posting.size();j++){
                    if(posting.get(j).get(0).equals(posting.get(i).get(0))){
                        boolean check = false;
                        for(int k=1;k<posting.get(i).size();k++){
                            if(check==false){
                            if(posting.get(j).get(1).equals(posting.get(i).get(k))||posting.get(j).get(1).equals(""+posting.get(i).get(k))){
                              posting.get(i).add(" "+position);
                              check =true;
                            }
                            }
                        }
                              if(check==false){
                              posting.get(i).add(" ; " );
                              posting.get(i).add(filename +" : ");
                              posting.get(i).add(" "+position);
                              }
                          posting.remove(j);
                          break;
                    }
                    else if(posting.get(j).get(0).equals("the --> ") || posting.get(j).get(0).equals("of --> ") || posting.get(j).get(0).equals("a --> ")
                            || posting.get(j).get(0).equals("and --> ") || posting.get(j).get(0).equals("with --> ") || posting.get(j).get(0).equals("to --> ")
                            || posting.get(j).get(0).equals("an --> ") || posting.get(j).get(0).equals("at --> ") || posting.get(j).get(0).equals("it --> ")
                            || posting.get(j).get(0).equals("from --> ") || posting.get(j).get(0).equals("for --> ") || posting.get(j).get(0).equals("not --> ")
                            || posting.get(j).get(0).equals("that --> ") || posting.get(j).get(0).equals("in --> ") || posting.get(j).get(0).equals("all --> ")
                            || posting.get(j).get(0).equals("as --> ") || posting.get(j).get(0).equals("on --> ")   || posting.get(j).get(0).equals("or --> ")
                            || posting.get(j).get(0).equals(" --> ")
                             ){
                          posting.remove(j);
                          break;
                    }
                    
                          
                    
                }
            }  
            
            position++;
            
         }
            
            
             x.close();
             position = 1;
             
             
             }
         
             catch (Exception e) {
                 System.out.println("could not find file");
             }
         }
         
         
         
         
        public void fw(){
            System.out.println(posting);
        int max=0;
        for (int i = 0; i < posting.size(); i++) {
            String St = posting.get(i).get(0).toString();
            if(St.length()>max)
            {
                max=St.length()+2;
            }
        }
           
             try {
                   pw = new PrintWriter(FN);
                
                 for(int i = 0;i<posting.size();i++){
                     for(int j=0;j<posting.get(i).size();j++){
                         if(j==0){
                             String pa ="";
                             String s1 =posting.get(i).get(0).toString();
                             for(int h=0;h<=(max-s1.length());h++){
                                 pa = pa+" ";
                             }
                             s1=s1+pa;
                             pw.print(s1);
                         }else
                             pw.print(posting.get(i).get(j).toString());
                         
                 }
                     pw.print("\n");
                 }
                 
             } catch (Exception e) {
             }
              pw.close();
         }
         
         public boolean check_File() {
      
             if(FN.exists())
                 return true;
             else
                 return false;
         }
         
}
