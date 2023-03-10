package org.example.model;

import org.example.exception.FileReadException;

public class UserMapper {
    private static final int MIN_STRING_LENGTH = 18;
    public String map(User user) {
        return String.format("<%s><%s><%s><%s><%d><%c>", user.getLastName(), user.getFirstName(), user.getThirdName(),
                user.getBirthDate(), user.getPhone(), user.getSex());
    }

    public User map(String line) throws FileReadException {
        if (line.length() < MIN_STRING_LENGTH) {
            throw new FileReadException("Error! Invalid data format in the file!");
        }
        line = line.substring(1, line.length() - 1);
        String[] lines = line.split("><");
        return new User(lines[0], lines[1], lines[2], lines[3], Long.parseLong(lines[4]), lines[5].charAt(0));
    }
}
