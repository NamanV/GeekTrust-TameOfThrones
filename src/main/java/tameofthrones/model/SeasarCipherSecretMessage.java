package tameofthrones.model;

import tameofthrones.interfaces.Message;

import java.util.HashMap;
import java.util.Map;

public class SeasarCipherSecretMessage implements Message {

    private char[] cipherKeys = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private int CHAR_A = 'A';
    private String message;
    private Kingdom toKingdom;

    public SeasarCipherSecretMessage(String message, Kingdom toKingdom) {
        this.message = message;
        this.toKingdom = toKingdom;
    }

    @Override
    public boolean isKingdomAnAlly() {
        Map<Character, Integer> emblemCharCount = new HashMap<>();
        for (char ch : toKingdom.getEmblem().toCharArray()) {
            int cipherCharIndex = (ch +toKingdom.getCipherKey() - CHAR_A)%cipherKeys.length;
            emblemCharCount.put(cipherKeys[cipherCharIndex], emblemCharCount.getOrDefault(cipherKeys[cipherCharIndex], 0) + 1);
        }

        Map<Character, Integer> messageCharCount = new HashMap<>();
        for (char ch : message.toCharArray()) {
            messageCharCount.put(ch, messageCharCount.getOrDefault(ch, 0) + 1);
        }

        if(messageCharCount.size() < emblemCharCount.size()){
            return false;
        }

        for(Map.Entry<Character, Integer> entry : emblemCharCount.entrySet()){
            if(messageCharCount.getOrDefault(entry.getKey(),0) < entry.getValue()){
                return false;
            }
        }

        return true;
    }

    @Override
    public Kingdom getKingdom() {
        return toKingdom;
    }

    @Override
    public String toString() {
        return toKingdom.name();
    }
}
