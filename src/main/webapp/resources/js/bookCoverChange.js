function onFileSelected(event) {
    alert('hello');
    var selectedFile = event.target.files[0];
    var reader = new FileReader();

    var imgtag = document.getElementById("bookCover");
    imgtag.title = selectedFile.name;

    reader.onload = function(event) {
        imgtag.src = event.target.result;
    };

    reader.readAsDataURL(selectedFile);
}

function showHide(elemID){
    document.getElementById(elemID=="basicInfo"?"connectedServices":"basicInfo").style.display = "none";
    document.getElementById(elemID=="basicInfo"?"basicInfo":"connectedServices").style.display = "block";
}