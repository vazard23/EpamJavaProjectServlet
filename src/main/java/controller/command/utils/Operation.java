package controller.command.utils;

public interface Operation {
    String LOGIN = "/view/login";
    String REGISTRATION = "/view/registration";
    String LOGOUT = "/view/logout";
    String ADMIN_MENU = "/view/admin/adminPage";
    String PERSON_MENU = "/view/personPage";
    String MAIN_MENU = "/view/mainPage";
    String DEPOSIT = "/view/deposit";
    String OFFER_PERSON_LIST = "/view/offerListPerson";
    String CHECK_OFFER_ACCEPT = "/view/checkOfferAccept";
    String ADD_NEW_OFFER = "/view/admin/addOffer";
    String ADD_NEW_PERSON = "/view/admin/addPerson";
    String ADMIN_CHANGE_OFFER = "/view/admin/adminChangeOffer";
    String ADMIN_CHANGE_OFFER_FORM = "/view/admin/offerChangeForm";
    String ADMIN_OFFER_DELETE = "/view/admin/offerDelete";
    String ADMIN_USER_PAGE = "/view/admin/adminUserPage";
    String BLOCKED_PAGE = "/view/blockedPage";
}
