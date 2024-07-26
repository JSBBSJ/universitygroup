
var stompClient = null;
var key;
var isConnected = false;

function isWebSocketSupported() {
    return 'WebSocket' in window;
}

if (isWebSocketSupported()) {
    console.log("이 브라우저는 WebSocket을 지원합니다.");
} else {
    console.log("이 브라우저는 WebSocket을 지원하지 않습니다.");
}

function formatTime() {
    var now = new Date();
    var ampm = (now.getHours() > 11) ? "오후" : "오전";
    var hour = now.getHours() % 12;
    if (hour == 0) hour = 12;
    var minute = now.getMinutes();
    return `${ampm} ${hour}:${minute}`;
}

function showMessage(tag) {
    const chatbotBody = document.getElementById('chatbot-body');
    const messageElement = document.createElement('div');
    messageElement.innerHTML = tag;
    chatbotBody.appendChild(messageElement);
    chatbotBody.scrollTop = chatbotBody.scrollHeight;
}

function formatDate(now) {
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    const dayOfWeek = now.getDay();
    const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
    return `${year}년 ${month}월 ${date}일 ${days[dayOfWeek]}`;
}

function resetChat() {
    $("#chatbot-body").html('<div id="category-buttons" class="button-grid"></div>');
    loadCategories();
}

function connect() {
    if (isConnected) return;
    const socket = new SockJS('/rara-bot');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        isConnected = true;
        key = new Date().getTime();
        console.log('Connected: ' + frame);

        resetChat();

        stompClient.subscribe(`/topic/bot/${key}`, (answer) => {
            var msgObj = JSON.parse(answer.body);
            console.log("Received message:", msgObj);

            var now = new Date();
            var time = formatTime();
            var date = formatDate(now);
            var tag = `
                <div class="flex center date">
                    ${date}
                </div>
                <div class="message bot flex">
                    <div class="icon">
                        <img src="/images/common/chatbot-icon.png">
                    </div>
                    <div class="message-content">
                        <p>${msgObj.content}</p>
                    </div>
                    <div class="time">${time}</div>
                </div>
            `;
            showMessage(tag);
        });

        var data = {
            key: key,
            content: "hello",
            name: document.getElementById('username').innerText
        };

        stompClient.send("/bot/hello", {}, JSON.stringify(data));
        console.log("Sent message:", data);

        stompClient.subscribe('/topic/category', function(message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/question', function(message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/children', function(message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/create', function(message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        isConnected = false;
    }
    console.log("Disconnected");
    resetChat();
}

function loadCategories() {
    fetch('/api/questions/categories')
        .then(response => response.json())
        .then(categories => {
            const categoryButtons = document.getElementById('category-buttons');
            if (categoryButtons) {
                categoryButtons.innerHTML = '';
                categories.forEach(category => {
                    const categoryDiv = document.createElement('div');
                    categoryDiv.className = 'category';
                    const button = document.createElement('button');
                    button.textContent = category;
                    button.className = 'category-button';
                    button.onclick = () => {
                        toggleSubQuestions(`sub-questions-${category}`);
                        loadSubQuestions(category, `sub-questions-${category}`);
                    };
                    categoryDiv.appendChild(button);
                    const subQuestionsDiv = document.createElement('div');
                    subQuestionsDiv.id = `sub-questions-${category}`;
                    subQuestionsDiv.className = 'sub-questions';
                    categoryDiv.appendChild(subQuestionsDiv);
                    categoryButtons.appendChild(categoryDiv);
                });
            }
        });
}

function loadSubQuestions(category, elementId) {
    fetch(`/api/questions/category/${category}/texts`)
        .then(response => response.json())
        .then(texts => {
            const subQuestionsDiv = document.getElementById(elementId);
            if (subQuestionsDiv) {
                subQuestionsDiv.innerHTML = '';
                texts.forEach(text => {
                    const button = document.createElement('button');
                    button.textContent = text;
                    button.className = 'category-button';
                    button.onclick = () => {
                        fetchAnswer(text);
                    };
                    subQuestionsDiv.appendChild(button);
                });
            }
        })
        .catch(error => console.error('Error fetching texts by category:', error));
}

function fetchAnswer(text) {
    const encodedText = encodeURIComponent(text);
    fetch(`/api/questions/question/${encodedText}/answer`)
        .then(response => response.text())
        .then(answer => {
            var now = new Date();
            var time = formatTime();
            var date = formatDate(now);
            var tag = `
                <div class="message bot flex">
                    <div class="message-content">
                        <p>${answer}</p>
                    </div>
                    <div class="time">${time}</div>
                </div>
            `;
            showMessage(tag);
        })
        .catch(error => console.error('Error fetching answer by text:', error));
}

function sendText(text) {
    stompClient.send("/app/question", {}, JSON.stringify({ 'text': text }));
}

function sendMessage() {
    const input = document.getElementById('input');
    const message = input.value;
    if (message) {
        stompClient.send("/app/question", {}, JSON.stringify({ 'text': message }));
        input.value = '';
    }
}

function btnBotClicked() {
    const chatbotWindow = document.getElementById('chatbotWindow');
    if (chatbotWindow.style.display === 'none' || chatbotWindow.style.display === '') {
        connect();
        chatbotWindow.style.display = 'flex';
    } else {
        disconnect();
        chatbotWindow.style.display = 'none';
    }
}

function btnCloseClicked() {
    const chatbotWindow = document.getElementById('chatbotWindow');
    disconnect();
    chatbotWindow.style.display = 'none';
}

function hideOtherContent(exceptionId) {
    const contentIds = ['text-buttons', 'answer'];
    contentIds.forEach(id => {
        if (id !== exceptionId) {
            const element = document.getElementById(id);
            if (element) {
                element.style.display = 'none';
            }
        }
    });
}

function toggleSubQuestions(id) {
    var subQuestions = document.getElementById(id);
    if (subQuestions.style.display === 'none' || subQuestions.style.display === '') {
        subQuestions.style.display = 'block';
    } else {
        subQuestions.style.display = 'none';
    }
}