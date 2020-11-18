/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationretrival;

import java.util.ArrayList;

/**
 *
 * @author Akram
 */
public class Search {
    private ArrayList<String> Return_Doc = new ArrayList<>();
    public void Search_Return(String Query,String QueryType) {
            
            
//-----------------------------To Run Positional Index Just One Time-----------------------------------        
                Poistional_Index PI = new Poistional_Index();
                if(!PI.check_File()){
                PI.frtokenizer("Doc1.txt","Doc1");
                PI.frtokenizer("Doc2.txt","Doc2");
                PI.frtokenizer("Doc3.txt","Doc3");
                PI.frtokenizer("Doc4.txt","Doc4");
                PI.frtokenizer("Doc5.txt","Doc5");
                PI.frtokenizer("Doc6.txt","Doc6");
                PI.frtokenizer("Doc7.txt","Doc7");
                PI.frtokenizer("Doc8.txt","Doc8");
                PI.frtokenizer("Doc9.txt","Doc9");
                PI.frtokenizer("Doc10.txt","Doc10");
                PI.frtokenizer("Doc11.txt","Doc11");
                PI.frtokenizer("Doc12.txt","Doc12");
                PI.frtokenizer("Doc13.txt","Doc13");
                PI.frtokenizer("Doc14.txt","Doc14");
                PI.frtokenizer("Doc15.txt","Doc15");
                PI.frtokenizer("Doc16.txt","Doc16");
                PI.frtokenizer("Doc17.txt","Doc17");
                PI.frtokenizer("Doc18.txt","Doc18");
                PI.frtokenizer("Doc19.txt","Doc19");
                PI.frtokenizer("Doc20.txt","Doc20");
                PI.fw();
                }
                
                //--------------------------------------------------------------------------------------------------------
//------------------------------To Run Vector Space Model Just One Time-----------------------------------
            
            
                Vector_Space_Model VSP = new Vector_Space_Model();
                if(!VSP.check_File()){
                VSP.Files_Terms("Doc1.txt","Doc1");
                VSP.Files_Terms("Doc2.txt","Doc2");
                VSP.Files_Terms("Doc3.txt","Doc3");
                VSP.Files_Terms("Doc4.txt","Doc4");
                VSP.Files_Terms("Doc5.txt","Doc5");
                VSP.Files_Terms("Doc6.txt","Doc6");
                VSP.Files_Terms("Doc7.txt","Doc7");
                VSP.Files_Terms("Doc8.txt","Doc8");
                VSP.Files_Terms("Doc9.txt","Doc9");
                VSP.Files_Terms("Doc10.txt","Doc10");
                VSP.Files_Terms("Doc11.txt","Doc11");
                VSP.Files_Terms("Doc12.txt","Doc12");
                VSP.Files_Terms("Doc13.txt","Doc13");
                VSP.Files_Terms("Doc14.txt","Doc14");
                VSP.Files_Terms("Doc15.txt","Doc15");
                VSP.Files_Terms("Doc16.txt","Doc16");
                VSP.Files_Terms("Doc17.txt","Doc17");
                VSP.Files_Terms("Doc18.txt","Doc18");
                VSP.Files_Terms("Doc19.txt","Doc19");
                VSP.Files_Terms("Doc20.txt","Doc20");
                VSP.fw_TF();
                VSP.fw_IDF();
                VSP.fw_TF_IDF();
                VSP.fw_E_L();
                }
                
//-------------------------------------------------------------------------------------------------------
//----------------------------phrase_query_search--------------------------------------------------------
                if(QueryType.equals("Phrase Query")){
                
                Phrase_Query_Search pqs = new Phrase_Query_Search();
                pqs.Query_Search(Query);
                for(int i=0;i<pqs.Docs_Return().size();i++){
                 Return_Doc.add(pqs.Docs_Return().get(i));
                }
                }

//--------------------------------------------------------------------------------------------------------
//--------------------------------------Free Text Query Search-------------------------------------------- 
                else if(QueryType.equals("Free Text Query")){

                Free_Text_Query_Search FTQS =new Free_Text_Query_Search();
                FTQS.Query_Search(Query);
                FTQS.best_sim();
                System.out.println(FTQS.save);
                for(int i=0;i<FTQS.best_sim().size();i++){
                Return_Doc.add(FTQS.best_sim().get(i));
                }
                 
                }
            
            
    }
    
    public ArrayList<String> Doc_Return(){
        return Return_Doc;
    }
    
}
