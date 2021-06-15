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
        name = "prisijungimoAdminServlet",
        urlPatterns = "/prisijungimoAdminServlet"
)

public class PrisijungimoAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();

        String ivestasLogin = req.getParameter("login");
        String ivestasPsw = req.getParameter("psw");

        try{
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

            String sqlUzklausa = "SELECT psw FROM admin_vartotojai WHERE login=?";

            PreparedStatement preSt = jungtis.prepareStatement(sqlUzklausa);
            preSt.setString(1, ivestasLogin);

            ResultSet rezultatai = preSt.executeQuery();

            int kiekEiluciu = 0;
            while(rezultatai.next()){
                kiekEiluciu++;
                String gautasPsw = rezultatai.getString("psw");
                if(ivestasPsw.equals(gautasPsw)){
                    resp.sendRedirect("admino_dashboard.html");
                }
                else{
                    out.println("Neteisingai ivestas slaptažodis");
                }
            }
            if(kiekEiluciu == 0){
                out.println("Tokio vartotojo nera.");
            }
        }
        catch (SQLException e){
            out.println("Nepavyko prisijungti");
            out.println(e.getMessage());
        }
    }
}