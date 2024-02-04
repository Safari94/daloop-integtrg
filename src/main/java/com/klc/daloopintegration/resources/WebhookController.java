package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.services.DaloopRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@Slf4j
public class WebhookController {

    @Autowired
    public DaloopRestService daloopRestService;



    @PostMapping("/api/webhook")
    public ResponseEntity<?> hookHandling(@RequestHeader("API_KEY") String apiKey, @RequestBody HookData hookTemplate){
        log.info(hookTemplate.toString());


        if(apiKey!=null){

            log.info(apiKey);
            return switch (hookTemplate.getEvent()) {
                case "started", "costCalculated", "ended" -> {
                    String res = this.daloopRestService.getTransactionsDetails(hookTemplate.getData().getUsageId());
                    log.info(res);
                    yield ResponseEntity.status(200).body(Collections.singletonMap("body", "ok"));
                }
                case "connectivity" -> {
                         this.daloopRestService.registerConnectivityEvent(hookTemplate);
                        yield ResponseEntity.status(200).body(Collections.singletonMap("body", "ok"));

                    }
               default -> ResponseEntity.status(400).body(Collections.singletonMap("result", "event unknown"));
            };
        }else{
            return ResponseEntity.status(403).body(Collections.singletonMap("result","invalid token"));
        }



    }
}
