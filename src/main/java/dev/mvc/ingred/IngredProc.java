package dev.mvc.ingred;

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

}
