package comtrue.homework.controller;


import java.io.File;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import comtrue.homework.domain.Criteria;
import comtrue.homework.domain.MemberVO;
import comtrue.homework.domain.SearchType;
import comtrue.homework.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ServiceTest {

	@Inject
	MemberService service;
	@Test
	public void selectlist() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(5);
		System.out.println(service.selectlist(cri));
	}
	@Test
	public void selectone() throws Exception{
		service.makeExcelFile();
	}
	@Test
	public void insert() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setEmail("pyb1010114@naver.com");
		vo.setLevel("사원");
		vo.setPhone("01066593590");
			vo.setName("박용범(서비스)");
		System.out.println(service.insert(vo));
	}
	@Test
	public void CriteriaTest() throws Exception{
		Criteria cri = new Criteria();
		cri.setTotal(1);
		System.out.println(cri);
	}
	@Test
	public void search() throws Exception{
		Criteria cri = new Criteria();
		SearchType type = new SearchType();
		type.setSearchtype("num");
		type.setKeyword("105");
		System.out.println(service.searchlist(cri, type));
		
	}
	
	
	
}
