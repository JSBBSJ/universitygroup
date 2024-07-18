$(document).ready(function() {
    var client;
    var key;
    let flag = false;

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

    function formatTime() {
        var now = new Date();
        var ampm = (now.getHours() > 11) ? "오후" : "오전";
        var hour = now.getHours() % 12 || 12; // 0시는 12시로 표기
        var minute = String(now.getMinutes()).padStart(2, '0'); // 분이 한 자리일 경우 앞에 0 추가
        return `${ampm} ${hour}:${minute}`;
    }

    function formatDate(now) {
        const year = now.getFullYear();
        const month = now.getMonth() + 1;
        const date = now.getDate();
        const dayOfWeek = now.getDay();
        const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
        return `${year}년 ${month}월 ${date}일 ${days[dayOfWeek]}`;
    }

    function showMessage(tag, prepend = false) {
        if (prepend) {
            $(".chatbot-body").prepend(tag);
        } else {
            $(".chatbot-body").append(tag);
        }
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight); // 스크롤을 맨 아래로 이동
    }

    //웹소켓 연결후 인삿말 출력
    //여기의 subscribe와 botcontroller 의 convertAndSend와 위치가 일치해야함
    function connect() {
        client = Stomp.over(new SockJS('/rara-bot'));
        client.connect({}, (frame) => {
            key = new Date().getTime();
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
                                <p>${msgObj.content}</p>
                                <span class="message-time">${time}</span>
                            </div>
                        </div>
                    </div>
                `;
                showMessage(tag);
            });
            var data = {
                key: key,
                content: "hello",
                name: "guest"
            };
            client.send("/bot/hello", {}, JSON.stringify(data));
        });
    }

    //소켓 종료
    function disconnect() {
        client.disconnect(() => {
            console.log("Disconnected...");
        });
    }

    //종료(x) 클릭시 이벤트
    function btnCloseClicked() {
        $("#chatbotWindow").hide();
        //대화창 리셋
        $("#chatbot-body").html("");
        disconnect();
        flag = false;
    }

    $(".btn-send").click(function() {
        var inputVal = $("#input").val();
        if (inputVal.trim() !== "") {
            displayUserMessage(inputVal);
            $("#input").val(""); // 입력값 초기화
        }
    });

    //챗봇 시작 버튼 이벤트
    function btnBotClicked() {
        if (flag) return;

        //1. 소켓 접속
        $("#chatbotWindow").show();
        connect();
        flag = true;
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

        // 사용자 메시지를 오른쪽 정렬로 챗봇 창의 가장 아래에 추가
        $(".chatbot-body").append(userMessage);
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight); // 스크롤을 맨 아래로 이동
    }

    // 전역으로 함수 노출
    window.btnBotClicked = btnBotClicked;
    window.btnCloseClicked = btnCloseClicked;
});
