var locModal = document.getElementById('borrowModal');
var btnclose = document.getElementById('w-change-close-borrow-modal-window');
var btnShow= document.getElementById('showAddBorrowerModal');
var btnSave = document.getElementById('btn-save-borrower');
// btnSave.addEventListener('click',addNewAuthor);
var likeEmailsDiv = document.getElementById("dropdownEmailSelect");

let emailInput = document.getElementById("dropdownEmailSearch");
let fnameInput = document.getElementById("borrowerFirstName");
let lnameInput = document.getElementById("borrowerLastName");
let borrowDateInput = document.getElementById("borrowDate");
let commentInput = document.getElementById("comment");
let timePeriodInput = document.getElementById("selectTimePeriod");
let returnedBookStatusInput = document.getElementById("returnedBookStatus");
//show the modal
btnclose.addEventListener('click', (e) => {
    locModal.style.display = "none";
    locModal.className="modal fade";
});
//hide the modal

function addMode(){
    emailInput.readOnly = false; fnameInput.readOnly = false; lnameInput.readOnly = false;
    timePeriodInput.readOnly = false;
    commentInput.hidden = false;
    borrowDateInput.hidden = true;
    returnedBookStatusInput.hidden = true;
    locModal.style.display = "block";
    locModal.style.paddingRight = "17px";
    locModal.className="modal fade show";
    document.getElementById("borrowDateLabel").hidden = true;
    document.getElementById("returnedBookStatusLabel").hidden = true;
    document.getElementById("commentLabel").hidden = false;
    btnSave.removeEventListener('click', editBorrowerOnTable);
    btnSave.addEventListener('click', addNewRowToBorrowersTable);

}

function editMode(){
    emailInput.readOnly = true; fnameInput.readOnly = true; lnameInput.readOnly = true;
    timePeriodInput.readOnly = true;
    commentInput.hidden = true;
    borrowDateInput.hidden = false;
    returnedBookStatusInput.hidden = false;
    locModal.style.display = "block";
    locModal.style.paddingRight = "17px";
    locModal.className="modal fade show";
    document.getElementById("commentLabel").hidden = true;
    document.getElementById("borrowDateLabel").hidden = false;
    document.getElementById("returnedBookStatusLabel").hidden = false;
    btnSave.removeEventListener('click', addNewRowToBorrowersTable);
    btnSave.addEventListener('click', editBorrowerOnTable);
}


async function checkExistingEmail() {
    if (document.getElementById("dropdownEmailSearch").value.length >= 3) {
        const response = await fetch('/get/readers/by-email', {
            method: 'POST', // или 'PUT'
            // body: 'email:' + document.getElementById("dropdownEmailSearch").value, // данные могут быть 'строкой' или {объектом}!
            body: "email="+document.getElementById("dropdownEmailSearch").value,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        });
        const json = await response.json();
        a = json;
        document.getElementsByName("borrowerEmail").forEach(a => likeEmailsDiv.removeChild(a));
        json.forEach(el => {
            let a = document.createElement('a');
            a.setAttribute('class', 'dropdown-item');
            a.setAttribute('name', 'borrowerEmail');
            a.setAttribute('href', '#');
            a.onclick = function () {setReaderName(this);};
            a.textContent = el.email;
            likeEmailsDiv.appendChild(a);
        });

    }
}

function setReaderName(th) {
    alert("work");
    a.forEach(reader =>{
        if (reader.email === th.textContent){
            document.getElementById("borrowerFirstName").setAttribute('value', reader.firstName);
            document.getElementById("borrowerLastName").setAttribute('value', reader.lastName);
            document.getElementById("dropdownEmailSearch").value = th.textContent;
        }
    })
}


function addNewRowToBorrowersTable(){
    if (checkEmailValidation() === false) alert("invalid email");
    else if (checkFNameValidation() === false) alert("invalid first name");
    else if (checkLNameValidation() === false) alert("invalid last name");
    else if (timePeriodInput.value === '') alert("select a borrow time period");
    else{
        alert(timePeriodInput.value);
        let tbody = document.getElementById("borrowersTable").getElementsByTagName('tbody')[0];
        let newRow = document.createElement('tr');
        let newEmailCol = document.createElement('td');
        newEmailCol.textContent = emailInput.value;
        let newNameCol = document.createElement('td');
        let hrefName = document.createElement('a');
        hrefName.setAttribute('href', '#');
        hrefName.textContent = fnameInput.value + ' ' + lnameInput.value;
        newNameCol.appendChild(hrefName);
        let borrowDateCol = document.createElement('td');
        borrowDateCol.textContent = moment(new Date(Date.now())).format("YYYY-MM-DD");
        let dueDateCol = document.createElement('td');
        dueDateCol.textContent = getDueDate(timePeriodInput.value);
        newRow.append(newEmailCol, newNameCol, borrowDateCol, dueDateCol, document.createElement('td'));
        tbody.appendChild(newRow);
    }
}

function editBorrowerOnTable() {
    
}

function checkEmailValidation() {
    const re = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return  re.test(emailInput.value)
}

function checkFNameValidation() {
    const re = /^[a-zA-Z0-9.\-_$@*!]{2,20}$/;
    return  re.test(fnameInput.value)
}

function checkLNameValidation() {
    const re = /^[a-zA-Z0-9.\-_$@*!]{2,20}$/;
    return  re.test(lnameInput.value)
}

function getDueDate(number) {
    var date = new Date(Date.now());
    return  moment(date).add(number, 'month').format("YYYY-MM-DD");
}

