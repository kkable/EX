<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<html>
<head>
	<title>Comtrue_Homework</title>
</head>
<style>
th, td {
    border-bottom: 1px solid #ddd;
}
</style>
<body>
<h1>
	리스트 페이지
</h1>

<select name="searchtype" class="typeselect" >
	<option value="name" >이름</option>
	<option value="num">번호</option>
	<option value="email" >이메일</option>
	<option value="level" >직급</option>
	<option value="phone">전화번호</option>
</select> 
 <input name=keyword type="text" class="searchipt" value="${searchtype.getKeyword() }">
 <button class="searchbtn">검색</button>
 <button id="dbbtn">DB다운</button>
 <button id="regbtn">등록</button>
<div id="listdiv">
<table>
<tr>
<td>번호</td><td>이름</td><td>직급</td><td>이메일</td><td>전화번호</td><td></td><td></td>
</tr>
<c:forEach items="${list }" var="vo">
<tr><td>${vo.getNum() }</td><td>${vo.getName() }</td><td>${vo.getLevel() }</td><td>${vo.getEmail() }</td>
<td>${vo.getPhone() }</td>
<td><button class="delbtn" cusid="${vo.getNum() }" >삭제</button></td>
<td><button class="uptbtn" cusid="${vo.getNum() }" >수정</button></td>
</tr>
</c:forEach>
</table>
</div>
<c:if test="${cri.isPrev() }">
<a href="${uri }${cri.getStartpage()-1 }${searchtype.toString()}">이전</a>
</c:if>
<c:forEach begin="${cri.getStartpage() }" end="${cri.getEndpage() }" var="idx">
<a href="${uri }${idx }${searchtype.toString()}">${idx }</a>
</c:forEach>
<c:if test="${cri.isNext() }">
<a href="${uri }${cri.getEndpage()+1 }${searchtype.toString()}">다음</a>
</c:if>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var searchinit = function(){
		var optionlist = document.querySelectorAll('option');
		for (var i = 0; i < optionlist.length; i++) {
			if($(optionlist[i]).val() == "${searchtype.getSearchtype()}"){
				$(optionlist[i]).attr("selected","");
			}
		}
	}
	searchinit();
	
	var repage = function(data){
		var str = "<table><tr><td>번호</td><td>이름</td><td>직급</td><td>이메일</td><td>전화번호"+
				  "</td><td></td><td></td></tr>";
		for (var i = 0; i < data.length; i++) {
			str += 
			'<tr><td>'+data[i].num+'</td><td>'+data[i].name+'</td><td>'+data[i].level+'</td><td>'+
			data[i].email+'</td><td>'+data[i].phone+'</td>'+	
			'<td><button class="delbtn" cusid="'+data[i].num+'" >삭제</button></td>'+
			'<td><button class="uptbtn" cusid="'+data[i].num+'" >수정</button></td></tr>';
		}
		str += "</table>"
		$('#listdiv').html(str);
	}
	
	$(".delbtn").on("click",function(event){
		  var num = $(event.target).attr("cusid");
		  var page = "${cri.getPage()}";
		  $.ajax({
	          type: 'delete'
	        , url: '/member/'+num
	        , success: function(data) {
	        	alert(num+"번 직원이 삭제되었습니다")
	        	 $.ajax({
	   	          type: 'post'
	   	        , url: '/member/list/'+page
	   	        , success: function(data) {
	   	        	repage(data);
	   	         	}
	   	   		 });
	        	}
	  		,error :function(data){
	  			alert("삭제에 실패하였습니다")
           }
		  });	
		})
		
	$(".uptbtn").on("click",function(event){
		var num = $(event.target).attr("cusid");
		location.href="/member/"+num;
	})
	$("#dbbtn").on("click",function(event){
		location.href="/member/down";
	})
	
	$(".typeselect").on("change",function(event){
		var type = $(event.target).val();
		if(type=="num"||type=="phone"){
		$(".searchipt").attr("type","number");
		}else if(type=="email"){
		$(".searchipt").attr("type","email");
		}else{
		$(".searchipt").attr("type","text");
		}
	})
	$(".searchbtn").on("click",function(event){
		  var keyword = $(".searchipt").val();
		  var searchtype = $(".typeselect").val();
		  location.href = "/member/search/1?searchtype="+searchtype+"&keyword="+keyword;
	})
	$("#regbtn").on("click",function(event){
		   location.href = "/member/register";
	})
	
})
</script>
</body>
</html>
