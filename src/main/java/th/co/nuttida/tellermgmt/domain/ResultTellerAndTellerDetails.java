package th.co.nuttida.tellermgmt.domain;

import lombok.Data;

@Data
public class ResultTellerAndTellerDetails {

    private Integer tellerId;
    private String tellerNo;
    private String tellerAddress;
    private String latitude;
    private String longitude;
    private String serial;
    private String contractNo;
    private String telTellerAddress;
    private String branch;
    private String gprsCompany;
    private int districtId;
    private int provinceId;
    private int zoneId;
    private int tellerDetailsId;
    private int typeAddressId;
    private int versionTellerId;
    private int typeTellerId;
    private int brandTellerId;
    private double distance;
}
