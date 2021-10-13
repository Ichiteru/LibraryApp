function addBookGenre() {
    if (checkCoincidenceGenre(document.getElementsByName('bookGenre')) == true){
        alert("This genre already exists in book genres list");
    } else{
        let elementsByName = document.getElementsByName('bookGenre');
        let genreDiv = document.getElementById("genreDiv");
        let divElement = document.createElement('div');
        divElement.setAttribute('class', 'row mt-1');
        document.getElementById("genreDiv").appendChild(divElement);
        let genreInput = document.createElement('input');
        genreInput.setAttribute('type', 'text');
        genreInput.setAttribute('name', 'bookGenre');
        genreInput.setAttribute('class', 'form-control ml-3');
        genreInput.setAttribute('style', 'width: 40%');
        genreInput.setAttribute('value', document.getElementById("select").value);
        genreInput.disabled = true;
        divElement.appendChild(genreInput);

        let button = document.createElement('input');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-danger');
        button.setAttribute('value', 'D');
        button.onclick = function () {deleteGenre(this);};
        divElement.appendChild(button);
    }
}

function checkCoincidenceGenre(list){
    for (let i = 0; i < list.length; i++) {
        if (list[i].value === document.getElementById('select').value){
            return true;
        }
    }
    return false;
}