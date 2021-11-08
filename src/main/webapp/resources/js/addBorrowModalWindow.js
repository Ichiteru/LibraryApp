var locModal = document.getElementById('borrowModal');
var btnclose = document.getElementById('w-change-close-borrow-modal-window');
var btnShow= document.getElementById('showAddBorrowerModal');
var btnSave = document.getElementById('btn-save-borrower');
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

btnShow.addEventListener('click', (e) => {
    locModal.style.display = "block";
    locModal.style.paddingRight = "17px";
    locModal.className="modal fade show";
});

btnSave.addEventListener('click', addNewRowToBorrowersTable);


async function checkExistingEmail() {
    if (document.getElementById("dropdownEmailSearch").value.length >= 3) {
        // alert('check')
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
    // alert("work");
    a.forEach(reader =>{
        if (reader.email === th.textContent){
            fnameInput.setAttribute('value', reader.firstName);
            lnameInput.setAttribute('value', reader.lastName);
            emailInput.value = th.textContent;
        }
    })
}


function addNewRowToBorrowersTable(){
    if (checkEmailValidation() === false) alert("invalid email");
    else if (checkFNameValidation() === false) alert("invalid first name");
    else if (checkLNameValidation() === false) alert("invalid last name");
    else if (timePeriodInput.value === '') alert("select a borrow time period");
    else if (isRecordLimit() === true) alert("There are not available copies of this book in the library");
    else{
        // alert(timePeriodInput.value);
        let tbody = document.getElementById("borrowersTable").getElementsByTagName('tbody')[0];
        let newRow = document.createElement('tr');
        let newEmailCol = document.createElement('td');
        newEmailCol.textContent = emailInput.value;
        newEmailCol.append(getInput('hidden', 'false', 'exist_prev')
            , getInput('hidden', emailInput.value, 'record_email'));

        let newNameCol = document.createElement('td');
        newNameCol.append(getInput('hidden', fnameInput.value, 'record_firstName')
            , getInput('hidden', lnameInput.value, 'record_lastName'));
        let hrefName = document.createElement('a');
        hrefName.setAttribute('href', '#');
        hrefName.textContent = fnameInput.value + ' ' + lnameInput.value;
        hrefName.onclick = function () {openEditModalWindow(this);};
        newNameCol.appendChild(hrefName);

        let borrowDateCol = document.createElement('td');
        borrowDateCol.textContent = moment(new Date(Date.now())).format("YYYY-MM-DD");
        borrowDateCol.appendChild(getInput('hidden', borrowDateCol.textContent, 'record_borrowDate'));
        let dueDateCol = document.createElement('td');
        dueDateCol.textContent = getDueDate(timePeriodInput.value);
        dueDateCol.appendChild(getInput('hidden', dueDateCol.textContent, 'record_dueDate'));
        let returnDateCol = document.createElement('td');
        returnDateCol.textContent = '';
        returnDateCol.appendChild(getInput('hidden', '', 'record_returnDate'));
        let hiddenComment = getInput('hidden', commentInput.value, 'record_comment');
        let hiddenReturnedBookStatus = getInput('hidden', '', 'record_returnStatus');
        newRow.append(newEmailCol, newNameCol, borrowDateCol, dueDateCol, returnDateCol, hiddenComment, hiddenReturnedBookStatus);
        tbody.appendChild(newRow);
        setBookStatus();
    }
}

function getInput(type, value, name) {
    let input = document.createElement('input');
    input.setAttribute('type', type);
    input.setAttribute('value', value);
    input.setAttribute('name', name);
    return input;
}

function setInputs(th) {
    let tr = th.parentElement.parentElement;
    let columns = tr.getElementsByTagName('td');
    alert(columns[0].textContent);
    emailInput.value = columns[0].textContent;
    let flname = columns[1].getElementsByTagName('a')[0].textContent.split(' ');
    alert(flname);
    fnameInput.value = flname[1];
    lnameInput.value = flname[2];
    borrowDateInput.value = columns[2].textContent;
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


