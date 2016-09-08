import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by Volio on 2016/9/8.
 */
public class PasswordGenerate {

    @Test
    public void generatePassword() {
        String password = "admin";
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder("53cr3t");
        System.out.println(passwordEncoder.encode(password));
    }
}
