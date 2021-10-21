var locModal = document.getElementById('borrowModal');
var btnclose = document.getElementById('w-change-close-borrow-modal-window');
var btnShow= document.getElementById('showAddBorrowerModal');
var btnSave = document.getElementById('btn-save-borrower');
// btnSave.addEventListener('click',addNewAuthor);
var likeEmailsDiv = document.getElementById("dropdownEmailSelect");
var jsonReaders;

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

async function checkExistingEmail() {
    let allReaders = document.getElementById("allReaders");
    if (document.getElementById("dropdownEmailSearch").value.length >= 3) {
        const response = await fetch('/get/readers/by-email', {
            method: 'POST', // или 'PUT'
            // body: 'email:' + document.getElementById("dropdownEmailSearch").value, // данные могут быть 'строкой' или {объектом}!
            body: "email="+document.getElementById("dropdownEmailSearch").value,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        });
        const json = await response.json();
        a = json;
        document.getElementsByName("borrowerEmail").forEach(a => likeEmailsDiv.removeChild(a));
        json.forEach(el => {
            let a = document.createElement('a');
            a.setAttribute('class', 'dropdown-item');
            a.setAttribute('name', 'borrowerEmail');
            a.setAttribute('href', '#');
            a.onclick = function () {setReaderName(this);};
            a.textContent = el.email;
            likeEmailsDiv.appendChild(a);
        });

    }
}

function setReaderName(th) {
    alert("work");
    a.forEach(reader =>{
        if (reader.email === th.textContent){
            document.getElementById("borrowerFirstName").setAttribute('value', reader.firstName);
            document.getElementById("borrowerLastName").setAttribute('value', reader.lastName);
            document.getElementById("dropdownEmailSearch").value = th.textContent;
        }
    })
}
