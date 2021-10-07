<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.10.2021
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA==" crossorigin="anonymous">
<%--    <link href="./../../resources/book-profile.css" rel="stylesheet">--%>
</head>
<body>
<c:set var="book" value="${book}"></c:set>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card">

                <div class="card-body">
                    <div class="card-title mb-4">
                        <div class="d-flex justify-content-start">
                            <div class="image-container">
                                <img src="#" id="bookCover" style="width: 150px; height: 150px" class="img-thumbnail" />
                                <div class="middle" style="width: 160px; flex: 0 0 auto">
<%--                                    <input type="button" class="btn btn-secondary" id="btnChangePicture" value="Change" />--%>
                                    <input type="file" onchange="onFileSelected(event)"/>
                                </div>
                            </div>
                            <div class="userData ml-3" style="flex: 1 1 auto">
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold"><c:out value="${book.title}"/></h2>
                                <p class="d-block" style="width: 100%"><c:out value="${book.description}"></c:out></p>
                            </div>
<%--                            <div class="ml-auto">--%>
<%--                                <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />--%>
<%--                            </div>--%>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">Book Info</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="connectedServices-tab" data-toggle="tab" href="#connectedServices" role="tab" aria-controls="connectedServices" aria-selected="false">Borrowers</a>
                                </li>
                            </ul>
                            <div class="tab-content ml-1" id="myTabContent">
                                <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">


                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Author(s)</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:forEach items="${book.authors}" var="author">
                                                <c:out value="${author.firstName} ${author.lastName};"/><br/>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <hr />

                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Publisher</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.publisher}"/>
                                        </div>
                                    </div>
                                    <hr />


                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Publish date</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.publishDate}"/>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Genre(-s)</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:forEach items="${book.genres}" var="genre">
                                                <c:out value="${genre.name}; "/>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Pages</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.pageCount}"/>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">ISBN</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.isbn}"/>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Total amount</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.totalAmount}"/>
                                        </div>
                                    </div>
                                    <hr />
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Status</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <c:out value="${book.status}"/>
                                        </div>
                                    </div>
                                    <hr />
                                </div>
                                <div class="tab-pane fade" id="connectedServices" role="tabpanel" aria-labelledby="ConnectedServices-tab">
                                    Facebook, Google, Twitter Account that are connected to this account
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
        </div>
    </div>
</div>

<script src="./../../resources/js/bookCoverChange.js"></script>
</body>
</html>
