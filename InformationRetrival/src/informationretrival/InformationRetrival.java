/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Akram
 */
public class InformationRetrival {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner Doc_name = new Scanner(System.in);
        Scanner x;
        Search S =new Search();
        System.out.print("Enter Query : ");
        String Query = Doc_name.nextLine();
        System.out.println("Enter Query Type : (Phrase Query) or (Free Text Query) ");
        String TOQ = Doc_name.nextLine();
        if(TOQ.equals("Phrase Query") || TOQ.equals("Free Text Query") ){
        S.Search_Return(Query, TOQ);
        
        if(S.Doc_Return().isEmpty()){
            System.out.println("Not found");
        }
        else{
            System.out.print(S.Doc_Return());
            System.out.println("\n");
            System.out.print("Enter FileName :  ");
            String line = Doc_name.next();
            boolean Check =false;
            for(int i=0;i<S.Doc_Return().size();i++){
                if(S.Doc_Return().get(i).equals(line)){
                    Check =true;
                    break;
                }
            }
            if(Check == true){
                line = line +".txt";
                try {
                    System.out.println("-------------------------------------------------------------");
                    x = new Scanner(new File(line));
                    while(x.hasNextLine()){
                        System.out.println(x.nextLine());
                    }
                    System.out.println("-------------------------------------------------------------");
                } catch (Exception e) {
                    System.out.println("Can not find file");
                }
                
            }
            else
                System.out.println("<<File Out Of The Result!!>>");
        }
        
        }else
            System.out.println("<<Query Type not Correct>>");
    }
    
}
