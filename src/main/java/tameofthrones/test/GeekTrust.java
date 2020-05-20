package tameofthrones.test;

import tameofthrones.error.Error;
import tameofthrones.error.TameOfThronesException;
import tameofthrones.model.KingdomCreator;
import tameofthrones.model.Kingdom;
import tameofthrones.interfaces.Message;
import tameofthrones.model.SeasarCipherSecretMessage;
import tameofthrones.utility.EnumUtility;
import tameofthrones.utility.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class GeekTrust {

    public static void main(String[] args) {

        String filePath = "/testcase-1.txt";

        List<Message> secretMessages = null;
        try {
            secretMessages = secretMessage(processInputFileToInstructions(filePath));
            KingdomCreator kingdom = new KingdomCreator(Kingdom.SPACE);
            kingdom.initSecretMessages(secretMessages);
            if(kingdom.getAllies().isEmpty()){
                Logger.printLog(Error.NO_ALLIES.getErrorMessage());
            }else {
                kingdom.getAllies().forEach((ally -> Logger.printLogSameLine(ally.name() +" ")));
            }

        } catch (IOException | TameOfThronesException e) {
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


    private static List<Message> secretMessage(List<String> messages) throws TameOfThronesException {
        List<Message> list = new ArrayList<>();
        for (String message : messages) {
            String[] breakup = message.split(" ");
            Kingdom kingdom = EnumUtility.loadUpperCase(breakup[0], Kingdom.class, Kingdom.NONE);
            if(kingdom.equals(Kingdom.NONE)){
                throw new TameOfThronesException(Error.KINGDOM_NOT_VALID);
            }
            list.add(new SeasarCipherSecretMessage(breakup[1],kingdom));
        }

        return list;
    }
}
