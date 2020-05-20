package tameofthrones.model;

import tameofthrones.model.Kingdom;
import tameofthrones.interfaces.Message;

import java.util.ArrayList;
import java.util.List;


public class KingdomCreator {

    private Kingdom kingdom;
    private List<Message> secretMessages;

    public KingdomCreator(Kingdom kingdom){
        this.kingdom = kingdom;
    }

    public void initSecretMessages(List<Message> secretMessages){
        this.secretMessages = secretMessages;
    }

    public List<Kingdom> getAllies(){
        List<Kingdom> allies = new ArrayList<>();
        for(Message message : secretMessages){
            if(message.isKingdomAnAlly()){
                allies.add(message.getKingdom());
            }
        }
        return allies;
    }


}
