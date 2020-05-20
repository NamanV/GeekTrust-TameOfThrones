package tameofthrones.interfaces;

import tameofthrones.error.TameOfThronesException;
import tameofthrones.model.KingdomInformation;

import java.util.List;
import java.util.Set;

public interface Kingdom {

    public void initSecretMessages(List<Message> secretMessages) throws TameOfThronesException;

    public boolean checkMessageValidity();

    public boolean isRuler() throws TameOfThronesException;

    public Set<KingdomInformation> getAllies() throws TameOfThronesException;

    public void printAllies(Set<KingdomInformation> allies);

}
