var locModal = document.getElementById('locModal');
var btnclose = document.getElementById('w-change-close');
var btnShow= document.getElementById('w-change-location');


//show the modal
btnShow.addEventListener('click', (e) => {
    locModal.style.display = "block";
    locModal.style.paddingRight = "17px";
    locModal.className="modal fade show";
});
//hide the modal
btnclose.addEventListener('click', (e) => {
    locModal.style.display = "none";
    locModal.className="modal fade";
});

