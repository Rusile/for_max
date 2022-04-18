package Rusile.server;



import Rusile.server.parser.FileManager;
import Rusile.server.util.CollectionManager;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static final Scanner scanner = ServerConfig.scanner;
    private volatile boolean running = true;

    @Override
    public void run() {
        ServerConfig.logger.info("Console thread is running");
        while (running) {
            String line = scanner.nextLine();
            if ("save".equalsIgnoreCase(line)) {
                //try {
                    ServerConfig.fileManager.writeCollection(ServerConfig.collectionManager.getCollection());
                    ServerConfig.logger.info("Collection saved");
//                } catch (IOException e) {
////                    ServerConfig.logger.fatal("Something went wrong, can't save collection");
////                }
            }
            if ("exit".equalsIgnoreCase(line)) {
                ServerConfig.logger.info("Server closed");
                System.exit(0);
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
