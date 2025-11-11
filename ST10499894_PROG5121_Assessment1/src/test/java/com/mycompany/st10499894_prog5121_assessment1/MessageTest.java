/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10499894_prog5121_assessment1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ethan
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkMessageLength method, of class Message.
     */
    @Test
    public void testCheckMessageLength() {
        System.out.println("checkMessageLength");
        String message = "";
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageLength(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        String messageID = "";
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cellNumber = "";
        Message instance = new Message();
        int expResult = 0;
        int result = instance.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageID = "";
        int messageNum = 0;
        String message = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.createMessageHash(messageID, messageNum, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class Message.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String recipient = "";
        String message = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.sendMessage(recipient, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processMessage method, of class Message.
     */
    @Test
    public void testProcessMessage() {
        System.out.println("processMessage");
        String recipient = "";
        String message = "";
        int choice = 0;
        Message instance = new Message();
        String expResult = "";
        String result = instance.processMessage(recipient, message, choice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySenderRecipient method, of class Message.
     */
    @Test
    public void testDisplaySenderRecipient() {
        System.out.println("displaySenderRecipient");
        Message instance = new Message();
        String expResult = "";
        String result = instance.displaySenderRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLongestMessage method, of class Message.
     */
    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        Message instance = new Message();
        String expResult = "";
        String result = instance.displayLongestMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessageByID method, of class Message.
     */
    @Test
    public void testSearchMessageByID() {
        System.out.println("searchMessageByID");
        String messageID = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.searchMessageByID(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessagesByRecipient method, of class Message.
     */
    @Test
    public void testSearchMessagesByRecipient() {
        System.out.println("searchMessagesByRecipient");
        String recipient = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.searchMessagesByRecipient(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMessageByHash method, of class Message.
     */
    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        String messageHash = "";
        Message instance = new Message();
        String expResult = "";
        String result = instance.deleteMessageByHash(messageHash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMessageReport method, of class Message.
     */
    @Test
    public void testDisplayMessageReport() {
        System.out.println("displayMessageReport");
        Message instance = new Message();
        String expResult = "";
        String result = instance.displayMessageReport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayStoredMessages method, of class Message.
     */
    @Test
    public void testDisplayStoredMessages() {
        System.out.println("displayStoredMessages");
        Message instance = new Message();
        String expResult = "";
        String result = instance.displayStoredMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadStoredMessages method, of class Message.
     */
    @Test
    public void testLoadStoredMessages() {
        System.out.println("loadStoredMessages");
        Message instance = new Message();
        String expResult = "";
        String result = instance.loadStoredMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class Message.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Message instance = new Message();
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessages method, of class Message.
     */
    @Test
    public void testGetSentMessages() {
        System.out.println("getSentMessages");
        Message instance = new Message();
        String[] expResult = null;
        String[] result = instance.getSentMessages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedMessages method, of class Message.
     */
    @Test
    public void testGetDisregardedMessages() {
        System.out.println("getDisregardedMessages");
        Message instance = new Message();
        String[] expResult = null;
        String[] result = instance.getDisregardedMessages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredMessages method, of class Message.
     */
    @Test
    public void testGetStoredMessages() {
        System.out.println("getStoredMessages");
        Message instance = new Message();
        String[] expResult = null;
        String[] result = instance.getStoredMessages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentCount method, of class Message.
     */
    @Test
    public void testGetSentCount() {
        System.out.println("getSentCount");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getSentCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisregardedCount method, of class Message.
     */
    @Test
    public void testGetDisregardedCount() {
        System.out.println("getDisregardedCount");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getDisregardedCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredCount method, of class Message.
     */
    @Test
    public void testGetStoredCount() {
        System.out.println("getStoredCount");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getStoredCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
