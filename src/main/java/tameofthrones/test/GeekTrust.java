package tameofthrones.test;

import tameofthrones.error.Error;
import tameofthrones.error.TameOfThronesException;
import tameofthrones.model.KingdomCreator;
import tameofthrones.model.Kingdom;
import tameofthrones.interfaces.Message;
import tameofthrones.model.SeasarCipherSecretMessage;
import tameofthrones.utility.Constants;
import tameofthrones.utility.EnumUtility;
import tameofthrones.utility.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeekTrust {

    public static void main(String[] args) {


        List<Message> secretMessages = null;
        try {
            if (args.length <= 0) {
                throw new TameOfThronesException(Error.INVALID_ARGUMENTS);
            }
            String filePath = args[0];

            secretMessages = secretMessage(processInputFile(filePath));
            KingdomCreator kingdom = new KingdomCreator(Kingdom.SPACE);
            kingdom.initSecretMessages(secretMessages);
            List<Kingdom> allies = kingdom.getAllies();
            if (allies.isEmpty()) {
                throw new TameOfThronesException(Error.NO_ALLIES);
            }
            kingdom.printAllies(allies);
        } catch (IOException | URISyntaxException |
                TameOfThronesException e) {
            Logger.printLog(e.getMessage());
        }

    }

    private void runThroughIDE() {
        String filePath = "/testcase-1.txt";

        List<Message> secretMessages = null;
        try {
            secretMessages = secretMessage(processInputFileToInstructions(filePath));
            KingdomCreator kingdom = new KingdomCreator(Kingdom.SPACE);
            kingdom.initSecretMessages(secretMessages);
            List<Kingdom> allies = kingdom.getAllies();
            if (allies.isEmpty()) {
                throw new TameOfThronesException(Error.NO_ALLIES);
            }
            kingdom.printAllies(allies);
        } catch (IOException |
                TameOfThronesException e) {
            Logger.printLog(e.getMessage());
        }
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


    private static List<Message> secretMessage(List<String> messages) throws TameOfThronesException {
        List<Message> list = new ArrayList<>();
        for (String message : messages) {
            String[] breakup = message.split(" ");
            if (breakup.length > Constants.MAXIMUM_INPUT_SIZE) {
                throw new TameOfThronesException(Error.INVALID_ARGUMENTS);
            }
            Kingdom kingdom = EnumUtility.loadUpperCase(breakup[0], Kingdom.class, Kingdom.NONE);
            if (kingdom.equals(Kingdom.NONE)) {
                throw new TameOfThronesException(Error.KINGDOM_NOT_VALID);
            }
            list.add(new SeasarCipherSecretMessage(breakup[1], kingdom));
        }

        return list;
    }
}
