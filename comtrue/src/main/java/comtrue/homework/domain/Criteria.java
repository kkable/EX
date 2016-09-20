package comtrue.homework.domain;



public class Criteria {
	
	int page,startpage,endpage,pagesize,total,pagenum;
	boolean prev,next;
	   
	public Criteria() {
		super();
		this.page = 1;
	    this.pagesize = 10;
	    this.pagenum = 5;
	    this.next = true;
	    this.prev = true;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", startpage=" + startpage + ", endpage=" + endpage + ", pagesize="
				+ pagesize + ", total=" + total + ", prev=" + prev + ", next=" + next + "]";
	}
	public int getPage() {
		return ((page-1)*pagesize);
	}
	public void setPage(int page) {
		 if(page<1){
	         page=1;
	      }
	      this.page = page;
		
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotal() {
		return total;
	}
	 public void setTotal(double total) {
		 if(total == 0){
			 this.total = (int)total;
			 this.page = 1;
			 this.startpage = 1;
			 this.endpage = 1;
			 this.prev = false;
			 this.next = false;
			 return;
		 }
		   double dbpagesize = (double)this.pagesize;
		   double dbpage = (double)this.page;
		   double dbpagenum = (double)this.pagenum;
		   
		   
	       int lastpage = (int)Math.ceil(total/dbpagesize);
	       if(page >= lastpage){
	    	   this.page = lastpage;
	       }
	       
	       this.endpage =(int) ((int) Math.ceil(dbpage/dbpagenum)*dbpagenum);
	       this.startpage = this.endpage-(pagenum-1);
	       
	       if(this.startpage == 1){
	    	   this.prev = false;
	       }
	       
	       if(this.endpage>=lastpage){
	    	   this.endpage = lastpage;
	    	   this.startpage = (int) (Math.ceil(lastpage/dbpagenum)*pagenum)-(pagenum-1);
	    	   this.next = false;
	       }
	      this.total = (int)total;
	   }
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
}
