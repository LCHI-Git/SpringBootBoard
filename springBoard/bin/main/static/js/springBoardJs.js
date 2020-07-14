
	const ICB = document.querySelector('#insertCommentButton');
	const SCB = document.querySelector('#saveCommentButton');
	const CCB = document.querySelector('#cancleCommentButton');
	const CA = document.querySelector('#CommentAfter');
	const CB = document.querySelector('#CommentBefore');
	const CC = document.querySelector('#contentCounter');
	const TA = document.querySelector('#textArea');
	const CW = document.querySelector('#CommentWriter');
	const CF = document.querySelector('#CommentForm');
	const SI = document.querySelector('#sbIdx');
	const CI = document.querySelector('#comment_idx');
	const EP = document.querySelector('#editPost'); 
	const DP = document.querySelector('#deletePost'); 
	const CP = document.querySelector('#currentPage');
	const CPass = document.querySelector('#commentPassword');
	
//	글 목록 돌아가기 이벤트
	function postListHandle(){
		window.history.back();	
	}
	
	
	
	function moveIndexPage(){
		window.history.go(-1);	
	}
	
	
//	댓글 취소 이벤트
	function cancleCommentHandle(){
		changeShow(CB);
	}
	
//	댓글달기 버튼 이벤트
	function insertCommentHandle(){
		changeShow(ICB);
	}
	
//  댓글 길이 카운트 이벤트CommentCountHandle
	function CommentCountHandle(){
		let contentLength = TA.value.length;
		CC.innerHTML="("+contentLength+"/100)";
	}

//	댓글저장 이벤트
	function saveCommentHandle(){
		if(CW.value.length!=0 && TA.value.length!=0){
			CF.submit();
		}
		else{
			alert("작성자 또는 내용을 확인해주세요");
		}
	}
	
	
//	본문 삭제 버튼 이벤트	
	function deletePostHandle(){
		passwordCheckAjax('deletePost'); 
	}
	
//	본문 수정 버튼 이벤트
	function editPostHandle(){
		passwordCheckAjax('editPostView');
	}
	
	function re(){
		
		let preContent = document.querySelector('#preContent');
		
		let str = preContent.innerText.replace(/<br>/gi,'\r\n');
		
		preContent.innerText=str;
		
		
	};
	
	
//	생성자
	function init(){
		console.log('init');
		//content
		
		ICB.addEventListener('click',insertCommentHandle);
		CCB.addEventListener('click',cancleCommentHandle);
		TA.addEventListener('keyup',CommentCountHandle);
		SCB.addEventListener('click',saveCommentHandle);
		EP.addEventListener('click',editPostHandle);
		DP.addEventListener('click',deletePostHandle);
		SI.value=getParam('sb_idx');
		CP.value=getParam('currentPage');
		re();
		
		
	}init();
	
	
//	show 토글
	function changeShow(div){
		if(div==ICB){
			div.style.display='none';
			CA.style.display='block';
		}
		else{
			ICB.style.display='block';
			CA.style.display='none';
		}
	}
	
	// url 에서 parameter 추출
	function getParam(sname) {
	    let params = location.search.substr(location.search.indexOf("?") + 1);
	    let sval = "";
	    params = params.split("&");
	    for (var i = 0; i < params.length; i++) {
	        temp = params[i].split("=");
	        if ([temp[0]] == sname) { sval = temp[1]; }
	    }
	    return sval;
	}
	
	//댓글 수정 버튼 클릭시 입력 div 보여주고 숨기기
	function updateComment(idx){
		
		let sig = idx.substring(0,1);
		if(sig!='-'){
			if(commentPasswordCheckAjax()){
				let updateDiv = document.getElementById("update"+idx);
				let updateBtn = document.getElementById(idx);
					
				if(idx>0){
					updateDiv.style.display='block';
					updateBtn.style.display='none';
				}else{
					updateDiv = document.getElementById("update"+idx*-1);
					updateBtn = document.getElementById(idx*-1);
					updateBtn.style.display='block';
					updateDiv.style.display='none';
				}
			}
		}
		
		else{
			let updateDiv = document.getElementById("update"+idx);
			let updateBtn = document.getElementById(idx);
			
			if(idx>0){
				updateDiv.style.display='block';
				updateBtn.style.display='none';
			}else{
				updateDiv = document.getElementById("update"+idx*-1);
				updateBtn = document.getElementById(idx*-1);
				updateBtn.style.display='block';
				updateDiv.style.display='none';
			}
		}
		
	}
	
	function deleteComment(result){
		if(commentPasswordCheckAjax()){
			let array = result.split("&");
			location.href='deleteComment?comment_idx='+array[0]+'&currentPage='+array[1]+'&sb_idx='+array[2];
		}
	}
	
	
	
	
	
	
	function commentPasswordCheckAjax(){
		let q = prompt("비밀번호를 입력해주세요");
		let result;
		let data = {
			userInputPassword : q,
			comment_password : CPass.value,
			comment_idx : CI.value
		}
		
		let xmlHttp;
		if (window.XMLHttpRequest) {  
		    xmlHttp = new XMLHttpRequest();
		} 
		
		else {  
		    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
	
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			let flag = xmlHttp.responseText=='true';
			console.log('cpa flag : '+flag);
	    	if(flag==false){
	    		alert('비밀번호 불 일치');
	    		result = false;
	    	}
	    	if(flag==true){
	    		alert('일치합니다');
	    		result = true;
	    	}
		}
	}
	xmlHttp.open("POST", "checkCommentPassword", false); 
	xmlHttp.setRequestHeader('Content-Type', 'application/json');
	xmlHttp.send(JSON.stringify(data)); 
	return result;
	}
	
	
	
	
	// 수정,삭제 xmlHttp 통신
	function passwordCheckAjax(command){
		let q = prompt("비밀번호를 입력해주세요");	
		let data = {
				userInputPassword : q,
				sb_idx : SI.value,
				currentPage : CP.value
		}
		
		let xmlhttp;
		if (window.XMLHttpRequest) {  
		    xmlhttp = new XMLHttpRequest();
		} 
		
		else {  
		    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {  
		    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		    	
		    	let flag = JSON.parse(xmlhttp.responseText)==true;
		    	
		    	if(flag==false)
		    		alert('비밀번호 불 일치');
		    	if(flag==true){
		    		alert('일치합니다'); 
		    		location.href=command+'?sb_idx='+data.sb_idx+'&currentPage='+data.currentPage;
		    	}
		         
		    }
		}
		
		xmlhttp.open("POST", "checkPassword", true); 
		xmlhttp.setRequestHeader('Content-Type', 'application/json');
		xmlhttp.send(JSON.stringify(data)); 
	}
	
	
	