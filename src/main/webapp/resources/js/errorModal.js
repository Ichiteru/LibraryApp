let alertModal = document.getElementById('alert-modal');
let btnErrorClose = document.getElementById('btn-error-close');

btnErrorClose.addEventListener('click', function (){
    alertModal.style.display = "none";
    alertModal.className="alert alert-danger alert-dismissible fixed-top fade";
})

function showAlertModal(title, message){
    document.getElementById('alert-title').textContent = title;
    document.getElementById('alert-message').textContent = message;
    alertModal.style.display = "block";
    alertModal.style.paddingRight = "17px";
    alertModal.className="alert alert-danger alert-dismissible fixed-top fade show";
}