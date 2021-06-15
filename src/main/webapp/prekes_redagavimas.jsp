<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Klases.PrekesKlase;"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <!-- Default imports -->
    <link rel="stylesheet" href="styles.css" >
    <script src="main.js"></script>
    <title>Hello, world!</title>
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    

    <%
        ArrayList<PrekesKlase> prekes = (ArrayList<PrekesKlase>) request.getAttribute("prekesVisos");
        String preke = prekes.get(0).getP_pav();
        String option1 = "<option value='";
        String option2 = "'>";
        String option3 = "</option>";
    %>


    <form method="POST" action="PrekesPaieskaServlet">
        <label for="id">Pasirinkite Preke</label>
        <select id="id" name="id" required>
            <%
                for(int i=0; i< prekes.size();i++){
                    out.write(option1
                    +prekes.get(i).getP_id()
                    +option2+prekes.get(i).getP_pav()
                    +option3);
                }
            %>
        </select>
        <br>
        <input type="submit" value="Redaguoti">


    </form>


</body>
</html>