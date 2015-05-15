package hibernate.tables;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "content_position_history")
public class ContentPositionHistory {
	@Id
	@Column(name = "cph_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	@Column(name = "cph_date")
	private Timestamp date;
	@Column(name = "cph_comment")
	private String comment;
	
	private Integer contentPositionId;
	
	private Integer userId;
	
	@OneToMany(mappedBy = "contentPositionHistoryId") 		// ссылка на поле в объекте ContentPosition
	private Set<ContentPositon> contentPositons;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime
				* result
				+ ((contentPositionId == null) ? 0 : contentPositionId
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
		if (contentPositionId == null) {
			if (other.contentPositionId != null)
				return false;
		} else if (!contentPositionId.equals(other.contentPositionId))
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
				.append("ContentPositionHistory [id=").append(id)
				.append(", date=").append(date)
				.append(", comment=").append(comment)
				.append(", contentPositionId=").append(contentPositionId)
				.append(", userId=").append(userId)
				.append("]").toString();
	}
	
	
	
	
	
}
