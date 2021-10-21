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
                        <input required oninput="checkExistingEmail()" type="text" class="form-control" name="email"  id="dropdownEmailSearch" data-toggle="dropdown">
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" id="dropdownEmailSelect">
                            <a class="dropdown-item" name="borrowerEmail" onclick="setReaderName(this)" href="#">test</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="borrowerFirstName"> Name </label>
                        <input required type="text" class="form-control" id="borrowerFirstName">
                    </div>
                    <div class="form-group">
                        <label for="borrowerLastName"> Surname </label>
                        <input required type="text" class="form-control" id="borrowerLastName">
                    </div>

                    <div class="form-group">
<%--                        <label for="borrowDate"> Borrow date </label>--%>
                        <input readonly hidden type="data" class="form-control" id="borrowDate" value="">
                    </div>

                    <div class="form-group">
                        <label for="selectTimePeriod"> Time period </label></br>
                            <select class="form-select" id="selectTimePeriod" size="3" style="width: 100%">
                                <c:forEach items="${timePeriodArray}" var="timePeriod">
                                    <option value="${timePeriod}">${timePeriod} month</option>
                                </c:forEach>
                            </select>
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