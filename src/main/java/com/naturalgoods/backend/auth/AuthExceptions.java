package com.naturalgoods.backend.auth;

import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Service
public class AuthExceptions extends AuthException {
    public AuthException authExceptions(Language lang) {
        if (lang.equals(Language.EN)) {
            return new AuthException("This user is not registered in the system");
        } else if (lang.equals(Language.RU)) {
            return new AuthException("Данный пользователь не зарегистрирован в системе");
        } else if (lang.equals(Language.KK)) {
            return new AuthException("Бұл пайдаланушы жүйеде тіркелмеген");
        }
        return null;
    }

    public AuthException blackList(Language lang) {
        if (lang.equals(Language.EN)) {
            return new AuthException("You are blacklisted!!!");
        } else if (lang.equals(Language.RU)) {
            return new AuthException("Вы в черном списке!!!");
        } else if (lang.equals(Language.KK)) {
            return new AuthException("Сіз қара тізімдесіз!!!");
        }
        return null;
    }

    public AuthException incorrectPassword(Language lang) {
        if (lang.equals(Language.EN)) {
            return new AuthException("Incorrect password");
        } else if (lang.equals(Language.RU)) {
            return new AuthException("Неверный пароль");
        } else if (lang.equals(Language.KK)) {
            return new AuthException("Қате құпия сөз");
        }
        return null;
    }

    public AuthException alreadyExist(Language lang) {
        if (lang.equals(Language.EN)) {
            return new AuthException("A user with this login is already registered in the system");
        } else if (lang.equals(Language.RU)) {
            return new AuthException("Пользователь с таким логином уже зарегистрирован в системе");
        } else if (lang.equals(Language.KK)) {
            return new AuthException("Бұл логині бар пайдаланушы жүйеде әлдеқашан тіркелген");
        }
        return null;
    }
}
