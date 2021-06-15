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
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(
        name = "prekesRedagavimoServlet2",
        urlPatterns = "/PrekesRedagavimoServlet2"
)
public class PrekesRedagavimoServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String naujasPav = req.getParameter("pav");
        String naujaKainaStr = req.getParameter("kaina");
        String naujasKiekisStr = req.getParameter("kiekis");
        String naujasAprasas = req.getParameter("aprasas");


        out.println(naujasPav);
        out.println(naujaKainaStr);
        out.println(naujasKiekisStr);
        out.println(naujasAprasas);



        double naujaKaina = Double.parseDouble(naujaKainaStr);
        int naujasKiekis = Integer.parseInt(naujasKiekisStr);
        int prekesId = -1;

        //cookies gavimas
        HashMap<String, String> puslapioCookies = new HashMap<>();


        Cookie[] ck = req.getCookies();
        for (int i = 0; i < ck.length; i++){
            puslapioCookies.put(ck[i].getName(), ck[i].getValue());
//            if(ck[i].getName().equals("prekesId")){
//                prekesId = Integer.parseInt(ck[i].getValue());
//                out.println(prekesId);
//            }
        }

        prekesId = Integer.parseInt(puslapioCookies.get("prekesId"));

        try{
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

            String sqlAtnaujintiPreke = "UPDATE prekes SET pav=?, kaina=?, kiekis=?, aprasas=? WHERE id=?";

            PreparedStatement prepSt = jungtis.prepareStatement(sqlAtnaujintiPreke);
            prepSt.setString(1, naujasPav);
            prepSt.setDouble(2, naujaKaina);
            prepSt.setInt(3, naujasKiekis);
            prepSt.setString(4, naujasAprasas);
            prepSt.setInt(5, prekesId);


            prepSt.executeUpdate();



            prepSt.close();
            jungtis.close();




        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
