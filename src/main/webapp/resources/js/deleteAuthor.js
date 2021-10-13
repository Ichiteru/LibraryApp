function deleteAuthor(th) {
    alert("delete");
    if (document.getElementsByName("authorName").length === 1){
        alert("One more authors are required to be");
    } else {
        let parentNode = th.parentNode.remove();
    }
}