/**
 * 
 */
/**
 * 
 */
//var sock = new SockJS('/ws-green-bot');
var client;
var key;
let flag=false;
// 브라우저가 WebSocket을 지원하는지 확인하는 함수
function isWebSocketSupported() {
	return 'WebSocket' in window;
}

// WebSocket 지원 여부를 출력
if (isWebSocketSupported()) {
	console.log("이 브라우저는 WebSocket을 지원합니다.");
} else {
	console.log("이 브라우저는 WebSocket을 지원하지 않습니다.");
}

function formatTime(){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var hour=now.getHours()%12;
	if(hour==0)hour=12;
	var minute=now.getMinutes();
	return `${ampm} ${hour}:${minute}`;
}



function formatDate(now){
	//년도
	const year=now.getFullYear();
	//월
	const month=now.getMonth()+1;
	//일
	const date=now.getDate();
	//일:0 월:1 화:2 . . .
	const dayOfWeek=now.getDay();
	const days=['일요일','월요일','화요일','수요일','목요일','금요일','토요일'];
	//console.log(year,":",month,":",date,":",days[dayOfWeek]);
	return `${year}년 ${month}월 ${date}일 ${days[dayOfWeek]}`;
}

//대화내용추가(인삿말 추가)
function showMessage(tag){
	$("#chat-content").append(tag);
	//스크롤을 제일 아래로
	//자동으로 스크롤이 마지막 대화로 내려감
	$("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

//웹소켓 연결후 인삿말 출력
//여기의 subscribe와 botcontreoller 의 convertAndSend와 위치가 일치해야함
function connect() {
	client = Stomp.over(new SockJS('/ws-green-bot'));
	client.connect({}, (frame) => {
		key = new Date().getTime();
		//console.log(frame)
		//구독설정
		//body로 부터 메세지를 받아야한다
		client.subscribe(`/topic/bot/${key}`, (answer) => {
			var msgObj = answer.body;
			//console.log("answer:", answer);
			//console.log("msg:", msgObj);
			//화면에 나오는 id를 jQuery로 append
			var now=new Date()
			var time=formatTime();
			var date=formatDate(now);
			var tag = `
						<div class="flex center date">
							${date}
						</div>
						<div class="msg bot flex">
							<div class="icon">
									<img src="/images/icon/robot-solid.svg">
								</div>
								<div class="message">
									<div class="part">
										<p>${msgObj}</p>
									</div>
									<div class="time">${time}</div>
								</div>
							</div >
							`;
			showMessage(tag)
		});
		//JSON
		//*
		var data = {
			key: key,
			content: "hello",
			name: "guest" //시큐리티의 인증정보의 사용자 name 정보를 가져와줘
			//시큐리티의 principal 데이터를 가져와라
			//페이지에 뿌려놓고 자바스크립트에서 가져와도되고, 다이렉트로 principal.getName등을 통해 가져오면됨.
		}
		//접속하자마자 연결시도
		client.send("/bot/hello", {}, JSON.stringify(data));
		//*/
	})
}

//소켓 종료
function disconnect() {
	client.disconnect(() => {
		console.log("Disconnected...")
	});
}
//종료(x) 클릭시 이벤트

function btnCloseClicked() {
	
	$("#bot-container").hide();
	//대화창 리셋
	$("#chat-content").html("");
	disconnect();
	flag=false;
}

//챗봇시작 버튼이벤트

function btnBotClicked() {
	if(flag)return;
	
	//1. 소켓 접속
	$("#bot-container").show();
	connect()
	flag=true;
}
function clearQuestion(){
	$("#question").val("");
}

//메세지 전송
function btnMsgSendClicked(){
	var question=$("#question").val().trim();
	if(question.length<2){
		alert("질문은 최소 2글자 이상으로 입력하세요.");
		//clearQuestion();
		return;
	}
	//2글자 이상이면 전송
	var data = {
			key: key,
			content: question,
			name: "guest" //시큐리티의 인증정보의 사용자 name 정보를 가져와줘
			//시큐리티의 principal 데이터를 가져와라
			//페이지에 뿌려놓고 자바스크립트에서 가져와도되고, 다이렉트로 principal.getName등을 통해 가져오면됨.
		}
		//접속하자마자 연결시도
		client.send("/bot/question", {}, JSON.stringify(data));
		clearQuestion();
}


$(function() {
	$("#btn-bot").click(btnBotClicked);
});