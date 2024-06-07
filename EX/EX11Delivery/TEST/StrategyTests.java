import ee.taltech.iti0202.delivery.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyTests {
    @Test
    void FarthestLocationStrategyAlwaysGetsTheFarthestLocation() {
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();
        Location narva = world.addLocation("Narva", List.of("Tallinn", "Tartu"), List.of(2, 4)).get();

        Courier mati = world.addCourier("Mati", "Tartu").get();
        Strategy strategyMati = new FarthestLocationStrategy(mati, world);
        world.giveStrategy("Mati", strategyMati);

        world.tick();

        Assertions.assertEquals(narva, mati.getTarget());
    }

    @Test
    void FarthestLocationStrategyPicksRightPacket() {
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();
        Location narva = world.addLocation("Narva", List.of("Tallinn", "Tartu"), List.of(2, 4)).get();
        Packet packetTartu1 = new Packet("tartu1", narva);
        Packet packetTartu2 = new Packet("tartu2", tallinn);
        tartu.addPacket(packetTartu1);
        tartu.addPacket(packetTartu2);

        Courier mati = world.addCourier("Mati", "Tartu").get();
        Strategy strategyMati = new FarthestLocationStrategy(mati, world);
        world.giveStrategy("Mati", strategyMati);

        world.tick();
        world.tick(); // moving towards target

        Map<String, Packet> expectedHashMap = new HashMap<>();
        expectedHashMap.put("tartu1", packetTartu1);
        Assertions.assertEquals(expectedHashMap, mati.getCurrentPackages());
    }

    @Test
    void ClosestLocationAlwaysGetsTheClosestLocation() {
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();
        Location narva = world.addLocation("Narva", List.of("Tallinn", "Tartu"), List.of(2, 4)).get();

        Courier mati = world.addCourier("Mati", "Narva").get();
        Strategy strategyMati = new ClosestLocationStrategy(mati, world);
        world.giveStrategy("Mati", strategyMati);

        world.tick();

        Assertions.assertEquals(tallinn, mati.getTarget());
    }

    @Test
    void ClosestLocationStrategyPicksRightPacket() {
        World world = new World();

        Location tallinn = world.addLocation("Tallinn", new ArrayList<>(), new ArrayList<>()).get();
        Location tartu = world.addLocation("Tartu", List.of("Tallinn"), List.of(3)).get();
        Location narva = world.addLocation("Narva", List.of("Tallinn", "Tartu"), List.of(2, 4)).get();
        Packet packetTartu1 = new Packet("tartu1", narva);
        Packet packetTartu2 = new Packet("tartu2", tallinn);
        tartu.addPacket(packetTartu1);
        tartu.addPacket(packetTartu2);

        Courier mati = world.addCourier("Mati", "Tartu").get();
        Strategy strategyMati = new ClosestLocationStrategy(mati, world);
        world.giveStrategy("Mati", strategyMati);

        world.tick();
        world.tick(); // moving towards target

        Map<String, Packet> expectedHashMap = new HashMap<>();
        expectedHashMap.put("tartu2", packetTartu2); // this time the closest is tartu, not narva.
        Assertions.assertEquals(expectedHashMap, mati.getCurrentPackages());
    }


}
