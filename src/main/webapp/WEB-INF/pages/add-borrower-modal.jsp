<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.10.2021
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="borrowModal" tabindex="-1" role="dialog" aria-labelledby="locModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="locModalLabel">New borrower</h5>

            </div>
            <div class="modal-body">
                <form action="" id="addBorrowerForm">
                    <div class="form-group">
                        <label for="dropdownEmailSearch"> Email </label>
                        <input required oninput="alert('change')" type="text" class="form-control"  id="dropdownEmailSearch" data-toggle="dropdown">
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" id="dropdownEmailSelect">
                            <a class="dropdown-item" name="borrowerEmail" href="#">test</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="borrowerName"> Name Surname </label>
                        <input required type="text" class="form-control" id="borrowerName">
                    </div>

                    <div class="form-group">
                        <label for="borrowDate"> Borrow date </label>
                        <input readonly type="date" class="form-control" id="borrowDate">
                    </div>

                    <div class="form-group">
                        <label for="dropdownTimePeriod"> Time period </label></br>
                            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownTimePeriod" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Time period
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="timePeriodArray" var="timePeriod">
                                    <a class="dropdown-item" href="#">${timePeriod}</a>
                                </c:forEach>
                            </div>
                    </div>
                    <div class="form-group">
                        <label for="comment"> Comment </label>
                        <textarea class="form-control" id="comment"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="w-change-close-borrow-modal-window" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="btn-save-borrower" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>