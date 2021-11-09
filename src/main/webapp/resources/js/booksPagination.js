var pagination_counter = 0;
let booksTableBody = document.getElementById('booksTable').getElementsByTagName('tbody')[0];

async function display(th){
    alert('work');
    let bookAmount = document.getElementById('bookAmount').value;
    if (th.id == 'prevPage'){
        if (pagination_counter != 0){
            pagination_counter = pagination_counter - 10;
        }
    }
    else if (th.id == 'firstPage'){
        pagination_counter = 0;
    }
    else if (th.id == 'secondPage'){
        pagination_counter = 10
    }
    else if (th.id == 'thirdPage'){
        pagination_counter = 20;
    }
    if (th.id == 'nextPage'){
        if (pagination_counter + 10 < bookAmount){
            pagination_counter = pagination_counter + 10;
        }
    }
    const response = await fetch('/get/ten/books', {
        method: 'POST', // или 'PUT'
        // body: 'email:' + document.getElementById("dropdownEmailSearch").value, // данные могут быть 'строкой' или {объектом}!
        body: "pag="+pagination_counter,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    });
    const json = await response.json();
    let booksArray = jsonToBooksArray(json);
    updateTableBody(booksArray);
}

function updateTableBody(resp) {
    let newTBody = document.createElement('tbody');
    resp.forEach(book => {
       let tr = document.createElement('tr');
       let titleTd = document.createElement('td');
       let hiddenId = document.createElement('input'); hiddenId.type = 'hidden'; hiddenId.value = book.id;
       let hrefTitle = document.createElement('a'); hrefTitle.href = '/books/' + book.id; hrefTitle.textContent = book.title;
       titleTd.append(hiddenId, hrefTitle);
       let authorsTd = document.createElement('td');
       let authorsTdContent;
       book.authors.forEach(a => {
           authorsTdContent += a.firstName + ' ' + a.lastName + '</br>';
       });
       authorsTd.textContent = authorsTdContent;
       let publishDateTd = document.createElement('td');
       publishDateTd.textContent = book.publishDate;
       let totalAmountTd = document.createElement('td');
       totalAmountTd.textContent = book.totalAmount;
       let checkBoxTd = document.createElement('td');
       let checkbox = document.createElement('input'); checkbox.type = 'checkbox'; checkbox.value = book.id; checkbox.name = 'deleteCheckBox';
       checkBoxTd.appendChild(checkbox);
       tr.append(titleTd, authorsTd, publishDateTd, totalAmountTd, checkBoxTd);
       newTBody.appendChild(tr);
    });
    document.getElementById('booksTable').replaceChild(newTBody, booksTableBody);
    booksTableBody = newTBody;
}

function jsonToBooksArray(resp) {
    let books = [];
    resp.forEach(b => {
        let book = {
            id : b.id,
            title : b.title,
            publishDate : b.publishDate,
            totalAmount : b.totalAmount,
            authors : b.authors
        }
        books.push(book);
    });
    return books;
}