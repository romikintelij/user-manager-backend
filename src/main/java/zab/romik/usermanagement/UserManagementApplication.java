package zab.romik.usermanagement;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserManagementApplication.class)
                // disable banner for fast starting
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
