let alertModal = document.getElementById('alert-modal');
let btnErrorClose = document.getElementById('btn-error-close');
let alertTitle = document.getElementById('alert-title');
let alertMessage = document.getElementById('alert-message');

btnErrorClose.addEventListener('click', function (){
    alertModal.style.display = "none";
    alertModal.className="alert alert-danger alert-dismissible fixed-top fade";
})

function showAlertModal(title, message){
    alertTitle.textContent = title;
    alertMessage.textContent = message;
    alertModal.style.display = "block";
    alertModal.style.paddingRight = "17px";
    alertModal.className="alert alert-danger alert-dismissible fixed-top fade show";
}