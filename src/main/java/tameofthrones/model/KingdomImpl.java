package tameofthrones.model;

import tameofthrones.error.Error;
import tameofthrones.error.TameOfThronesException;
import tameofthrones.interfaces.Kingdom;
import tameofthrones.interfaces.Message;
import tameofthrones.utility.Constants;
import tameofthrones.utility.Logger;

import java.util.*;


public class KingdomImpl implements Kingdom {

    private KingdomInformation kingdom;
    private List<Message> secretMessages;

    public KingdomImpl(KingdomInformation kingdom) {
        this.kingdom = kingdom;
    }

    @Override
    public void initSecretMessages(List<Message> secretMessages) throws TameOfThronesException {
        this.secretMessages = secretMessages;
        if(!checkMessageValidity()){
            throw new TameOfThronesException(Error.SECRET_MESSAGES_NOT_VALID);
        }
    }

    @Override
    public boolean isRuler() throws TameOfThronesException {
        Set<KingdomInformation> allies = getAllies();
        if (allies.size() >= Constants.MINIMUM_ALLIES_TO_RULE) {
            printAllies(allies);
            return true;
        }
        return false;
    }

    @Override
    public Set<KingdomInformation> getAllies() throws TameOfThronesException {

        Set<KingdomInformation> allies = new HashSet<>();
        for (Message message : secretMessages) {
            if (message.isKingdomAnAlly()) {
                allies.add(message.getKingdom());
            }
        }
        return allies;
    }

    @Override
    public boolean checkMessageValidity() {
        if (Objects.isNull(secretMessages) || secretMessages.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void printAllies(Set<KingdomInformation> allies) {

        // Ruler's Name
        Logger.printLogSameLine(kingdom.name() + " ");
        // Allies Name
        allies.forEach((ally -> Logger.printLogSameLine(ally.name() + " ")));
        Logger.printLog("");
    }


}
