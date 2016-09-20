package comtrue.homework.domain;

public class SearchType {
String searchtype,keyword;
int num,page;
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public String getSearchtype() {
	return searchtype;
}
public void setSearchtype(String searchtype) {
	if(this.keyword != null){
		if(searchtype.equals("num")){
			this.num = Integer.parseInt(keyword);
		}
	}
	this.searchtype = searchtype;
}
public String getKeyword() {
	return keyword;
}
public String getDbkeyword() {
	return "%"+keyword+"%";
}
public void setKeyword(String keyword) {
	if(this.searchtype != null){
		if(this.searchtype.equals("num")){
			this.num = Integer.parseInt(keyword);
		}
	}
	this.keyword = keyword;	
}
public int getNum() {
	return num;
}
public int getDbnum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
@Override
public String toString() {
	return "?searchtype=" + searchtype + "&keyword=" + keyword + "&num=" + num ;
}
}
