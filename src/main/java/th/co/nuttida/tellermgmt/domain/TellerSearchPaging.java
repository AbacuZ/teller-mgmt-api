package th.co.nuttida.tellermgmt.domain;

import java.util.List;
import lombok.Data;

@Data
public class TellerSearchPaging {
    
    private List<Teller> tellerResult;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;
    private int totalPages;
    private long recordsTotal;
    private long recordsFiltered;
}
