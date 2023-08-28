package dev.mvc.ingred;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.ingred.IngredProc")
public class IngredProc implements IngredProcInter {
	
	@Autowired
	public IngredDAOInter ingredDAO;
	
	public int insert_ingred(IngredVO ingredvo) {
		int cnt = this.ingredDAO.insert_ingred(ingredvo);
		return cnt;
	}
	
	public ArrayList<IngredVO> ingred_list(){
		ArrayList<IngredVO> list = this.ingredDAO.ingred_list();
		
		return list;
	}
	public ArrayList<IngredVO> ingred_by_cosmeno(int  cosmeno){
		return this.ingredDAO.ingred_by_cosmeno(cosmeno);
	}

}
