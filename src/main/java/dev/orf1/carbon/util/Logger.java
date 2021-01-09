package dev.orf1.carbon.util;

public final class Logger {

    public static void connectionLog(String data, String message) {
        System.out.println("New Connection!");
        System.out.println("Data: " + data);
        System.out.println("Message: " + message);
    }

    public static void errorLog(String errorMessage) {
        System.out.println("[ERROR] There has been an error! ");
        System.out.println("[ERROR] Error Message: " + errorMessage);
    }
}
