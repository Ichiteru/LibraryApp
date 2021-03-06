const table = document.getElementById("booksTable");

function getSelectedBooksId() {

        const checkboxes = document.getElementsByName('deleteCheckBox');
        let checkedValues = [];
        for (let index = 0; index < checkboxes.length; index++) {
            if (checkboxes[index].checked){
                checkedValues.push(checkboxes[index].value);
            }
        }
        return checkedValues;

}

function deleteSelectedBooks() {
    let checkedValues = getSelectedBooksId(); // получаем массив isbn книг
    if (checkedValues.length == 0){
        showAlertModal('Validation error!', 'No one book was selected for delete.');
        return false;
    } else {
        let form = document.getElementById("deleteForm");
        for (let i = 0; i < checkedValues.length; i++) {
            let input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "booksId");
            input.setAttribute("value", checkedValues[i]);
            form.appendChild(input);
        }
        return true;
    }
}
