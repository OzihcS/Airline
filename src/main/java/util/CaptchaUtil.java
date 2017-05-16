package util;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Captcha util class
 */
public class CaptchaUtil {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 40;

    /**
     * Create new captcha
     *
     * @return Object {@link Captcha}
     */
    public static Captcha getCaptcha() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.black);
        colors.add(Color.red);

        java.util.List<Font> fonts = new ArrayList<>();
        fonts.add(new Font("Arial", Font.ITALIC, FONT_SIZE));

        Captcha captcha = new Captcha.Builder(150, 75)
                .addText()
                .addBackground(new GradiatedBackgroundProducer())
                .addNoise()
                .build();

        return captcha;
    }
}
