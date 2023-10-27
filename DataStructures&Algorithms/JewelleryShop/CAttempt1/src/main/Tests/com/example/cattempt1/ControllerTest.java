package com.example.cattempt1;

import com.sun.source.tree.AssertTree;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    displayCase cases;
    displayCase cases1;


    @Test
    void addCase() {
        cases = new displayCase(null, 1, "Mounted", false);
        assertTrue(cases.ID == 1);
        assertTrue(cases.toString().contains("Mounted"));
        assertTrue(cases.toString().contains("unlit"));
        assertTrue(cases.next == null);

        cases.next = new displayCase(null, 2, "Freestanding", true);
        assertTrue(cases.next.ID == 2);
        assertTrue(cases.next.toString().contains("Freestanding"));
        assertTrue(cases.next.toString().contains("lit"));
        assertTrue(cases.next.next == null);

        cases.trayHead = new displayTray(null,1,"A","red",111,111);
        cases.trayHead.next = new displayTray(null,2,"B","red",222,222);
        assertTrue(cases.trayHead.getID() == 1);
        assertTrue(cases.trayHead.toString().contains("red"));
        assertTrue(cases.trayHead.getTrayWidth() == 111);
        assertTrue(cases.trayHead.getTrayDepth() == 111);
        assertTrue(cases.trayHead.next != null);

        displayTray tray = cases.trayHead;
        tray.jewelleryHead = new displayJewellery(null,1,"watch","Gold","male","http",100);

    }

    @Test
    void testCasesGetters(){
    cases1 = new displayCase(null, 1, "Mounted", false);
    cases1.next = new displayCase(null, 2, "Mounted", false);
    cases1.next.next = new displayCase(null, 3, "Mounted", false);

    assertEquals(1,cases1.getID());
    assertEquals(2, cases1.next.getID());
    assertEquals(3, cases1.next.next.getID());
    }

    @Test
    void testCasesSetters() {
        cases = new displayCase(null, 1, "Mounted", false);
        cases.next = new displayCase(null, 2, "Mounted", false);
        cases.next.next = new displayCase(null, 3, "Mounted", false);

        cases.setLighting(true);
        cases.next.setLighting(true);
        cases.next.next.setLighting(true);

        assertTrue(cases.toString().contains("lit"));
        assertTrue(cases.next.toString().contains("lit"));
        assertTrue(cases.next.next.toString().contains("lit"));
    }
}