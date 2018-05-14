<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/lightbox.css" rel="stylesheet" type="text/css">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/lightbox.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){
	// 업로드 다이알로그
	var dialogUpload = $( "#dialog-upload-form" ).dialog({
		autoOpen: false,
		height: 280,
		width: 300,
		modal: true,
		buttons: {
			"업로드": function(event) {
				$( "#dialog-upload-form form" ).submit();
				
				event.preventDefault();
				
				var 
				
				
				
				 	
				$( this ).dialog( "close" );
			},
			"취소" : function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( "#dialog-upload-form form" ).get(0).reset();	
		}
	});
		
	$("#upload-image").click( function(event) {
		event.preventDefault();
		dialogUpload.dialog( "open" );
	});
});	
</script>
<style>

#gallery h1	{
     
     width:400px;
     max-width:500px;
     display:inline;

}

#hi:before {background-image: url(${pageContext.servletContext.contextPath }/assets/images/gallery.png);
background-size: 30px 30px;
padding-right:10px;
padding-top:10px;
line-height:10px;
width:20px;
height:20px;
display:inline-block;

content:"";
}

#upload-image{
display:block;
float:right;
}

#gallery ul li {
	float:left;
	position:relative;
	
	border:3px double #696969 ;	
	width:110px;
	margin-right:10px;
	margin-bottom:10px;
	heigth:120px;
	overflow-y:hidden;
    
}

#gallery ul li .image {
	width:110px;
	height:110px;
	display:block;
	background-size: 110px;
	background-repeat: no-repeat;
	background-position: center center;
	
	
}


#gallery ul li:hover{




}



#gallery ul li .del-button {
    background-image: url(${pageContext.servletContext.contextPath }/assets/images/delete-imge.png);
    background-repeat :no-repeat;
    width: 15px;
    height:15px;
    font-size:0px;
	background-size:15px;
    position:absolute;
    left:88px;
	top:0px;	
	display:none;
}

#gallery ul li:hover .del-button {
	display:block;
}







</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1 id="hi">갤러리</h1>
					<a href="" id="upload-image">이미지 올리기</a>
				</div>
				<ul>
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im1.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im1.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/1"
								class="del-button"
								title="삭제">삭제
								</a>
								
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im2.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im2.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im3.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im3.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/3"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im4.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im4.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/4"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
	
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im5.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im5.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/5"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im6.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im6.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/6"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im7.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im7.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/7"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im8.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im8.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/8"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im9.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im9.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/9"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im10.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im10.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/10"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im11.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im11.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/11"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im12.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im12.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/12"
								class="del-button"
								title="삭제">삭제</a>
						</li>																														
		
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im13.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im13.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/13"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im14.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im14.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/14"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						<li>
							<a	href="${pageContext.request.contextPath }/assets/gallery-examples/im15.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath }/assets/gallery-examples/im15.jpg')"> </a>
								
							<a	href="${pageContext.request.contextPath }/gallery/delete/15"
								class="del-button"
								title="삭제">삭제</a>
						</li>																																				
				</ul>	
			</div>

			<div id="dialog-upload-form" title="이미지 업로드" style="display:none">
  				<p class="validateTips normal">이미지와 간단한 코멘트를 입력해 주세요.</p>
  				<form action="${pageContext.request.contextPath }/gallery/upload" 
  					  method="post"
  					  enctype="multipart/form-data">
					<label>코멘트</label>
					<input type="text" id="input-comments" name="comments" value="">
					<label>이미지</label>
					<input type="file" id="input-file" name="file">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>