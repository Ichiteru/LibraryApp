    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"
          integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA=="
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="./../resources/css/search.css">
</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="error-modal.jsp"></c:import>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="card">

                <div class="card-body">

                    <div class="row">
                        <div class="col-12">
                            <form method="POST" action="/search/books" id="searchForm">
                                <div class="row">
                                    <div class="col-sm-3 col-md-2 col-5">
                                        <label style="font-weight:bold;">Title</label>
                                    </div>
                                    <div class="col-md-8 col-6">
                                        <input class="form-control"  placeholder="Title" name="title" id="title">
                                    </div>
                                </div>
                                <hr />

                                <div class="row">
                                    <div class="col-sm-3 col-md-2 col-5">
                                        <label style="font-weight:bold;">Author(-s)</label>
                                    </div>
                                    <div class="col-md-6 col-6">
                                        <select id="select-author" class="form-select" style="width: 30%">
                                            <c:forEach items="${authors}" var="author">
                                                <option value="${author.id}">${author.firstName} ${author.lastName}</option>
                                            </c:forEach>
                                        </select>
                                        <button type="button" class="btn btn-success mr-2" onclick="addReader()" >Add</button>
                                    </div>
                                    <div class="col-md-4 col-6" id="authorsDiv">

                                    </div>
                                </div>
                                <hr />


                                <div class="row">
                                    <div class="col-sm-3 col-md-2 col-5">
                                        <label style="font-weight:bold;">Genre(-s)</label>
                                    </div>
                                    <div class="col-md-6 col-6">
                                        <select id="select-genre" class="form-select" style="width: 30%">
                                            <c:forEach items="${genres}" var="genre">
                                                <option value="${genre.id}">${genre.name}</option>
                                            </c:forEach>
                                        </select>
                                        <button type="button" class="btn btn-success mr-2" onclick="addGenre()" >Add</button>
                                    </div>
                                    <div class="col-md-4 col-6" id="genreDiv">

                                    </div>
                                </div>
                                <hr />
                                <div class="row">
                                    <div class="col-sm-3 col-md-2 col-5">
                                        <label style="font-weight:bold;">Description</label>
                                    </div>
                                    <div class="col-md-8 col-6">
                                        <textarea id="description" name="description" class="form-control" placeholder="Description text"
                                                  style="height: 150px;"></textarea>
                                    </div>
                                </div>
                                <hr />
                                <div class="row">
                                    <div class="container">
                                        <button type="submit" class="btn btn-primary btn-lg" onclick="return isAllInputsEmpty()" style="float: right">Search</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="card-body">
                    <table class="table table-bordered table-hover" id="booksTable">
                        <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Authors</th>
                            <th scope="col">Publish date</th>
                            <th scope="col">Copies</th>
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

                                        <p class="mt-0 mb-0"><c:out value="${author.firstName} ${author.lastName}"/></p>

                                    </c:forEach>
                                </td>
                                <td><c:out value="${book.publishDate}"/></td>
                                <td>${book.totalAmount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    </div>
</div>

<script src="./../resources/js/searchBooks.js"></script>
<script src="../resources/js/errorModal.js"></script>
</body>
</html>
