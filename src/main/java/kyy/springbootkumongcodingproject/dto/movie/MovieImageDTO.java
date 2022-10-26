package kyy.springbootkumongcodingproject.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieImageDTO {
    private String uuid;

    private String imgName;

    private String path;

    private String imageUrl;

    private String thumbnailUrl;

    public String getImageUrl() {
        try {
            return URLEncoder.encode(path + File.separator + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailUrl() {
        try {
            return URLEncoder.encode(path + "_s" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
