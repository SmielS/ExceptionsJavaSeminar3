package org.example.view;

import org.example.controller.UserController;
import org.example.exception.DateFormatException;
import org.example.model.User;

import java.time.LocalDate;
import java.util.Scanner;

public class ViewUser {
    private static final byte NUMBER_OF_INPUT_DATA_FIELDS = 6;
    private static final byte NUMBER_OF_DATE_DATA_FIELDS = 3;
    private static final byte DAY_FIELD_SIZE = 2;
    private static final byte MONTH_FIELD_SIZE = 2;
    private static final byte YEAR_FIELD_SIZE = 4;
    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        while (true) {
            System.out.println(("Input format:\n")+
            ("FullName BirthDate(dd.mm.yyyy) PhoneNumber Sex(f/m)\n"));
            Scanner iScanner = new Scanner(System.in);
            System.out.println("Enter user details:");
            String inputString = iScanner.nextLine();
            String[] inputData = inputString.split(" ");
            int errorCode = checkInputString(inputData);
            if (errorCode == -1) {
                System.out.println("Error! The input data is less than it should be!");
            } else if (errorCode == -2) {
                System.out.println("Error! There is more input data than there should be!");
            } else {
                try {
                    this.checkBirthDate(inputData[3]);
                    this.checkPhone(inputData[4]);
                    this.checkSex(inputData[5]);
                    this.userController.saveUser(new User(inputData[0], inputData[1], inputData[2], inputData[3],
                                                     Long.parseLong(inputData[4]), inputData[5].charAt(0)));
                    System.out.println("Add new user? (y/n)?: ");
                    inputString = iScanner.nextLine();
                    if (!inputString.equalsIgnoreCase("y")) {
                        return;
                    }
                } catch (DateFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public Integer checkInputString(String[] inputData) {
        int errorCode = 0;
        if (inputData.length < NUMBER_OF_INPUT_DATA_FIELDS) {
            errorCode = -1;
        } else if (inputData.length > NUMBER_OF_INPUT_DATA_FIELDS) {
            errorCode = -2;
        }
        return errorCode;
    }

    public void checkBirthDate(String birthDateString) throws DateFormatException {
        String[] birthDateData = birthDateString.split("\\.");
        if (birthDateData.length != NUMBER_OF_DATE_DATA_FIELDS) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): Number of fields less then 3!");
        }
        checkDay(birthDateData[0]);
        checkMonth(birthDateData[1]);
        checkYear(birthDateData[2]);
    }

    public void checkPhone(String phone) throws DateFormatException {
        if (!isNumeric(phone)) {
            throw new DateFormatException("Error! Invalid phone number format");
        }
    }

    public void checkSex(String sex) throws DateFormatException {
        if (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
            throw new DateFormatException("Error! Sex is only 'f' or 'm'");
        }
    }

    public void checkDay(String day) throws DateFormatException {
        if (day.length() != DAY_FIELD_SIZE) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains day (dd) must include 2 digits!");
        }
        if (!isNumeric(day)) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains day (dd) must be a number!");
        }
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains day (dd) must be from 01 to 31");
        }
    }
    public void checkMonth(String month) throws DateFormatException {
        if (month.length() != MONTH_FIELD_SIZE) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains month (mm) must include 2 digits!");
        }
        if (!isNumeric(month)) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): поле месяц (mm) must be a number!");
        }
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): поле месяц (mm) must be from 01 to 12");
        }
    }

    public void checkYear(String year) throws DateFormatException {
        if (year.length() != YEAR_FIELD_SIZE) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy):  field that contains year (yyyy) must include 4 digits!");
        }
        if (!isNumeric(year)) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains year (yyyy) must be a number!");
        }
        LocalDate localDate = LocalDate.now();
        if (Integer.parseInt(year) > localDate.getYear()) {
            throw new DateFormatException("Error! Invalid date format (dd.mm.yyyy): field that contains year (yyyy) must be from 0000 до " + localDate.getYear());
        }
    }

    public Boolean isNumeric(String str) {
        for (char symbol : str.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }
}
