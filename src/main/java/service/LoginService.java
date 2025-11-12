package service;

import jakarta.servlet.http.HttpServletRequest;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LoginService {
    Optional<String> getUsermane(HttpServletRequest request);
}
