package com.mycompany.loginregister;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    Messages message = new Messages();

    @Test
    public void testRecipientCorrectlyFormatted() {
        String result = message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        String result = message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashTestCase1() {
        // From PDF: Message 1
        String id = "0012345678";
        String msg = "Hi Mike, can you join us for dinner tonight?";
        String result = message.createMessageHash(id, 0, msg);
        assertEquals("00:0:HITONIGHT", result);
    }

    @Test
    public void testMessageIDGenerated() {
        String id = message.generateMessageID();
        String result = message.checkMessageID(id);
        assertTrue(result.startsWith("Message ID generated:"));
    }

    @Test
    public void testMessageLengthSuccess() {
        String result = message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "a".repeat(260);
        String result = message.checkMessageLength(longMsg);
        assertTrue(result.contains("Message exceeds 250 characters by 10"));
    }

    @Test
    public void testSentMessageSend() {
        assertEquals("Message successfully sent.", message.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        assertEquals("Press 0 to delete the message.", message.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        assertEquals("Message successfully stored.", message.SentMessage(3));
    }
}
