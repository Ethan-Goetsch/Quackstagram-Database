package application;

import database.Database;

public class CredentialsValidator
{
    public boolean isRequestValid(RegisterUserRequest request)
    {
        return !request.username().isEmpty()
                && !request.password().isEmpty()
                && !request.bio().isEmpty()
                && request.pictureFile() != null;
    }

    public boolean isUsernameValid(String username)
    {
        // valid if no other user exists with the same username
        return Database
                .getUser(username)
                .isEmpty();
    }

    public boolean isCredentialsValid(String username, String password)
    {
        // valid if a user exits with the provided password
            return Database
                .getUser(username)
                .map(user -> user.password().equals(password))
                .orElse(false);

        // For Password Encryption that is not used with the Database since I do not want to manually encrypt the generated data
//        return Database
//                .getUser(username)
//                .map(user -> EncryptionController.validate(password, user.password()))
//                .orElse(false);
    }
}