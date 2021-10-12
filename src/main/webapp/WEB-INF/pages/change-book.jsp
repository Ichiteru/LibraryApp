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
    <title></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"
          integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA=="
          crossorigin="anonymous">

</head>
<body>
<c:set var="book" value="${book}"></c:set>
<c:import url="navbar.jsp"/>
<form>
    <div class="container" style="padding: 20px">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img src="#" id="bookCover" style="width: 150px; height: 150px" class="img-thumbnail"/>
                                    <div class="middle" style="width: 160px; flex: 0 0 auto">
                                        <div class="custom-file" style="margin-top: 10px">
                                            <input type="file" onchange="onFileSelected(event)" class="custom-file-input"
                                                   id="inputGroupFile04">
                                            <label class="custom-file-label" for="inputGroupFile04" style="font-size: small">Choose file</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="userData ml-3" style="flex: 1 1 auto">
                                    <input type="text" class="form-control" value="${book.title}"/>
                                    <p class="d-block" style="width: 100%; margin-top: 5px">
                                        <textarea class="form-control" style="width: 100%; height: 80%"><c:out
                                                value="${book.description}"></c:out></textarea>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo"
                                           role="tab" aria-controls="bookInfo" aria-selected="true">Book Info</a>
                                    </li>
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="bookInfo" role="tabpanel"
                                         aria-labelledby="basicInfo-tab">


                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Author(s)</label>
                                            </div>
                                            <div class="col-md-8 col-6" style="margin-left: 15px" id="authorDiv">
                                                <c:forEach items="${book.authors}" var="author">
                                                    <div class="row mt-1">
                                                        <input type="text" class="form-control" name="firstName" value="${author.firstName}"
                                                               style="width: 30%">
                                                        <input type="text" class="form-control" name="secondName" value="${author.lastName}"
                                                               style="width: 30%">
                                                        <button type="button" class="btn btn-danger" >Delete</button><br/>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <button type="button" class="btn btn-success mt-1" style="max-height: 40px">Add</button>
                                        </div>
                                        <hr/>

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Publisher</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <input type="text" class="form-control" value="${book.publisher}"/>
                                            </div>
                                        </div>
                                        <hr/>


                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Publish date</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <input type="date" class="form-control"  value="${book.publishDate}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Genre(-s)</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <c:forEach items="${book.genres}" var="genre">
                                                    <div class="row mt-1">

                                                    </div>
                                                </c:forEach>
                                            </div>
                                                        <button type="button" class="btn btn-danger">Delete</button><br/>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Pages</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <input type="number" class="form-control" value="${book.pageCount}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">ISBN</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <input type="text" class="form-control" value="${book.isbn}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Total amount</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <input type="number" class="form-control" value="${book.totalAmount}"/>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Status</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <c:out value="${book.status}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <%--                                <div class="tab-pane fade" id="borrowersInfo" role="tabpanel" aria-labelledby="ConnectedServices-tab">--%>
                                    <%--                                    Facebook, Google, Twitter Account that are connected to this account--%>
                                    <%--                                </div>--%>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="./../../resources/js/bookCoverChange.js"></script>
</body>
</html>
