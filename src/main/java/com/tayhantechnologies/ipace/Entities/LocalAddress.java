package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import java.net.*;

@Component
public class LocalAddress {
    String ipLocal;
    String mask;

    public String findAddress() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            InetAddress localHost = Inet4Address.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

            for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {

                System.out.println(address.getAddress());
                System.out.println(address.getNetworkPrefixLength());
            }

            ipLocal = socket.getLocalAddress().getHostAddress();





            System.out.println("IP Address of local machine: " + ipLocal + " " + mask);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
            return ipLocal;
    }
}
