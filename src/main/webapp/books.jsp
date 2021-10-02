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
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Authors</th>
            <th scope="col">Publish date</th>
            <th scope="col">Copies</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${bookList}" var="book">
                    <td><c:out value="${book.title}"/> </td>
                    <td>
                        <ul>
                            <c:forEach items="${book.authors}" var="author">
                                <li>${author.firstName} ${author.lastName}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${book.publishDate}</td>
                    <td>${book.totalAmount}</td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
    <button type="button" class="btn btn-outline-primary">Add book</button>
</body>
</html>