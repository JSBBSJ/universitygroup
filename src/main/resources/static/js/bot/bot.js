$(document).ready(function() {
    var client; // WebSocket 클라이언트
    var key; // 고유 키
    let flag = false; // 챗봇 창의 열림 상태 플래그

    // WebSocket 지원 여부 확인
    function isWebSocketSupported() {
        return 'WebSocket' in window;
    }

    if (isWebSocketSupported()) {
        console.log("이 브라우저는 WebSocket을 지원합니다.");
    } else {
        console.log("이 브라우저는 WebSocket을 지원하지 않습니다.");
    }

    // 현재 시간을 형식화하는 함수
    function formatTime() {
        var now = new Date();
        var ampm = (now.getHours() > 11) ? "오후" : "오전";
        var hour = now.getHours() % 12 || 12; // 0시는 12시로 표기
        var minute = String(now.getMinutes()).padStart(2, '0'); // 분이 한 자리일 경우 앞에 0 추가
        return `${ampm} ${hour}:${minute}`;
    }

    // 현재 날짜를 형식화하는 함수
    function formatDate(now) {
        const year = now.getFullYear();
        const month = now.getMonth() + 1;
        const date = now.getDate();
        const dayOfWeek = now.getDay();
        const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
        return `${year}년 ${month}월 ${date}일 ${days[dayOfWeek]}`;
    }

    // 메시지를 화면에 표시하는 함수
    function showMessage(tag, prepend = false) {
        if (prepend) {
            $(".chatbot-body").prepend(tag); // 맨 앞에 추가
        } else {
            $(".chatbot-body").append(tag); // 맨 뒤에 추가
        }
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight); // 스크롤을 맨 아래로 이동
    }

    // WebSocket에 연결하고 초기 인삿말 전송
    function connect() {
        client = Stomp.over(new SockJS('/rara-bot'));
        client.connect({}, (frame) => {
            key = new Date().getTime(); // 고유 키 생성

            // 서버로부터 메시지 수신
            client.subscribe(`/topic/bot/${key}`, (answer) => {
                var msgObj = JSON.parse(answer.body);
                var now = new Date();
                var time = formatTime();
                var date = formatDate(now);
                var tag = `
                    <div class="message-wrapper">
                        <div class="message-date flex center date">${date}</div>
                        <div class="message-bubble">
                            <div class="message-icon">
                                <img src="/images/common/chatbot-icon.png" alt="챗봇 아이콘">
                            </div>
                            <div class="message-content">
                                <p>${msgObj.answer}</p> <!-- 서버 응답의 answer 필드 사용 -->
                                <span class="message-time">${time}</span>
                            </div>
                        </div>
                    </div>
                `;
                showMessage(tag); // 메시지 표시
            });

            // 초기 인삿말 전송
            client.send("/bot/hello", {}, JSON.stringify({ key: key })); // key만 전송
        });
    }

    // WebSocket 연결 종료
    function disconnect() {
        client.disconnect(() => {
            console.log("Disconnected..."); // 종료 메시지 출력
        });
    }

    // 종료 버튼 클릭 이벤트
    function btnCloseClicked() {
        $("#chatbotWindow").hide(); // 챗봇 창 숨김
        $("#chatbot-body").html(""); // 대화 내용 리셋
        disconnect(); // WebSocket 종료
        flag = false; // 상태 플래그 초기화
    }

    // 전송 버튼 클릭 이벤트
    $(".btn-send").click(function() {
        var inputVal = $("#input").val(); // 입력값 가져오기
        if (inputVal.trim() !== "") {
            displayUserMessage(inputVal); // 사용자 메시지 표시
            sendMessageToServer(inputVal); // 서버로 메시지 전송
            $("#input").val(""); // 입력값 초기화
        }
    });

    // 챗봇 시작 버튼 클릭 이벤트
    function btnBotClicked() {
        if (flag) return; // 이미 열려있으면 반환

        $("#chatbotWindow").show(); // 챗봇 창 표시
        connect(); // WebSocket 연결
        flag = true; // 상태 플래그 설정
    }

    // 사용자 메시지를 표시하는 함수
    function displayUserMessage(message) {
        var time = formatTime();
        var userMessage = `
            <div class="message-wrapper user">
                <div class="message-bubble user">
                    <div class="message-content">
                        <p>${message}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;
        $(".chatbot-body").append(userMessage); // 사용자 메시지 추가
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight); // 스크롤을 맨 아래로 이동
    }

    // 서버로 메시지를 전송하는 함수
    function sendMessageToServer(message) {
        var data = {
            key: key,
            content: message, // 사용자 입력 메시지
            name: "guest" // 필요한 경우 사용자 이름 추가
        };
        client.send("/bot/question", {}, JSON.stringify(data)); // 질문 전송
    }

    // 전역으로 함수 노출
    window.btnBotClicked = btnBotClicked;
    window.btnCloseClicked = btnCloseClicked;
});
