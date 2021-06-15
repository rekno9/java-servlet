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
        name = "prekesRedagavimoServlet",
        urlPatterns = "/PrekesRedagavimoServlet"
)


public class PrekesRedagavimoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        //nusiskaitome is HTML formos reiksmes
        String ivestasPav = req.getParameter("pav");
        String ivestaKainaStr = req.getParameter("kaina");
        String ivestasKiekisStr = req.getParameter("kiekis");
        String ivestasAprasas = req.getParameter("aprasas");
        String ivestasIdStr = req.getParameter("id");

        double ivestaKaina;
        int ivestasKiekis;

        int ivestasId;

        //Jeigu ID nera tuscias tada konvertuoti i skiaciu ir pradeti prisjungima prie duomenu bazes
        if(!ivestasIdStr.isEmpty()){
            ivestasId = Integer.parseInt(ivestasIdStr);
            try{
//                //Konvertuojam tekstines reiksmes i skaitines kur reikia
//                if(!ivestaKainaStr.isEmpty()){
//                    double ivestaKaina = Double.parseDouble(ivestaKainaStr);
//                }
//

                //sukuriame prisijungima i duombaze
                Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

                //redaguojamos prekes gavimas is duomenu bazes
                String sqlGautiPreke = "SELECT * FROM prekes WHERE id=?";

                //sakynio paruosimas
                PreparedStatement prepSt = jungtis.prepareStatement(sqlGautiPreke);
                prepSt.setInt(1, ivestasId);

                //sakinio ivykdymas ir grazinimas i ResultSet
                ResultSet rezultatai = prepSt.executeQuery();

                //kol yra eiluciu gautu
                while(rezultatai.next()){
                    String orgPav = rezultatai.getString("pav");
                    double orgKaina = rezultatai.getDouble("kaina");
                    int orgKiekis = rezultatai.getInt("kiekis");
                    String orgAprasas = rezultatai.getString("aprasas");


                    //Tikriname jeigu kuris laukelis butu tuscias tada paliekame orginalia reiksme
                    if(ivestasPav.isEmpty()){
                        ivestasPav = orgPav;
                    }

                    if(ivestaKainaStr.isEmpty()){
                        ivestaKaina = orgKaina;
                    }else{
                        ivestaKaina = Double.parseDouble(ivestaKainaStr);
                    }

                    if(ivestasKiekisStr.isEmpty()){
                        ivestasKiekis = orgKiekis;
                    }else{
                        ivestasKiekis = Integer.parseInt(ivestasKiekisStr);
                    }

                    if(ivestasAprasas.isEmpty()){
                        ivestasAprasas = orgAprasas;
                    }

                    out.println("--------------------------------------------");
                    out.println(orgPav + " " + orgKaina + " " + orgKiekis + " " + orgAprasas);
                    out.println("--------------------------------------------");
                    //testuojam ar veikia ivedimas/gavimas
                    out.println(ivestasPav);
                    out.println(ivestaKaina);
                    out.println(ivestasKiekis);
                    out.println(ivestasAprasas);
                    out.println(ivestasId);

                    //Atnaujinti preke
                    String sqlAtnaujintiPreke = "UPDATE prekes SET pav=?, kaina=?, kiekis=?, aprasas=? WHERE id=?";
                    prepSt = jungtis.prepareStatement(sqlAtnaujintiPreke);
                    prepSt.setString(1, ivestasPav);
                    prepSt.setDouble(2, ivestaKaina);
                    prepSt.setInt(3, ivestasKiekis);
                    prepSt.setString(4, ivestasAprasas);
                    prepSt.setInt(5, ivestasId);

                    prepSt.executeUpdate();
                    out.println("Duombaze atnaujinta");
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            out.println("Butina ivesti prekes ID");
        }
    }
}
