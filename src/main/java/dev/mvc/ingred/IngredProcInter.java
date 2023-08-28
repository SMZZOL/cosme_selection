package dev.mvc.ingred;

import java.util.ArrayList;

public interface IngredProcInter {
	
	public int insert_ingred(IngredVO ingredvo);
	
	public ArrayList<IngredVO> ingred_list();
	public ArrayList<IngredVO> ingred_by_cosmeno(int  cosmeno);

}
