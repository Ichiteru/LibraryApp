function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();
    var cover = document.getElementById("bookCover");
    let coverHidden = document.getElementById('cover-hidden');
    cover.title = selectedFile.name;

    reader.onload = function(event) {
        cover.src = event.target.result;
        coverHidden.value = event.target.result;
        alert(cover.src);
    };
    reader.readAsDataURL(selectedFile);
}

function showHide(elemID){
    document.getElementById(elemID=="basicInfo"?"connectedServices":"basicInfo").style.display = "none";
    document.getElementById(elemID=="basicInfo"?"basicInfo":"connectedServices").style.display = "block";
}