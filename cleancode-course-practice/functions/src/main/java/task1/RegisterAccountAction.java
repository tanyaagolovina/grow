package task1;

import task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private static final int MIN_NAME_LENGTH = 5;
    private static final int MIN_PASSWORD_LENGTH = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;
    private Account account;

    public void register() {
        accountVerification();
        setAccountDetails();
        accountManager.createNewAccount(account);
    }

    private void accountVerification() {
        checkAccountName();
        checkPassword();
    }

    private void setAccountDetails() {
        setCreatedDate();
        setAddressesToAccount();
    }

    private void checkAccountName() {
        if (account.getName().length() <= MIN_NAME_LENGTH){
            throw new WrongAccountNameException();
        }
    }

    private void checkPassword() {
        String password = account.getPassword();
        if (password.length() <= MIN_PASSWORD_LENGTH) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
    }

    private void setCreatedDate() {
        account.setCreatedDate(new Date());
    }

    private void setAddressesToAccount() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }

    public void setAccount(Account account){
        this.account = account;
    }
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

}
