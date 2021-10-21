var locModal = document.getElementById('borrowModal');
var btnclose = document.getElementById('w-change-close-borrow-modal-window');
var btnShow= document.getElementById('showAddBorrowerModal');
var btnSave = document.getElementById('btn-save-borrower');
// btnSave.addEventListener('click',addNewAuthor);

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

function f() {
    
}
