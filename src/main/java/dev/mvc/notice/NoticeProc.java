package dev.mvc.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter{
	@Autowired
	private NoticeDAOInter noticeDAO;
	
	@Override
	public int create(NoticeVO noticeVO) {
		int cnt = this.noticeDAO.create(noticeVO);
		
		return cnt;
	}

	@Override
	public ArrayList<NoticeVO> list_all() {
		ArrayList<NoticeVO> list = this.noticeDAO.list_all();
		
		for (NoticeVO noticeVO : list) {
		      String ntitle = noticeVO.getNtitle();
		      String ncontent = noticeVO.getNcontent();
		      
		      ntitle = Tool.convertChar(ntitle);  // 특수 문자 처리
		      ncontent = Tool.convertChar(ncontent); 
		      
		      noticeVO.setNtitle(ntitle);
		      noticeVO.setNcontent(ncontent);  

		    }
		
		return list;
	}

	@Override
	public NoticeVO read(int noticeno) {
		NoticeVO noticeVO = this.noticeDAO.read(noticeno);
		return noticeVO;
	}

}
