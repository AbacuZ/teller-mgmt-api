package th.co.nuttida.tellermgmt.controller;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "logbook")
public class LogBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logbook_id")
	private Integer logBookId;

	@Column(name = "date_time")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7:00")
	private Date dateTime;

	@Basic(optional = false)
	@Column(name = "problem")
	private String problem;

	@Basic(optional = false)
	@Column(name = "solution")
	private String solution;

	@OneToOne(mappedBy = "username", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;

	@OneToOne(mappedBy = "teller_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Teller teller;

	public LogBook() {
		super();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (logBookId != null ? logBookId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof LogBook)) {
			return false;
		}
		LogBook other = (LogBook) object;
		if ((this.logBookId == null && other.logBookId != null)
				|| (this.logBookId != null && !this.logBookId.equals(other.logBookId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "LogBook [logBookId=" + logBookId + ", dateTime=" + dateTime + ", problem=" + problem + ", solution="
				+ solution + ", user=" + user + ", teller=" + teller + "]";
	}

}
