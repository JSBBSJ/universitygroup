$(document).ready(function() {
	var city = "Seoul";
	var apiURI = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + "11c58e7e95de1afdb3f1a6cf14125c9d";

	$.ajax({
		url: apiURI,
		dataType: "json",
		type: "GET",
		success: function(data) {
			$('#city-name').text("도시: " + city);
			$('#temperature').text("온도: " + (data.main.temp - 273.15).toFixed(1) + "°C");
			$('#humidity').text("현재 습도: " + data.main.humidity + "%");
			$('#weather').text("날씨: " + data.weather[0].main);
			$('#weather-description').text("상세 날씨 설명: " + data.weather[0].description);
			$('#wind-speed').text("바람 속도: " + data.wind.speed + "m/s");
			$('#cloudiness').text("구름: " + data.clouds.all + "%");

			var weatherMain = data.weather[0].main.toLowerCase();
			var weatherIconCode = data.weather[0].icon;
			var weatherSection = $(".weather");

			// 새로운 아이콘 경로 설정 및 매핑
			var iconPath = '/image';
			var iconExtension = '.png';
			var iconMap = {
				'01d': 'sunny-day',
				'01n': 'clear-night',
				'02d': 'partly-cloudy-day',
				'02n': 'partly-cloudy-night',
				'03d': 'cloudy',
				'03n': 'cloudy-night',
				'04d': 'overcast',
				'04n': 'overcast-night',
				'09d': 'shower-rain-day',
				'09n': 'shower-rain-night',
				'10d': 'rain-day',
				'10n': 'rain-night',
				'11d': 'thunderstorm-day',
				'11n': 'thunderstorm-night',
				'13d': 'snow-day',
				'13n': 'snow-night',
				'50d': 'mist-day',
				'50n': 'mist-night'
			};
			var iconName = iconMap[weatherIconCode];

			$('#weather-icon').css('background-image', 'url(' + iconPath + iconName + iconExtension + ')');

			switch (weatherMain) {
				case 'clear':
					weatherSection.addClass('clear');
					break;
				case 'clouds':
					weatherSection.addClass('clouds');
					break;
				case 'rain':
					weatherSection.addClass('rain');
					break;
				case 'snow':
					weatherSection.addClass('snow');
					break;
				case 'thunderstorm':
					weatherSection.addClass('thunderstorm');
					break;
				default:
					weatherSection.addClass('default');
			}
		}
	});
});
