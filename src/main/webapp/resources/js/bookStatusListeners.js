let body = document.getElementById('borrowersTable').getElementsByTagName('tbody')[0];
let statusDiv = document.getElementById('bookStatusDiv');
let rentedBooksHidden = document.getElementById('rentedBooks');
let totalAmountInput = document.getElementsByName('totalAmount')[0];
let bookAvailableStatus = document.getElementsByName('status')[0];
let rented_ = 0;

function setBookStatus() {
    // alert(statusDiv.innerHTML);
    let totalAmount = document.getElementsByName('totalAmount')[0].value;
    let rented = 0;
    let returnDates = document.getElementsByName('record_returnDate');
    returnDates.forEach(date => {
        if (date.value === ""){
            rented++;
        }
    })
    rented_ = rented;
    rentedBooksHidden.value = rented;
    // alert(rented === totalAmountInput.value);
    // alert(isRentedEqualsTotalAmount());
    if (rented < totalAmountInput.value){
        bookAvailableStatus.value = 'AVAILABLE';
        statusDiv.textContent = bookAvailableStatus.value + "(" + (totalAmountInput.value - rented) + " out of " + totalAmountInput.value + ")";
    }
    else {
        bookAvailableStatus.value = 'UNAVAILABLE';
        if (totalAmount === '0'){
            statusDiv.textContent = bookAvailableStatus.value + "(0 copies of book in the library)";
        } else{
            statusDiv.textContent = bookAvailableStatus.value + "(expected to become available on " + getEarlierAvailabilityDate() + ")";
        }
    }

    //добавить проверку если rented === totalAmount
    let returnStatuses = document.getElementsByName('record_returnStatus');

}

function isRentedEqualsTotalAmount(){
    return rentedBooksHidden.value == totalAmountInput.value;
}

function getEarlierAvailabilityDate() {
    let dues = document.getElementsByName('record_dueDate');
    let earlierDue = dues[0].value;
    for (let i = 1; i < dues.length; i++) {
       if (moment(earlierDue).isBefore(dues[i].value) === false){
            earlierDue = dues[i].value;
       }
    }
    return earlierDue;
}

function isRecordLimit() {
    return rentedBooksHidden.value === totalAmountInput.value;
}

function editBookStatus(returnedBookStatus){
    if (returnedBookStatus === 'RETURNED'){
        setBookStatus();
    } else {
        totalAmountInput.value = totalAmountInput.value - 1;
        setBookStatus();
    }

}
