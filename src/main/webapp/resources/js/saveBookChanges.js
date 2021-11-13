var form = document.getElementById("changeBookInfo");

function saveBookChanges() {
    let genres = document.getElementsByName('bookGenre');
    let authors = document.getElementsByName('authorName');

    if (genres.length == 0 || authors.length == 0){
        alert("Book must have one more genre and author");
        return false;
    }
    else if(isEmpty(document.getElementsByName('title')[0].value)){
        alert("Title can't be empty.");
        return false;
    }
    else if(isEmpty(document.getElementsByName('publisher')[0].value)){
        alert("Publisher can't be empty.");
        return false;
    }
    else if(!isDateValid( document.getElementsByName('publishDate')[0].value)){
        alert("Publish date can't be empty or invalid.");
        return false;
    }
    else if(!isNumberValid(document.getElementsByName('pageCount')[0].value, 0, 1500)){
        alert('Page count should be in 0-1500 range and not empty');
        return false;
    }
    else if(!isValidAndNotEmpty(/^(?=(?:\D*\d){10}(?:(?:\D*\d){3})?$)[\d-]+$/, document.getElementsByName('isbn')[0].value)){
        showAlertModal('Validation error!', 'ISBN should contains 10 or 13 numbers.');
        return false;
    }
    else if(!isNumberValid(document.getElementsByName('totalAmount')[0].value, 0, 1500)){
        alert('Total amount should be in 0-1500 range and not empty');
        return false;
    }
    else {
        getRecordsData();
        return true;
    }
}

function isEmpty(value) {
    return  value === '';
}

function isValidAndNotEmpty(expr, value){
    return expr.test(value) && value != null && value !='';
}

function isDateValid(value){
    return value != null && value !='';
}

function isNumberValid(value, min, max){
    return value >= min && value <=max && value != '' && value != null;
}

function getRecordsData(){
    let existingRecords = [];
    let newRecords = [];
    let id = form.querySelectorAll('input[name="record_id"]');
    let email = form.querySelectorAll('input[name="record_email"]');
    let firstName = form.querySelectorAll('input[name="record_firstName"]');
    let lastName = form.querySelectorAll('input[name="record_lastName"]');
    let borrowDate = form.querySelectorAll('input[name="record_borrowDate"]');
    let dueDate = form.querySelectorAll('input[name="record_dueDate"]');
    let returnDate = form.querySelectorAll('input[name="record_returnDate"]');
    let comment = form.querySelectorAll('input[name="record_comment"]');
    let returnStatus = form.querySelectorAll('input[name="record_returnStatus"]');
    let timePeriod = form.querySelectorAll('input[name="record_timePeriod"]');
    for (let i = 0; i < id.length; i++) {
        let record = {
            id : id[i].value,
            email : email[i].value,
            fName : firstName[i].value,
            lName : lastName[i].value,
            borrowDate : borrowDate[i].value,
            dueDate : dueDate[i].value,
            returnDate : returnDate[i].value,
            comment : comment[i].value,
            returnStatus : returnStatus[i].value,
            timePeriod : timePeriod[i].value
        }
        if (id[i].value === '0'){
            newRecords.push(record);
        }
        else existingRecords.push(record);
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

function isTotalAmountMoreThanRented(th){
    if(th.value < rentedBooksHidden.value){
        th.value = rentedBooksHidden.value;
        alert("Books total amount can't be less than rented books amount.")
    }
}