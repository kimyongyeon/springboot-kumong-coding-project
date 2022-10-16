package kyy.springbootkumongcodingproject.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String fileName;
    private String uuid;

    private String folderPath;

    private String imgageURL;

    public UploadResultDTO(String fileName, String uuid, String folderPath) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.folderPath = folderPath;
        this.imgageURL = getImageURL();
    }

    private String getImageURL() {
        try {
            return URLEncoder.encode(this.folderPath + File.separator + this.uuid + "_" + this.fileName, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
