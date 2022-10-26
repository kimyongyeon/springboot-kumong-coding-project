package kyy.springbootkumongcodingproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootTest
class SpringbootKumongCodingProjectApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        //         불편하다.
//         이름 작명이 불편하다.
        Date date = new Date(); // timestamp
        long time = date.getTime(); // 날짜에서 시간을 가져온다. 이상하다. 1970년대 기준으로 밀리세콘드를 준다.
        System.out.println(time);

        Thread.sleep(1000 * 5);
        Date after3second = new Date();

        System.out.println(after3second);
        // 멀티쓰레드 환경에서 안전하게 쓰기 어렵다.
        // 쓰레드가 오퍼레이션 할때, 싱크로나이즈 걸고 쓸때는 문제가 없다.
        // T1이 쓰고 있는데 T2가 건들어서 값이 변경될 수 있다.
        after3second.setTime(time); // 시간이 고쳐 진다. mutable 하다. 객체 상태를 바꿀 수 있다.
        System.out.println(after3second);

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        // 타입 세이프 하지 않다.
        Calendar kyyBirthday = new GregorianCalendar(1982, Calendar.APRIL, 10);

//         LocalDate 원칙 : clear, immutable, Fluent, Extensible

//         기계용 시간
//        Date date = new Date();
//        long time = date.getTime();
//        System.out.println(time);
    }

}
