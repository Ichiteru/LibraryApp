var locModal = document.getElementById('locModal');
var btnclose = document.getElementById('w-change-close');
var btnShow= document.getElementById('w-change-location');


//show the modal
btnShow.addEventListener('click', (e) => {
    locModal.style.display = "block";
    locModal.style.paddingRight = "17px";
    locModal.className="modal fade show";
});
//hide the modal
btnclose.addEventListener('click', (e) => {
    locModal.style.display = "none";
    locModal.className="modal fade";
});

function addNewAuthor() {
    if (checkCoincidenceNewAuthor(document.getElementsByName('authorName')) == true){
        alert("This author already exists in book authors list");
    } else {
        let divElement = document.createElement('div');
        divElement.setAttribute('class', 'row mt-1');
        document.getElementById("authorDiv").appendChild(divElement);
        let authorInput = document.createElement('input');
        authorInput.setAttribute('type', 'text');
        authorInput.setAttribute('name', 'authorName');
        authorInput.setAttribute('class', 'form-control');
        authorInput.setAttribute('style', 'width: 30%');
        authorInput.setAttribute('value', document.getElementById("firstName").value + " " +
            document.getElementById("lastName").value);
        authorInput.disabled = true;
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
