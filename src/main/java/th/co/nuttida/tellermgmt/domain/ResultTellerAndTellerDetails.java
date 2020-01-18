package th.co.nuttida.tellermgmt.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
