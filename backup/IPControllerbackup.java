package com.tayhantechnologies.ipace.Controllers;

import com.tayhantechnologies.ipace.Entities.*;
import com.tayhantechnologies.ipace.ScanResult;
import com.tayhantechnologies.ipace.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@Controller
@RequestMapping("/")
public class IPControllerbackup {




    @Autowired
    Scanner scanner;

    @Autowired
    IpaceDevicesEntity ipaceDevicesEntity;

    @Autowired
    IpaceDevicesPatchEntity ipaceDevicesPatchEntity;

    @Autowired
    DevicePortsEntity devicePortsEntity;
    @Autowired
    DeviceStreamEntity deviceStreamEntity;




    RestTemplate restTemplate = new RestTemplate();


  @RequestMapping(value = "/ClearStream", method = RequestMethod.GET)
  String ClearStream() throws Exception {
      EmbeddedStreamResponse responseDetails = restTemplate
              .exchange("http://127.0.0.1:8080/deviceStreamEntities?size=2000", HttpMethod.GET, null, new ParameterizedTypeReference<EmbeddedStreamResponse>() {
              }).getBody();

      for (int j = 0; j < responseDetails.getEmbeddedStream().getDevicesStreamEntityArrayList().size(); j++ ) {
          System.out.print("http://127.0.0.1:8080/deviceStreamEntities/" + responseDetails.getEmbeddedStream().getDevicesStreamEntityArrayList().get(j).getId());
          restTemplate.delete("http://127.0.0.1:8080/deviceStreamEntities/" + responseDetails.getEmbeddedStream().getDevicesStreamEntityArrayList().get(j).getId());
      }
      return "Finish Clear";
  }

    @RequestMapping(value = "/Discovery", method = RequestMethod.GET)
    String Discovery(@RequestParam(value = "subnet", required = true) String subnet, @RequestParam(value = "mask", required = true) String mask) throws Exception {





        //String subnet = "192.168.0";

        final List<Future<String>> futures = new ArrayList<>();
        final ExecutorService es = Executors.newFixedThreadPool(20);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);

        final int timeoutping = 1000;
        for (int i=1;i<255;i++) {
            String host = subnet + "." + i;
                // for (int port = 1; port <= 80; port++) {
            Integer deviceCount = 1;
            try {
                EmbeddedResponse deviceScanResponse = restTemplate.exchange("http://127.0.0.1:8080/ipaceDevicesEntities/" + host, HttpMethod.GET, null, new ParameterizedTypeReference<EmbeddedResponse>() {
                }).getBody();
            }catch(NullPointerException ex){
                deviceCount = 0;
            }catch(HttpClientErrorException ex2){
                deviceCount = 0;
            }
            if ( deviceCount > 0){


                System.out.println("Existing IP");
            } else {
                futures.add(scanner.SubDiscovery(host, es, timeoutping));

            }


           // deviceStreamEntity.setId(i);
           // deviceStreamEntity.setDeviceId(subnet + "." + i);
          //  deviceStreamEntity.setDateScanned("Scanning....");

           // ResponseEntity<DeviceStreamEntity> tester2 = restTemplate.postForEntity("http://127.0.0.1:8080/deviceStreamEntities?page=0&size=255", deviceStreamEntity, DeviceStreamEntity.class);


            }
            es.awaitTermination(1000L, TimeUnit.MILLISECONDS);




