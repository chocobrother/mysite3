<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
//jQuery plugin
(function($){
	$.fn.hello = function(){
		var $element = $(this);
		console.log($element.attr("id")+ ":hello~");
	}
})(jQuery);


var isEnd = false;

var ejsListItem = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/template/listitem.ejs"
});

var messageBox = function(title, message, callback){
	
	$("#dialog-message").attr("title", title);
	$("#dialog-message p").text(message);
	
	$( "#dialog-message" ).dialog({
	      modal: true,
	      buttons: {
	        "확인": function() {
	          $( this ).dialog( "close" );
	        }
	      },
	      close: callback || function(){}
	    });
	
	
}
var render = function(mode, vo ){
	
	var html = ejsListItem.render(vo);
		 /*"<li data-no='" + vo.no + "'>" + 
		 "<strong>"+vo.name+"</strong>" +
		 "<p>" + vo.content.replace(/\n/gi,"<br>") + "</p>"+
		 "<strong></strong>" +
		 "<a href = '#' data-no='"+vo.no+"'>삭제</a>" +
		 "</li>";*/
 

		
	 	if(mode==true){
	 		$("#list-guestbook").prepend(html);
	 	} else {
	 		$("#list-guestbook").append(html);
	 	}
	 	//$("#list-guestbook")[ mode ? "prepend" : "append" ](html);
}



var fetchList = function(){
	
	if(isEnd == true){
		return;
	}
	
	var startNo = $("#list-guestbook li").last().data("no") || 0;
	
	$.ajax({
		
	 url:"/mysite3/api/guestbook/list?no=" + startNo,
	 type:"get",
	 dataType:"json",
	 success: function(response){
		 console.log(response);
		 
		 
		 //성공 유무
		 if(response.result != "success"){
			 console.log(response.message);
			 return;
		 }
		 
		 //끝 감지
		 if(response.length < 5){
			 isEnd = true;
			 $("btn-fetch").prop("disabled", true);
		 }
		 //render
		 $.each(response.data, function(index, vo){
		      render(false, vo );
		 });
		
		 
		 
	 }
	
	});
	
	
}


