package th.co.nuttida.tellermgmt.domain;

import lombok.Data;

@Data
public class ResultTeller {

    private String tellerNo;
    private String tellerAddress;
    private String latitude;
    private String longitude;
    private String serial;
    private String contractNo;
    private String telTellerAddress;
    private String branch;
    private String gprsCompany;
    private String district;
    private String province;
    private String zone;
    private String typeAddress;
    private String versionTeller;
    private String typeTeller;
    private String brandTeller;
    private String ipAddress;
    private String ipGateway;
    private String servicePort;
    private String localPort;
    private String indexMasterKey;
}
