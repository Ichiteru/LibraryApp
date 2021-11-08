var form = document.getElementById("changeBookInfo");

function saveBookChanges() {
    getRecordsData();
}

function getRecordsData(){
    let existingRecords = [];
    let newRecords = [];
    let existPrev = form.querySelectorAll('input[name="exist_prev"]');
    let email = form.querySelectorAll('input[name="record_email"]');
    let firstName = form.querySelectorAll('input[name="record_firstName"]');
    let lastName = form.querySelectorAll('input[name="record_lastName"]');
    let borrowDate = form.querySelectorAll('input[name="record_borrowDate"]');
    let dueDate = form.querySelectorAll('input[name="record_dueDate"]');
    let returnDate = form.querySelectorAll('input[name="record_returnDate"]');
    let comment = form.querySelectorAll('input[name="record_comment"]');
    let returnStatus = form.querySelectorAll('input[name="record_returnStatus"]');
    for (let i = 0; i < existPrev.length; i++) {
        // alert("cycle");
        let record = {
            email : email[i].value,
            firstName : firstName[i].value,
            lastName : lastName[i].value,
            borrowDate : borrowDate[i].value,
            dueDate : dueDate[i].value,
            returnDate : returnDate[i].value,
            comment : comment[i].value,
            returnStatus : returnStatus[i].value
        }
        if (existPrev[i].value === 'true'){
            alert(record.email);
            existingRecords.push(record);
        }
        else newRecords.push(record);
    }
    let e = document.createElement('input');
    e.setAttribute('type', 'hidden');
    e.setAttribute('name', 'existingRecords');
    e.setAttribute('value', JSON.stringify(existingRecords));
    let n = document.createElement('input');
    n.setAttribute('type', 'hidden');
    n.setAttribute('name', 'newRecords');
    n.setAttribute('value', JSON.stringify(newRecords));
    form.append(e, n);
}