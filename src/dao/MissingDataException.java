package dao;

public class MissingDataException extends RuntimeException {

	
	private static final long serialVersionUID = 6813697254809460396L;
	
	public MissingDataException (Exception e) {
		super(e);
	}	
	
}