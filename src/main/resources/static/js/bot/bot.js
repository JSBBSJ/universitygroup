$(document).ready(function() {
    let flag = false;

    var answers = {
        'chatbot': '챗봇 기능을 이용할 수 있습니다.',
        'contacts': '교내 연락처는 홈페이지에서 확인할 수 있습니다.',
        'menu': '학식 메뉴는 매일 업데이트되니 확인해주세요.',
        'scholarship': '장학금 관련 문의는 학생처에서 도와드립니다.'
    };

    // 버튼 클릭을 처리하는 이벤트 리스너를 추가합니다
    $('.btn-category').on('click', function() {
        var category = $(this).data('category');
        var question = $(this).text();
        var answer = answers[category];
        displayUserMessage(question);
        setTimeout(function() {
            displayAnswer(answer);
        }, 500); // 0.5초 후에 챗봇 답변을 표시
    });

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

    function toggleChatbot() {
        var chatbotWindow = $('#chatbotWindow');
        chatbotWindow.toggle();

        if (chatbotWindow.is(':visible') && !flag) {
            flag = true;
            var now = new Date();
            var date = formatDate(now);
            var time = formatTime();
            var msgObj = "안녕하세요! 무엇을 도와드릴까요?";

            var tag = `
                <div class="message-wrapper">
                    <div class="message-date flex center date">${date}</div>
                    <div class="message-bubble">
                        <div class="message-icon">
                            <img src="/images/common/chatbot-icon.png" alt="챗봇 아이콘">
                        </div>
                        <div class="message-content">
                            <p>${msgObj}</p>
                            <span class="message-time">${time}</span>
                        </div>
                    </div>
                </div>
            `;

            showMessage(tag, true); // 인삿말은 prepend
        }
    }

    $(".btn-send").click(function() {
        var inputVal = $("#input").val();
        if (inputVal.trim() !== "") {
            displayUserMessage(inputVal);
            $("#input").val(""); // 입력값 초기화
        }
    });

    // 사용자 메시지를 표시하는 함수입니다
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
    
    // 질문에 대한 답변을 표시하는 함수입니다
    function displayAnswer(answer) {
        var time = formatTime();

        var botMessage = `
            <div class="message-wrapper">
                <div class="message-bubble">
                    <div class="message-icon">
                        <img src="/images/common/chatbot-icon.png" alt="챗봇 아이콘">
                    </div>
                    <div class="message-content">
                        <p>${answer}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;

        showMessage(botMessage); // 챗봇 답변은 append
    }

    window.toggleChatbot = toggleChatbot;
});
