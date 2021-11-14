<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Error</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" integrity="sha512-P5MgMn1jBN01asBgU0z60Qk4QxiXo86+wlFahKrsQf37c9cro517WzVSPPV1tDKzhku2iJ2FVgL67wG03SGnNA==" crossorigin="anonymous">
</head>
<body>

  <div class="page-wrap d-flex flex-row align-items-center" style="padding-top: 200px">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-12 text-center">
          <span id="error-heading"class="display-1 d-block">${heading}</span>
          <div class="mb-4 lead" style="padding-top: 30px">
            <span id="error-message">
              ${message}
            </span>
          </div>
          <a href="/books" class="btn btn-link">Back to Home</a>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
