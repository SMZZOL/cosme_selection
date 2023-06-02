package dev.mvc.cosmetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.cosmetype.cosmetypeProc")
public class cosmetypeProc implements cosmetypeProcInter{
	@Autowired
	public cosmetypeDAOInter cosmetypeDAO;
	
	public int insert_cosmetype(cosmetypeVO cosmetypevo) {
		int cnt =this.cosmetypeDAO.insert_cosmetype(cosmetypevo);
		return cnt;
	}
}
	