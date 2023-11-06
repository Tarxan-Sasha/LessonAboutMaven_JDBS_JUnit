package LessonAboutJDBC;

public class Workers_ss13 {

	private int idWorker;
	private String nameWorker;
	private int numberJob;
	private String raceWorker;

	Workers_ss13(String nw, int nj) {
		this.nameWorker = nw;
		this.numberJob = nj;

	}
	public int getIdWorker() {
		return idWorker;
	}

	public String getNameWorker() {
		return nameWorker;
	}

	public void setNameWorker(String nameWorker) {
		this.nameWorker = nameWorker;
	}

	public int getNumberJob() {
		return numberJob;
	}

	public void setNumberJob(int numberJob) {
		this.numberJob = numberJob;
	}

	public String getRaceWorker() {
		return raceWorker;
	}

	public void setRaceWorker(String raceWorker) {
		this.raceWorker = raceWorker;
	}

}
