$(document).ready(function () {
	let flag = false;
	
    function formatTime() {
        var now = new Date();
        var ampm = (now.getHours() > 11) ? "오후" : "오전";
        var hour = now.getHours() % 12;
        if (hour == 0) hour = 12;
        var minute = now.getMinutes();
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

    function showMessage(tag) {
        $(".chatbot-body").append(tag);
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight); // 스크롤을 맨 아래로 이동
    }

    function toggleChatbot() {
        var chatbotWindow = $('#chatbotWindow');
        chatbotWindow.toggle();

        if (chatbotWindow.is(':visible') && ! flag) {
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

            showMessage(tag);
        }
    }

    window.toggleChatbot = toggleChatbot;
});
