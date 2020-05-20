package tameofthrones.test;

import tameofthrones.error.Error;
import tameofthrones.error.TameOfThronesException;
import tameofthrones.interfaces.Kingdom;
import tameofthrones.interfaces.Message;
import tameofthrones.model.KingdomImpl;
import tameofthrones.model.KingdomInformation;
import tameofthrones.model.SeasarCipherSecretMessage;
import tameofthrones.utility.EnumUtility;
import tameofthrones.utility.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeekTrust {

    public static void main(String[] args) {
        //runThroughIDE("/testcase-1.txt");
        //runThroughIDE("/testcase-2.txt");
        //runThroughIDE("/testcase-3.txt");
        //runThroughIDE("/testcase-4.txt");
        runThroughTerminal(args);
    }

    private static void runThroughTerminal(String[] args) {
        try {
            if (args.length <= 0) {
                throw new TameOfThronesException(Error.INVALID_ARGUMENTS);
            }

            Kingdom kingdom = new KingdomImpl(KingdomInformation.SPACE);
            kingdom.initSecretMessages(secretMessages(processInputFile(args[0])));
            if (!kingdom.isRuler()) {
                throw new TameOfThronesException(Error.NO_ALLIES);
            }
        } catch (IOException | URISyntaxException |
                TameOfThronesException e) {
            Logger.printLog(e.getMessage());
        }

    }


    public static List<Message> secretMessages(List<String> messages) throws TameOfThronesException {
        List<Message> secretMessages = new ArrayList<>();
        for (String message : messages) {
            String[] breakup = message.split(" ");
            KingdomInformation kingdom = EnumUtility.loadUpperCase(breakup[0], KingdomInformation.class, KingdomInformation.NONE);

            if (kingdom.equals(KingdomInformation.NONE)) {
                throw new TameOfThronesException(Error.KINGDOM_NOT_VALID);
            }

            List<String> messageList = Arrays.asList(breakup).subList(0, breakup.length);
            String fullMessage = messageList.stream()
                    .collect(Collectors.joining(" "));
            secretMessages.add(new SeasarCipherSecretMessage(fullMessage, kingdom));
        }

        return secretMessages;
    }

    private static List<String> processInputFileToInstructions(String fileName) throws IOException {
        List<String> instructions = null;
        try (InputStream inputStream = GeekTrust.class.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            instructions = reader.lines()
                    .collect(Collectors.toList());
        }
        return instructions;
    }

    private static List<String> processInputFile(String fileName) throws IOException, URISyntaxException {
        File file = new File(fileName);
        Path path = Paths.get(file.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    private static void runThroughIDE(String filePath) {

        try {
            KingdomImpl kingdom = new KingdomImpl(KingdomInformation.SPACE);
            kingdom.initSecretMessages(secretMessages(processInputFileToInstructions(filePath)));
            if (!kingdom.isRuler()) {
                throw new TameOfThronesException(Error.NO_ALLIES);
            }
        } catch (IOException |
                TameOfThronesException e) {
            Logger.printLog(e.getMessage());
        }
    }
}
