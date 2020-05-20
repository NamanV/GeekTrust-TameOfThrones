package tameofthrones.model;

import tameofthrones.interfaces.Message;

import java.util.HashMap;
import java.util.Map;

public class SeasarCipherSecretMessage implements Message {

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
            char cipherChar = (char)(ch +toKingdom.getCipherKey());
            emblemCharCount.put(cipherChar, emblemCharCount.getOrDefault(cipherChar, 0) + 1);
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
