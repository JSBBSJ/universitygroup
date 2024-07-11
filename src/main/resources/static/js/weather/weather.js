var city = "Seoul";
var apiURI = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + "11c58e7e95de1afdb3f1a6cf14125c9d";

var iconMap = {
    "01d": "fas fa-sun clear-day",
    "01n": "fas fa-moon clear-night",
    "02d": "fas fa-cloud-sun cloudy",
    "02n": "fas fa-cloud-moon cloudy",
    "03d": "fas fa-cloud cloudy",
    "03n": "fas fa-cloud cloudy",
    "04d": "fas fa-cloud-meatball cloudy",
    "04n": "fas fa-cloud-meatball cloudy",
    "09d": "fas fa-cloud-showers-heavy rain",
    "09n": "fas fa-cloud-showers-heavy rain",
    "10d": "fas fa-cloud-sun-rain rain",
    "10n": "fas fa-cloud-moon-rain rain",
    "11d": "fas fa-poo-storm storm",
    "11n": "fas fa-poo-storm storm",
    "13d": "fas fa-snowflake snow",
    "13n": "fas fa-snowflake snow",
    "50d": "fas fa-smog mist",
    "50n": "fas fa-smog mist"
};

$.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    success: function(prop) {
        $('#city-name').text("도시 이름: " + prop.name);
        $('#temperature').text("현재 온도: " + (prop.main.temp - 273.15).toFixed(1) + "°C");
        $('#humidity').text("현재 습도: " + prop.main.humidity + "%");
        $('#weather').text("날씨: " + prop.weather[0].main);
        $('#weather-description').text("상세 날씨 설명: " + prop.weather[0].description);

        var iconClass = iconMap[prop.weather[0].icon];
        var weatherClass = iconClass.split(' ').pop();
        $('#weather-icon').attr('class', iconClass);

        // 배경 변경
        $('body').removeClass().addClass(weatherClass);

        $('#wind-speed').text("바람 속도: " + prop.wind.speed + " m/s");
        $('#country').text("나라: " + prop.sys.country);
        $('#cloudiness').text("구름: " + prop.clouds.all + "%");
    },
    error: function() {
        console.error("Weather data could not be retrieved.");
    }
});
