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
    <div class="container mt-5">

        <table class="table table-bordered table-hover" id="booksTable">
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
                    <c:forEach items="${bookList}" var="book">
                    <tr>
                        <td>
                            <input type="hidden" value="${book.id}">
                            <a href="/books/${book.id}"><c:out value="${book.title}"/></a>
                        </td>
                        <td>
                                <c:forEach items="${book.authors}" var="author">

                                        <c:out value="${author.firstName} ${author.lastName}"/><br/>

                                </c:forEach>
                        </td>
                        <td><c:out value="${book.publishDate}"/></td>
                        <td>${book.totalAmount}</td>
                        <td>
                            <input type="checkbox" name="deleteCheckBox" value="${book.id}">
                        </td>
                    </tr>
                    </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col-sm">
                <form action="/books/${book.id}" method="get">
                    <button type="submit" class="btn btn-outline-primary">Add book</button>
                </form>
            </div>
            <div class="col-sm text-center">
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="float: contour">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-sm">
                <form onsubmit="deleteSelectedBooks()" name="deleteForm" id="deleteForm" action="/delete" method="post" >
                    <input  type="submit" class="btn btn-outline-primary" value="Delete selected books" style="float: right"></input>
                </form>
            </div>
        </div>

        <script src="./../../resources/js/deleteSelectedBooks.js"></script>
    </div>
</body>
</html>