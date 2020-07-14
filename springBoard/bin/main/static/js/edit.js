//write
	const PSB = document.querySelector('#postSave');
	const PLB = document.querySelector('#postList');
	
	const PWF = document.querySelector('#postWriteForm'); 
	
	const PW = document.querySelector('#postWriter');
	const PT = document.querySelector('#postTitle');
	const TA = document.querySelector('#postContent');
	const PP = document.querySelector('#postPassword');
	const TC = document.querySelector('#textCount')
	
	function writeCountHandle(){
		let contentLength = TA.value.length;
		TC.innerHTML="("+contentLength+"/500)";
	}
		
//	글 목록 돌아가기 이벤트
	function postListHandle(){
		window.history.back();	
	}
	
//	글 저장 이벤트	
	function postEditHandle(){
		if(PW.value.length != 0 && PT.value.length != 0 && TA.value.length != 0){
			if(PP.value.length == 0){
				PP.value='0000';
				PWF.submit();
			}
			else{
				PWF.submit();
			}
		}
		else{alert('비어있는 입력이 있습니다.');}
	}	
	
	function re(){
		
		
		
		let str = TA.value.replace(/<br>/gi,'\r\n');
		
		TA.innerHTML=str;
		
	};
	
//	생성자
	function init(){
		//write
		TA.addEventListener('keyup',writeCountHandle);
		PSB.addEventListener('click',postEditHandle);
		PLB.addEventListener('click',postListHandle);
		re();
	}init();