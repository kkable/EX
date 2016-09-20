<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<html>
<head>
	<title>Comtrue_Homework</title>
</head>
<body>
<h1>
	등록 페이지  
</h1>
<div id = "numdiv" hidden="true">
번호<input name="uptnum" id = "iptnum" type="number" value="0" style=" color:#ff0000; "class="ipts" ><button id="check">확인</button><br>
</div>
이름<input name="name" id = "iptname"class="ipts"><br>
직급<input name="level" id = "iptlevel"class="ipts"><br>
전화번호<input name="phone" type="number" id = "iptphone"class="ipts"><br>
email<input name="email" type="email" id = "iptemail"class="ipts"><br>
<button id="regbtn">등록</button>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		if("${member}"){
		$("#numdiv").show()
		$("#iptnum").val("${member.num}")
		$("#iptname").val("${member.name}")
		$("#iptlevel").val("${member.level}")
		$("#iptphone").val("${member.phone}")
		$("#iptemail").val("${member.email}")
	}
		var checker = true;
		
		$("#regbtn").on("click",function(event){
			var uri = "${uri}"
			var list = 	document.querySelectorAll(".ipts")
			if(checker){
				for (var i = 0; i < list.length; i++) {
					if($(list[i]).val() == ""){
						alert("모두 입력해주세요")
						return;
					   }
					}
			}else{
				alert("번호를체크해주세요")
				return;
			}
			
			 $.ajax({
	   	          type: 'post'
	   	        , url: uri
	   	        , data : {
	   	        	num : "${member.num}",
	   	        	name : $("#iptname").val(),
	   	        	phone : $("#iptphone").val(),
	   	        	level : $("#iptlevel").val(),
	   	        	email : $("#iptemail").val(),
	   	        	updatenum : $("#iptnum").val()
	   	        	
	   	        }
	   	        , success: function(data) {
	   	        	alert("성공하였습니다")
	   	   	  		location.href =data;
	   	        }
	   	        , error : function(data) {
	   	        	alert("실패하였습니다")
	   	        }
	   	   	});		
		})
		
		
		
		$("#check").on("click",function(event){
			 $.ajax({
	   	          type: 'get'
	   	        , url: "check/"+$("#iptnum").val()
	   	        , success: function(data) {
	   	        	document.querySelector("#iptnum").style.color="#00ff00"
	   	        	checker=true;
	   	   	  	}
	   	        , error : function(data) {
	   	        	alert("불가능")
	   	        	checker=false;
	   	        }
	   	   	});		
			
		})
		$("#iptnum").on("change",function(){
			document.querySelector("#iptnum").style.color="#ff0000"
   	        checker=false;
		})
		
		
})
</script>
</html>
