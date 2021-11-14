var btnSave = document.getElementById('btn-save');
btnSave.addEventListener('click',addNewAuthor);

document.getElementById('add-existing-author').addEventListener('click', addExistingBookAuthor);


function addExistingBookAuthor() {
    if (checkCoincidenceAuthor(document.getElementsByName('authorName')) == false){
        let divElement = document.createElement('div');
        divElement.setAttribute('class', 'row mt-1 ml-1');
        document.getElementById("authorDiv").appendChild(divElement);
        let authorInput = document.createElement('input');
        authorInput.setAttribute('type', 'text');
        authorInput.setAttribute('name', 'authorName');
        authorInput.setAttribute('class', 'form-control');
        authorInput.setAttribute('style', 'width: 40%');
        authorInput.setAttribute('value', document.getElementById("selectAuthor").value);
        authorInput.readOnly = true;
        divElement.appendChild(authorInput);
        let button = document.createElement('input');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-danger');
        button.setAttribute('value', 'D');
        button.onclick = function () {deleteAuthor(this);};
        divElement.appendChild(button);
    } else {
        showAlertModal('Validation error!', 'Author already exists.');
    }

}

function checkCoincidenceAuthor(list){
        for (let i = 0; i < list.length; i++) {
                if (list[i].value == document.getElementById('selectAuthor').value){
                        // showAlertModal('Validation error!', 'Author already exists.');
                        return true;
                }
        }
        return false;
}

function deleteAuthor(th) {
    if (document.getElementsByName("authorName").length === 1){
        showAlertModal('Validation error!', 'One more authors are required to be');
    } else {
        let parentNode = th.parentNode.remove();
    }
}

function addNewAuthor() {
    if (!isValidAndNotEmpty(/^[a-zA-Z0-9.\-_$@*!]{2,20}$/, document.getElementById('firstName').value)){
        showAlertModal('Validation error!', 'Author name should be correct(without spaces!!!).');
    } else if (!isValidAndNotEmpty(/^[a-zA-Z0-9.\-_$@*!]{2,20}$/, document.getElementById('lastName').value)){
        showAlertModal('Validation error!', 'Author surname should be correct(without spaces!!!).');
    }
    else if (checkCoincidenceNewAuthor(document.getElementsByName('authorName')) == false){
        let divElement = document.createElement('div');
        divElement.setAttribute('class', 'row mt-1 ml-1');
        document.getElementById("authorDiv").appendChild(divElement);
        let authorInput = document.createElement('input');
        authorInput.setAttribute('type', 'text');
        authorInput.setAttribute('name', 'authorName');
        authorInput.setAttribute('class', 'form-control');
        authorInput.setAttribute('style', 'width: 40%');
        authorInput.setAttribute('value', document.getElementById("firstName").value + " " +
            document.getElementById("lastName").value);
        authorInput.readOnly = true;
        divElement.appendChild(authorInput);

        let button = document.createElement('input');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-danger');
        button.setAttribute('value', 'D');
        button.onclick = function () {deleteAuthor(this);};
        divElement.appendChild(button);
        locModalAuthor.style.display = "none";
        locModalAuthor.className="modal fade";
    }
}

function checkCoincidenceNewAuthor(list){
    for (let i = 0; i < list.length; i++) {
        if (list[i].value === document.getElementById("firstName").value + " " +
            document.getElementById("lastName").value){
            return true;
        }
    }
    return false;
}