        /**Patch in the faultFlag and alarmCount values to enable the BFEF Process*/
    ;
        int i = 0;
        for (final Future<String> f : futures) {
            i = i + 1;
            if (f.get().contains("up")) {
                Random rand;

                String hostIP = f.get().replaceFirst("up", "");
                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int randomNum = 0 + (int) (Math.random() * ((45000 - 0) + 1));
                InetAddress addr = InetAddress.getByName(f.get().replaceFirst("up", ""));
                String host = addr.getHostName();
                System.out.println("Hostname" + host);
                System.out.println("New IP Found");
                ipaceDevicesEntity.setId(randomNum);
                ipaceDevicesEntity.setDeviceName(hostIP);
                ipaceDevicesEntity.setIpAddress(hostIP);
                ipaceDevicesEntity.setDateScanned("New IP Found");
                ipaceDevicesEntity.setOnline(1);
                deviceStreamEntity.setId(i);
                deviceStreamEntity.setDeviceId(hostIP);
                deviceStreamEntity.setDateScanned("New IP Found");
                deviceStreamEntity.setDisplayFlag(1);
                HttpEntity<DeviceStreamEntity> entity = new HttpEntity<>(deviceStreamEntity, headers);
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                RestTemplate restTemplatePatch = new RestTemplate(requestFactory);
                ResponseEntity<DeviceStreamEntity> tester2 = restTemplate.postForEntity("http://127.0.0.1:8080/deviceStreamEntities?page=0&size=255", deviceStreamEntity, DeviceStreamEntity.class);
                // EmbeddedStreamResponse responseDetails2 = restTemplate
                //         .exchange("http://127.0.0.1:8080/deviceStreamEntities/search/findByDeviceId?deviceID=" + f.get(), HttpMethod.GET, null, new ParameterizedTypeReference<EmbeddedStreamResponse>() {
                //         }).getBody();
                // deviceStreamEntity.setId(responseDetails2.getEmbeddedStream().getDevicesStreamEntityArrayList().get(0).getId());
                // deviceStreamEntity.setDeviceId(responseDetails2.getEmbeddedStream().getDevicesStreamEntityArrayList().get(0).getDeviceId());

                // System.out.println("http://127.0.0.1:8080/deviceStreamEntities/" + responseDetails2.getEmbeddedStream().getDevicesStreamEntityArrayList().get(0).getId());
                // restTemplatePatch.exchange("http://127.0.0.1:8080/deviceStreamEntities/" + responseDetails2.getEmbeddedStream().getDevicesStreamEntityArrayList().get(0).getId(), HttpMethod.PATCH, entity, Void.class);


                ResponseEntity<IpaceDevicesEntity> tester = restTemplate.postForEntity("http://127.0.0.1:8080/ipaceDevicesEntities", ipaceDevicesEntity, IpaceDevicesEntity.class);


                System.out.println("Alive at " + f.get());
                System.out.println(tester.getStatusCode());
            }else{
                deviceStreamEntity.setId(i);
                deviceStreamEntity.setDeviceId(f.get());
                deviceStreamEntity.setDateScanned("Scanned - No IP");
                deviceStreamEntity.setDisplayFlag(0);
                ResponseEntity<DeviceStreamEntity> tester2 = restTemplate.postForEntity("http://127.0.0.1:8080/deviceStreamEntities?page=0&size=255", deviceStreamEntity, DeviceStreamEntity.class);
            }
        }
    return "Finish IP Controller";
    }


    @RequestMapping(value = "/PortDiscovery", method = RequestMethod.GET)
    String PortDiscovery() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);
        String hostIP;
        String subnet = "192.168.0";
       //ResponseEntity<ListIpaceDevices> tester = restTemplate.getForEntity("http://127.0.0.1:8080/ipaceDevicesEntities", ListIpaceDevices.class);

        EmbeddedResponse responseDetails = restTemplate
                .exchange("http://127.0.0.1:8080/ipaceDevicesEntities", HttpMethod.GET, null, new ParameterizedTypeReference<EmbeddedResponse>() {
                }).getBody();


        System.out.println(responseDetails.toString());
        for (int j = 0; j < responseDetails.getEmbeddedDevices().getIpaceDevicesEntityArrayList().size(); j++ ) {
            hostIP = responseDetails.getEmbeddedDevices().getIpaceDevicesEntityArrayList().get(j).getDeviceName();

            if (InetAddress.getByName(hostIP).isReachable(1000)) {

                System.out.println("Scanning " + hostIP + " for open ports");
                final List<Future<ScanResult>> futures = new ArrayList<>();
                final ExecutorService es = Executors.newFixedThreadPool(120);


                final int timeoutports = 5000;

                for (int port = 1; port <= 10000; port++) {
                    // for (int port = 1; port <= 80; port++) {
                    futures.add(scanner.portIsOpen(es, hostIP, port, timeoutports));
                }
                es.awaitTermination(5000L, TimeUnit.MILLISECONDS);
                int openPorts = 0;
                int i = 0;
                for (final Future<ScanResult> f : futures) {
                    if (f.get().isOpen()) {
                        i++;
                        openPorts++;
                        System.out.println(f.get().getPort());
                        int randomNum = 0 + (int)(Math.random() * ((45000 - 0) + 1));
                        devicePortsEntity.setId(randomNum);
                        devicePortsEntity.setPortDetails(String.valueOf(f.get().getPort()));
                        devicePortsEntity.setDeviceId(hostIP);
                        deviceStreamEntity.setId(i);
                        deviceStreamEntity.setDeviceId(hostIP);
                        deviceStreamEntity.setPortDetails(String.valueOf(f.get().getPort()));
                        deviceStreamEntity.setDateScanned("Port Open");
                        HttpEntity<DeviceStreamEntity> entity = new HttpEntity<>(deviceStreamEntity, headers);
                        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                        RestTemplate restTemplatePatch = new RestTemplate(requestFactory);
                        ResponseEntity<DeviceStreamEntity> tester2 = restTemplate.postForEntity("http://127.0.0.1:8080/deviceStreamEntities?page=0&size=255", deviceStreamEntity, DeviceStreamEntity.class);

                        ResponseEntity<DevicePortsEntity> tester = restTemplate.postForEntity("http://127.0.0.1:8080/devicePortsEntities", devicePortsEntity, DevicePortsEntity.class);
                        System.out.println(tester.getStatusCode());
                    }
                }
                System.out.println("There are " + openPorts + " open ports on host " + hostIP + " (probed with a timeout of "
                        + timeoutports + "ms)");
            }else {
                System.out.println("Host " + hostIP + " not reachable");
            }
        }


        return "Finish IP Controller";
    }

    @RequestMapping(value = "/DevicePortDiscovery", method = RequestMethod.GET)
    String DevicePortDiscovery(@RequestParam(value = "deviceId", required = true) String deviceId) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);
        String hostIP;
        String subnet = "192.168.0";
        //ResponseEntity<ListIpaceDevices> tester = restTemplate.getForEntity("http://127.0.0.1:8080/ipaceDevicesEntities", ListIpaceDevices.class);





            hostIP = deviceId;

            if (InetAddress.getByName(hostIP).isReachable(1000)) {

                System.out.println("Scanning " + hostIP + " for open ports");
                final List<Future<ScanResult>> futures = new ArrayList<>();
                final ExecutorService es = Executors.newFixedThreadPool(120);


                final int timeoutports = 5000;

                for (int port = 1; port <= 10000; port++) {
                    // for (int port = 1; port <= 80; port++) {
                    futures.add(scanner.portIsOpen(es, hostIP, port, timeoutports));
                }
                es.awaitTermination(5000L, TimeUnit.MILLISECONDS);
                int openPorts = 0;
                int i = 0;
                for (final Future<ScanResult> f : futures) {
                    if (f.get().isOpen()) {
                        i++;
                        openPorts++;
                        System.out.println(f.get().getPort());
                        int randomNum = 0 + (int)(Math.random() * ((45000 - 0) + 1));
                        devicePortsEntity.setId(randomNum);
                        devicePortsEntity.setPortDetails(String.valueOf(f.get().getPort()));
                        devicePortsEntity.setDeviceId(hostIP);
                        deviceStreamEntity.setId(i);
                        deviceStreamEntity.setDeviceId(hostIP);
                        deviceStreamEntity.setPortDetails(String.valueOf(f.get().getPort()));
                        deviceStreamEntity.setDateScanned("Port Open");
                        HttpEntity<DeviceStreamEntity> entity = new HttpEntity<>(deviceStreamEntity, headers);
                        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                        RestTemplate restTemplatePatch = new RestTemplate(requestFactory);
                        ResponseEntity<DeviceStreamEntity> tester2 = restTemplate.postForEntity("http://127.0.0.1:8080/deviceStreamEntities?page=0&size=255", deviceStreamEntity, DeviceStreamEntity.class);

                        ResponseEntity<DevicePortsEntity> tester = restTemplate.postForEntity("http://127.0.0.1:8080/devicePortsEntities", devicePortsEntity, DevicePortsEntity.class);
                        System.out.println(tester.getStatusCode());
                    }
                }
                System.out.println("There are " + openPorts + " open ports on host " + hostIP + " (probed with a timeout of "
                        + timeoutports + "ms)");
            }else {
                System.out.println("Host " + hostIP + " not reachable");
            }



        return "Finish IP Controller";
    }

    @RequestMapping(value = "/Online", method = RequestMethod.GET)
    String Online() throws Exception {


        final int timeoutping = 1000;
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);
        String hostIP;

        EmbeddedResponse responseDetails = restTemplate
                .exchange("http://127.0.0.1:8080/ipaceDevicesEntities?size=20000", HttpMethod.GET, null, new ParameterizedTypeReference<EmbeddedResponse>() {
                }).getBody();

        final List<Future<String>> futures = new ArrayList<>();
        final ExecutorService es = Executors.newFixedThreadPool(120);
        System.out.println(responseDetails.toString());
        for (int j = 0; j < responseDetails.getEmbeddedDevices().getIpaceDevicesEntityArrayList().size(); j++ ) {
            hostIP = responseDetails.getEmbeddedDevices().getIpaceDevicesEntityArrayList().get(j).getDeviceName();


            System.out.println("Checking " + hostIP + " is Online");


            futures.add(scanner.SubDiscovery(hostIP, es, timeoutping));


        }
            es.awaitTermination(100L, TimeUnit.MILLISECONDS);
            int openPorts = 0;
            int i = 0;

            HttpEntity<IpaceDevicesPatchEntity> entity = new HttpEntity<>(ipaceDevicesPatchEntity, headers);
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplatePatch = new RestTemplate(requestFactory);
                for (final Future<String> f : futures) {
                    if (f.get().contains("up")) {
                        i++;
                        openPorts++;
                        ipaceDevicesPatchEntity.setOnline(1);
                        hostIP = f.get().replaceFirst("up", "");
                        System.out.println("Device Online");
                    } else {
                        ipaceDevicesPatchEntity.setOnline(0);
                        hostIP = f.get().replaceFirst("down", "");
                        System.out.println("Device Offline");
                    }


                        ipaceDevicesPatchEntity.setIpAddress(hostIP);
                        ipaceDevicesPatchEntity.setDeviceName(hostIP);


                        System.out.println("http://127.0.0.1:8080/ipaceDevicesEntities/" + hostIP);
                        restTemplatePatch.exchange("http://127.0.0.1:8080/ipaceDevicesEntities/" + hostIP, HttpMethod.PATCH, entity, Void.class);
                        System.out.println("Patched Online Status");

                }

        return "Finish Online Check";
    }

}

