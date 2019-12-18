package th.co.nuttida.tellermgmt.domain;

import lombok.Data;

@Data
public class DataSearchCriteria {

    private String tellerAddress;
    private String tellerNo;
    private int districtId;
    private int provinceId;
    private int zoneId;
    private int versionTellerId;
    private int typeTellerId;
    private int brandTellerId;
}
