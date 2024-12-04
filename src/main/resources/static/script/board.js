function loginCheck(){
	if(document.login.userid.value==""){
		alert("아이디를 입력하세요");
		document.login.userid.focus();
		return false;
	}else if(document.login.pwd.value==""){
		alert("패스워드를 입력하세요");
		document.login.pwd.focus();
		return false;
	}else{
		return true;
	}
}


function idcheck(){
	if(document.join.userid.value==""){
		alert("아이디를 입력하세요");
		document.login.userid.focus();
		return;
	}
	
	var inputid = document.join.userid.value;
	var opt = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=200";
	window.open('idcheck?userid=' + inputid, 'idcheck',opt);
	// inputid 변수에 저장된 아이디로 중복체크를 먼저하고 그 결과를 팝업창의 첫번째 내용을 보여줍니다.
	}
	
	function joinCheck(){
		if(document.join.userid.value.length < 4){
			alert("아이디는 4글자 이상이어야 합니다");
			document.join.userid.focus();
			return false;
		}else if(document.join.userid.value!=document.idCheckConfirm.value){
			alert("아이디 중복 검사를 진행하세요");
			document.join.userid.focus();
			return false;
		}else if(document.join.pwd.value==""){
			alert("비밀버호는 필수 입력입니다");
			document.join.pwd.focus();
			return false;
		}else if(document.join.pwd.value != document.join.pwd_check.value){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
			document.join.pwd_check.focus();
			return false;
		}else if(document.join.name.value==""){
			alert("이름은 필수 입력 사항입니다");
			document.join.name.focus();
			return false;
		}else if(document.join.email.value==""){
			alert("이메일은 필수 입력 사항입니다");
			document.join.email.focus();
			return false;
		}else if(document.join.phone.value==""){
			alert("전화번호는 필수 입력 사항입니다");
			document.join.phone.focus();
			return false;
		}else{
			return true;
		}
}


function updateCheck(){
	
			if(document.join.pwd.value==""){
				alert("비밀버호는 필수 입력입니다");
				document.join.pwd.focus();
				return false;
			}else if(document.join.pwd.value != document.join.pwd_check.value){
				alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
				document.join.pwd_check.focus();
				return false;
			}else if(document.join.name.value==""){
				alert("이름은 필수 입력 사항입니다");
				document.join.name.focus();
				return false;
			}else if(document.join.email.value==""){
				alert("이메일은 필수 입력 사항입니다");
				document.join.email.focus();
				return false;
			}else if(document.join.phone.value==""){
				alert("전화번호는 필수 입력 사항입니다");
				document.join.phone.focus();
				return false;
			}else{
				return true;
			}
	
}


function deleteConfirm(){
	var ans=confirm("정말로 탈퇴할거야?");
	if(ans){
		location.href="deleteMember";
	}
	
}

function boardCheck(){
	if(document.insertBoard.pass.value==""){
		alert("비밀번호는 수정 삭제시에 필요합니다")
		document.insertBoard.pass.focus();
		return false;
	}else if(document.insertBoard.title.value==""){
		alert("제목을 입력하세요")
		document.insertBoard.title.focus();
		return false;
	}else if(document.insertBoard.content.value==""){
		alert("내용을 입력하세요")
		document.insertBoard.content.focus();
		return false;
	}else if(document.insertBoard.email.value==""){
		alert("이메일을 입력하세요")
		document.insertBoard.email.focus();
		return false;
	}else{
		return true;
	}
}



function updateBoardCheck(){
	if(document.insertBoard.pass.value==""){
		alert("비밀번호 입력하세요")
		document.insertBoard.pass.focus();
		return false;
	}else if(document.insertBoard.title.value==""){
		alert("제목을 입력하세요")
		document.insertBoard.title.focus();
		return false;
	}else if(document.insertBoard.content.value==""){
		alert("내용을 입력하세요")
		document.insertBoard.content.focus();
		return false;
	}else if(document.insertBoard.email.value==""){
		alert("이메일을 입력하세요")
		document.insertBoard.email.focus();
		return false;
	}else{
		return true;
	}
	
}


function deleteBoardConfirm(num){
	var ans = confirm("정말로 삭제할까?");
	if(ans){
		
		window.open('checkPass?num=' + num, 'checkPass', 'width=500, height=300')
		//location.href='board.do?command=deleteBoard&num' + num;
	}
	
}


function updatePass(num){
	window.open('board.do?command=updatePassForm&num='+ num,
		'updatePass' , 'width=400, height=300');
	
}


function replyCheck(){
	if(document.addRep.content.value==""){
		alert("댓글 내용을 입력하세요")
		document.addRep.content.focus();
		return false;
	}else{
		return true;
	}
	
}


function deleteReply(replynum,boardnum){
	var ans=confirm("현재 댓글을 삭제할거야?");
	if(ans){
		location.href
		="deleteReply?replynum=" + replynum + "&boardnum=" + boardnum;
	}
	
}

function selectImg(){
	var opt="toolbar=no,menubar=no,resizable=no,width=450,height=200";
	window.open('selectimg','selectimg',opt);
}