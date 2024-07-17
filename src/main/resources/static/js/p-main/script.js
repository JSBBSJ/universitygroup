document.addEventListener("DOMContentLoaded", function() {
    // 현재 날짜 객체 생성
    var currentDate = new Date();

    // 월 이름 배열
    var monthNames = [
        "1월", "2월", "3월", "4월", "5월", "6월",
        "7월", "8월", "9월", "10월", "11월", "12월"
    ];

    // 현재 월과 년도 가져오기
    var currentMonth = currentDate.getMonth();
    var currentYear = currentDate.getFullYear();

    // 현재 달력의 시작 요일을 구하기 위해 현재 월의 첫째 날을 구함
    var firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    var startingDay = firstDayOfMonth.getDay(); // 0 (일요일) ~ 6 (토요일)

    // 월과 연도를 표시
    document.getElementById("currentMonth").textContent = monthNames[currentMonth] + " " + currentYear;

    var daysContainer = document.querySelector(".days");

    // 이전 월의 날짜가 표시될 부분을 비우기 위해 초기화
    daysContainer.innerHTML = "";

    // 이전 월의 빈 칸 생성
    for (var i = 0; i < startingDay; i++) {
        var emptyDay = document.createElement("div");
        emptyDay.classList.add("day", "empty");
        daysContainer.appendChild(emptyDay);
    }

    // 현재 월의 마지막 날짜 가져오기
    var lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0);
    var daysInMonth = lastDayOfMonth.getDate();

    // 현재 월의 날짜 생성
    for (var date = 1; date <= daysInMonth; date++) {
        var day = document.createElement("div");
        day.textContent = date;
        day.classList.add("day");

        // 현재 날짜에 해당하는 경우 .today 클래스 추가
        if (date === currentDate.getDate() && currentMonth === currentDate.getMonth() && currentYear === currentDate.getFullYear()) {
            day.classList.add("today");
        }

        daysContainer.appendChild(day);
    }

    // 남은 빈 칸을 채우기 위해 빈 div 추가
    var remainingDays = 7 - (daysContainer.children.length % 7);
    for (var i = 0; i < remainingDays; i++) {
        var emptyDay = document.createElement("div");
        emptyDay.classList.add("day", "empty");
        daysContainer.appendChild(emptyDay);
    }

    // 다음 달로 이동하는 버튼에 클릭 이벤트 추가
    var nextMonthButton = document.getElementById("nextMonthButton");
    nextMonthButton.addEventListener("click", function() {
        // 현재 월을 다음 달로 설정
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }

        // 달력 다시 그리기
        redrawCalendar(currentMonth, currentYear);
    });

    // 이전 달로 이동하는 버튼에 클릭 이벤트 추가
    var prevMonthButton = document.getElementById("prevMonthButton");
    prevMonthButton.addEventListener("click", function() {
        // 현재 월을 이전 달로 설정
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }

        // 달력 다시 그리기
        redrawCalendar(currentMonth, currentYear);
    });

    // 함수로 달력을 다시 그리는 부분 분리
    function redrawCalendar(month, year) {
        // 월과 연도를 업데이트하여 표시
        document.getElementById("currentMonth").textContent = monthNames[month] + " " + year;

        // 달력을 초기화
        daysContainer.innerHTML = "";

        // 새로운 달의 첫째 날의 요일을 구함
        var firstDayOfMonth = new Date(year, month, 1);
        var startingDay = firstDayOfMonth.getDay();

        // 이전 달의 빈 칸 생성
        for (var i = 0; i < startingDay; i++) {
            var emptyDay = document.createElement("div");
            emptyDay.classList.add("day", "empty");
            daysContainer.appendChild(emptyDay);
        }

        // 새로운 달의 마지막 날짜를 가져옴
        var lastDayOfMonth = new Date(year, month + 1, 0);
        var daysInMonth = lastDayOfMonth.getDate();

        // 새로운 달의 날짜 생성
        for (var date = 1; date <= daysInMonth; date++) {
            var day = document.createElement("div");
            day.textContent = date;
            day.classList.add("day");

            // 현재 날짜에 해당하는 경우 .today 클래스 추가
            if (date === currentDate.getDate() && month === currentDate.getMonth() && year === currentDate.getFullYear()) {
                day.classList.add("today");
            }

            daysContainer.appendChild(day);
        }

        // 남은 빈 칸을 채우기 위해 빈 div 추가
        var remainingDays = 7 - (daysContainer.children.length % 7);
        for (var i = 0; i < remainingDays; i++) {
            var emptyDay = document.createElement("div");
            emptyDay.classList.add("day", "empty");
            daysContainer.appendChild(emptyDay);
        }
    }
});
