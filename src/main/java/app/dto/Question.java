package app.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
//	@JoinColumn
	private User writer;
	
	private String title;
	
	private String contents;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
	public Question() {}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = Calendar.getInstance().getTime();
	}
	
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		
		String pattern = "yyyy.MM.dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(this.createDate);
		return date;
	}

	public void update(String title2, String contents) {
		this.title = title;
		this.contents = contents;
		
	}

	public boolean isSameWriter(User loginUser) {
		
		return this.writer.equals(loginUser);
	}

	
	
	
	
}
