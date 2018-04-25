package zab.romik.usermanagement.inversioncontrol;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.inversioncontrol.PasswordEncrypter;

@Component
public class PasswordEncrypterImpl implements PasswordEncrypter {
    @Override
    public String encode(CharSequence in) {
        // without encoding
        return in.toString();
    }
}
