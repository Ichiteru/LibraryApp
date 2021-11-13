<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.10.2021
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><c:if test="${book.id != null}">
        <c:out value="${book.title}"></c:out>
    </c:if> <c:if test="${book.id == null}">
        <c:out value="${'New book'}"></c:out>
    </c:if>
    </title>
    <link rel="stylesheet" href="./../resources/css/current-book.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"
          integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA=="
          crossorigin="anonymous">

</head>
<body onload="setBookStatus()" style="background-image: url(https://i.pinimg.com/originals/67/18/22/671822c2f63dd5f65d8fd15c9710420b.jpg);
                                        background-position: 50% 0;">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
<c:set var="book" value="${book}"></c:set>
<c:import url="navbar.jsp"/>
<c:import url="add-borrower-modal.jsp"></c:import>
<c:import url="edit-borrow-status-modal.jsp"></c:import>
<form id="changeBookInfo" method="post"
      <c:if test="${book.id != null}">action="/books/change"</c:if>
      <c:if test="${book.id == null}">action="/books/add"</c:if> >
    <input type="hidden" name="bookId" value="${book.id}">
    <div class="container" style="padding: 100px 0px 100px 0px; background-image: none">
        <div class="row">
            <div class="col-12">
                <div class="card" >

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img <c:if test="${book.id == null}">src="./../resources/img/default-cover.jpg"</c:if>
                                         <c:if test="${book.id != null}">src="${book.cover}"</c:if> alt="cover_img" id="bookCover" style="width: 160px; height: 150px"
                                         class="img-thumbnail"/>
                                    <input type="hidden" id="cover-hidden" name="cover" value="${book.cover}">
                                    <div class="middle" style="width: 160px; flex: 0 0 auto">
                                        <div class="custom-file" style="margin-top: 10px">
                                            <input type="file" onchange="onFileSelected(event)"
                                                   class="custom-file-input"
                                                   id="inputGroupFile04"
                                                   name="bookTitle" >
                                            <label class="custom-file-label" for="inputGroupFile04"
                                                   style="font-size: small; background: none">Choose file</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="userData ml-3" style="flex: 1 1 auto">
                                    <input required name="title" type="text" class="form-control" value="${book.title}"/>
                                    <p class="d-block" style="margin-top: 5px">
                                        <textarea name="description" class="form-control"
                                                  style="height: 80%"><c:out
                                                value="${book.description}"></c:out></textarea>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a onclick="showHide('basicInfo')" class="nav-link" id="basicInfo-tab" data-toggle="tab"
                                           href="#basicInfo" role="tab" aria-controls="basicInfo" >Book
                                            Info</a>
                                    </li>
                                    <li class="nav-item">
                                        <a onclick="showHide('connectedServices')" class="nav-link" id="connectedServices-tab" data-toggle="tab"
                                           href="#connectedServices" role="tab" aria-controls="connectedServices"
                                           aria-selected="false">Borrowers</a>
                                    </li>
                                </ul>
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="basicInfo">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Author(s)</label>
                                            </div>
                                            <div class="col-md-4 col-6" id="authorDiv">
                                                <c:forEach items="${book.authors}" var="author">
                                                    <div class="row mt-1 ml-1">
                                                        <input type="text" class="form-control" name="authorName"
                                                               value="${author.firstName} ${author.lastName}"
                                                               style="width: 40%" readonly/>
                                                        <input type="button" class="btn btn-danger" value="D"
                                                               onclick="deleteAuthor(this)"></input>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="col-md-6 col-3">
                                                <div class="row">
                                                    <div class="col-sm">
                                                        <select id="selectAuthor" class="form-select" aria-label="Default select example" style="height: 38px; float: right">
                                                            <c:forEach items="${authors}" var="opt_author">
                                                                <option value="${opt_author.firstName} ${opt_author.lastName}">${opt_author.firstName} ${opt_author.lastName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm">
                                                            <button type="button" class="btn btn-success"
                                                                    onclick="addExistingBookAuthor()">Add author
                                                            </button>
                                                            <button id="w-change-location" type="button"
                                                                    class="btn btn-primary" data-toggle="modal"
                                                                    data-target="#locModal">New author</button>
                                                            <c:import url="add-author-modal.jsp"></c:import>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <hr/>

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Publisher</label>
                                            </div>
                                            <div class="col-md-10 col-6">
                                                <input required name="publisher" type="text" class="form-control shadow-none border-none"
                                                       value="${book.publisher}"/>
                                            </div>
                                        </div>
                                        <hr/>


                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Publish date</label>
                                            </div>
                                            <div class="col-md-10 col-6">
                                                <input required
                                                       pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}"
                                                       maxlength="10" name="publishDate" type="date"
                                                       class="form-control" value="${book.publishDate}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Genre(-s)</label>
                                            </div>
                                            <div class="col-md-4 col-6" id="genreDiv">
                                                <c:forEach items="${book.genres}" var="genre">
                                                    <div class="row mt-1">
                                                        <input type="text" class="form-control ml-3" name="bookGenre"
                                                               value="${genre.name}"
                                                               style="width: 40%" readonly/>
                                                        <input type="button" class="btn btn-danger"
                                                               onclick="deleteGenre(this)" value="D"></input>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="col-md-6 col-3">
                                                <div class="row">
                                                    <div class="col-sm">
                                                        <select id="select" class="form-select"
                                                                aria-label="Default select example"
                                                                style="height: 38px; float: right">
                                                            <c:forEach items="${genres}" var="opt_genre">
                                                                <option value="${opt_genre.name}">${opt_genre.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm">
                                                        <button type="button" class="btn btn-success"
                                                                onclick="addBookGenre()" style="float: right;
                                                                                                margin-right: 10%">Add genre
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Pages</label>
                                            </div>
                                            <div class="col-md-10 col-6">
                                                <input required name="pageCount" type="number" min="0" max="1500"
                                                       class="form-control" value="${book.pageCount}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">ISBN</label>
                                            </div>
                                            <div class="col-md-10 col-6">
                                                <input required pattern="^(?=(?:\D*\d){10}(?:(?:\D*\d){3})?$)[\d-]+$"
                                                       type="text" class="form-control" name="isbn"
                                                       value="${book.isbn}"/>
                                                <input type="hidden" name="startIsbn" value="${book.isbn}">
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Total amount</label>
                                            </div>
                                            <div class="col-md-10 col-6">
                                                <input required onchange="isTotalAmountMoreThanRented(this)" min="0" max="1500" name="totalAmount" type="number"
                                                       class="form-control" value="${book.totalAmount}"/>
                                                <input type="hidden" id="rentedBooks">
                                            </div>
                                        </div>
                                        <hr/>
                                        <c:if test="${book.id != null}">
                                            <div class="row">
                                                <div class="col-sm-3 col-md-2 col-5">
                                                    <label style="font-weight:bold;">Status</label>
                                                </div>
                                                <div class="col-md-10 col-6" id="bookStatusDiv">
<%--                                                    <c:out value="${book.status}"/>--%>
                                                </div>
                                                    <input type="hidden"  name="status" value="${book.status}">
                                            </div>
                                        </c:if>
                                        <hr/>

                                    </div>
                                    <div id="connectedServices" style="display: none">
                                        <c:set var="switch"></c:set>
                                        <input type="hidden" value="${allReaders}" id="allReaders">
                                        <table class="table table-bordered table-hover" id="borrowersTable">
                                            <thead>
                                            <tr>
                                                <th scope="col">Email</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Borrow date</th>
                                                <th scope="col">Due date</th>
                                                <th scope="col">Return date</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${book.borrowRecords}" var="borrowRecord">
                                                <tr>
                                                    <td>
                                                        <c:out value="${borrowRecord.reader.email}"/>
                                                    </td>
                                                    <td>
                                                        <a href="#" onclick="openEditModalWindow(this)"> <c:out value="${borrowRecord.reader.firstName} ${borrowRecord.reader.lastName}"/></a>
                                                    </td>
                                                    <td>
                                                        <c:out value="${borrowRecord.borrowDate}"/>
                                                    </td>
                                                    <td>
                                                        <c:out value="${borrowRecord.dueDate}"/>
                                                    </td>
                                                    <td>
                                                        <c:out value="${borrowRecord.returnDate}"/>
                                                    </td>
                                                        <input type="hidden" value="${borrowRecord.id}" name="record_id">
                                                        <input type="hidden" value="${borrowRecord.reader.email}" name="record_email">
                                                        <input type="hidden" value="${borrowRecord.reader.firstName}" name="record_firstName">
                                                        <input type="hidden" value="${borrowRecord.reader.lastName}" name="record_lastName">
                                                        <input type="hidden" value="${borrowRecord.borrowDate}" name="record_borrowDate">
                                                        <input type="hidden" value="${borrowRecord.dueDate}" name="record_dueDate">
                                                        <input type="hidden" value="${borrowRecord.returnDate}" name="record_returnDate">
                                                        <input type="hidden" name="record_comment" value="${borrowRecord.comment}">
                                                        <input type="hidden" name="record_returnStatus" value="${borrowRecord.status}">
                                                        <input type="hidden" name="record_timePeriod" value="${borrowRecord.timePeriod}">
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <input type="button" id="showAddBorrowerModal" class="btn btn-outline-primary" value="Add reader">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-first col-6">
                                        <c:choose>
                                            <c:when test="${book.id != null}">
                                                <button type="submit" class="btn btn-success mt-3" onclick="return saveBookChanges()"
                                                        style="float: right">Save
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-success mt-3" onclick="return saveBookChanges()"
                                                        style="float: right">Add
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-last col-6">
                                        <form method="get" action="/books/${book.id}">
                                            <button type="submit" class="btn btn-danger mt-3">Discard</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form>

<script src="../resources/js/bookCoverChange.js"></script>
<script src="../resources/js/addBookGenre.js"></script>
<script src="../resources/js/deleteBookGenre.js"></script>
<script src="../resources/js/openAuthorModalWindow.js"></script>
<script src="../resources/js/addAuthor.js"></script>
<script src="../resources/js/saveBookChanges.js"></script>
<script src="../resources/js/addBorrowModalWindow.js"></script>
<script src="../resources/js/editBorrowModalWindow.js"></script>
<script src="../resources/js/bookStatusListeners.js"></script>
<script src="../resources/js/moment.js"></script>
</body>
</html>
