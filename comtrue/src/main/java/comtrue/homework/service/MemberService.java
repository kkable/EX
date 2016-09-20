package comtrue.homework.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comtrue.homework.domain.Criteria;
import comtrue.homework.domain.MemberVO;
import comtrue.homework.domain.SearchType;
import comtrue.homework.persistence.MemberDAO;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
@Service
@Transactional
public class MemberService {
	@Inject
	MemberDAO dao;
	
	    
	
	public int insert(MemberVO vo){
		return dao.insert(vo);
	}
	public int delete(int num){
		return  dao.delete(num);
	}
	public int update(MemberVO vo){
		return  dao.update(vo);
	}
	public MemberVO selectone(int num){
		return dao.selecteone(num);
	}
	public List<MemberVO> selectlist(Criteria cri){
		cri.setTotal(dao.totalcnt());
		return dao.selectelist(cri);
	}
	public List<MemberVO> searchlist(Criteria cri,SearchType vo){
		cri.setTotal(dao.searchcnt(vo));
		vo.setPage(cri.getPage());
		return dao.searchlist(vo);
	}
	
	
	public String makeExcelFile()
	{ 
		String filepath = "c:\\zzz\\temp.xls";
		WritableSheet ws;
		WritableWorkbook workbook = null;
		File file = new File(filepath);
		if(file.mkdirs()&&file.delete()){
		try {
			workbook = Workbook.createWorkbook(new File("c:\\zzz\\temp.xls"));
			ws = workbook.createSheet("회원 목록", 0);
			ws.addCell((new Label(0,0,"번호")));
			ws.addCell((new Label(1,0,"이름")));
			ws.addCell((new Label(2,0,"직급")));
			ws.addCell((new Label(3,0,"이메일")));
			ws.addCell((new Label(4,0,"전화")));
			List<MemberVO> list = dao.all();
			int i = 1;
			for (MemberVO memberVO : list) {
				ws.addCell((new Label(0, i, memberVO.getNum()+"")));
	            ws.addCell((new Label(1, i, memberVO.getName())));
	            ws.addCell((new Label(2, i, memberVO.getLevel())));
	            ws.addCell((new Label(3, i, memberVO.getEmail())));
	            ws.addCell((new Label(4, i, memberVO.getPhone())));
	            i++;
	        }
			workbook.write();
		} catch (Exception e) {return null;
		}finally {
			try {workbook.close();
				} catch (Exception e) {e.printStackTrace();return null;}
			}
		}
		return filepath;
	}
	
}
