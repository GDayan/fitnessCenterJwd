package by.epam.webtask.controller.filter.permission;

import java.util.Set;

import static by.epam.webtask.controller.PathPage.*;

public enum PagePermission {
    ADMIN(Set.of(SIGN_PAGE, REGISTRATION_PAGE, GUEST_PAGE, PRICING_PAGE, TRAINER_PAGE)),
    CLIENT(Set.of(SIGN_PAGE, REGISTRATION_PAGE, GUEST_PAGE, PRICING_PAGE, TRAINER_PAGE)),
    TRAINER(Set.of(SIGN_PAGE, REGISTRATION_PAGE, GUEST_PAGE, PRICING_PAGE, TRAINER_PAGE)),
    GUEST(Set.of(SIGN_PAGE, REGISTRATION_PAGE, GUEST_PAGE, PRICING_PAGE, TRAINER_PAGE));

    Set<String> userPages;
    PagePermission(Set<String> userPages){
        this.userPages = userPages;
    }
    public Set<String> getUserPages(){
        return userPages;
    }
}
