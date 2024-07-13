package org.app.utils.access;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Icons {

    public static BufferedImage editUserBTN() throws IOException {
        InputStream editBTN = Icons.class.getResourceAsStream("/icons/edit-user.jpg");
        if (editBTN == null) {
            throw new IllegalArgumentException("Edit User ButtonIcon File not found!");
        }

        return ImageIO.read(editBTN);
    }
}
