 function approvalOnoff() {
        var y = document.getElementById("y");
        // 현재 y 요소의 display 상태 확인
        var YDisplay = window.getComputedStyle(y).display;

        // 토글 기능 구현
        if (YDisplay === "none") {
            y.style.display = "block"; // 보이기
        } else {
            y.style.display = "none"; // 숨기기
        }
    }
    
    
 function receiverOnoff() {
        var f = document.getElementById("f");
        // 현재 y 요소의 display 상태 확인
        var FDisplay = window.getComputedStyle(f).display;

        // 토글 기능 구현
        if (FDisplay === "none") {
            f.style.display = "block"; // 보이기
        } else {
            f.style.display = "none"; // 숨기기
        }
    }