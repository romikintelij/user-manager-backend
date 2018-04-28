package zab.romik.usermanagement.security;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usermanagement.users.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Component
public class Sha256PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String in) {
        return Hashing.sha256()
                .hashString(in, StandardCharsets.UTF_8)
                .toString();
    }
}
