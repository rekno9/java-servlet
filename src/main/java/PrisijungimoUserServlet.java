import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        name = "prisijungimoUserServlet",
        urlPatterns = "/PrisijungimoUserServlet")

public class PrisijungimoUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String ivestasName = req.getParameter("name");
        String ivestasPsw = req.getParameter("psw");

        try{
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();
            String sqlUzklausa = "SELECT psw FROM vartotojai WHERE name=?";

            PreparedStatement prepSt = jungtis.prepareStatement(sqlUzklausa);
            prepSt.setString(1, ivestasName);

            ResultSet rezultatai = prepSt.executeQuery();

            int kiekEiluciu = 0;
            while(rezultatai.next()){
                kiekEiluciu++;
                String gautasPsw = rezultatai.getString("psw");
                if(ivestasPsw.equals(gautasPsw)){
                    resp.sendRedirect("vartotojo_dashboard.html");
                }else{
                    out.println("Neteisingai ivestas slaptazodis");
                }
            }
            if(kiekEiluciu == 0){
                out.println("Tokio vartotojo nera");
                out.println(ivestasName);
                out.println(ivestasPsw);
            }

        }catch (SQLException e){
            out.println("Nepavyko prisijungti");
            out.println(e.getMessage());
        }
    }
}
