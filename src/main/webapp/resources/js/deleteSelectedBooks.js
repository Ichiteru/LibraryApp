const table = document.getElementById("booksTable");

function getSelectedBooksISBN() {
    // for (let r = 1, n = table.rows.length; r < n; r++) {
        // const book_isbn = table.rows[r].cells[0].innerHTML;
        // const checkbox = table.rows[r].cells[4].innerHTML;
        // // table.rows[r].cells[4]
        //     alert(table.rows[r].cells[0].innerHTML);
        //     alert(table.rows[r].cells[4].value);
        //     alert(table.rows[r].cells[4].checked);
        const checkboxes = document.getElementsByName('deleteCheckBox');
        let checkedValues = [];
        for (let index = 0; index < checkboxes.length; index++) {
            if (checkboxes[index].checked){
                checkedValues.push(checkboxes[index].value);
                alert(checkboxes[index].value);
            }
        }
        return checkedValues;
    // }
}

function deleteSelectedBooks() {
    let checkedValues = getSelectedBooksISBN();
}
