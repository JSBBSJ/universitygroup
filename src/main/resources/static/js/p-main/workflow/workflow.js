//결제 작성하기 눌렀을때 페이지 이동하는 자바 스크립트

function ToWritingPage() {

    window.location.href = 'work-list'; //html 정해지면 넣기
}

//span 태그 눌렀을때 해당 id들이 들어와서 상태에 따라 보이고 숨기는 스크립트


function approvalOnoff(id) { //코드가 중복되어 id로 가져와서 뿌리기

       var key = document.getElementById(id);

       // 현재 key 요소의 display 상태 확인

       var display = window.getComputedStyle(key).display;

       // 토글 기능 구현

       if (display === "none") {

           key.style.display = "block"; // 보이기

       } else {

           key.style.display = "none"; // 숨기기

       }

   }