DelayRedirect();
function DelayRedirect() {
    let seconds = 5;
    let timecount = document.getElementById("timecount");
    timecount.innerHTML = seconds;
    setInterval(function () {
        seconds--;
        if(seconds > 0){
            timecount.innerHTML = seconds;
        }
        if (seconds == 0) {
            timecount.innerHTML = 0;
            window.location = "/home";
        }
    }, 1000);
}