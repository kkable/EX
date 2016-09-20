package comtrue.homework.persistence;

import java.util.List;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import comtrue.homework.domain.Criteria;
import comtrue.homework.domain.MemberVO;
import comtrue.homework.domain.SearchType;

@Repository
public class MemberDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "comtrue.homework.persistence.MemberMapper.";

	public int insert(MemberVO vo){
		return session.insert(namespace+"insert",vo);
		
	}
	public int totalcnt(){
		return session.selectOne(namespace+"totalcnt");
		
	}
	public int searchcnt(SearchType vo){
		return session.selectOne(namespace+"searchcnt",vo);
	}
	
	public MemberVO selecteone(int num){
		return session.selectOne(namespace+"selecteone",num);
		
	}
	public List<MemberVO> selectelist(Criteria cri){
		return session.selectList(namespace+"selectelist",cri);
		
	}
	public List<MemberVO> all(){
		return session.selectList(namespace+"all");
		
	}
	
	public List<MemberVO> searchlist(SearchType vo){
		return session.selectList(namespace+"searchlist", vo);
	}
	public int update(MemberVO vo){
		return session.update(namespace+"update",vo);
		
	}
	public int delete(int num){
		return session.delete(namespace+"delete",num);
		
	}
	

}
