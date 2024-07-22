$(document).ready(function() {
	let stompClient;
	let isBotClicked = false;
	let isConnected = false;

	const fetchDataFromDB = () => {
		$.ajax({
			url: '/chats',
			type: 'GET',
			success: function(data) {
				displayCategories(data);
			},
			error: function() {
				console.log('Failed to fetch data from the server.');
			}
		});
	}

	const displayCategories = (chats) => {
		const categories = chats.reduce((acc, chat) => {
			acc[chat.category] = acc[chat.category] || [];
			acc[chat.category].push(chat);
			return acc;
		}, {});

		for (const category in categories) {
			const button = $('<button>')
				.text(category)
				.addClass('category-button')
				.on('click', () => displayChatTitles(categories[category]));

			$("#chatbot-body").append(button);
		}
	}

	const displayChatTitles = (chats) => {
		$("#chatbot-body button").remove();

		chats.forEach(chat => {
			const button = $('<button>')
				.text(chat.chatTitle)
				.addClass('chat-title-btn')
				.on('click', () => displayChatContent(chat.chatContent));

			$("#chatbot-body").append(button);
		});
	}

	const displayChatContent = (content) => {
		const time = new Date().toLocaleTimeString();
		const chatMessage = `
            <div class="message-wrapper bot">
                <div class="message-bubble bot">
                    <div class="message-content">
                        <p>${content}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;
		$(".chatbot-body").append(chatMessage).scrollTop($(".chatbot-body")[0].scrollHeight);
	}

	const connectToWebSocket = () => {
		const socket = new SockJS('/rara-bot');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, frame => {
			isConnected = true;
			console.log('Connected to WebSocket');

			const time = new Date().toLocaleTimeString();
			const message = "안녕하세요, 무엇을 도와드릴까요?";

			const botMessage = `
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
			$("#chatbotWindow").css("display", "flex");
		});
	}

	const btnBotClicked = () => {
		if (!isBotClicked) {
			isBotClicked = true;
			$("#chatbot-body").empty(); // 챗봇 창 내용을 초기화
			fetchDataFromDB(); // 데이터를 다시 가져와 버튼을 추가
			connectToWebSocket();
		}
	}

	const btnCloseClicked = () => {
		isBotClicked = false;
		$("#chatbotWindow").css("display", "none");
		disconnectWebSocket();
	}

	$(".chatbot-icon").on('click', btnBotClicked);
	$(".close-btn").on('click', btnCloseClicked);

	$(".chatbot-icon").click(function() {
		if (!isBotClicked) {
			openChatbot();
			isBotClicked = true;
		} else {
			isBotClicked = false;
			closeChatbot();
		}
	});

	window.sendMessage = () => {
		const message = $("#input").val().trim();
		if (message === "") return;

		const time = new Date().toLocaleTimeString();
		const userMessage = `
            <div class="message-wrapper user">
                <div class="message-bubble user">
                    <div class="message-content">
                        <p>${message}</p>
                        <span class="message-time">${time}</span>
                    </div>
                </div>
            </div>
        `;
		$(".chatbot-body").append(userMessage).scrollTop($(".chatbot-body")[0].scrollHeight);

		if (isConnected) {
			stompClient.send("/app/sendMessage", {}, JSON.stringify({ content: message }));
		}
		$("#input").val("");
	}

	$(".btn-send").on('click', window.sendMessage);

	$("#input").on('keypress', function(e) {
		if (e.which === 13) window.sendMessage();
	});

	$("#chatbotWindow").addClass("hidden");

	fetchDataFromDB();
});
