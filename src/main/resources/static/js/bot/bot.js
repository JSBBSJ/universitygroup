let stompClient = null;
//소켓 연결
function connect() {
    const socket = new SockJS('/rara-bot');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        loadCategories();

        stompClient.subscribe('/topic/category', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/question', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/children', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/create', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

//소켓 연결 해제
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function loadCategories() {
    fetch('/api/questions/categories')
        .then(response => response.json())
        .then(categories => {
            const categoryButtons = document.getElementById('category-buttons');
            if (categoryButtons) {
				categoryButtons.innerHTML = ''; // 기존 버튼들을 초기화
                categories.forEach(category => {
                    const button = document.createElement('button');
                    button.textContent = category;
					button.className = 'category-button';
                    button.onclick = () => sendCategory(category);
                    categoryButtons.appendChild(button);
                });
            }
        });
}

function sendCategory(category) {
    fetch(`/api/questions/category/${category}/texts`)
        .then(response => response.json())
        .then(texts => {
            if (Array.isArray(texts)) {
                const chatbotBody = document.getElementById('chatbot-body');
                const textButtonsContainer = document.createElement('div');
                textButtonsContainer.id = 'text-buttons';

                texts.forEach(text => {
                    const button = document.createElement('button');
                    button.textContent = text;
					button.className = 'category-button';
                    button.onclick = () => fetchAnswer(text);
                    textButtonsContainer.appendChild(button);
                });

                const existingTextButtons = document.getElementById('text-buttons');
                if (existingTextButtons) {
                    chatbotBody.removeChild(existingTextButtons);
                }
                chatbotBody.appendChild(textButtonsContainer);
            } else {
                console.error('Expected an array but got:', texts);
            }
        })
        .catch(error => console.error('Error fetching texts by category:', error));
}

function fetchAnswer(text) {
    const encodedText = encodeURIComponent(text);
    fetch(`/api/questions/question/${encodedText}/answer`)
        .then(response => response.text())
        .then(answer => {
            showMessage(answer);
        })
        .catch(error => console.error('Error fetching answer by text:', error));
}


function sendText(text) {
    stompClient.send("/app/question", {}, JSON.stringify({'text': text}));
}

function sendMessage() {
    const input = document.getElementById('input');
    const message = input.value;
    if (message) {
        stompClient.send("/app/question", {}, JSON.stringify({'text': message}));
        input.value = '';
    }
}

function showMessage(message) {
    const chatbotBody = document.getElementById('chatbot-body');
    const messageElement = document.createElement('div');
    messageElement.className = 'message';
    messageElement.textContent = message;
    chatbotBody.appendChild(messageElement);
}

function btnBotClicked() {
    const chatbotWindow = document.getElementById('chatbotWindow');
	
	connect();
    chatbotWindow.style.display = chatbotWindow.style.display === 'none' ? 'flex' : 'none';
}

function btnCloseClicked() {
    const chatbotWindow = document.getElementById('chatbotWindow');
	disconnect();
    chatbotWindow.style.display = 'none';
	
}

