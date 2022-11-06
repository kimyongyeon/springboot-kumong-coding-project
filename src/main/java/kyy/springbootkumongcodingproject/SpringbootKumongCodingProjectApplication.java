package kyy.springbootkumongcodingproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;


@SpringBootApplication
@EnableJpaAuditing // AuditionEntityListner 활성화를 위해 추가
public class SpringbootKumongCodingProjectApplication implements ApplicationRunner {

    @Autowired
    ApplicationContext context;

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKumongCodingProjectApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment env = context.getEnvironment();
        System.out.println("env ====> " + env);
        System.out.println("SPRING_PROFILES_ACTIVE :: " + environment.getProperty("SPRING_PROFILES_ACTIVE"));
        System.out.println("system env ::: " + System.getenv("SPRING_PROFILES_ACTIVE"));

        //시스템 환경변수 값 전체 가져오기 (key, value 형태)
//        Map<String, String> map = System.getenv();
//        for (Map.Entry <String, String> entry: map.entrySet()) {
//            System.out.println("Variable Name:- " + entry.getKey() + " Value:- " + entry.getValue());
//        }
//
//        //'TEST_ENV_STRING' 이라는 환경변수의 값 가져오기
//        String testString = System.getenv("TEST_ENV_STRING");

    }


}
