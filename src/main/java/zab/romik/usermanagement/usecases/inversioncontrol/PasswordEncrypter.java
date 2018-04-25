package zab.romik.usermanagement.usecases.inversioncontrol;

public interface PasswordEncrypter {
    String encode(CharSequence in);
}
