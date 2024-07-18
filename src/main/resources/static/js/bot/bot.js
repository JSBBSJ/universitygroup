$(document).ready(function() {
    var stompClient;
    var isConnected = false;

    // AJAX를 사용하여 서버에서 데이터를 불러오는 함수
    function fetchDataFromDB() {
        $.ajax({
            url: '/chats', // 백엔드 엔드포인트 URL
            type: 'GET', // HTTP 요청 방식 설정
            success: function(data) {
                // 성공적으로 데이터를 불러왔을 때 수행할 작업
                // data 변수에 서버에서 받아온 데이터가 들어있음
                displayChatTitles(data); // 데이터를 표시하는 함수 호출
            },
            error: function() {
                // 데이터를 불러오는데 실패했을 때 수행할 작업
                console.log('Failed to fetch data from the server.');
            }
        });
    }

    // 서버에서 받아온 chat_title 데이터를 버튼으로 표시하는 함수
    function displayChatTitles(chats) {
        chats.forEach(function(chat) {
            var button = $('<button>')
                .text(chat.chatTitle)
                .addClass('chat-title-btn')
                .on('click', function() {
                    displayChatContent(chat.chatContent);
                });
            $("#chatbot-body").append(button);
        });
    }

    // chat_content를 표시하는 함수
    function displayChatContent(content) {
        var time = new Date().toLocaleTimeString();
        var chatMessage = `
            <div class="message-wrapper bot">
                <div class="message-bubble bot">
                    <div class="message-content">
                        <p>${content}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;
        $(".chatbot-body").append(chatMessage);
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight);
    }

    // 페이지 로드 시 DB에서 데이터 불러오기
    fetchDataFromDB();

    function isWebSocketSupported() {
        return 'WebSocket' in window;
    }

    function connectToWebSocket() {
        var socket = new SockJS('/rara-bot');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            isConnected = true;
            console.log('Connected to WebSocket');

            // 현재 날짜 및 시간 구하기
            var time = new Date().toLocaleTimeString();
            var message = "안녕하세요, 무엇을 도와드릴까요?";

            var botMessage = `
                <div class="message-wrapper bot">
                    <div class="message-bubble bot">
                        <div class="message-content">
                            <p>${message}</p>
                            <span class="message-time">${time}</span>
                        </div>
                    </div>
                </div>
            `;
            $(".chatbot-body").append(botMessage);
            $("#chatbotWindow").css("display", "block");
        });
    }

    function displayBotMessage(message) {
        var time = new Date().toLocaleTimeString();
        var botMessage = `
            <div class="message-wrapper bot">
                <div class="message-bubble bot">
                    <div class="message-content">
                        <p>${message}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;
        $(".chatbot-body").append(botMessage);
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight);
    }

    function disconnectWebSocket() {
        stompClient.disconnect(function() {
            isConnected = false;
            console.log('Disconnected from WebSocket');
        });
    }

    function toggleWebSocketConnection() {
        if (isConnected) {
            disconnectWebSocket();
        } else {
            connectToWebSocket();
        }
    }

    window.btnBotClicked = function() {
        toggleWebSocketConnection();
    };

    window.btnCloseClicked = function() {
        $("#chatbotWindow").css("display", "none");
        if (isConnected) {
            disconnectWebSocket();
        }
    };

    window.sendMessage = function() {
        var message = $("#input").val();
        if (message.trim() === "") {
            return;
        }
        var time = new Date().toLocaleTimeString();
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
        $(".chatbot-body").append(userMessage);
        $(".chatbot-body").scrollTop($(".chatbot-body")[0].scrollHeight);

        // WebSocket을 통해 서버로 메시지 전송
        if (isConnected) {
            stompClient.send("/app/sendMessage", {}, JSON.stringify({ content: message }));
        }
        $("#input").val("");
    };

    // Chatbot icon 클릭 이벤트 핸들러 연결
    $(".chatbot-icon").on('click', btnBotClicked);

    // Send 버튼 클릭 이벤트 핸들러 연결
    $(".btn-send").on('click', sendMessage);

    // Enter 키를 눌렀을 때 메시지 전송
    $("#input").on('keypress', function(e) {
        if (e.which == 13) {
            sendMessage();
        }
    });

    // chatbotWindow 초기에 숨기기
    $("#chatbotWindow").addClass("hidden");
});
