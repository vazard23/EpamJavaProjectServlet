package controller.command.factory;

import controller.command.Command;
import controller.command.*;
import controller.command.adminPack.*;
import controller.command.utils.Operation;
import java.util.HashMap;
import java.util.Map;


public class CommandFactory {

    private static final Map<String, Command> allKnownCommandMap = new HashMap<>();

    static {
        allKnownCommandMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandMap.put(Operation.REGISTRATION, new RegistrationCommand());
        allKnownCommandMap.put(Operation.LOGOUT, new LogoutCommand());
        allKnownCommandMap.put(Operation.ADMIN_MENU, new AdminPageCommand());
        allKnownCommandMap.put(Operation.PERSON_MENU, new PersonPageCommand());
        allKnownCommandMap.put(Operation.MAIN_MENU, new MainPageCommand());
        allKnownCommandMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandMap.put(Operation.OFFER_PERSON_LIST, new OfferListPersonCommand());
        allKnownCommandMap.put(Operation.CHECK_OFFER_ACCEPT, new CheckOfferAcceptCommand());
        allKnownCommandMap.put(Operation.ADD_NEW_OFFER, new AdminAddOfferCommand());
        allKnownCommandMap.put(Operation.ADD_NEW_PERSON, new AdminUserRegisterCommand());
        allKnownCommandMap.put(Operation.ADMIN_CHANGE_OFFER, new AdminOfferChangeCommand());
        allKnownCommandMap.put(Operation.ADMIN_CHANGE_OFFER_FORM, new AdminOfferChangeFormCommand());
    }


    public static Command getCommand(String url) throws Exception {
        Command command = allKnownCommandMap.get(url);

        if (command == null) {
            throw new Exception();
        }

        return command;
    }
}