package BUS;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageHandler {
    public String setBannerURL(String sourceBannerURL, String currentBannerURL) {
        return copyImage(sourceBannerURL);
    }

    public String copyImage(String sourceBannerURL) {
        String projectBannerDir = "src/icon/game";
        try {
            Path sourcePath = Paths.get(sourceBannerURL);
            Path destinationPath = Paths.get(projectBannerDir + "/" + sourcePath.getFileName());
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return destinationPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
