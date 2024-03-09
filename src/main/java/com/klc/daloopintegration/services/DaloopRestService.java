package com.klc.daloopintegration.services;

import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.dto.ChargingActivityDataDTO;

public interface DaloopRestService {

    ChargingActivityDataDTO getTransactionsDetails(String transactionId);

    void registerConnectivityEvent(HookData hookTemplate);
}
