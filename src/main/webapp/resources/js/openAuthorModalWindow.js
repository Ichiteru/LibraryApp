var locModalAuthor = document.getElementById('locModalAuthor');
var btnCloseAuthor = document.getElementById('w-change-close');
var btnShowAuthor = document.getElementById('w-change-location');


//show the modal
btnShowAuthor.addEventListener('click', (e) => {
    locModalAuthor.style.display = "block";
    locModalAuthor.style.paddingRight = "17px";
    locModalAuthor.className="modal fade show";
});
//hide the modal
btnCloseAuthor.addEventListener('click', (e) => {
    locModalAuthor.style.display = "none";
    locModalAuthor.className="modal fade";
});

