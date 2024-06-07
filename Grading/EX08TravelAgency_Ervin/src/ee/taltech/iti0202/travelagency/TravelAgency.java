package ee.taltech.iti0202.travelagency;

import ee.taltech.iti0202.travelagency.client.Client;
import ee.taltech.iti0202.travelagency.client.StandardClient;
import ee.taltech.iti0202.travelagency.client.SilverClient;
import ee.taltech.iti0202.travelagency.client.GoldClient;
import ee.taltech.iti0202.travelagency.client.SilverClientBuilder;
import ee.taltech.iti0202.travelagency.client.GoldClientBuilder;
import ee.taltech.iti0202.travelagency.packages.TravelPackage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TravelAgency {
    private static final Logger LOGGER = Logger.getLogger(TravelAgency.class.getName());

    private List<Client> clients = new ArrayList<>();
    private List<TravelPackage> packages = new ArrayList<>();

    /**
     * @param client Client to be added.
     * @return Boolean statment (for testing mainly).
     */
    public boolean addClient(Client client) {
        boolean idExists = clients.stream().anyMatch(c -> c.getId() == client.getId());
        boolean emailExists = clients.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(client.getEmail()));

        if (idExists || emailExists) {
            LOGGER.warning("Client not added due to duplicate ID or email: " + client.getName());
            return false;
        } else {
            clients.add(client);
            LOGGER.info("New client added: " + client.getName());
            return true;
        }
    }

    /**
     * @param travelPackage TravelPackage to be added.
     * @return Boolean statment (for testing mainly).
     */
    public boolean addPackage(TravelPackage travelPackage) {
        boolean packageExists = packages.stream().anyMatch(p -> p.getId() == travelPackage.getId());

        if (packageExists) {
            LOGGER.warning("Package not added due to duplicate ID: " + travelPackage.getName());
            return false;
        } else {
            packages.add(travelPackage);
            LOGGER.info("New travel package added: " + travelPackage.getName());
            return true;
        }
    }

    /**
     * @param clientId ID of the client who the package is sold to.
     * @param packageId ID of the package being sold.
     * @return Boolean for confirmation.
     */
    public boolean sellPackageToClient(int clientId, int packageId) {
        Client client = clients.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst()
                .orElse(null);

        TravelPackage travelPackage = packages.stream()
                .filter(p -> p.getId() == packageId)
                .findFirst()
                .orElse(null);

        if (client != null && travelPackage != null && client.purchasePackage(travelPackage)) {
            LOGGER.info("Travel package " + travelPackage.getName() + " sold to " + client.getName());
            checkAndUpgradeClient(client);
            return true;
        } else {
            LOGGER.warning("Failed to sell package " + (travelPackage != null ? travelPackage.getName()
                    : "with ID " + packageId) + " to client ID " + clientId);
            return false;
        }
    }

    /**
     * Method for promoting clients. Triggered when a package is sold to a client.
     *
     * @param client Client being checked for promotion.
     */
    public void checkAndUpgradeClient(Client client) {
        if (client instanceof StandardClient && ((StandardClient) client).isReadyForUpgrade()) {
            SilverClient upgradedClient = new SilverClientBuilder().setId(client.getId()).setName(client.getName())
                    .setEmail(client.getEmail()).setAge(client.getAge()).setBudget(client.getBudget())
                    .createSilverClient();
            upgradedClient.setPurchasedPackages(client.getPurchasedPackages());
            updateClientInList(client, upgradedClient);
            LOGGER.info("Client " + client.getName() + " promoted to Silver member");
        } else if (client instanceof SilverClient && ((SilverClient) client).isReadyForUpgrade()) {
            GoldClient upgradedClient = new GoldClientBuilder().setId(client.getId()).
                    setName(client.getName()).setEmail(client.getEmail()).setAge(client.getAge())
                    .setBudget(client.getBudget()).createGoldClient();
            upgradedClient.setPurchasedPackages(client.getPurchasedPackages());
            updateClientInList(client, upgradedClient);
            LOGGER.info("Client " + client.getName() + " promoted to Gold member");
        }
    }

    /**
     * Helper method (used by checkAndUpgradeClient) for replacing the old client with the promoted one.
     *
     * @param oldClient Outdated client.
     * @param newClient Same client but now with promoted membership (will replace old client).
     */
    private void updateClientInList(Client oldClient, Client newClient) {
        int index = clients.indexOf(oldClient);
        if (index != -1) {
            clients.set(index, newClient);
        }
    }

    /**
     * @return Sorted list of top clients where the top clients have the lowest index.
     */
    public List<Client> getTopClients() {
        clients.sort(Comparator.comparingInt(c -> c.getPurchasedPackages().size()));
        return clients.reversed();
    }

    /**
     * @return Sorted list of the most popular packages.
     */
    public List<TravelPackage> getMostPopularPackages() {
        Map<TravelPackage, Long> packageFrequency = clients.stream()
                .flatMap(client -> client.getPurchasedPackages().stream())
                .collect(Collectors.groupingBy(pack -> pack, Collectors.counting()));

        List<TravelPackage> sortedPackages = packageFrequency.entrySet().stream()
                .sorted(Map.Entry.<TravelPackage, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return sortedPackages;
    }

    /**
     * Helper method for getting the client membership (used in testing).
     *
     * @param clientId ID of the client needing a status check.
     * @return Membership status in string.
     */
    public String getClientStatus(int clientId) {
        Client client = clients.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst()
                .orElse(null);
        if (client == null) {
            return "Not Found";
        } else if (client instanceof GoldClient) {
            return "Gold";
        } else if (client instanceof SilverClient) {
            return "Silver";
        } else if (client instanceof StandardClient) {
            return "Standard";
        } else {
            return "Unknown";
        }
    }
}
