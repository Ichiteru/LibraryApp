<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.11.2021
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editRecordModal" tabindex="-1" role="dialog" aria-labelledby="locModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit record</h5>

            </div>
            <div class="modal-body">
                <form action="" id="editBorrowerForm">
                    <div class="form-group">
                        <label for="edit-modal-email"> Email </label>
                        <input readonly type="text" class="form-control" name="email"  id="edit-modal-email" data-toggle="dropdown">
                    </div>
                    <div class="form-group">
                        <label for="edit-modal-first-name" > Name </label>
                        <input readonly type="text" class="form-control" id="edit-modal-first-name">
                    </div>
                    <div class="form-group">
                        <label for="edit-modal-last-name"> Surname </label>
                        <input readonly type="text" class="form-control" id="edit-modal-last-name">
                    </div>
                    <div class="form-group">
                        <label for="edit-modal-borrow-date" > Borrow Date </label>
                        <input readonly type="data" class="form-control" id="edit-modal-borrow-date" value="">
                    </div>
                    <div class="form-group">
                        <label for="edit-modal-time-period"> Time period </label></br>
                        <input readonly type="text" class="form-control" id="edit-modal-time-period">
                    </div>
                    <div class="form-group">
                        <label for="edit-modal-returned-book-status" > Status </label>
                        <select required class="form-control" id="edit-modal-returned-book-status">
                            <c:forEach items="${returnedBookStatus}" var="st">
                                <option value="${st}">${st}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="close-record-edit-modal-window" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="btn-edit-borrower" type="button" class="btn btn-primary">Edit</button>
            </div>
        </div>
    </div>
</div>