<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Readers</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA==" crossorigin="anonymous">
</head>
<body>
<c:import url="navbar.jsp"/>
<c:import url="reader-modal.jsp"></c:import>
<div class="container mt-5">

  <table class="table table-bordered table-hover" id="readersTable">
    <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Registration date</th>
      <th scope="col">Phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${readers}" var="reader">
      <tr onclick="openReaderModal(this)">
          <input type="hidden" name="reader_id" value="${reader.id}">
          <input type="hidden" name="reader_firstName" value="${reader.firstName}">
          <input type="hidden" name="reader_lastName" value="${reader.lastName}">
          <input type="hidden" name="reader_email" value="${reader.email}">
          <input type="hidden" name="reader_registrationDate" value="${reader.registrationDate}">
          <input type="hidden" name="reader_phone" value="${reader.phone}">
          <input type="hidden" name="reader_gender" value="${reader.gender}">
        <td>
          <c:out value="${reader.firstName} ${reader.lastName}"/>
        </td>
        <td>
            <c:out value="${reader.email}"/>
        </td>
        <td>
          <c:out value="${reader.registrationDate}"/>
        </td>
        <td>
            ${reader.phone}
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="row">
    <div class="col" style="max-width: 20%">
        <button class="btn btn-outline-primary" onclick="openReaderModal(this)">Add book</button>
    </div>
    <div class="col">
      <nav aria-label="Page navigation example">
        <ul class="pagination" style="margin-left: 25%">
          <li class="page-item">
            <a class="page-link" href="#" id="prevPage"  aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
              <span class="sr-only">Previous</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" id="firstPage" href="#">1</a></li>
          <li class="page-item"><a class="page-link" id="secondPagePage" href="#">2</a></li>
          <li class="page-item"><a class="page-link" id="thirdPage" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" id="nextPage" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
              <span class="sr-only">Next</span>
            </a>
          </li>
        </ul>
        <input type="hidden" id="readersAmount" value="${readersAmount}">
      </nav>
    </div>
  </div>

</div>
<script src="./../resources/js/readerModalWindow.js"></script>
<script src="../resources/js/moment.js"></script>
</body>
</html>
