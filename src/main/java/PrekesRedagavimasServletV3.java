import Klases.PrekesKlase;

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
import java.util.ArrayList;

@WebServlet( name = "prekesRedagavimas3", urlPatterns = "/prekesRedagavimas")
public class PrekesRedagavimasServletV3 extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        //Klases.PrekesKlase prekeVnt = new Klases.PrekesKlase();
        ArrayList<PrekesKlase> prekesVisos = new ArrayList<PrekesKlase>();

        out.println("mes viduje.");

        try{
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();
            String gautiPrekes = "SELECT * FROM prekes";

            PreparedStatement prepSt = jungtis.prepareStatement(gautiPrekes);
            ResultSet gautosPrekes = prepSt.executeQuery();
            int detuvas = 1;

            while(gautosPrekes.next()){
                int id = gautosPrekes.getInt("id");
                String pav = gautosPrekes.getString("pav");
                double kaina = gautosPrekes.getDouble("kaina");
                int kiekis = gautosPrekes.getInt("kiekis");
                String aprasas = gautosPrekes.getString("aprasas");
                //out.println("CIA" + detuvas++ + "CIA");
                //out.println(gautosPrekes.getInt("id"));
                //out.println(gautosPrekes.getString("pav"));
                //out.println(gautosPrekes.getDouble("kaina"));
                //out.println(gautosPrekes.getInt("kiekis"));
                //out.println(gautosPrekes.getString("aprasas"));
                PrekesKlase prekeVnt = new PrekesKlase(id, pav, kaina, kiekis, aprasas);
                prekesVisos.add(prekeVnt);
                out.println("Preke ivesta nr: " + id);
                prekesVisos.get(0).getP_pav();
            }

            req.setAttribute("prekesVisos", prekesVisos);
            req.getRequestDispatcher("prekes_redagavimas.jsp").forward(req, resp);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
