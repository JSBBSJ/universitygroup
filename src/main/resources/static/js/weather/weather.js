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
			$('#weather-icon').attr('src', 'http://openweathermap.org/img/w/' + data.weather[0].icon + '.png');
			$('#weather-description').text("상세 날씨 설명: " + data.weather[0].description);
			$('#wind-speed').text("바람 속도: " + data.wind.speed + "m/s");
			$('#cloudiness').text("구름: " + data.clouds.all + "%");
			
			
			var weatherMain = data.weather[0].main.toLowerCase();
			var weatherSection =$(".weather");
			
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
