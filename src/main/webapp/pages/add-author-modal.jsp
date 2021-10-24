<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.10.2021
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="locModal" tabindex="-1" role="dialog" aria-labelledby="locModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="locModalLabel">New author</h5>

            </div>
            <div class="modal-body">
                <form action="" id="w-form">
                    <div class="form-group">
                        <label for="firstName"> Name </label>
                        <input pattern="^[a-z](?!.* {2})[ \w.-]{2,24}$" type="text" class="form-control" id="firstName">
                    </div>
                    <div class="form-group">
                        <label for="lastName"> Surname </label>
                        <input pattern="^[a-z](?!.* {2})[ \w.-]{2,24}$" type="text" class="form-control" id="lastName">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="w-change-close" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="btn-save" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>