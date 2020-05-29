package challenge;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "scripts")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer episode;

	@Column(name = "episode_name")
	private String episodeName;

	private String segment;

	private String type;

	private String actor;

	private String character;

	private String detail;

	@Column(name = "record_date")
	private Date recordDate;

	private String series;

	@Column(name = "transmission_date")
	private Date transmissionDate;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getQuote() {
		return this.detail;
	}

	public void setQuote(String quote) {
		this.detail = quote;
	}

}
