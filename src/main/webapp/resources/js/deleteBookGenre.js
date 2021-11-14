function deleteGenre(th) {
    if (document.getElementsByName("bookGenre").length === 1){
        showAlertModal('Validation error!', 'One more genres are required to be.');
    } else {
        let parentNode = th.parentNode.remove();
    }

}