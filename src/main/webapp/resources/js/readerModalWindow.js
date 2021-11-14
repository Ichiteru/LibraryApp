let btnSaveReader = document.getElementById('btn-save-reader');
let btnCloseReaderModal = document.getElementById('btn-close-reader-modal');
let readerModal = document.getElementById('readerModal');
let readerForm = document.getElementById('readerForm');
let chosenEmail;

btnCloseReaderModal.addEventListener('click', () => {
    readerModal.style.display = "none";
    readerModal.className="modal fade";
})

function openReaderModal(th) {
    readerModal.style.display = "block";
    readerModal.style.paddingRight = "17px";
    readerModal.className="modal fade show";
    if (th.tagName === 'TR'){
        btnSaveReader.removeEventListener('click', addReader);
        btnSaveReader.addEventListener('click', editReader);
        let dataInputs = th.getElementsByTagName('input');
        readerForm.querySelector('input[id="id"]').value = dataInputs.namedItem('reader_id').value;
        readerForm.querySelector('input[id="registrationDate"]').value = dataInputs.namedItem('reader_registrationDate').value;
        readerForm.querySelector('input[id="firstName"]').value = dataInputs.namedItem('reader_firstName').value;
        readerForm.querySelector('input[id="lastName"]').value = dataInputs.namedItem('reader_lastName').value;
        readerForm.querySelector('input[id="email"]').value = dataInputs.namedItem('reader_email').value;
        readerForm.querySelector('input[id="init_email"]').value = dataInputs.namedItem('reader_email').value;
        chosenEmail = dataInputs.namedItem('reader_email').value;
        readerForm.querySelector('input[id="phone"]').value = dataInputs.namedItem('reader_phone').value;
        if (dataInputs.namedItem('reader_gender').value === 'MALE'){
            document.getElementById('gender_radio2').checked = false;
            document.getElementById('gender_radio1').checked = true;
        } else {
            document.getElementById('gender_radio1').checked = false;
            document.getElementById('gender_radio2').checked = true;

        }
    } else {
        readerForm.querySelector('input[id="id"]').value = '0';
        readerForm.querySelector('input[id="registrationDate"]').value = moment(new Date(Date.now())).format("YYYY-MM-DD") ;
        document.getElementById('gender_radio1').checked = true;
        readerForm.querySelector('input[id="init_email"]').value = 'none';
        btnSaveReader.removeEventListener('click', editReader);
        btnSaveReader.addEventListener('click', addReader);
    }
    // readerForm.querySelector('input[id="gender"]').value = dataInputs.itemByName('reader_gender').value;
}

function addReader(){
    if (!isEmailValid() ) showAlertModal('Validation error!', 'Email is incorrect!');
    else if (!isEmailUnique()) showAlertModal('Validation error!', 'This email already in use!');
    else  if (!isFNameValid()) showAlertModal('Validation error!', 'Name is incorrect!');
    else  if (!isLNameValid()) showAlertModal('Validation error!', 'Surname is incorrect!');
    else if (!isPhoneNumberValid()) showAlertModal('Validation error!', 'Phone number is incorrect!');
    else {
        readerForm.submit();
    }
}

function editReader() {
    if (!isEmailValid() ) showAlertModal('Validation error!', 'Email is incorrect!');
    else if (!isEmailUnique() && readerForm.querySelector('input[id="email"]').value != chosenEmail) showAlertModal('Validation error!', 'This email already in use!');
    else  if (!isFNameValid()) showAlertModal('Validation error!', 'Name is incorrect!');
    else  if (!isLNameValid()) showAlertModal('Validation error!', 'Surname is incorrect!')
    else if (!isPhoneNumberValid()) showAlertModal('Validation error!', 'Phone number is incorrect!');
    else {
        readerForm.submit();
    }

    // readerForm.submit();
}

function isEmailValid() {
    const re = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return  re.test( readerForm.querySelector('input[id="email"]').value)
}

function isEmailUnique() {
    for (const email of document.getElementsByName('reader_email')) {
      if (email.value === readerForm.querySelector('input[id="email"]').value)
          return false;
    };
    return  true;
}

function isFNameValid() {
    const re = /^[a-zA-Z0-9.\-_$@*!]{2,20}$/;
    return  re.test(readerForm.querySelector('input[id="firstName"]').value)
}

function isLNameValid() {
    const re = /^[a-zA-Z0-9.\-_$@*!]{2,20}$/;
    return  re.test(readerForm.querySelector('input[id="lastName"]').value)
}

function isPhoneNumberValid() {
    const re = /^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$/;
    return  re.test(readerForm.querySelector('input[id="phone"]').value) || readerForm.querySelector('input[id="phone"]').value == '';
}