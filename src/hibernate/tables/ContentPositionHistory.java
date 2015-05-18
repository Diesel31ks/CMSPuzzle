package hibernate.tables;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "content_position_history")
public class ContentPositionHistory implements Serializable, hibernate.tables.Table{
	private static final long serialVersionUID = 3341299262014786262L;
	@Id
	@Column(name = "cph_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	@Column(name = "cph_date")
	private Timestamp date;
	@Column(name = "cph_comment")
	private String comment;
	
	private Integer contentId;
	
	private Integer userId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime
				* result
				+ ((contentId == null) ? 0 : contentId
						.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		ContentPositionHistory other = (ContentPositionHistory) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (contentId == null) {
			if (other.contentId != null)
				return false;
		} else if (!contentId.equals(other.contentId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer()
				.append("ContentPositionHistoryDao [id=").append(id)
				.append(", date=").append(date)
				.append(", comment=").append(comment)
				.append(", contentId=").append(contentId)
				.append(", userId=").append(userId)
				.append("]").toString();
	}
	
	
}
