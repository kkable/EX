package comtrue.homework.controller;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import comtrue.homework.domain.Criteria;
import comtrue.homework.domain.MemberVO;
import comtrue.homework.domain.SearchType;
import comtrue.homework.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DAOTest {

	@Inject
	MemberDAO dao;
	
	@Test
	public void selectone() throws Exception{
		System.out.println(dao.selecteone(5));
	}
	@Test
	public void insert() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setEmail("pyb1010114@naver.com");
		vo.setLevel("사원");
		vo.setPhone("01066593590");
		for (int i = 0; i < 188; i++) {
			vo.setName("박용범("+(i)+")");
			System.out.println(dao.insert(vo));	
		}
		
	}
	
	@Test
	public void totalcnt() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(5);
		cri.setTotal(dao.totalcnt());
		System.out.println(cri);
		System.out.println(dao.selectelist(cri));
		
	}
	
	@Test
	public void searchcnt() throws Exception{
		SearchType type = new SearchType();
		type.setKeyword("name");
		type.setSearchtype("박");
		type.setNum(5);
		System.out.println(type);
		System.out.println(dao.searchcnt(type));
		System.out.println(type);
	}
	@Test
	public void search() throws Exception{
		SearchType type = new SearchType();
		type.setKeyword("현");
		type.setSearchtype("name");
		type.setPage(1);
		System.out.println(type);
		System.out.println(dao.searchlist(type));
		System.out.println(type);
	}
	@Test
	public void SearchType() throws Exception{
		SearchType type = new SearchType();
		System.out.println(dao.searchlist(type));
	}	
	@Test
	public void update() throws Exception{
		MemberVO vo = dao.selecteone(5);
		System.out.println(vo);
		vo.setName("바??lll");
		vo.setUpdatenum(456);
		dao.update(vo);
		System.out.println(dao.selecteone(456));
	}
	
	
	
	
}
