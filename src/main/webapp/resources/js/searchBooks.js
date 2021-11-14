let selAuthor = document.getElementById('select-author');
let selGenre = document.getElementById('select-genre');


function addReader() {
    if (isExist('authorId', selAuthor.value)){
        showAlertModal('Validation error!', 'This author already exists in list.')
    } else {
        let hidden = document.createElement('input');
        hidden.type = 'hidden';
        hidden.value = selAuthor.value;
        hidden.name = 'authorId';
        let p = document.createElement('p');
        p.className = 'm-0';
        p.textContent = selAuthor.options[selAuthor.selectedIndex].textContent;
        let div = document.createElement('div');
        let closeBtn = document.createElement('button');
        closeBtn.type = 'button';
        closeBtn.className = 'close';
        closeBtn.addEventListener('click', function (){deleteValue(this)});
        closeBtn.innerHTML = '<span aria-hidden="true">&times;</span>';
        div.append(closeBtn, hidden,p);
        document.getElementById('authorsDiv').appendChild(div);
    }
}

function addGenre() {
    if (isExist('genreId', selGenre.value)){
        showAlertModal('Validation error!', 'This genre already exists in list.');
    } else{

        let hidden = document.createElement('input');
        hidden.type = 'hidden';
        hidden.value = selGenre.value;
        hidden.name = 'genreId';
        let p = document.createElement('p');
        p.className = 'm-0';
        p.textContent = selGenre.options[selGenre.selectedIndex].textContent;
        let div = document.createElement('div');
        let closeBtn = document.createElement('button');
        closeBtn.type = 'button';
        closeBtn.className = 'close';
        closeBtn.addEventListener('click', function (){deleteValue(this)});
        closeBtn.innerHTML = '<span aria-hidden="true">&times;</span>';
        div.append(closeBtn, hidden,p);
        document.getElementById('genreDiv').appendChild(div);
    }
}

function deleteValue(th) {
    th.parentNode.remove();
}

function isExist(elemName, val) {
    for (let i = 0; i < document.getElementsByName(elemName).length; i++) {
        if (document.getElementsByName(elemName)[i].value == val){
            return true;
        }
    }
    return false;
}

function isAllInputsEmpty() {
    if (document.getElementsByName('authorId').length == 0 &&
    document.getElementsByName('genreId').length == 0 &&
    document.getElementById('title').value == '' &&
    document.getElementById('description').value == ''){
        showAlertModal('Validation error!', 'At least one field should be not empty');
        return false;
    } else return true;
}