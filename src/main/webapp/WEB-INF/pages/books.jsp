<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA==" crossorigin="anonymous">
</head>
<body>
    <c:import url="navbar.jsp"/>
    <table class="table" id="booksTable">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Authors</th>
            <th scope="col">Publish date</th>
            <th scope="col">Copies</th>
            <th scope="col">Select</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${bookList}" var="book">
                    <td><c:out value="${book.title}"/> </td>
                    <td>
                            <c:forEach items="${book.authors}" var="author">
                                    <c:out value="${author.firstName}"/>
                                    <c:out value="${author.lastName}"/> <br/>
                            </c:forEach>
                    </td>
                    <td><c:out value="${book.publishDate}"/></td>
                    <td>${book.totalAmount}</td>
                    <td>
                        <input type="checkbox" name="deleteCheckBox" value="${book.isbn}">
                    </td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
    <button type="button" class="btn btn-outline-primary">Add book</button>
    <button onclick="deleteSelectedBooks()" type="button" class="btn btn-outline-primary">Delete selected books</button>
    <script src="./../../resources/js/deleteSelectedBooks.js"></script>
</body>
</html>