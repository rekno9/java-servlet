import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "papildytiSaskaitaServlet",
        urlPatterns = "/PapildytiSaskaitaServlet"
)

public class PapildytiSaskaitaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String ivestaSumaStr = req.getParameter("sask");





        if(ivestaSumaStr.isEmpty()){
            double ivestaSuma = Double.parseDouble(ivestaSumaStr);
        }

    }
}
