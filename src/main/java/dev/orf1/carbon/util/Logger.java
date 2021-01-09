package dev.orf1.carbon.util;

public final class Logger {

    public static void connectionLog(String data, String message) {
        System.out.println("New Connection!");
        System.out.println("Data: " + data);
        System.out.println("Message: " + message);
    }
}
