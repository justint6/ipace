package com.tayhantechnologies.ipace;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public  class Scanner  {
    public Future<String> SubDiscovery(String subnet, final ExecutorService es, final int timeout){

        return es.submit(new Callable<String>() {
            @Override public String call() {
                String deviceIP = "false";

                    try {


                        String host = subnet;
                        deviceIP = host + "down";
                        if (InetAddress.getByName(host).isReachable(timeout)) {
                            deviceIP = host + "up";

                        }
                        } catch (UnknownHostException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                        e.printStackTrace();
                    }
                return deviceIP;
            }
        });
    }

    public static void checkHosts(String subnet){
        int timeout=1000;
        for (int i=1;i<255;i++) {
            String host = subnet + "." + i;
            try {
                if (InetAddress.getByName(host).isReachable(timeout)) {

                    System.out.println(host + " is reachable");

                    final ExecutorService es = Executors.newFixedThreadPool(20);
                    final String ip = "127.0.0.1";
                    final int timeoutports = 100;
                    final List<Future<ScanResult>> futures = new ArrayList<>();
                    for (int port = 1; port <= 10000; port++) {
                        // for (int port = 1; port <= 80; port++) {
                        futures.add(portIsOpen(es, host, port, timeoutports));
                    }
                    es.awaitTermination(200L, TimeUnit.MILLISECONDS);
                    int openPorts = 0;
                    for (final Future<ScanResult> f : futures) {
                        if (f.get().isOpen()) {
                            openPorts++;
                            System.out.println(f.get().getPort());
                        }
                    }
                    System.out.println("There are " + openPorts + " open ports on host " + host + " (probed with a timeout of "
                            + timeout + "ms)");

                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
        return es.submit(new Callable<ScanResult>() {
            @Override public ScanResult call() {
                try {
                    Socket socket = new Socket();
                    ScanResult scanResult = new ScanResult();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    scanResult.setOpen(true);
                    scanResult.setPort(port);
                    return scanResult;
                } catch (Exception ex) {
                    ScanResult scanResult = new ScanResult();
                    scanResult.setOpen(false);
                    scanResult.setPort(port);
                    return scanResult;
                }
            }
        });
    }
}
