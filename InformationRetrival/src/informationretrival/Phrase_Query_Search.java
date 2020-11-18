/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Akram
 */
public class Phrase_Query_Search {
    String Query;
    private Scanner x;
    private Scanner y;
    private ArrayList<ArrayList> Query_PL = new ArrayList<>();
    private ArrayList<ArrayList> readlist = new ArrayList<>();
    private ArrayList<ArrayList> Terms_founded = new ArrayList<>();
    private ArrayList<String> Doc_B_match = new ArrayList<>();
    private ArrayList<String> Doc_match = new ArrayList<>();
    private ArrayList<Integer> counter = new ArrayList<>();
    private ArrayList<String> Doc_match_true = new ArrayList<>();
    private ArrayList<String> list1 = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();
    ArrayList<ArrayList> arraylist = new ArrayList<>();
    private int position =1 ;
    public void Query_Search(String Query) {
        this.Query = Query;
        x = new Scanner(Query);
        while (x.hasNext()) {
           
        String s1= x.next();
        String replace = s1.replaceAll("[;.,:><*^%!~`(){}|'/]","");
        ArrayList<String> pos = new ArrayList<>();
            pos.add(replace.toLowerCase());
            pos.add(""+position);
            Query_PL.add(pos);
        
            for(int j=0;j<Query_PL.size();j++){
            if(Query_PL.get(j).get(0).equals("the") || Query_PL.get(j).get(0).equals("of") || Query_PL.get(j).get(0).equals("a")
                 || Query_PL.get(j).get(0).equals("and") || Query_PL.get(j).get(0).equals("with") || Query_PL.get(j).get(0).equals("to")
                 || Query_PL.get(j).get(0).equals("an") || Query_PL.get(j).get(0).equals("at") || Query_PL.get(j).get(0).equals("it")
                 || Query_PL.get(j).get(0).equals("from") || Query_PL.get(j).get(0).equals("for") || Query_PL.get(j).get(0).equals("not")
                 || Query_PL.get(j).get(0).equals("that") || Query_PL.get(j).get(0).equals("in") || Query_PL.get(j).get(0).equals("all")
                 || Query_PL.get(j).get(0).equals("as") || Query_PL.get(j).get(0).equals("on")   || Query_PL.get(j).get(0).equals("or")
                 || Query_PL.get(j).get(0).equals("")  )
                     Query_PL.remove(j);
        
          }
            position++;
            
            
        }
        
        
 //-------------------------------------------------------------------------------------------------------------------------        
//------------------------------------------read from file ----------------------------------------------------------------
        ReadFromFile();
//-------------------------------------------------------------------------------------------------------------------------
        if(Found_Terms()){
        if(Same_Doc())
            Docs_Return();
        }
    }
    private void ReadFromFile(){
        
        try {
            x = new Scanner(new File("Positional.txt"));
            while(x.hasNextLine()){
                ArrayList<String> Dic = new ArrayList<>();
                String line = x.nextLine();
                y = new Scanner(line);
                while(y.hasNext()){
                String No =y.next();
                   if(No.equals("-->") || No.equals(":") || No.equals(";")){}
                   else
                       Dic.add(No);
                }    
                readlist.add(Dic);
            }
        }
        catch (Exception e) {
                 System.out.println("could not find file");
             }
         
    }
    
    
    private boolean Found_Terms(){
        boolean result =false; 
        for(int i=0;i<Query_PL.size();i++){
            result = false;
                for(int j=0;j<readlist.size();j++){  
                    if(Query_PL.get(i).get(0).equals(readlist.get(j).get(0))){
                        Terms_founded.add(readlist.get(j));
                        result = true;
                        break;
                    }
                    else
                        result = false;
                    
                }
                if(result==false)
                    break;
        }
        return result; 
    }
    
    
    
    
    public boolean Same_Doc(){
       boolean t_f=false; 
    if(Terms_founded.size()>1){   
        for(int i=0;i<Terms_founded.size()-1;i++){
            for(int j=1;j<Terms_founded.get(i).size();j++){
                if(!isInt(Terms_founded.get(i).get(j).toString())){
                    for(int k=1;k<Terms_founded.get(i+1).size();k++){
                        if(!isInt(Terms_founded.get(i+1).get(k).toString())){
                            if(Terms_founded.get(i).get(j).equals(Terms_founded.get(i+1).get(k))){
                                Doc_B_match.add(Terms_founded.get(i).get(j).toString());
                            }
                        }
                    }
                }
            }
        }
//---------------------------------------------------------------------------------------------------------------------        
        for(int i=0;i<Doc_B_match.size();i++){
            int c =0;
            String u =Doc_B_match.get(i).toString();
            for(int j=0;j<Doc_B_match.size();j++){                
                if(Doc_B_match.get(j).toString().equals(u)){
                  c++;
                }
            }
            counter.add(c);
        }
        for(int i =0;i<counter.size();i++){
            if(counter.get(i).equals(Terms_founded.size()-1)){
            Doc_match.add(Doc_B_match.get(i));
            t_f = true;
            }
         for(int t=0;t<Doc_match.size()-1;t++){
             for(int x =t+1;x<Doc_match.size();x++){
                if(Doc_match.get(t).equals(Doc_match.get(x))){
                    Doc_match.remove(x);
                }
             }    
         }
            
        
        }
//-------------------------------------------------------------------------------------------------------------------------
     
        
    }
    else if(Terms_founded.size()==1){
        for(int j=1;j<Terms_founded.get(0).size();j++){
                if(!isInt(Terms_founded.get(0).get(j).toString())){            
                     Doc_match.add(Terms_founded.get(0).get(j).toString());
                }
                
            }
       
        t_f = true;
    }
    else
        t_f = false;
        
    return t_f;        
    }
    
    
    public ArrayList<String> Docs_Return(){
        if(Terms_founded.size()>1 && !Doc_match.isEmpty()){
        for(int i=0;i<Terms_founded.size()-1;i++){
            int diff =Integer.parseInt(Query_PL.get(i+1).get(1).toString())-Integer.parseInt(Query_PL.get(i).get(1).toString());
            for(int j=1;j<Terms_founded.get(i).size();j++){
                if(!isInt(Terms_founded.get(i).get(j).toString())){
                    for(int k=1;k<Terms_founded.get(i+1).size();k++){
                        if(!isInt(Terms_founded.get(i+1).get(k).toString())){
                            if(Terms_founded.get(i).get(j).equals(Terms_founded.get(i+1).get(k))){
                                for(int g=0;g<Doc_match.size();g++){
                                    if(Terms_founded.get(i+1).get(k).equals(Doc_match.get(g))){
                                        for(int q=k+1;q<Terms_founded.get(i).size();q++){
                                            if(isInt(Terms_founded.get(i).get(q).toString())){
                                               for(int w=k+1;w<Terms_founded.get(i+1).size();w++){
                                                   if(isInt(Terms_founded.get(i+1).get(w).toString())){
                                                       if((Integer.parseInt(Terms_founded.get(i+1).get(w).toString())-Integer.parseInt(Terms_founded.get(i).get(q).toString()))== diff){
                                                           
                                                           ArrayList<String> list1 = new ArrayList<>();
                                                           ArrayList<String> list2 = new ArrayList<>();
                                                           
                                                           list1.add(Terms_founded.get(i+1).get(0).toString());
                                                           list1.add(Terms_founded.get(i+1).get(k).toString());
                                                           list1.add(Terms_founded.get(i+1).get(w).toString());
                                                           arraylist.add(list1);
                                                           list2.add(Terms_founded.get(i).get(0).toString());
                                                           list2.add(Terms_founded.get(i).get(j).toString());
                                                           list2.add(Terms_founded.get(i).get(q).toString()+"\n");
                                                           arraylist.add(list2);
                                                           
                                                           for(int t=0;t<arraylist.size()-1;t++){
                                                                    for(int x=t+1;x<arraylist.size();x++){
                                                                    if(arraylist.get(t).equals(arraylist.get(x))){
                                                                        arraylist.remove(x);
                                                                    }
                                                                    }
                                                                }
                                                           //System.out.println(arraylist);
                                                           /*if(Terms_founded.get(i).get(0).equals(Query_PL.get(i).get(0))
                                                              &&Terms_founded.get(i+1).get(0).equals(Query_PL.get(i+1).get(0))){*/
                                                           Doc_match_true.add(Terms_founded.get(i).get(j).toString());
                                                                for(int t=0;t<Doc_match_true.size()-1;t++){
                                                                    for(int x=t+1;x<Doc_match_true.size();x++){
                                                                    if(Doc_match_true.get(t).equals(Doc_match_true.get(x))){
                                                                        Doc_match_true.remove(x);
                                                                    }
                                                                    }
                                                                }
                                                            //}
                                                       }
                                                   }
                                                   else
                                                       break;
                                               }
                                            }
//                                            else
//                                                       break;
                                        }
                                        break;
                                    }
                                }   
                            }
                        }
                    }
                }
            }
        }
            
        return Doc_match_true;
        }
        else
            return Doc_match;
        
    }
    
    
    
     static boolean isInt(String message)
     {
         try
         {
             int isint = Integer.parseInt(message);
             return true;
         }
         catch(NumberFormatException e)
         {
            return false;
         }
     }
    
}
