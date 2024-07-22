var stompClient = null;

function connect() {
    var socket = new SockJS('/rara-bot');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);

        // 메시지 구독
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            var message = messageOutput.body;

            if (message.includes(", ")) {
                if (message.split(":").length === 1) {
                    // 카테고리 목록일 때
                    showCategoryButtons(message);
                } else {
                    // 질문 목록일 때
                    showQuestionButtons(message);
                }
            } else {
                // 답변일 때
                showMessageOutput(message);
            }
        });

        // 기본 카테고리 가져오기
        stompClient.send("/app/chat", {}, "GET_TOPICS");
    });
}


function btnBotClicked() {
    document.getElementById('chatbotWindow').style.display = 'block';
}

function btnCloseClicked() {
    document.getElementById('chatbotWindow').style.display = 'none';
}

function sendMessage() {
    var inputElem = document.getElementById('input');
    var message = inputElem.value.trim();
    if (message) {
        stompClient.send("/app/chat", {}, message);
        inputElem.value = ''; // 입력 필드 초기화
    }
}

function showCategoryButtons(message) {
    var chatbotBody = document.getElementById('chatbot-body');
    chatbotBody.innerHTML = '';

    var categories = message.split(', ');
    categories.forEach(function(category) {
        var button = document.createElement('button');
        button.textContent = category;
        button.onclick = function() {
            stompClient.send("/app/chat", {}, category);
        };
        chatbotBody.appendChild(button);
    });
}

function showQuestionButtons(message) {
    var chatbotBody = document.getElementById('chatbot-body');
    chatbotBody.innerHTML = '';

    // 메시지를 ':'로 분리하여 카테고리와 질문을 추출
    var messagesParts = message.split(":");
    if (messagesParts.length === 2) {
        var category = messagesParts[0].trim();
        var questions = messagesParts[1].split(', ');

        questions.forEach(function(question) {
            var button = document.createElement('button');
            button.textContent = question; // 카테고리 정보를 제외하고 질문만 표시
            button.onclick = function() {
                stompClient.send("/app/chat", {}, category + ": " + question); // 서버로 질문 전송
            };
            chatbotBody.appendChild(button);
        });
    } else {
        console.error('Unexpected message format for questions:', message);
    }
}



function showMessageOutput(message) {
    var chatbotBody = document.getElementById('chatbot-body');
    chatbotBody.innerHTML = ''; // 기존 내용을 지우고

    // 답변 메시지를 표시합니다.
    var messageElement = document.createElement('div');
    messageElement.textContent = message;
    chatbotBody.appendChild(messageElement);

    // 선택 사항: 스크롤을 아래로 이동시켜 최신 메시지를 볼 수 있게 합니다.
    chatbotBody.scrollTop = chatbotBody.scrollHeight;
}


connect(); // 페이지 로드 시 웹소켓 연결
