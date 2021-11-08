let editLocModal = document.getElementById('editRecordModal');
let editBtn = document.getElementById('btn-edit-borrower');
let btnClose = document.getElementById('close-record-edit-modal-window');
var selectedRow;
let emailEditInput = document.getElementById('edit-modal-email');
let fnameEditInput = document.getElementById('edit-modal-first-name');
let lnameEditInput = document.getElementById('edit-modal-last-name');
let borrowDateEditInput = document.getElementById('edit-modal-borrow-date');
let timePeriodEditInput = document.getElementById('edit-modal-time-period');
let returnedBookStatusEditInput = document.getElementById('edit-modal-returned-book-status');

editBtn.addEventListener('click', editRecord);

btnClose.addEventListener('click', (e) => {
    editLocModal.style.display = "none";
    editLocModal.className="modal fade";
});

function openEditModalWindow(th){
    editLocModal.style.display = "block";
    editLocModal.style.paddingRight = "17px";
    editLocModal.className="modal fade show";
    let parentElement = th.parentNode.parentElement;
    selectedRow = parentElement;
    let elementsByTagName = parentElement.getElementsByTagName('input');
    emailEditInput.value = elementsByTagName.namedItem('record_email').value;
    fnameEditInput.value = elementsByTagName.namedItem('record_firstName').value;
    lnameEditInput.value = elementsByTagName.namedItem('record_lastName').value;
    borrowDateEditInput.value = elementsByTagName.namedItem('record_borrowDate').value;
    timePeriodEditInput.value = elementsByTagName.namedItem('record_timePeriod').value;

}

function editRecord() {
    let inputs = selectedRow.getElementsByTagName('input');
    let returnDateTd = selectedRow.getElementsByTagName('td')[4];
    inputs.namedItem('record_returnStatus').value = returnedBookStatusEditInput.value;
    inputs.namedItem('record_returnDate').value = moment(new Date(Date.now())).format("YYYY-MM-DD");
    returnDateTd.textContent = inputs.namedItem('record_returnDate').value;
    editLocModal.style.display = "none";
    editLocModal.className="modal fade";
    editBookStatus(returnedBookStatusEditInput.value);
}