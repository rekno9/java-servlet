import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(
        name = "prekesPaieskaServlet",
        urlPatterns = "/PrekesPaieskaServlet"
)

public class PrekesPaieskaServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String ieskomaPrekeIdStr = req.getParameter("id");
        int ieskomaPrekeId = Integer.parseInt(ieskomaPrekeIdStr);
        System.out.println("");

        //cookies!
        String cName = "prekesId";
        String cValue = String.valueOf(ieskomaPrekeId);
        Cookie cookiePrekesId = new Cookie(cName, cValue);
        resp.addCookie(cookiePrekesId);

        try{
        Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();
        String sqlGautiPreke = "SELECT * FROM prekes WHERE id=?";

            PreparedStatement prepSt = jungtis.prepareStatement(sqlGautiPreke);
            prepSt.setInt(1, ieskomaPrekeId);

            ResultSet rezultai = prepSt.executeQuery();

            while(rezultai.next()){
                String prekesPav = rezultai.getString("pav");
                double prekesKaina = rezultai.getDouble("kaina");
                int prekesKiekis = rezultai.getInt("kiekis");
                String prekesAprasas = rezultai.getString("aprasas");

//                out.println(prekesPav);
//                out.println(prekesKaina);
//                out.println(prekesKiekis);
//                out.println(prekesAprasas);

                req.setAttribute("pav", prekesPav);
                req.setAttribute("kaina", prekesKaina);
                req.setAttribute("kiekis", prekesKiekis);
                req.setAttribute("aprasas", prekesAprasas);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
