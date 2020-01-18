/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.nuttida.tellermgmt.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.nuttida.tellermgmt.domain.ResultTellerAndTellerDetails;

/**
 *
 * @author Kitsada
 */
@Repository
public class TellerImpl {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ResultTellerAndTellerDetails> findNearestLocationMap(String lat, String lng) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        List<Map<String, Object>> resultSet = null;
        try {
            sql.append("SELECT *, (6371 * acos( cos( radians(:lat) ) * cos( radians( latitude ) ) ");
            sql.append(" * cos( radians( longitude ) - radians(:lng) ) + sin( radians(:lat) ) ");
            sql.append(" * sin( radians( latitude ) ) ) ) AS distance FROM teller HAVING ");
            sql.append(" distance < 5 ORDER BY distance asc;");
            System.out.println("lat " + lat);
            System.out.println("lng " + lng);
            paramSource.addValue("lat", lat);
            paramSource.addValue("lng", lng);
            resultSet = jdbcTemplate.queryForList(sql.toString(), paramSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapResult(resultSet);
    }
    
    private List<ResultTellerAndTellerDetails> mapResult(List<Map<String, Object>> resultSet) {
        List<ResultTellerAndTellerDetails> list = new ArrayList<>();
        if (resultSet.size() > 0) {
            for (Map<String, Object> obj : resultSet) {
                ResultTellerAndTellerDetails data = new ResultTellerAndTellerDetails();
                System.out.println(obj.get("teller_id"));
                System.out.println(obj.get("teller_no"));
                System.out.println(obj.get("branch"));
                System.out.println(obj.get("contract_no"));
                System.out.println(obj.get("district_id"));
                System.out.println(obj.get("province_id"));
                System.out.println(obj.get("zone_id"));
                System.out.println(obj.get("gprs_company"));
                System.out.println(obj.get("longitude"));
                System.out.println(obj.get("latitude"));
                System.out.println(obj.get("serial"));
                System.out.println(obj.get("tel_teller_address"));
                System.out.println(obj.get("teller_address"));
                System.out.println(obj.get("teller_details_id"));
                System.out.println(obj.get("type_address_id"));
                System.out.println(obj.get("version_teller_id"));
                System.out.println(obj.get("type_teller_id"));
                System.out.println(obj.get("brand_teller_id"));
                System.out.println(obj.get("distance"));
                data.setTellerId((Integer) obj.get("teller_id"));
                data.setTellerNo((String) obj.get("teller_no"));
                data.setBranch((String) obj.get("branch"));
                data.setContractNo((String) obj.get("contract_no"));
                data.setDistrictId((Integer) obj.get("district_id"));
                data.setProvinceId((Integer) obj.get("province_id"));
                data.setZoneId((Integer) obj.get("zone_id"));
                data.setGprsCompany((String) obj.get("gprs_company"));
                data.setLongitude((String) obj.get("longitude"));
                data.setLatitude((String) obj.get("latitude"));
                data.setSerial((String) obj.get("serial"));
                data.setTelTellerAddress((String) obj.get("tel_teller_address"));
                data.setTellerAddress((String) obj.get("teller_address"));
                data.setTellerDetailsId((Integer) obj.get("teller_details_id"));
                data.setTypeAddressId((Integer) obj.get("type_address_id"));
                data.setVersionTellerId((Integer) obj.get("version_teller_id"));
                data.setTypeTellerId((Integer) obj.get("type_teller_id"));
                data.setBrandTellerId((Integer) obj.get("brand_teller_id"));
                data.setDistance((Double) obj.get("distance"));
                list.add(data);
            }
            return list;
        } else {
            return list;
        }
    }
}
