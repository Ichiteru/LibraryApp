var btnSave = document.getElementById('btn-save');
btnSave.addEventListener('click',addNewAuthor);


function addExistingBookAuthor() {
    if (checkCoincidenceAuthor(document.getElementsByName('authorName')) == true){
        alert("This author already exists in book authors list");
    } else{
        // let genreDiv = document.getElementById("authorDiv");
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
    }
}

function checkCoincidenceAuthor(list){
        for (let i = 0; i < list.length; i++) {
                // alert(list[i].value == document.getElementById('selectAuthor').value);
                if (list[i].value == document.getElementById('selectAuthor').value){
                        return true;
                }
        }
        return false;
}

function deleteAuthor(th) {
    alert("delete");
    if (document.getElementsByName("authorName").length === 1){
        alert("One more authors are required to be");
    } else {
        let parentNode = th.parentNode.remove();
    }
}

function addNewAuthor() {
    if (checkCoincidenceNewAuthor(document.getElementsByName('authorName')) == true){
        alert("This author already exists in book authors list");
    } else {
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
        locModal.style.display = "none";
        locModal.className="modal fade";
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
