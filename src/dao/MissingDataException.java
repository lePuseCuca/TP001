package dao;

public class MissingDataException extends RuntimeException {

	private static final long serialVersionUID = -8623007721846988678L;
		
	public MissingDataException (Exception e) {
		super(e);
	}	
	
}