function deleteGenre(th) {
    if (document.getElementsByName("bookGenre").length === 1){
        alert("One more genres are required to be");
    } else {
        let parentNode = th.parentNode.remove();
    }

}