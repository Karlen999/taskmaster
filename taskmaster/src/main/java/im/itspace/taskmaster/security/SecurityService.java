package im.itspace.taskmaster.security;

import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityService {

    public static CurrentUser getCurrentUser(){
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getCurrentUserEmail(){
        return getCurrentUser().getUsername();
    }
}
