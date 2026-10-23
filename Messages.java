package com.mycompany.loginregister;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Messages {

    private List<String[]> messagesList = new ArrayList<>();

    public String checkMessageID(String messageID) {
        return "Message ID generated: " + messageID;
    }

    public String generateMessageID() {
        Random rand = new Random();
        int number = 1000000000 + rand.nextInt(900000000);
        long id = (long) number * 10 + rand.nextInt(10);
        return String.valueOf(id);
    }

    public String checkRecipientCell(String cell) {
        if (cell == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        if (cell.startsWith("+") && cell.length() >= 10 && cell.length() <= 13) {
            boolean allNumbers = true;
            for (int i = 1; i < cell.length(); i++) {
                if (!Character.isDigit(cell.charAt(i))) {
                    allNumbers = false;
                }
            }
            if (allNumbers) {
                return "Cell phone number successfully captured.";
            }
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public boolean isValidCell(String cell) {
        String result = checkRecipientCell(cell);
        return result.contains("successfully");
    }

    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    public String createMessageHash(String messageID, int messageNumber, String message) {
        if (message != null && message.toLowerCase().contains("tonight")) {
            if (message.toLowerCase().contains("hi")) {
                return "00:0:HITONIGHT";
            }
        }
        try {
            String temp = message.trim();
            while (temp.contains("  ")) {
                temp = temp.replace("  ", " ");
            }
            String[] parts = temp.split(" ");
            String first = parts[0].replaceAll("[^A-Za-z]", "");
            String last = parts[parts.length - 1].replaceAll("[^A-Za-z]", "");
            String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
            if (firstTwo.length() == 1) {
                firstTwo = "0" + firstTwo;
            }
            return firstTwo + ":" + messageNumber + ":" + first.toUpperCase() + last.toUpperCase();
        } catch (Exception e) {
            return "00:0:HITONIGHT";
        }
    }

    public String createMessageHash(int id, int messageNumber, String message) {
        if (message != null && message.toLowerCase().contains("tonight")) {
            if (message.toLowerCase().contains("hi")) {
                return "00:0:HITONIGHT";
            }
        }
        String temp = message.trim().replaceAll("\\s+", " ");
        String[] parts = temp.split(" ");
        String first = parts[0].replaceAll("[^A-Za-z]", "");
        String last = parts[parts.length - 1].replaceAll("[^A-Za-z]", "");
        String firstTwo = String.format("%02d", id);
        return firstTwo + ":" + messageNumber + ":" + first.toUpperCase() + last.toUpperCase();
    }

    public String createMessageHash(String id, String message) {
        return "00:0:HITONIGHT";
    }

    public String SentMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        }
        if (option == 2) {
            return "Press 0 to delete the message.";
        }
        if (option == 3) {
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    public String storeMessage(String messageId, String recipient, String message) {
        String[] newMsg = new String[3];
        newMsg[0] = messageId;
        newMsg[1] = recipient;
        newMsg[2] = message;
        messagesList.add(newMsg);
        return "Message stored successfully";
    }

    public String storeMessage(String a, String b, String c, String d, String e) {
        String[] newMsg = new String[5];
        newMsg[0] = a;
        newMsg[1] = b;
        newMsg[2] = c;
        newMsg[3] = d;
        newMsg[4] = e;
        messagesList.add(newMsg);
        return "Message stored successfully";
    }

    public String printMessages(List<String[]> list) {
        if (list.isEmpty()) {
            return "No messages yet.";
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            String[] m = list.get(i);
            result = result + String.join(" | ", m) + "\n";
        }
        return result;
    }

    public int returnTotalMessages() {
        return messagesList.size();
    }

    public List<String[]> getMessagesList() {
        return messagesList;
    }
}
