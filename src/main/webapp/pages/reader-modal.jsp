<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.11.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="readerModal" tabindex="-1" role="dialog" aria-labelledby="locModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit reader</h5>

            </div>
            <div class="modal-body">
                <form action="/reader/add" id="readerForm" method="post">
                    <input type="hidden" name="id" id="id">
                    <input type="hidden" name="registrationDate" id="registrationDate">
                    <div class="form-group">
                        <label for="firstName"> Name </label>
                        <input pattern="^[a-z](?!.* {2})[ \w.-]{2,24}$" type="text" name="fName" class="form-control" id="firstName">
                    </div>
                    <div class="form-group">
                        <label for="lastName"> Surname </label>
                        <input pattern="^[a-z](?!.* {2})[ \w.-]{2,24}$" type="text" name="lName" class="form-control" id="lastName">
                    </div>
                    <div class="form-group">
                        <label for="email"> Email </label>
                        <input  type="text" class="form-control" name="email" id="email">
                        <input  type="hidden" name="initEmail" id="init_email">
                    </div>
                    <div class="form-group">
                        <label for="phone"> Phone </label>
                        <input  type="text" class="form-control" name="phone" id="phone">
                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender_radio" id="gender_radio1" value="MALE" checked>
                            <label class="form-check-label" for="gender_radio1">
                                Male
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender_radio" id="gender_radio2" value="FEMALE">
                            <label class="form-check-label" for="gender_radio2">
                                Female
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-close-reader-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="btn-save-reader" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
