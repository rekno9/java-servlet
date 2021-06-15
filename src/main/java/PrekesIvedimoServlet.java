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

@WebServlet(
        name = "prekesIvedimoServlet",
        urlPatterns = "/PrekesIvedimoServlet"
)
public class PrekesIvedimoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        //Nuskaitome Duomenis is IVEDIMO formos
        String ivestasPav = req.getParameter("pav");
        String ivestaKainaStr = req.getParameter("kaina");
        String ivestasKiekisStr = req.getParameter("kiekis");
        String ivestasAprasas = req.getParameter("aprasas");

        //Isankstinis ivestu duomenu konvertavimas i tinkamas reikmes
        double ivestaKaina = Double.parseDouble(ivestaKainaStr);
        int ivestasKiekis = Integer.parseInt(ivestasKiekisStr);


        out.println("--------------------------------------------");
        out.println(ivestasPav + " " + ivestaKainaStr + " " + ivestasKiekisStr + " " + ivestasAprasas);
        out.println("--------------------------------------------");

        try {
            //sukuriamas duombazes prisijungimas
            Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

            //Ivedamos prekes suformulavimas
            String sqlIvestiPreke = "INSERT INTO prekes (pav, kaina, kiekis, aprasas) " +
                    "VALUES (?, ?, ?, ?)";

            //Paruosimas prekes ivedimui
            PreparedStatement prepSt = jungtis.prepareStatement(sqlIvestiPreke);
            prepSt.setString(1, ivestasPav);
            prepSt.setDouble(2, ivestaKaina);
            prepSt.setInt(3, ivestasKiekis);
            prepSt.setString(4, ivestasAprasas);

            prepSt.executeUpdate();
            out.println("Preke sekmingai ivesta");

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
