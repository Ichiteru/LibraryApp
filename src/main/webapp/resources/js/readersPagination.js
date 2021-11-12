var pagination_reader_counter = 0;
let readersTableBody = document.getElementById('readersTable').getElementsByTagName('tbody')[0];

async function displayReaders(th){
    // alert('work');
    let readersAmount = document.getElementById('readersAmount').value;
    if (th.id == 'prevPage'){
        if (pagination_reader_counter != 0){
            pagination_reader_counter = pagination_reader_counter - 2;
        }
    }
    else if (th.id == 'firstPage'){
        pagination_reader_counter = 0;
    }
    else if (th.id == 'secondPage'){
        pagination_reader_counter = 2
    }
    else if (th.id == 'thirdPage'){
        pagination_reader_counter = 4;
    }
    if (th.id == 'nextPage'){
        if (pagination_reader_counter + 2 < readersAmount){
            pagination_reader_counter = pagination_reader_counter + 2;
        }
    }
    const response = await fetch('/get/ten/readers', {
        method: 'POST', // или 'PUT'
        // body: 'email:' + document.getElementById("dropdownEmailSearch").value, // данные могут быть 'строкой' или {объектом}!
        body: "pag="+pagination_reader_counter,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    });
    const json = await response.json();
    let readersArray = jsonToReadersArray(json);
    updateTBody(readersArray);
}

function updateTBody(resp) {
    let newTBody = document.createElement('tbody');
    resp.forEach(reader => {
        let tr = document.createElement('tr');
        tr.onclick = function(){openReaderModal(this)};
        tr.append(getInput('hidden', reader.id, 'reader_id'),
            getInput('hidden', reader.fName, 'reader_firstName'),
            getInput('hidden', reader.lName, 'reader_lastName'),
            getInput('hidden', reader.email, 'reader_email'),
            getInput('hidden', reader.regDate, 'reader_registrationDate'),
            getInput('hidden', reader.phone, 'reader_phone'),
            getInput('hidden', reader.gender, 'reader_gender'),);
        tr.append(createTdElem(reader.fName + ' ' + reader.lName),
            createTdElem(reader.email),
            createTdElem(reader.regDate),
            createTdElem(reader.phone))
        newTBody.appendChild(tr);
    });
    document.getElementById('readersTable').replaceChild(newTBody, readersTableBody);
    readersTableBody = newTBody;
}

function jsonToReadersArray(resp) {
    let readers = [];
    resp.forEach(reader => {
        let date = document.createElement('input'); date.type='date'; date.value = reader.registrationDate;
        let book = {
            id : reader.id,
            email : reader.email,
            fName : reader.firstName,
            lName : reader.lastName,
            regDate : date.value,
            phone : reader.phone,
            gender : reader.gender
        }
        readers.push(book);
    });
    return readers;
}

function createTdElem(content) {
    let td = document.createElement('td');
    td.textContent = content;
    return td;
}

function getInput(type, value, name) {
    let input = document.createElement('input');
    input.setAttribute('type', type);
    input.setAttribute('value', value);
    input.setAttribute('name', name);
    return input;
}