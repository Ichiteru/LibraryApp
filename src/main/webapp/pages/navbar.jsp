<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.10.2021
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <a class="navbar-brand" href="#">Library</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/books">Books
<%--          <span class="sr-only">(current)</span>--%>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/readers">Readers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/search/books">Search</a>
      </li>
    </ul>
  </div>
</nav>
