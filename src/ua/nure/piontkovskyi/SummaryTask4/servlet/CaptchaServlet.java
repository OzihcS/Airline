package ua.nure.piontkovskyi.SummaryTask4.servlet;

import nl.captcha.Captcha;
import ua.nure.piontkovskyi.SummaryTask4.util.CaptchaUtil;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Captcha servlet which create captcha and put it on response
 */
@WebServlet(urlPatterns = Constants.ServletPaths.CAPTCHA)
public class CaptchaServlet extends BaseServlet {

    String sImgType = null;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        sImgType = servletConfig.getInitParameter("ImageType");
        sImgType = sImgType == null ? "png" : sImgType.trim().toLowerCase();
        if (!sImgType.equalsIgnoreCase("png") && !sImgType.equalsIgnoreCase("jpg") &&
                !sImgType.equalsIgnoreCase("jpeg")) {
            sImgType = "png";
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        byte[] captchaBytes;

        Captcha captcha = CaptchaUtil.getCaptcha();

        request.getSession().setAttribute(Constants.Attributes.CAPTCHA, captcha);
        ImageIO.write(captcha.getImage(), sImgType, imgOutputStream);
        captchaBytes = imgOutputStream.toByteArray();

        // Set appropriate http headers.
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/" + (sImgType.equalsIgnoreCase("png") ? "png" : "jpeg"));

        // Write the image to the client.
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(captchaBytes);
        outStream.flush();
        outStream.close();
    }

}
