package ua.nure.piontkovskyi.SummaryTask4.util;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CaptchaUtil {

    public static Captcha getCaptcha() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.black);
        colors.add(Color.red);

        java.util.List<Font> fonts = new ArrayList<>();
        fonts.add(new Font("Arial", Font.ITALIC, 40));

        nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(120, 50)
                .addText(new DefaultWordRenderer(colors, fonts))
                .addBackground(new GradiatedBackgroundProducer(Color.white, Color.white))
                .gimp()
                .addBorder()
                .build();

        return captcha;
    }
}
