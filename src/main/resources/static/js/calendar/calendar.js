/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var monthYearEl = document.getElementById('month-year');
    var prevMonthBtn = document.getElementById('prev-month');
    var nextMonthBtn = document.getElementById('next-month');
    var currentDate = new Date();
    var currentMonth = currentDate.getMonth();
    var currentYear = currentDate.getFullYear();

    function updateCalendar(month, year) {
        calendarEl.innerHTML = ''; 
        monthYearEl.innerText = new Date(year, month).toLocaleDateString('en-US', { month: 'long', year: 'numeric' });
        
        var firstDay = new Date(year, month, 1).getDay();
        var daysInMonth = new Date(year, month + 1, 0).getDate();
        
        // Fill initial empty cells
        for (var i = 0; i < firstDay; i++) {
            var emptyCell = document.createElement('div');
            emptyCell.classList.add('day');
            calendarEl.appendChild(emptyCell);
        }
        
        // Fill days of the month
        for (var day = 1; day <= daysInMonth; day++) {
            var dayCell = document.createElement('div');
            dayCell.classList.add('day');
            dayCell.innerText = day;
            calendarEl.appendChild(dayCell);
        }
    }

    prevMonthBtn.addEventListener('click', function() {
        if (currentMonth === 0) {
            currentMonth = 11;
            currentYear--;
        } else {
            currentMonth--;
        }
        updateCalendar(currentMonth, currentYear);
    });

    nextMonthBtn.addEventListener('click', function() {
        if (currentMonth === 11) {
            currentMonth = 0;
            currentYear++;
        } else {
            currentMonth++;
        }
        updateCalendar(currentMonth, currentYear);
    });

    updateCalendar(currentMonth, currentYear); 
});
