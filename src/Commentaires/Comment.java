package Commentaires;

public class Comment {
	private int id;
	private String comment;
	private int student_id;

	public Comment(){};

	public Comment(String _comment, int _student_id) {
		this.comment = _comment;
		this.student_id = _student_id;
	}

	public int getId () { return id;}
	public void setId (int _id) { this.id = _id;};
	public String getComment () { return comment;}
	public void setComment (String _comment) { this.comment = _comment;}
	public int getStudentId () { return student_id;}
	public void setStudentId (int _student_id) { this.student_id = _student_id;};
}
