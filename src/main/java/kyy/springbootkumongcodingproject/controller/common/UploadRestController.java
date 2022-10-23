package kyy.springbootkumongcodingproject.controller.common;

import kyy.springbootkumongcodingproject.dto.common.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.channels.ReadPendingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadRestController {
    @Value("${upload.path}")
    private String uploadPath;

    List<UploadResultDTO> resultDTOList = new ArrayList<>();

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName: ", srcFileName);
            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file: ", file);
            HttpHeaders headers = new HttpHeaders();

            // MIME타입 처리
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    @PostMapping("/uploadFetch")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName : " + fileName);
            String folderPath = makerFolder();
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath);
                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    private String makerFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
