import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(
        name = "registracijosServlet",
        urlPatterns = "/RegistracijosServlet"
)

public class RegistracijosServlet extends HttpServlet {
    private Object SQLException;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        //Nuskaitome is registracijos formos duomenis
        String ivestasName = req.getParameter("name");
        String ivestasPsw = req.getParameter("psw");
        String ivestasEmail = req.getParameter("email");
        String ivestasVardas = req.getParameter("vardas");
        String ivestasPavarde = req.getParameter("pavarde");

        try{
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

            String sqlRegistruotiVartotoja = "INSERT INTO vartotojai (name, psw, email, vardas, pavarde) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement prepSt = jungtis.prepareStatement(sqlRegistruotiVartotoja);
            prepSt.setString(1, ivestasName);
            prepSt.setString(2, ivestasPsw);
            prepSt.setString(3, ivestasEmail);
            prepSt.setString(4, ivestasVardas);
            prepSt.setString(5, ivestasPavarde);

            prepSt.executeUpdate();

            out.println("Registracija sekminga");

        }
        catch (SQLIntegrityConstraintViolationException ei){
            out.println("Toks vartotojo vardas jau yra");
            ei.printStackTrace();
        }
        catch (SQLException e) {
//            out.println("e.getSQLState()-> " + e.getSQLState());
//            out.println("e.getMessage()-> " + e.getMessage());
//            out.println("e.getErrorCode()-> " + e.getErrorCode());


            //Duplicate entry error code = 1062
            if (e.getErrorCode() == DuombazesSukurimas.sqlDublicateEntryErrCode) {
                out.println("Toks vartotojo vardas jau yra");

            }else{
                e.printStackTrace();
            }
        }

    }



}

