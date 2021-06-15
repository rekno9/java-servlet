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
        name = "prekesIstrynimoServlet",
        urlPatterns = "/PrekesIstrinimoServlet"
)

public class PrekesIstrinimoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String ivestasIdStr = req.getParameter("id");

        //labas
        if(!ivestasIdStr.isEmpty()){
            try{
                //konvertuojam is STRING tipo i INT tipa
                int ivestasId = Integer.parseInt(ivestasIdStr);

                //Prisijungiame prie duombazes
                Connection jungtis = DuombazesSukurimas.sukurtiDuombaze();

                //Sukuriam vykdymo nurodymus
                String sqlIstrintiPreke = "DELETE FROM prekes WHERE id=?";

                //Surenkam reikiamus duomenis ir vykdom uzklausa
                PreparedStatement prepSt = jungtis.prepareStatement(sqlIstrintiPreke);
                prepSt.setInt(1, ivestasId);
                prepSt.executeUpdate();

                out.println("Preke su ID: " + ivestasId + " istrinta");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            out.println("Id nebuvo ivestas");
        }
    }
}
