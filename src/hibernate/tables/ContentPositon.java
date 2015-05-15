package hibernate.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="content_position")
public class ContentPositon {
	@Id
	@Column(name="cp_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="cp_name")
	private String name;
	
	@ManyToOne											
	@JoinColumn(name = "content_id")					//как поле называется в таблице
	private Content contentId;
	
	@ManyToOne												
	@JoinColumn(name = "cph_id")							//как поле называется в таблице
	private ContentPositionHistory contentPositionHistoryId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentId == null) ? 0 : contentId.hashCode());
		result = prime
				* result
				+ ((contentPositionHistoryId == null) ? 0
						: contentPositionHistoryId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContentPositon other = (ContentPositon) obj;
		if (contentId == null) {
			if (other.contentId != null)
				return false;
		} else if (!contentId.equals(other.contentId))
			return false;
		if (contentPositionHistoryId == null) {
			if (other.contentPositionHistoryId != null)
				return false;
		} else if (!contentPositionHistoryId
				.equals(other.contentPositionHistoryId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return new StringBuffer()
			.append("ContentPositon [id=").append(id)
			.append(", name=").append(name)
			.append(", contentId=").append(contentId)
			.append(", contentPositionHistoryId=").append(contentPositionHistoryId)
			.append("]").toString();
	}	
	
	

}
