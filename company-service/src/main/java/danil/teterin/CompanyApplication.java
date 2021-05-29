package danil.teterin;


import danil.teterin.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class CompanyApplication {

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    private static String[] name = new String[]{"Microsoft", "Nasa", "Yandex"};
    private static String[] address = new String[]{"Samara", "Moscow"};
    private static String[] country = new String[]{"USA", "Russia"};

    public static void main(String[] args) {
        CompanyService companyService = SpringApplication.run(CompanyApplication.class, args)
                .getBean(CompanyService.class);

    }


}
