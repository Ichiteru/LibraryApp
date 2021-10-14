function saveBookChanges() {
    let authors = document.getElementsByName("authorName");
    let genres = document.getElementsByName("bookGenre");
    for (let i = 0; i < authors.length; i++) {
        authors[i].disabled = false;
    }
    for (let i = 0; i < genres.length; i++) {
        genres[i].disabled = false;
    }
    document.getElementById('changeBookInfo').submit();
}