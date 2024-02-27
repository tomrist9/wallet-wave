package org.example.service.menu;

import org.example.service.inter.ExitServiceInter;

public class ExitService implements ExitServiceInter {
    @Override
    public void processLogic() {

    }
    public static void exitMenu() {
        System.out.println("Exiting program...");
        // Add any cleanup or closing operations if needed
        System.exit(0);
    }
}
