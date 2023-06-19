package dev.mvc.fboard;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.fboard.FboardProc")
public class FboardProc implements FboardProcInter{
	@Autowired
	private FboardDAOInter fboardDAO;

	@Override
	public int create(FboardVO fboardVO) {
		int cnt = this.fboardDAO.create(fboardVO);
		return cnt;
	}

	@Override
	public ArrayList<FboardVO> list_all() {
		ArrayList<FboardVO> list = this.fboardDAO.list_all();
		
		for (FboardVO fboardVO : list) {
		      String ftitle = fboardVO.getFtitle();
		      String fcontent = fboardVO.getFcontent();
		      
		      ftitle = Tool.convertChar(ftitle);  // 특수 문자 처리
		      fcontent = Tool.convertChar(fcontent); 
		      
		      fboardVO.setFtitle(ftitle);
		      fboardVO.setFcontent(fcontent);  

		    }
		return list;
	}

	@Override
	public FboardVO read(int fboardno) {
		FboardVO fboardVO = this.fboardDAO.read(fboardno);
		return fboardVO;
	}

}