$(function(){
	
	//삭제시 비밀번호 입력 모달 다이알로그
	 var deletedialog = $( "#dialog-delete-form" ).dialog({
	      autoOpen: false,
	      modal: true,
	      buttons: {
	        "삭제": function(){
	        	
	        	var password = $("#password-delete").val();
	        	var no = $("#hidden-no").val();
	        	
	        	console.log(password + ":" + no);
	        	
	        	//ajax 통신
	        	
	        	$.ajax({
	        		url: "/mysite3/api/guestbook/delete",
	        		type:"post",
	        		dataType:"json",
	        		data:"no=" + no + "&password=" + password,
	        		success: function(response){
	        		
	        			if(response.result == "fail"){
	        				console.log(response.message);
	        				return;
	        			}
	        			
	        			if(response.data == -1){
	        				
	        				$(".validateTips.normal").hide();
	        				$(".validateTips.error").show();
	        				
	        				$("#password-delete").val("");
	        				
	        				return;
	        			}
	        			
	        			
	        			$("#list-guestbook li[data-no=" + response.data + "]").remove();
	        			deletedialog.dialog("close");
	        			
	        			
	        		}
	        	});
	        	
	        },
	        "취소": function() {
	        	deletedialog.dialog( "close" );
	        }
	      },
	      close: function() {
				$("#password-delete").val("");
				$("#hidden-no").val("");
				
				$(".validateTips.normal").show();
				$(".validateTips.error").hide();
	    	  
	      }
	    });
	
	//Live Event Listener
	$(document).on("click", "#list-guestbook li a",function(event){
	
		event.preventDefault();
	
		var no = $(this).data("no");
		
		$("#hidden-no").val(no);
		
		deletedialog.dialog("open");
	});
	
	$("#add-form").submit(function(event){
		
		event.preventDefault();
		
	/*	var queryString = $(this).serialize();
	   // 방법 1 .스트링으로 보내는법
		$.ajax({
			
			url :"/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json",
			data: queryString,
			success: function(response){
				render(true, response.data );
				$("#add-form")[0].reset();
				
			}
			
			
		});*/
		
		//방법 2 객체로 보내주는 방법
		var data = {};
		
		$.each($(this).serializeArray(),function(index, o){
			data[o.name] = o.value;
			
		});
		
		if(data["name"] == ''){

			messageBox("메세지 등록", "이름이 비어 있습니다.", function(){
				$("#input-name").focus();
			});
			
		
			return ;
		}
		if(data["password"] == ''){
			messageBox("메세지 등록", "비밀번호가 비어 있습니다.",function(){
				$("#input-password").focus();
			});
			
			return ;
		}
		if(data["content"] == ''){

			messageBox("메세지 등록", "내용이 비어 있습니다.", function(){
				$("#tx-content").focus();
			});
			
			return ;
		}
		//제이슨으로 나옴
		console.log(data);
		
		
		
		$.ajax({
			
			url :"/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json",//내가 받는 타입
			contentType:"application/json",//내가보내는 타입
			data: JSON.stringify(data),//보내는 값
			
			success: function(response){
				render(true, response.data );
				$("#add-form")[0].reset();
				
			}
			
			
		});
		
	});
	
	
	$("#btn-fetch").click(function(){
		
		 fetchList();
		
	});
	
	
	$(window).scroll(function(){
		
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
	//	console.log( scrollTop + ":" + windowHeight +":"+ documentHeight);
	
		//scrollbar의 thumb가 바닥 전 30픽셀 까지 도달했을 떄 패치 
		if(scrollTop + windowHeight+ 30 > documentHeight  ){fetchList();}
			
	});
	//최초 리스트 가져오기
	fetchList();
	
	// plugin 테스트
	$("#container").hello();
	
});

</script>
<style>

#guestbook		{ margin:20px auto;	width:540px; }

#guestbook h1	{
	color: #464A4F;
	margin-bottom: 10px;
	padding-left: 40px;
	background: url('/mysite3/assets/images/guestbook.png') 0 0 no-repeat;	
	background-size: 35px;
} 

#guestbook form input,
#guestbook form textarea {
	display:block;
	border: 1px solid #a9a9a9;
	outline: none;
	width: 540px;
	padding: 5px;
	margin-bottom: 10px;
}

#guestbook form textarea {
	height:80px;
	resize: none;
}

#guestbook form input[type="submit"] {
	width:552px;
	font-weight:bold;
	color:#846b5c;
	background-color: #fff;
	cursor: pointer;
}

#guestbook form input[type="submit"]:hover {
	background-color: #65b791;
	color: #fff;
}

#guestbook ul {
	width:552px;
	margin-top: 20px;
	border-top:1px solid #a9a9a9;
}

#guestbook ul li {
	position: relative;
	width:100%;
	margin-top:20px;
	padding-left:50px;
	background: url('/mysite3/assets/images/user.png') 0 5px no-repeat;	
}

#guestbook ul li strong,
#guestbook ul li a {
	display: block;
}

#guestbook ul li strong {
	color:#777;
}

#guestbook ul li p {
	width: 465px;
	margin: 5px 0;
	padding: 10px;
	border: 1px solid #cacccc;
	border-radius: 4px;
	background-color: #eaeaea;
}

#guestbook ul li a {
	position: absolute;
	width: 20px;
	height: 20px;
	left:18px;
	top:26px;
	font-size: 0;
	background: url('/mysite3/assets/images/delete.png') 0 0 no-repeat;
}
</style>

<script>

var onClick = function(){
	alert("hi");
}

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1 id="hi">방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" name = "name" id="input-name" placeholder="이름">
					<input type="password" name = "password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				
				
				
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<button id = "btn-fetch">가져오기</button>
			<div id="dialog-message" title="" >
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>