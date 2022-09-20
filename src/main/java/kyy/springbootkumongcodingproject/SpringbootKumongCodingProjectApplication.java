package kyy.springbootkumongcodingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableJpaAuditing // AuditionEntityListner 활성화를 위해 추가
public class SpringbootKumongCodingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKumongCodingProjectApplication.class, args);
    }

}
