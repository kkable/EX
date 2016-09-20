package comtrue.homework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import comtrue.homework.domain.Criteria;
import comtrue.homework.domain.MemberVO;
import comtrue.homework.domain.SearchType;
import comtrue.homework.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String regGET() {
		logger.info("register.......GET");
		return "member/reg";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> regPOST(MemberVO vo ) {
		logger.info("register.......POST");
		logger.info("vo  "+vo);
		try {
			service.insert(vo);
			return  new ResponseEntity<String>("/member/list/1",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResponseEntity<String>("fail",HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "list/{page}", method = RequestMethod.GET)
	public String list(Criteria cri,Model model) {
		logger.info("list.......GET");
		logger.info("Criteria  "+cri);
		try {
			model.addAttribute("list", service.selectlist(cri));
			model.addAttribute("cri",cri);
			model.addAttribute("uri","/member/list/");
		} catch (Exception e) {
			logger.info("Exception  "+e);
			// TODO: handle exception
		}
		
		return "member/list";
	}
	
	
	@RequestMapping(value = "list/{page}", method = RequestMethod.POST)
	public ResponseEntity<List<MemberVO>> searchlist(Criteria cri) {
		    List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			list = service.selectlist(cri);
			return new ResponseEntity<List<MemberVO>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<MemberVO>>(list,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	
	@RequestMapping(value = "/{num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> del(@PathVariable("num") int num) {
		try {
			service.delete(num);
			return  new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			return  new ResponseEntity<String>("fail",HttpStatus.FAILED_DEPENDENCY);
		}
	}
	@RequestMapping(value = "/{num}", method = RequestMethod.POST)
	public ResponseEntity<String> update(MemberVO vo,HttpServletResponse res) {
		System.out.println("**********"+vo);
		try {
			service.update(vo);
			return  new ResponseEntity<String>("/member/list/1",HttpStatus.OK);
			
		} catch (Exception e) {
			return  new ResponseEntity<String>("fail",HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@RequestMapping(value = "/{num}", method = RequestMethod.GET)
	public String selectone(@PathVariable("num")int num,Model model,RedirectAttributes rttr,HttpServletResponse res,HttpServletRequest req) {
		MemberVO vo = new MemberVO();
		String uri = req.getRequestURI();
		try {
			vo = service.selectone(num);
			model.addAttribute("member", vo);
			model.addAttribute("uri", uri);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				rttr.addAttribute("error", "번 회원에 문제점이 생겼습니다");
				res.sendRedirect(uri);
			} catch (IOException e1) {
				e1.printStackTrace();
				return "member/eror";
			}
		}
		return "member/reg";
	}
	@RequestMapping(value = "search/{page}", method = RequestMethod.GET)
	public String searchlist(SearchType vo,Criteria cri,Model model) {
		try {
			model.addAttribute("list", service.searchlist(cri, vo));
			model.addAttribute("cri",cri);
			model.addAttribute("searchtype",vo);
			model.addAttribute("uri","/member/search/");
		} catch (Exception e) {
			logger.info("Exception  "+e);
			// TODO: handle exception
		}
		return "member/list";
	}
	@RequestMapping(value = "/check/{num}", method = RequestMethod.GET)
	public ResponseEntity<String> check(SearchType vo,Criteria cri) {
		System.out.println("전"+vo );
		vo.setSearchtype("num");
		System.out.println("후"+vo );
		try {
			System.out.println("************************");
			List<MemberVO> list = service.searchlist(cri, vo);
			System.out.println("************"+list+"************");
			if(list.size()==0){
				return  new ResponseEntity<String>("possible",HttpStatus.OK);
			}else{
				return  new ResponseEntity<String>("fail",HttpStatus.FAILED_DEPENDENCY);
			}
		} catch (Exception e) {
			return  new ResponseEntity<String>("fail",HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "down", method = RequestMethod.GET)
	public ResponseEntity<byte[]>  down() {
			ResponseEntity<byte[]> entity = null;
		    String name = "회원_엑셀.xls";
	         HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	          try {
	        	  String filepath = service.makeExcelFile();
	        	  if(filepath != null){
	        		  FileInputStream in = new FileInputStream(filepath);
	        		  headers.add("Content-Disposition",
	        				  "attachment; fileName = \"" + new String(name.getBytes("UTF-8"), "ISO-8859-1") + "\"");
	        		  entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
	        		  return entity;
	        	  }else{
	        		  return  new ResponseEntity<byte[]>(null, headers, HttpStatus.FAILED_DEPENDENCY);
	        	  }
	        } catch (Exception e) {
	        	return  new ResponseEntity<byte[]>(null, headers, HttpStatus.FAILED_DEPENDENCY);
			}
	    }
	
	
	
	
}
